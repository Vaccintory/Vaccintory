package com.ku.vaccintory.calendar;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfoFunc extends AppCompatActivity {

    public static String loadInfo(AppCompatActivity thisClass,String fileName){

        String info;

        FileInputStream fis = null;
        try {
            fis = thisClass.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();



            while ((info = br.readLine()) != null){
                sb.append(info).append('\0');
            }
            info = sb.toString();

        } catch (Exception e){
            Toast.makeText(thisClass,"Error_LoadInfo : "+e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        } finally {
            if(fis != null){
                try{
                    fis.close();
                }catch (IOException e) {
                    Toast.makeText(thisClass,"Error_fis.close : "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }

        return info;
    }


    public static void saveInfo(AppCompatActivity thisClass, String fileName, String info) {

        try (FileOutputStream fos = thisClass.openFileOutput(fileName, MODE_PRIVATE)) {
            fos.write(info.getBytes());


            /////Toast.makeText(thisClass, "Saved to" + thisClass.getFilesDir() + "/" + fileName, Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            Toast.makeText(thisClass, "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isFileExist(AppCompatActivity thisClass, String fileName)
    {
        File file = new File(thisClass.getApplicationContext().getFilesDir(),fileName);
        return file.exists();
    }


    //Get theese Data from RawInfo

    public static String getInfo_Type(String rawData)
    {
        String[] infoArray = rawData.split(":");
        return infoArray[1];

    }
    public static String getInfo_Place(String rawData)
    {
        String[] infoArray = rawData.split(":");
        return infoArray[2];
    }
    public static String getInfo_Price(String rawData)
    {
        String[] infoArray = rawData.split(":");
        return infoArray[3];
    }
    public static String getInfo_Note(String rawData)
    {
        String[] infoArray = rawData.split(":");
        return infoArray[4];
    }
    public static String getInfo_Check(String rawData)
    {
        String[] infoArray = rawData.split(":");
        return infoArray[5];
    }


}
