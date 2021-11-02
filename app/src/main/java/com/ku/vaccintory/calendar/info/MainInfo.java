package com.ku.vaccintory.calendar.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ku.vaccintory.calendar.InfoFunction;

public class MainInfo extends AppCompatActivity {

    private String dateKey=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            dateKey = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }

        String fileName = dateKey+".txt";
        if( InfoFunction.isFileExist(this,fileName) )
            openInfo();
        else
            openEditInfo();


    }


    private void openEditInfo() {    ///use this function to pass Date  && Active MainInfo class
        Intent intent = new Intent(this, EditInfo.class);
        intent.putExtra("dateKey",dateKey);
        startActivity(intent);
    }
    private void openInfo() {    ///use this function to pass Date  && Active MainInfo class
        Intent intent = new Intent(this, Info.class);
        intent.putExtra("dateKey",dateKey);
        startActivity(intent);
    }
}