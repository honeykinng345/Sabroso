package com.sabroso.qms;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sabroso.qms.Models.Api;
import com.sabroso.qms.Models.Asset_Detail;
import com.sabroso.qms.Models.CheckAsset;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenCameraActivity extends AppCompatActivity {
    ImageView ivPlaceHolder;
    Button takeImage, uploadImage;

    String lat, loung, address,cid,assetid;

    String storeImagestring;
    private Bitmap bitmap;
String uname;
ProgressDialog progressDialog;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camera);
        lat = getIntent().getStringExtra("lat");
        loung = getIntent().getStringExtra("loung");
        address = getIntent().getStringExtra("address");
        cid = getIntent().getStringExtra("cid");
        assetid = getIntent().getStringExtra("assetid");
        uname = Helper.fetchUserId(OpenCameraActivity.this);

        Helper.SHowToast(OpenCameraActivity.this,uname);
        ivPlaceHolder = findViewById(R.id.ivPlaceHolder);
        takeImage = findViewById(R.id.takeImage);
        uploadImage = findViewById(R.id.uploadImage);

        progressDialog = new ProgressDialog(OpenCameraActivity.this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setCanceledOnTouchOutside(false);

/*
        Helper.SHowToast(OpenCameraActivity.this,"lat"+lat+"long"+loung+"add"+address);
        Helper.SHowToast(OpenCameraActivity.this,"cid"+cid+"assest"+assetid);*/
        takeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.openCamera(OpenCameraActivity.this, Helper.IMAGE_REQUEST_CODE);
            }
        });
        uploadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(storeImagestring)){
                    Helper.SHowToast(OpenCameraActivity.this,"Please Select Image First");
                    return;
                }else{
                    uploaddata();

                }


            }
        });


    }

    private void uploaddata() {

        progressDialog.setMessage("Data is uploading");
        progressDialog.show();

        Api api = ApiClient2.getClient2().create(Api.class);

        // Api api = ApiClient.getClient2().create(Api.class);
        Call<Asset_Detail> assetCall = api.AddVisit(uname,assetid,cid,lat,loung,address,storeImagestring);
        assetCall.enqueue(new Callback<Asset_Detail>() {
            @Override
            public void onResponse(Call<Asset_Detail> call, Response<Asset_Detail> response) {
progressDialog.dismiss();
                Helper.SHowToast(OpenCameraActivity.this,""+response.body().getStatus());
if (response.body().getStatus() ==1){

    startActivity(new Intent(OpenCameraActivity.this,MainActivity.class));
    finish();

}
            }

            @Override
            public void onFailure(Call<Asset_Detail> call, Throwable t) {
                progressDialog.dismiss();
                Helper.SHowToast(OpenCameraActivity.this,""+t.getMessage());

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Helper.IMAGE_REQUEST_CODE:
                    try {
                        //Uri fiePath = data.getData();
                        //picture.setImageURI(fiePath);
                        bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                        // SiliCompressor.with(getContext()).getCompressBitmap(imageUriString);
                        ivPlaceHolder.setImageBitmap(bitmap);
                        ivPlaceHolder.setVisibility(View.VISIBLE);
                        Bitmap imageResize = ImageResizer.reduceBitmapSize(bitmap, 200000);
                        storeImagestring = Helper.getStringImage(imageResize);



                        //  teacher.setPic(Helper.getStringImage(imageResize));
                        // teacher.setPic("oo");
                    } catch (Exception e) {
                        //  Helper.ShowSnackBar(snackbarAction, e.getMessage());
                    }
                    break;


            }

        }

    }
}