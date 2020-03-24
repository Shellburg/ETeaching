package com.sdp.eteaching.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static JSONObject getJsonObj(String str){

        if (str!=null&&str!=""){

            return JSON.parseObject(str);

        }

        return null;

    }

    public static ResultData getResult(String path, String param, Class clazz){

        ResultData resultData = null;

        try {

            MyThread run = new MyThread(path,param);

            run.start();//开启多线程传输数据

            run.join();//接收线程数据，结束线程

            JSONObject json = getJsonObj(run.getResponse());

            if (json!=null){

                resultData = new ResultData<>();

                resultData.setStatus(json.getInteger("status"));

                resultData.setMsg(json.getString("msg"));

                resultData.setData(json.getObject("data", clazz));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return resultData;

    }

}