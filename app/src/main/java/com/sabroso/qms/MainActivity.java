 package com.sabroso.qms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sabroso.qms.Models.Api;
import com.sabroso.qms.Models.Asset_Detail;
import com.sabroso.qms.Models.CheckAsset;
import com.sabroso.qms.Models.UserDetail;
import com.sabroso.qms.Models.customers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    // initializing
    // FusedLocationProviderClient
    // object
    FusedLocationProviderClient mFusedLocationClient;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PERMISSION_ID = 44;

    SharedPreferences preferences;
    TextView userName;
    ImageView QrIv;

    ImageButton ic_logout;
    private CodeScanner mCodeScanner;
    CodeScannerView scannerView;


    ProgressDialog progressDialog;

    List<customers.CustomerListDTO> customerListDTOS;
    List<Address> addresses;
    Geocoder geocoder;
    String lat, loung, CompleteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this);

        customerListDTOS = new ArrayList<>();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setCanceledOnTouchOutside(false);
        preferences = MainActivity.this.getSharedPreferences("MyPref", 0);
        String s1 = preferences.getString("UserName", "");
        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);


        userName = findViewById(R.id.UserName);
        ic_logout = findViewById(R.id.ic_logout);
        QrIv = findViewById(R.id.QrIv);
        userName.setText(s1);
        if (checkPermission()) {
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

        } else {
            requestPermission();
        }
        getLastLocation();

        //  Helper.SHowToast(MainActivity.this,""+Helper.fetchUserId(MainActivity.this));

        ///  fetchCustomerList(Helper.fetchUserId(MainActivity.this));
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                /*Toast.makeText(MainActivity.this, ""+firstTwo(result.getText()), Toast.LENGTH_SHORT).show();*/

                scannerView.animate();
                checkQR(result.getText());

                Toast.makeText(MainActivity.this, "QRCode" + result.getText(), Toast.LENGTH_SHORT).show();


                //checkUserAssest(result.getText());




               /* String s = result.getText().toString();

                s.substring(0,2);

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();*/

            }
        }));

        QrIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   qrScan.initiateScan();
                scannerView.setVisibility(View.VISIBLE);


            }

        });
        ic_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.logOut(MainActivity.this);

            }
        });
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (checkPermissions1()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {

                            Helper.SHowToast(MainActivity.this, "" + location.getLongitude());
                            Helper.SHowToast(MainActivity.this, "" + location.getLatitude());

                            try {
                                lat = String.valueOf(location.getLatitude());
                                loung = String.valueOf(location.getLongitude());
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                                CompleteAddress = addresses.get(0).getAddressLine(0);

                                Helper.SHowToast(MainActivity.this, "" + addresses.get(0).getAddressLine(0));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            /*latitudeTextView.setText(location.getLatitude() + "");
                            longitTextView.setText(location.getLongitude() + "");*/
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions1();
        }


    }


    private void checkUserAssest(String QRScanId) {

        progressDialog.setMessage("Processing...");
        progressDialog.show();
        Api api = ApiClient2.getClient2().create(Api.class);

        // Api api = ApiClient.getClient2().create(Api.class);
        Call<CheckAsset> assetCall = api.CheckAsset(QRScanId);
        assetCall.enqueue(new Callback<CheckAsset>() {
            @Override
            public void onResponse(Call<CheckAsset> call, Response<CheckAsset> response) {
                Log.d("JSON  LIST", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                // Helper.SHowToast(MainActivity.this,""+response.body().getMessage());

                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();




                    if (Helper.fetchUserId(MainActivity.this).equals(response.body().getCustomerDetail().getUserId())) {
                    Helper.SHowToast(MainActivity.this,""+ Helper.distance(Double.parseDouble(lat)
                            ,Double.parseDouble(loung),Double.parseDouble(response.body().getCustomerDetail().getCustomerLat()),
                            Double.parseDouble(response.body().getCustomerDetail().getCustomerLong())));

                   double t = Helper.distance(Double.parseDouble(lat)
                            ,Double.parseDouble(loung),Double.parseDouble(response.body().getCustomerDetail().getCustomerLat()),
                            Double.parseDouble(response.body().getCustomerDetail().getCustomerLong()));
                    Helper.SHowToast(MainActivity.this,""+Helper.milesToMeters(t));

                    if (Helper.milesToMeters(t) <=30){
                        Intent intent = new Intent(MainActivity.this, OpenCameraActivity.class);
                        intent.putExtra("lat", lat);
                        intent.putExtra("loung", loung);
                        intent.putExtra("address", CompleteAddress);
                        intent.putExtra("cid", response.body().getCustomerDetail().getCustomerId());
                        intent.putExtra("assetid", QRScanId);
                        startActivity(intent);

                    }else{

                        Helper.OpenLottiDialoug(MainActivity.this,scannerView);
                    }


/*


                        //
*/


                    } else {
                        fakeVisit(response.body().getCustomerDetail().getUserId(), QRScanId, response.body().getCustomerDetail().getCustomerId());


                    }


                } else {
                    progressDialog.dismiss();
                    Helper.SHowToast(MainActivity.this, "No Customer Assigned this Assets..");
                }
            }

            @Override
            public void onFailure(Call<CheckAsset> call, Throwable t) {
                progressDialog.dismiss();
                Helper.SHowToast(MainActivity.this, t.getMessage());
            }
        });


    }

    private void fakeVisit(String userId, String QRScanId, String customerId) {


        progressDialog.setMessage("Data is uploading");
        progressDialog.show();

        Api api = ApiClient2.getClient2().create(Api.class);

        // Api api = ApiClient.getClient2().create(Api.class);
        Call<Asset_Detail> assetCall = api.AddVisit(Helper.fetchUserId(MainActivity.this), QRScanId, customerId, lat, loung, "CompleteAddress", userId);

        assetCall.enqueue(new Callback<Asset_Detail>() {
            @Override
            public void onResponse(Call<Asset_Detail> call, Response<Asset_Detail> response) {
                progressDialog.dismiss();


                Helper.SHowToast(MainActivity.this, "" + response.body().getStatus());
                if (response.body().getStatus() == 1) {

                    Helper.SHowToast(MainActivity.this, "This customer is not belongs to you");
                    showThanksDialoug();
                }
            }

            @Override
            public void onFailure(Call<Asset_Detail> call, Throwable t) {
                Helper.SHowToast(MainActivity.this, t.getMessage());
            }
        });

    }

    private void showThanksDialoug() {

        scannerView.setVisibility(View.GONE);
        View view = LayoutInflater.from(this).inflate(R.layout.row_thanks, null);
        ImageButton imageButton = view.findViewById(R.id.backBtn);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void checkQR(String QrNUmber) {

        if (preferences.getString("Field_Flag", "").equals("Y")) {

            checkUserAssest(QrNUmber);
            // Helper.SHowToast(MainActivity.this,"Done ha ");


        } else {

            //  Helper.SHowToast(MainActivity.this,"Failed  ha ");
            openDialougBOX(QrNUmber);
        }

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
            case PERMISSION_ID:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    getLastLocation();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow Location",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions1();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String s, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(s)
                .setPositiveButton("OK", onClickListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    public String firstTwo(String str) {
        return str.substring(0, 2);
    }


    private void openDialougBOX(String qrCode) {

        progressDialog.setMessage("Processing...");
        progressDialog.show();
        Api api = ApiClient2.getClient2().create(Api.class);

        Call<Asset_Detail> assetDetailCall = api.AssetDetail(qrCode);


        assetDetailCall.enqueue(new Callback<Asset_Detail>() {
            @Override
            public void onResponse(Call<Asset_Detail> call, Response<Asset_Detail> response) {

                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    Helper.SHowToast(MainActivity.this, response.body().getMessage());
                    Helper.showDialoug(response.body(),MainActivity.this,scannerView,mCodeScanner);
                } else {
                    progressDialog.dismiss();
                    Helper.SHowToast(MainActivity.this, "UnknownError");
                }

            }

            @Override
            public void onFailure(Call<Asset_Detail> call, Throwable t) {
                progressDialog.dismiss();
                Helper.SHowToast(MainActivity.this, t.getMessage());
            }
        });


    }



    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            Helper.SHowToast(MainActivity.this, "" + mLastLocation.getLongitude());

            Helper.SHowToast(MainActivity.this, "" + mLastLocation.getLatitude());
            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 10);
                lat = String.valueOf(mLastLocation.getLatitude());
                loung = String.valueOf(mLastLocation.getLongitude());
                //  addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                CompleteAddress = addresses.get(0).getAddressLine(0);
                Helper.SHowToast(MainActivity.this, "" + addresses.get(0).getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }



           /* latitudeTextView.setText("Latitude: " + mLastLocation.getLatitude() + "");
            longitTextView.setText("Longitude: " + mLastLocation.getLongitude() + "");*/
        }
    };

    // method to check for permissions
    private boolean checkPermissions1() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions1() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
