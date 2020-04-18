package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sdp.eteaching.R;

import androidx.appcompat.app.AppCompatActivity;

public class StudentInfoActivity extends AppCompatActivity {
    private int studentID;
    private int teacherID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent=getIntent();
        studentID= (int) intent.getExtras().get("studentID");
        teacherID=(int)intent.getExtras().get("teacherID");


    }

    public void checkHomework(View view){

    }
}
