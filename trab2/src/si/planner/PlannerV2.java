/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package si.planner;

import si.api.utils.BufferData;
import si.api.utils.ComplexInstruction;
/**
 *
 * @author Andre Ricardo
 */
public class PlannerV2 extends Planner implements Runnable{

    public PlannerV2(BufferData _data) {
        super(_data);
    }

    @Override
    public void run() {
        ComplexInstruction complex;
        while(true){

            try {
                complex = data.ComplexInstruction.take();
                data.ComplexCurrentInstruction = complex;
                System.out.println("Starting Complex Instruction : "+complex.getOp());
                if("PUT_PIECE".equals(complex.getOp()))
                    putPieceAt(complex.getX(),complex.getZ());
                if("GET_PIECE".equals(complex.getOp()))
                    getPieceFrom(complex.getX(), complex.getZ());
                if("SWITCH_PIECE".equals(complex.getOp()))
                    switchPiece(complex.getX(), complex.getZ(), complex.getX_dest(), complex.getZ_dest());

            }catch (Exception e){

            }


        }
    }




}
