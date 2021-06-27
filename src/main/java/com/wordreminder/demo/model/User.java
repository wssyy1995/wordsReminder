package com.wordreminder.demo.model;

import lombok.Data;


@Data
public class User {

    private int id;
    private String name;
    private String password;
    private String createTime;
    private int remindTime;
    private int status;

}
