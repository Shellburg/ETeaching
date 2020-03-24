package com.sdp.eteaching.util;

//开启多线程处理的类

public class MyThread extends Thread implements Runnable {

    private String path;//开启线程跳转路径

    private String param;//开启线程传递参数

    private String response;//获取返回的信息

    public MyThread(){

        path = "";

        param = "";

    }

    public MyThread(String path, String param) {

        this.path = path;

        this.param = param;

    }

    @Override

    public void run() {

        response = HttpPostUtil.doPostRequest(path,param);

    }

    public String getPath() {

        return path;

    }

    public void setPath(String path) {

        this.path = path;

    }

    public String getParam() {

        return param;

    }

    public void setParam(String param) {

        this.param = param;

    }

    public String getResponse() {

        return response;

    }

    public void setResponse(String response) {

        this.response = response;

    }

}
