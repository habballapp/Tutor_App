package com.example.tutor_app.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;
import com.example.tutor_app.UserType.User_Type;

public class Welcome extends AppCompatActivity {

    private RelativeLayout btn_signin,btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_signin = findViewById(R.id.btn_signin);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this, SignIn.class);
                startActivity(intent);
            }

        });

        btn_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this, User_Type.class);
                startActivity(intent);
            }

        });



    }



}
