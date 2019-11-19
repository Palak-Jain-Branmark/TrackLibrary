package in.branmark.track_event;

import android.content.Context;
import android.util.Log;

public class Track {

    private static String TAG = "TRACK_LIBRARY";
    public Context context;

    public static void sendReferrer(String rawReferrer, Context context) {
        Track track = new Track();
        track.context = context;
        Log.d(TAG,rawReferrer);
    }

    public void log(String msg){
        Log.d(TAG,msg);
    }

    public static void onCreate(TrackConfig trackConfig){

    }
}
