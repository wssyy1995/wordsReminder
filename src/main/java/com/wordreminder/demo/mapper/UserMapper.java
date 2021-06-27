package com.wordreminder.demo.mapper;

import com.wordreminder.demo.controller.UserController;
import com.wordreminder.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="userMapper")
public interface UserMapper {

    User findById(@Param("id") int id);
    User findByName(@Param("name") String name);


    int insert(@Param("name") String name, @Param("createTime") String createTime,@Param("password") String password,@Param("remindTime") int remindTime,@Param("status") int status);

    List<User> selectAll(@Param("pageStart") int pagestart ,@Param("pageSize") int pagesize);

}
