package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Teacher;
import com.sdp.eteaching.util.JsonUtil;
import com.sdp.eteaching.util.ResultData;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherLoginActivity extends AppCompatActivity {
    private EditText et_main_phonenum;
    private EditText et_main_upass;
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        et_main_phonenum=(EditText)findViewById(R.id.phonenum);
        et_main_upass=(EditText)findViewById(R.id.pwd);
    }
    public void teacherLogin(View view){
        String uname=et_main_phonenum.getText().toString();
        String upass=et_main_upass.getText().toString();
        System.out.println(uname+upass);

//判断

        if (checkStr(uname)&&checkStr(upass)){

//上传的参数部分

            String param = "?t_phonenum="+uname+"&teacher_password="+upass;

            String path = "teacher/login.do";

            ResultData<Teacher> resultData =

                    JsonUtil.getResult(path,param,Teacher.class);


            if(resultData==null){
                Toast.makeText(TeacherLoginActivity.this,"服务器未返回数据",Toast.LENGTH_SHORT).show();
            }

            else{
                teacher=resultData.getData();
            }

            if (resultData!=null){

                Toast.makeText(TeacherLoginActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    jumpTo();

                }

            }

        }else {

            Toast.makeText(TeacherLoginActivity.this,"输入有误",Toast.LENGTH_SHORT).show();

        }

    }

    public void register(View v){

        Intent in = new Intent(this, TeacherRegisterActivity.class);

        startActivity(in);

    }

    public boolean checkStr(String str){

        if(str==null||"".equals(str.trim())){

            return false;

        }

        return true;

    }

    public void jumpTo(){

        Intent in = new Intent(this,TeacherMainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("t_id",teacher.getTeacher_id());

        Log.d("teacherid", String.valueOf(teacher.getTeacher_id()));

        in.putExtras(bundle);

        startActivity(in);

    }

}
