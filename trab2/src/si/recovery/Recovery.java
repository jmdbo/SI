package si.recovery;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import si.api.utils.BufferData;
import si.api.utils.ComplexInstruction;
import si.api.utils.Instruction;

/**
 * Created by Aires on 11-11-2014.
 */
public class Recovery implements Runnable {

    private static final String ERROR1 ="EntryReadyError";
    private static final String ERROR2 ="EntryLoadError";
    private static final String ERROR3 ="OccupiedStationError";
    private static final String ERROR4 ="NoPieceError";
    private static final String ERROR5 ="StorageFullError";
    private static final String ERROR6 ="StorageEmptyError";
    private static final String ERROR7 ="LostPieceOnRetrievalError";

    BufferData data;
    String errorType;
    public BlockingQueue<String>erros;
    
    
    private BlockingQueue<Instruction> Backup;
    private BlockingQueue<ComplexInstruction> ComplexBackup, ComplexBackup2;
    private ComplexInstruction CplxInst;
    
    public Recovery(BufferData _data) {
        data = _data;
        errorType = "";
    }

    public void setNewError(String errorType){
        this.errorType = errorType;
    }
     
    private boolean fixError1and2(){
        Backup.add(new Instruction(0, 1, 0, 0, "GOTO_STATION"));    //mete no get
        Backup.add(new Instruction(-1, 2, -1,-1, "STATION_GET"));   //mete o Y
        Backup.add(new Instruction(-1, -1, -1, 1, "STATION_GET"));  // levanta put
        Backup.add(new Instruction(-1, 1, -1, -1, "STATION_GET"));  //tira Y

        data.SimpleInstruction.drainTo(Backup);
        Backup.drainTo(data.SimpleInstruction);
        return true;
    }
    
    //armazem cheio, procurar complexInstrution 
    //de remover e passala para primeiro
    private boolean fixError5(){
        Iterator<ComplexInstruction> iteradorCI = data.ComplexInstruction.iterator();
        while(iteradorCI.hasNext()){
            CplxInst = iteradorCI.next();

            if (CplxInst.getOp().equals("GET_PIECE")){

                ComplexBackup2.add(CplxInst);
                ComplexBackup2.addAll(ComplexBackup);
                ComplexBackup2.addAll(data.ComplexInstruction);
                data.ComplexInstruction.addAll(ComplexBackup2);
                ComplexBackup.clear();
                ComplexBackup2.clear();
                return true;
            }
            ComplexBackup.add(CplxInst);
        }
        ComplexBackup.addAll(data.ComplexInstruction);
        data.ComplexInstruction.addAll(ComplexBackup);
        //super erro popup
        ComplexBackup.clear();
        ComplexBackup2.clear();
        return false;
    }
    
    //armazem vazio, procurar complexInstrution 
    //de colocar e passala para primeiro
    private boolean fixError6(){
        Iterator<ComplexInstruction> iteradorCI = data.ComplexInstruction.iterator();
        while(iteradorCI.hasNext()){
            CplxInst = iteradorCI.next();
            if (CplxInst.getOp().equals("PUT_PIECE")){
                ComplexBackup2.add(CplxInst);
                ComplexBackup2.addAll(ComplexBackup);
                ComplexBackup2.addAll(data.ComplexInstruction);
                data.ComplexInstruction.addAll(ComplexBackup2);
                ComplexBackup.clear();
                ComplexBackup2.clear();
                return true;
            }
            ComplexBackup.add(CplxInst);
        }
        ComplexBackup.addAll(data.ComplexInstruction);
        data.ComplexInstruction.addAll(ComplexBackup);
        //super erro popup
        ComplexBackup.clear();
        ComplexBackup2.clear();
        return false;
    }
    
    //procurar outra cell ocupada e retirar caixa
    private boolean fixError7(){
        int[] aux = new int [2];
        aux = data.ocuppiedcell();
        if(aux[0]==0 && aux[1]==0){
            //error warehouse completly empty
            return false;
        }
        ComplexBackup.add(new ComplexInstruction(aux[0],aux[1],"PUT_PIECE", 0, 0));
        ComplexBackup.addAll(data.ComplexInstruction);
        data.ComplexInstruction.addAll(ComplexBackup);
        ComplexBackup.clear();
        return true;
    }
    
    
    private boolean checkErrors (){
        if(errorType.equals(""))
            return false;
        
        if(errorType.equals(ERROR1) || errorType.equals(ERROR2))
            fixError1and2();
        
        if(errorType.equals(ERROR3));
            //super erro popup
            
        if(errorType.equals(ERROR4));
            //super erro popup
       
        if(errorType.equals(ERROR5))
            fixError5();
        
        if(errorType.equals(ERROR6))
            fixError6();
            
        //procurar outra cell ocupada e retirar caixa
        if(errorType.equals(ERROR7))
            fixError7();
        
        errorType = "";
        return true;
        
    }

    @Override
    public void run() {
       
        
        
            
        
        
        
        
    
    }

   
}
