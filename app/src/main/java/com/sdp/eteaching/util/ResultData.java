package com.sdp.eteaching.util;


import java.io.Serializable;

public class ResultData<T> implements Serializable {

    private T data;//携带数据

    private int status;//状态码

    private String msg;//信息

    public ResultData() { }

    public ResultData(T data, int status, String msg) {

        this.data = data;

        this.status = status;

        this.msg = msg;

    }

    public T getData() {

        return data;

    }

    public void setData(T data) {

        this.data = data;

    }

    public int getStatus() {

        return status;

    }

    public void setStatus(int status) {

        this.status = status;

    }

    public String getMsg() {

        return msg;

    }

    public void setMsg(String msg) {

        this.msg = msg;

    }

}
