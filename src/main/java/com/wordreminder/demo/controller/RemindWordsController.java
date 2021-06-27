package com.wordreminder.demo.controller;

import com.wordreminder.demo.mapper.RemindWordsMapper;
import com.wordreminder.demo.mapper.UserWordsMapper;
import com.wordreminder.demo.model.ListReq;
import com.wordreminder.demo.model.RemindWords;
import com.wordreminder.demo.model.UserWords;
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
@RequestMapping(value="/remindWords")
public class RemindWordsController {

    @Autowired
    private RemindWordsMapper remindWordsMapper;

    @GetMapping("/{id}")
    public Result getRemindWordById(@PathVariable int id){
        RemindWords remindWords =remindWordsMapper.findById(id);
        return ResultUtil.success(remindWords);
    }

    @GetMapping("/{userId}")
    public Result getRemindWordByuserId(@PathVariable int userId){
        RemindWords remindWords =remindWordsMapper.findByuserId(userId);
        return ResultUtil.success(remindWords);
    }




    @PostMapping("/del")
    public Result del(@RequestBody int id){
        int count=remindWordsMapper.delete(id);
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
        List<RemindWords> RemindWordsList=remindWordsMapper.selectAll((listReq.getPageNo()-1)* listReq.getPageSize(), listReq.getPageSize());
        return ResultUtil.success(RemindWordsList);

    }




}

