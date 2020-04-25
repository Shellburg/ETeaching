package com.sdp.eteaching.util;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Administrator
 * 播放录音类
 */

public class MediaManager {
    private static MediaPlayer mMediaPlayer;

    private static boolean isPause;

    private static int currVolume = 0;

    //播放录音
    public static void playSound(String filePath, MediaPlayer.OnCompletionListener onCompletionListener){
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            //播放错误 防止崩溃
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        }else{
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 如果 播放时间过长,如30秒
     * 用户突然来电话了,则需要暂停
     */
    public static void pause() {
        if (mMediaPlayer != null&&mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 播放
     */
    public static void resume(){
        if (mMediaPlayer != null && isPause) {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * activity 被销毁  释放
     */
    public static void release(){
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

//    private void static setSpeakerPhoneOn(boolean on) {
//
//        if (on) {
//            audioManager.setSpeakerphoneOn(true);
//            audioManager.setMode(android.media.AudioManager.MODE_NORMAL);
//            //设置音量，解决有些机型切换后没声音或者声音突然变大的问题
//            audioManager.setStreamVolume(android.media.AudioManager.STREAM_MUSIC,
//                    audioManager.getStreamVolume(android.media.AudioManager.STREAM_MUSIC), android.media.AudioManager.FX_KEY_CLICK);
//
//        } else {
//            audioManager.setSpeakerphoneOn(false);
//
//            //5.0以上
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                audioManager.setMode(android.media.AudioManager.MODE_IN_COMMUNICATION);
//                //设置音量，解决有些机型切换后没声音或者声音突然变大的问题
//                audioManager.setStreamVolume(android.media.AudioManager.STREAM_VOICE_CALL,
//                        audioManager.getStreamMaxVolume(android.media.AudioManager.STREAM_VOICE_CALL), android.media.AudioManager.FX_KEY_CLICK);
//
//            } else {
//                audioManager.setMode(android.media.AudioManager.MODE_IN_CALL);
//                audioManager.setStreamVolume(android.media.AudioManager.STREAM_VOICE_CALL,
//                        audioManager.getStreamMaxVolume(android.media.AudioManager.STREAM_VOICE_CALL), android.media.AudioManager.FX_KEY_CLICK);
//            }
//        }
//
//    }


}