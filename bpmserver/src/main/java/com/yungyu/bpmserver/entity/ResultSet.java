package com.yungyu.bpmserver.entity;

import java.io.Serializable;

/**
 * 前端请求放回格式
 * @param <T>
 */
public class ResultSet<T> implements Serializable {

    public final static int SUCCESS = 200;
    public final static int FAILURE = 404;

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
