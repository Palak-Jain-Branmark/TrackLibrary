package in.branmark.track_event;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Track {

    private static String TAG = "TRACK_LIBRARY";
    public Context context;
    public SessionManager session;

    public Track(Context context) {
        this.context = context;
        this.session = new SessionManager(context);
    }

    public static void sendReferrer(String rawReferrer, Context context) {
        Track track = new Track(context);
        track.session.save_referer(rawReferrer);
        Log.d(TAG," Referer : "+rawReferrer);

        RetrofitClientInstance.GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitClientInstance.GetDataService.class);
        Call<ResponseBody> call = service.send_referer(track.session.gettoken(),
                track.session.getGaidID(),
                rawReferrer
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "Response Body: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error Response" +  t.getMessage());
            }
        });
    }

    public void log(String msg){
        Log.d(TAG,msg);
    }


    public static void onCreate(final TrackConfig trackConfig){

        HashMap<String,String> secretInfo = new HashMap<>();
        secretInfo.put(TrackConfig.KEY_SECTRET_KEY,trackConfig.secretID);
        secretInfo.put(TrackConfig.KEY_INFO1,trackConfig.info1);
        secretInfo.put(TrackConfig.KEY_INFO2,trackConfig.info2);
        secretInfo.put(TrackConfig.KEY_INFO3,trackConfig.info3);
        secretInfo.put(TrackConfig.KEY_INFO4,trackConfig.info4);

        trackConfig.secretInfo = secretInfo;
        Track track = new Track(trackConfig.context);

        String gaid = Utils.getGaidID(trackConfig);
        String analyticsID = Utils.getAnalyticsID(trackConfig.context);

        Log.e(TAG," token " + trackConfig.token);
        Log.e(TAG," Gaid " + gaid);
        Log.e(TAG," analyticsID " + analyticsID);
        Log.e(TAG," referer " + track.session.getreferer());

        track.session.save(trackConfig.token,gaid,analyticsID);
        track.session.saveDeviceDetail(android.os.Build.FINGERPRINT,android.os.Build.VERSION.SDK,android.os.Build.VERSION.RELEASE,android.os.Build.MODEL);
    }

    public static void sendData(TrackConfig trackConfig,String gaid){
        RetrofitClientInstance.GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitClientInstance.GetDataService.class);
        Call<ResponseBody> call = service.post_request(trackConfig.token,gaid
                ,android.os.Build.FINGERPRINT,android.os.Build.VERSION.SDK,android.os.Build.VERSION.RELEASE,android.os.Build.MODEL,
                trackConfig.secretInfo
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                responseBody(response.body(),trackConfig);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(trackConfig.context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error Response " +  t.getMessage());
            }
        });
    }

    private static void responseBody(ResponseBody body,TrackConfig trackConfig) {
        try {String resBody = body.string();
            Log.i(TAG, "Response Body: " + resBody);
            /*JSONObject outputjs = null;
            outputjs = new JSONObject(resBody);
            if (outputjs.getString("stat").equals("1")) {

            } else {
                Toast.makeText(trackConfig.context, "Error: Please Login Again" + outputjs.getString("response"), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error Response" + outputjs.getString("response"));
            }*/
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public void triggerEvent(String eventName){
        RetrofitClientInstance.GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitClientInstance.GetDataService.class);
        Call<ResponseBody> call = service.event_trigger(this.session.getreferer(),this.session.getGaidID(),
                eventName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String resBody = null;
                try {
                    resBody = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Response Body: " + resBody);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(trackConfig.context, "Error: Please Login Again" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error Response " +  t.getMessage());
            }
        });
    }
}
