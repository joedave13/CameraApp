package com.example.joeimageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    ImageView imageCamera;
    Button btnBackToCamera, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageCamera = findViewById(R.id.image_result);
        btnBackToCamera = findViewById(R.id.btn_back_camera);
        btnSave = findViewById(R.id.btn_save_image);

        final Bundle extras = getIntent().getExtras();
        if (extras != null){
            Bitmap img = (Bitmap)extras.get("image");
            if (imageCamera != null){
                imageCamera.setImageBitmap(img);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap savedImage = (Bitmap)extras.get("image");
                String savedImageUrl = MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        savedImage,
                        "example_image",
                        "example_image_camera"
                );
                Uri savedImageURI = Uri.parse(savedImageUrl);
                Toast.makeText(ResultActivity.this, "Image saved to gallery", Toast.LENGTH_LONG).show();
            }
        });

        btnBackToCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
