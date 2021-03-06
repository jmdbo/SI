package si.recovery;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import si.api.utils.BufferData;
import si.api.utils.ComplexInstruction;
import si.api.utils.Instruction;

/**
 * Created by Aires on 11-11-2014.
 */
public class Recovery implements Runnable {

    private static final int ERROR1 =1;
    private static final int ERROR2 =2;
    private static final int ERROR3 =3;
    private static final int ERROR4 =4;
    private static final int ERROR5 =5;
    private static final int ERROR6 =6;
    private static final int ERROR7 =7;

    BufferData data;
    int errorType;

    public BlockingQueue<Integer> erros;
    
    
    private BlockingQueue<Instruction> Backup;
    private BlockingQueue<ComplexInstruction> ComplexBackup;
    private ComplexInstruction CplxInst;
    
    public Recovery(BufferData _data) {
        data = _data;
        errorType = 0;
        Backup = new ArrayBlockingQueue<>(100);
        ComplexBackup = new ArrayBlockingQueue<>(100);
    }
    
    private boolean fixError1(){
        while(!pieceAtElevatorEstation()){
            try{
                Thread.sleep(1000);
                
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        data.emergency = false;
        data.diagnosed = false;
        data.corrected = false;
        System.out.println(data.SimpleCurrentInstruction.getOp());
        return true;
    }

    private boolean fixError2(){

        
        Backup.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MIDDLE")); 
        Backup.add(new Instruction(-1, -1, -1, 0, "GOTO_STATION"));     //mete no get
        Backup.add(new Instruction(-1, 2, -1,-1, "ELEVATOR_INSIDE"));   //mete o Y
        Backup.add(new Instruction(-1, -1, -1, 1, "STATION_GET"));      // levanta 
        Backup.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MID"));     //tira Y
        Backup.add(new Instruction(-1, -1, -1, -1, "STATION_LOAD_DONE")); 
        
        data.SimpleInstruction.drainTo(Backup);
        Backup.drainTo(data.SimpleInstruction);
        data.emergency = false;
        data.diagnosed = false;
        data.corrected = false;
        return true;
    }
    
    //armazem cheio, procurar complexInstrution 
    //de remover e passala para primeiro
    private boolean fixError5(){
        boolean solved = false;
        Iterator<ComplexInstruction> iteradorCI = data.ComplexInstruction.iterator();
        while(iteradorCI.hasNext() && !solved){
            CplxInst = iteradorCI.next();
            if (CplxInst.getOp().equals("GET_PIECE")){
                ComplexBackup.add(CplxInst);
                iteradorCI.remove();
                ComplexBackup.add(data.ComplexCurrentInstruction);
                data.ComplexInstruction.drainTo(ComplexBackup);
                ComplexBackup.drainTo(data.ComplexInstruction);
                ComplexBackup.clear();
                
                //clear blocking queue 
                data.ComplexCurrentInstruction = CplxInst;
                data.SimpleCurrentInstruction = new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX");
                data.SimpleInstruction.clear();
                data.emergency = false;
                data.diagnosed = false;
                data.corrected = false;
                data.ComplexCurrentInstructionDone = true;
                solved = true;
                return true;
            }
        }
         if(solved)
            return true;
        
        //super POPUP
        
        data.ComplexCurrentInstructionDone=true;
        data.gui.jErrorId.setText("Armazem Cheio");
        data.gui.setVisible(true);
        data.SimpleInstruction.clear();

        return false;
    }
    
    //armazem vazio, procurar complexInstrution 
    //de colocar e passala para primeiro
    private boolean fixError6(){
        Iterator<ComplexInstruction> iteradorCI = data.ComplexInstruction.iterator();
        boolean solved = false;
        while(iteradorCI.hasNext() && !solved){
            CplxInst = iteradorCI.next();
            if (CplxInst.getOp().equals("PUT_PIECE")){
                ComplexBackup.add(CplxInst);
                iteradorCI.remove();
                ComplexBackup.add(data.ComplexCurrentInstruction);
                data.ComplexInstruction.drainTo(ComplexBackup);
                ComplexBackup.drainTo(data.ComplexInstruction);
                ComplexBackup.clear();
                
                //clear blocking queue 
                data.ComplexCurrentInstruction = CplxInst;
                data.SimpleCurrentInstruction = new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX");
                data.SimpleInstruction.clear();
                data.emergency = false;
                data.diagnosed = false;
                data.corrected = false;
                data.ComplexCurrentInstructionDone = true;
                solved = true;
                return true;
            }
        }
        if(solved)
            return true;
        //super erro popup
        //super POPUP
        
        
        data.gui.jErrorId.setText("Armazem Vazio");
        data.gui.setVisible(true);
        
        
        //clear blocking queue 
        data.ComplexCurrentInstructionDone = true;
        data.SimpleInstruction.clear();
        return false;
    }
    
    //procurar outra cell ocupada e retirar caixa
    private boolean fixError7(){
        int[] aux;
        aux = data.ocuppiedcell();
        if(aux[0]==0 && aux[1]==0){
            //error warehouse completly empty
            //super POPUP
            data.gui.jErrorId.setText("Armazem Vazio");
            data.gui.setVisible(true);
            return false;
        }
        
        data.SimpleInstruction.clear();
        
        int x_dest = data.ComplexCurrentInstruction.getX_dest();
        int z_dest = data.ComplexCurrentInstruction.getZ_dest();
        int z = (aux[1]-1)*2;
        int x = aux[0]-1;
        
        System.out.println("NOVA INSTRUÇÃO DO GET_PIECE PARA X Z" + (aux[0])+" "+ (aux[1]) );
        
        ComplexBackup.add(new ComplexInstruction(x,z,"GET_PIECE", x_dest, z_dest));
        data.ComplexInstruction.drainTo(ComplexBackup);
        ComplexBackup.drainTo(data.ComplexInstruction);
        data.SimpleCurrentInstruction = new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX");        
        
        data.emergency = false;
        data.diagnosed = false;
        data.corrected = false;
        data.ComplexCurrentInstructionDone = true;
        
        return true;
    }
    
    
    private boolean checkErrors (){
        if(errorType==0)
            return false;
        if(errorType==1)
            fixError1();
        if(errorType==2)
            fixError2();
        
        if(errorType==3){
            data.gui.jErrorId.setText("Estação de saída ocupada");
            data.gui.setVisible(true);
        }
        
        if(errorType==4){
            data.gui.jErrorId.setText("Ausência de peça no Elevador");
            data.SimpleInstruction.clear();
            data.SimpleInstruction.add(new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX"));
            data.gui.setVisible(true);
        }
            //super erro popup
        
       
        if(errorType==5)
            fixError5();
        
        if(errorType==6)
            fixError6();
            
        //procurar outra cell ocupada e retirar caixa
        if(errorType==7)
            fixError7();
        
        errorType = 0;
        return true;
        
    }

    @Override
    public void run() {
       
        while(true){
        
            while(data.diagnosed && !data.corrected){
                errorType = data.errorID;
                data.corrected=true;
                checkErrors();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Recovery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean pieceAtElevatorEstation() {
        System.out.println("posX:" + data.posX + " posZ:" +data.posZ + " Piece in Station :"+data.pieceInStation() );
       if(data.pieceInStation()==3 && data.posZ == 0 && (data.posX==0 || data.posX==9))
           return true;
       if(data.pieceInStation()==1 && data.posZ == 0 && data.posX==0)
           return true;
       if(data.pieceInStation()==2 && data.posZ == 0 && data.posX==9)
           return true;
       
       return false;
    }
}
