package com.wordreminder.demo.schedule;
import com.wordreminder.demo.mapper.RemindWordsMapper;
import com.wordreminder.demo.mapper.UserWordsMapper;
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

    @Async
    @Scheduled(fixedDelay = 10000)  //间隔10秒
    public void addRemindWords() throws InterruptedException {
        String currentTime = dateUtil.currentDate();
        List<UserWords> remindWordsList=UserwordsMapper.selectRemind(currentTime);
        System.out.println("当前时间为:"+currentTime);
        for(UserWords u :remindWordsList ){
            int count = remindWordsMapper.insert(u.getUserId(),u.getId(),u.getWord(),u.getDefining());
            if(count == 1){
                System.out.println("添加"+count+"个"+u.getWord());
                UserwordsMapper.updateIsInRem(1,u.getId());
            }

        }
        Thread.sleep(1000 * 30);
    }

}
