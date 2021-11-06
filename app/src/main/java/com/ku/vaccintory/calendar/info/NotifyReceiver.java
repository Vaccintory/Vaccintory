package com.ku.vaccintory.calendar.info;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotifyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String date = sdfDate.format(new Date());
            String dateTextForm = date.replaceAll("[-]", "/");

            if (date.equals(DateClass.getYourDate())){
                Intent intent1 =  new Intent(context, EditInfo.class);
                createNotification(context, intent1, "new message", "Vaccintory Reminder", "Notification of vaccination on "+dateTextForm);
            }

        }catch (Exception e){
            Log.i("date","error == "+e.getMessage()+"*****************");
        }



    }

    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence description){

        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.ic_baseline_alarm_24);
        builder.setContentIntent(p);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200,builder.build());


    }
}

