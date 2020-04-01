package com.sdp.eteaching.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import androidx.core.app.ActivityCompat;

public class AudioManager {

    public static int STREAM_MUSIC;

    private MediaRecorder mMediaRecorder;
    private String mDir;
    private String mCurrentFilePath;
    private String mFileName;


    public String getmFileName() {
        return mFileName;
    }


    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };

    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }

    private static AudioManager mInstance;

    private boolean isPrepared;
    public AudioManager(String dir){
        mDir = dir;
    };



    /**
     * 回调准备完毕
     */
    public interface AudioStateListener {
        void wellPrepared();
    }

    public AudioStateListener mListener;

    public void setOnAudioStateListener(AudioStateListener listener){
        mListener = listener;
    }

    public static AudioManager getInstance(String dir){
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }


    /**
     * 准备
     */
    public void prepareAudio() {
        try {
            isPrepared = false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            //用UUID生成文件名
            String fileName = generateFileName();

            //在音频文件目录下新建文件
            File file = new File(dir, fileName);

            mFileName=fileName;

            //获得绝对路径名
            mCurrentFilePath = file.getAbsolutePath();


            mMediaRecorder = new MediaRecorder();

            //设置输出文件
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            //设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            //设置音频的格式为amr
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
            //准备结束
            isPrepared = true;
            if (mListener != null) {
                mListener.wellPrepared();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    生成UUID唯一标示符
//    算法的核心思想是结合机器的网卡、当地时间、一个随机数来生成GUID
//    .amr音频文件
    private String generateFileName() {
        return UUID.randomUUID().toString()+".amr";
    }

    public int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            //获得最大的振幅getMaxAmplitude() 1-32767
            try {
                return maxLevel * mMediaRecorder.getMaxAmplitude()/32768+1;
            } catch (Exception e) {

            }
        }
        return 1;
    }

    public void release() {
//        if (recorder != null) {
//            recorder.stop();
//            recorder.release();
//            recorder = null;
//        }
//    }

        if (mMediaRecorder != null) {
            try {
                mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                // TODO 如果当前java状态和jni里面的状态不一致，
                //e.printStackTrace();
                mMediaRecorder = null;
                mMediaRecorder = new MediaRecorder();
            }
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }
//
//    public void release() {
//        //3.30修改
//        //stop();
//        mMediaRecorder.stop();
//        mMediaRecorder.release();
//        mMediaRecorder = null;
//    }

    public void cancel(){
        release();
        if(mCurrentFilePath!=null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }
    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }

    public Uri getUriFromFilepath() {
                Uri mUri = Uri.parse(mCurrentFilePath);
                return mUri;

    }
}