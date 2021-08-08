package com.wordreminder.demo.model;
import lombok.Data;

@Data
public class UserInfo {
    private String nickName;
    private String avatarUrl;
    private String country;
    private String city;
    private String language;
    private int gender;
    private String province;

}

