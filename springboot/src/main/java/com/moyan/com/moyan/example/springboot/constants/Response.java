package com.moyan.com.moyan.example.springboot.constants;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private int code;

    private String desc;

    private T data;

    public Response(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Response(ResponseConstants responseConstants) {
        this.code = responseConstants.getCode();
        this.desc = responseConstants.getDesc();
    }

    public Response success(T data) {
        this.setData(data);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
