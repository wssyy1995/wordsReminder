package com.wordreminder.demo.util;

import com.wordreminder.demo.mapper.UserMapper;
import com.wordreminder.demo.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class loginUtil {

    public static loginUtil loginutil;

    @PostConstruct
    public void init() {
        loginutil = this;
    }
    @Autowired
    private UserMapper userMapper;

    public Boolean checkNewUser(String name){
        User user = loginutil.userMapper.findByName(name);
        if(user == null){
            System.out.println("user == null");
            return true;
        }
        return false;

    }
}
