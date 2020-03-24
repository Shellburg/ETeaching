package com.sdp.eteaching.Activity.TeacherActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sdp.eteaching.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class TeacherMainActivity extends AppCompatActivity {
    private String phone;
    private int teacherId;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        BottomNavigationView navView = findViewById(R.id.teacher_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.teacher_navigation_home, R.id.teacher_navigation_dashboard, R.id.teacher_navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.teacher_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent=getIntent();
        phone=(String)intent.getExtras().get("phone");
        teacherId=(Integer) intent.getExtras().get("t_id");



    }

    public void updateTeacherMessage(View v) {
        Intent intent = new Intent(this, TeacherAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void addClass(View v){
        Intent intent = new Intent(this, AddClassActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void checkClassInfo(View v){
        Intent intent = new Intent(this, CheckClassInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("t_id", teacherId);

        intent.putExtras(bundle);
        startActivity(intent);
    }

}
