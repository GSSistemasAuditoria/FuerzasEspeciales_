package com.auditorias.fuerzasespeciales.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.ui.main.MainActivity;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends AppCompatActivity {

    private static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 100;
    private final String[] permissions = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA, ACCESS_NETWORK_STATE, INTERNET};
    private Thread splashTreadSF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        requestPemissionsMulti();
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.alpha);
        anim.reset();
        RelativeLayout RelativeLySplash = findViewById(R.id.relativeLayoutSplash);
        RelativeLySplash.clearAnimation();
        RelativeLySplash.startAnimation(anim);
        //anim = AnimationUtils.loadAnimation(contextSF, R.anim.zoom);
        //anim.reset();
        ImageView ImgSplash = findViewById(R.id.imageViewLogoSplash);
        ImgSplash.clearAnimation();
        ImgSplash.startAnimation(anim);
        splashTreadSF = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }

                    if (TableDataUser.getIdEmpleado(SplashActivity.this).isEmpty()) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashTreadSF.start();
    }

    private boolean validatePermissions(int[] grantResults) {
        boolean allGranted = false;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                allGranted = true;
            } else {
                allGranted = false;
                break;
            }
        }
        return allGranted;
    }

    public void requestPemissionsMulti() {
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, permissions, MULTIPLE_PERMISSIONS_REQUEST_CODE);
        } else {
            startAnimations();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case MULTIPLE_PERMISSIONS_REQUEST_CODE:
                if (validatePermissions(grantResults)) {
                    startAnimations();
                } else {
                    Toast.makeText(this, "Faltan Permisos por Aceptar", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}