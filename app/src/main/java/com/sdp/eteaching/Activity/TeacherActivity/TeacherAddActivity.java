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

public class TeacherAddActivity extends AppCompatActivity {
    private EditText t_nick,t_schoolname,t_gender;

    private String nick,school_name,gender;

    private int teacherId;

    private Teacher teacher;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_add);

        t_nick = (EditText)findViewById(R.id.t_nick);

        t_gender=(EditText)findViewById(R.id.t_gender);

        t_schoolname=(EditText)findViewById(R.id.t_schoolname);

//获取用户id

        Intent intent = getIntent();

        teacherId= (Integer) intent.getExtras().get("t_id");

        Log.d("gotID", String.valueOf(teacherId));

    }

    public void enterIn(View v){

        nick = t_nick.getText().toString();

        gender=t_gender.getText().toString();

        school_name=t_schoolname.getText().toString();

        boolean isOk = true;

//检查是否为空

        if(!checkStr(nick)){

            toast("请输入用户名");

            isOk=false;

        }



//检查是否合格

        if (isOk){

//补全信息

            String param = "?teacher_id="+teacherId+"&teacher_name="+nick+"&gender="+gender+"&school_name="+school_name;

            String path = "teacher/updateMessage.do";

            ResultData<Teacher> resultData = JsonUtil.getResult(path,param,Teacher.class);

            if (resultData!=null){

                teacher=resultData.getData();

                Toast.makeText(TeacherAddActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    System.out.println("user="+resultData.getData());

                    jumpTo();

                }

            }

        }

    }

//进入首页

    private void jumpTo() {

        Intent in=new Intent(TeacherAddActivity.this, TeacherMainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("t_id",teacher.getTeacher_id());

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

        Toast.makeText(TeacherAddActivity.this,msg,Toast.LENGTH_SHORT).show();

        return;

    }

}
