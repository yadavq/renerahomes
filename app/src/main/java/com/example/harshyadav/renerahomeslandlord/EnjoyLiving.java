package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EnjoyLiving extends AppCompatActivity {

    private String  landlord_phone, tenant_phone, ekyc = "", confirmLandlord = "", confirmTenant = "" ;
    Button btn, btn_ekyc;
    private final static String QUIT_URL="http://www.renerahomes.com/quit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoy_living);

        btn = findViewById(R.id.button_quit);
        btn_ekyc = findViewById(R.id.button_ekyc);

        landlord_phone = getIntent().getStringExtra("landlordphone");
        tenant_phone = getIntent().getStringExtra("tenantphone");

        btn_ekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Confirm.class);
                intent.putExtra("landlordphone",landlord_phone);
                intent.putExtra("tenantphone", tenant_phone);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitLandlord();
            }
        });


    }

    private void quitLandlord() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, QUIT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                        intent.putExtra("phone",landlord_phone);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EnjoyLiving.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone",landlord_phone);
                params.put("ekyc",ekyc);
                params.put("confirmlandlord",confirmLandlord);
                params.put("confirmtenant",confirmTenant);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}
