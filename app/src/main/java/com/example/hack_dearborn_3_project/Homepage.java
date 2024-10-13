package com.example.hack_dearborn_3_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Homepage extends AppCompatActivity {

    Intent profileIntent;
    Intent groupsIntent;
    Intent homeIntent;

    TextView hp_tv_j_profile;
    TextView hp_tv_j_groups;
    ImageView hp_iv_j_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        hp_tv_j_profile=findViewById(R.id.hp_tv_v_profile);
        hp_tv_j_groups=findViewById(R.id.hp_tv_v_groups);
        hp_iv_j_back=findViewById(R.id.hp_iv_v_back);

        profileIntent=new Intent(Homepage.this, UserProfile.class);
        groupsIntent=new Intent(Homepage.this, Groups.class);
        homeIntent=new Intent(Homepage.this, MainActivity.class);


        profileButtonEvent();
        groupsButtonEvent();
        backButtonEvent();
    }

    public void profileButtonEvent()
    {
        hp_tv_j_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(profileIntent);}
        });
    }

    public void groupsButtonEvent()
    {
        hp_tv_j_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(groupsIntent);}
        });
    }

    public void backButtonEvent()
    {
        hp_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeIntent);
            }
        });
    }

}