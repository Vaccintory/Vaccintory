package com.ku.vaccintory.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ku.vaccintory.R;

public class MainCalendar extends AppCompatActivity {

    private String dateKey=null; //// String form dd-mm-yyyy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        /*dateKey = "12-10-1564";   //ตัวอย่างการเรียกใช้หน้าข้อมูลของวัน
        openDateInfo();

        //การ Load ข้อมูล
        String fileName = dateKey+".txt";
        String rawData = InfoFunction_LoadNSave.loadInfo(this,fileName);*/
    }


    public void openDateInfo() {    ///use this function to pass Date  && Active DateInfo class
        Intent intent = new Intent(this, DateInfo.class);
        intent.putExtra("dateKey",dateKey);
        startActivity(intent);
    }



}



