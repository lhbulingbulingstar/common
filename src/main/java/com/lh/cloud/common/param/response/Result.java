package com.lh.cloud.common.param.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Date: 2022/6/17 17:38
 * @author lh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static <T> Result<T> success(T t){
        return new Result<T>(ResultCode.SUCCESS,"操作成功",t);
    }

    public static Result success(){
        return new Result(ResultCode.SUCCESS,"操作成功");
    }

    public static Result error(String msg){
        return new Result(ResultCode.ERROR,msg);
    }
    public static Result error(String code,String msg){
        return new Result(code,msg);
    }

}
