package com.wordreminder.demo.model;

import lombok.Data;

//定时根据UserWords的nextTime创建
@Data
public class RemindWords {
    private int id;
    private int userId;
    private int wordId;
    private String word;
    private String defining;
    private int testTimes;
    private int rightTimes;
    //该单词当日状态，0：未测试，1:已测试未通过 2：测试通过
    private int status;
}
