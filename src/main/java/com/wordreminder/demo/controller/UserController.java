package com.wordreminder.demo.controller;

import com.wordreminder.demo.mapper.UserMapper;
import com.wordreminder.demo.model.User;
import com.wordreminder.demo.model.ListReq;
import com.wordreminder.demo.model.UserLogin;
import com.wordreminder.demo.util.Result;
import com.wordreminder.demo.util.ResultUtil;
import com.wordreminder.demo.util.dateUtil;
import com.wordreminder.demo.util.loginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/login")
    public Result login(@RequestBody UserLogin userLogin){
        loginUtil loginutil = new loginUtil();
        boolean isnew = loginutil.checkNewUser(userLogin.getName());
        if(isnew == true){
            return ResultUtil.fail("用户不存在，请先注册");

        }else {
            if(userMapper.findByName(userLogin.getName()).getPassword().equals(userLogin.getPassword())){
                return ResultUtil.success("登录成功");
            }
            else{
                return ResultUtil.fail("登录失败，密码错误");
            }
        }

    }

    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        user.setStatus(0);
        user.setRemindTime(8);
        String createTime= dateUtil.currentDate();
        int count = userMapper.insert(user.getName(),createTime,user.getPassword(),user.getRemindTime(),user.getStatus());
        if(count==1){
            return ResultUtil.success("添加成功："+user.getName());
        }else{
            return ResultUtil.fail("用户注册失败");
        }

    }


    @GetMapping("/{id}")
    public Result getUser(@PathVariable int id){
        User user =userMapper.findById(id);
        return ResultUtil.success(user);
    }

    @PostMapping("/list")
    public Result UserList(@Valid @RequestBody ListReq listReq, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            for (ObjectError object : bindingResult.getAllErrors()) {
                System.out.println(object.getDefaultMessage());
            }
            return ResultUtil.fail(bindingResult.getAllErrors().toString());

        }
        List<User> userlist= userMapper.selectAll((listReq.getPageNo()-1)* listReq.getPageSize(), listReq.getPageSize());
        return ResultUtil.success(userlist);

    }

}
