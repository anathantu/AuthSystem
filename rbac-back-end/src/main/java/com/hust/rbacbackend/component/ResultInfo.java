package com.hust.rbacbackend.component;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultInfo implements Serializable {

    private Integer status;

    private String msg;

    private Object data;

    public static ResultInfo failed(Integer code,String msg,Object data){
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setStatus(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }

    public static ResultInfo failed(String msg,Object data){
        return failed(400,msg,data);
    }

    public static ResultInfo failed(String msg){
        return failed(msg,null);
    }

    public static ResultInfo success(Integer code,String msg,Object data){
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setStatus(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(data);
        return resultInfo;
    }

    public static ResultInfo success(String msg,Object data){
        return success(200,msg,data);
    }

    public static ResultInfo success(String msg){
        return success(200,msg,null);
    }
}
