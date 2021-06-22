package com.auditorias.fuerzasespeciales.ui.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
import com.auditorias.fuerzasespeciales.ui.main.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends AppCompatActivity {

    private static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 100;
    private final String[] permissions = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA, ACCESS_NETWORK_STATE, INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        solicitudPermisos();
        getCertificateSHA1Fingerprint();
        hash();
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.alpha);
        anim.reset();
        RelativeLayout relativeLayoutContainerSA = findViewById(R.id.relativeLayoutContainerSA);
        relativeLayoutContainerSA.clearAnimation();
        relativeLayoutContainerSA.startAnimation(anim);
        //anim = AnimationUtils.loadAnimation(contextSF, R.anim.zoom);
        //anim.reset();
        ImageView imageViewLogoSA = findViewById(R.id.imageViewLogoSA);
        imageViewLogoSA.clearAnimation();
        imageViewLogoSA.startAnimation(anim);
        Thread splashTreadSF = new Thread() {
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

    public void solicitudPermisos() {
        if (ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(SplashActivity.this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SplashActivity.this, permissions, MULTIPLE_PERMISSIONS_REQUEST_CODE);
        } else {
            startAnimations();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MULTIPLE_PERMISSIONS_REQUEST_CODE) {
            if (validatePermissions(grantResults)) {
                startAnimations();
            } else {
                Toast.makeText(this, getText(R.string.text_label_faltan_permisos_por_aceptar), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void hash() {


        PackageInfo info;
        try {

            info = getPackageManager().getPackageInfo(
                    this.getPackageName(), PackageManager.GET_SIGNATURES);

            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("sagar sha key", md.toString());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("sagar Hash key", something);
                System.out.println("Hash key" + something);
            }

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    private void getCertificateSHA1Fingerprint() {
        PackageManager pm = this.getPackageManager();
        String packageName = this.getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        String hexString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getEncoded());
            Log.e("sagar SHA",byte2HexFormatted(publicKey));
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }

}