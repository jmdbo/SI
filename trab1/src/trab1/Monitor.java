package trab1;
import CLIPSJNI.Environment;

/**
 * Created by João on 21/10/2014.
 */
public class Monitor {

    public static BufferData bufferData;

    public Monitor(BufferData _bd){
        bufferData=_bd;
    }
    private static Monitor ourInstance = new Monitor();

    public static Monitor getInstance() {
        return ourInstance;
    }

    private Monitor() { }

    static public void main(String[] args){

        Environment environment = new Environment();

        environment.load("RulesMonitor.CLP");
        environment.reset();
        System.out.println("OLA" + environment.eval("(get-defrule-list)").toString());


    }


    private boolean error_conditions (){

        //ERRO1
        //(PieceInEntryStation false)
        if ((bufferData.pieceInStation() == 0)
        //(ElevatorAtEntryStation true)
        & (bufferData.elevatorInStation()!=0)
        //(LoadingFromStationToElevator true)
        & (true)){}


        //ERRO2
        //(PieceInElevator false)
        if ((bufferData.hasPiece())
        //(ElevatorLoadComplete true)
        &(true)
        //(LoadingFromStationToElevator true)
        &(true)){}




        return true;
    }
}
