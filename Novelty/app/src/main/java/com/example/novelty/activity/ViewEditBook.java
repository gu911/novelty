package com.example.novelty.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.novelty.R;

import java.io.IOException;

public class ViewEditBook extends AppCompatActivity {

    public static final int UPLOAD_PHOTO = 100;

    private Button uploadPhotoButton;
    private Button deletePhotoButton;
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_book);

        uploadPhotoButton = findViewById(R.id.btn_upload);
        deletePhotoButton = findViewById(R.id.btn_deletePhoto);
        photoView = findViewById(R.id.photoView);

        photoView.setBackgroundColor(Color.LTGRAY);

        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(ViewEditBook.this, UploadPhoto.class);
                startActivityForResult(uploadIntent, UPLOAD_PHOTO);
            }
        });

        deletePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoView.setImageResource(0);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case UPLOAD_PHOTO:
                if (resultCode == 2) {
                    Uri imageUri = Uri.parse(data.getStringExtra("returnPhoto"));
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        photoView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }

}

