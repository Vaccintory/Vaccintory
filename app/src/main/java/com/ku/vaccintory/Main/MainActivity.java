package com.ku.vaccintory.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ku.vaccintory.calendar.MainCalendar;
import com.ku.vaccintory.chatbot.MainChatBot;
import com.ku.vaccintory.R;

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