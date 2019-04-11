package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberOfRooms extends AppCompatActivity {

    ImageButton btn_rooms;
    EditText number;
    private String rooms, name, phone, password, location, address, preferance, clickedType;
    private ArrayList<LocationItem> mlocationlist;
    private LocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_rooms);

        btn_rooms = findViewById(R.id.btn_rooms);
        number = findViewById(R.id.number);


        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        address = getIntent().getStringExtra("address");
        location = getIntent().getStringExtra("location");
        preferance = getIntent().getStringExtra("preferance");


        initList();

        Spinner spinnerLocation =  findViewById(R.id.spinner_location);
        mAdapter = new LocationAdapter(this, mlocationlist);
        spinnerLocation.setAdapter(mAdapter);


        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem)parent.getItemAtPosition(position);
                clickedType = clickedItem.getMlocationname().toString();
                Toast.makeText(NumberOfRooms.this, "Location Selected: "+clickedType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rooms = number.getText().toString();
                if (rooms.length() != 0) {
                    Intent intent = new Intent(getApplicationContext(), Rules.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone",phone);
                    intent.putExtra("password",password);
                    intent.putExtra("location",location);
                    intent.putExtra("address",address);
                    intent.putExtra("preferance",preferance);
                    intent.putExtra("type",clickedType);
                    intent.putExtra("rooms",rooms);
                    startActivity(intent);
                }
                else {
                    number.setError("Enter");
                }
            }
        });
    }

    private void initList() {
        mlocationlist = new ArrayList<>();
        mlocationlist.add(new LocationItem("Flat", R.drawable.flat));
        mlocationlist.add(new LocationItem("P.G.", R.drawable.pg));
        mlocationlist.add(new LocationItem("Hostel", R.drawable.hostel));
    }
}
