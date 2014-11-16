package si;

import CLIPSJNI.Environment;
import si.api.utils.BufferData;
import si.api.utils.BufferDataV2;
import si.api.utils.HwByte;
import si.dispatcher.Dispatcher;
import si.dispatcher.DispatcherV2;

import java.nio.Buffer;

import si.monitor.Monitor;
import si.planner.PlannerV2;

/**
 * Created by dd.fernandes on 07/10/2014.
 */
public class test {


    public static void main(String[] args) throws InterruptedException {

        BufferData bd = new BufferDataV2();

        Thread.sleep(1000);

        new Thread(new DispatcherV2(bd)).start();
        
        new Thread(new PlannerV2(bd)).start();
        new Thread(new Monitor(bd)).start();
        Environment clips = new Environment();
        Environment clips2 = new Environment();
    }

}
