package com.wordreminder.demo.model;
import lombok.Data;

@Data
public class DailyInfo {

    public DailyInfo(int dateAlready, int countPending, int countAlready){
        this.dateAlready=dateAlready;
        this.countPending=countPending;
        this.countAlready=countAlready;

    }
    private int countPending;
    private int countAlready;
    private int dateAlready;

}

