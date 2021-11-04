package com.ku.vaccintory.calendar.info;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunc;
import com.ku.vaccintory.calendar.MainCalendar;

import java.util.Calendar;


public class EditInfo extends AppCompatActivity implements View.OnClickListener {

    //private ScheduleClient scheduleClient;

    private String date = null;

    private EditText editTextType;
    private EditText editTextPlace;
    private EditText editTextPrice;
    private EditText editTextNote;


    private CheckBox checkRemind;
    public EditInfo() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            date = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }

        editTextType = findViewById(R.id.EditInfo_typeVaccine);
        editTextPlace = findViewById(R.id.EditInfo_placeVaccine);
        editTextPrice = findViewById(R.id.EditInfo_price);
        editTextNote = findViewById(R.id.EditInfo_note);


        String dateTextForm = date.replaceAll("[-]", "/");

        TextView textDate;
        textDate = findViewById(R.id.EditInfo_textDate);
        textDate.setText(String.format("Date : %s", dateTextForm)); ///Set textDate to date

        Button buttonCancel = findViewById(R.id.EditInfo_cancelButton);
        buttonCancel.setOnClickListener(this);

        Button buttonSave = findViewById(R.id.EditInfo_saveButton);
        buttonSave.setOnClickListener(this);

        checkRemind = findViewById(R.id.EditInfo_remindMe);
        checkRemind.setOnClickListener(this);


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EditInfo_cancelButton:
                openCalendar();
                break;
            case R.id.EditInfo_saveButton:
                try {
                    //settingReminder();
                    saveInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }



    private void saveInfo() {
        String info = this.date;
        info = info + ": " + editTextType.getText();
        info = info + ": " + editTextPlace.getText();
        info = info + ": " + editTextPrice.getText();
        info = info + ": " + editTextNote.getText();
        info = info + ": " + checkRemind.isChecked();

        InfoFunc.saveInfo(this, this.date + ".txt", info);
        editTextNote.getText().clear();
        editTextPrice.getText().clear();
        editTextPlace.getText().clear();
        editTextType.getText().clear();

        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);


    }
    private void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }

    /*@RequiresApi(api = Build.VERSION_CODES.O)
    private void settingReminder() {

        CreateNotificationCh();
        setAlarm();
        Toast.makeText(this, "เปิดการแจ้งเตือนแล้ว!", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void CreateNotificationCh() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "alarmRemindCh";
            String description = "Channel for alarmRemind";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("alarmRemind", name, importance);

            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setAlarm() {



        int day = 3;
        int month = 2;
        int year = 2014;

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, Notify.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


        Calendar calendar = Calendar. getInstance () ;
        calendar.set(year, month, day);
        calendar.set(Calendar. SECOND , 0 ) ;
        calendar.set(Calendar. MINUTE , 0 ) ;
        calendar.set(Calendar. HOUR , 0 ) ;


        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),1000 * 60 * 60 * 24, pendingIntent);

        alarmManager.cancel(pendingIntent);


    }*/




}

