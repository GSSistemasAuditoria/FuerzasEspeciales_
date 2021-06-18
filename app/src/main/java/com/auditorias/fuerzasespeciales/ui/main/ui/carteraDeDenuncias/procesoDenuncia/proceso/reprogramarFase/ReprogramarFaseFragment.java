package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.reprogramarFase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.Serial;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.request.DatosFecha;
import com.auditorias.fuerzasespeciales.request.SolicitudRequest;
import com.auditorias.fuerzasespeciales.request.denuncia.DatosDenunciaRequest;
import com.auditorias.fuerzasespeciales.request.envioRequest;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.auditorias.fuerzasespeciales.request.reprogramacion.CalculoFechaCompromiso;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters.GaleriaFotosAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ReprogramarFaseFragment extends Fragment implements View.OnClickListener {

    //TODO:todas los listas que se pueden utilizar en el fragmento
    private final List<Documentos> documentos = new ArrayList<>();
    private final List<String> listGaleriaFotos = new ArrayList<>();
    private final ArrayList<Documentos> listDocumentosSelectos = new ArrayList<>();

    private TextView textViewSubTiutuloCST;

    private LinearLayout linearLayoutColorEtapaDenunciaCDD;
    private TextView textViewFolioDenunciaCDD;
    private TextView textViewNombreDenunciaCDD;
    private TextView textViewPorcentajeGeneralDenunciaCDD;
    private TextView textViewTipoDelitoCDD;
    private TextView textViewTipoDenunciaCDD;
    private TextView textViewUnidadNegocioCDD;
    private TextView textViewFechaResgistroCDD;
    private TextView textViewFechaCompromisoFaseTextCDD;
    private TextView textViewFechaCompromisoFaseCDD;
    private TextView textViewZonaCDD;
    private TextView textViewAutorizacionTextCDD;
    private TextView textViewAutorizacionCDD;

    private TextView textViewFaseEtapaCDF;
    private TextView textViewFaseEtapaColorCDF;
    private TextView textViewFechaCompromisoCDF;

    private TextInputEditText textInputEditTextMotivoSolicitidRFF;

    private ImageView imageViewDocumentoCAE;
    private TextView textViewDocumentoCAE;
    private ImageView imageViewVerDocumentoCAE;

    private ImageView imageViewCamaraCAE;
    private TextView textViewCamaraCAE;
    private ImageView imageViewVerCamaraCAE;

    private ImageView imageViewGaleriaCAE;
    private TextView textViewGaleriaCAE;
    private ImageView imageViewVerGaleriaCAE;

    private LinearLayout linearLayoutAdvertenciaFotosGTFF;
    private ImageView imagenViewAdvertenciaFotosGTFF;
    private GaleriaFotosAdapter galeriaFotosAdapter;
    private String imagen;

    private Integer valorDeConfiguracionPhotoNumber;
    private String descripcionConfiguracionPhotoNumber;

    private String[] parts;
    private String doc;
    private String docx;
    private String pdf;
    private String valorDeConfiguracionFormatDocuments;
    private String descripcionConfiguracionFormatDocuments;

    private String valorDeConfiguraciontipoAppMovil = "";
    private String descripcionConfiguraciontipoAppMovil;

    private String valorDeConfiguracionFormatImages;
    private String descripcionConfiguracionFormatImages;

    private String justificacion = "";
    //TODO: variable estring para almacenar la variable de la direcciion de la foto que se tomo con la camara
    private String mPath = "";
    //TODO: variables que se utilizan en el  fragment
    private Bundle args;
    private String idCasoGeneral;
    private String idCasoFase = "";
    private String idFase = "";

    private String fechaCompromiso;

    private String stringBase64Documento;
    private String stringCompressDocumento;

    private Bitmap bitmapImageFoto;
    private String extension;
    private Uri uriImagenOrPdf;
    private String nombreDeArchivoFoto;
    private String nombreFoto;
    private String tamanioPDFFotos = "";
    private String tamanio;

    private Double valorDeConfiguracionFileMaxSize;
    private String descripcionConfiguracionFileMaxSize;
    private int banderaFotos = 0;

    //TODO: son todos los view del fragment
    private View view;
    //TODO: es el context del fragment
    private Context context;
    //TODO: es el activity del fragment
    private Activity activity;


    public ReprogramarFaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBoolean(IS_EDITING_KEY, isEditing);
        outState.putString("justificacion", justificacion);
        //outState.putParcelableArray("listCasosAbogado", listCasosAbogado);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_reprogramar_fase, container, false);
        activity = getActivity();
        context = getContext();
        //TODO: es el fragmentmanager del fragment
        //FragmentManager fragmentManager = getFragmentManager();

        refereciasConInterface(view);
        ocultarElementos();

        args = getArguments();
        if (args != null) {
            idCasoGeneral = args.getString("idCaso");
            nombreDeArchivoFoto = args.getString("nombrePDFFotos");
            tamanioPDFFotos = args.getString("tamanioPDFFotos");
            extension = args.getString("extenciionPDFFotos");
            valorDeConfiguraciontipoAppMovil = args.getString("tipoAppMovil");
            mPath = args.getString("uriStringPDFPDFFotos");
            justificacion = args.getString("justificacion");
            //textInputEditTextMotivoSolicitidRFF.setText(args.getString("justificacion"));
        }

        if (mPath != null) {
            imageViewVerDocumentoCAE.setVisibility(View.GONE);
            imageViewVerCamaraCAE.setVisibility(View.VISIBLE);
            imageViewVerGaleriaCAE.setVisibility(View.GONE);
            banderaFotos = 2;
            try {
                File file = new File(mPath);
                stringBase64Documento = Utils.fileToBase64(activity, Uri.fromFile(file));
                stringCompressDocumento = Utils.compressBase64(stringBase64Documento);
                if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                    documentos.add(new Documentos(nombreDeArchivoFoto, extension, Integer.parseInt(tamanioPDFFotos), stringBase64Documento));
                } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                    documentos.add(new Documentos(nombreDeArchivoFoto, extension, Integer.parseInt(tamanioPDFFotos), stringCompressDocumento));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            stringsToNull();
            banderaFotos = 0;
        }

        getDatosCasos(activity, Integer.parseInt(idCasoGeneral));
        getObtenerConfiguracionFormatImages(activity);
        getObtenerConfiguracionFileMaxSize(activity);
        getObtenerConfiguracionFormatDocuments(activity);
        getObtenerConfiguracionTipoAppMovil(activity);
        getObtenerConfiguracionPhotoNumber(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //para versiones con android 10.0 o superior.
            if ((activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (activity.checkSelfPermission(WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                activity.requestPermissions(new String[]{Manifest.permission.CAMERA, WRITE_EXTERNAL_STORAGE}, Constantes.RESPUESTA_DE_CAMARA);
            }
        } else {
            //para versiones inferiores a android 10.0.
            myRequestStoragePermission(activity);
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
        textViewTipoDenunciaCDD = view.findViewById(R.id.textViewTipoDenunciaCDD);
        textViewUnidadNegocioCDD = view.findViewById(R.id.textViewUnidadNegocioCDD);
        textViewFechaResgistroCDD = view.findViewById(R.id.textViewFechaResgistroCDD);
        textViewFechaCompromisoFaseTextCDD = view.findViewById(R.id.textViewFechaCompromisoFaseTextCDD);
        textViewFechaCompromisoFaseCDD = view.findViewById(R.id.textViewFechaCompromisoFaseCDD);
        textViewZonaCDD = view.findViewById(R.id.textViewZonaCDD);
        textViewAutorizacionTextCDD = view.findViewById(R.id.textViewAutorizacionTextCDD);
        textViewAutorizacionCDD = view.findViewById(R.id.textViewAutorizacionCDD);

        textViewFaseEtapaCDF = view.findViewById(R.id.textViewFaseEtapaCDF);
        textViewFaseEtapaColorCDF = view.findViewById(R.id.textViewFaseEtapaColorCDF);
        textViewFechaCompromisoCDF = view.findViewById(R.id.textViewFechaCompromisoCDF);

        textInputEditTextMotivoSolicitidRFF = view.findViewById(R.id.textInputEditTextMotivoSolicitidRFF);

        imageViewDocumentoCAE = view.findViewById(R.id.imageViewDocumentoCAE);
        imageViewDocumentoCAE.setOnClickListener(this);
        textViewDocumentoCAE = view.findViewById(R.id.textViewDocumentoCAE);
        textViewDocumentoCAE.setOnClickListener(this);
        imageViewVerDocumentoCAE = view.findViewById(R.id.imageViewVerDocumentoCAE);
        imageViewVerDocumentoCAE.setOnClickListener(this);

        imageViewCamaraCAE = view.findViewById(R.id.imageViewCamaraCAE);
        imageViewCamaraCAE.setOnClickListener(this);
        textViewCamaraCAE = view.findViewById(R.id.textViewCamaraCAE);
        textViewCamaraCAE.setOnClickListener(this);
        imageViewVerCamaraCAE = view.findViewById(R.id.imageViewVerCamaraCAE);
        imageViewVerCamaraCAE.setOnClickListener(this);

        imageViewGaleriaCAE = view.findViewById(R.id.imageViewGaleriaCAE);
        imageViewGaleriaCAE.setOnClickListener(this);
        textViewGaleriaCAE = view.findViewById(R.id.textViewGaleriaCAE);
        textViewGaleriaCAE.setOnClickListener(this);
        imageViewVerGaleriaCAE = view.findViewById(R.id.imageViewVerGaleriaCAE);
        imageViewVerGaleriaCAE.setOnClickListener(this);

        Button buttonReprogramarRFF = view.findViewById(R.id.buttonReprogramarRFF);
        buttonReprogramarRFF.setOnClickListener(this);
    }

    public void ocultarElementos() {
        textViewPorcentajeGeneralDenunciaCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseCDD.setVisibility(View.GONE);
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);
        imageViewVerDocumentoCAE.setVisibility(View.GONE);
        imageViewVerCamaraCAE.setVisibility(View.GONE);
        imageViewVerGaleriaCAE.setVisibility(View.GONE);
    }

    private void getDatosCasos(Activity activity, int idCaso) {
        try {
            Gson gsonParams = new Gson();
            String params = gsonParams.toJson(new DatosDenunciaRequest(idCaso));
            //                                                         idCaso
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    Serial serial = gson.fromJson(result, Serial.class);
                    if (serial.getDatosCasoModel().getExito().equals(Constantes.exitoTrue)) {
                        getDatosDenuncia(serial.getDatosCasoModel());
                        idCasoGeneral = String.valueOf(serial.getDatosCasoModel().getId());
                        idFase = String.valueOf(serial.getDatosCasoModel().getIdFase());
                        idCasoFase = String.valueOf(serial.getDatosCasoModel().getIdCasoFase());

                        try {
                            fechaCompromiso = Utils.cambiarFechayyyyMMdd(serial.getDatosCasoModel().getFechaCompromiso());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
                //}, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerDatosCaso.concat(Constantes.signoInterrogacion).concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso));
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.ObtenerDatosCaso, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDatosDenuncia(DatosDenuncia datosDenuncia) {
        if (datosDenuncia.getSubFase() != null) {
            textViewSubTiutuloCST.setText(getString(R.string.text_label_reprogramacion).concat(" - ").concat(datosDenuncia.getSubFase()));
        } else {
            textViewSubTiutuloCST.setText(getString(R.string.text_label_reprogramacion).concat(" - ").concat(datosDenuncia.getFase()));
        }

        linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(datosDenuncia.getColorEtapaCaso()));
        textViewFolioDenunciaCDD.setText(datosDenuncia.getFolio());
        textViewNombreDenunciaCDD.setText(datosDenuncia.getNombre());
        textViewTipoDelitoCDD.setText(datosDenuncia.getTipoFraude());
        textViewTipoDenunciaCDD.setText(datosDenuncia.getTipoDenuncia());
        textViewUnidadNegocioCDD.setText(datosDenuncia.getUdN().concat(" - ").concat(datosDenuncia.getUdNCeco()));
        textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(datosDenuncia.getFechaRegistro())));
        textViewZonaCDD.setText(datosDenuncia.getRegion().concat(" - ").concat(datosDenuncia.getZona()));

        if (datosDenuncia.getSubFase() != null) {
            textViewFaseEtapaCDF.setText(datosDenuncia.getSubFase());
        } else {
            textViewFaseEtapaCDF.setText(datosDenuncia.getFase());
        }

        if (datosDenuncia.getEtapaSubFase() != null){
            textViewFaseEtapaColorCDF.setText(datosDenuncia.getEtapaSubFase());
        }else {
            textViewFaseEtapaColorCDF.setText(datosDenuncia.getEtapaFase());
        }

        if (datosDenuncia.getColorSubFase() != null){
            textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(datosDenuncia.getColorSubFase()));
        }else {
            textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(datosDenuncia.getColorFase()));
        }

        textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(datosDenuncia.getFechaCompromiso())));

    }

    private void getObtenerConfiguracionFileMaxSize(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionFileMaxSize = (double) Utils.getByteToMegas(Long.parseLong(respuestaGeneral.getConfiguracionData().getValor()));
                            descripcionConfiguracionFileMaxSize = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat("FileMaxSize"));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getObtenerConfiguracionFormatDocuments(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionFormatDocuments = respuestaGeneral.getConfiguracionData().getValor().replace("|", " ");
                            parts = valorDeConfiguracionFormatDocuments.split(" ");
                            doc = parts[0];
                            docx = parts[1];
                            pdf = parts[2];
                            descripcionConfiguracionFormatDocuments = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.formatImages));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getObtenerConfiguracionFormatImages(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionFormatImages = respuestaGeneral.getConfiguracionData().getValor().replace("|", " ");
                            //parts = valorDeConfiguracionFormatImages.split(" ");
                            descripcionConfiguracionFormatImages = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.formatImages));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
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

    private void getObtenerConfiguracionPhotoNumber(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionPhotoNumber = Integer.parseInt(respuestaGeneral.getConfiguracionData().getValor());
                            descripcionConfiguracionPhotoNumber = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.photoNumber));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewDocumentoCAE:
            case R.id.textViewDocumentoCAE:
                if (stringBase64Documento != null) {
                    showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 2, v);
                } else {
                    //cargarArchivo(doc, docx, pdf);
                    cargarArchivo();
                }
                break;

            case R.id.imageViewCamaraCAE:
            case R.id.textViewCamaraCAE:
                justificacion = Objects.requireNonNull(textInputEditTextMotivoSolicitidRFF.getText()).toString().trim();
                if (stringBase64Documento != null) {
                    showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 3, v);
                } else {
                /*    documentos.clear();
                    Bundle bundle = new Bundle();
                    bundle.putString("idCaso", idCasoGeneral);
                    bundle.putString("idCasoFase", idCasoFase);
                    bundle.putString("navigationFragment", "reprogramadas");
                    bundle.putString("justificacion", justificacion);
                    bundle.putString("tipoAppMovil", valorDeConfiguraciontipoAppMovil);
                    Navigation.findNavController(v).navigate(R.id.action_navigation_reprogramadas_prentacion_de_demanda_fragment_to_navigation_geleria_tomar_fotos_fragment, bundle);
                */
                    showDialogfotos(activity);
                }
                break;

            case R.id.imageViewGaleriaCAE:
            case R.id.textViewGaleriaCAE:
                if (stringBase64Documento != null) {
                    showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 1, v);
                } else {
                    cargarImagenGaleria("png");
                }
                break;

            case R.id.imageViewVerDocumentoCAE:
            case R.id.imageViewVerCamaraCAE:
            case R.id.imageViewVerGaleriaCAE:

                if (/*extension.equals(doc) || extension.equals(docx)*/ extension.equals(".docx") || extension.equals("docx") || extension.equals(".doc") || extension.equals("doc")) {
                    //verDocumentos(activityTPDF, mPath);
                    File file = new File(mPath);
                    abrirDocumentoWord(activity, file);
                } else {
                    showZoomImage(activity, stringBase64Documento, mPath, extension);
                }
                break;

            case R.id.buttonReprogramarRFF:
                justificacion = Objects.requireNonNull(textInputEditTextMotivoSolicitidRFF.getText()).toString().trim();
                if (idCasoGeneral.isEmpty()) {
                    Utils.messageShort(activity, getString(R.string.text_label_id_caso));
                } else if (String.valueOf(idCasoFase).isEmpty()) {
                    Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
                } else if (justificacion.isEmpty()) {
                    textInputEditTextMotivoSolicitidRFF.setError(getString(R.string.text_label_la_justificacion_esta_vacia));
                    textInputEditTextMotivoSolicitidRFF.requestFocus();
                } else if (justificacion.length() <= 9) {
                    textInputEditTextMotivoSolicitidRFF.setError(getString(R.string.text_label_la_justificacion_es_menor_a_10_caracteres));
                    textInputEditTextMotivoSolicitidRFF.requestFocus();
                } else  {
                    if (Functions.isNetworkAvailable(activity)) {
                        showAlertDialogReprogramcionPresentacion(activity, getString(R.string.text_label_reprogramacion),
                                getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                String.valueOf(idCasoGeneral), Integer.parseInt(idFase), Integer.parseInt(idCasoFase), valorDeConfiguraciontipoAppMovil, fechaCompromiso, justificacion, documentos);
                    } else {
                        Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
                    }
                }
                break;

            default:
                break;
        }
    }

    public void showAlertDialogSeleccionarEvidencia(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, int tipoDocumento, View view) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                imageViewVerDocumentoCAE.setVisibility(View.GONE);
                imageViewVerCamaraCAE.setVisibility(View.GONE);
                imageViewVerGaleriaCAE.setVisibility(View.GONE);
                stringsToNull();
                if (tipoDocumento == 1) {
                    cargarImagenGaleria("png");
                } else if (tipoDocumento == 2) {
                    cargarArchivo();
                } else if (tipoDocumento == 3) {
                    listDocumentosSelectos.clear();
                    showDialogfotos(activity);
                    /*Bundle bundle = new Bundle();
                    bundle.putString("idCaso", idCasoGeneral);
                    bundle.putString("navigationFragment", "reprogramadas");
                    bundle.putString("tipoAppMovil", valorDeConfiguraciontipoAppMovil);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_cerrar_fase_fragment_to_navigation_geleria_tomar_fotos_fragment, bundle);*/
                }

            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void showAlertDialogReprogramcionPresentacion(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje,
                                                         String idCaso, int idFase, int idCasoFase, String tipoApp, String fechaCompromiso, String justificacion, List<Documentos> documentos) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                setCalculaFechaCompromiso(activity, idCaso, idFase, idCasoFase, tipoApp, fechaCompromiso, justificacion, documentos);
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void setCalculaFechaCompromiso(Activity activity, String idCaso, int idFase, int idCasoFase, String tipoApp, String fechaCompromiso, String justificacion, List<Documentos> documentos) {

        if (Functions.isNetworkAvailable(activity)) {
            Gson gsonParams = new Gson();
            String params = gsonParams.toJson(new CalculoFechaCompromiso(new DatosFecha(idFase, fechaCompromiso)));
            try {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);
                        if (serial.getCalculaFechaCompromisoModel().getExito().equals(Constantes.exitoTrue)) {
                            setReprogramarDenuncia(activity, idCaso, idCasoFase, tipoApp, justificacion, serial.getCalculaFechaCompromisoModel().getFechaCompromisoNueva(), documentos);
                        } else {
                            Utils.messageShort(activity, serial.getCalculaFechaCompromisoModel().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.calculaFechaCompromiso, params).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    public void setReprogramarDenuncia(Activity activity, String idCaso, int idCasoFase, String tipoApp, String justificacion, String nuevaFechaCompromiso, List<Documentos> documentos) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new envioRequest(Integer.parseInt(tipoApp), new SolicitudRequest(idCasoFase, justificacion, nuevaFechaCompromiso), documentos));
                //String params = gsonParams.toJson(new IniciarFaceRequest(2, new FaseRequest(idCasoFase), listResposablesPresentacion, documentos));
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);

                        if (serial.getNuevaSolicitudCambioFechaModel().getExito().equals(Constantes.exitoTrue)) {//Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(serial.getNuevaSolicitudCambioFechaModel().getFechaCompromisoNueva())
                            showDialogReprogramacionExito(activity, getString(R.string.title_reprogramar_presentacion_de_denuncia), getString(R.string.text_label_se_ha_soliciatdo_reprogramacion_exitosamente_para_el_dia) + Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(serial.getNuevaSolicitudCambioFechaModel().getFechaCompromisoNueva())), getString(R.string.text_label_aceptar), idCaso);
                        } else {
                            Utils.message(activity, serial.getNuevaSolicitudCambioFechaModel().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.nuevaSolicitudCambioFecha, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialogReprogramacionExito(Activity activity, String titulo, String mensaje, String butonText, String idCaso) {
        Dialog dialogTipoEmpleado = new Dialog(activity, R.style.CustomDialogTheme);
        dialogTipoEmpleado.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTipoEmpleado.setCancelable(false);
        dialogTipoEmpleado.setContentView(R.layout.dialog_error_conexion_internet);
        Objects.requireNonNull(dialogTipoEmpleado.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //ImageView imageViewLogoDRCI = dialogTipoEmpleado.findViewById(R.id.imageViewLogoDRCI);
        TextView textViewTituloDECI = dialogTipoEmpleado.findViewById(R.id.textViewTituloDECI);
        textViewTituloDECI.setText(titulo);
        TextView textViewMensageDECI = dialogTipoEmpleado.findViewById(R.id.textViewMensageDECI);
        textViewMensageDECI.setText(mensaje);
        Button buttonIntentarDECI = dialogTipoEmpleado.findViewById(R.id.buttonIntentarDECI);
        buttonIntentarDECI.setText(butonText);

        buttonIntentarDECI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTipoEmpleado.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                Navigation.findNavController(ReprogramarFaseFragment.this.view).navigate(R.id.action_navigation_reprogramadas_prentacion_de_demanda_fragment_to_navigation_proceso_fase_del_caso_fragment, bundle);
            }
        });

        dialogTipoEmpleado.show();
    }

    public void abrirDocumentoWord(Activity activity, File url) {

        //Uri uri = Uri.fromFile(url);
        Uri uri = FileProvider.getUriForFile(activity, "com.auditorias.fuerzasespeciales" + ".fileprovider", url);
        Intent intent = new Intent(Intent.ACTION_VIEW);

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

    //TODO: este es el dialog para poder visualizar la imagen de manera grande
    public void showZoomImage(Activity activity, String documentoEnBase64, String pathDocumento, String extension) {
        Dialog dialogAdjuntarDocumentos = new Dialog(activity, R.style.CustomDialogTheme);
        dialogAdjuntarDocumentos.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAdjuntarDocumentos.setCancelable(false);
        dialogAdjuntarDocumentos.setContentView(R.layout.dialog_zoom_image);
        Objects.requireNonNull(dialogAdjuntarDocumentos.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Button buttonCerrarDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonCerrarDZI);
        Button buttonEliminarEvidenciaDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonEliminarEvidenciaDZI);
        ImageView imageViewViewImageDZI = dialogAdjuntarDocumentos.findViewById(R.id.imageViewViewImageDZI);
        PDFView pdfView = dialogAdjuntarDocumentos.findViewById(R.id.pdfView);

        if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
            pdfView.setVisibility(View.GONE);
            imageViewViewImageDZI.setVisibility(View.VISIBLE);
            if (documentoEnBase64 != null) {
                Glide.with(activity).load(Utils.base64ToBitmap(documentoEnBase64)).fitCenter().into(imageViewViewImageDZI);
            }
        } else if (extension.equals(".pdf") || extension.equals("pdf")) {
            pdfView.setVisibility(View.VISIBLE);
            imageViewViewImageDZI.setVisibility(View.GONE);
            if (banderaFotos == 1) {
                File file = new File(String.valueOf(pathDocumento));
                pdfView.fromFile(file).load();
            } else {
                File file = new File(pathDocumento);
                pdfView.fromFile(file).load();
            }
        }

        buttonCerrarDZI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdjuntarDocumentos.dismiss();
            }
        });

        buttonEliminarEvidenciaDZI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogAdjuntarDocumentos.dismiss();
                showAlertDialogEliminarEvidencia(activity, getString(R.string.text_label_liminar_evidencia), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), dialogAdjuntarDocumentos);

            }
        });
        dialogAdjuntarDocumentos.show();
    }

    public void showAlertDialogEliminarEvidencia(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, Dialog dialog) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialog.dismiss();
                stringsToNull();
                ocultarElementos();
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //if (resultCode != Activity.RESULT_CANCELED && data.getData() != null) {
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == Constantes.RESPUESTA_DE_GALERIA) {
                uriImagenOrPdf = data.getData();

                String pruba = getAbsolutePath(data.getData());
                Log.i("onActivityResult", "onActivityResult: " + pruba);
                if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {

                    mPath = uriImagenOrPdf.getPath();
                    try {
                        bitmapImageFoto = Utils.getUriToBitmapImagen(activity, uriImagenOrPdf);
                        //bitmapImageFoto = Utils.cambiarPosicionImage(bitmapImageFoto);
                        uriImagenOrPdf = Utils.resizeImage(activity, bitmapImageFoto);

                        nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
                        extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);

                        stringBase64Documento = Utils.bitmapToBase64(bitmapImageFoto, extension);
                        stringCompressDocumento = Utils.compressBase64(stringBase64Documento);

                        if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                            documentos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64Documento));
                        } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                            documentos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumento));
                        }
                        //documentos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activityRPDDF, uriImagenOrPdf)), stringBase64DocumentoImganen));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageViewVerGaleriaCAE.setVisibility(View.VISIBLE);
                    imageViewVerCamaraCAE.setVisibility(View.GONE);
                    imageViewVerDocumentoCAE.setVisibility(View.GONE);
                } else {
                    stringsToNull();
                    Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                }
            } else if (requestCode == Constantes.RESPUESTA_DE_ALMACENAMIENTO) {
                uriImagenOrPdf = data.getData();
                if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {

                    mPath = Utils.getPath(activity, uriImagenOrPdf);
                    try {
                        nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
                        extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
                        stringBase64Documento = Utils.fileToBase64(activity, uriImagenOrPdf);
                        stringCompressDocumento = Utils.compressBase64(stringBase64Documento);
                        if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                            documentos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64Documento));
                        } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                            documentos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumento));
                        }
                        //documentos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activityRPDDF, uriImagenOrPdf)), stringBase64DocumentoImganen));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    banderaFotos = 1;
                    imageViewVerGaleriaCAE.setVisibility(View.GONE);
                    imageViewVerCamaraCAE.setVisibility(View.GONE);
                    imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);

                } else {
                    stringsToNull();
                    Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                    banderaFotos = 0;
                }
            }else if (requestCode == Constantes.RESPUESTA_DE_CAMARA) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    galleryAddPic();
                    setPic();
                } else {
/*                    MediaScannerConnection.scanFile(activity,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });

                    Bitmap bitmapImageFoto = BitmapFactory.decodeFile(mPath);

                    linearLayoutAdvertenciaFotosGTFF.setVisibility(View.GONE);

                    try {
                        uriImagenOrPdf = Utils.resizeImage(activity, bitmapImageFoto);
                        imagen = Utils.fileToBase64(activity, uriImagenOrPdf);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    galeriaFotosAdapter.getList().add(imagen);
                    galeriaFotosAdapter.notifyDataSetChanged();*/
                }
            }
        }
    }

    public void stringsToNull() {
        uriImagenOrPdf = null;
        mPath = null;
        nombreDeArchivoFoto = null;
        extension = null;
        stringBase64Documento = null;
        documentos.clear();
    }

    public String getAbsolutePath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    /*public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }*/


    public void cargarImagenGaleria(String extensionPng) {
        Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        String png = null;
        if (extensionPng.equals("png") || extensionPng.equals(".png")) {
            png = "image/png";
        }
        String[] mimetypes = {png, "image/jpg", "image/jpeg"};
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        startActivityForResult(Intent.createChooser(intent, "Selecciona el archivo"), Constantes.RESPUESTA_DE_GALERIA);
    }

    public void cargarArchivo() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        String[] mimetypes = {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/pdf"};
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        startActivityForResult(Intent.createChooser(intent, "Selecciona el archivo"), Constantes.RESPUESTA_DE_ALMACENAMIENTO);
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

    public void showDialogfotos(Activity activity) {
        Dialog dialog = new Dialog(activity, R.style.CustomDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_galeria_tomar_fotos);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCloseDGTF = dialog.findViewById(R.id.textViewCloseDGTF);
        linearLayoutAdvertenciaFotosGTFF = dialog.findViewById(R.id.linearLayoutAdvertenciaFotosGTFF);
        imagenViewAdvertenciaFotosGTFF = dialog.findViewById(R.id.imagenViewAdvertenciaFotosGTFF);

        ImageButton buttonAgregarFotoGTFF = dialog.findViewById(R.id.buttonAgregarFotoGTFF);
        buttonAgregarFotoGTFF.setOnClickListener(this);

        ImageButton buttonGuardarPDFGTFF = dialog.findViewById(R.id.buttonGuardarPDFGTFF);
        buttonGuardarPDFGTFF.setOnClickListener(this);

        RecyclerView recyclerViewGaleria = dialog.findViewById(R.id.recyclerViewGaleria);
        galeriaFotosAdapter = new GaleriaFotosAdapter(activity, listGaleriaFotos, new GaleriaFotosAdapter.OnListener() {
            @Override
            public void onItemSelectedListener(String fotoString, int posicion) {
                showAlertDialogEliminarEnvidencia(activity, getString(R.string.text_label_liminar_evidencia), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), posicion);
            }
        });
        recyclerViewGaleria.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new GridLayoutManager(activity, 3);
        recyclerViewGaleria.setLayoutManager(layoutManagerCategory);
        recyclerViewGaleria.setNestedScrollingEnabled(false);
        recyclerViewGaleria.setAdapter(galeriaFotosAdapter);

        buttonAgregarFotoGTFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGaleriaFotos.size() < valorDeConfiguracionPhotoNumber) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        dispatchTakePictureIntent();
                    } else {
                        //para versiones inferiores a android 10.0.
                        //openCamera(activity);
                    }

                } else {
                    Utils.messageShort(activity, descripcionConfiguracionPhotoNumber);
                }
            }
        });

        buttonGuardarPDFGTFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listGaleriaFotos.isEmpty() || listGaleriaFotos == null) {
                    Utils.messageShort(activity, getString(R.string.text_label_falta_tomar_una_foto));
                } else {
                    GeneratePDF(dialog);
                }
            }
        });

        textViewCloseDGTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGaleriaFotos.clear();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri imagenUri = FileProvider.getUriForFile(activity, "com.auditorias.fuerzasespeciales.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
                startActivityForResult(takePictureIntent, Constantes.RESPUESTA_DE_CAMARA);
            }
        }
    }

    private File createImageFile() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
        Date date = new Date();
        nombreFoto = dateFormat.format(date);
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(nombreFoto, ".jpg", storageDir);
        mPath = image.getAbsolutePath();
        //currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void GeneratePDF(Dialog dialog) {
        if (write()) {
            dialog.dismiss();
            Toast.makeText(activity, "Archivo PDF creado correctamente", Toast.LENGTH_LONG).show();
            File file = new File(mPath);
            try {
                stringBase64Documento = Utils.fileToBase64(activity, Uri.fromFile(file));
                stringCompressDocumento = Utils.compressBase64(stringBase64Documento);
                if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, extension, Integer.parseInt(tamanio), stringBase64Documento));
                } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, extension, Integer.parseInt(tamanio), stringCompressDocumento));
                }
                listGaleriaFotos.clear();
                //if (idSubFase != null) {
                //    if (idSubFase.equals("1")) {
                //        textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                //        recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                //        documentosInicioSubfaseAdapter.notifyDataSetChanged();
                //    } else /*if (idSubFase.equals("2"))*/ {
                        imageViewVerGaleriaCAE.setVisibility(View.GONE);
                        imageViewVerCamaraCAE.setVisibility(View.VISIBLE);
                        imageViewVerDocumentoCAE.setVisibility(View.GONE);
                //    }
                //}/* else {

                //}*/
