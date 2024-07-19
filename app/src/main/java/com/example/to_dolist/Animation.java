package com.example.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Animation extends AppCompatActivity {
    LottieAnimationView lottie;
    TextView goBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);

        lottie=findViewById(R.id.lottie);
        goBtn=findViewById(R.id.goBtn);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                lottie.setAnimation(R.raw.second);
                lottie.playAnimation();
            }
        }, 3000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                lottie.setAnimation(R.raw.third);
                lottie.playAnimation();
            }
        }, 5000);

    goBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Animation.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    });



    }
}
