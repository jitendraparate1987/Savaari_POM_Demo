package com.savaari.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimeUtils {

	Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.hhmmss");
    String TodayDateString=sdf.format(date);
    String Date =TodayDateString.substring(6, 8);
    String TodayDate=Integer.toString(Integer.parseInt(Date));
    String Month= TodayDateString.substring(4, 6);
    String Year=TodayDateString.substring(0, 4);;
    String hour=TodayDateString.substring(9, 11);
    String minutes=TodayDateString.substring(11, 13);
    String seconds=TodayDateString.substring(13, 15);
    
    public String getTodaysDate()
    {
    	return TodayDate;
    }
    
    public String getThisMonth()
    {
    	return Month;
    }
    
    public String getThisYearInNumeric()
    {
    	return Year;
    }
    
    public String getCurrentTime()
    {
    	return(hour + "_" + minutes + "_" + seconds);
    }
   
 }
