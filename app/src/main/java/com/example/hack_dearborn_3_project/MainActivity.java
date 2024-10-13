package com.example.hack_dearborn_3_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    Intent loginIntent;
    Intent signUpIntent;

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




        loginIntent=new Intent(MainActivity.this, Login.class);
        signUpIntent=new Intent(MainActivity.this, SignUp.class);

        //Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);
        //call the initializeDB() function to fill the records into our table
        dbHelper.initializeDB();


    }
}