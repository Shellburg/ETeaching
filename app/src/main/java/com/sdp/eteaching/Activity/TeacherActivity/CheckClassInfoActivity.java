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
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.util.JsonArrayUtil;
import com.sdp.eteaching.util.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class CheckClassInfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private int teacherID;
    private ArrayList<Class> classArrayList;
    private ListView mListView;
    private SimpleAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_class_info);
        final Intent intent = getIntent();
        teacherID = (Integer) intent.getExtras().get("t_id");

        classArrayList=getAllClass(teacherID);

        initView();
        setListViewAdapter();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("classID",classArrayList.get(position).getClass_id());
                bundle.putInt("t_id", teacherID);
                Intent intentToStudentInfo=new Intent(CheckClassInfoActivity.this,CheckAllStudentsActivity.class);
                intentToStudentInfo.putExtras(bundle);
                startActivity(intentToStudentInfo);
            }

        });
    }

    private void initView() {
        mListView = (ListView)findViewById(R.id.class_info_list);

    }

    private void setListViewAdapter() {
        mAdapter = new SimpleAdapter(this,putData(),R.layout.class_info_item,
                new String[]{"班级名称:","班课码:","学校名称:"},new int[]{R.id.tv1,R.id.tv2,R.id.tv3});//listdata和str均可

        mListView.setAdapter(mAdapter);

    }


    public ArrayList<Map<String,String>> putData(){
        ArrayList<Map<String,String>> classList=new ArrayList<>();
        for(int i=0;i<classArrayList.size();i++) {
            Map<String,String> map=new HashMap<>();
            map.put("班级名称:","班级名称:"+classArrayList.get(i).getClass_name());
            map.put("班课码:", "班课码:"+String.valueOf(classArrayList.get(i).getClass_id()));
            map.put("学校名称:","学校名称:"+classArrayList.get(i).getSchool_name());
            Log.i("mapinfo",map.get("班级名称:"));
            classList.add(map);
        }
        return  classList;
    }

        public ArrayList<Class> getAllClass(int teacherId) {
        ArrayList<Class> classArrayList = new ArrayList<>();

        String param = "?teacher_id=" + teacherId;

        String path = "teacher/checkAllClass.do";

        ResultData<ArrayList<Class>> resultData = JsonArrayUtil.getResult(path, param, Class.class);

        if (resultData != null) {

            classArrayList = resultData.getData();
            Log.i("classArray", String.valueOf(resultData.getData()));

            Toast.makeText(CheckClassInfoActivity.this, resultData.getMsg(), Toast.LENGTH_SHORT).show();

        }
        return classArrayList;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
