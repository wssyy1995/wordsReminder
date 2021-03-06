package com.wordreminder.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.wordreminder.demo.mapper.RemindWordsMapper;
import com.wordreminder.demo.mapper.UserMapper;
import com.wordreminder.demo.mapper.UserWordsMapper;
import com.wordreminder.demo.model.*;
import com.wordreminder.demo.pojo.AccessToken;
import com.wordreminder.demo.service.FileService;
import com.wordreminder.demo.util.SignUtil;
import com.wordreminder.demo.util.dateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.restassured.response.Response;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


@RestController
@RequestMapping(value="/wx")
public class wxController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RemindWordsMapper remindWordsMapper;


    @Autowired
    private FileService fileService;


    @GetMapping("/login/{temCode}")
    public ResponseJson auth(@PathVariable String temCode){
        String authUrl=String.format("https://api.weixin.qq.com/sns/jscode2session?appid=wx588f1244304cf5a7&secret=fed6c355b79aa4f7261e399113f44118&js_code=%s&grant_type=authorization_code",temCode);
        Response response = get(authUrl);
        JSONObject responseObject = JSONObject.parseObject(response.getBody().asString());
        String openId=responseObject.getString("openid");
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(response.getStatusCode());
        JSONObject data=new JSONObject();
        data.put("openId",openId);
        responseJson.setData(data);
        //?????????????????????????????????
        if(userMapper.countById(openId)==0){
            userMapper.insert(openId,dateUtil.currentDate(),responseObject.getString("session_key"),1);
            System.out.println("???????????????");
        }else{
            System.out.println("???????????????????????????session_key");
        }
        return responseJson;

    }
    @GetMapping("/UserProfile/get")
    public ResponseJson UserInfoGet(@RequestHeader("openId") String openId){
        System.out.println(openId);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(200);
        String userProfileAll=userMapper.getUserProfile(openId);
        if(userProfileAll.equals("")){
            responseJson.setData("");
            return responseJson;
        }
        String userProfile=JSONObject.parseObject(userProfileAll).getString("userInfo");
        System.out.println("????????????profile: "+userProfile);
        responseJson.setData(userProfile);
        return responseJson;

    }


    @PostMapping("/UserProfile/update")
    public ResponseJson UserInfo(@RequestHeader("openId") String openId, @RequestBody UserProfile userProfile){
        System.out.println(openId);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(200);
        String user_profile=JSONObject.toJSONString(userProfile);
        userMapper.updateUserProfile(user_profile,openId);
        return responseJson;

    }
    @GetMapping("/DailyInfo")
    public Object DailyInfo(@RequestHeader("openId") String openId) throws ParseException {
        //1.???????????????????????????
        String curDate=dateUtil.currentDate();
        String createTime= userMapper.getCreateTimeById(openId);
        int dateAlready=dateUtil.daysBetween(createTime,curDate)+1;
        //2.????????????????????????????????????????????????
        int countPending =remindWordsMapper.countPending(openId);
        int countAlready =remindWordsMapper.countAlready(openId);
        DailyInfo dailyInfo=new DailyInfo(dateAlready,countPending,countAlready);
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(200);
        responseJson.setData(dailyInfo);
        return responseJson;

    }



    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileService.storeFile(file);
        return fileName;

    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        // Load file as Resource
        Resource resource=fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @GetMapping("/getAccessToken")
    public ResponseJson getAccessToken(){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(200);
        responseJson.setData(AccessToken.accessToken);
        return responseJson;

    }











}
