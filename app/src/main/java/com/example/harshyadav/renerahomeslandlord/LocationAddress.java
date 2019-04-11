package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
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

public class LocationAddress extends AppCompatActivity {

    private String clickedLocation, phone, name, password, landlord_address;

    private ArrayList<LocationItem> mlocationlist;
    private LocationAdapter mAdapter;
    ImageButton btn_location;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_address);

        btn_location = findViewById(R.id.btn_location);
        address = findViewById(R.id.address);

        initList();

        Spinner spinnerLocation =  findViewById(R.id.spinner_location);
        mAdapter = new LocationAdapter(this, mlocationlist);
        spinnerLocation.setAdapter(mAdapter);

        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");


        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocationItem clickedItem = (LocationItem)parent.getItemAtPosition(position);
                clickedLocation = clickedItem.getMlocationname().toString();
                Toast.makeText(LocationAddress.this, "Location Selected: "+clickedLocation, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landlord_address = address.getText().toString();
                if(landlord_address.length() == 0){
                    address.setError("Enter");
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Preferance.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("password", password);
                    intent.putExtra("location", clickedLocation);
                    intent.putExtra("address", landlord_address);
                    startActivity(intent);
                }
            }
        });
    }
    private void initList() {
        mlocationlist = new ArrayList<>();
        mlocationlist.add(new LocationItem("Sales Tax Office", R.drawable.kanpur));
        mlocationlist.add(new LocationItem("Udyan Vihar", R.drawable.kanpur));
        mlocationlist.add(new LocationItem("BarsaitPur", R.drawable.kanpur));
    }
}

