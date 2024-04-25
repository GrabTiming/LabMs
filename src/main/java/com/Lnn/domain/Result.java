package com.Lnn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public  Result(Integer code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public static Result ok(String msg,Object data)
    {
        return new Result<>(200,msg,data);
    }

    public static Result ok(Object data)
    {
        return new Result<>(200,null,data);
    }

    public static Result error(Integer code,String msg)
    {
        return new Result<>(code,msg,null);
    }



}
