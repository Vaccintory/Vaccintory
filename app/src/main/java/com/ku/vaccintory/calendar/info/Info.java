package com.ku.vaccintory.calendar.info;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunction;
import com.ku.vaccintory.calendar.MainCalendar;

public class Info extends AppCompatActivity implements View.OnClickListener   {

    private String dateKey=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dateKey = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }

        String rawData;
        String fileName = this.dateKey+".txt";
        rawData = InfoFunction.loadInfo(this,fileName);


        String dateTextForm = dateKey.replaceAll("[-]", "/");
        TextView textDate;
        textDate = findViewById(R.id.Info_textDate);
        textDate.setText(String.format("Date : %s", dateTextForm)); ///Set textDate to date

        TextView textType = findViewById(R.id.Info_typeVaccine);
        TextView textPlace = findViewById(R.id.Info_placeVaccine);
        TextView textPrice = findViewById(R.id.Info_price);
        TextView textNote = findViewById(R.id.Info_noteText);
        CheckBox checkRemind = findViewById(R.id.Info_remindMe);

        assert rawData != null;
        String[] infoArray = rawData.split(":");
        textType.setText(String.format("ประเภทวัคซีน : %s", infoArray[1]));
        textPlace.setText(String.format("สถานที่รับวัคซีน : %s", infoArray[2]));
        textPrice.setText(String.format("ราคา %s บาท", infoArray[3]));
        textNote.setText(infoArray[4]);
        checkRemind.setChecked(infoArray[5].contains("true"));


        Button editButton = findViewById(R.id.Info_EditButton);
        editButton.setOnClickListener(this);

        Button backButton = findViewById(R.id.Info_BackButton);
        backButton.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Info_EditButton:
                openEditInfo();
                break;
            case R.id.Info_BackButton:
                openCalendar();
                break;

        }
    }

    private void openEditInfo() {    ///use this function to pass Date  && Active MainInfo class
        Intent intent = new Intent(this, EditInfo.class);
        intent.putExtra("dateKey",dateKey);
        startActivity(intent);
    }

    private void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }

}

