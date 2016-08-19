package tw.com.pcschool.t081901;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Student on 2016/8/19.
 */
public class GcmIntentService extends IntentService {

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class),0);
        NotificationCompat.Builder builer = new NotificationCompat.Builder(this);

        builer.setTicker("Message");
        builer.setContentTitle("Title");
        builer.setContentText("Text");
        builer.setSmallIcon(R.mipmap.ic_launcher);
        builer.setContentIntent(pi);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builer.setSound(uri);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(123, builer.build());

    }
}
