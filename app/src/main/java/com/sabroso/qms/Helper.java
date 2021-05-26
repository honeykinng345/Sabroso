package com.sabroso.qms;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Helper {
    public static final int IMAGE_REQUEST_CODE = 100;
    public static void openCamera(Context fragment, int requestCode) {
        Dexter.withContext(fragment).withPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (fragment instanceof Activity) {
                        ((Activity) fragment).startActivityForResult(intent, requestCode);
                        //fragment.startActivityForResult();
                    }
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        }).withErrorListener(dexterError -> Toast.makeText(fragment, dexterError.toString(), Toast.LENGTH_SHORT).show()).check();
    }
    public static boolean validation(String variableName, EditText editText, Context context) {

        Vibrator vibrator;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (TextUtils.isEmpty(variableName)) {
            // Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            editText.setError("All fields are required");
            editText.requestFocus();

            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
            editText.startAnimation(shake);
            return true;

        }
return  false;

    }


    public static void ShowDialoug(Context context, String Message) {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(Message);
        progressDialog.show();

    }

    public static void hideDialoug(Context context) {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.dismiss();
    }


    public static void transectionActivityToActivity(Context context, Class context1) {
        Intent intent = new Intent(context, context1);
        context.startActivity(intent);

    }


    public static void SHowToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String fetchUserId(Context context) {
        SQLiteHandler sqLiteHandler;
        sqLiteHandler = new SQLiteHandler(context);
        Cursor res = sqLiteHandler.getAllData();
        String id = null;
        while (res.moveToNext()) {
            id = res.getString(1);
        }
        return id;
    }
    public static String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 65, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    public  static void logOut(Context context){

        SessionManager sessionManager = new SessionManager(context);
        sessionManager.setLogin(false);
        SQLiteHandler sqLiteHandler;
        sqLiteHandler = new SQLiteHandler(context);

        sqLiteHandler.removeAll();


        if (context instanceof Activity) {
            ((Activity) context).startActivity(new Intent(context,LoginActivity.class));
            //fragment.startActivityForResult();
            ((Activity) context).finish();
        }

    }

}
