package tw.com.pcschool.t081901;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    StringRequest request = new StringRequest("http://gohiking.mwnl.tw/push/register.php?nickname=Teacher&token=" + regid,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request);
                    queue.start();

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
