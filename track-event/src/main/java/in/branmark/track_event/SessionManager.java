package in.branmark.track_event;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {

    public static final String FIREBASE_TOKEN = "firebase_token";
    private static final String IS_TOKEN_SAVED = "is_token_saved";

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 501;
    public static final String TOPIC = "news";
    //public static final String SHARED_PREF = "mailer_firebase";
    public static final String ACCOUNT = "account" ;


    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TRACK_APP";
    public static final String KEY_REFERER_ID = "user_id";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void save_referer(String referrer){
        editor.putString(KEY_REFERER_ID, referrer);
        editor.commit();
    }
    public String getreferer(){
        return pref.getString(KEY_REFERER_ID, null);
    }

    public HashMap<String, String> getDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_REFERER_ID,pref.getString(KEY_REFERER_ID, null));
        return user;
    }

}
