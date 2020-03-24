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

import androidx.appcompat.app.AppCompatActivity;

public class StudentLoginActivity extends AppCompatActivity {
    private EditText et_main_phonenum;
    private EditText et_main_upass;
    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        et_main_phonenum=(EditText)findViewById(R.id.phonenum);
        et_main_upass=(EditText)findViewById(R.id.pwd);
    }


    public void studentLogin(View view){
        String uname=et_main_phonenum.getText().toString();
        String upass=et_main_upass.getText().toString();
        System.out.println(uname+upass);

//判断

        if (checkStr(uname)&&checkStr(upass)){

//上传的参数部分

            String param = "?s_phonenum="+uname+"&student_password="+upass;

            String path = "student/login.do";

            ResultData<Student> resultData =

                    JsonUtil.getResult(path,param,Student.class);

            student=resultData.getData();

            if (resultData!=null){

                Toast.makeText(StudentLoginActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    jumpTo();

                }

            }

        }else {

            Toast.makeText(StudentLoginActivity.this,"输入有误",Toast.LENGTH_SHORT).show();

        }

    }

    public void register(View v){

        Intent in = new Intent(this, StudentRegisterActivity.class);

        startActivity(in);

    }

    public boolean checkStr(String str){

        if(str==null||"".equals(str.trim())){

            return false;

        }

        return true;

    }

    public void jumpTo(){

        Intent in = new Intent(this, StudentMainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("s_id",student.getStudent_id());

        in.putExtras(bundle);

        startActivity(in);

    }

    }
