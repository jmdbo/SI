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

    public void updateInstructions() {
        System.out.println("NotYet Implemented");
    }

    public void putPieceAt(int x, int z) {

        // Get Piece From The Station if Missing
        if (!data.pieceAtLift()) {
            data.SimpleInstruction.add(new Instruction(0, 1, 0, "GOTO_STATION"));
            data.SimpleInstruction.add(new Instruction(-1, 2, -1, "STATION_GET"));
        }

        // Goto the required position
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, "STATION_MID"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, "STATION_MID"));

        // Put the piece in place
        data.SimpleInstruction.add(new Instruction(-1, 0, -1, "STATION_INSIDE"));

    }

    public void gotoXZ(int x, int z) {
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, "STATION_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, "STATION_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(-1, -1, -1, "FINISHED_COMPLEX"));
    }

    public void getPieceFrom(int x, int z) {
        //Go To Desired Position!
        data.SimpleInstruction.add(new Instruction(-1, 1, -1, "STATION_MIDDLE"));
        data.SimpleInstruction.add(new Instruction(x, -1, z, "STATION_MIDDLE"));
    }

    public void switchPiece(int x, int z, int x_dest, int z_dest) {
    }
}
