package com.wordreminder.demo.mapper;
import com.alibaba.fastjson.JSONObject;
import com.wordreminder.demo.model.User;
import com.wordreminder.demo.model.UserProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value="userMapper")
public interface UserMapper {

    User findById(@Param("id") String id);
    String getCreateTimeById(@Param("id") String id);
    String getUserProfile(@Param("id") String id);
    int countById(@Param("id") String id);
    int insert(@Param("id") String id,@Param("create_time") String createTime,@Param("session_key") String sessionKey,@Param("status") int status);
    int updateSessionKey();
    int updateUserProfile(@Param("user_profile") String userProfile, @Param("id") String id);
    //List<User> selectAll(@Param("pageStart") int pagestart ,@Param("pageSize") int pagesize);

}
