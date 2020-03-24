package com.sdp.eteaching.Activity.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.util.JsonUtil;
import com.sdp.eteaching.util.ResultData;

import androidx.appcompat.app.AppCompatActivity;

public class JoinInClassActivity extends AppCompatActivity {
    private int studentID;
    private int classID;
    private EditText class_id;
    private Class englishClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_in_class);
        Intent intent=getIntent();
        studentID=(Integer) intent.getExtras().get("s_id");
        class_id=(EditText)findViewById(R.id.join_class_id);
    }

    public void Join_in_class(View v){
        classID=Integer.parseInt(class_id.getText().toString());
        Log.d("class_id", String.valueOf(classID));
        boolean isOk = true;

//检查是否合格

        if (isOk){

////补全信息

            String param = "?student_id="+studentID+"&class_id="+classID;

            String path = "student/joinInClass.do";

            ResultData<Class> resultData = JsonUtil.getResult(path,param, Student.class);

            if (resultData!=null){

                //englishClass=resultData.getData();

                Toast.makeText(JoinInClassActivity.this,resultData.getMsg(),Toast.LENGTH_SHORT).show();

                if (resultData.getStatus()==1){

                    System.out.println("class="+resultData.getData());

                    jumpTo();

                }

            }

        }

    }

    public void jumpTo(){

        Intent in = new Intent(this,StudentMainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("s_id",studentID);

        in.putExtras(bundle);

        startActivity(in);

    }
}
