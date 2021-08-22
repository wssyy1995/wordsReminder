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
    private UserWordsMapper userWordsMapper;


    @PostMapping("/add")
    public ResponseJson UserWordAdd(@RequestHeader("openId") String openId, @RequestBody UserWords userWords){
        ResponseJson responseJson = new ResponseJson();
        String createTime= dateUtil.currentDate();
        //新增单词，默认level=0 ,nextTime为30分钟后
        String nextTime=dateUtil.DateMoveSeconds(Level.ONE.delaySecond);
        int count = userWordsMapper.insert(openId,userWords.getWord(), userWords.getDefining(),nextTime,createTime,userWords.getImgPath());
        if(count==1){
            responseJson.setStatus(200);
        }else{
            responseJson.setStatus(200);
            responseJson.setErrorMsg("添加失败");
        }
        responseJson.setStatus(200);
        return responseJson;

    }

    @PostMapping("/update")
    public ResponseJson UserWordUpdate(@RequestHeader("openId") String openId, @RequestBody UserWords userWords){
        ResponseJson responseJson = new ResponseJson();
        System.out.println("更新单词");
        System.out.println(userWords);
        int count = userWordsMapper.update(userWords.getId(),userWords.getWord(), userWords.getDefining(),userWords.getImgPath());
        if(count==1){
            responseJson.setStatus(200);
        }else{
            responseJson.setStatus(200);
            responseJson.setErrorMsg("更新失败，请稍后重试");
        }
        responseJson.setStatus(200);
        return responseJson;

    }

    @PostMapping("/del")
    public Result del(@RequestBody @Valid idReq idreq){
        System.out.println("idreq.getDeleteIdList()");
        System.out.println(idreq.getDeleteIdList());
        List<String> deleteIdListd=idreq.getDeleteIdList();
        int count=0;
        for(String id:deleteIdListd){
            count+=userWordsMapper.delete(Integer.parseInt(id));
        }
        if(count==deleteIdListd.size()){
            return ResultUtil.success("删除成功");
        }else {
            return ResultUtil.fail("删除失败");
        }


    }

    @PostMapping("/list")
    public Result list(@Valid @RequestBody ListReq listReq, BindingResult bindingResult,@RequestHeader("openId") String openId){
        if (bindingResult.hasErrors()) {
            for (ObjectError object : bindingResult.getAllErrors()) {
                System.out.println(object.getDefaultMessage());
            }
            return ResultUtil.fail(bindingResult.getAllErrors().toString());

        }
        //select limit x,y : 从第x条记录开始，查询y行
        List<UserWords> userwordslist= userWordsMapper.selectAll((listReq.getPageNo()-1)* listReq.getPageSize(), listReq.getPageSize(),openId);
        return ResultUtil.success(userwordslist);

    }


    @GetMapping("/{id}")
    public Result getWord(@PathVariable int id){
        UserWords userwords =userWordsMapper.findById(id);
        return ResultUtil.success(userwords);
    }

    @PostMapping("/finishTest")
    public void finishTest(@RequestBody int userId){

    }


}



