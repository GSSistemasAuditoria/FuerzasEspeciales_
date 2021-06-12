package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenuncia;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaResponsables;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDocumento;
import com.auditorias.fuerzasespeciales.request.documentos.ObtenerDocumentos;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaDocumentosAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaFasesAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaReprogramadasAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaSubfasesAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleEmpleadosResponsablesAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetalleDelCasoFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = DetalleDelCasoFragment.class.getName();

    private final List<DetalleDenunciaResponsables> listDetallaResponsables = new ArrayList<>();
    private final List<DetalleDocumento> listDetalleDocumentos = new ArrayList<>();
    private final List<DetalleDenunciaFase> listDetalleFases = new ArrayList<>();

    private View view;
    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;
    private Bundle args;
    private DetalleDenuncia detalleDenuncia;
    private TextView textViewSubTiutuloCST;
    private TextView textViewDenunciaDDF;
    private TextView textViewResponsablesDDF;
    private TextView textViewDocumentoDDF;
    private TextView textViewFasesDDF;
    private String idCaso;
    private String valorDeConfiguraciontipoAppMovil;
    private String descripcionConfiguraciontipoAppMovil;

    public DetalleDelCasoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complaint_details, container, false);
        activity = getActivity();
        context = getContext();
        fragmentManager = getFragmentManager();

        refereciasConInterface(view);
        textViewSubTiutuloCST.setText(getString(R.string.title_detalle_del_caso));

        args = getArguments();
        if (args != null) {
            //guardaCatalogoCasoModel = args.getParcelable("guardaCatalogoCasoModel");

            idCaso = args.getString("idCaso");
            if (idCaso != null) {
                getDetalleCaso(activity, idCaso);
                getObtenerConfiguracionTipoAppMovil(activity);
            } //else/* if (serialDatosCaso != null) {
            //    getDetalleCaso(activityCDF, serialDatosCaso.getDatosCasoModel().getFolio());
            //} else*/ //if (guardaCatalogoCasoModel != null) {
            //getDetalleCaso(activity, String.valueOf(guardaCatalogoCasoModel.getIdCaso()));
            //}

        }
        return view;
    }

    public void refereciasConInterface(View view) {

        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        textViewDenunciaDDF = view.findViewById(R.id.textViewDenunciaDDF);
        textViewDenunciaDDF.setOnClickListener(this);

        textViewResponsablesDDF = view.findViewById(R.id.textViewResponsablesDDF);
        textViewResponsablesDDF.setOnClickListener(this);

        textViewFasesDDF = view.findViewById(R.id.textViewFasesDDF);
        textViewFasesDDF.setOnClickListener(this);

        textViewDocumentoDDF = view.findViewById(R.id.textViewDocumentoDDF);
        textViewDocumentoDDF.setOnClickListener(this);

    }

    public void getDetalleCaso(Activity activity, String idCaso) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(this.activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getDetalleDenuncia().getExito().equals(Constantes.exitoTrue)) {
                            if (respuestaGeneral.getDetalleDenuncia() != null || !respuestaGeneral.getDetalleDenuncia().equals("")) {
                                detalleDenuncia = respuestaGeneral.getDetalleDenuncia();
                            }

                            if (/*respuestaGeneral.getDetalleDenuncia().getListFases() != null || */!respuestaGeneral.getDetalleDenuncia().getListFases().isEmpty()) {
                                listDetalleFases.addAll(respuestaGeneral.getDetalleDenuncia().getListFases());
                            }

                            if (respuestaGeneral.getDetalleDenuncia().getLisResponsables() != null || !respuestaGeneral.getDetalleDenuncia().getLisResponsables().isEmpty()) {
                                listDetallaResponsables.addAll(respuestaGeneral.getDetalleDenuncia().getLisResponsables());
                            }

                            if (respuestaGeneral.getDetalleDenuncia().getListDocumentos() != null || !respuestaGeneral.getDetalleDenuncia().getListDocumentos().isEmpty()) {
                                listDetalleDocumentos.addAll(respuestaGeneral.getDetalleDenuncia().getListDocumentos());
                            }

                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getDetalleDenuncia().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerDetalleCaso.concat(Constantes.signoInterrogacion)
                        .concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso).concat(Constantes.signoAnd).concat(Constantes.idTipoApp).concat(Constantes.signoIgual).concat("2"));

            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getObtenerConfiguracionTipoAppMovil(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguraciontipoAppMovil = respuestaGeneral.getConfiguracionData().getValor();
                            descripcionConfiguraciontipoAppMovil = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.tipoAppMovil));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getObtenerDocumento(Activity activity, int idDocumento, int valorDeConfiguraciontipoAppMovil) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new ObtenerDocumentos(idDocumento, valorDeConfiguraciontipoAppMovil));
                //                                                                  idTipoDenuncia  IdUdN  IdTipoFraude  IdAbogado  IdEtapaCaso  Nombre  Descripcion  Importe  MontoRecuperado  FechaReporte  IdRegion   listResponsables

                new AsyncTaskGral(this.activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGenerall = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGenerall.getDetalleDocumento().getExito().equals(Constantes.exitoTrue)) {
                            String tipoArchivo = respuestaGenerall.getDetalleDocumento().getTipoArchivo();
                            if (tipoArchivo.equals(".doc") || tipoArchivo.equals("doc") || tipoArchivo.equals(".docx") || tipoArchivo.equals("docx")) {
                                //Documento word que se descarga
                                documentoWord(activity, respuestaGenerall.getDetalleDocumento().getStringArchivo(), respuestaGenerall.getDetalleDocumento().getDescripcion(), respuestaGenerall.getDetalleDocumento().getTipoArchivo());
                            } else if (tipoArchivo.equals(".pdf") || tipoArchivo.equals("pdf") || tipoArchivo.equals(".png") || tipoArchivo.equals(".jpg") || tipoArchivo.equals(".jpeg") || tipoArchivo.equals("png") || tipoArchivo.equals("jpg") || tipoArchivo.equals("jpeg")) {
                                // docmuentos pdf ,
                                showZoomImage(activity, String.valueOf(respuestaGenerall.getDetalleDocumento().getStringArchivo()), respuestaGenerall.getDetalleDocumento().getTipoArchivo(), respuestaGenerall.getDetalleDocumento().getDescripcion());
                            }
                        } else {
                            Utils.messageShort(activity, respuestaGenerall.getDetalleDocumento().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerDocumento, params);//.concat(Constantes.signoInterrogacion)
                //  .concat(Constantes.idDocumento).concat(Constantes.signoIgual).concat(String.valueOf(idDocumento)).concat(Constantes.signoAnd).concat(Constantes.idTipoApp).concat(Constantes.signoIgual).concat("2"));

            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewDenunciaDDF:
                showDialogDetalleDenuncia(activity, detalleDenuncia);
                break;

            case R.id.textViewFasesDDF:
                if (!listDetalleFases.isEmpty()) {
                    showDialogDetalleFases(activity, listDetalleFases);
                } else {
                    Utils.messageShort(activity, "No se cuentran fases avanzadas");
                }
                break;

            case R.id.textViewResponsablesDDF:
                if (!listDetallaResponsables.isEmpty()) {
                    showDialogDetalleResponsables(activity, listDetallaResponsables);
                } else {
                    Utils.messageShort(activity, "No se encuentran imputados registrados");
                }
                break;

            case R.id.textViewDocumentoDDF:
                if (!listDetalleDocumentos.isEmpty()) {
                    showDialogDetalleDocumentos(activity, listDetalleDocumentos);
                } else {
                    Utils.messageShort(activity, "No se encuentran documentos almacenados");
                }

                break;
            default:
                break;
        }
    }

    public void showDialogDetalleDenuncia(Activity activity, DetalleDenuncia detalleDenuncia) {
        Dialog dialogDetalleDenuncia = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleDenuncia.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleDenuncia.setCancelable(false);
        dialogDetalleDenuncia.setContentView(R.layout.dialog_detalle_denuncia);
        Objects.requireNonNull(dialogDetalleDenuncia.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewFolioDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewFolioDenunciaDDD);
        TextView textViewNombreDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewNombreDenunciaDDD);
        TextView textViewColorEtapaDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewColorEtapaDenunciaDDD);
        TextView textViewFaseDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewFaseDenunciaDDD);
        TextView textViewColorFaseDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewColorFaseDenunciaDDD);
        TextView textViewFechaReporteDDD = dialogDetalleDenuncia.findViewById(R.id.textViewFechaReporteDDD);
        TextView textViewFechaCompromisoDDD = dialogDetalleDenuncia.findViewById(R.id.textViewFechaCompromisoDDD);
        TextView textViewNombreAbogadoResponsableDDD = dialogDetalleDenuncia.findViewById(R.id.textViewNombreAbogadoResponsableDDD);
        TextView textViewNombreUnidadNegocioDDD = dialogDetalleDenuncia.findViewById(R.id.textViewNombreUnidadNegocioDDD);
        TextView textViewCecoUnidadNegocioDDD = dialogDetalleDenuncia.findViewById(R.id.textViewCecoUnidadNegocioDDD);
        TextView textViewTipoFraudeDDD = dialogDetalleDenuncia.findViewById(R.id.textViewTipoFraudeDDD);
        TextView textViewImporteDDD = dialogDetalleDenuncia.findViewById(R.id.textViewImporteDDD);
        TextView textViewMontoRecuperadoDDD = dialogDetalleDenuncia.findViewById(R.id.textViewMontoRecuperadoDDD);
        TextView textViewZonaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewZonaDDD);
        TextView textViewRegionDDD = dialogDetalleDenuncia.findViewById(R.id.textViewRegionDDD);
        TextView textViewDatosDemandaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewDatosDemandaDDD);
        TextView textViewDatosAgenciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewDatosAgenciaDDD);
        TextView textViewDescripcionDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewDescripcionDenunciaDDD);
        TextView textViewAvanceDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewAvanceDenunciaDDD);
        TextView textViewCerrarDDD = dialogDetalleDenuncia.findViewById(R.id.textViewCerrarDDD);
        TextView textViewSubFaseDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewSubFaseDenunciaDDD);
        TextView textViewColorSubFaseDenunciaDDD = dialogDetalleDenuncia.findViewById(R.id.textViewColorSubFaseDenunciaDDD);

        if (detalleDenuncia.getFolio() != null) {
            textViewFolioDenunciaDDD.setText(detalleDenuncia.getFolio());
        } else {
            textViewFolioDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getNombre() != null) {
            textViewNombreDenunciaDDD.setText(detalleDenuncia.getNombre());
        } else {
            textViewNombreDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getTipoFraude() != null) {
            textViewTipoFraudeDDD.setText(detalleDenuncia.getTipoFraude());
        } else {
            textViewTipoFraudeDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getImporte() != null) {
            textViewImporteDDD.setText(Constantes.signoPesos.concat(Utils.setFormatoNumeroDecimalDinero(detalleDenuncia.getImporte())));
        } else {
            textViewImporteDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getMontoRecuperado() != null) {
            textViewMontoRecuperadoDDD.setText(Constantes.signoPesos.concat(Utils.setFormatoNumeroDecimalDinero(detalleDenuncia.getMontoRecuperado())));
        } else {
            textViewMontoRecuperadoDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getUdN() != null) {
            textViewNombreUnidadNegocioDDD.setText(detalleDenuncia.getUdN());
        } else {
            textViewNombreUnidadNegocioDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getUdNCeco() != null) {
            textViewCecoUnidadNegocioDDD.setText(detalleDenuncia.getUdNCeco());
        } else {
            textViewCecoUnidadNegocioDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getAbogado() != null && detalleDenuncia.getIdAbogado() != null) {
            textViewNombreAbogadoResponsableDDD.setText(detalleDenuncia.getIdAbogado().concat(" - ").concat(detalleDenuncia.getAbogado()));
        } else {
            textViewNombreAbogadoResponsableDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getZona() != null) {
            textViewZonaDDD.setText(detalleDenuncia.getZona());
        } else {
            textViewZonaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getRegion() != null) {
            textViewRegionDDD.setText(detalleDenuncia.getRegion());
        } else {
            textViewRegionDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getEtapaCaso() != null) {
            textViewColorEtapaDenunciaDDD.setText(detalleDenuncia.getEtapaCaso());
            textViewColorEtapaDenunciaDDD.setBackground(Utils.cambiarColorTextView(detalleDenuncia.getColorEtapaCaso()));
        } else {
            textViewColorEtapaDenunciaDDD.setTextColor(activity.getColor(R.color.dark));
            textViewColorEtapaDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
            textViewColorEtapaDenunciaDDD.setBackgroundColor(Color.TRANSPARENT);
        }

        if (detalleDenuncia.getAvanceCaso() != null) {
            textViewAvanceDenunciaDDD.setText(Utils.setFormatoNumeroEnteroPorcentaje(detalleDenuncia.getAvanceCaso()));
        } else {
            textViewAvanceDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getFase() != null) {
            textViewFaseDenunciaDDD.setText(detalleDenuncia.getFase());
        } else {
            textViewFaseDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getEtapaFase() != null && detalleDenuncia.getColorFase() != null) {
            textViewColorFaseDenunciaDDD.setBackground(Utils.cambiarColorTextView(detalleDenuncia.getColorFase()));
            textViewColorFaseDenunciaDDD.setText(detalleDenuncia.getEtapaFase());
        } else {
            textViewColorFaseDenunciaDDD.setVisibility(View.GONE);
            //textViewColorFaseDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
            //textViewColorFaseDenunciaDDD.setBackgroundColor(Color.TRANSPARENT);
        }

        if (detalleDenuncia.getSubFase() != null) {
            //textViewSubFaseDenunciaDDD.setText(detalleDenuncia.getSubFase());
        } else {
            textViewSubFaseDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getEtapaSubFase() != null && detalleDenuncia.getColorSubFase() != null) {
            //textViewColorSubFaseDenunciaDDD.setBackground(Utils.cambiarColorTextView(detalleDenuncia.getColorSubFase()));
            //textViewColorSubFaseDenunciaDDD.setText(detalleDenuncia.getEtapaSubFase());
        } else {
            textViewColorSubFaseDenunciaDDD.setVisibility(View.GONE);
            //textViewColorSubFaseDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
            //textViewColorSubFaseDenunciaDDD.setBackgroundColor(Color.TRANSPARENT);
        }

        if (detalleDenuncia.getFechaRegistro() != null) {
            textViewFechaReporteDDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenuncia.getFechaRegistro()));
        } else {
            textViewFechaReporteDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getFechaCompromiso() != null) {
            textViewFechaCompromisoDDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenuncia.getFechaCompromiso()));
        } else {
            textViewFechaCompromisoDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getDatosDemanda() != null) {
            textViewDatosDemandaDDD.setText(detalleDenuncia.getDatosDemanda());
        } else {
            textViewDatosDemandaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getDatosAgencia() != null) {
            textViewDatosAgenciaDDD.setText(detalleDenuncia.getDatosAgencia());
        } else {
            textViewDatosAgenciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        if (detalleDenuncia.getDescripcion() != null) {
            textViewDescripcionDenunciaDDD.setText(detalleDenuncia.getDescripcion());
        } else {
            textViewDescripcionDenunciaDDD.setText(getString(R.string.text_label_no_aplica));
        }

        textViewCerrarDDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleDenuncia.dismiss();
            }
        });

        dialogDetalleDenuncia.show();
    }

    public void showDialogDetalleFases(Activity activity, List<DetalleDenunciaFase> listDetalleFases) {
        Dialog dialogDetalleFases = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleFases.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleFases.setCancelable(false);
        dialogDetalleFases.setContentView(R.layout.dialog_detalle_denuncia_fases);
        Objects.requireNonNull(dialogDetalleFases.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarDDDF = dialogDetalleFases.findViewById(R.id.textViewCerrarDDDF);
        RecyclerView recyclerViewResponsablesDDDF = dialogDetalleFases.findViewById(R.id.recyclerViewResponsablesDDDF);

        DetalleDenunciaFasesAdapter detalleDenunciaFasesAdapter = new DetalleDenunciaFasesAdapter(activity, listDetalleFases, new DetalleDenunciaFasesAdapter.OnListener() {
            @Override
            public void onItemClick(DetalleDenunciaFase detalleDenunciaFase, int position, String imagenString, String tipoArchivo) {

            }

            @Override
            public void onClickDetalleDocumentos(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDocumento> documentos) {

            }

            @Override
            public void onClickDetalleSubfases(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDenunciaSubFase> listSubfases) {
                if (/*listSubfases != null || */!listSubfases.isEmpty()) {
                    showDialogDetalleSubFases(activity, listSubfases);
                } else {
                    Utils.messageShort(DetalleDelCasoFragment.this.activity, "No se cuenta con sub fases");
                }
            }

            @Override
            public void onClickDetalleReprogramaciones(DetalleDenunciaFase detalleDenunciaFase, int positio, List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones) {
                if (/*listSubfases != null || */!listReprogramaciones.isEmpty()) {
                    showDialogDetalleReprogramaciones(activity, listReprogramaciones);
                } else {
                    Utils.messageShort(DetalleDelCasoFragment.this.activity, "No se cuenta con reprogramaciones");
                }

            }
        });
        recyclerViewResponsablesDDDF.setHasFixedSize(false);
        recyclerViewResponsablesDDDF.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResponsablesDDDF.setAdapter(detalleDenunciaFasesAdapter);
        recyclerViewResponsablesDDDF.setNestedScrollingEnabled(false);

        textViewCerrarDDDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleFases.dismiss();
            }
        });

        dialogDetalleFases.show();
    }

    public void showDialogDetalleSubFases(Activity activity, List<DetalleDenunciaSubFase> listDetalleSubfase) {
        Dialog dialogDetalleResponsables = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleResponsables.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleResponsables.setCancelable(false);
        dialogDetalleResponsables.setContentView(R.layout.dialog_detalle_denuncia_fases);
        Objects.requireNonNull(dialogDetalleResponsables.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarDDDF = dialogDetalleResponsables.findViewById(R.id.textViewCerrarDDDF);
        TextView textViewTituloDDDF = dialogDetalleResponsables.findViewById(R.id.textViewTituloDDDF);
        textViewTituloDDDF.setText("Subfases");
        RecyclerView recyclerViewResponsablesDDDF = dialogDetalleResponsables.findViewById(R.id.recyclerViewResponsablesDDDF);

        DetalleDenunciaSubfasesAdapter detalleDenunciaSubfasesAdapter = new DetalleDenunciaSubfasesAdapter(activity, listDetalleSubfase, new DetalleDenunciaSubfasesAdapter.OnListener() {
            @Override
            public void onItemClick(DetalleDenunciaFase detalleDenunciaFase, int position, String imagenString, String tipoArchivo) {

            }
        });
        recyclerViewResponsablesDDDF.setHasFixedSize(false);
        recyclerViewResponsablesDDDF.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResponsablesDDDF.setAdapter(detalleDenunciaSubfasesAdapter);
        recyclerViewResponsablesDDDF.setNestedScrollingEnabled(false);

        textViewCerrarDDDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleResponsables.dismiss();
                //showDialogDetalleFases(activity, listDetalleFases);
            }
        });

        dialogDetalleResponsables.show();
    }

    public void showDialogDetalleReprogramaciones(Activity activity, List<DetalleDenunciaFaseReprogramaciones> detalleDenunciaReprogramaciones) {
        Dialog dialogDetalleResponsables = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleResponsables.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleResponsables.setCancelable(false);
        dialogDetalleResponsables.setContentView(R.layout.dialog_detalle_denuncia_fases);
        Objects.requireNonNull(dialogDetalleResponsables.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarDDDF = dialogDetalleResponsables.findViewById(R.id.textViewCerrarDDDF);
        TextView textViewTituloDDDF = dialogDetalleResponsables.findViewById(R.id.textViewTituloDDDF);
        textViewTituloDDDF.setText("Reprogramaciones");
        RecyclerView recyclerViewResponsablesDDDF = dialogDetalleResponsables.findViewById(R.id.recyclerViewResponsablesDDDF);

        DetalleDenunciaReprogramadasAdapter detalleDenunciaReprogramadasAdapter = new DetalleDenunciaReprogramadasAdapter(activity, detalleDenunciaReprogramaciones, new DetalleDenunciaReprogramadasAdapter.OnListener() {
            @Override
            public void onItemClick(DetalleDenunciaFaseReprogramaciones detalleDenunciaFaseReprogramaciones, int position) {
                if (detalleDenunciaFaseReprogramaciones.getIdDocAdjunto() != null) {
                    if (!detalleDenunciaFaseReprogramaciones.getIdDocAdjunto().equals(0)) {
                        getObtenerDocumento(activity, detalleDenunciaFaseReprogramaciones.getIdDocAdjunto(), Integer.parseInt(valorDeConfiguraciontipoAppMovil));
                    } else {
                        Utils.messageShort(DetalleDelCasoFragment.this.activity, "Esta respogramación no cuenta con documento");
                    }

                } else {
                    Utils.messageShort(DetalleDelCasoFragment.this.activity, "No se cuenta con documentos en reprogramaciones");
                }
            }
        });

        recyclerViewResponsablesDDDF.setHasFixedSize(false);
        recyclerViewResponsablesDDDF.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResponsablesDDDF.setAdapter(detalleDenunciaReprogramadasAdapter);
        recyclerViewResponsablesDDDF.setNestedScrollingEnabled(false);

        textViewCerrarDDDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleResponsables.dismiss();
                //showDialogDetalleFases(activity, listDetalleFases);
            }
        });

        dialogDetalleResponsables.show();
    }

    public void showDialogDetalleResponsables(Activity activity, List<DetalleDenunciaResponsables> listDenunciaDetallaResponsables) {
        Dialog dialogDetalleResponsables = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleResponsables.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleResponsables.setCancelable(false);
        dialogDetalleResponsables.setContentView(R.layout.dialog_detalle_denuncia_responsables);
        Objects.requireNonNull(dialogDetalleResponsables.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarDDDR = dialogDetalleResponsables.findViewById(R.id.textViewCerrarDDDR);
        RecyclerView recyclerViewResponsablesDDDR = dialogDetalleResponsables.findViewById(R.id.recyclerViewResponsablesDDDR);

        DetalleEmpleadosResponsablesAdapter totalEmpleadosResponsablesDCAadpter = new DetalleEmpleadosResponsablesAdapter(activity, listDenunciaDetallaResponsables);
        recyclerViewResponsablesDDDR.setHasFixedSize(false);
        recyclerViewResponsablesDDDR.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResponsablesDDDR.setAdapter(totalEmpleadosResponsablesDCAadpter);
        recyclerViewResponsablesDDDR.setNestedScrollingEnabled(false);

        textViewCerrarDDDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleResponsables.dismiss();
            }
        });

        dialogDetalleResponsables.show();
    }

    public void showDialogDetalleDocumentos(Activity activity, List<DetalleDocumento> listDetalleDocumentos) {

        Dialog dialogDetalleResponsables = new Dialog(activity, R.style.CustomDialogTheme);
        dialogDetalleResponsables.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDetalleResponsables.setCancelable(false);
        dialogDetalleResponsables.setContentView(R.layout.dialog_detalle_denuncia_fases);
        Objects.requireNonNull(dialogDetalleResponsables.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarDDDF = dialogDetalleResponsables.findViewById(R.id.textViewCerrarDDDF);
        TextView textViewTituloDDDF = dialogDetalleResponsables.findViewById(R.id.textViewTituloDDDF);
        textViewTituloDDDF.setText("Documentos");
        RecyclerView recyclerViewResponsablesDDDF = dialogDetalleResponsables.findViewById(R.id.recyclerViewResponsablesDDDF);

        DetalleDenunciaDocumentosAdapter detalleDenunciaDocumentosAdapter = new DetalleDenunciaDocumentosAdapter(activity, listDetalleDocumentos, new DetalleDenunciaDocumentosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DetalleDocumento detalleDocumento, int position, String imagenString, String tipoArchivo) {
                //dialogDetalleResponsables.dismiss();
                getObtenerDocumento(activity, detalleDocumento.getId(), Integer.parseInt(valorDeConfiguraciontipoAppMovil));
            }
        });
        recyclerViewResponsablesDDDF.setHasFixedSize(false);
        recyclerViewResponsablesDDDF.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResponsablesDDDF.setAdapter(detalleDenunciaDocumentosAdapter);
        recyclerViewResponsablesDDDF.setNestedScrollingEnabled(false);

        textViewCerrarDDDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetalleResponsables.dismiss();
            }
        });

        dialogDetalleResponsables.show();
    }


    //TODO: este es el dialog para poder visualizar la imagen de manera grande
    public void showZoomImage(Activity activity, String imageDecodableString, String tipoArchivo, String nombre) {
        Dialog dialogAdjuntarDocumentos = new Dialog(activity, R.style.CustomDialogTheme);
        dialogAdjuntarDocumentos.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAdjuntarDocumentos.setCancelable(false);
        dialogAdjuntarDocumentos.setContentView(R.layout.dialog_zoom_image);
        Objects.requireNonNull(dialogAdjuntarDocumentos.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Button buttonCerrarDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonCerrarDZI);
        Button buttonEliminarEvidenciaDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonEliminarEvidenciaDZI);
        buttonEliminarEvidenciaDZI.setVisibility(View.GONE);
        ImageView imageViewViewImageDZI = dialogAdjuntarDocumentos.findViewById(R.id.imageViewViewImageDZI);
        PDFView pdfView = dialogAdjuntarDocumentos.findViewById(R.id.pdfView);

        if (tipoArchivo.equals(".png") || tipoArchivo.equals(".jpg") || tipoArchivo.equals(".jpeg") || tipoArchivo.equals("png") || tipoArchivo.equals("jpg") || tipoArchivo.equals("jpeg")) {
            pdfView.setVisibility(View.GONE);
            imageViewViewImageDZI.setVisibility(View.VISIBLE);
            if (imageDecodableString != null) {
                try {
                    String imagenDecompres = Utils.decompressBase64(imageDecodableString);
                    Bitmap imagenSinBase64 = Utils.base64ToBitmap(imagenDecompres);
                    Glide.with(activity).load(/*Utils.rotateImage(*/imagenSinBase64/*, -90)*/).fitCenter().into(imageViewViewImageDZI);
                    //Glide.with(activity).load(Utils.rotateImage(Utils.base64ToBitmap(imageDecodableString), -90)).fitCenter().into(imageViewViewImageDZI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (tipoArchivo.equals(".pdf") || tipoArchivo.equals("pdf")) {
            pdfView.setVisibility(View.VISIBLE);
            imageViewViewImageDZI.setVisibility(View.GONE);
            try {
                String path = Environment.getExternalStorageDirectory() + "/sifra/";
                Utils.createScanFolder(path);
                String documentoDecompres = Utils.decompressBase64(imageDecodableString);
                File file;
                if (tipoArchivo.equals(".pdf")) {
                    file = Utils.base64ToFile(path.concat(nombre).concat(tipoArchivo), documentoDecompres);
                } else {
                    file = Utils.base64ToFile(path.concat(nombre).concat(".").concat(tipoArchivo), documentoDecompres);
                }
                pdfView.fromFile(file).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buttonCerrarDZI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdjuntarDocumentos.dismiss();
            }
        });

        dialogAdjuntarDocumentos.show();
    }

    public void documentoWord(Activity activity, String archivoCompress, String nombreArchivo, String tipoArchivo) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/sifra/";
        Utils.createScanFolder(path);
        String documentoDecompres = null;
        try {
            documentoDecompres = Utils.decompressBase64(archivoCompress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = null;
        try {
            file = Utils.base64ToFile(path.concat(nombreArchivo).concat(".").concat(tipoArchivo), documentoDecompres);
        } catch (IOException e) {
            e.printStackTrace();
        }

        abrirDocumentoWord(activity, file);
    }

    public void abrirDocumentoWord(Activity activity, File url) {

        //Uri uri = Uri.fromFile(url);
        Uri uri = FileProvider.getUriForFile(activity, "com.auditorias.fuerzasespeciales" + ".fileprovider", url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        /*if (url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        } else*/
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if (url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(Intent.createChooser(intent, "Selecciona app de imagen"), Constantes.RESPUESTA_DE_ALMACENAMIENTO);

    }
}
