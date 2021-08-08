package com.wordreminder.demo.model;

import lombok.Data;


@Data
public class User {

    //wx openId
    private int id;
    private String name;
    private String avatarUrl;
    private String createTime;
    private String sessionKey;
    private int status;
    private UserProfile userProfile;

}

