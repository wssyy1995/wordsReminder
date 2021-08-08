package com.wordreminder.demo.model;

import lombok.Data;

@Data
public class UserWords {
    private int id;
    private String userId;
    private String word;
    private String defining;
    private String imgPath;
    private String nextTime;
    private String createTime;
//    单词等级：1-7，分别对应单词下次复习时间 30min >12h>24h>2d>4d>7d>15d
    private int level;
    private int isInRemind;

}
