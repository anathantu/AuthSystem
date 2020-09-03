package com.hust.rbacbackend.component;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultInfo implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public static ResultInfo success(Integer code,String msg,Object data){
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }

    public static ResultInfo failed(Integer code,String msg,Object data){
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }
}
