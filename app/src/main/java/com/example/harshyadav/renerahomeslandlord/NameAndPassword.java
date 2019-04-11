package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class NameAndPassword extends AppCompatActivity {

    WebView wbvw;
    ImageButton btn_name_and_password;
    EditText name, password;
    private String landlord_password, landlord_name, phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_and_password);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        btn_name_and_password = findViewById(R.id.btn_name_and_password);

        wbvw = (WebView)findViewById(R.id.manandpassword);
        wbvw.loadUrl("file:///android_asset/man_and_password.html");

        password.setError("8 Digits");

        phoneNumber = getIntent().getStringExtra("phone");


        btn_name_and_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                landlord_name = name.getText().toString();
                landlord_password = password.getText().toString();

                if ((landlord_name.length() > 0) && (landlord_password.length() == 8)) {
                    Intent intent = new Intent(getApplicationContext(), LocationAddress.class);
                    intent.putExtra("name",landlord_name);
                    intent.putExtra("password",landlord_password);
                    intent.putExtra("phone",phoneNumber);
                    startActivity(intent);
                }
                else if(landlord_name.length() == 0 ){
                    name.setError("Enter");
                }
                else if(landlord_password.length() == 0){
                    password.setError("Enter");
                }
                else if(landlord_password.length() < 8){
                    password.setError("8 digits");
                }
            }
        });



    }
}
