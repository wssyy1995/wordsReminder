package com.wordreminder.demo.util;

import lombok.Data;

@Data
public class ResultUtil {
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(ResultEnum.FAIL);
        result.setMsg(msg);
        return result;
    }


}


//定义code枚举类
enum ResultEnum{
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;
    ResultEnum(int code) {
        this.code = code;
    }
}

