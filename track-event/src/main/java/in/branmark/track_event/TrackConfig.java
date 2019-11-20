package in.branmark.track_event;

import android.content.Context;

public class TrackConfig {


    public static final String KEY_SECTRET_KEY = "secret_key";
    public static final String KEY_INFO1 = "info1";
    public static final String KEY_INFO2 = "info2";
    public static final String KEY_INFO3 = "info4";
    public static final String KEY_INFO4 = "info5";
    public static int ENVIRONMENT_DEVELOPMENT = 1;
    public static int ENVIRONMENT_DPRODUCTION = 2;
    public String token;
    public Context context;
    public int environment;
    public String secretID;
    public String info1;
    public String info2;
    public String info3;
    public String info4;


    public TrackConfig(Context context, String Token, int envirement) {
        this.context = context;
        this.token = Token;
        this.environment = envirement;
    }
    
    public void setAppSecret(String secretId,String  info1,String  info2,String  info3,String  info4){
        this.secretID = secretId;
        this.info1 = info1;
        this.info2 = info2;
        this.info3 = info3;
        this.info4 = info4;
    }
}
