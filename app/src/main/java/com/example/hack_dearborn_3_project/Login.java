package com.example.hack_dearborn_3_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    DatabaseHelper dbHelper;

    Intent mainIntent;
    Intent homePageIntent;

    TextView l_tv_j_error;
    ImageView l_iv_j_back;
    ImageView l_iv_j_login;
    EditText l_et_j_uname;
    EditText l_et_j_pword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        l_tv_j_error=findViewById(R.id.l_tv_v_error);
        l_iv_j_back=findViewById(R.id.l_iv_v_back);
        l_iv_j_login=findViewById(R.id.l_iv_v_login);
        l_et_j_uname=findViewById(R.id.l_et_v_uname);
        l_et_j_pword=findViewById(R.id.l_et_v_pword);


        mainIntent=new Intent(Login.this, MainActivity.class);
        homePageIntent=new Intent(Login.this, Homepage.class);

        dbHelper = new DatabaseHelper(this);

        loginButtonEvent();
        backButtonEvent();
    }


    //USERNAME-PASSWORD CHECKING===================================================================
    public void loginButtonEvent()
    {
        l_iv_j_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!usernamePasswordNull())
                {
                    //call dbHelper and pass the username and password.
                    String username = l_et_j_uname.getText().toString();
                    String password = l_et_j_pword.getText().toString();

                    if(dbHelper.validUsernamePasswordCombo(username, password))
                    {
                        l_tv_j_error.setVisibility(View.INVISIBLE);

                        //Set username as static string
                        AppData.setUsername(username);

                        startActivity(homePageIntent);


                    }
                    else
                    {
                        l_tv_j_error.setVisibility(View.VISIBLE);
                        l_tv_j_error.setText("Username and/or Password was incorrect");
                        l_et_j_uname.setText("Username");
                        l_et_j_pword.setText("Password");
                    }
                }
                else
                {
                    l_tv_j_error.setVisibility(View.VISIBLE);
                    l_tv_j_error.setText("Please enter a username & password");
                }

            }

        });
    }

    private boolean usernamePasswordNull()
    {
        String username = l_et_j_uname.getText().toString();
        String password = l_et_j_pword.getText().toString();
        if(username.equals("Username") && password.equals("Password"))
        {
            return true;
        }
        else if(username.equals("") && password.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //UI===========================================================================================
    public void backButtonEvent()
    {
        l_iv_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainIntent);
            }
        });
    }


}