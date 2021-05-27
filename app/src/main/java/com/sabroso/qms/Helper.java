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
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.airbnb.lottie.LottieAnimationView;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sabroso.qms.Models.Asset_Detail;

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
        return false;

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

    public static String getStringImage(Bitmap bm) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 65, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    public static void logOut(Context context) {

        SessionManager sessionManager = new SessionManager(context);
        sessionManager.setLogin(false);
        SQLiteHandler sqLiteHandler;
        sqLiteHandler = new SQLiteHandler(context);

        sqLiteHandler.removeAll();


        if (context instanceof Activity) {
            ((Activity) context).startActivity(new Intent(context, LoginActivity.class));
            //fragment.startActivityForResult();
            ((Activity) context).finish();
        }

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public static double milesToMeters(double miles) {
        return miles / 0.0006213711;
    }

     public  static  void OpenLottiDialoug(Context context,CodeScannerView codeScannerView){
         LottieAnimationView anim ,anim1;
         TextView titleTv,tv1;
         View view = LayoutInflater.from(context).inflate(R.layout.row_thanks,null);
         ImageButton imageButton = view.findViewById(R.id.backBtn);
codeScannerView.setVisibility(View.GONE);
         titleTv = view.findViewById(R.id.thanksTv);
         tv1 = view.findViewById(R.id.t);
         anim = view.findViewById(R.id.cheat);
         anim1 = view.findViewById(R.id.checkAnimation);
         AlertDialog.Builder builder = new AlertDialog.Builder(context);
         builder.setView(view);
         final AlertDialog dialog = builder.create();
         titleTv.setVisibility(View.VISIBLE);
         anim.setVisibility(View.VISIBLE);
         anim1.setVisibility(View.GONE);
         tv1.setVisibility(View.GONE);

         titleTv.setText("You are try to cheat  visit your own  Customer");

         dialog.show();
         imageButton.setOnClickListener(v -> dialog.dismiss());
     }
    public static void showDialoug(Asset_Detail response, Context context, CodeScannerView codeScannerView, CodeScanner codeScanner) {

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Fetch Asset Detail..");
        progressDialog.show();

        TextView idTv, Asset_Title, Asset_Pvalue, Asset_Cvalue, Department_Flag, Customer_Id, Department, Location_Details;
        View view = LayoutInflater.from(context).inflate(R.layout.dialoug_box_show_assest_details, null);
        ImageButton imageButton = view.findViewById(R.id.backBtn);
        idTv = view.findViewById(R.id.idTv);
        Asset_Title = view.findViewById(R.id.Asset_Title);
        Asset_Pvalue = view.findViewById(R.id.Asset_Pvalue);
        Asset_Cvalue = view.findViewById(R.id.Asset_Cvalue);
        Department_Flag = view.findViewById(R.id.Department_Flag);
        Customer_Id = view.findViewById(R.id.Customer_Id);
        Department = view.findViewById(R.id.Department);
        Location_Details = view.findViewById(R.id.Location_Details);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        idTv.setText(response.getAssetDetail().getAssetId());
        Asset_Title.setText(response.getAssetDetail().getAssetTitle());
        Asset_Pvalue.setText(String.valueOf(response.getAssetDetail().getAssetPvalue()));
        Asset_Cvalue.setText(String.valueOf(response.getAssetDetail().getAssetCvalue()));
        Department_Flag.setText("" + response.getAssetDetail().getDepartmentFlag());
        Customer_Id.setText("" + response.getAssetDetail().getCustomerId());
        Department.setText("" + response.getAssetDetail().getDepartment());
        Location_Details.setText("" + response.getAssetDetail().getLocationDetails());
        progressDialog.dismiss();
        codeScannerView.setVisibility(View.GONE);
        codeScanner.releaseResources();
      progressDialog.dismiss();
        dialog.show();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
