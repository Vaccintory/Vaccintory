package com.ku.vaccintory.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.info.MainInfo;

public class MainCalendar extends AppCompatActivity {

    private String dateKey=null; //// String form dd-mm-yyyy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);




        /*dateKey = "12-10-2021";   //ตัวอย่างการเรียกใช้หน้าข้อมูลของวัน
        openMainInfo();

        //การ Load ข้อมูล
        String fileName = dateKey+".txt";
        String rawData = InfoFunc.loadInfo(this,fileName);

        InfoFunc.isFileExist(this,fileName); //เช็คว่าไฟล์ชื่อนี้  มีอยู่ไหม fileName  Ex.  12-10-1564.txt  return 1 ถ้ามีไฟล์ชื่อนี้ใน save*/



    }


    private void openMainInfo() {    ///use this function to pass Date  && Active MainInfo class
        Intent intent = new Intent(this, MainInfo.class);
        intent.putExtra("dateKey",dateKey);
        startActivity(intent);
    }



}



