package com.example.hack_dearborn_3_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Groups extends AppCompatActivity {

    DatabaseHelper dbHelper;
    GoalListAdapter adapter;
    ArrayList<Goals> goalList;

    Intent homePageIntent;
    Intent groupPageIntent;
    Intent newGroupIntent;

    ListView g_lv_j_goals;
    ImageView g_iv_j_newGroup;
    ImageView g_iv_j_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_groups);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        g_lv_j_goals=findViewById(R.id.g_lv_v_goals);
        g_iv_j_newGroup=findViewById(R.id.g_iv_v_newGroup);
        g_iv_j_back=findViewById(R.id.g_iv_v_back);

        homePageIntent=new Intent(Groups.this, Homepage.class);
        groupPageIntent=new Intent(Groups.this, GroupPage.class);
        newGroupIntent=new Intent(Groups.this, NewGroup.class);

        goalList=new ArrayList<Goals>();
        dbHelper=new DatabaseHelper(this);
        goalList=dbHelper.getAllGoals();

        fillListView();
        newGroupButtonEvent();
        backButtonEvent();
    }

    public void newGroupButtonEvent()
    {
        g_iv_j_newGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newGroupIntent);
            }
        });
    }

    public void backButtonEvent()
    {
        g_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homePageIntent);
            }
        });
    }

    //Miscellaneous Functions=======================================================================

    public void fillListView()
    {
        adapter=new GoalListAdapter(this, goalList);
        //set the listview's adapter
        g_lv_j_goals.setAdapter(adapter);
    }
}