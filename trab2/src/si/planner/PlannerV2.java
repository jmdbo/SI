/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package si.planner;

import si.api.utils.BufferData;

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
        gotoXZ( 3 ,3);
    }
}
