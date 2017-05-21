package com.eriknakamura.skinsense;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Testing {
    static OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    static String post() throws IOException {
        InputStream in = new FileInputStream(new File("C:\\Users\\temp\\Downloads\\Blank_Map_Boston.png"));
        byte[] buf;
        buf = new byte[in.available()];
        while (in.read(buf) != -1);
        RequestBody formBody = RequestBody
                .create(MediaType.parse("application/octet-stream"), buf);

        Request request = new Request.Builder()
                .addHeader("Content-Type",  "application/octet-stream")
                .addHeader("Prediction-Key", "c6b9366855dc483c96321e1d7525348f")
                .url("https://southcentralus.api.cognitive.microsoft.com/customvision/v1.0/Prediction/753a5e39-044d-424d-b876-4cda8f679571/image?iterationId=2fee4e9a-0c3d-41f7-b43b-3eb6d3c1d011")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args){
        try {
            System.out.println(post());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
