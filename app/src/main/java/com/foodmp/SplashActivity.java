package com.foodmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodmp.Fragment.HotelFragment;
import com.foodmp.Fragment.Individual_Organization_Activity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        ImageView imglogo=findViewById(R.id.applogo);
        TextView slogan = findViewById(R.id.anim_3);

        imglogo.setAnimation(topAnim);
    
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPref shrdpreference =new SharedPref(getApplicationContext());
                String  type=shrdpreference.getType();
                System.out.println(type);
                if(shrdpreference.getType().equals("o")) {
                    startActivity(new Intent(SplashActivity.this, Individual_Organization_Activity.class));

                }else if(shrdpreference.getType().equals("u")) {
                   startActivity(new Intent(SplashActivity.this, User_View_Activity.class));

                }else  if(shrdpreference.getType().equals("h")){

                        Fragment fragment = new HotelFragment();
                        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

                }else
                startActivity(new Intent(SplashActivity.this, Welcome.class));

            }
        }, SPLASH_SCREEN);

    }
}