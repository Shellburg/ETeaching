package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sdp.eteaching.R;
import com.sdp.eteaching.util.AudioRecorderButton;
import com.sdp.eteaching.util.MediaManager;
import com.sdp.eteaching.util.RecorderAdaper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class TeacherMainActivity extends AppCompatActivity {
    private String phone;
    private int teacherId;

    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas =new ArrayList<>();

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        BottomNavigationView navView = findViewById(R.id.teacher_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.teacher_navigation_home, R.id.teacher_navigation_dashboard, R.id.teacher_navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.teacher_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent=getIntent();
        phone=(String)intent.getExtras().get("phone");
        teacherId=(Integer) intent.getExtras().get("t_id");


        initView();
        setListViewAdapter();


    }

    public void updateTeacherMessage(View v) {
        Intent intent = new Intent(this, TeacherAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void addClass(View v){
        Intent intent = new Intent(this, AddClassActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void checkClassInfo(View v){
        Intent intent = new Intent(this, CheckClassInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initView(){
        mListView = findViewById(R.id.id_listview);
        mAudioRecorderButton = findViewById(R.id.id_recorder_button);

        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(int seconds, String FilePath) {

            }

            @Override
            public void onFinish(float seconds, String filePath) {
                //每完成一次录音
                Recorder recorder = new Recorder(seconds,filePath);
                mDatas.add(recorder);
                //更新adapter
                mAdapter.notifyDataSetChanged();
                //设置listview 位置
                mListView.setSelection(mDatas.size()-1);
            }
        });
    }

    private void setListViewAdapter(){
        mAdapter = new RecorderAdaper(this, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果第一个动画正在运行， 停止第一个播放其他的
                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
                animation.start();

                //播放音频  完成后改回原来的background
                MediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.adj);
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
