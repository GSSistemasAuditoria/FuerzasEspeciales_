package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.terminarFase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.request.DocumentoRequest;
import com.auditorias.fuerzasespeciales.request.FaseRequest;
import com.auditorias.fuerzasespeciales.request.IniciaCasoRequest;
import com.auditorias.fuerzasespeciales.request.ResponsablesPresentacionRequest;
import com.auditorias.fuerzasespeciales.request.denuncia.DatosDenunciaRequest;
import com.auditorias.fuerzasespeciales.request.envioRequest;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.terminarFase.adapters.EstatusResponsableFaseCerrarAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CerrarFaseFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = CerrarFaseFragment.class.getName();

    //TODO:todas los listas que se pueden utilizar en el fragmento
    private final List<EstatusResponsableFase> listEstatusResponsableFase = new ArrayList<>();


    private final List<DocumentoRequest> listDocumentosSelectos = new ArrayList<>();
    private final List<ResponsablesPresentacionRequest> listNuevoEstatusResposables = new ArrayList<>();
    int idFase;
    //TODO:todas los adapters que se pueden utilizar en el fragmento
    private EstatusResponsableFaseCerrarAdapter estatusResponsableFaseCerrarAdapter;
    //TODO: variable estring para almacenar la variable de la direcciion de la foto que se tomo con la camara
    private String mPath = "";
    private String idCaso;
    //private String nombreFase = "";
    //private String fechaCompromiso;
    private String idCasoFase = "";
    //TODO: son todos los textview del fragment
    private TextView textViewFolioDenunciaCDD;
    private TextView textViewNombreDenunciaCDD;
    private TextView textViewFechaCompromisoFaseTextCDD;
    private TextView textViewFechaCompromisoCasoCDD;
    private TextView textViewFechaResgistroCDD;
    private TextView textViewUnidadNegocioCDD;
    private TextView textViewZonaCDD;
    private TextView textViewPorcentajeGeneralDenunciaCDD;
    private TextView textViewTipoDelitoCDD;
    private TextView textViewAutorizacionCDD;
    private TextView textViewAutorizacionTextCDD;
    private TextView textViewDocumentoCAE;
    private TextView textViewCamaraCAE;
    private TextView textViewGaleriaCAE;
    private TextView textViewTotalImputadosCLI;
    private TextView textViewMostrarOcultarCLI;
    private TextView textViewFaseEtapaCDF;
    private TextView textViewFaseEtapaColorCDF;
    private TextView textViewFechaCompromisoCDF;
    //private TextView textViewDatosDenunciaCDF;
    //private TextView textViewDatosAgenciaCDF;
    private TextView textViewAdjuntarEvidenciaCAE;
    private TextView textViewSubTiutuloCST;
    //TODO: son todos los Imageview del fragment
    private ImageView imageViewDocumentoCAE;
    private ImageView imageViewCamaraCAE;
    private ImageView imageViewGaleriaCAE;
    private ImageView imageViewVerDocumentoCAE;
    private ImageView imageViewVerFotosCAE;
    private ImageView imageViewVerImagenesCAE;
    //TODO: son todos los botones del fragment
    private Button buttonCerraFaseCFF;
    //TODO: son todos los Linearlayout del fragment
    private LinearLayout linearLayoutColorEtapaDenunciaCDD;
    //TODO: son todos los recyclerview del fragment
    private RecyclerView recyclerViewListaImputadosCLI;
    //TODO: son todos los view del fragment
    private View view;
    private View custumDatosDenuciaAndDatosAgenciaIFF;
    //TODO: es el context del fragment
    private Context context;
    //TODO: es el activity del fragment
    private Activity activity;
    //TODO: es el fragmentmanager del fragment
    private FragmentManager fragmentManager;
    //TODO: todo referente a los dtaos de tranferencia de fragments
    private Bundle args;
    //TODO: son todas las varibles de datos
    private String stringBase64DocumentoImganen;
    private String stringCompressDocumentoImagen;
    private Bitmap bitmapImageFoto;
    private String extension = "";
    private Uri uriImagenOrPdf;
    private String nombreDeArchivoFoto;
    //private String currentPhotoPath;
    private Double valorDeConfiguracionFileMaxSize;
    private String descripcionConfiguracionFileMaxSize;
    private String nombrePDFFotos = "";
    private String tamanioPDFFotos = "";
    private String extenciionPDFFotos = "";
    private String stringBase64PDFFotos = "";
    private String uriStringPDFPDFFotos = "";
    private String[] partsDocs;
    private String doc;
    private String docx;
    private String pdf;
    private String[] partsImg;
    private String png;
    private String jpg;
    private String jpeg;
    private String valorDeConfiguracionFormatDocuments;
    private String descripcionConfiguracionFormatDocuments;
    private int banderaFotos = 0;
    private String valorDeConfiguraciontipoAppMovil = "";
    private String descripcionConfiguraciontipoAppMovil;
    private String valorDeConfiguracionFormatImages;
    private String descripcionConfiguracionFormatImages;
    private int mostrarListaImputados = 0;
    private TextInputEditText textInputEditTextDatosDenunciaCDDDA;
    private TextInputEditText textInputEditTextDatosAgenciaCDDDA;

    public CerrarFaseFragment() {
        // Required empty public constructor
    }

    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            //Log.i("onActivityResult", "onActivityResult: uri" + uri.toString());
            //Log.i("onActivityResult", "onActivityResult: authority" + uri.getAuthority());
            //Log.i("onActivityResult", "onActivityResult: path" + uri.getPath());

            // ExternalStorageProvider
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Log.i("onActivityResult", "onActivityResult: path" + "getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);
                //System.out.println("getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);

                // This is for checking Main Memory
                if ("primary".equalsIgnoreCase(type)) {
                    if (split.length > 1) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1] + "/";
                    } else {
                        return Environment.getExternalStorageDirectory() + "/";
                    }
                    // This is for checking SD Card
                } else if ("home".equalsIgnoreCase(type)) {
                    return /*"storage"*/ Environment.getExternalStorageDirectory() + "/" + "document/" + docId.replace(":", "/");
                }

            }
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBoolean(IS_EDITING_KEY, isEditing);
        outState.putString("valorDeConfiguraciontipoAppMovil", valorDeConfiguraciontipoAppMovil);
        //outState.putParcelableArray("listCasosAbogado", listCasosAbogado);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cerrar_fase, container, false);
        activity = getActivity();
        context = getContext();

        refereciasConInterface(view);
        ocultarElementos();

        textViewAdjuntarEvidenciaCAE.setText(getString(R.string.text_label_adjuntar_evidencia));

        args = getArguments();
        if (args != null) {
            idCaso = args.getString("idCaso");
            idCasoFase = args.getString("idCasoFase");
            nombreDeArchivoFoto = args.getString("nombrePDFFotos");
            tamanioPDFFotos = args.getString("tamanioPDFFotos");
            extension = args.getString("extenciionPDFFotos");
            mPath = args.getString("uriStringPDFPDFFotos");
            valorDeConfiguraciontipoAppMovil = args.getString("tipoAppMovil");
        }

        if (mPath != null) {
            imageViewVerImagenesCAE.setVisibility(View.GONE);
            imageViewVerFotosCAE.setVisibility(View.VISIBLE);
            imageViewVerDocumentoCAE.setVisibility(View.GONE);
            banderaFotos = 2;
            try {
                File file = new File(mPath);
                stringBase64DocumentoImganen = Utils.fileToBase64(activity, Uri.fromFile(file));
                stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);
                if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                    listDocumentosSelectos.add(new DocumentoRequest(nombreDeArchivoFoto, extension, Integer.parseInt(tamanioPDFFotos), stringBase64DocumentoImganen));
                } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                    listDocumentosSelectos.add(new DocumentoRequest(nombreDeArchivoFoto, extension, Integer.parseInt(tamanioPDFFotos), stringCompressDocumentoImagen));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            stringsToNull();
            banderaFotos = 0;
        }

        getEstatusImputadoFase(activity);
        getDatosDelCaso(activity, Integer.parseInt(idCaso));
        getObtenerConfiguracionFormatImages(activity);
        getObtenerConfiguracionFileMaxSize(activity);
        getObtenerConfiguracionFormatDocuments(activity);
        getObtenerConfiguracionTipoAppMovil(activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //para versiones con android 10.0 o superior.
            /*if ((activityTPDF.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (activityTPDF.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                activityTPDF.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constantes.RESPUESTA_DE_CAMARA);
            }*/
            if ((ActivityCompat.checkSelfPermission(activity, CAMERA) != PackageManager.PERMISSION_GRANTED) &&
                    (ActivityCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                    (ActivityCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(activity, new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, Constantes.MIS_PERMISOS);
            }
        } else {
            //para versiones inferiores a android 10.0.
            //myRequestStoragePermission(activityTPDF);
        }

        return view;
    }

    public void refereciasConInterface(View view) {

        textViewFolioDenunciaCDD = view.findViewById(R.id.textViewFolioDenunciaCDD);
        textViewNombreDenunciaCDD = view.findViewById(R.id.textViewNombreDenunciaCDD);
        textViewFechaCompromisoFaseTextCDD = view.findViewById(R.id.textViewFechaCompromisoFaseTextCDD);
        textViewFechaCompromisoCasoCDD = view.findViewById(R.id.textViewFechaCompromisoFaseCDD);
        textViewFechaResgistroCDD = view.findViewById(R.id.textViewFechaResgistroCDD);
        textViewPorcentajeGeneralDenunciaCDD = view.findViewById(R.id.textViewPorcentajeGeneralDenunciaCDD);
        textViewUnidadNegocioCDD = view.findViewById(R.id.textViewUnidadNegocioCDD);
        textViewZonaCDD = view.findViewById(R.id.textViewZonaCDD);
        linearLayoutColorEtapaDenunciaCDD = view.findViewById(R.id.linearLayoutColorEtapaDenunciaCDD);
        textViewTipoDelitoCDD = view.findViewById(R.id.textViewTipoDelitoCDD);
        textViewAutorizacionCDD = view.findViewById(R.id.textViewAutorizacionCDD);
        textViewAutorizacionTextCDD = view.findViewById(R.id.textViewAutorizacionTextCDD);
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        recyclerViewListaImputadosCLI = view.findViewById(R.id.recyclerViewListaImputadosCLI);
        textViewTotalImputadosCLI = view.findViewById(R.id.textViewTotalImputadosCLI);
        textViewAdjuntarEvidenciaCAE = view.findViewById(R.id.textViewAdjuntarEvidenciaCAE);
        textViewFaseEtapaCDF = view.findViewById(R.id.textViewFaseEtapaCDF);
        textViewFaseEtapaColorCDF = view.findViewById(R.id.textViewFaseEtapaColorCDF);
        textViewFechaCompromisoCDF = view.findViewById(R.id.textViewFechaCompromisoCDF);

        textInputEditTextDatosDenunciaCDDDA = view.findViewById(R.id.textInputEditTextDatosDenunciaCDDDA);
        textInputEditTextDatosAgenciaCDDDA = view.findViewById(R.id.textInputEditTextDatosAgenciaCDDDA);
        custumDatosDenuciaAndDatosAgenciaIFF = view.findViewById(R.id.custumDatosDenuciaAndDatosAgenciaIFF);

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

        imageViewVerFotosCAE = view.findViewById(R.id.imageViewVerCamaraCAE);
        imageViewVerFotosCAE.setOnClickListener(this);

        imageViewGaleriaCAE = view.findViewById(R.id.imageViewGaleriaCAE);
        imageViewGaleriaCAE.setOnClickListener(this);

        textViewGaleriaCAE = view.findViewById(R.id.textViewGaleriaCAE);
        textViewGaleriaCAE.setOnClickListener(this);

        imageViewVerImagenesCAE = view.findViewById(R.id.imageViewVerGaleriaCAE);
        imageViewVerImagenesCAE.setOnClickListener(this);

        buttonCerraFaseCFF = view.findViewById(R.id.buttonCerraFaseCFF);
        buttonCerraFaseCFF.setOnClickListener(this);

        textViewMostrarOcultarCLI = view.findViewById(R.id.textViewMostrarOcultarCLI);
        textViewMostrarOcultarCLI.setOnClickListener(this);
    }

    //TODO: los elementos que se ocultan al iniciar el fragment
    public void ocultarElementos() {
        textViewPorcentajeGeneralDenunciaCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
        textViewFechaCompromisoCasoCDD.setVisibility(View.GONE);
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);
        imageViewVerImagenesCAE.setVisibility(View.GONE);
        imageViewVerFotosCAE.setVisibility(View.GONE);
        imageViewVerDocumentoCAE.setVisibility(View.GONE);
        recyclerViewListaImputadosCLI.setVisibility(View.GONE);
        custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);
    }

    public void getDatosDelCaso(Activity activity, int idCaso) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new DatosDenunciaRequest(idCaso));
                //                                                         idCaso
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getDatosDenuncia().getExito().equals(Constantes.exitoTrue)) {
                            datosDenuncia(respuestaGeneral.getDatosDenuncia());
                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getDatosDenuncia().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                //}, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerDatosCaso.concat(Constantes.signoInterrogacion).concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso));
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.ObtenerDatosCaso, params);
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void datosDenuncia(DatosDenuncia datosDenuncia) {
        idCaso = String.valueOf(datosDenuncia.getId());
        idCasoFase = String.valueOf(datosDenuncia.getIdCasoFase());

        if (datosDenuncia.getIdSubFase() != null) {
            textViewSubTiutuloCST.setText(getString(R.string.title_cierre).concat(" - ").concat(datosDenuncia.getSubFase()));
        } else {
            textViewSubTiutuloCST.setText(getString(R.string.title_cierre).concat(" - ").concat(datosDenuncia.getFase()));
        }

        linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(datosDenuncia.getColorEtapaCaso()));
        textViewFolioDenunciaCDD.setText(datosDenuncia.getFolio());
        textViewNombreDenunciaCDD.setText(datosDenuncia.getNombre());
        textViewUnidadNegocioCDD.setText(datosDenuncia.getUdN());
        textViewZonaCDD.setText(datosDenuncia.getRegion());
        textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(datosDenuncia.getFechaRegistro())));

        textViewFaseEtapaCDF.setText(datosDenuncia.getFase());
        textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(datosDenuncia.getFechaCompromiso())));
        textViewFechaCompromisoCDF.setTextColor(activity.getColor(R.color.green_secondary));
        textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(datosDenuncia.getColorEtapaCaso()));
        textViewFaseEtapaColorCDF.setText(datosDenuncia.getEtapaFase());
        textViewTotalImputadosCLI.setText(String.valueOf(datosDenuncia.getTotalResponsables()));
        textViewTipoDelitoCDD.setText(datosDenuncia.getTipoFraude());
        if (datosDenuncia.getIdFase().equals(2)) {
            idFase = datosDenuncia.getIdFase();
            custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.VISIBLE);
            textInputEditTextDatosDenunciaCDDDA.setText(datosDenuncia.getDatosDemanda());
            textInputEditTextDatosAgenciaCDDDA.setText(datosDenuncia.getDatosAgencia());
        } else {
            custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);
        }

        estatusResponsableFaseCerrarAdapter = new EstatusResponsableFaseCerrarAdapter(activity, datosDenuncia.getListResponsables(), fragmentManager, listEstatusResponsableFase, new EstatusResponsableFaseCerrarAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int idCasoResponsable, int idStatusResponsable) {
                if (idStatusResponsable != 0) {
                    listNuevoEstatusResposables.add(new ResponsablesPresentacionRequest(idCasoFase, idCasoResponsable, idStatusResponsable));
                    //parametros para la lista que se envia                             IdCasoFase  IdCasoResponsable  IdStatusResponsable
                }
            }
        });
        recyclerViewListaImputadosCLI.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
        recyclerViewListaImputadosCLI.setLayoutManager(layoutManagerCategory);
        recyclerViewListaImputadosCLI.setNestedScrollingEnabled(false);
        recyclerViewListaImputadosCLI.setAdapter(estatusResponsableFaseCerrarAdapter);
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
                        listEstatusResponsableFase.addAll(respuestaGeneral.getLisEstatusResponsableFase());
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
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.fileMaxSize));
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
                            partsDocs = valorDeConfiguracionFormatDocuments.split(" ");
                            if (partsDocs[0] != null) {
                                if (partsDocs[0].equals("doc") || partsDocs[0].equals(".doc")) {
                                    doc = partsDocs[0];
                                }
                            }

                            if (partsDocs[1] != null) {
                                if (partsDocs[1].equals("docx") || partsDocs[1].equals(".docx")) {
                                    docx = partsDocs[1];
                                }
                            }

                            if (partsDocs[2] != null) {
                                if (partsDocs[2].equals("pdf") || partsDocs[2].equals(".pdf")) {
                                    pdf = partsDocs[2];
                                }
                            }

                            descripcionConfiguracionFormatDocuments = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.formatDocuments));
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
                            partsImg = valorDeConfiguracionFormatImages.split(" ");
                            for (int x = 0; x < partsImg.length; x++) {
                                if (partsImg[x].equals("png") || partsImg[x].equals(".png")) {
                                    png = partsImg[x];
                                }

                                if (partsImg[x].equals("jpg") || partsImg[x].equals(".jpg")) {
                                    jpg = partsImg[x];
                                }

                                if (partsImg[x].equals("jpeg") || partsImg[x].equals(".jpeg")) {
                                    jpeg = partsImg[x];
                                }
                            }
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.textViewMostrarOcultarCLI:
                if (mostrarListaImputados == 0) {
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_ocultar));
                    recyclerViewListaImputadosCLI.setVisibility(View.VISIBLE);
                    mostrarListaImputados = 1;
                } else {
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_mostrar));
                    recyclerViewListaImputadosCLI.setVisibility(View.GONE);
                    mostrarListaImputados = 0;
                }
                break;

            case R.id.imageViewDocumentoCAE:
            case R.id.textViewDocumentoCAE:
                if (stringBase64DocumentoImganen != null) {
                    showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 2, v);
                } else {
                    cargarArchivo();
                }
                break;

            case R.id.imageViewCamaraCAE:
            case R.id.textViewCamaraCAE:
                if (stringBase64DocumentoImganen != null) {
                    showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 3, v);
                } else {
                    listDocumentosSelectos.clear();
                    Bundle bundle = new Bundle();
                    bundle.putString("idCaso", idCaso);
                    bundle.putString("navigationFragment", "terminar");
                    bundle.putString("tipoAppMovil", valorDeConfiguraciontipoAppMovil);
                    Navigation.findNavController(v).navigate(R.id.action_navigation_terminar_presentacion_denuncia_fragment_to_navigation_geleria_tomar_fotos_fragment, bundle);
                }
                break;

            case R.id.imageViewGaleriaCAE:
            case R.id.textViewGaleriaCAE:
                if (stringBase64DocumentoImganen != null) {
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
                    showZoomImage(activity, stringBase64DocumentoImganen, mPath, extension);
                }
                break;

            case R.id.buttonCerraFaseCFF:
                String datosDenuncia = Objects.requireNonNull(textInputEditTextDatosDenunciaCDDDA.getText()).toString().trim();
                String datosAgencia = Objects.requireNonNull(textInputEditTextDatosAgenciaCDDDA.getText()).toString().trim();
                if (idFase == 2) {
                    if (idCaso.isEmpty()) {
                        Utils.messageShort(activity, "El idCaso esta vacío");
                    } else if (String.valueOf(idCasoFase).isEmpty()) {
                        Utils.messageShort(activity, "El idCasoFase esta vacío");
                    } else if (datosDenuncia.isEmpty()) {
                        textInputEditTextDatosDenunciaCDDDA.setError(getString(R.string.text_label_datos_demanda_vacio));
                        textInputEditTextDatosDenunciaCDDDA.requestFocus();
                    } else if (datosDenuncia.length() <= 9) {
                        textInputEditTextDatosDenunciaCDDDA.setError(getString(R.string.text_label_datos_demanda_menor_a_diez_caracteres));
                        textInputEditTextDatosDenunciaCDDDA.requestFocus();
                    } else if (datosAgencia.isEmpty()) {
                        textInputEditTextDatosAgenciaCDDDA.setError(getString(R.string.text_label_datos_agencia_vacio));
                        textInputEditTextDatosAgenciaCDDDA.requestFocus();
                    } else if (datosAgencia.length() <= 9) {
                        textInputEditTextDatosAgenciaCDDDA.setError(getString(R.string.text_label_datos_agencia_menor_a_diez_caracteres));
                        textInputEditTextDatosAgenciaCDDDA.requestFocus();
                    } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
                        Utils.messageShort(activity, "Agregue al menos una evidencia");
                    } else {
                        if (Functions.isNetworkAvailable(activity)) {
                            showAlertDialogCierrePresentacion(activity, getString(R.string.text_label_cierre_de_presentacion), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                    valorDeConfiguraciontipoAppMovil, datosDenuncia, datosAgencia, idFase, Integer.parseInt(idCasoFase), listDocumentosSelectos, listNuevoEstatusResposables);
                        } else {
                            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                        }
                    }
                } else {
                    if (idCaso.isEmpty()) {
                        Utils.messageShort(activity, "El idCaso esta vacío");
                    } else if (String.valueOf(idCasoFase).isEmpty()) {
                        Utils.messageShort(activity, "El idCasoFase esta vacío");
                    } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
                        Utils.messageShort(activity, "Agregue al menos una evidencia");
                    } else {
                        if (Functions.isNetworkAvailable(activity)) {
                            showAlertDialogCierrePresentacion(activity, getString(R.string.text_label_cierre_de_presentacion), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                    valorDeConfiguraciontipoAppMovil, datosDenuncia, datosAgencia, idFase, Integer.parseInt(idCasoFase), listDocumentosSelectos, listNuevoEstatusResposables);
                        } else {
                            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                        }
                    }
                }

                break;

            default:
                break;
        }
    }

    public void showAlertDialogSeleccionarEvidencia(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, int tipoDocumento, View view) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                stringsToNull();
                if (tipoDocumento == 1) {
                    cargarImagenGaleria("png");
                } else if (tipoDocumento == 2) {
                    cargarArchivo();
                } else if (tipoDocumento == 3) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idCaso", idCaso);
                    bundle.putString("navigationFragment", "terminar");
                    bundle.putString("tipoAppMovil", valorDeConfiguraciontipoAppMovil);
                    Navigation.findNavController(view).navigate(R.id.action_navigation_terminar_presentacion_denuncia_fragment_to_navigation_geleria_tomar_fotos_fragment, bundle);
                }
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void showAlertDialogCierrePresentacion(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje,
                                                  String tipoApp, String datosDenuncia, String datosAgencia, int idFase, int idCasoFase, List<DocumentoRequest> documentos, List<ResponsablesPresentacionRequest> listResposablesPresentacion) {
        AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                setAlertDialogTerminarFase(activity, tipoApp, datosDenuncia, datosAgencia,idFase, idCasoFase, documentos, listResposablesPresentacion);
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void setAlertDialogTerminarFase(Activity activity, String tipoApp, String datosDenuncia, String datosAgencia, int idFase, int idCasoFase, List<DocumentoRequest> documentos, List<ResponsablesPresentacionRequest> listResposablesPresentacion) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params;
                if (idFase == 2){
                     params = gsonParams.toJson(new envioRequest(new IniciaCasoRequest(datosDenuncia, datosAgencia), Integer.parseInt(tipoApp), new FaseRequest(idCasoFase), listResposablesPresentacion, documentos));
                } else {
                    params = gsonParams.toJson(new envioRequest(Integer.parseInt(tipoApp), new FaseRequest(idCasoFase), listResposablesPresentacion, documentos));
                }
                //Log.d("estosSonLosParamsQueSe", "setTerminarFase: " + params);
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getCerrarFase().getExito().equals(Constantes.exitoTrue)) {
                            showDialogCerrarFaseConExito(activity, getString(R.string.text_label_exito), getString(R.string.text_label_se_ha_cerrado_esta_fase_exitosamente), getString(R.string.text_label_aceptar), String.valueOf(respuestaGeneral.getCerrarFase().getIdCaso()));
                        } else {
                            Utils.message(activity, respuestaGeneral.getCerrarFase().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.cerrarFase, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialogCerrarFaseConExito(Activity activity, String titulo, String mensaje, String butonText, String idCaso) {
        Dialog dialogTipoEmpleado = new Dialog(activity, R.style.CustomDialogTheme);
        dialogTipoEmpleado.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTipoEmpleado.setCancelable(false);
        dialogTipoEmpleado.setContentView(R.layout.dialog_error_conexion_internet);
        Objects.requireNonNull(dialogTipoEmpleado.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView imageViewLogoDRCI = dialogTipoEmpleado.findViewById(R.id.imageViewLogoDRCI);
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
                Navigation.findNavController(CerrarFaseFragment.this.view).navigate(R.id.action_navigation_iniciar_fase_fragment_to_navigation_proceso_fase_denuncia_fragment, bundle);
            }
        });

        dialogTipoEmpleado.show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == Constantes.RESPUESTA_DE_GALERIA) {
                uriImagenOrPdf = data.getData();

                if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {

                    mPath = uriImagenOrPdf.getPath();
                    try {
                        bitmapImageFoto = Utils.getUriToBitmapImagen(activity, uriImagenOrPdf);
                        //bitmapImageFoto = Utils.cambiarPosicionImage(bitmapImageFoto);
                        uriImagenOrPdf = Utils.resizeImage(activity, bitmapImageFoto);
                        nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
                        extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
                        stringBase64DocumentoImganen = Utils.bitmapToBase64(bitmapImageFoto, extension);
                        stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);
                        if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                            //listDocumentosSelectos.add(new DocumentoRequest(nombrePDFFotos, extension, Integer.parseInt(tamanioPDFFotos), stringBase64DocumentoImganen));
                            listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64DocumentoImganen));
                        } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                            //listDocumentosSelectos.add(new DocumentoRequest(nombrePDFFotos, extension, Integer.parseInt(tamanioPDFFotos), stringCompressDocumentoImagen));
                            listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumentoImagen));
                        }
                        //listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activityTPDF, uriImagenOrPdf)), stringCompressDocumentoImagen));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageViewVerImagenesCAE.setVisibility(View.VISIBLE);
                    imageViewVerFotosCAE.setVisibility(View.GONE);
                    imageViewVerDocumentoCAE.setVisibility(View.GONE);

                } else {
                    //stringsToNull();
                    Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                }

            } else if (requestCode == Constantes.RESPUESTA_DE_ALMACENAMIENTO) {

                if (data != null) {
                    // Perform operations on the document using its URI.
                    uriImagenOrPdf = data.getData();

                    if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {

                        mPath = getPath(activity, uriImagenOrPdf);

                        try {
                            nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
                            extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
                            stringBase64DocumentoImganen = Utils.fileToBase64(activity, uriImagenOrPdf);
                            stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);

                            if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                                //listDocumentosSelectos.add(new DocumentoRequest(nombrePDFFotos, extension, Integer.parseInt(tamanioPDFFotos), stringBase64DocumentoImganen));
                                listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64DocumentoImganen));
                            } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                                //listDocumentosSelectos.add(new DocumentoRequest(nombrePDFFotos, extension, Integer.parseInt(tamanioPDFFotos), stringCompressDocumentoImagen));
                                listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumentoImagen));
                            }
                            //listDocumentosSelectos.add(new DocumentoRequest(Utils.getNombreDocumentos(nombreDeArchivoFoto), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activityTPDF, uriImagenOrPdf)), stringCompressDocumentoImagen));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        banderaFotos = 1;
                        imageViewVerImagenesCAE.setVisibility(View.GONE);
                        imageViewVerFotosCAE.setVisibility(View.GONE);
                        imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);
                    } else {
                        //stringsToNull();
                        Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                    }
                }
            }
        }
    }

    public void stringsToNull() {
        uriImagenOrPdf = null;
        mPath = null;
        nombreDeArchivoFoto = null;
        extension = null;
        stringBase64DocumentoImganen = null;
        listDocumentosSelectos.clear();
    }

    public void cargarImagenGaleria(String extensionPng) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        String png = null;
        if (extensionPng.equals("png") || extensionPng.equals(".png")) {
            png = "image/png";
        }
        String[] mimetypes = {png, "image/jpg", "image/jpeg"};
        intent.setType("*/*");
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
}
