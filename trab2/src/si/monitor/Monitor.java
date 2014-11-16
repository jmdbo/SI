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
        if (bufferData.pieceInStation() == 0)
            clips.eval("(assert (PieceInStation false))");
        else clips.eval("(assert (PieceInStation true))");

        if (bufferData.posZ == 0){
            if(bufferData.posX == 0) {
                clips.eval("(assert (ElevatorAtStation Left))");
            }
            else if( bufferData.posX==9){
                clips.eval("(assert (ElevatorAtStation Right))");
            }
            else clips.eval("(assert (ElevatorAtStation false))");
        } else{
            clips.eval("(assert (ElevatorAtStation false))");
        }


        //ERRO2
        //(PieceInElevator false)
        if (bufferData.pieceAtLift())
            clips.eval("(assert (PieceInElevator true))");
        else clips.eval("(assert (PieceInElevator false))");


        //ERRO 4
        String Asrt;
        Asrt = "(assert (Position (x ";
        Asrt+= bufferData.posX +") (y ";
        Asrt+= bufferData.posY +") (z ";
        Asrt+= bufferData.posZ +")))";
        clips.eval(Asrt);

        return true;
    }

    @Override
    public void run() {
        while(true){
            String asrt;
            while(!bufferData.emergency){
                clips.reset();
                if(bufferData.ComplexCurrentInstruction!=null) {
                    asrt = "(assert (ComplexAction ";
                    asrt += bufferData.ComplexCurrentInstruction.getOp();
                    if (bufferData.ComplexCurrentInstructionDone) {
                        asrt += "_DONE";
                    }
                    asrt += "))";
                    System.out.println("Sending to CLIPS: " + asrt );
                    clips.eval(asrt);
                }
                if(bufferData.SimpleCurrentInstruction!=null){
                    asrt = "(assert (SimpleAction " + bufferData.SimpleCurrentInstruction.getOp();
                    if (bufferData.SimpleCurrentInstructionDone) {
                        asrt += "_DONE";
                    }
                    asrt += "))";
                    System.out.println("Sending to CLIPS: " + asrt);
                    clips.assertString(asrt);
                    error_conditions();
                }
                try {
                    Thread.sleep(4000);
                }catch (Exception e){

                }
                try {
                    Thread.sleep(4000);
                }catch (Exception e){

                }
            }



        }

    }
}