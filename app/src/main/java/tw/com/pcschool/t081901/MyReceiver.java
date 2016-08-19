package tw.com.pcschool.t081901;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle bData = intent.getExtras();
        if(bData.get("msg").equals("my_alarm_action"))
        {
            Log.d("0819", "Received!");

        }
    }
}
