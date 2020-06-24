package com.example.tutor_app.UserType;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tutor_app.R;
import com.example.tutor_app.SignUp.Signup_Institute;
import com.example.tutor_app.SignUp.Signup_Student;
import com.example.tutor_app.SignUp.Signup_Teacher;

public class User_Type extends AppCompatActivity {

    private RelativeLayout rl_user,rl_user_teacher,rl_user_institute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__type);

        rl_user =  findViewById(R.id.rl_user);
        rl_user_teacher =  findViewById(R.id.rl_user_teacher);
        rl_user_institute =  findViewById(R.id.rl_user_institute);

        rl_user.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(User_Type.this, Signup_Student.class);
                startActivity(intent);
            }

        });

        rl_user_institute.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(User_Type.this, Signup_Teacher.class);
                startActivity(intent);
            }

        });

        rl_user_teacher.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(User_Type.this, Signup_Institute.class);
                startActivity(intent);
            }

        });

    }
}
