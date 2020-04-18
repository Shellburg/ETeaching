package com.sdp.eteaching.Activity.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Homework;
import com.sdp.eteaching.util.JsonArrayUtil;
import com.sdp.eteaching.util.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class CheckMyHomeworkActivity extends AppCompatActivity {
    private int studentID;
    private ArrayList<Homework> homeworkArrayList;
    private ListView mListView;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_homework);

        final Intent intent=getIntent();
        studentID=(int)intent.getExtras().get("s_id");

        homeworkArrayList=getAllHomework(studentID);

    initView();
    setListViewAdapter();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Bundle bundle=new Bundle();
            bundle.putInt("s_id",studentID);
            bundle.putInt("homeworkID",homeworkArrayList.get(position).getHomework_id());
            Intent intent1=new Intent(CheckMyHomeworkActivity.this,StudentAudioActivity.class);
            intent1.putExtras(bundle);
            startActivity(intent1);
        }

    });
}

    private void initView() {
        mListView = (ListView)findViewById(R.id.homework_student);

    }

    private void setListViewAdapter() {
        mAdapter = new SimpleAdapter(this,putData(),R.layout.homework_info_student,
                new String[]{"班级名称","教师姓名","作业内容","作业布置时间","作业提交截止时间"},new int[]{R.id.my_class,R.id.my_teacher_id,R.id.my_homework_description,R.id.start_time_student,R.id.deadline_student});//listdata和str均可

        mListView.setAdapter(mAdapter);

    }


    public ArrayList<Map<String,String>> putData(){
        ArrayList<Map<String,String>> homeworkList=new ArrayList<>();
        for(int i=0;i<homeworkArrayList.size();i++) {
            Map<String,String> map=new HashMap<>();
            map.put("班级名称","班级名称:"+homeworkArrayList.get(i).getClass_id());
            map.put("教师姓名","教师姓名:"+homeworkArrayList.get(i).getTeacher_id());
            map.put("作业内容", "作业内容:"+String.valueOf(homeworkArrayList.get(i).getHomework_description()));
            map.put("作业布置时间","作业布置时间:"+homeworkArrayList.get(i).getStart_time());
            map.put("作业提交截止时间","作业提交截止时间:"+homeworkArrayList.get(i).getDeadline());
            //Log.i("mapinfo",map.get("班级名称:"));
            homeworkList.add(map);
        }
        return  homeworkList;
    }

    public ArrayList<Homework> getAllHomework(int studentID) {
        ArrayList<Homework> homeworkArrayList = new ArrayList<>();

        String param = "?student_id=" + studentID;

        String path = "student/checkAllHomework.do";

        ResultData<ArrayList<Homework>> resultData = JsonArrayUtil.getResult(path, param, Homework.class);

        if (resultData != null) {

            homeworkArrayList = resultData.getData();
            Log.i("homeworkArray", String.valueOf(resultData.getData()));

            Toast.makeText(CheckMyHomeworkActivity.this, resultData.getMsg(), Toast.LENGTH_SHORT).show();

        }
        return homeworkArrayList;
    }
}
