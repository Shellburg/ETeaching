package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sdp.eteaching.R;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.util.AssiginHomeworkUtil.ItemBean;
import com.sdp.eteaching.util.AssiginHomeworkUtil.ListViewWithCheckBoxAdapter;
import com.sdp.eteaching.util.JsonArrayUtil;
import com.sdp.eteaching.util.ResultData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class AssignHomeworkActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private int teacherID;
    private ArrayList<Class> classArrayList;
    private Set<Integer> classIDSet=new HashSet<>();
    private ListView mListView;
    private SimpleAdapter mAdapter;

    private LinearLayout linearLayout;//按钮布局
    private Button sure,cancel;
    private ListView listView;
    private ListViewWithCheckBoxAdapter adapter;
    private List<ItemBean> list;
    private boolean isLineaLayoutVisible = true;//标记按钮布局的显示


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_homework);
        final Intent intent = getIntent();
        teacherID = (Integer) intent.getExtras().get("t_id");

        classArrayList=getAllClass(teacherID);

        //initView();
        //setListViewAdapter();

        listView = (ListView)findViewById(R.id.class_info_list_for_homework);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        linearLayout = (LinearLayout) findViewById(R.id.assign_homework_linearLayout);
        sure = (Button) findViewById(R.id.sure);
        cancel = (Button) findViewById(R.id.cancel);
        sure.setOnClickListener(this);
        cancel.setOnClickListener(this);
        list = new ArrayList<ItemBean>();

        for(int i=0;i<classArrayList.size();i++) {
            Map<String,String> map=new HashMap<>();
            map.put("班级名称:","班级名称:"+classArrayList.get(i).getClass_name());
            map.put("班课码:", "班课码:"+classArrayList.get(i).getClass_id());
            map.put("学校名称:","学校名称:"+classArrayList.get(i).getSchool_name());
            Log.i("mapinfo",map.get("班级名称:"));
            list.add(new ItemBean("班课码："+classArrayList.get(i).getClass_id(),"班级名称"+classArrayList.get(i).getClass_name(),"学校名称:"+classArrayList.get(i).getSchool_name(),false,true));
        }
        adapter = new ListViewWithCheckBoxAdapter(this,list);


//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                classIDSet.add(classArrayList.get(position).getClass_id());
//                Log.i("classID", String.valueOf(classArrayList.get(position).getClass_id()));
//                for (Iterator iterator = classIDSet.iterator(); iterator.hasNext();) {
//                    Integer classid = (Integer) iterator.next();
//                    Log.i("classIDSet", String.valueOf(classid));
//                }
//
//            }
//
//        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        //listView.setOnItemLongClickListener(this);
    }

//    private void initView() {
//        mListView = (ListView)findViewById(R.id.class_info_list_for_homework);
//
//    }

//    private void setListViewAdapter() {
//        mAdapter = new SimpleAdapter(this,putData(),R.layout.class_info_item_for_homework,
//                new String[]{"班级名称:","班课码:","学校名称:"},new int[]{R.id.class_name_homework,R.id.class_id_homework,R.id.school_name_homework});//listdata和str均可
//
//        mListView.setAdapter(mAdapter);
//
//    }
//
//
//    public ArrayList<Map<String,String>> putData(){
//        ArrayList<Map<String,String>> classList=new ArrayList<>();
//        for(int i=0;i<classArrayList.size();i++) {
//            Map<String,String> map=new HashMap<>();
//            map.put("班级名称:","班级名称:"+classArrayList.get(i).getClass_name());
//            map.put("班课码:", "班课码:"+String.valueOf(classArrayList.get(i).getClass_id()));
//            map.put("学校名称:","学校名称:"+classArrayList.get(i).getSchool_name());
//            Log.i("mapinfo",map.get("班级名称:"));
//            classList.add(map);
//        }
//        return  classList;
//    }

    public ArrayList<Class> getAllClass(int teacherId) {
        ArrayList<Class> classArrayList = new ArrayList<>();

        String param = "?teacher_id=" + teacherId;

        String path = "teacher/checkAllClass.do";

        ResultData<ArrayList<Class>> resultData = JsonArrayUtil.getResult(path, param, Class.class);

        if (resultData != null) {

            classArrayList = resultData.getData();
            Log.i("classArray", String.valueOf(resultData.getData()));

            Toast.makeText(AssignHomeworkActivity.this, resultData.getMsg(), Toast.LENGTH_SHORT).show();

        }
        return classArrayList;
    }

//    public void jumpToMain(View v) {
//        jumpTo();
//    }
//
//    public void jumpTo() {
//
//        Intent in = new Intent(this, TeacherMainActivity.class);
//
//        Bundle bundle = new Bundle();
//
//        bundle.putInt("t_id", teacherID);
//
//        in.putExtras(bundle);
//
//        startActivity(in);
//
//    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListViewWithCheckBoxAdapter.ViewHolder viewHolder = (ListViewWithCheckBoxAdapter.ViewHolder) view.getTag();


        //if(isLineaLayoutVisible){//当按钮布局显示时候才有权多项选择
            list.get(position).setIsSelect(list.get(position).isSelect());//向表中记录被选择的item
            adapter.notifyDataSetChanged();//更新ListView
        //}
//        else{
//            Toast.makeText(this,list.get(position).getClass_name_homework()+"班",Toast.LENGTH_SHORT).show();
//        }

    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//        list.get(position).setIsSelect(true);//记录选择的item
//        for(ItemBean itemBean : list){
//            itemBean.setIsShowCheckBox(true);//将所有的Item的CheckBox设置为选择状态
//        }
//        adapter.notifyDataSetChanged();
//        linearLayout.setVisibility(View.VISIBLE);//长按item设置按钮布局为显示状态
//        isLineaLayoutVisible = true;
//        return true;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure:
                String str = "";
                for(ItemBean itemBean : list){
                    if(itemBean.isSelect()){
                        str+=itemBean.getClass_name_homework()+"班"+"\n";
                        classIDSet.add(Integer.valueOf(itemBean.getClass_id_homework().substring(4)));
                    }
                }
                if(str.equals("")){
                    Toast.makeText(this,"您没有选择",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this,str,Toast.LENGTH_SHORT).show();

                    ArrayList<Integer> classIdList=new ArrayList<>();
                    Bundle bundle=new Bundle();
                    for(Integer integer : classIDSet){
                        classIdList.add(integer);
                    }
                    bundle.putIntegerArrayList("ClassesID",classIdList);


                }
                break;
            case R.id.cancel:
                if(cancel.getText().equals("选择全部")){
                    for(ItemBean itemBean : list){
                        itemBean.setIsSelect(true);
                    }
                    cancel.setText("取消全部");
                }else{
                    for(ItemBean itemBean : list){
                        itemBean.setIsSelect(false);
                    }
                    cancel.setText("选择全部");
                }


                adapter.notifyDataSetChanged();
                break;

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

//    @Override
//    public void onBackPressed() {
//        if (isLineaLayoutVisible){
//            linearLayout.setVisibility(View.INVISIBLE);
//            isLineaLayoutVisible = false;
//            for(ItemBean itemBean : list){//按返回键时取消所有选择记录，同是吧按钮布局设置为不可见
//                itemBean.setIsShowCheckBox(false);
//                itemBean.setIsSelect(false);
//            }
//            adapter.notifyDataSetChanged();
//        }else {
//            super.onBackPressed();
//        }
//    }

}
