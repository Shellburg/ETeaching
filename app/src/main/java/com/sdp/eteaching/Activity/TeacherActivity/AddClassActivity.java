package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.util.JsonUtil;
import com.sdp.eteaching.util.ResultData;

import androidx.appcompat.app.AppCompatActivity;

public class AddClassActivity extends AppCompatActivity {
    private String schoolName,className;
    private EditText school_name,class_name;
    private int teacherID;
    private Class englishClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Intent intent=getIntent();
        teacherID=(Integer) intent.getExtras().get("t_id");

        school_name=(EditText)findViewById(R.id.school_name);
        class_name=(EditText)findViewById(R.id.class_name);
    }

    public void add_class_message(View v){

        schoolName=school_name.getText().toString();

        className=class_name.getText().toString();

        boolean isOk = true;

//检查是否合格

         if (isOk){

////补全信息

            String param = "?teacher_id="+teacherID+"&class_name="+className+"&school_name="+schoolName;

            String path = "teacher/createClass.do";

            ResultData<Class> resultData = JsonUtil.getResult(path,param, Class.class);

            if (resultData!=null){

                englishClass=resultData.getData();

                Toast.makeText(AddClassActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    System.out.println("class="+resultData.getData());

                    jumpTo();

                }

            }

        }

   }

    public void jumpTo(){
        Intent in = new Intent(this, ShowClassIdActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("t_id",teacherID);

        bundle.putInt("class_id",englishClass.getClass_id());

        in.putExtras(bundle);

        startActivity(in);
    }

}
