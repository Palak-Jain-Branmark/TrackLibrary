package in.branmark.track_event;

import android.content.Context;
import android.os.Build;
import android.util.Log;

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

            //  Log.e("myDeviceDetail", "" + phone_detail);
            //  Log.i("TAG", "SERIAL: " + Build);
            Log.i("TAG", "MODEL: " + Build.MODEL);
            // Log.i("TAG", "ID: " + Build.ID);
            // Log.i("TAG", "Manufacture: " + Build.MANUFACTURER); // Log.i("TAG", "HOST " + Build.HOST);//  Log.i("TAG", "brand: " + Build.BRAND);//samsung
            // Log.i("TAG", "type: " + Build.TYPE);
            //  Log.i("TAG", "user: " + Build.USER);
            //  Log.i("TAG", "BASE: " + Build.VERSION_CODES.BASE);
            // Log.i("TAG", "INCREMENTAL " + Build.VERSION.INCREMENTAL);
            Log.i("TAG", "SDK  " + Build.VERSION.SDK);
            //  Log.i("TAG", "BOARD: " + Build.BOARD);
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

    public static String getGaidID(Context context){
        String advId = null;
        try {
            advId = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (IOException|GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        return advId;
    }
    public static String getAnalyticsID(Context context){
        String analyticsID = InstanceID.getInstance(context).getId();
        return analyticsID;
    }
}
