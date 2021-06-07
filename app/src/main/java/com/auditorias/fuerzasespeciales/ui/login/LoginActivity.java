package com.auditorias.fuerzasespeciales.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.request.login.LoginRequest;
import com.auditorias.fuerzasespeciales.ui.main.MainActivity;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.AsyncTaskGralSinToken;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import okhttp3.HttpUrl;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonEnterokayLF;
    private TextInputEditText textInputEditTextEmployeeIdLF;
    private TextInputLayout textInputLayoutNumeroEmpleadoLF;
    private String valorDeConfiguracion;
    private String descripcionConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //activity = this;
        textInputEditTextEmployeeIdLF = findViewById(R.id.textInputEditTextNumeroEmpleadoLF);
        textInputLayoutNumeroEmpleadoLF = findViewById(R.id.textInputLayoutNumeroEmpleadoLF);

        buttonEnterokayLF = findViewById(R.id.buttonIngresarLF);
        buttonEnterokayLF.setOnClickListener(this);

        ConstraintLayout contrainfLayoutContainerLF = findViewById(R.id.constraint_layout_container_LF);
        contrainfLayoutContainerLF.setOnClickListener(this);

        getObtenerConfiguracion(LoginActivity.this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIngresarLF:
                if (!valorDeConfiguracion.equals("true")) {
                    HttpUrl authorizeUrl = HttpUrl.parse(Constantes.LOGIN_URL)
                            .newBuilder()
                            .addQueryParameter("response_type", "token")
                            //.addQueryParameter(new Encryption().decryptAES(getString(R.string.client_text)), getString(R.string.client))
                            .addQueryParameter("acr_values", "gs/doblefactoridm/uri")
                            .addQueryParameter("scope", "SistemaFraudes")
                            .addQueryParameter("client_id", "5f7f0e14-c698-44ec-9525-8531c0b93217")
                            .addQueryParameter("client_secret", "frjoxXAYVMUxuEY2vUUrMOiHUV3U0rNOjOZKxPdvaXLMUYXS-_j14_CAszKG8OKjVCNizhkDzSTCa8JfLrWiRA")
                            .build();
                    Intent mIntent = new Intent(Intent.ACTION_VIEW);
                    mIntent.setData(Uri.parse(String.valueOf(authorizeUrl.url())));
                    startActivity(mIntent);
                } else {
                    String numeroEmpleado = textInputEditTextEmployeeIdLF.getText().toString();
                    if (numeroEmpleado.isEmpty()) {
                        textInputEditTextEmployeeIdLF.setError(getString(R.string.text_label_el_numero_empleado_esta_vacio));
                        textInputEditTextEmployeeIdLF.requestFocus();
                    } else {
                        if (Functions.isNetworkAvailable(LoginActivity.this)) {
                            setLoginAuth(LoginActivity.this, numeroEmpleado, "", "");
                        } else {
                            Utils.messageShort(LoginActivity.this, getString(R.string.text_label_error_de_conexion));
                        }
                    }
                }
                break;

            case R.id.constraint_layout_container_LF:
                Functions.hideTheKeyboard(LoginActivity.this, textInputEditTextEmployeeIdLF);
                break;

            default:
                break;
        }
    }

    public void setLoginAuth(Activity activity, String numeroEmpleado, String token, String browserInfo) {
        try {
            if (Functions.isNetworkAvailable(LoginActivity.this)) {
                Gson gsonParams = new Gson();
                String params;
                if (!valorDeConfiguracion.equals(Constantes.exitoTrue)) {
                    params = gsonParams.toJson(new LoginRequest(2, token));
                } else {
                    params = gsonParams.toJson(new LoginRequest(numeroEmpleado, 2, token, browserInfo));
                }

                new AsyncTaskGralSinToken(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();

                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getLoginoAuthResult().getExito().equals(Constantes.exitoTrue)) {

                            if (respuestaGeneral.getLoginoAuthResult().getJWT() != null && respuestaGeneral.getLoginoAuthResult().getUsuario() != null) {

                                TableDataUser.setAgregarDatosDeAbogado(activity, respuestaGeneral.getLoginoAuthResult(), respuestaGeneral.getLoginoAuthResult().getUsuario());

                                if (TableDataUser.getIdEmpleado(activity).isEmpty() || TableDataUser.getIdEmpleado(activity) != null) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    activity.finish();
                                }

                            } else {
                                Utils.message(activity, respuestaGeneral.getLoginoAuthResult().getError());
                            }
                        } else {
                            Utils.message(activity, respuestaGeneral.getLoginoAuthResult().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.loginoAuth, params);
            } else {
                Utils.message(LoginActivity.this, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getObtenerConfiguracion(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracion = respuestaGeneral.getConfiguracionData().getValor();
                            descripcionConfiguracion = respuestaGeneral.getConfiguracionData().getDescripcion();
                            if (!valorDeConfiguracion.equals("true")) {
                                textInputEditTextEmployeeIdLF.setVisibility(View.GONE);
                                textInputLayoutNumeroEmpleadoLF.setVisibility(View.GONE);
                                buttonEnterokayLF.setText(getString(R.string.text_label_llave_maestra));
                                Uri data = null;
                                if (activity.getIntent() != null && activity.getIntent().getData() != null) {
                                    data = getIntent().getData();
                                }

                                if (data != null && !TextUtils.isEmpty(data.getScheme())) {
                                    if ("sistemafraudes".equals(data.getScheme())) {
                                        String code = null;
                                        for (String mValue : data.toString().split("&")) {
                                            String[] values = mValue.split("=");
                                            if (values[0].equals("access_token")) {
                                                code = values[1];
                                                break;
                                            }
                                        }
                                        if (!TextUtils.isEmpty(code)) {
                                            setLoginAuth(LoginActivity.this, "", code, "app");
                                        }
                                    }
                                }
                            } else {
                                textInputEditTextEmployeeIdLF.setVisibility(View.VISIBLE);
                                textInputLayoutNumeroEmpleadoLF.setVisibility(View.VISIBLE);
                                buttonEnterokayLF.setText(getString(R.string.text_label_ingresar));
                            }
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat("Debug"));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void setLlenarCatalogoEtapasCaso(Activity activity ) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);

                        for (int x = 0; x < serial.getEtapaCasoList().size(); x++) {
                            if (serial.getEtapaCasoList().get(x).getId() > 0) {
                                TableEtapasCaso.setAgregarNuevaEtapaSQLite(activity, serial.getEtapaCasoList().get(x));
                            }
                        }
                    }

                    @Override
                    public void executeInBackground(String result) {

                    }
                }, getString(R.string.text_label_cargando)).execute(AppFuerzasEspeciales.METHOD_GET, AppFuerzasEspeciales.obtenerCatalogoEtapaCaso).get();
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (RuntimeException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }*/

    //TODO: consumo de servicio para el llenado del spinner de tipo de fraude
    /*private void setLlenarCatalogoTipoEmpleado(Activity activity) {
        if (Functions.isNetworkAvailable(activity)) {
            try {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);

                        for (int x = 0; x < serial.gettipoDeEmpleadoList().size(); x++) {
                            //if (serial.getEtapaCasoList().get(x).getId() > 0) {
                                TablaTipoEmpleado.setAgregarTipoEmpleado(activity, serial.gettipoDeEmpleadoList().get(x));
                            //}
                        }
                    }

                    @Override
                    public void executeInBackground(String result) {

                    }
                }, getString(R.string.text_label_cargando)).execute(AppFuerzasEspeciales.METHOD_GET, AppFuerzasEspeciales.obtenerCatalogoTipoEmpleado).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }*/
}