package com.ku.vaccintory.calendar;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfoFunction extends AppCompatActivity {

    public static String loadInfo(AppCompatActivity thisClass,String fileName){

        String info = null;

        FileInputStream fis = null;
        try {
            fis = thisClass.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();



            while ((info = br.readLine()) != null){
                sb.append(info).append("\n");
            }
            info = sb.toString();

        }catch (FileNotFoundException e){
            Toast.makeText(thisClass,"Error_LoadInfo : "+e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }catch (Exception e){
            Toast.makeText(thisClass,"Error_LoadInfo : "+e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }finally {
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


    public static void saveInfo(AppCompatActivity thisClass, String fileName, String info) throws IOException {
        FileOutputStream fos = null;

        try{
            fos = thisClass.openFileOutput(fileName,MODE_PRIVATE);
            fos.write(info.getBytes());


            Toast.makeText(thisClass,"Saved to"+thisClass.getFilesDir()+"/"+fileName,Toast.LENGTH_LONG).show();


        } catch (IOException e){
            Toast.makeText(thisClass,"Error : "+e.getMessage(),Toast.LENGTH_LONG).show();
        } finally{
            fos.close();
        }
    }

    public static boolean isFileExist(AppCompatActivity thisClass, String fileName)
    {
        File file = new File(thisClass.getApplicationContext().getFilesDir(),fileName);
        if(file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

}
