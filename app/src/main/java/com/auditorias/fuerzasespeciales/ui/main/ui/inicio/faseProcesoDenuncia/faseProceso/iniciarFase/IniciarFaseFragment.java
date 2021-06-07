package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.Serial;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.request.FaseRequest;
import com.auditorias.fuerzasespeciales.request.IniciaCasoRequest;
import com.auditorias.fuerzasespeciales.request.envioRequest;
import com.auditorias.fuerzasespeciales.request.ResponsablesPresentacionRequest;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters.ImputadosFaseAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IniciarFaseFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = IniciarFaseFragment.class.getName();

    //TODO: todas las lista que se pueden utilizar en el fragmento
    private final List<EstatusResponsableFase> listEstatusResponsableFase = new ArrayList<>();

    private final List<ResponsablesPresentacionRequest> listResposablesPresentacion = new ArrayList<>();


    //TODO:todas los adapters que se pueden utilizar en el fragmento
    private ImputadosFaseAdapter imputadosFaseAdapter;

    //TODO:todas las variales que se pueden utilizar en el fragmento
    private String idCasoFase;
    private String idCaso;
    private String fechaCompromiso;

    //TODO: son todos los textview del fragment
    private TextView textViewSubTiutuloCST;
    private TextView textViewFolioDenunciaCDD;
    private TextView textViewNombreDenunciaCDD;
    private TextView textViewPorcentajeGeneralDenunciaCDD;
    private TextView textViewTipoDelitoCDD;
    private TextView textViewUnidadNegocioCDD;
    private TextView textViewFechaResgistroCDD;
    private TextView textViewFechaCompromisoFaseTextCDD;
    private TextView textViewFechaCompromisoFaseCDD;
    private TextView textViewZonaCDD;
    private TextView textViewAutorizacionTextCDD;
    private TextView textViewAutorizacionCDD;
    private TextView textViewFechaCompromisoCFC;
    private TextView textViewMostrarOcultarCLI;
    private TextView textViewTotalImputadosCLI;
    private TextView textViewAlertErrorCFC;

    //TODO: todos los textInputLayout de este fragment
    private TextInputLayout textInputLayoutDatosDenunciaCDDDA;
    private TextInputLayout textInputLayputDatosAgenciaCDDDA;

    //TODO: todos los textInputEditText de esta fragment
    private TextInputEditText textInputEditTextDatosDenunciaCDDDA;
    private TextInputEditText textInputEditTextDatosAgenciaCDDDA;

    //TODO: todos los buttons de este fragment
    private Button buttonInicioFaseIFF;

    //TODO: todos los recyclerview de este fragment
    private RecyclerView recyclerViewListaImputadosCLI;

    //TODO: todos los ImageButton de este fragment
    private ImageView imageViewAlertErrorCFC;

    //TODO:todos los linearLayout de este fragment
    private LinearLayout linearLayoutColorEtapaDenunciaCDD;

    //TODO: todas los view que se pueden utilizar en el fragmento
    private View view;
    private View custumSpinnerSelectIFF;
    private View custumCardViewAdjuntarDocumentosIFF;
    private View custumDocumentosIntegracionIFF;
    //TODO:todas las lista que se pueden utilizar en el fragmento
    private Context context;
    //TODO: todas las activity que se pueden utilizar en el fragmento
    private Activity activity;
    //TODO: todas las fragmentmanager que se pueden utilizar en el fragmento
    private FragmentManager fragmentManager;
    //TODO: variables de argumentos para leer datos de otro fragment
    private Bundle args;

    private int mostrarListaImputados = 0;

    public IniciarFaseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_iniciar_fases, container, false);
        activity = getActivity();
        context = getContext();
        fragmentManager = getFragmentManager();

        refereciasConInterface(view);
        ocultarElementos();

        args = getArguments();
        if (args != null) {
            idCaso = args.getString("idCaso");
            idCasoFase = args.getString("idCasoFase");
            getEstatusImputadoFase(activity);
            if (idCaso != null) {
                getDatosCasos(activity, idCaso);
            }
        }

        return view;
    }

    public void refereciasConInterface(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        linearLayoutColorEtapaDenunciaCDD = view.findViewById(R.id.linearLayoutColorEtapaDenunciaCDD);
        textViewFolioDenunciaCDD = view.findViewById(R.id.textViewFolioDenunciaCDD);
        textViewNombreDenunciaCDD = view.findViewById(R.id.textViewNombreDenunciaCDD);
        textViewPorcentajeGeneralDenunciaCDD = view.findViewById(R.id.textViewPorcentajeGeneralDenunciaCDD);
        textViewTipoDelitoCDD = view.findViewById(R.id.textViewTipoDelitoCDD);
        textViewUnidadNegocioCDD = view.findViewById(R.id.textViewUnidadNegocioCDD);
        textViewFechaResgistroCDD = view.findViewById(R.id.textViewFechaResgistroCDD);
        textViewFechaCompromisoFaseTextCDD = view.findViewById(R.id.textViewFechaCompromisoFaseTextCDD);
        textViewFechaCompromisoFaseCDD = view.findViewById(R.id.textViewFechaCompromisoFaseCDD);
        textViewZonaCDD = view.findViewById(R.id.textViewZonaCDD);
        textViewAutorizacionTextCDD = view.findViewById(R.id.textViewAutorizacionTextCDD);
        textViewAutorizacionCDD = view.findViewById(R.id.textViewAutorizacionCDD);

        textViewFechaCompromisoCFC = view.findViewById(R.id.textViewFechaCompromisoCFC);
        textViewAlertErrorCFC = view.findViewById(R.id.textViewAlertErrorCFC);
        imageViewAlertErrorCFC = view.findViewById(R.id.imageViewAlertErrorCFC);

        textInputLayoutDatosDenunciaCDDDA = view.findViewById(R.id.textInputLayoutDatosDenunciaCDDDA);
        textInputEditTextDatosDenunciaCDDDA = view.findViewById(R.id.textInputEditTextDatosDenunciaCDDDA);
        textInputLayputDatosAgenciaCDDDA = view.findViewById(R.id.textInputLayputDatosAgenciaCDDDA);
        textInputEditTextDatosAgenciaCDDDA = view.findViewById(R.id.textInputEditTextDatosAgenciaCDDDA);

        textViewMostrarOcultarCLI = view.findViewById(R.id.textViewMostrarOcultarCLI);
        textViewMostrarOcultarCLI.setOnClickListener(this);
        recyclerViewListaImputadosCLI = view.findViewById(R.id.recyclerViewListaImputadosCLI);
        textViewTotalImputadosCLI = view.findViewById(R.id.textViewTotalImputadosCLI);

        buttonInicioFaseIFF = view.findViewById(R.id.buttonInicioFaseIFF);
        buttonInicioFaseIFF.setOnClickListener(this);

        custumSpinnerSelectIFF = view.findViewById(R.id.custumSpinnerSelectIFF);
        custumCardViewAdjuntarDocumentosIFF = view.findViewById(R.id.custumCardViewAdjuntarDocumentosIFF);
        custumDocumentosIntegracionIFF = view.findViewById(R.id.custumDocumentosIntegracionIFF);

    }

    public void ocultarElementos() {
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
        textViewPorcentajeGeneralDenunciaCDD.setVisibility(View.GONE);
        textViewAlertErrorCFC.setVisibility(View.GONE);
        imageViewAlertErrorCFC.setVisibility(View.GONE);
        custumSpinnerSelectIFF.setVisibility(View.GONE);
        custumCardViewAdjuntarDocumentosIFF.setVisibility(View.GONE);
        custumDocumentosIntegracionIFF.setVisibility(View.GONE);
        recyclerViewListaImputadosCLI.setVisibility(View.GONE);
    }

    private void getDatosCasos(Activity activity, String idCaso) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        //Serial serial = gson.fromJson(result, Serial.class);
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getDatosDenuncia().getExito().equals(Constantes.exitoTrue)) {

                            textViewSubTiutuloCST.setText(getString(R.string.text_label_inicio).concat(" - ").concat(respuestaGeneral.getDatosDenuncia().getFase()));
                            linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(respuestaGeneral.getDatosDenuncia().getColorEtapaCaso()));
                            textViewFolioDenunciaCDD.setText(respuestaGeneral.getDatosDenuncia().getFolio());
                            textViewNombreDenunciaCDD.setText(respuestaGeneral.getDatosDenuncia().getNombre());
                            textViewUnidadNegocioCDD.setText(respuestaGeneral.getDatosDenuncia().getUdN());
                            textViewZonaCDD.setText(respuestaGeneral.getDatosDenuncia().getZona());
                            textViewTipoDelitoCDD.setText(respuestaGeneral.getDatosDenuncia().getTipoFraude());
                            textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(respuestaGeneral.getDatosDenuncia().getFechaRegistro()));
                            if (respuestaGeneral.getDatosDenuncia().getFechaCompromiso() != null) {
                                //fechaCompromiso = serial.getDatosCasoModel().getFechaCompromiso();
                                try {
                                    fechaCompromiso = Utils.cambiarFechayyyyMMdd(respuestaGeneral.getDatosDenuncia().getFechaCompromiso());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                textViewFechaCompromisoCFC.setText(Utils.SetCambioFormatoFechaDiaMesAnio(respuestaGeneral.getDatosDenuncia().getFechaCompromiso()));
                                //textViewFechaCompromisoCFC.setTextColor(activity.getColor(R.color.green_secondary));
                            } else {
                                fechaCompromiso = "";
                                textViewFechaCompromisoCFC.setText("");
                            }

                            textViewTotalImputadosCLI.setText(String.valueOf(respuestaGeneral.getDatosDenuncia().getTotalResponsables()));

                            imputadosFaseAdapter = new ImputadosFaseAdapter(activity, respuestaGeneral.getDatosDenuncia().getListResponsables(), fragmentManager, listEstatusResponsableFase, new ImputadosFaseAdapter.OnItemSelectedListener() {
                                @Override
                                public void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int idCasoResponsable, int idStatusResponsable) {
                                    if (idStatusResponsable != 0) {
                                        listResposablesPresentacion.add(new ResponsablesPresentacionRequest(idCasoFase, idCasoResponsable, idStatusResponsable));
                                        //parametros para la lista que se envia                             IdCasoFase, IdCasoResponsable, IdStatusResponsable
                                    }
                                }
                            });
                            recyclerViewListaImputadosCLI.setHasFixedSize(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                            recyclerViewListaImputadosCLI.setLayoutManager(layoutManagerCategory);
                            recyclerViewListaImputadosCLI.setNestedScrollingEnabled(false);
                            recyclerViewListaImputadosCLI.setAdapter(imputadosFaseAdapter);
                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getDatosDenuncia().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerDatosCaso.concat(Constantes.signoInterrogacion).concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: consumo de servicio de status de los responsables que estan dentro de la lista de resposales
    private void getEstatusImputadoFase(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        getEstatusImputadosList(respuestaGeneral.getLisEstatusResponsableFase());

                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoStatusResponsableFase);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEstatusImputadosList(List<EstatusResponsableFase> list) {
        listEstatusResponsableFase.add(new EstatusResponsableFase(Constantes.selecionar, "", 0, 0));
        listEstatusResponsableFase.addAll(list);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewMostrarOcultarCLI:
                if (mostrarListaImputados == 0){
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_ocultar));
                    recyclerViewListaImputadosCLI.setVisibility(View.VISIBLE);
                    mostrarListaImputados = 1;
                } else {
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_mostrar));
                    recyclerViewListaImputadosCLI.setVisibility(View.GONE);
                    mostrarListaImputados = 0;
                }
                break;

            case R.id.buttonInicioFaseIFF:
                String datosDemanda = Objects.requireNonNull(textInputEditTextDatosDenunciaCDDDA.getText()).toString().trim();
                String datosAgencia = Objects.requireNonNull(textInputEditTextDatosAgenciaCDDDA.getText()).toString().trim();
                if (idCaso.isEmpty()) {
                    Utils.messageShort(activity, getString(R.string.text_label_id_caso));
                } else if (String.valueOf(idCasoFase).isEmpty()) {
                    Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
                } else if (datosDemanda.isEmpty()) {
                    textInputEditTextDatosDenunciaCDDDA.setError(getString(R.string.text_label_datos_demanda_vacio));
                    textInputEditTextDatosDenunciaCDDDA.requestFocus();
                } else if (datosDemanda.length() <= 9) {
                    textInputEditTextDatosDenunciaCDDDA.setError(getString(R.string.text_label_datos_demanda_menor_a_diez_caracteres));
                    textInputEditTextDatosDenunciaCDDDA.requestFocus();
                } else if (datosAgencia.isEmpty()) {
                    textInputEditTextDatosAgenciaCDDDA.setError(getString(R.string.text_label_datos_agencia_vacio));
                    textInputEditTextDatosAgenciaCDDDA.requestFocus();
                } else if (datosAgencia.length() <= 9) {
                    textInputEditTextDatosAgenciaCDDDA.setError(getString(R.string.text_label_datos_agencia_menor_a_diez_caracteres));
                    textInputEditTextDatosAgenciaCDDDA.requestFocus();
                } else if (fechaCompromiso.isEmpty()) {
                    Utils.messageShort(activity, getString(R.string.text_label_fecha_compromiso_esta_vacio));
                } else  {
                    if (Functions.isNetworkAvailable(activity)) {
                        showAlertDialogInicioPresentacion(activity, v, getString(R.string.text_label_inicio_de_presentacion), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                idCaso, Integer.parseInt(idCasoFase), datosDemanda, datosAgencia, fechaCompromiso, listResposablesPresentacion);
                    } else {
                        Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                    }
                }
                break;

            default:
                break;
        }
    }

    public void showAlertDialogInicioPresentacion(Activity activity, View view, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, String idCaso, int idCasoFase, String datosDemanda, String datosAgencia, String fechaCompromiso, List<ResponsablesPresentacionRequest> resposablesRequestList) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                setIniciarFase(activity, view, idCaso, idCasoFase, datosDemanda, datosAgencia, fechaCompromiso, resposablesRequestList);
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void setIniciarFase(Activity activity, View view, String idCaso, int idCasoFase, String datosDemanda, String datosAgencia, String fechaCompromiso, List<ResponsablesPresentacionRequest> resposablesRequestList) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new envioRequest(new IniciaCasoRequest(Integer.parseInt(idCaso), datosDemanda, datosAgencia), new FaseRequest(idCasoFase, fechaCompromiso), resposablesRequestList));
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);

                        if (serial.getIniciarFaseResult().getExito().equals(Constantes.exitoTrue)) {
                            showDialogInicioFaseConExito(activity, view , getString(R.string.text_label_inicio), getString(R.string.text_label_se_ha_iniciado_esta_fase_con_exito), getString(R.string.text_label_aceptar), String.valueOf(serial.getIniciarFaseResult().getIdCaso()));
                        } else {
                            Utils.message(activity, serial.getIniciarFaseResult().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.iniciarFase, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialogInicioFaseConExito(Activity activity,View view, String titulo, String mensaje,  String butonText, String idCaso) {
        Dialog dialog = new Dialog(activity, R.style.CustomDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_error_conexion_internet);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //ImageView imageViewLogoDRCI = dialog.findViewById(R.id.imageViewLogoDRCI);
        TextView textViewTituloDECI = dialog.findViewById(R.id.textViewTituloDECI);
        textViewTituloDECI.setText(titulo);
        TextView textViewMensageDECI = dialog.findViewById(R.id.textViewMensageDECI);
        textViewMensageDECI.setText(mensaje);
        Button buttonIntentarDECI = dialog.findViewById(R.id.buttonIntentarDECI);
        buttonIntentarDECI.setText(butonText);

        buttonIntentarDECI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                Navigation.findNavController(view).navigate(R.id.action_navigation_iniciar_fase_fragment_to_navigation_proceso_fase_denuncia_fragment, bundle);
            }
        });

        dialog.show();
    }

}