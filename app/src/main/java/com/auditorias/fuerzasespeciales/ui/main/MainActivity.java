package com.auditorias.fuerzasespeciales.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.datosUsuario.DetalleUsuario;
import com.auditorias.fuerzasespeciales.request.envioRequest;
import com.auditorias.fuerzasespeciales.ui.login.LoginActivity;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import okhttp3.HttpUrl;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    //private TextView textViewTituloPrincipalMA;
    private ImageView imageViewSpacioMA;
    private int idUsuario;
    private int numeroNotificaciones;

    private TextView textCartItemCount;
    //int mCartItemCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        setObtenerNotificacionesUsuario(this, Integer.parseInt(TableDataUser.getIdEmpleado(this)));
        toolbar = findViewById(R.id.toolbar_generic);

        //textViewTituloPrincipalMA = findViewById(R.id.textViewSubTiutuloCST);
        imageViewSpacioMA = findViewById(R.id.imageViewSpacioMA);

        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_inicio_fragment, R.id.navigation_nueva_denuncia_fragment, R.id.navigation_busqueda_denuncias, R.id.navigation_denuncias_terminadas, R.id.navigation_notificaciones_fragment).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        //navView.getBadge(R.id.menu_action_notification).setNumber(2);
        //navView.getBadge(R.id.menu_action_notification).setNumber(9);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.navigation_inicio_fragment:
                    case R.id.navigation_nueva_denuncia_fragment:
                    case R.id.navigation_busqueda_denuncias:
                    case R.id.navigation_denuncias_terminadas:
                    case R.id.navigation_notificaciones_fragment:
                        //textViewTituloPrincipalMA.setText(getString(R.string.title_complaints_finished));
                        //textViewTituloPrincipalMA.setText(getString(R.string.title_search));
                        //textViewTituloPrincipalMA.setText(getString(R.string.title_nuevas_denuncias));
                        //textViewTituloPrincipalMA.setText(TableDataUser.getNombreAbodago(MainActivity.this).concat(" - ").concat(TableDataUser.getPerfil(MainActivity.this)));
                        imageViewSpacioMA.setVisibility(View.VISIBLE);
                        setObtenerNotificacionesUsuario(MainActivity.this, Integer.parseInt(TableDataUser.getIdEmpleado(MainActivity.this)));
                        setupBadge();
                        break;
                    case R.id.navigation_proceso_fase_denuncia_fragment:
                        //txtVwTileMainContainer.setText(getString(R.string.title_detalle_del_caso));
                        //imageView3.setVisibility(View.GONE);
                        //break;
                    case R.id.navigation_detalle_del_caso_fragment:
                    case R.id.navigation_cerrar_fase_fragment:
                    case R.id.navigation_iniciar_fase_fragment:
                    case R.id.navigation_reprogramar_fase_fragment:
                    case R.id.navigation_geleria_tomar_fotos_fragment:
                        //textViewTituloPrincipalMA.setText(R.string.title_reprogramar_presentacion_de_denuncia);
                        //textViewTituloPrincipalMA.setText(R.string.title_presentacion_de_denuncia);
                        //textViewTituloPrincipalMA.setText(getString(R.string.title_detalle_del_caso));
                        imageViewSpacioMA.setVisibility(View.GONE);
                        setObtenerNotificacionesUsuario(MainActivity.this, Integer.parseInt(TableDataUser.getIdEmpleado(MainActivity.this)));
                        setupBadge();
                        break;

                    default:
                        break;
                }
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //para versiones con android 10.0 o superior.
            if ((checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constantes.RESPUESTA_DE_CAMARA);
            }
        } else {
            //para versiones inferiores a android 10.0.
            myRequestStoragePermission(this);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.navigation_notificaciones_fragment);

        View actionView = menuItem.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        int id = item.getItemId();

        if (id == R.id.menu_action_cerrar_session) {
            showAlertDialog(MainActivity.this, getString(R.string.text_label_titulo_cerrar_sesion), getString(R.string.text_label_estas_seguro_de_cerrar_sesion), getString(R.string.text_label_si), getString(R.string.text_label_no));
        } else if (id == R.id.navigation_notificaciones_fragment) {
            //Toast.makeText(this, "OPCION notifications", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menu_action_perfil) {
            //showDialogDetallePerfil(this, "");
            //Toast.makeText(this, "OPCION notifications", Toast.LENGTH_SHORT).show();
            getObtenerDetalleUsuario(this);
        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (numeroNotificaciones == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(numeroNotificaciones, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //TODO: consumo de servicio para el llenado del spinner de tipo de empleado
    private void getObtenerDetalleUsuario(Activity activity) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    for (int x = 0; x < respuestaGeneral.getDetalleUsuario().size(); x++) {
                        showDialogDetallePerfil(activity, "Abogado", respuestaGeneral.getDetalleUsuario().get(x));
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerDetalleUsuario.concat(Constantes.signoInterrogacion).concat(Constantes.idUsuario).concat(Constantes.signoIgual).concat(TableDataUser.getIdEmpleado(activity)));
        } else {
            //showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 1);
            //Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    public void setObtenerNotificacionesUsuario(Activity activity, int idUsuario) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new envioRequest(idUsuario));
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        //Serial serial = gson.fromJson(result, Serial.class);
                        numeroNotificaciones = respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().size();

                        int menuItemId = navView.getMenu().getItem(4).getItemId();
                        BadgeDrawable badge = navView.getOrCreateBadge(menuItemId);
                        badge.setNumber(numeroNotificaciones);
                        Log.i("getDelegate", "getDelegate: " + numeroNotificaciones);
                        //if (serial.getIniciarFaseResult().getExito().equals(Constantes.exitoTrue)) {
                        //showDialogInicioFaseConExito(activity, view , getString(R.string.text_label_inicio), getString(R.string.text_label_se_ha_iniciado_esta_fase_con_exito), getString(R.string.text_label_aceptar), String.valueOf(serial.getIniciarFaseResult().getIdCaso()));
                        //} else {
                        //    Utils.message(activity, serial.getIniciarFaseResult().getError());
                        //}
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerNotificacionesUsuario, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialogDetallePerfil(Activity activity, String titulo, DetalleUsuario detalleUsuario) {
        Dialog dialog = new Dialog(activity, R.style.CustomDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_detalle_abogado);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView imageViewPerfilDDA = dialog.findViewById(R.id.imageViewPerfilDDA);
        TextView textViewNombrePerfilDDA = dialog.findViewById(R.id.textViewNombrePerfilDDA);
        TextView textViewTituloDDA = dialog.findViewById(R.id.textViewTituloDDA);
        TextView textViewPerfilPerfilDDA = dialog.findViewById(R.id.textViewPerfilPerfilDDA);
        TextView textViewCecoPerfilDDA = dialog.findViewById(R.id.textViewCecoPerfilDDA);
        TextView textViewNumeroEmpleadoPerfilDDA = dialog.findViewById(R.id.textViewNumeroEmpleadoPerfilDDA);
        TextView textViewRegionPerfilDDA = dialog.findViewById(R.id.textViewRegionPerfilDDA);
        TextView textViewZonaPerfilDDA = dialog.findViewById(R.id.textViewZonaPerfilDDA);
        TextView textViewCorreoPerfilDDA = dialog.findViewById(R.id.textViewCorreoPerfilDDA);
        TextView buttonCerrarDDA = dialog.findViewById(R.id.buttonCerrarDDA);
        TextView textViewTipoEmpleadoDDA = dialog.findViewById(R.id.textViewTipoEmpleadoDDA);

        textViewTituloDDA.setText(titulo);
        textViewNombrePerfilDDA.setText(detalleUsuario.getNombre());
        textViewPerfilPerfilDDA.setText(detalleUsuario.getPerfil());
        textViewCecoPerfilDDA.setText(detalleUsuario.getCeco());
        textViewNumeroEmpleadoPerfilDDA.setText(detalleUsuario.getIdUsuario());
        textViewRegionPerfilDDA.setText(detalleUsuario.getRegion());
        textViewZonaPerfilDDA.setText(detalleUsuario.getZona());
        textViewCorreoPerfilDDA.setText(detalleUsuario.getCorreo());
        textViewTipoEmpleadoDDA.setText(detalleUsuario.getTipoEmpleado());

        try {
            Picasso.get().load("https://portal.socio.gs/foto/back_office/empleados/".concat(detalleUsuario.getIdUsuario()).concat(".jpg")).into(imageViewPerfilDDA);
        } catch (Exception e) {
            e.printStackTrace();
            //Picasso.get().load("https://portal.socio.gs/foto/back_office/empleados/".concat(detalleUsuario.getIdUsuario()).concat(".jpg")).into(imageViewPerfilDDA);
        }

        //buttonCerrarDDA.setText(butonText);
        buttonCerrarDDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showAlertDialog(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Functions.signOff(activity);

                HttpUrl authorizeUrl = HttpUrl.parse("https://authns.desadsi.gs/nidp/jsp/logoutSuccess_latest.jsp?rp=https://portal.socio.gs").newBuilder().build();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(String.valueOf(authorizeUrl.url())));
                startActivity(intent);
                finish();

            }
        });

        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    private boolean myRequestStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if ((activity.checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) && (activity.checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }

        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))) {
            cargarDialogoRecomendacion(activity);
        } else {
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, Constantes.MIS_PERMISOS);
        }
        return false;
    }

    private void cargarDialogoRecomendacion(Activity activity) {
        android.app.AlertDialog.Builder dialogo = new android.app.AlertDialog.Builder(activity);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la Ap");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, Constantes.MIS_PERMISOS);
                }
            }
        });
        dialogo.show();
    }
}

