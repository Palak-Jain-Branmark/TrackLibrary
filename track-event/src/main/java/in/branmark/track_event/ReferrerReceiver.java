package in.branmark.track_event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReferrerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String rawReferrer = intent.getStringExtra("referrer");
        if (null != rawReferrer) {
            Track.sendReferrer(rawReferrer, context);
        }
    }
}
