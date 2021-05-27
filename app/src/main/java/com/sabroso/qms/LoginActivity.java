package com.sabroso.qms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.sabroso.qms.Models.Api;
import com.sabroso.qms.Models.UserDetail;
import com.sabroso.qms.Models.customers;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    Vibrator vibrator;
    EditText userName, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog;

    SQLiteHandler databaseHelper;

    SessionManager sessionManager;

    Executor mExceutor = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorprimary));
        }*/
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        databaseHelper = new SQLiteHandler(LoginActivity.this);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        sessionManager = new SessionManager(LoginActivity.this);



        preferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = preferences.edit();

        loginBtn = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.emailET);
        password = findViewById(R.id.password);

        loginBtn.setOnClickListener(v -> {
            String u = userName.getText().toString().trim();
            String p = password.getText().toString().trim();
            checkCredentaility(u, p);
        });


    }

    private void checkCredentaility(String u, String p) {


        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (TextUtils.isEmpty(u)) {
            // Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            userName.setError("All fields are required");
            userName.requestFocus();

            Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
            userName.startAnimation(shake);
            return;
        }
        if (TextUtils.isEmpty(p)) {
            // Vibrate for 100 milliseconds
            vibrator.vibrate(100);
            password.setError("All fields are required");
            password.requestFocus();

            Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
            password.startAnimation(shake);
            return;
        }

        login(u, p);

    }

    private void login(String username, String reqpasword) {
        progressDialog.setMessage("Checking your Credential's");
        progressDialog.show();
        Api api =  ApiClient.getClient().create(Api.class);

        Call<UserDetail> login = api.Login(username, reqpasword);
        login.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {

               ;
          /*      Log.d("JSON  LIST", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                Helper.SHowToast(LoginActivity.this, "" + response.body().getMessage());
                Helper.SHowToast(LoginActivity.this, "" + response.body().getStatus());*/
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                 //   Helper.SHowToast(LoginActivity.this, "" + response.body().getMessage());
                    if (databaseHelper.insertData(username, String.valueOf(response.body().getUserDetails().getUserTypeCode()),response.body().getUserDetails().getUserName(),response.body().getUserDetails().getUserActive())){

                    }
                    sessionManager.setLogin(true);
                    editor.putString("UserName", response.body().getUserDetails().getUserName().toString());
                    editor.putString("Field_Flag",response.body().getUserDetails().getFieldFlag().toString());
                    editor.commit();

                    finish();



                    Helper.transectionActivityToActivity(LoginActivity.this, MainActivity.class);

                } else if (response.body().getStatus() == 3) {
                    progressDialog.dismiss();
                    Helper.SHowToast(LoginActivity.this, "" + response.body().getMessage());
                } else if (response.body().getStatus() == 5) {
                    progressDialog.dismiss();
                    Helper.SHowToast(LoginActivity.this, "" + response.body().getMessage());

                } else {
                    progressDialog.dismiss();
                    Helper.SHowToast(LoginActivity.this, "UnknownError");
                }
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                progressDialog.dismiss();
              //  Helper.hideDialoug(LoginActivity.this);
                //   Helper.hideDialoug(LoginActivity.this);
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}