/*                if (idSubFase.equals("1")) {
                    textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                    recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                    documentosInicioSubfaseAdapter.notifyDataSetChanged();
                } else if (idSubFase.equals("2")) {
                    imageViewVerGaleriaCAE.setVisibility(View.GONE);
                    imageViewVerCamaraCAE.setVisibility(View.VISIBLE);
                    imageViewVerDocumentoCAE.setVisibility(View.GONE);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(activity, getString(R.string.text_label_no_se_creo_el_archivo_pdf), Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean write() {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
            Date date = new Date();
            nombreFoto = dateFormat.format(date);

            String path = Environment.getExternalStorageDirectory() + "/sifra/";

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File newFile = new File(dir, nombreFoto + ".pdf");
            Document document = new Document();

            if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(String.valueOf(newFile.length()))) {

                if (listGaleriaFotos != null || listGaleriaFotos.size() != 0) {

                    PdfWriter.getInstance(document, new FileOutputStream(newFile.getAbsoluteFile()));
                    document.open();

                    for (int i = 0; i < listGaleriaFotos.size(); i++) {
                        Image foto = Image.getInstance(Base64.decode(listGaleriaFotos.get(i), Base64.NO_WRAP));
                        foto.scaleAbsolute(500, 350);

                        document.add(foto);
                        document.add(new Phrase(Chunk.NEWLINE));
                    }
                    document.close();
                    nombreDeArchivoFoto = Utils.getNombreDocumentos(String.valueOf(newFile));
                    extension = "pdf";
                    tamanio = String.valueOf(newFile.length());
                    mPath = newFile.getPath();

                } else {
                    document.close();
                    Utils.messageShort(activity, "No hay fotos");
                }

            } else {
                document.close();
                Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mPath);
        uriImagenOrPdf = Uri.fromFile(f);
        mediaScanIntent.setData(uriImagenOrPdf);
        activity.sendBroadcast(mediaScanIntent);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setPic() {
        float targetW = imagenViewAdvertenciaFotosGTFF.getWidth();
        float targetH = imagenViewAdvertenciaFotosGTFF.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        float scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = (int) scaleFactor;
        bmOptions.inPurgeable = true;

        linearLayoutAdvertenciaFotosGTFF.setVisibility(View.GONE);
        Bitmap bitmap = null;
        try {
            bitmap = Utils.getBitmap(activity, uriImagenOrPdf);
            uriImagenOrPdf = Utils.resizeImage(activity, Utils.cambiarPosicionImage(bitmap));
            imagen = Utils.fileToBase64(activity, uriImagenOrPdf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        galeriaFotosAdapter.getList().add(imagen);
        galeriaFotosAdapter.notifyDataSetChanged();
    }

    public void showAlertDialogEliminarEnvidencia(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if (!listGaleriaFotos.isEmpty()) {
                    listGaleriaFotos.remove(position);
                    galeriaFotosAdapter.notifyDataSetChanged();
                }
            }
        });
        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }


}