package com.wordreminder.demo.mapper;

import com.wordreminder.demo.model.User;
import com.wordreminder.demo.model.UserWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value="UserWordsMapper")
public interface UserWordsMapper {

    UserWords findById(@Param("id") int id);

    List<UserWords> selectAll(@Param("recordStart") int pagestart , @Param("recordSize") int pagesize,@Param("userId") String openId);



    int insert(@Param("userId") String userId,
               @Param("word") String word,
               @Param("defining") String defining,
               @Param("nextTime") String nextTime,
               @Param("createTime") String createTime,
               @Param("imgPath") String imgPath);

    int update(@Param("id") int id,
               @Param("word") String word,
               @Param("defining") String defining,
               @Param("imgPath") String imgPath);

    int delete(@Param("id") int id);

    List<UserWords> selectRemind(@Param("currentTime") String currentTime);

    int updateIsInRem(@Param("isInRemind") int isInRemind,@Param("id") int id);



}
