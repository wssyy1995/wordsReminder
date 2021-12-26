package com.wordreminder.demo.schedule;
import com.wordreminder.demo.mapper.RemindWordsMapper;
import com.wordreminder.demo.mapper.UserWordsMapper;
import com.wordreminder.demo.model.LEVEL;
import com.wordreminder.demo.model.RemindWords;
import com.wordreminder.demo.model.UserWords;
import com.wordreminder.demo.util.dateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;




@Component
@EnableAsync        // 开启多线程,与Scheduled配合使用，定时任务会在新线程中执行
public class RemindWordsScheduleTask {
    @Autowired
    private RemindWordsMapper remindWordsMapper;
    @Autowired
    private UserWordsMapper UserwordsMapper;


    /*
    定时任务1：定时30秒，将符合复习条件的userwords放入reminder里
    1. isInRemind为0
    2. 根据level判断，是否nextTime小于当前时间了
     */
    @Async
    @Scheduled(fixedDelay = 30000)  //间隔30秒
    public void addRemindWords() throws InterruptedException {
        String currentTime = dateUtil.currentDate();
        System.out.println("当前时间为:"+currentTime);
        //remindWordsList为：已经达到nextTime复习时间，且未存在于remindWords表中的单词
        List<UserWords> remindWordsList=UserwordsMapper.selectRemind(currentTime);
        for(UserWords u :remindWordsList ){
            int count = remindWordsMapper.insert(u.getId(),u.getUserId(),u.getWord(),u.getDefining(),u.getImgPath(),u.getLevel());
            if(count == 1){
                System.out.println("添加"+count+"个"+u.getWord());
                UserwordsMapper.updateIsInRem(1,u.getId());
            }

        }
    }

    /*
    定时任务2，定时每天0点，将reminder里status为2的单词清除，并同步更新该单词在userWords的下次更新时间和
    1. 查询remindWords里status为2的单词
    2. 再根据wordId,在 Userwords表中 更新level+1,nextTime为当前时间+当前level的间隔，isInRemind更新为0
    3. 从remindWords里删除该单词
     */
    @Async
    @Scheduled(fixedDelay = 300000)  //间隔30秒
    public void clearRemindWords() throws InterruptedException {
        List<RemindWords> reviewFinshedList=remindWordsMapper.getReviewFinshed();
        reviewFinshedList.stream().forEach(item -> {
            System.out.println("已完成复习的单词为"+item.getWordId());
            UserwordsMapper.upgrade(item.getLevel()+1, LEVEL.getNextTime(item.getLevel()+1),0,item.getWordId());
            remindWordsMapper.delete(item.getId());
        });
    }





}
