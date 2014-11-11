package si.recovery;
import CLIPSJNI.Environment;
import si.api.utils.BufferData;

/**
 * Created by Aires on 11-11-2014.
 */
public class Recovery {

    private static String ERROR1 ="EntryReadyError";
    private static String ERROR2 ="EntryLoadError";
    private static String ERROR3 ="OccupiedStationError";
    private static String ERROR4 ="NoPieceError";
    private static String ERROR5 ="StorageFullError";
    private static String ERROR6 ="StorageEmptyError";
    private static String ERROR7 ="LostPieceOnRetrievalError";

    BufferData data;
    private static Environment clips;

    Recovery(BufferData _data) {
        data = _data;


        clips = new Environment();
        clips.load("RulesDiagnoser.CLP");
    }


    void Error_recovery(){

        String ERROR;

        //devolve o primeiro erro que encontra
        ERROR = clips.eval("find-fact ((?e error)) ERROR").toString();

        if(ERROR.equals(ERROR1)){
            //RELOAD INTO LIFT



        }

        if(ERROR.equals(ERROR2));

        if(ERROR.equals(ERROR3));

        if(ERROR.equals(ERROR4));//IRRECOPERÁVEL

        if(ERROR.equals(ERROR5));

        if(ERROR.equals(ERROR6));

        if(ERROR.equals(ERROR7));



    }

}
