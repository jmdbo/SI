package si.planner;

import si.api.utils.BufferData;
import si.api.utils.Instruction;

/**
 * Created by dd.fernandes on 22/10/2014.
 */
public class Planner {

    BufferData data;

    Planner(BufferData _data) {
        data = _data;
    }

    /**
     * @param asd
     */
    public void updateInstructions() {
        System.out.println("NotYet Implemented");
    }

    /**
     * isto faz bla bla bla
     * <img src="http://www.ipsantarem.pt/wp-content/uploads/2013/06/logo-fct.png">
     * @param x este parametr e' mesmo importante
     * @param z este nao vale nada
     * @param x_orig enfim
     * @param z_orig tou farto de discussoes
     */
	 public void putPieceAt(int x, int z, int x_orig,int z_orig) {
        // Get Piece From The Station if Missing
        data.SimpleInstruction.add(new Instruction(-1,-1,-1,-1, "PUT_PIECE_BEGIN"));
        if (!data.pieceAtLift()) {            
            data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MIDDLE"));
            data.SimpleInstruction.add(new Instruction(x_orig, -1, z_orig, 0, "GOTO_STATION"));
            data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "AT_STATION"));
            data.SimpleInstruction.add(new Instruction(-1, 2, -1,-1, "ELEVATOR_INSIDE"));
            data.SimpleInstruction.add(new Instruction(-1, -1, -1, 1, "STATION_GET"));
        }

        // Goto the required position
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MID"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "STATION_LOAD_DONE"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, 1, "GOTO_XZ"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "AT_CELL"));

        // Put the piece in place
        data.SimpleInstruction.add(new Instruction(-1, 0, -1,-1, "ELEVATOR_INSIDE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, 0, "CELL_PUT"));
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "CELL_PUT_DONE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX"));
    }

    public void gotoXZ(int x, int z) {
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "STATION_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, 1, "STATION_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX"));
    }

    public void getPieceFrom(int x, int z, int x_dest, int z_dest) {
        
        //Go To Desired Position!
        data.SimpleInstruction.add(new Instruction(-1,-1,-1,-1, "GET_PIECE_BEGIN"));
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, 0, "GOTO_XZ"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "AT_CELL"));

        //TAke the piece
        data.SimpleInstruction.add(new Instruction(-1, 0, -1,-1, "ELEVATOR_INSIDE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, 1, "CELL_GET"));
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "STATION_MID"));
        
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "CELL_GET_DONE"));
        data.SimpleInstruction.add(new Instruction(x_dest, -1, z_dest, 0, "GOTO_STATION"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "STATION_PUT_BEGIN"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, 1, "STATION_PUT_BEGIN"));
        data.SimpleInstruction.add(new Instruction(-1, 2, -1, -1, "ELEVATOR_OUTSIDE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, 0, "STATION_PUT"));
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "ELEVATOR_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "STATION_PUT_DONE"));
        
        data.SimpleInstruction.add(new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX"));
        
        
        
        
    }

    public void switchPiece(int x, int z, int x_dest, int z_dest) {
        if (!data.pieceAtLift()) {
            data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "STATION_MID"));
            data.SimpleInstruction.add(new Instruction(x, -1, z, 0, "GOTO_STATION"));
            data.SimpleInstruction.add(new Instruction(-1, 0, -1,-1, "CELL_GET"));
            data.SimpleInstruction.add(new Instruction(-1, -1, -1, 1, "CELL_GET"));
            data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "CELL_GET_DONE"));

        }

        // Goto the required position
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "STATION_MID"));
        data.SimpleInstruction.add(new Instruction(x_dest, -1, z_dest, 1, "GOTO_XZ"));

        // Put the piece in place
        data.SimpleInstruction.add(new Instruction(-1, 0, -1,-1, "STATION_INSIDE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, 0, "PUT_PIECE"));
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, -1, "STATION_MID"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, -1, "CELL_PUT_DONE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1,-1, "FINISHED_COMPLEX"));
    }
}
