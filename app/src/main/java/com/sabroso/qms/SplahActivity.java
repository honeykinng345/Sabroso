package com.sabroso.qms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplahActivity extends AppCompatActivity {
ImageView logoImage;
    TextView companyName,zasaCompany;

    private Animation fadein, fadeout;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splah);
        sessionManager = new SessionManager(SplahActivity.this);
        fadein = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadein);

        companyName = findViewById(R.id.compnayName);
        zasaCompany = findViewById(R.id.zasa);
        logoImage = findViewById(R.id.ivLogoSplash);

      logoImage.setVisibility(View.VISIBLE);



      logoImage.setAnimation(fadein);

        companyName.setAnimation(fadein);

        companyName.setVisibility(View.VISIBLE);

        zasaCompany.setAnimation(fadeout);
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            if (sessionManager.isLoggedIn()){
                Intent intent = new Intent(SplahActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplahActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }




        }, 4000);
    }
}