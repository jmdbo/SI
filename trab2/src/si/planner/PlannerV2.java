/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package si.planner;

import java.util.logging.Level;
import java.util.logging.Logger;
import si.api.utils.BufferData;
import si.api.utils.ComplexInstruction;
/**
 *
 * @author Andre Ricardo
 */
public class PlannerV2 extends Planner implements Runnable{

    public PlannerV2(BufferData _data) {
        super(_data);
        data.ComplexCurrentInstructionDone = true;
    }

    @Override
    public void run() {
        ComplexInstruction complex;
        while(true){
            
            while (data.ComplexCurrentInstructionDone) {

                try {
                    complex = data.ComplexInstruction.take();
                    data.ComplexCurrentInstruction = complex;
                    data.ComplexCurrentInstructionDone = false;
                    System.out.println("Starting Complex Instruction : " + complex.getOp());
                    if ("PUT_PIECE".equals(complex.getOp())) {
                        putPieceAt(complex.getX(), complex.getZ(), complex.getX_dest(), complex.getZ_dest());
                    }
                    if ("GET_PIECE".equals(complex.getOp())) {
                        getPieceFrom(complex.getX(), complex.getZ(), complex.getX_dest(), complex.getZ_dest());
                    }
                    if ("SWITCH_PIECE".equals(complex.getOp())) {
                        switchPiece(complex.getX(), complex.getZ(), complex.getX_dest(), complex.getZ_dest());
                    }

                } catch (Exception e) {

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlannerV2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlannerV2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
