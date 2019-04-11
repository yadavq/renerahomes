package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.jar.Attributes;

public class PhoneRegistration extends AppCompatActivity {

    EditText phone;
    ImageButton btn_reigister;
    TextView btn_login;
    String phoneNumber;
    private static final String REGISTER_URL="http://www.renerahomes.com/landlord_mobile_check.php";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_registration);

        phone = findViewById(R.id.phonenumber);
        btn_reigister = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBarPhone);


        btn_reigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = phone.getText().toString();

                if (phoneNumber.length() == 10) {
                    progressBar.setVisibility(View.VISIBLE);
                    btn_reigister.setVisibility(View.GONE);
                    checkPhone();
                }
                else if((phoneNumber.length() > 0) && (phoneNumber.length() <10) ) {
                    phone.setError("10 digit Number");
                }
                else {
                    phone.setError("Enter");
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(loginIntent);
            }
        });
    }

    private void checkPhone() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();


                        if(response.equals("exists")){
                            phone.setError("RE-ENTER");
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            btn_reigister.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(getApplicationContext(), NameAndPassword.class);
                            intent.putExtra("phone",phoneNumber);
                            startActivity(intent);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PhoneRegistration.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone",phoneNumber);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }


}
