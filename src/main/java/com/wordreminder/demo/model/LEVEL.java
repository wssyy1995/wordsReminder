package com.wordreminder.demo.model;

import com.wordreminder.demo.util.dateUtil;

public class LEVEL {
    public static String getNextTime(int level) {
        //    单词等级：1-7，分别对应单词距离下次复习的时间间隔 30min >12h>24h>2d>4d>7d>15d
        switch (level) {
            case 1:
                return dateUtil.DateMoveSeconds(30 * 60);
            case 2:
                return dateUtil.DateMoveSeconds(12 * 60 * 60);
            case 3:
                return dateUtil.DateMoveDay(1);
            case 4:
                return dateUtil.DateMoveDay(2);
            case 5:
                return dateUtil.DateMoveDay(4);
            case 6:
                return dateUtil.DateMoveDay(7);
            case 7:
                return dateUtil.DateMoveDay(15);

        }
        return dateUtil.currentDate();


    }
}
