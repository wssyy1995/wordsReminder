package com.wordreminder.demo.mapper;

import com.wordreminder.demo.model.User;
import com.wordreminder.demo.model.UserWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value="UserWordsMapper")
public interface UserWordsMapper {

    UserWords findById(@Param("id") int id);

    List<UserWords> selectAll(@Param("pageStart") int pagestart , @Param("pageSize") int pagesize);



    int insert(@Param("userId") int userId,
               @Param("word") String word,
               @Param("defining") String defining,
               @Param("nextTime") String nextTime,
               @Param("createTime") String createTime,
               @Param("level") int level);

    int delete(@Param("id") int id);

    List<UserWords> selectRemind(@Param("currentTime") String currentTime);

    int updateIsInRem(@Param("isInRemind") int isInRemind,@Param("id") int id);



}
