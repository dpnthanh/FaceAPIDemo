package com.thanh.android.faceapidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.graphics.*;
import android.widget.*;
import android.provider.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;
    ImageView imgPhoto;
    Button btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initDisplay();
        initEvents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null){
            //get link image in device
            Uri uri = data.getData();
            try {
                //get bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //set image and show in screen
                imgPhoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initEvents() {
        btnRun.setOnClickListener(this);
    }

    private void initDisplay() {

    }

    private void initControls() {
        //mapped
        imgPhoto = (ImageView) findViewById(R.id.imageView_photo);
        btnRun = (Button) findViewById(R.id.button_run);

        detectionProgressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_run:
                //select a picture in gallery
                GetImageInGallery();
                break;
        }
    }

    private void GetImageInGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
        intentGallery.setType("image/*");
        startActivityForResult(Intent.createChooser(intentGallery,
                "Select Picture"),
                PICK_IMAGE);
    }
}
