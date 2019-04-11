package com.example.harshyadav.renerahomeslandlord;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

public class MainPage extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageButton button_main;
    SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime",true);

        if (firstTime){

            button_main = findViewById(R.id.btn_main_page);
            lottieAnimationView = findViewById(R.id.first_animation);


            startCheckAnimation();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            firstTime = false;
            editor.putBoolean("firstTime", firstTime);
            editor.apply();
            button_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), SecondPage.class);
                    startActivity(intent);
                }
            });

        }

        else{
            Intent intent = new Intent(getApplicationContext(), LogIn.class);
            startActivity(intent);
        }

    }

    private void startCheckAnimation(){
        final ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(15000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lottieAnimationView.setProgress((Float)valueAnimator.getAnimatedValue());
            }
        });
        if (lottieAnimationView.getProgress() == 0f){
            animator.start();
        }
        else {
            lottieAnimationView.setProgress(0f);
        }
    }


}
