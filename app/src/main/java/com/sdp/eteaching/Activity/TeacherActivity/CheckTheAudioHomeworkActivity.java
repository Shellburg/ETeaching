package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdp.eteaching.R;
import com.sdp.eteaching.util.CheckHomeworkAdapter;
import com.sdp.eteaching.util.MediaManager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static com.sdp.eteaching.util.DownloadUtil.downLoad;

public class CheckTheAudioHomeworkActivity extends AppCompatActivity {
    private Integer teacherID;
    private String homeworkPath;
    private String rootPath;
    private ArrayAdapter<Recorder> mAdapter;
    private ListView mListView;
    private View mAnimView;
    private List<Recorder> mDatas =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_the_audio_homework);

        Intent intent=getIntent();
        teacherID=(int)intent.getExtras().get("t_id");
        homeworkPath=(String)intent.getExtras().get("path");

        String[] filename=homeworkPath.split("/");
        downLoad("http://120.26.77.81/uploadAudio/getfile.do?homeworkPath="+homeworkPath,filename[3]);
        rootPath = Environment.getExternalStorageDirectory() + "/received_audios/";

        initView();
        setListViewAdapter();
    }

    private void initView() {
        mListView = findViewById(R.id.id_listview);
        //设置listview 位置
        mListView.setSelection(mDatas.size()-1);
        String[] filename=homeworkPath.split("/");
        Recorder recorder = new Recorder(0,rootPath+filename[3]);
        mDatas.add(recorder);
        //更新adapter
        //mAdapter.notifyDataSetChanged();

    }

    private void setListViewAdapter(){
        mAdapter =new CheckHomeworkAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果第一个动画正在运行， 停止第一个播放其他的
                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj_left);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim_left);
                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
                animation.start();

                Log.i("play filepath",mDatas.get(position).filePath);
                //播放音频  完成后改回原来的background
                MediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.adj_left);
                    }
                });
            }
        });
    }


    /**
     * 根据生命周期 管理播放录音
     */
    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }

    //数据类
    public class Recorder{

        float time;
        String filePath;

        public float getTime() {
            return time;
        }

        public void setTime(float time) {
            this.time = time;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public Recorder(float time, String filePath) {
            super();
            this.time = time;
            this.filePath = filePath;
        }
    }

}

