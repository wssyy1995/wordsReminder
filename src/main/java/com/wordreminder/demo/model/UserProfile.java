package com.wordreminder.demo.model;
import lombok.Data;


@Data
public class UserProfile {
    private String cloudID;
    private String encryptedData;
    private String country;
    private String iv;
    private String signature;
    private UserInfo userInfo;

}


