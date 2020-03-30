package com.sdp.eteaching.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sdp.eteaching.Activity.StudentActivity.StudentLoginActivity;
import com.sdp.eteaching.Activity.TeacherActivity.TeacherLoginActivity;
import com.sdp.eteaching.R;

import androidx.appcompat.app.AppCompatActivity;

import static com.sdp.eteaching.util.AudioManager.verifyAudioPermissions;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        verifyAudioPermissions(IndexActivity.this);
        //MediaRecorder mRecorders = new MediaRecorder();
        //mRecorders.setAudioSource(MediaRecorder.AudioSource.MIC);
    }

    public void studentInter(View v){
        startActivity(new Intent(this, StudentLoginActivity.class));
    }

    public void teacherInter(View v){
        startActivity(new Intent(this, TeacherLoginActivity.class));
    }
}
