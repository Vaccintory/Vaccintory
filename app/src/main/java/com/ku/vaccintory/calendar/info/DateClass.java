package com.ku.vaccintory.calendar.info;

public class DateClass {

    private static String yourDate;
    private static String yourHour;

    public static void setYourDate(String yourDate){
        DateClass.yourDate = yourDate;
    }
    public static void setYourHour(String yourDate){
        DateClass.yourHour = yourHour;
    }
    public static String getYourDate(){
        return  DateClass.yourDate;
    }
    public static String getYourHour(){
        return  DateClass.yourHour;
    }


}
