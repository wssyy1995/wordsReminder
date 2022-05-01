package com.wordreminder.demo.schedule;
import com.alibaba.fastjson.JSONObject;
import com.wordreminder.demo.mapper.RemindWordsMapper;
import com.wordreminder.demo.mapper.UserWordsMapper;
import com.wordreminder.demo.pojo.AccessToken;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.get;


@Component
public class WxTokenScheduleTask {
    // @schedule + @Aysnc ，多个定时任务异步执行，springboot默认执行器线程池大小为100
    @Async
    @Scheduled(fixedDelay = 7000000)  //间隔7000秒
    public void getAccessToken() throws InterruptedException {
        String authUrl=String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx588f1244304cf5a7&secret=fed6c355b79aa4f7261e399113f44118");
        Response response = get(authUrl);
        System.out.println();
        JSONObject responseObject = JSONObject.parseObject(response.getBody().asString());
        String access_token=responseObject.getString("access_token");
        System.out.println("access_token: "+access_token);
        AccessToken.accessToken=access_token;
    }

}
