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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherRegisterActivity extends AppCompatActivity {

    private EditText t_phone,t_code,t_set_pwd,t_sure_pwd;
    private String phone_num,code,pwd,finalPwd;

    private boolean isOk = false;

    private Teacher teacher;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_register);

        t_phone = (EditText)findViewById(R.id.t_re_phone);

        t_code = (EditText)findViewById(R.id.t_code);

        t_set_pwd = (EditText)findViewById(R.id.t_set_pwd);

        t_sure_pwd = (EditText)findViewById(R.id.t_sure_pwd);

    }

    public void teacherGetCode(View view){

        phone_num = t_phone.getText().toString();

        if(phone_num!=null&&phone_num.trim().length()==11){

            Toast.makeText(TeacherRegisterActivity.this,"手机号格式正确",Toast.LENGTH_SHORT).show();

//短信验证码接口

//这里自动生成6位数字

            code = "";

            for (int i = 0; i < 6; i++) {

                code += (int) (Math.random() * 10);

            }

            Toast.makeText(TeacherRegisterActivity.this, "验证码=" + code, Toast.LENGTH_SHORT).show();

            t_code.setText(code);

            isOk = true;

        }else {

            Toast.makeText(TeacherRegisterActivity.this,"手机号格式错误",Toast.LENGTH_SHORT).show();

        }

    }

    public void teacherRegister(View v){

        phone_num = t_phone.getText().toString();

        pwd=t_set_pwd.getText().toString();

        Log.d("pwd:",pwd);

        Log.d("phonenum:",phone_num);

        finalPwd=t_sure_pwd.getText().toString();

        if(!checkStr(pwd)){

            toast("请输入密码");

            isOk = false;

        }

        if(!checkStr(finalPwd)){

            isOk = false;

            toast("请确认密码");

        }

        if (checkStr(pwd)){

            if(!pwd.equals(finalPwd)){

                isOk = false;

                toast("确认密码错误");

            }

        }

        if(isOk){

            String param = "?t_phonenum="+phone_num+"&teacher_password="+pwd;

            String path = "teacher/register.do";

            ResultData<Teacher> resultData = JsonUtil.getResult(path,param,Teacher.class);

            if (resultData!=null){

                Toast.makeText(TeacherRegisterActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    teacher = resultData.getData();

                    jumpTo();

                }

            }

        }

    }

    public boolean checkStr(String str){

        if(str==null||"".equals(str.trim())){

            return false;

        }

        return true;

    }

    public void toast(String msg){

        Toast.makeText(TeacherRegisterActivity.this,msg,Toast.LENGTH_SHORT).show();

        return;

    }

    public void jumpTo(){

        Intent in = new Intent(TeacherRegisterActivity.this, TeacherAddActivity.class);

        Bundle bundle = new Bundle();

        bundle.putLong("t_id",teacher.getAd_id());

        in.putExtras(bundle);

        startActivity(in);

    }

}
