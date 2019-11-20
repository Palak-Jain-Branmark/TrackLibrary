package in.branmark.mailmart;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.firebase.messaging.FirebaseMessaging;

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
    private static final String PREF_NAME = "MailerPref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
   // public static final String KEY_USER_DETAIL = "userdetail";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_EMAIL = "useremail";
    public static final String KEY_USER_SITEID = "siteid";
    public static final String KEY_USER_EXPIRYTIME = "expire_time";
    public static final String KEY_USER_LOGINKEY = "loginkey";

    public static final String DATABASE_NAME = "Mailer_db";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void userLoggedIn(boolean isLoggedIn){
        editor.putBoolean(IS_LOGIN, isLoggedIn);
        editor.commit();
    }
    /**
     * Create login session
     * */
    public void createLoginSession(String uid){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_ID, uid);
       /*editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_EMAIL, email_id);
        editor.putString(KEY_USER_SITEID, siteID);
        editor.putString(KEY_USER_EXPIRYTIME, expiryTime);
//, String userName, String email_id, String siteID, String expiryTime*/
        editor.commit();
    }
    public String getUserId(){
        return pref.getString(KEY_USER_ID , null);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_ID,pref.getString(KEY_USER_ID , null));
        //user.put(KEY_USER_NAME,pref.getString(KEY_USER_NAME , null));
        //user.put(KEY_USER_EMAIL,pref.getString(KEY_USER_EMAIL , null));
        //user.put(KEY_USER_SITEID,pref.getString(KEY_USER_SITEID , null));
        //user.put(KEY_USER_EXPIRYTIME,pref.getString(KEY_USER_EXPIRYTIME , null));
        // return user
        return user;
    }
    /*** Check login method wil check user login status* If false it will redirect user to login page* Else won't do anything* */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(i);
        }
    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
        // unsubscribe for firebase
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC);

    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void saveFirebaseToken(String FirebaseToken){
        editor.putBoolean(IS_TOKEN_SAVED, true);
        editor.putString(FIREBASE_TOKEN,FirebaseToken);
        editor.commit();
    }
    public String firebaseToken(){
        return pref.getString(FIREBASE_TOKEN , null);
    }
    public boolean isFirebaseTokenSaved(){
        return pref.getBoolean(IS_TOKEN_SAVED , false);

    }
}
