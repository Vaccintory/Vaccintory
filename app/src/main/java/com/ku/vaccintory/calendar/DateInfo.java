package com.ku.vaccintory.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ku.vaccintory.R;

import java.io.IOException;

public class DateInfo extends AppCompatActivity implements View.OnClickListener  {

    private String date=null;

    private EditText editTextType;
    private EditText editTextPlace;
    private EditText editTextPrice;
    private EditText editTextNote;
    private TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_info);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            date = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }
        editTextType = findViewById(R.id.dateInfo_typeVaccine);
        editTextPlace = findViewById(R.id.dateInfo_placeVaccine);
        editTextPrice = findViewById(R.id.dateInfo_price);
        editTextNote = findViewById(R.id.dateInfo_note);


        String rawData = "";
        String fileName = this.date+".txt";
        rawData = InfoFunction.loadInfo(this,fileName);





        String dateTextForm = date.replaceAll("[-]", "/");

        textDate = findViewById(R.id.dateInfo_textDate);
        textDate.setText("Date : "+dateTextForm); ///Set textDate to date

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
                try {
                    saveInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void saveInfo() throws IOException {
        String info = this.date+":"+editTextType.getText()+":"+editTextPlace.getText()+":"+editTextPrice.getText()+":"+editTextNote.getText();
        InfoFunction.saveInfo(this,this.date+".txt",info);
        editTextNote.getText().clear();editTextPrice.getText().clear();editTextPlace.getText().clear();editTextType.getText().clear();

        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);



    }



    private void openCalendar() {
        Intent intent = new Intent(this, MainCalendar.class);
        startActivity(intent);
    }

}