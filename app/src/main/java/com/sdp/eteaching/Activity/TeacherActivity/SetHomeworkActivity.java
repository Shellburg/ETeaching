package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Homework;
import com.sdp.eteaching.util.JsonUtil;
import com.sdp.eteaching.util.ResultData;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SetHomeworkActivity extends AppCompatActivity {
    private ArrayList<Integer> classIdArray;
    private int teacherID;
    private String homeworkDescription,deadline;
    private EditText homework_description,e_deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_homework);
        Intent intent=getIntent();
        classIdArray=intent.getIntegerArrayListExtra("ClassesID");
        teacherID=(int)intent.getExtras().get("t_id");

        homework_description=(EditText)findViewById(R.id.homework_description);
        e_deadline=(EditText)findViewById(R.id.deadline);
    }

    public void add_homework(View v){

        homeworkDescription=homework_description.getText().toString();

        deadline=e_deadline.getText().toString();

        boolean isOk = true;

//检查是否合格

        if (isOk){

////补全信息

            String param = "?teacher_id="+teacherID+"&class_id="+classIdArray+"&homework_description="+homeworkDescription+"&deadline="+deadline;

            String path = "teacher/assignHomework.do";

            ResultData<Homework> resultData = JsonUtil.getResult(path,param, Homework.class);

            if (resultData!=null){

                Toast.makeText(SetHomeworkActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    //System.out.println("class="+resultData.getData());

                    jumpTo();

                }

            }

        }

    }

    public void jumpTo(){
        Intent in = new Intent(this, ShowClassIdActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("t_id",teacherID);

        in.putExtras(bundle);

        startActivity(in);
    }
}
