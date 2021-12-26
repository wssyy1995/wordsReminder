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
        //把日期往后增加一天.整数往后推,负数往前移动
        return dateUtil.DateMoveSeconds(86400*day);
    }
    public static String DateMoveSeconds(int Seconds) {
        Calendar calendar  = new GregorianCalendar();
        //生成当前时间的calendar，并在此上修改时间
        calendar.setTime(new Date());
        calendar.add(calendar.SECOND,Seconds);
        //获取修改后的date对象
        Date date=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    public static long dateCompare(String Date1,String Date2) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(Date1));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(Date2));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return between_days;
    }


}
