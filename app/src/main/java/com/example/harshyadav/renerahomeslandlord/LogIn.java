package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    private EditText phone_number, password;
    ImageButton btn_login;
    private String login_number, login_password;
    TextView register;
    private String profile_phone, profile_password;
    private final static String URL_LOGIN_PROFILE = "http://www.renerahomes.com/landlord_login.php";
    private int flag = 0;
    SharedPreferences sharedPreferences;
    private Boolean notLoggedIn;
    private String sharedPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        notLoggedIn = sharedPreferences.getBoolean("notLoggedIn", true);
        sharedPhone = sharedPreferences.getString("sharedPhone", "");


        if (notLoggedIn)
        {
        phone_number = findViewById(R.id.login_phone);
        password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.button_login);
        register = findViewById(R.id.register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_number = phone_number.getText().toString();
                login_password = password.getText().toString();
                if (login_number.length() == 0) {
                    password.setError("Enter");
                } else if (login_password.length() == 0) {
                    password.setError("Enter");
                } else if (login_password.length() != 8) {
                    password.setError("8 Digits Password");
                }

                loadCredentials();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhoneRegistration.class);
                startActivity(intent);
            }
        });


    }

    else{
            Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
            intent.putExtra("phone",sharedPhone);
            startActivity(intent);
        }

}



    private void loadCredentials() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LOGIN_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for (int i=0; i<products.length(); i++){
                                JSONObject productObject = products.getJSONObject(i);
                                profile_phone = productObject.getString("phone");

                                if(profile_phone.equals(login_number)) {
                                    flag=1;
                                    profile_password = productObject.getString("password");
                                     if(login_password.equals(profile_password)){
                                         Toast.makeText(LogIn.this, "LogIn Complete", Toast.LENGTH_SHORT).show();
                                         SharedPreferences.Editor editor = sharedPreferences.edit();
                                         notLoggedIn = false;
                                         editor.putBoolean("notLoggedIn", notLoggedIn);
                                         editor.putString("sharedPhone",profile_phone);
                                         editor.apply();
                                        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                                        intent.putExtra("phone",profile_phone);
                                        startActivity(intent);
                                    }
                                    else{
                                         Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                     }

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogIn.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
