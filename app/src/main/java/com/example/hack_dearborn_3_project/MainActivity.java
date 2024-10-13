package com.example.hack_dearborn_3_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    Intent loginIntent;
    Intent signUpIntent;

    ImageView ma_iv_j_login;
    ImageView ma_iv_j_signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ma_iv_j_login=findViewById(R.id.ma_iv_v_login);
        ma_iv_j_signUp=findViewById(R.id.ma_iv_v_signUp);


        loginIntent=new Intent(MainActivity.this, Login.class);
        signUpIntent=new Intent(MainActivity.this, SignUp.class);

        //Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        //call the initializeDB() function to fill the records into our table
        dbHelper.initializeDB();

        loginButtonEvent();
        signUpButtonEvent();
    }

    public void loginButtonEvent()
    {
        ma_iv_j_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginIntent);
            }
        });
    }

    public void signUpButtonEvent()
    {
        ma_iv_j_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(signUpIntent);
            }
        });
    }
}