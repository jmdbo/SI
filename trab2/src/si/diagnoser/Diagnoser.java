package si.diagnoser;
import CLIPSJNI.Environment;
import si.api.utils.*;

/**
 * Created by Aires on 11-11-2014.
 */
public class Diagnoser {


    public static BufferData bufferData;

    public Diagnoser(BufferData _bd){
        bufferData=_bd;
    }
    private static Environment clips;



    private Diagnoser() { }

    static public void main(String[] args){

        clips = new Environment();

        clips.load("RulesDiagnoser.CLP");
        clips.reset();
        System.out.println("get list" + clips.eval("(get-defrule-list)").toString());


    }





    private boolean error_conditions (){

        //ERRO1
        if (bufferData.getState(2).getBit(7) == 0)
            clips.eval("assert PieceInStation false");
        else clips.eval("assert PieceInStation true");

        if ((bufferData.posX==0&& bufferData.posZ== 00) || (bufferData.posX== 9 && bufferData.posZ==0) )
            clips.eval("assert ElevatorAtStation true");
        else clips.eval("assert ElevatorAtStation false");


        //ERRO2
        //(PieceInElevator false)
        if (bufferData.pieceAtLift())
            clips.eval("assert PieceInElevator true");
        else clips.eval("assert PieceInElevator false");



        return true;
    }

}
