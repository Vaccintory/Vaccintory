package com.ku.vaccintory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateInfo extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_info);

        Bundle extras = getIntent().getExtras();
        String date=null;
        if (extras != null) {
            date = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }



        TextView textDate = findViewById(R.id.dateInfo_textDate);
        textDate.setText("Date : "+date); ///Set textDate to date


        Button buttonCancel = findViewById(R.id.dateInfo_cancelButton);
        buttonCancel.setOnClickListener(this);
        Button buttonSave = findViewById(R.id.dateInfo_saveButton);
        buttonSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dateInfo_cancelButton:
                openCalendar();
                break;
            case R.id.dateInfo_saveButton:
                break;
        }
    }

    public void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }
}