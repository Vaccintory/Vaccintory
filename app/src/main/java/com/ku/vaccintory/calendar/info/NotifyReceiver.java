package com.ku.vaccintory.calendar.info;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ku.vaccintory.Main.MenuNotifyReceiver;
import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunc;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class NotifyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        String date = intent.getStringExtra("key");
        int AlarmID = intent.getIntExtra("alarmID",0);


        DateFormat df = new SimpleDateFormat("HH");
        String timeNow = df.format(Calendar.getInstance().getTime());

        int time = Integer.parseInt(timeNow);
        if( (time > 0 && time < 3) && (checkXDay(7,date) || checkXDay(1,date) || checkXDay(0,date) ) ){
            try {

                String dateTextForm = date.replaceAll("[-]", "/");

                Intent intent1 =  new Intent(context, EditInfo.class);

                PendingIntent p = PendingIntent.getActivity(context, 0, intent1, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setTicker("new message");
                builder.setContentTitle("Vaccintory Reminder");
                builder.setContentText("แจ้งเตือนการรับวัคซีนในวันที่ "+dateTextForm);
                if( checkXDay(0,date) )
                    builder.setContentText("แจ้งเตือนการรับวัคซีน, วันนี้!!");
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

    private boolean checkXDay(int day,String date){

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateTarget = sdfDate.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(Objects.requireNonNull(sdf.parse(dateTarget)));
        } catch (ParseException e) {
            Log.i("checkXDay","Error "+e.getMessage()+"*****************");
        }
        c.add(Calendar.DATE, day);  // number of days to add
        dateTarget = sdf.format(c.getTime());  // is now the new date

        return dateTarget.equals(date);

    }


}

