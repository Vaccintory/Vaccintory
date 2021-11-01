package com.ku.vaccintory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout buttonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalendar = (LinearLayout) findViewById(R.id.buttonLayoutCalendar);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCalendar();
            }
        });
    }


    public void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }
}