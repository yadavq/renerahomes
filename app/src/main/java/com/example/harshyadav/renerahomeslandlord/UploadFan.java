package com.example.harshyadav.renerahomeslandlord;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class UploadFan extends AppCompatActivity {

    ImageView upload_fan;
    ImageButton btn_fan;
    TextView skip,number;
    Bitmap photo;
    public static final String URL = "http://www.renerahomes.com/saveImage.php";
    int Img_Request = 12;
    String phone;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_fan);

        phone = getIntent().getStringExtra("phone");

        upload_fan = findViewById(R.id.fan_image);
        btn_fan = findViewById(R.id.fan_continue);
        progressBar = findViewById(R.id.progressBarFan);

        upload_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , Img_Request);
            }
        });

        skip = findViewById(R.id.fan_skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UploadBed.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });

        btn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btn_fan.setVisibility(View.GONE);

                Bitmap image = ((BitmapDrawable) upload_fan.getDrawable()).getBitmap();
                new UploadFan.UploadImage(image,"fan", phone).execute();
            }
        });
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Img_Request && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                photo = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                upload_fan.setImageBitmap(photo);
            } catch (IOException e) {
                Toast.makeText(UploadFan.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }

    private  class UploadImage extends AsyncTask<Void, Void, Void> {

        Bitmap bitmap;
        String id, phone;
        public UploadImage(Bitmap bitmap, String id, String phone) {
            this.bitmap = bitmap;
            this.id = id;
            this.phone = phone;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
            btn_fan.setVisibility(View.GONE);

            Toast.makeText(UploadFan.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image",encodedImage));
            dataToSend.add(new BasicNameValuePair("id",id));
            dataToSend.add(new BasicNameValuePair("phone",phone));

            HttpParams httpConnectionParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpConnectionParams);
            HttpPost post = new HttpPost(URL);


            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private HttpParams getHttpRequestParams (){
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,100*30);
        HttpConnectionParams.setSoTimeout(httpParams,100*30);
        return httpParams;

    }
}
