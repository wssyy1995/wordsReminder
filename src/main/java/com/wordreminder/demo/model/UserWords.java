package com.wordreminder.demo.model;

import lombok.Data;

@Data
public class UserWords {
    private int id;
    private int userId;
    private String word;
    private String defining;
    private String nextTime;
    private String createTime;
    private int level;
    private int isInRemind;

}
