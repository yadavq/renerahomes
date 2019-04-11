package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Price extends AppCompatActivity {

    private EditText minimum, maximum;
    private ImageButton imageButton;
    TextView textView;
    private String price_min, price_max;
    private String rooms, name, phone, password, location, address, preferance, type, rule_add, rule_time, rule_smoking, rule_friends, specifications;
    private static final String REGISTER_URL="http://www.renerahomes.com/uploads/landlord_registration.php";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        minimum = findViewById(R.id.minimum_price);
        maximum = findViewById(R.id.maximum_price);
        textView = findViewById(R.id.expectedPrice);
        progressBar = findViewById(R.id.progressBarRegiter);


        imageButton = findViewById(R.id.imageButton);

        phone = getIntent().getStringExtra("phone");
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        address = getIntent().getStringExtra("address");
        location = getIntent().getStringExtra("location");
        preferance = getIntent().getStringExtra("preferance");
        type = getIntent().getStringExtra("type");
        rooms = getIntent().getStringExtra("rooms");
        rule_time = getIntent().getStringExtra("rule_time");
        rule_friends = getIntent().getStringExtra("rule_friends");
        rule_smoking = getIntent().getStringExtra("rule_smoking");
        rule_add = getIntent().getStringExtra("rule_add");



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.GONE);

                registerLandlord();

            }
        });

    }

    private void registerLandlord() {
        price_min = minimum.getText().toString();
        price_max = maximum.getText().toString();
        if (price_min.length() == 0){
            minimum.setError("Enter");
        }
        else if (price_max.length() == 0){
            maximum.setError("Enter");
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                        imageButton.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                        intent.putExtra("phone",phone);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Price.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone",phone);
                params.put("name",name);
                params.put("password",password);
                params.put("location",location);
                params.put("address",address);
                params.put("preferance",preferance);
                params.put("type",type);
                params.put("rooms",rooms);
                params.put("ruleone",rule_time);
                params.put("ruletwo",rule_friends);
                params.put("rulethree",rule_smoking);
                params.put("rulefour",rule_add);
                params.put("maxprice",price_max);
                params.put("minprice",price_min);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

}
