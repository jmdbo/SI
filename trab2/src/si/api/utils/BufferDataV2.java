package si.api.utils;

/**
 * Created by dd.fernandes on 04/11/2014.
 */
public class BufferDataV2 extends BufferData {

    private void updateXValue() {
        for (int i = 0; i < 8; i++)
            if (getState(0).getBit(i) == 0)
                posX = i;
        if (getState(1).getBit(0) == 0)
            posX = 8;
        if (getState(1).getBit(1) == 0)
            posX = 9;
    }

    private void updateYValue() {
        if (getState(1).getBit(2) == 0)
            posY = 2;
        if (getState(1).getBit(3) == 0)
            posY = 1;
        if (getState(1).getBit(4) == 0)
            posY = 0;
    }

    private void updateZValue(){
        if( getState(1).getBit(5) == 0)
            posZ = 9;
        if( getState(1).getBit(6) == 0)
            posZ = 8;
        if( getState(1).getBit(7) == 0)
            posZ = 7;        
        for(int i = 0 ; i < 7 ; i++)
            if(getState(2).getBit(i) == 0)
                posZ = 6 - i;
    }

    @Override
    public void runAtUpdate() {
        updateXValue();
        updateYValue();
        updateZValue();
    }
}
