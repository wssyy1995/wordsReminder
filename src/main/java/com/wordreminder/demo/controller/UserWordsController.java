package com.wordreminder.demo.controller;

import com.wordreminder.demo.mapper.UserWordsMapper;

import com.wordreminder.demo.model.*;
import com.wordreminder.demo.util.Result;
import com.wordreminder.demo.util.ResultUtil;
import com.wordreminder.demo.util.dateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value="/userwords")
public class UserWordsController {

    @Autowired
    private UserWordsMapper UserwordsMapper;




    @PostMapping("/add")
    public Result add(@RequestBody UserWords userwords){
        String createTime= dateUtil.currentDate();
        //新增单词，默认level=0 ,nextTime为30分钟后
        String nextTime=dateUtil.DateMoveSeconds(Level.ONE.delaySecond);
        int count = UserwordsMapper.insert(userwords.getUserId(),userwords.getWord(), userwords.getDefining(),nextTime,createTime,1);
        if(count==1){
            return ResultUtil.success("添加成功："+userwords.getWord());
        }else{
            return ResultUtil.fail("添加失败");
        }

    }

    @PostMapping("/del")
    public Result del(@RequestBody @Valid idReq idreq){
        int count=UserwordsMapper.delete(idreq.getId());
        if(count==1){
            return ResultUtil.success("删除成功");
        }else {
            return ResultUtil.fail("删除失败");
        }


    }

    @PostMapping("/list")
    public Result list(@Valid @RequestBody ListReq listReq, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            for (ObjectError object : bindingResult.getAllErrors()) {
                System.out.println(object.getDefaultMessage());
            }
            return ResultUtil.fail(bindingResult.getAllErrors().toString());

        }
        List<UserWords> userwordslist= UserwordsMapper.selectAll((listReq.getPageNo()-1)* listReq.getPageSize(), listReq.getPageSize());
        return ResultUtil.success(userwordslist);

    }


    @GetMapping("/{id}")
    public Result getWord(@PathVariable int id){
        UserWords userwords =UserwordsMapper.findById(id);
        return ResultUtil.success(userwords);
    }

    @PostMapping("/finishTest")
    public void finishTest(@RequestBody int userId){

    }


}



