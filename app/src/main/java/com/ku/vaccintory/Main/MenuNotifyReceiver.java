package com.ku.vaccintory.Main;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ku.vaccintory.Main.MainActivity;
import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunc;
import com.ku.vaccintory.calendar.info.EditInfo;


public class MenuNotifyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        String date = intent.getStringExtra("key");
        int AlarmID = intent.getIntExtra("alarmID",0);

        try {

            String dateTextForm = date.replaceAll("[-]", "/");

            Intent intent1 =  new Intent(context, EditInfo.class);

            PendingIntent p = PendingIntent.getActivity(context, 0, intent1, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setTicker("new message");
            builder.setContentTitle("Vaccintory Reminder");
            builder.setContentText("Notification of vaccination on "+dateTextForm);
            builder.setSmallIcon(R.drawable.ic_baseline_alarm_24);
            builder.setContentIntent(p);
            builder.setAutoCancel(false);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(AlarmID,builder.build());


        }catch (Exception e){
            Log.i("date","error == "+e.getMessage()+"*****************");
        }



    }


}

