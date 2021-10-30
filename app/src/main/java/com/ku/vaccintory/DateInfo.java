package com.ku.vaccintory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DateInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String date = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }
        setContentView(R.layout.activity_date_info);
    }


}