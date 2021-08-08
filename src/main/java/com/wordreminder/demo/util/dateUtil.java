package com.wordreminder.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class dateUtil {
    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        return dateStr;
    }

    public static String DateMoveDay(int day) {
        return dateUtil.DateMoveSeconds(86400*day);
    }
    public static String DateMoveSeconds(int Seconds) {
        Date date = new Date();
        Calendar calendar  = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.SECOND,Seconds);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
        String dateStr = format.format(date);
        return dateStr;
    }
    public static int daysBetween(String smallDate,String bigDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smallDate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bigDate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
