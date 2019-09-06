package com.yungyu.mybatisdemo.entity;

public class Result<T> {

    public static final int RESULT_SUCCESS = 200;

    public static final int RESULT_ERROR = 404;

    private int code;

    private String msg;

    private T data;

    public Result(){}

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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
