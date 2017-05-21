package com.eriknakamura.skinsense;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView imageView;
    Button submitInfo;
    Button takePicture;
    Button jankButton;
    static String currentPhotoPath;
    OkHttpClient client = new OkHttpClient();

    String urlkey1 = "https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/753a5e39-044d-424d-b876-4cda8f679571/image?iterationId=10bdf036-2a78-4fc3-8fcb-cd78022fa198";
    String urlkey2 = "https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/6816f56b-c6c8-4286-adb8-4bf3b704a86d/image?iterationId=22701ec9-7cad-4df8-a468-5b75db005829";
    String urlkey3 = "https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/a3a9dd7c-4178-481c-ba66-32a14924f67c/image?iterationId=1956ee3a-19db-49d6-a6ee-76d7860d11f4";
    String urlkey4 = "https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/abb2f1cc-9c3f-4b4c-8239-642b5467e9fc/image?iterationId=ec73ccb4-ec75-4645-8aee-698fb23c5c78";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);

        jankButton = (Button) findViewById(R.id.jank_button);
        jankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setThumbnail(currentPhotoPath);
                try {
                    post(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String responseStr = response.body().string();
                                System.out.println(responseStr);
                            } else {
                            }
                        }
                    }, urlkey1);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    post(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Dude this sucks");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body());
                            System.out.println(response.headers());
                            System.out.println(response.message());
                            System.out.println(response.toString());
                            if (response.isSuccessful()) {
                                System.out.println("hellhello");
                                String responseStr = response.body().string();
                                System.out.println(responseStr);
                            } else {
                                System.out.println("we failed");
                            }
                        }
                    }, urlkey2);

                    try {
                        post(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    String responseStr = response.body().string();
                                    System.out.println(responseStr);
                                } else {
                                }
                            }
                        }, urlkey3);

                        try {
                            post(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        String responseStr = response.body().string();
                                        System.out.println(responseStr);
                                    } else {
                                    }
                                }
                            }, urlkey4);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        takePicture = (Button) findViewById(R.id.picture_button);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        submitInfo = (Button) findViewById(R.id.submit_button);
        submitInfo.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(CameraActivity.this, RashSymptom.class);


            @Override
            public void onClick(View v) {
                intent.putExtra("stringPath", currentPhotoPath);
                startActivity(intent);
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
        return image;
    }

    private void setThumbnail(String path){
        Bitmap bmImg = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bmImg);
    }


    /*Call post(Callback callback) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmap = BitmapFactory.decodeFile(currentPhotoPath);
        bmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        RequestBody formBody = RequestBody
                .create(MediaType.parse("application/octet-stream"), byteArray);

        Request request = new Request.Builder()
                .addHeader("Prediction-Key", "c6b9366855dc483c96321e1d7525348f")
                .addHeader("Content-Type",  "application/octet-stream")
                .url("https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/753a5e39-044d-424d-b876-4cda8f679571/image?iterationId=2fee4e9a-0c3d-41f7-b43b-3eb6d3c1d011")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }*/

    Call post(Callback callback, String url) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmap = BitmapFactory.decodeFile(currentPhotoPath);
        bmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        RequestBody formBody = RequestBody
                .create(MediaType.parse("application/octet-stream"), byteArray);

        Request request = new Request.Builder()
                .addHeader("Prediction-Key", "c6b9366855dc483c96321e1d7525348f")
                .addHeader("Content-Type",  "application/octet-stream")
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }
}
