package in.branmark.trackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.CookieManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.branmark.track_event.Track;
import in.branmark.track_event.TrackConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Track track = new Track(getApplicationContext());
            track.log("library is working");


            MessageDigest mdEnc = MessageDigest.getInstance("MD5");

            String random = "1234";
            mdEnc.update(random.getBytes());
            String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
            TrackConfig config = new TrackConfig(getApplicationContext(),md5, TrackConfig.ENVIRONMENT_DEVELOPMENT);
            Track.onCreate(config);



            /*String cookieString = "cookie_name=cookie_value; path=/";
            CookieManager.getInstance().setCookie(baseUrl, cookieString);*/
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
