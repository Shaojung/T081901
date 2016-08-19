package tw.com.pcschool.t081901;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String SENDER_ID = "518538475960";
    private GoogleCloudMessaging gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                super.run();
                gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
                try {
                    String regid = gcm.register(SENDER_ID);
                    Log.d("GCM", regid);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    public void click1(View v)
    {
        Calendar cal = Calendar.getInstance();
        // 設定於 3 分鐘後執行
        cal.add(Calendar.SECOND, 10);

        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("msg", "my_alarm_action");

        PendingIntent pi = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
    }
}
