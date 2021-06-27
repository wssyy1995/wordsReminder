package com.wordreminder.demo.mapper;

import com.wordreminder.demo.model.RemindWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value="RemindWordsMapper")
public interface RemindWordsMapper {

    RemindWords findById(@Param("id") int id);
    RemindWords findByuserId(@Param("userId") int userId);

    List<RemindWords> selectAll(@Param("pageStart") int pagestart , @Param("pageSize") int pagesize);

    int insert(@Param("wordId") int wordId,
               @Param("userId") int userId,
               @Param("word") String word,
               @Param("defining") String defining);


    int delete(@Param("id") int id);




}
