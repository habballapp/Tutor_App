package com.example.tutor_app.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tutor_app.Dashboard.Dashboard_Drawer;
import com.example.tutor_app.Welcome.Welcome;
import com.example.tutor_app.R;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private ImageView tutor_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tutor_logo = findViewById(R.id.tutor_logo);

        Animation myAnimation_icon = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myAnimation_icon.setDuration(5000);
        tutor_logo.startAnimation(myAnimation_icon);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Dashboard_Drawer.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
