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
import com.ku.vaccintory.calendar.info.DateClass;
import com.ku.vaccintory.calendar.info.NotifyReceiver;
import com.ku.vaccintory.chatbot.MainChatBot;
import com.ku.vaccintory.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout buttonCalendar;
    private LinearLayout buttonChatBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalendar = (LinearLayout) findViewById(R.id.buttonLayoutCalendar);
        buttonCalendar.setOnClickListener(this);
        buttonChatBot = (LinearLayout) findViewById(R.id.buttonLayoutChatBot);
        buttonChatBot.setOnClickListener(this);

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateToday = sdfDate.format(new Date());
        String fileName = dateToday+".txt";

        if( InfoFunc.isFileExist(this,fileName) ){

            DateClass.setYourDate(dateToday);


            Intent intent = new Intent(this, NotifyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long timeNow = System.currentTimeMillis();
            alarmManager.set(AlarmManager.RTC_WAKEUP,timeNow + 100 , pendingIntent);
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