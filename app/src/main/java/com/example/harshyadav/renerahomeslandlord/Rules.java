package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Rules extends AppCompatActivity {

    RadioGroup rule_friends,rule_smoking;
    RadioButton  radioButton_friends,radioButton_smoking;
    EditText addition_rule, rule1;
    ImageButton rulebutton;
    private String rooms, name, phone, password, location, address, preferance, type, add_rule,time_rule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rule_friends = findViewById(R.id.friends_rule);
        rule_smoking = findViewById(R.id.smoking_rule);
        addition_rule = findViewById(R.id.extra_rule);
        rule1 = findViewById(R.id.time);

        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        address = getIntent().getStringExtra("address");
        location = getIntent().getStringExtra("location");
        preferance = getIntent().getStringExtra("preferance");
        type = getIntent().getStringExtra("type");
        rooms = getIntent().getStringExtra("rooms");


        rulebutton = findViewById(R.id.rule_button);
        rulebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_rule = addition_rule.getText().toString();
                if (add_rule.length() == 0){
                    add_rule = "No Extra Rules, Enjoy A Stay with us.";
                }
                time_rule = rule1.getText().toString();
                if(time_rule.length() == 0){

                    rule1.setError("Enter");
                }
                Intent intent = new Intent(getApplicationContext(), Price.class);
                intent.putExtra("name", name);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                intent.putExtra("location",location);
                intent.putExtra("address",address);
                intent.putExtra("preferance",preferance);
                intent.putExtra("type",type);
                intent.putExtra("rooms",rooms);
                intent.putExtra("rule_time",time_rule);
                intent.putExtra("rule_friends",radioButton_friends.getText());
                intent.putExtra("rule_smoking",radioButton_smoking.getText());
                intent.putExtra("rule_add", add_rule);
                startActivity(intent);
            }
        });
    }



    public void clickButton_friends(View v){
        int radioId_friends = rule_friends.getCheckedRadioButtonId();
        radioButton_friends = findViewById(radioId_friends);

        Toast.makeText(this, radioButton_friends.getText(), Toast.LENGTH_SHORT).show();
    }

    public void clickButton_smoking(View v){
        int radioId_smoking = rule_smoking.getCheckedRadioButtonId();
        radioButton_smoking= findViewById(radioId_smoking);

        Toast.makeText(this, radioButton_smoking.getText(), Toast.LENGTH_SHORT).show();
    }
}
