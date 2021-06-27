package com.wordreminder.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class dateUtil {
    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //从前端或者自己模拟一个日期格式，转为String即可
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
}
