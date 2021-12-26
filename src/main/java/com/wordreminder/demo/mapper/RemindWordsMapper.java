package com.wordreminder.demo.mapper;

import com.wordreminder.demo.model.RemindWords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value="RemindWordsMapper")
public interface RemindWordsMapper {

    RemindWords findById(@Param("id") int id);
    List<RemindWords> getPending(@Param("userId") String userId);

    List<RemindWords> selectAll(@Param("pageStart") int pagestart , @Param("pageSize") int pagesize);

    List<RemindWords> getReviewFinshed();

    int insert(@Param("wordId") int wordId,
               @Param("userId") String userId,
                @Param("word") String word,
                @Param("defining") String defining,
                @Param("imgPath") String imgPath,
               @Param("level") int level);


    int delete(@Param("id") int id);
    int countPending(@Param("userId") String userId);
    int countAlready(@Param("userId") String userId);

    int updateReviewList(@Param("userId") String userId);




}
