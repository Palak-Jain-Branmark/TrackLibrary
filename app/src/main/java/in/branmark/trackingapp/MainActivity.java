package in.branmark.trackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;

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

            final Track track = new Track(getApplicationContext());
            track.log("library is working");


            String random = "12345678909876543212345678909876";
            TrackConfig config = new TrackConfig(getApplicationContext(),random, TrackConfig.ENVIRONMENT_DEVELOPMENT);
            Track.onCreate(config);



            /*String cookieString = "cookie_name=cookie_value; path=/";
            CookieManager.getInstance().setCookie(baseUrl, cookieString);*/

        final Button event_trigger =  findViewById(R.id.event_trigger);
        event_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                track.triggerEvent("Button Click ");
            }
        });

    }
}
