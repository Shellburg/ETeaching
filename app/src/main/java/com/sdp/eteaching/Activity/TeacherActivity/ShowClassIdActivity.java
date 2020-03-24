package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sdp.eteaching.R;

import androidx.appcompat.app.AppCompatActivity;

public class ShowClassIdActivity extends AppCompatActivity {

    private int teacherID,classID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_class_id);
        Intent intent=getIntent();
        teacherID=(Integer)intent.getExtras().get("t_id");
        classID=(Integer)intent.getExtras().get("class_id");
        TextView text=findViewById(R.id.show_class_id);
        text.setText(""+classID);
        Log.d("showclassid", String.valueOf(classID));
    }

    public void jumpToMain(View v){
        jumpTo();
    }

    public void jumpTo(){

        Intent in = new Intent(this,TeacherMainActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt("t_id",teacherID);

        bundle.putInt("class_id",classID);

        in.putExtras(bundle);

        startActivity(in);

    }
}
