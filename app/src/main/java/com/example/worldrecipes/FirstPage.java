package com.example.worldrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class FirstPage extends AppCompatActivity {
ImageView logo, splashanim,appname,text;
LottieAnimationView lottieAnimationView;
    private static int SPLASH_SCREEN_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        logo= findViewById(R.id.log);
        appname= findViewById(R.id.appname);
        splashanim=findViewById(R.id.b);
        lottieAnimationView= findViewById(R.id.lottie);
        text = findViewById(R.id.text);

        splashanim.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        appname.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        text.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);

             new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(getApplicationContext(), ChoixLangue.class);
                    startActivity(intent);
                    finish();

                }
            }, SPLASH_SCREEN_TIMEOUT);


    }

}