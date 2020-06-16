package com.example.tutor_app.Signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tutor_app.Dashboard.Dashboard_Drawer;
import com.example.tutor_app.ForgetPassword.Forget_Password;
import com.example.tutor_app.R;

public class SignIn extends AppCompatActivity {

    private RelativeLayout btn_signin;
    private TextView txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signin = findViewById(R.id.btn_signin_user);
        txt_password = findViewById(R.id.txt_password);

        btn_signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(SignIn.this, Dashboard_Drawer.class);
                startActivity(intent);
            }

        });

        txt_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignIn.this, Forget_Password.class);
                startActivity(intent);
            }
        });
    }
}
