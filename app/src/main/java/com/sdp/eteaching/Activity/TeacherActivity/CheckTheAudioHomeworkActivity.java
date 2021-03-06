package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.sdp.eteaching.R;
import com.sdp.eteaching.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;

public class CheckTheAudioHomeworkActivity extends AppCompatActivity {
    Integer teacherID;
    String homeworkPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_the_audio_homework);

        Intent intent=getIntent();
        teacherID=(int)intent.getExtras().get("t_id");
        homeworkPath=(String)intent.getExtras().get("path");

        String[] filename=homeworkPath.split("/");
        downLoad("http://192.168.2.218:10080/uploadAudio/getfile.do?homeworkPath="+homeworkPath,filename[3]);
        //downLoad("http://192.168.2.218:10080/uploadAudio/getfile.do?homeworkPath=E:/Eteaching/audio/hhh.txt","hhh.txt");
    }

    /**
     * 从服务器下载文件
     * @param path 下载文件的地址
     * @param fileName 文件名字
     */
    public static void downLoad(final String path, final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setReadTimeout(5000);
                    con.setConnectTimeout(5000);
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestMethod("GET");
                    con.connect();
                    if (con.getResponseCode() == 200) {
                        InputStream is = con.getInputStream();//获取输入流
                        FileOutputStream fileOutputStream = null;//文件输出流
                        if (is != null) {
                            FileUtils fileUtils=new FileUtils();
                            String rootPath = Environment.getExternalStorageDirectory() + "/received_audios";
                            File dir = new File(rootPath);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            fileOutputStream = new FileOutputStream(fileUtils.createFile(fileName));//指定文件保存路径，代码看下一步
                            byte[] buf = new byte[1024];
                            int ch;
                            while ((ch = is.read(buf)) !=-1) {
                                fileOutputStream.write(buf, 0, ch);//将获取到的流写入文件中
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            con.disconnect();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
