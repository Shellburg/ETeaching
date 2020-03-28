package com.sdp.eteaching.Activity.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.util.JsonUtil;
import com.sdp.eteaching.util.ResultData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentAddActivity extends AppCompatActivity {

    private EditText s_nick,s_schoolname,s_gender;

    private String nick,school_name,gender;

    private int studentID;

    private Student student;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_add);

        s_nick = (EditText)findViewById(R.id.s_nick);

        s_gender=(EditText)findViewById(R.id.s_gender);

        s_schoolname=(EditText)findViewById(R.id.s_schoolname);

//获取用户id

        Intent intent = getIntent();

        studentID = (Integer) intent.getExtras().get("s_id");

    }

    public void enterIn(View v){

        nick = s_nick.getText().toString();

        gender=s_gender.getText().toString();

        school_name=s_schoolname.getText().toString();

        boolean isOk = true;

//检查是否为空

        if(!checkStr(nick)){

            toast("请输入用户名");

            isOk=false;

        }



//检查是否合格

        if (isOk){

//补全信息

            String param = "?student_id="+studentID+"&student_name="+nick+"&gender="+gender+"&school_name="+school_name;

            String path = "student/updateMessage.do";

            ResultData<Student> resultData = JsonUtil.getResult(path,param,Student.class);

            if (resultData!=null){

                student=resultData.getData();

                Toast.makeText(StudentAddActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    System.out.println("user="+resultData.getData());

                    jumpTo();

                }

            }

        }

    }

//进入首页

    private void jumpTo() {

        Intent in=new Intent(StudentAddActivity.this, StudentMainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("s_id",student.getStu_id());

        in.putExtras(bundle);

        startActivity(in);

    }

    public boolean checkStr(String str){

        if(str==null||"".equals(str.trim())){

            return false;

        }

        return true;

    }

    public void toast(String msg){

        Toast.makeText(StudentAddActivity.this,msg,Toast.LENGTH_SHORT).show();

        return;

    }

}