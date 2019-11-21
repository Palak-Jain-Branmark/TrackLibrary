package in.branmark.track_event;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Utils {

    public static JSONObject deviceDetail(Context context) {
        JSONObject deviceDetailjs = new JSONObject();

        try {
            String advId = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
            String analyticsID = InstanceID.getInstance(context).getId();
            deviceDetailjs.put("PD_MODEL", android.os.Build.MODEL);
            deviceDetailjs.put("PD_SDK", android.os.Build.VERSION.SDK);
            deviceDetailjs.put("PD_FINGERPRINT", android.os.Build.FINGERPRINT);
            deviceDetailjs.put("PD_RELEASE", android.os.Build.VERSION.RELEASE);
            deviceDetailjs.put("PD_SERIAL", android.os.Build.SERIAL);
            deviceDetailjs.put("PD_ID", android.os.Build.ID);
            deviceDetailjs.put("PD_MANUFACTURER", android.os.Build.MANUFACTURER);
            deviceDetailjs.put("PD_TYPE", android.os.Build.TYPE);
            deviceDetailjs.put("PD_USER", android.os.Build.USER);
            deviceDetailjs.put("PD_BASE", android.os.Build.VERSION_CODES.BASE);
            deviceDetailjs.put("PD_INCREMENTAL", android.os.Build.VERSION.INCREMENTAL);
            deviceDetailjs.put("PD_ADVERTISINGID",advId);
            deviceDetailjs.put("PD_ANALYTICSID", analyticsID);

            Log.i("TAG", "MODEL: " + Build.MODEL);
            Log.i("TAG", "SDK  " + Build.VERSION.SDK);
            Log.i("TAG", "FINGERPRINT: " + Build.FINGERPRINT);
            Log.i("TAG", "Version Code: " + Build.VERSION.RELEASE);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        return deviceDetailjs;
    }

    public static String getGaidID(TrackConfig config){
        final String[] advertId1 = new String[1];
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {String advId = null;
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(config.context);
                    advId = adInfo.getId();
                    boolean isLAT = adInfo.isLimitAdTrackingEnabled();
                } catch (IOException|GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
                return advId;
            }

            @Override
            protected void onPostExecute(String advertId) {
                advertId1[0] = advertId;
                SessionManager session = new SessionManager(config.context);
                session.saveGaid(advertId);
                TrackConfig.gaid = advertId;
                Track.sendData(config,advertId);
                Log.d("UTILS","Gaid ID : "+advertId);
                Toast.makeText(config.context, advertId, Toast.LENGTH_SHORT).show();
            }

        };
        task.execute();
        return advertId1[0];

    }
    public static String getAnalyticsID(Context context){
        return InstanceID.getInstance(context).getId();
    }
}
