package si.monitor;

import CLIPSJNI.Environment;
import si.api.utils.BufferData;

/**
 * Created by Aires on 11-11-2014.
 */
public class Monitor implements Runnable {

    public static BufferData bufferData;
    private static Environment clips;

    public Monitor(BufferData _bd) {
        this.bufferData = _bd;
        clips = new Environment();

        clips.load("RulesMonitor.CLP");
        clips.reset();
        System.out.println("get list" + clips.eval("(get-defrule-list)").toString());
    }

    private boolean error_conditions() {

        //ERRO1
        if (bufferData.pieceInStation() == 0) {
            clips.eval("(assert (PieceInStation (Position false)))");
        } else if (bufferData.pieceInStation() == 1) {
            clips.eval("(assert (PieceInStation (Position Left)))");
        } else if (bufferData.pieceInStation() == 2) {
            clips.eval("(assert (PieceInStation (Position Right)))");
        } else if (bufferData.pieceInStation() == 3) {
            clips.eval("(assert (PieceInStation (Position Right)))");
            clips.eval("(assert (PieceInStation (Position Left)))");
        }

        if (bufferData.posZ == 0) {
            if (bufferData.posX == 0) {
                clips.eval("(assert (ElevatorAtStation (Position Left)))");
            } else if (bufferData.posX == 9) {
                clips.eval("(assert (ElevatorAtStation (Position Right)))");
            } else {
                clips.eval("(assert (ElevatorAtStation (Position false)))");
            }
        } else {
            clips.eval("(assert (ElevatorAtStation (Position false)))");
        }

        //ERRO2
        //(PieceInElevator false)
        if (bufferData.pieceAtLift()) {
            clips.eval("(assert (PieceInElevator (State true)))");
        } else {
            clips.eval("(assert (PieceInElevator (State false)))");
        }

        //ERRO 4
        int freeCell[] = bufferData.nextfreecell();
        int fullCell[] = bufferData.ocuppiedcell();
        if(freeCell[0]==0 && freeCell[1]==0){
            clips.eval("(assert (StorageFull true))");
        }else{
            clips.eval("(assert (StorageFull false))");
        }
        if(fullCell[0]==0 && fullCell[1]==0){
            clips.eval("(assert (StorageEmpty true))");
        }else{
            clips.eval("(assert (StorageEmpty false))");
        }
        return true;
    }
    
    int getErro() {
        try {
            int TAM =clips.eval("(find-all-facts ((?E Erro)) TRUE)").size();
            if (TAM != 0) {
                return Integer.parseInt(clips.eval("(find-all-facts ((?E Erro)) TRUE)").get(0).getFactSlot("id").toString());
            }
        } catch (Exception ex) {
            System.out.println("Erro a ir buscar os erros");
        }
        return 0;
    }

    @Override
    public void run() {
        while (true) {
            String asrt;
            while (!bufferData.emergency) {
                clips.reset();
                if (bufferData.ComplexCurrentInstruction != null
                        && bufferData.SimpleCurrentInstruction != null) {
                    clips.eval("(assert (Action (complx "
                            + bufferData.ComplexCurrentInstruction.getOp()
                            + ") (simpl " + bufferData.SimpleCurrentInstruction.getOp()
                            + ")))");
                }
                error_conditions();
                
                clips.run();
                int erroId = getErro();
                if(erroId>0){
                    bufferData.emergency = true;
                    System.out.print("Erro detectado. Paragem de emergência ");
                    System.out.println(erroId);
                }
            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
    }
}
