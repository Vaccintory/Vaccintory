package com.ku.vaccintory.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ku.vaccintory.calendar.InfoFunc;
import com.ku.vaccintory.calendar.MainCalendar;
import com.ku.vaccintory.chatbot.MainChatBot;
import com.ku.vaccintory.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout buttonCalendar;
    private LinearLayout buttonChatBot;
    private static int alarmID=1;
    private static boolean alert=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalendar = (LinearLayout) findViewById(R.id.buttonLayoutCalendar);
        buttonCalendar.setOnClickListener(this);
        buttonChatBot = (LinearLayout) findViewById(R.id.buttonLayoutChatBot);
        buttonChatBot.setOnClickListener(this);


        if( !alert ){
            checkNotification();
        }



    }

    private void checkNotification(){

        alert = true;
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateToday = sdfDate.format(new Date());
        String fileName = dateToday+".txt";
        notify(dateToday,fileName);


        checkXDay(1);

        checkXDay(7);


    }

    private void checkXDay(int day){

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateTarget = sdfDate.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(Objects.requireNonNull(sdf.parse(dateTarget)));
        } catch (ParseException e) {
            Toast.makeText(this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        c.add(Calendar.DATE, day);  // number of days to add
        dateTarget = sdf.format(c.getTime());  // is now the new date

        notify(dateTarget,dateTarget+".txt");

    }

    private void notify(String date,String fileName){

        if( InfoFunc.isFileExist(this,fileName) ){
            String rawData = InfoFunc.loadInfo(this,fileName);
            assert rawData != null;

            if( InfoFunc.getInfo_Check(rawData).contains("true") )
            {

                Intent intent = new Intent(this, MenuNotifyReceiver.class);
                intent.putExtra("key",date);
                intent.putExtra("alarmID",alarmID);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,alarmID,intent,0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long timeNow = System.currentTimeMillis();
                alarmManager.set(AlarmManager.RTC,timeNow , pendingIntent);
                alarmID++;
            }
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLayoutCalendar:
                openCalendar();
                break;
            case R.id.buttonLayoutChatBot:
                openChatBot();
                break;
        }
    }

    public void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }
    public void openChatBot() {
        Intent intent = new Intent(this, MainChatBot.class);
        startActivity(intent);
    }
}