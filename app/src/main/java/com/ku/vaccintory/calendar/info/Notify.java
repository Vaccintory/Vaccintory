package com.ku.vaccintory.calendar.info;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.MainCalendar;

public class Notify extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {


        Intent i = new Intent(context, MainCalendar.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(context,0,i,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"alarmRemind")
                .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                .setContentTitle("Vaccintory Remind Me")
                .setContentText("อีก จะต้องรับ")
                .setAutoCancel(false)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat nmc = NotificationManagerCompat.from(context);
        nmc.notify(200,builder.build());

    }
}
