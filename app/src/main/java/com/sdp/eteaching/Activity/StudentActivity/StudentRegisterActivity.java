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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentRegisterActivity extends AppCompatActivity {
    private EditText s_phone,s_code,s_set_pwd,s_sure_pwd;
    private String phone_num,code,pwd,finalPwd;

    private boolean isOk = false;

    private Student student;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_register);

        s_phone = (EditText)findViewById(R.id.re_phone);

        s_code = (EditText)findViewById(R.id.s_code);

        s_set_pwd = (EditText)findViewById(R.id.s_set_pwd);

        s_sure_pwd = (EditText)findViewById(R.id.s_sure_pwd);

    }

    public void studentGetCode(View view){

        phone_num = s_phone.getText().toString();

        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone_num);

        if(phone_num!=null&&phone_num.trim().length()==11&&m.matches()){

            Toast.makeText(StudentRegisterActivity.this,"手机号格式正确",Toast.LENGTH_SHORT).show();

//短信验证码接口

//这里自动生成6位数字

            code = "";

            for (int i = 0; i < 6; i++) {

                code += (int) (Math.random() * 10);

            }

            Toast.makeText(StudentRegisterActivity.this, "验证码=" + code, Toast.LENGTH_SHORT).show();

            s_code.setText(code);

            isOk = true;

        }else {

            Toast.makeText(StudentRegisterActivity.this,"手机号格式错误",Toast.LENGTH_SHORT).show();

        }

    }

    public void studentRegister(View v){

        phone_num = s_phone.getText().toString();

        pwd=s_set_pwd.getText().toString();

        finalPwd=s_sure_pwd.getText().toString();

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

            String param = "?s_phonenum="+phone_num+"&student_password="+pwd;

            String path = "student/register.do";

            ResultData<Student> resultData = JsonUtil.getResult(path,param,Student.class);

            if (resultData!=null){

                Toast.makeText(StudentRegisterActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    student = resultData.getData();

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

        Toast.makeText(StudentRegisterActivity.this,msg,Toast.LENGTH_SHORT).show();

        return;

    }

    public void jumpTo(){

        Intent in = new Intent(StudentRegisterActivity.this, StudentAddActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("s_id",student.getStudent_id());

        in.putExtras(bundle);

        startActivity(in);

    }

}
