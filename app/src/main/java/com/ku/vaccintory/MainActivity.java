package com.ku.vaccintory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalendar = (Button)findViewById(R.id.buttonCalendar);
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openCalendar();
            }
        });
    }


    public void openCalendar() {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}