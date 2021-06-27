package com.wordreminder.demo.util;

import lombok.Data;

//Result泛型类
@Data
public class Result<T>{
    private Integer code;
    private String msg;
    private T data;

    public Result() {

    }
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}';
    }
    public Result setCode(ResultEnum resultEnum) {
        this.code = resultEnum.code;
        return this;
    }

}
