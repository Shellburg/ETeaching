package com.sdp.eteaching.Activity.TeacherActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdp.eteaching.R;
import com.sdp.eteaching.util.AudioRecorderButton;
import com.sdp.eteaching.util.MediaManager;
import com.sdp.eteaching.util.PermissionUtils;
import com.sdp.eteaching.util.RecorderAdaper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.sdp.eteaching.util.AudioManager.verifyAudioPermissions;

public class AudioActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas =new ArrayList<>();
    private Uri uri;

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        initView();
        setListViewAdapter();

        verifyAudioPermissions(AudioActivity.this);
        MediaRecorder mRecorders = new MediaRecorder();
        mRecorders.setAudioSource(MediaRecorder.AudioSource.MIC);
        PermissionUtils.isGrantExternalRW(this, 1);

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

                //上传文件
                uploadFile(new File(filePath));
                Log.d("AudiotoUpload",filePath.toString());

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


//    // 打开系统的文件选择器
//    public void pickFile(View view) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("*/*");
//        this.startActivityForResult(intent, REQUEST_CODE);
//    }
//
//    // 获取文件的真实路径
//    @Override
//    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data == null) {
//            // 用户未选择任何文件，直接返回
//            return;
//        }
//        Uri uri = data.getData(); // 获取用户选择文件的URI
//        // 通过ContentProvider查询文件路径
//        ContentResolver resolver = this.getContentResolver();
//        Cursor cursor = resolver.query(uri, null, null, null, null);
//        if (cursor == null) {
//            // 未查询到，说明为普通文件，可直接通过URI获取文件路径
//            String path = uri.getPath();
//            return;
//        }
//        if (cursor.moveToFirst()) {
//            // 多媒体文件，从数据库中获取文件的真实路径
//            String path = cursor.getString(cursor.getColumnIndex("_data"));
//        }
//        cursor.close();
//    }


    // 使用OkHttp上传文件
    public void uploadFile(File file) {
        OkHttpClient client = new OkHttpClient();
        MediaType contentType = MediaType.parse("audio/amr"); // 上传文件的Content-Type
        RequestBody body = RequestBody.create(contentType, file); // 上传文件的请求体
        Log.d("Request",body.toString());
        Request request = new Request.Builder()
                .url("https://192.168.2.218:10080/uploadAudio/fileUpload") // 上传地址
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 文件上传成功
                if (response.isSuccessful()) {
                    Log.i("uploadsuccess", "onResponse: " + response.body().string());
                } else {
                    Log.i("uploadfile", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // 文件上传失败
                Log.i("uploadfailed", "onFailure: " + e.getMessage());
            }
        });
    }

}
