package com.example.harshyadav.renerahomeslandlord;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilePage extends AppCompatActivity {

    Button  btn_update;
    ImageView profilephoto;
    TextView doorin, doorout, bed, bedding, table, chair, wardrobe, bathroom, other;
    private String phone, profile_phone_number, profile_full_name, profile_full_location, profile_confirm_tenant, photo_doorin, photo_doorout, photo_bed, photo_bedding, photo_table, photo_chair, photo_wardrobe, photo_bathroom, photo_other;
    private int profile_interested ;
    private TextView profile_name, profile_location, profile_phone, price;
    private final static String URL_PROFILE="http://www.renerahomes.com/landlord_information.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_page);
        btn_update = findViewById(R.id.btn_update);
        profilephoto = findViewById(R.id.profile_photo);
        profile_name = findViewById(R.id.profile_name);
        profile_location = findViewById(R.id.profile_location);
        profile_phone = findViewById(R.id.profile_phone);
        price = findViewById(R.id.price);
        doorin = findViewById(R.id.photo_doorin);
        doorout = findViewById(R.id.photo_doorout);
        table = findViewById(R.id.photo_table);
        chair = findViewById(R.id.photo_chair);
        bed = findViewById(R.id.photo_bed);
        bedding = findViewById(R.id.photo_bedding);
        wardrobe = findViewById(R.id.photo_wardrobe);
        bathroom = findViewById(R.id.photo_bathroom);
        other = findViewById(R.id.photo_other);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        profilephoto.setImageDrawable(roundedBitmapDrawable);


        phone = getIntent().getStringExtra("phone");

        loadProducts();


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateIntent = new Intent(getApplicationContext(), UploadDoorIn.class);
                updateIntent.putExtra("phone",phone);
                startActivity(updateIntent);
            }
        });
    }

    private void loadProducts() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for (int i=0; i<products.length(); i++){
                                JSONObject productObject = products.getJSONObject(i);
                                profile_phone_number = productObject.getString("phone");

                                if(profile_phone_number.equals(phone)) {
                                    profile_confirm_tenant = productObject.getString("confirmtenant");
                                    profile_full_name = productObject.getString("name");
                                    profile_full_location = productObject.getString("location");
                                    profile_interested = productObject.getInt("interested");

                                    profile_name.setText("Hello, "+ profile_full_name);
                                    profile_location.setText("Your Location is at " + profile_full_location);
                                    profile_phone.setText("People will call you at " + profile_phone_number);
                                    price.setText(profile_interested + " People are interested right now in your room.");

                                    photo_doorin = productObject.getString("doorin");
                                    photo_doorout = productObject.getString("doorout");
                                    photo_table = productObject.getString("tables");
                                    photo_chair = productObject.getString("chair");
                                    photo_bed = productObject.getString("bed");
                                    photo_bedding = productObject.getString("bedding");
                                    photo_wardrobe = productObject.getString("wardrobe");
                                    photo_bathroom = productObject.getString("bathroom");
                                    photo_other = productObject.getString("other");

                                    if (photo_doorin.length() > 2){


                                        doorin.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_doorout.length() > 2){


                                        doorout.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_bed.length() > 2){


                                        bed.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_bedding.length() > 2){


                                        bedding.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_table.length() > 2){


                                        table.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_chair.length() > 2){


                                        chair.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_wardrobe.length() > 2){


                                        wardrobe.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_bathroom.length() > 2){


                                        bathroom.setVisibility(View.VISIBLE);
                                    }

                                    if (photo_other.length() > 2){


                                        other.setVisibility(View.VISIBLE);
                                    }


                                    if(profile_confirm_tenant.length() > 0){
                                        Intent confirmIntent = new Intent(getApplicationContext(), EnjoyLiving.class);
                                        confirmIntent.putExtra("landlordphone", profile_phone_number);
                                        confirmIntent.putExtra("tenantphone", profile_confirm_tenant);
                                        startActivity(confirmIntent);
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
                        Toast.makeText(ProfilePage.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
