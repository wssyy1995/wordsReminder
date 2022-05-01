package com.wordreminder.demo;

import com.wordreminder.demo.pojo.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.wordreminder.demo.mapper")
//发现注解@Scheduled的任务并由后台执行
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    }

}
