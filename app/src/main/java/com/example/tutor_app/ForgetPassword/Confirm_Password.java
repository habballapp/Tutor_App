package com.example.tutor_app.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;

public class Confirm_Password extends AppCompatActivity {

    private RelativeLayout signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__password);

        signin = findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Confirm_Password.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}
