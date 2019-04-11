package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Confirm extends AppCompatActivity {

    RadioGroup ekyc;
    RadioButton ekyc_btn;
    private String ekyc_final, landlord_phone, tenant_phone, confirmLandlord = "yes";
    TextView confirm;
    ImageButton imageButton;
    private final static String CONFIRM_URL="http://www.renerahomes.com/confirm.php";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        ekyc = findViewById(R.id.ekyc);
        confirm = findViewById(R.id.confirm);
        imageButton =findViewById(R.id.imageButton);
        landlord_phone = getIntent().getStringExtra("landlordphone");
        tenant_phone = getIntent().getStringExtra("tenantphone");
        confirm.setText("Hey, "+ landlord_phone +" Your new tenant is " + tenant_phone + ". Hope you both enjoy your stay !");

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLandlord();
            }
        });

    }

    public void ekyc_confirm_Landlord (View v){
        int ekyc_confirm = ekyc.getCheckedRadioButtonId();
        ekyc_btn = findViewById(ekyc_confirm);
        ekyc_final = ekyc_btn.getText().toString();
        Toast.makeText(this, ekyc_btn.getText(), Toast.LENGTH_SHORT).show();
    }

    private void confirmLandlord() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CONFIRM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), EnjoyLiving.class);
                    intent.putExtra("landlordphone",landlord_phone);
                    intent.putExtra("tenantphone", tenant_phone);
                    startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Confirm.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone",landlord_phone);
                params.put("ekyc",ekyc_final);
                params.put("confirmlandlord",confirmLandlord);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

}


