package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Preferance extends AppCompatActivity {

    ImageView student, family, selected_student, selected_family;
    ImageButton btn;
    private String variable, name, password, phone, location, address;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferance);

        heading = findViewById(R.id.heading);
        student = (ImageView)findViewById(R.id.student);
        family = (ImageView)findViewById(R.id.family);
        selected_student = (ImageView)findViewById(R.id.selected_student);
        selected_family = (ImageView)findViewById(R.id.selected_family);
        btn = (ImageButton)findViewById(R.id.btn);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        location = getIntent().getStringExtra("location");
        address = getIntent().getStringExtra("address");


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setVisibility(View.GONE);
                family.setVisibility(View.GONE);
                heading.setVisibility(View.GONE);
                selected_student.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                variable = "student";
                Toast.makeText(Preferance.this, variable, Toast.LENGTH_SHORT).show();
            }
        });

        selected_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setVisibility(View.VISIBLE);
                family.setVisibility(View.VISIBLE);
                heading.setVisibility(View.VISIBLE);
                selected_family.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
            }
        });

        selected_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setVisibility(View.VISIBLE);
                family.setVisibility(View.VISIBLE);
                heading.setVisibility(View.VISIBLE);
                selected_student.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setVisibility(View.GONE);
                family.setVisibility(View.GONE);
                heading.setVisibility(View.GONE);
                selected_family.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                variable = "family";
                Toast.makeText(Preferance.this, variable, Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NumberOfRooms.class);
                intent.putExtra("name", name);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                intent.putExtra("location",location);
                intent.putExtra("address",address);
                intent.putExtra("preferance",variable);
                startActivity(intent);
            }
        });


    }
}
