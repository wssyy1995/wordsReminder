package com.wordreminder.demo.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ResponseJson {
    private int status;
    private Object data;
    private String errorMsg;


}
