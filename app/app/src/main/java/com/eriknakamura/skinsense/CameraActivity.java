package com.eriknakamura.skinsense;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView imageView;
    Button submitInfo;
    Button takePicture;
    Button test;
    CheckBox itchiness;
    CheckBox burning;
    CheckBox pain;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);

        itchiness = (CheckBox) findViewById(R.id.itchiness);
        burning = (CheckBox) findViewById(R.id.burning);
        pain = (CheckBox) findViewById(R.id.pain);

        takePicture = (Button) findViewById(R.id.picture_button);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        submitInfo = (Button) findViewById(R.id.submit_button);
        submitInfo.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(CameraActivity.this, ResultsActivity.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("itchiness", itchiness.isChecked());
                intent.putExtra("stringPath", currentPhotoPath);
                startActivity(intent);
            }
        });

        test = (Button) findViewById(R.id.button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setThumbnail(currentPhotoPath);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.eriknakamura.skinsense",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws Exception {
        String timeStamp = DateFormat.getDateTimeInstance().format(new Date()).replaceAll(" ", "").replaceAll(":", "").replaceAll(",", "");
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );


        currentPhotoPath = image.getAbsolutePath();
        System.out.println(currentPhotoPath);
        return image;
    }

    private void setThumbnail(String path){
        Bitmap bmImg = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bmImg);
    }
}
