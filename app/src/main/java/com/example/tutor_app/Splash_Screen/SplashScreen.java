package com.example.tutor_app.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tutor_app.Dashboard.ui.Institude.Dashboard_Drawer_Institute;
import com.example.tutor_app.Dashboard.ui.Student.Dashboard_Drawer_Student;
import com.example.tutor_app.Dashboard.ui.Teacher.Dashboard_Drawer_Teacher;
import com.example.tutor_app.Session;
import com.example.tutor_app.Welcome.Welcome;
import com.example.tutor_app.R;

public class SplashScreen extends AppCompatActivity {


    private ImageView tutor_logo;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tutor_logo = findViewById(R.id.tutor_logo);
        session = new Session(this);

        Log.i("12487654", "in splash");

        Animation myAnimation_icon = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myAnimation_icon.setDuration(5000);
        tutor_logo.startAnimation(myAnimation_icon);
        final SharedPreferences personal_profile = getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.getusename().equals("")){
                    Intent intent = new Intent(SplashScreen.this, Welcome.class);
                    startActivity(intent);
                    finish();
                }
                else if( !session.getusename().equals("") && personal_profile.getString("userrole" ,"").equals("Student"))  {
                    Intent intent = new Intent(SplashScreen.this, Dashboard_Drawer_Student.class);
                    startActivity(intent);
                    finish();
                }
                else if(!session.getusename().equals(" ") &&!session.getusename().equals("null")  && personal_profile.getString("userrole" ,"").equals("Teacher"))  {
                    Intent intent = new Intent(SplashScreen.this, Dashboard_Drawer_Teacher.class);
                    startActivity(intent);
                    finish();
                }
                else if(personal_profile.getString("userrole" ,"").equals("Institute"))  {
                    Intent intent = new Intent(SplashScreen.this, Dashboard_Drawer_Institute.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 4000);
    }
}
