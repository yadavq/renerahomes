package com.example.harshyadav.renerahomeslandlord;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class SecondPage extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    Button btn_landlord, btn_tenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        btn_landlord = findViewById(R.id.button_landlord);
        btn_tenant = findViewById(R.id.button_tenant);

        lottieAnimationView = findViewById(R.id.second_animation);
        startCheckAnimation();

        btn_landlord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneRegistration.class);
                startActivity(intent);
            }
        });

        btn_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondPage.this, "Tenant Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCheckAnimation() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(8000);
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
