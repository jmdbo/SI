package si.dispatcher;

import si.api.utils.BufferData;

/**
 * Created by dd.fernandes on 30/09/2014.
 */
public class Dispatcher {

    BufferData data;

    public Dispatcher(BufferData bufferdata) {
        this.data = bufferdata;
    }

    /**
     * Move the Hardware in the XZ Axis
     *
     * @param move_x ( -1 , 0 , 1 )  = ( Left , Stop , Right )
     * @param move_z ( -1 , 0 , 1 )  = ( Down , Stop , Up )
     */
    public void axis_xz(int move_x, int move_z) {
        switch (move_x) {
            case -1:
                data.newState[4].setBit(0, 0);
                data.newState[4].setBit(1, 1);
                break;
            case 0:
                data.newState[4].setBit(0, 0);
                data.newState[4].setBit(1, 0);
                break;
            case 1:
                data.newState[4].setBit(0, 1);
                data.newState[4].setBit(1, 0);
                break;
        }

        switch (move_z) {
            case -1:
                data.newState[4].setBit(5, 0);
                data.newState[4].setBit(6, 1);
                break;
            case 0:
                data.newState[4].setBit(5, 0);
                data.newState[4].setBit(6, 0);
                break;
            case 1:
                data.newState[4].setBit(5, 1);
                data.newState[4].setBit(6, 0);
                break;
        }
    }

    /**
     * Move the Hardware in the Y Axis
     *
     * @param move_y ( -1 , 0 , 1 ) = ( In , Stop , Out )
     */
    public void axis_y(int move_y) {
        switch (move_y) {
            case -1:
                data.newState[4].setBit(3, 0);
                data.newState[4].setBit(4, 1);
                break;
            case 0:
                data.newState[4].setBit(3, 0);
                data.newState[4].setBit(4, 0);
                break;
            case 1:
                data.newState[4].setBit(3, 1);
                data.newState[4].setBit(4, 0);
                break;
        }
    }

    /**
     * Move the IO left in or out
     *
     * @param move_IO ( -1 , 0 , 1 ) = ( In , stop , Out )
     */
    public void station_left(int move_IO) {
        switch (move_IO) {
            case -1:
                data.newState[4].setBit(7, 0);
                data.newState[5].setBit(0, 1);
                break;
            case 0:
                data.newState[4].setBit(7, 0);
                data.newState[5].setBit(0, 0);
                break;
            case 1:
                data.newState[4].setBit(7, 1);
                data.newState[5].setBit(0, 0);
                break;
        }
    }

    /**
     * Move the IO Right in or out
     *
     * @param move_IO ( -1 , 0 , 1 ) = ( In , stop , Out )
     */
    public void station_right(int move_IO) {
        switch (move_IO) {
            case -1:
                data.newState[5].setBit(1, 0);
                data.newState[5].setBit(2, 1);
                break;
            case 0:
                data.newState[5].setBit(1, 0);
                data.newState[5].setBit(2, 0);
                break;
            case 1:
                data.newState[5].setBit(1, 1);
                data.newState[5].setBit(2, 0);
                break;
        }
    }

}
