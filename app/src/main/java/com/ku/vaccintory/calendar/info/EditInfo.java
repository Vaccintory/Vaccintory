package com.ku.vaccintory.calendar.info;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
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

import com.ku.vaccintory.Main.MainActivity;
import com.ku.vaccintory.Main.MenuNotifyReceiver;
import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunc;
import com.ku.vaccintory.calendar.MainCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditInfo extends AppCompatActivity implements View.OnClickListener {


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
        checkRemind = findViewById(R.id.EditInfo_remindMe);

        String fileName = date+".txt";
        if( InfoFunc.isFileExist(this,fileName) ){


            String rawData = InfoFunc.loadInfo(this,fileName);

            assert rawData != null;
            editTextType.setText( InfoFunc.getInfo_Type(rawData) );
            editTextPlace.setText( InfoFunc.getInfo_Place(rawData) );
            editTextPrice.setText( InfoFunc.getInfo_Price(rawData) );
            editTextNote.setText( InfoFunc.getInfo_Note(rawData) );
            checkRemind.setChecked( InfoFunc.getInfo_Check(rawData).contains("true") );
        }


        String dateTextForm = date.replaceAll("[-]", "/");

        TextView textDate;
        textDate = findViewById(R.id.EditInfo_textDate);
        textDate.setText(String.format("Date : %s", dateTextForm)); ///Set textDate to date

        Button buttonCancel = findViewById(R.id.EditInfo_cancelButton);
        buttonCancel.setOnClickListener(this);

        Button buttonSave = findViewById(R.id.EditInfo_saveButton);
        buttonSave.setOnClickListener(this);


        checkRemind.setOnClickListener(this);


        SimpleDateFormat sdfDay = new SimpleDateFormat("dd", Locale.getDefault());
        String day = sdfDay.format(new Date());
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM", Locale.getDefault());
        String month = sdfMonth.format(new Date());
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy", Locale.getDefault());
        String year = sdfYear.format(new Date());
        String[] dateSplited = date.split("-");
        int day1 = Integer.parseInt(dateSplited[0]);
        int day2 = Integer.parseInt(day);
        int month1 = Integer.parseInt(dateSplited[1]);
        int month2 = Integer.parseInt(month);
        int year1 = Integer.parseInt(dateSplited[2]);
        int year2 = Integer.parseInt(year);
        if( year1 < year2  )
        {
            checkRemind.setEnabled(false);
        }else if( month1 < month2 )
        {
            checkRemind.setEnabled(false);
        }
        else checkRemind.setEnabled(day1 >= day2);




    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EditInfo_cancelButton:
                openMainActivity();
                break;
            case R.id.EditInfo_saveButton:
                try {
                    if(checkTextLength()){

                        if(checkRemind.isChecked())
                            setAlarm();

                        saveInfo();
                    }

                } catch (Exception e) {
                     Toast.makeText(this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private boolean checkTextLength(){
        boolean check = true;

        if( this.editTextType.length()>30 )
            check = false;
        else if( this.editTextPlace.length()>70 )
            check = false;
        else if( this.editTextPrice.length()>10 )
            check = false;

        return check;
    }



    private void saveInfo() {
        String info = this.date;
        info = info + ":" + editTextType.getText();
        info = info + ":" + editTextPlace.getText();
        info = info + ":" + editTextPrice.getText();
        info = info + ":" + editTextNote.getText();
        info = info + ":" + checkRemind.isChecked();

        InfoFunc.saveInfo(this, this.date + ".txt" , info);
        editTextNote.getText().clear();
        editTextPrice.getText().clear();
        editTextPlace.getText().clear();
        editTextType.getText().clear();



        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    private void setAlarm() {

        String token = date.replaceAll("[-]", "");
        int rq = Integer.parseInt(token);
        boolean alarm = (PendingIntent.getBroadcast(this, rq, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);
        if(alarm){
            Intent intentAlarm = new Intent(this, NotifyReceiver.class);
            intentAlarm.putExtra("key",date);
            intentAlarm.putExtra("alarmID",rq);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,rq,intentAlarm,0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),3600000, pendingIntent);


        }


    }






}

