package com.example.harshyadav.renerahomeslandlord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Check extends AppCompatActivity {


    TextView final_name, final_phone, final_password, final_address, final_location, final_preferance, final_type, rule1, rule2, rule3, rule4, rooms, specify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


        final_type = findViewById(R.id.final_type);
        final_name = findViewById(R.id.name);
        final_phone = findViewById(R.id.phone);
        final_password = findViewById(R.id.password);
        final_location = findViewById(R.id.final_location);
        final_address = findViewById(R.id.address);
        final_preferance = findViewById(R.id.preferance);
        rooms = findViewById(R.id.rooms);
        rule1 = findViewById(R.id.rule1);
        rule2 = findViewById(R.id.rule2);
        rule3 = findViewById(R.id.rule3);
        rule4 = findViewById(R.id.rule4);
        specify = findViewById(R.id.specify);

        final String phone = getIntent().getStringExtra("phone");
        final String name = getIntent().getStringExtra("name");
        final String password = getIntent().getStringExtra("password");
        final String address = getIntent().getStringExtra("address");
        final String location = getIntent().getStringExtra("location");
        final String preferance = getIntent().getStringExtra("preferance");
        final String type = getIntent().getStringExtra("type");
        final String rooms_number = getIntent().getStringExtra("rooms");
        final String rule_1 = getIntent().getStringExtra("rule_time");
        final String rule_2 = getIntent().getStringExtra("rule_friends");
        final String rule_3 = getIntent().getStringExtra("rule_smoking");
        final String rule_4 = getIntent().getStringExtra("rule_add");
        final String specification = getIntent().getStringExtra("specify");


        final_name.setText(name);
        final_phone.setText(phone);
        final_password.setText(password);
        final_location.setText(location);
        final_address.setText(address);
        final_preferance.setText(preferance);
        final_type.setText(type);
        rooms.setText(rooms_number);
        rule1.setText(rule_1);
        rule2.setText(rule_2);
        rule3.setText(rule_3);
        rule4.setText(rule_4);
        specify.setText(specification);
    }
}
