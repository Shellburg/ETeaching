package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sdp.eteaching.R;

import androidx.appcompat.app.AppCompatActivity;

public class CheckClassInfoActivity extends AppCompatActivity {
    private int teacherID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_class_info);
        Intent intent=getIntent();
        teacherID=(Integer)intent.getExtras().get("t_id");

    }
}
