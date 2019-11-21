package in.branmark.track_event;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionManager {


    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TRACK_APP";
    public static final String KEY_REFERER_ID = "referer";
    private static final String KEY_TOKEN_ID = "token";
    private static final String KEY_GAID = "token";
    private static final String KEY_ANALYST_ID = "token";
    private static final String KEY_DEVICE_SDK = "d_sdk";
    private static final String KEY_DEVICE_RELEASE = "d_release";
    private static final String KEY_DEVICE_MODEL = "d_model";
    private static final String KEY_DEVICE_FINGERPRINT = "d_fingerprint";

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
    public String gettoken(){
        return pref.getString(KEY_TOKEN_ID, null);
    }
    public String getGaidID(){
        return pref.getString(KEY_GAID, null);
    }

    public HashMap<String, String> getDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_REFERER_ID,pref.getString(KEY_REFERER_ID, null));
        return user;
    }

    public void save(String token, String gaid, String analyticsID) {
        editor.putString(KEY_TOKEN_ID, token);
        editor.putString(KEY_GAID, gaid);
        editor.putString(KEY_ANALYST_ID, analyticsID);
        editor.commit();
    }
    public void saveGaid( String gaid) {
        editor.putString(KEY_GAID, gaid);
        editor.commit();
    }

    public void saveDeviceDetail(String fingerprint, String sdk, String release, String model) {
        editor.putString(KEY_DEVICE_FINGERPRINT, fingerprint);
        editor.putString(KEY_DEVICE_SDK, sdk);
        editor.putString(KEY_DEVICE_RELEASE, release);
        editor.putString(KEY_DEVICE_MODEL, model);
        editor.commit();
    }
}
