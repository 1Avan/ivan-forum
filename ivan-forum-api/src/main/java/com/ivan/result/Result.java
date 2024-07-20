package com.ivan.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Integer code;//编码200表示成功，201表示失败
    private String msg;
    private Object data;
    //成功返回数据方法
    public static Result success(Object data){
        return new Result(200,"success",data);
    }
    public static Result success(){
        Result result = new Result();
        result.code = 200;
        result.msg = "success";
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.code = 201;
        result.msg = "error";
        return result;
    }
}
