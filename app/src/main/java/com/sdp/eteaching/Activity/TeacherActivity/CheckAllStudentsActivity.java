package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.util.JsonArrayUtil;
import com.sdp.eteaching.util.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class CheckAllStudentsActivity extends AppCompatActivity {
    private int classID;
    private int teacherID;
    private ListView mListView;
    private SimpleAdapter mAdapter;
    private ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_all_students);

        Intent intent=getIntent();
        classID=(int)intent.getExtras().get("classID");
        teacherID = (Integer) intent.getExtras().get("t_id");
        Log.i("classID", String.valueOf(classID));
        studentArrayList=getAllStudent(classID);
        initView();
        setListViewAdapter();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("teacherID",teacherID);
                bundle.putInt("studentID",studentArrayList.get(position).getStudent_id());
                Intent intentToStudentInfo=new Intent(CheckAllStudentsActivity.this,StudentInfoActivity.class);
                intentToStudentInfo.putExtras(bundle);
                startActivity(intentToStudentInfo);
            }

        });
    }

    private void initView() {
        mListView = (ListView)findViewById(R.id.student_info_list);

    }

    private void setListViewAdapter() {
        mAdapter = new SimpleAdapter(this,putData(),R.layout.student_info_item,
                new String[]{"学生姓名:","学生码:","手机号码:"},new int[]{R.id.studentinfo_tv1,R.id.studentinfo_tv2,R.id.studentinfo_tv3});//listdata和str均可

        mListView.setAdapter(mAdapter);

    }


    public ArrayList<Map<String,String>> putData(){
        ArrayList<Map<String,String>> studentList=new ArrayList<>();
        for(int i=0;i<studentArrayList.size();i++) {
            Map<String,String> map=new HashMap<>();
            map.put("学生姓名:","学生姓名:"+studentArrayList.get(i).getStudent_name());
            map.put("学生码:", "学生码:"+String.valueOf(studentArrayList.get(i).getStudent_id()));
            map.put("手机号码:","手机号码:"+studentArrayList.get(i).getS_phonenum());
            Log.i("mapinfo",map.get("学生姓名:"));
            studentList.add(map);
        }
        return  studentList;
    }

    public ArrayList<Student> getAllStudent(int classID) {
        ArrayList<Student> studentArrayList = new ArrayList<>();

        String param = "?class_id=" + classID;

        String path = "teacher/checkAllStudents.do";

        ResultData<ArrayList<Student>> resultData = JsonArrayUtil.getResult(path, param, Student.class);

        if (resultData != null) {

            studentArrayList = resultData.getData();
            Log.i("studentsArray", String.valueOf(resultData.getData()));

            Toast.makeText(CheckAllStudentsActivity.this, resultData.getMsg(), Toast.LENGTH_SHORT).show();

        }
        return studentArrayList;
    }

    public void jumpToMain(View v) {
        jumpTo();
    }

    public void jumpTo() {

        Intent in = new Intent(this, TeacherMainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("t_id", teacherID);

        in.putExtras(bundle);

        startActivity(in);

    }
}
