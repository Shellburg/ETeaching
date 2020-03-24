package com.sdp.eteaching.Activity.StudentActivity;

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

public class StudentMainActivity extends AppCompatActivity {
    private int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        BottomNavigationView navView = findViewById(R.id.student_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.student_navigation_home, R.id.student_navigation_dashboard, R.id.student_navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.student_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent=getIntent();
        studentID=(Integer) intent.getExtras().get("s_id");

    }


    public void updateMessage(View v){
        Intent intent=new Intent(this, StudentAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("s_id",studentID);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void joinInClass(View v){
        Intent intent=new Intent(this, JoinInClassActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("s_id",studentID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
