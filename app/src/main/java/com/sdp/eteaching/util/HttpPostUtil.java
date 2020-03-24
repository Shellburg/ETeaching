package com.sdp.eteaching.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostUtil {

    private static HttpURLConnection connection;

    public static String doPostRequest(String path,String content){

        BufferedReader br = null;

        String result = "";

        try {

            System.out.println("要发送的信息"+content);

            String address = "http://192.168.2.218:10080/"+path+content;

            URL url = new URL(address);

            connection = (HttpURLConnection)url.openConnection();

            connection.setDoInput(true);

            connection.setDoOutput(true);

            connection.setReadTimeout(10+1000);

            connection.setConnectTimeout(10+1000);

            connection.setRequestMethod("GET");

            connection.connect();

            if (connection.getResponseCode()==200){

                br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

                String line = null;

                while ((line=br.readLine())!=null){

                    System.out.println("line="+line);

                    result+=line;

                }

            }

            System.out.println("服务器返回的数据是"+result);

            return result;

        } catch (Exception e) {

            e.printStackTrace();

        }finally {

            if (br!=null){

                try {

                    br.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

        return null;

    }

}
