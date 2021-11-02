package com.ku.vaccintory.calendar.info;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ku.vaccintory.R;
import com.ku.vaccintory.calendar.InfoFunction;
import com.ku.vaccintory.calendar.MainCalendar;


public class EditInfo extends AppCompatActivity implements View.OnClickListener  {

    private String date=null;

    private EditText editTextType;
    private EditText editTextPlace;
    private EditText editTextPrice;
    private EditText editTextNote;

    private CheckBox checkRemind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            date = extras.getString("dateKey");
            //The key argument here must match that used in the other activity
        }

        editTextType = findViewById(R.id.EditInfo_typeVaccine);
        editTextPlace = findViewById(R.id.EditInfo_placeVaccine);
        editTextPrice = findViewById(R.id.EditInfo_price);
        editTextNote = findViewById(R.id.EditInfo_note);








        String dateTextForm = date.replaceAll("[-]", "/");

        TextView textDate;
        textDate = findViewById(R.id.EditInfo_textDate);
        textDate.setText(String.format("Date : %s", dateTextForm)); ///Set textDate to date

        Button buttonCancel = findViewById(R.id.EditInfo_cancelButton);
        buttonCancel.setOnClickListener(this);

        Button buttonSave = findViewById(R.id.EditInfo_saveButton);
        buttonSave.setOnClickListener(this);

        checkRemind = findViewById(R.id.EditInfo_remindMe);
        checkRemind.setOnClickListener(this);


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.EditInfo_cancelButton:
                openCalendar();
                break;
            case R.id.EditInfo_saveButton:
                try {
                    saveInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    private void saveInfo() {
        String info = this.date;
        info = info+": " +editTextType.getText();
        info = info+": " +editTextPlace.getText();
        info = info+": " +editTextPrice.getText();
        info = info+": " +editTextNote.getText();
        info = info+": " +checkRemind.isChecked();

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