package trab1;
import CLIPSJNI.Environment;

/**
 * Created by Aires on 11-11-2014.
 */
public class Diagnozer {


    public static BufferData bufferData;

    public Diagnozer (BufferData _bd){
        bufferData=_bd;
    }
    private static Environment clips;


    private Diagnozer() { }

    static public void main(String[] args){

        clips = new Environment();

        clips.load("RulesDiagnoser.CLP");
        clips.reset();
        System.out.println("get list" + clips.eval("(get-defrule-list)").toString());


    }





    private boolean error_conditions (){

        //ERRO1
        if (bufferData.pieceInStation() == 0)
            clips.eval("assert PieceInStation false");
        else clips.eval("assert PieceInStation true");

        if (bufferData.elevatorInStation()!=0)
            clips.eval("assert ElevatorAtStation true");
        else clips.eval("assert ElevatorAtStation false");


        //ERRO2
        //(PieceInElevator false)
        if (bufferData.hasPiece())
            clips.eval("assert PieceInElevator true");
        else clips.eval("assert PieceInElevator false");


        //ERRO 4
        if (bufferData.hasPiece())
            clips.eval("assert PieceInElevator true");
        else clips.eval("assert PieceInElevator false");

        if (bufferData.gety() == 2)
            clips.eval("assert ElevatorAtCell true");
        else clips.eval("assert ElevatorAtCell false");



        return true;
    }

}
