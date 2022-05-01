package com.wordreminder.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/kafka")
public class MyKafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @GetMapping("/send")
    public String sendMsg(){
        kafkaTemplate.send("test0",0,"key","this is a msg!");
        return "send success";


    }


}
