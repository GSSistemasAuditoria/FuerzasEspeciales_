package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.inicioSubFases;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.catalogos.integracion.IntegracionDocData;
import com.auditorias.fuerzasespeciales.request.ResponsablesPresentacionRequest;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters.GaleriaFotosAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.IniciarFaseFragment;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.DocumentosInicioSubfaseAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.inicioSubFases.adapters.EstatusResponsablesInicioSubFaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class IniciarSubFasesFragment extends Fragment /*implements View.OnClickListener */{

    private static final String TAG = IniciarFaseFragment.class.getName();
    //TODO: todas las lista que se pueden utilizar en el fragmento
    private final List<ResponsablesPresentacionRequest> listResposablesPresentacion = new ArrayList<>();
    private final List<EstatusResponsableFase> listStatusResponsableFase = new ArrayList<>();
    private final List<IntegracionDocData> listIntegracionDoc = new ArrayList<>();
    private final List<String> listFotos = new ArrayList<>();
    private final ArrayList<Documentos> listDocumentosSelectos = new ArrayList<>();

    private DocumentosInicioSubfaseAdapter documentosInicioSubfaseAdapter;

    private EstatusResponsablesInicioSubFaseAdapter estatusResponsablesInicioSubFaseAdapter;

    private String idCasoFase;
    private String idCaso;
    private String idSubFase;
    private int idIntegracionDoc;
    private String tipoDocumento;
    private String mPath = "";
    private String stringBase64DocumentoImganen;
    private String stringCompressDocumentoImagen;
    private Bitmap bitmapImageFoto;
    private String extension = "";
    private Uri uriImagenOrPdf;
    private String nombreDeArchivoFoto;
    private Double valorDeConfiguracionFileMaxSize;
    private String descripcionConfiguracionFileMaxSize;
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
    private String valorDeConfiguraciontipoAppMovil = "";
    private String descripcionConfiguraciontipoAppMovil;
    private String valorDeConfiguracionFormatImages;
    private String descripcionConfiguracionFormatImages;
    private int mostrarListaImputados = 0;
    private String imagen;
    private String tamanio;
    private ImageView imagenfeo;
    private LinearLayout folderEmpty;
    private String nombreFoto;
    private Integer valorDeConfiguracionPhotoNumber;
    private String descripcionConfiguracionPhotoNumber;

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
    private TextView textViewTextCSS;
    private TextView textViewMostrarOcultarCLI;
    private TextView textViewTotalImputadosCLI;
    private TextView textViewAlertErrorCFC;
    private TextView textViewAlertErrorCSS;
    private TextView textViewDocumentoCAE;
    private TextView textViewCamaraCAE;
    private TextView textViewGaleriaCAE;
    private TextView textViewAdjuntarEvidenciaCAE;
    private TextView textViewListaDocumentosCDI;
    private TextView textViewFaseEtapaCDF;
    private TextView textViewFaseEtapaColorCDF;
    private TextView textViewFechaCompromisoCDF;

    //TODO: todos los buttons de este fragment
    private Button buttonInicioFaseIFF;
    //TODO: todos los recyclerview de este fragment
    private RecyclerView recyclerViewListaImputadosCLI;
    private RecyclerView recyclerViewDocumentosCDI;
    //TODO: todos los ImageButton de este fragment
    private ImageView imageViewAlertErrorCFC;
    private ImageView imageViewAlertErrorCSS;
    private ImageView imageViewDocumentoCAE;
    private ImageView imageViewVerDocumentoCAE;
    private ImageView imageViewCamaraCAE;
    private ImageView imageViewVerCamaraCAE;
    private ImageView imageViewGaleriaCAE;
    private ImageView imageViewVerGaleriaCAE;
    private Spinner spinnerCSS;
    //TODO:todos los linearLayout de este fragment
    private LinearLayout linearLayoutColorEtapaDenunciaCDD;
    //TODO: todas los view que se pueden utilizar en el fragmento
    private View view;
    private View custumDatosDenuciaAndDatosAgenciaIFF;
    private View custumCardViewAdjuntarDocumentosIFF;
    private View custumSpinnerSelectIFF;
    private View custumCardViewDatosFaseIFF;
    private View custumFechaCompromisoIFF;
    //TODO:todas las lista que se pueden utilizar en el fragmento
    private Context context;
    //TODO: todas las activity que se pueden utilizar en el fragmento
    private Activity activity;
    //TODO: todas las fragmentmanager que se pueden utilizar en el fragmento
    private FragmentManager fragmentManager;
    //TODO: variables de argumentos para leer datos de otro fragment
    private Bundle args;
    private GaleriaFotosAdapter galeriaFotosAdapter;
    private Uri uri;

    public IniciarSubFasesFragment() {
        // Required empty public constructor
    }

//    public static String getPath(final Context context, final Uri uri) {
//        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//
//        // DocumentProvider
//        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//            //Log.i("onActivityResult", "onActivityResult: uri" + uri.toString());
//            //Log.i("onActivityResult", "onActivityResult: authority" + uri.getAuthority());
//            //Log.i("onActivityResult", "onActivityResult: path" + uri.getPath());
//
//            // ExternalStorageProvider
//            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
//                final String docId = DocumentsContract.getDocumentId(uri);
//                final String[] split = docId.split(":");
//                final String type = split[0];
//                Log.i("onActivityResult", "onActivityResult: path" + "getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);
//                //System.out.println("getPath() docId: " + docId + ", split: " + split.length + ", type: " + type);
//
//                // This is for checking Main Memory
//                if ("primary".equalsIgnoreCase(type)) {
//                    if (split.length > 1) {
//                        return Environment.getExternalStorageDirectory() + "/" + split[1] + "/";
//                    } else {
//                        return Environment.getExternalStorageDirectory() + "/";
//                    }
//                    // This is for checking SD Card
//                } else if ("home".equalsIgnoreCase(type)) {
//                    return /*"storage"*/ Environment.getExternalStorageDirectory() + "/" + "document/" + docId.replace(":", "/");
//                }
//
//            }
//        }
//        return null;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_iniciar_fases, container, false);
        activity = getActivity();
        context = getContext();
        fragmentManager = getFragmentManager();

//        refereciasConInterface(view);
//        ocultarElementos();
//
//        textViewTextCSS.setText(getString(R.string.text_label_tipo_de_documento));
//        textViewAdjuntarEvidenciaCAE.setText(getString(R.string.text_label_adjuntar_evidencia));
//
//        args = getArguments();
//        if (args != null) {
//            idCaso = args.getString("idCaso");
//            idSubFase = args.getString("idSubFase");
//            idCasoFase = args.getString("idCasoFase");
//            valorDeConfiguraciontipoAppMovil = args.getString("tipoAppMovil");
//        }
//
//        if (idSubFase.equals("1")) {
//            getIntegracionDoc(activity);
//            custumSpinnerSelectIFF.setVisibility(View.VISIBLE);
//        } else /*if (idSubFase.equals("2"))*/ {
//            custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
//        }
//
//        getDatosCasos(activity, Integer.parseInt(idCaso));
//        getStatusResponsableFaseList(activity);
//        getObtenerConfiguracionFormatImages(activity);
//        getObtenerConfiguracionFileMaxSize(activity);
//        getObtenerConfiguracionFormatDocuments(activity);
//        getObtenerConfiguracionTipoAppMovil(activity);
//        getObtenerConfiguracionPhotoNumber(activity);
//
//        if (listDocumentosSelectos != null /*|| !listDocumentosSelectos.isEmpty()*/) {
//            documentosInicioSubfaseAdapter = new DocumentosInicioSubfaseAdapter(activity, listDocumentosSelectos,  new DocumentosInicioSubfaseAdapter.OnItemSelectedListener() {
//                @Override
//                public void onEliminarListener(Documentos documentoRequest, int position) {
//                    showAlertDialogEliminarResponsable(activity, getString(R.string.text_label_liminar_documento), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), position);
//                }
//
//                @Override
//                public void onVerListener(Documentos documentoRequest, int position) {
//                    if (/*extension.equals(doc) || extension.equals(docx)*/ documentoRequest.getTipoArchivo().equals(".docx") || documentoRequest.getTipoArchivo().equals("docx") || documentoRequest.getTipoArchivo().equals(".doc") || documentoRequest.getTipoArchivo().equals("doc")) {
//                        //verDocumentos(activityTPDF, mPath);
//                        File file = new File(documentoRequest.getmPath());
//                        abrirDocumentoWord(activity, file);
//                    } else {
//                        showZoomImage(activity, documentoRequest.getStringArchivo(), documentoRequest.getmPath(), documentoRequest.getTipoArchivo());
//                    }
//                }
//            });
//
//            recyclerViewDocumentosCDI.setHasFixedSize(false);
//            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
//            recyclerViewDocumentosCDI.setLayoutManager(layoutManagerCategory);
//            recyclerViewDocumentosCDI.setNestedScrollingEnabled(false);
//            recyclerViewDocumentosCDI.setAdapter(documentosInicioSubfaseAdapter);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            //para versiones con android 10.0 o superior.
//            /*if ((activityTPDF.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (activityTPDF.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//                activityTPDF.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constantes.RESPUESTA_DE_CAMARA);
//            }*/
//            if ((ActivityCompat.checkSelfPermission(activity, CAMERA) != PackageManager.PERMISSION_GRANTED) &&
//                    (ActivityCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
//                    (ActivityCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//                ActivityCompat.requestPermissions(activity, new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, Constantes.MIS_PERMISOS);
//            }
//        } else {
//            //para versiones inferiores a android 10.0.
//            //myRequestStoragePermission(activityTPDF);
//        }

        return view;
    }

    /*public void refereciasConInterface(View view) {
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
        textViewAlertErrorCFC = view.findViewById(R.id.textViewFechaCompromisoAlertErrorCFC);
        imageViewAlertErrorCFC = view.findViewById(R.id.imageViewFechaCompromisoAlertErrorCFC);

        textViewTextCSS = view.findViewById(R.id.textViewTextCSS);
        spinnerCSS = view.findViewById(R.id.spinnerCSS);
        textViewAlertErrorCSS = view.findViewById(R.id.textViewAlertErrorCSS);
        imageViewAlertErrorCSS = view.findViewById(R.id.imageViewAlertErrorCSS);

        custumDatosDenuciaAndDatosAgenciaIFF = view.findViewById(R.id.custumDatosDenuciaAndDatosAgenciaIFF);
        custumCardViewAdjuntarDocumentosIFF = view.findViewById(R.id.custumCardViewAdjuntarDocumentosIFF);
        custumSpinnerSelectIFF = view.findViewById(R.id.custumSpinnerSelectIFF);
        custumCardViewDatosFaseIFF = view.findViewById(R.id.custumCardViewDatosFaseIFF);
        custumFechaCompromisoIFF = view.findViewById(R.id.custumFechaCompromisoIFF);

        textViewMostrarOcultarCLI = view.findViewById(R.id.textViewMostrarOcultarCLI);
        textViewMostrarOcultarCLI.setOnClickListener(this);

        recyclerViewListaImputadosCLI = view.findViewById(R.id.recyclerViewListaResponsablesCLI);
        textViewTotalImputadosCLI = view.findViewById(R.id.textViewTotalResponsablesCLI);

        buttonInicioFaseIFF = view.findViewById(R.id.buttonInicioFaseIFF);
        buttonInicioFaseIFF.setOnClickListener(this);

        textViewAdjuntarEvidenciaCAE = view.findViewById(R.id.textViewAdjuntarEvidenciaCAE);
        textViewDocumentoCAE = view.findViewById(R.id.textViewDocumentoCAE);
        textViewDocumentoCAE.setOnClickListener(this);

        imageViewDocumentoCAE = view.findViewById(R.id.imageViewDocumentoCAE);
        imageViewDocumentoCAE.setOnClickListener(this);

        imageViewVerDocumentoCAE = view.findViewById(R.id.imageViewVerDocumentoCAE);
        imageViewVerDocumentoCAE.setOnClickListener(this);
        textViewCamaraCAE = view.findViewById(R.id.textViewCamaraCAE);
        textViewCamaraCAE.setOnClickListener(this);

        imageViewCamaraCAE = view.findViewById(R.id.imageViewCamaraCAE);
        imageViewCamaraCAE.setOnClickListener(this);

        imageViewVerCamaraCAE = view.findViewById(R.id.imageViewVerCamaraCAE);
        imageViewVerCamaraCAE.setOnClickListener(this);
        textViewGaleriaCAE = view.findViewById(R.id.textViewGaleriaCAE);
        textViewGaleriaCAE.setOnClickListener(this);

        imageViewGaleriaCAE = view.findViewById(R.id.imageViewGaleriaCAE);
        imageViewGaleriaCAE.setOnClickListener(this);

        imageViewVerGaleriaCAE = view.findViewById(R.id.imageViewVerGaleriaCAE);
        imageViewVerGaleriaCAE.setOnClickListener(this);

        recyclerViewDocumentosCDI = view.findViewById(R.id.recyclerViewDocumentosCDI);
        textViewListaDocumentosCDI = view.findViewById(R.id.textViewListaDocumentosCDI);
        textViewFaseEtapaCDF = view.findViewById(R.id.textViewFaseEtapaCDF);
        textViewFaseEtapaColorCDF = view.findViewById(R.id.textViewFaseEtapaColorCDF);
        textViewFechaCompromisoCDF = view.findViewById(R.id.textViewFechaCompromisoCDF);


    }*/

   /* public void ocultarElementos() {
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
        textViewPorcentajeGeneralDenunciaCDD.setVisibility(View.GONE);
        textViewAlertErrorCFC.setVisibility(View.GONE);
        imageViewAlertErrorCFC.setVisibility(View.GONE);
        textViewAlertErrorCSS.setVisibility(View.GONE);
        imageViewAlertErrorCSS.setVisibility(View.GONE);
        recyclerViewListaImputadosCLI.setVisibility(View.GONE);
        imageViewVerDocumentoCAE.setVisibility(View.GONE);
        imageViewVerCamaraCAE.setVisibility(View.GONE);
        imageViewVerGaleriaCAE.setVisibility(View.GONE);
        custumCardViewAdjuntarDocumentosIFF.setVisibility(View.GONE);
        recyclerViewDocumentosCDI.setVisibility(View.GONE);
        textViewListaDocumentosCDI.setVisibility(View.GONE);
        custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);
        custumSpinnerSelectIFF.setVisibility(View.GONE);
        custumFechaCompromisoIFF.setVisibility(View.GONE);
    }*/

    /*private void getDatosCasos(Activity activity, int idCaso) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new DatosDenunciaRequest(idCaso));
                //                                                         idCaso
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);

                        if (serial.getDatosCasoModel().getExito().equals(Constantes.exitoTrue)) {

                            if (serial.getDatosCasoModel().getIdSubFase() != null) {
                                textViewTextCSS.setVisibility(View.VISIBLE);
                                spinnerCSS.setVisibility(View.VISIBLE);
                                textViewSubTiutuloCST.setText(getString(R.string.text_label_inicio).concat(" - ").concat(serial.getDatosCasoModel().getSubFase()));
                                custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);
                            } else {
                                textViewSubTiutuloCST.setText(getString(R.string.text_label_inicio).concat(" - ").concat(serial.getDatosCasoModel().getFase()));
                            }
                            textViewFaseEtapaCDF.setText(serial.getDatosCasoModel().getSubFase());
                            textViewFaseEtapaColorCDF.setText(serial.getDatosCasoModel().getEtapaSubFase());
                            textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(serial.getDatosCasoModel().getColorSubFase()));
                            textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(serial.getDatosCasoModel().getFechaCompromiso())));
                            textViewFechaCompromisoCDF.setTextColor(activity.getColor(R.color.green_secondary));

                            linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(serial.getDatosCasoModel().getColorEtapaCaso()));
                            textViewFolioDenunciaCDD.setText(serial.getDatosCasoModel().getFolio());
                            textViewNombreDenunciaCDD.setText(serial.getDatosCasoModel().getNombre());
                            textViewUnidadNegocioCDD.setText(serial.getDatosCasoModel().getUdN());
                            textViewZonaCDD.setText(serial.getDatosCasoModel().getZona());
                            textViewTipoDelitoCDD.setText(serial.getDatosCasoModel().getTipoFraude());
                            textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(serial.getDatosCasoModel().getFechaRegistro()));
                            if (serial.getDatosCasoModel().getFechaCompromiso() != null) {
                                textViewFechaCompromisoCFC.setText(Utils.SetCambioFormatoFechaDiaMesAnio(serial.getDatosCasoModel().getFechaCompromiso()));
                            } else {
                                textViewFechaCompromisoCFC.setText("");
                            }

                            textViewTotalImputadosCLI.setText(String.valueOf(serial.getDatosCasoModel().getTotalResponsables()));

                            estatusResponsablesInicioSubFaseAdapter = new EstatusResponsablesInicioSubFaseAdapter(activity, serial.getDatosCasoModel().getListResponsables(), fragmentManager, listStatusResponsableFase, new EstatusResponsablesInicioSubFaseAdapter.OnItemSelectedListener() {
                                @Override
                                public void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int idCasoResponsable, int idStatusResponsable) {
                                    if (idStatusResponsable != 0) {
                                        listResposablesPresentacion.add(new ResponsablesPresentacionRequest(idCasoFase, idCasoResponsable, idStatusResponsable));
                                        //parametros para la lista que se envia                             IdCasoFase, IdCasoResponsable, IdStatusResponsablelistResposablesPresentacion
                                    }
                                }
                            });
                            recyclerViewListaImputadosCLI.setHasFixedSize(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                            recyclerViewListaImputadosCLI.setLayoutManager(layoutManagerCategory);
                            recyclerViewListaImputadosCLI.setNestedScrollingEnabled(false);
                            recyclerViewListaImputadosCLI.setAdapter(estatusResponsablesInicioSubFaseAdapter);
                        } else {
                            Utils.messageShort(activity, serial.getDatosCasoModel().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                    //}, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerDatosCaso.concat(Constantes.signoInterrogacion).concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso));
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.ObtenerDatosCaso, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //TODO: consumo de servicio de status de los responsables que estan dentro de la lista de resposales
   /* private void getStatusResponsableFaseList(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        //Serial serial = gson.fromJson(result, Serial.class);
                        //listStatusResponsableFase.addAll(serial.getStatusResponsableFaseList());
                        //getEstatusResponsablesList(serial.getStatusResponsableFaseList());
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        listStatusResponsableFase.addAll(respuestaGeneral.getLisEstatusResponsableFase());
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
    }*/

    //TODO: consumo de servicio de status de los responsables que estan dentro de la lista de resposales
    /*private void getIntegracionDoc(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        llenadoSpinnerIntegracionDoc(activity, respuestaGeneral.getIntegracionDoc().getDataList());
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoIntegracionDoc);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*public void llenadoSpinnerIntegracionDoc(Activity activity, List<IntegracionDocData> integracionDocList) {
        listIntegracionDoc.clear();
        listIntegracionDoc.add(new IntegracionDocData(Constantes.selecionar, "", 0, 0));
        listIntegracionDoc.addAll(integracionDocList);

        ArrayAdapter<IntegracionDocData> myAdapter = new IntegracionDocArrayAdapter(activity, R.layout.cell_spinner_item, listIntegracionDoc);
        spinnerCSS.setAdapter(myAdapter);
        spinnerCSS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listIntegracionDoc.get(position).getId() == 0) {
                    textViewAlertErrorCSS.setVisibility(View.GONE);
                    imageViewAlertErrorCSS.setVisibility(View.GONE);
                    custumCardViewAdjuntarDocumentosIFF.setVisibility(View.GONE);
                    textViewListaDocumentosCDI.setVisibility(View.GONE);
                    idIntegracionDoc = listIntegracionDoc.get(position).getId();
                    tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                } else if (listIntegracionDoc.get(position).getId() >= 1) {
                    textViewAlertErrorCSS.setVisibility(View.GONE);
                    imageViewAlertErrorCSS.setVisibility(View.GONE);
                    custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
                    //textViewListaDocumentosIFF.setVisibility(View.VISIBLE);
                    idIntegracionDoc = listIntegracionDoc.get(position).getId();
                    tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

    /*private void getObtenerConfiguracionFileMaxSize(Activity activity) {
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
*/
    /*private void getObtenerConfiguracionTipoAppMovil(Activity activity) {
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
    }*/

    /*private void getObtenerConfiguracionFormatDocuments(Activity activity) {
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
    }*/

    /*private void getObtenerConfiguracionFormatImages(Activity activity) {
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
*/
    /*private void getObtenerConfiguracionPhotoNumber(Activity activity) {
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
    }*/

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.imageViewDocumentoCAE:
//            case R.id.textViewDocumentoCAE:
//                if (idSubFase.equals("1")) {
//                    cargarArchivo();
//                } else/* if (idSubFase.equals("2"))*/ {
//                    if (stringBase64DocumentoImganen != null) {
//                        showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 2, v);
//                    } else {
//                        cargarArchivo();
//                    }
//                }
//                break;
//
//            case R.id.imageViewGaleriaCAE:
//            case R.id.textViewGaleriaCAE:
//                if (idSubFase.equals("1")) {
//                    cargarImagenGaleria("png");
//                } else /*if (idSubFase.equals("2"))*/ {
//                    if (stringBase64DocumentoImganen != null) {
//                        showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 1, v);
//                    } else {
//                        cargarImagenGaleria("png");
//                    }
//                }
//                break;
//
//            case R.id.imageViewCamaraCAE:
//            case R.id.textViewCamaraCAE:
//                if (idSubFase.equals("1")) {
//                    showDialogfotos(activity);
//                } else /*if (idSubFase.equals("2"))*/ {
//                    if (stringBase64DocumentoImganen != null) {
//                        showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 3, v);
//                    } else {
//                        listDocumentosSelectos.clear();
//                        showDialogfotos(activity);
//                    }
//                }
//                break;
//
//            case R.id.imageViewVerGaleriaCAE:
//            case R.id.imageViewVerCamaraCAE:
//            case R.id.imageViewVerDocumentoCAE:
//                if (/*extension.equals(doc) || extension.equals(docx)*/ extension.equals(".docx") || extension.equals("docx") || extension.equals(".doc") || extension.equals("doc")) {
//                    //verDocumentos(activityTPDF, mPath);
//                    File file = new File(mPath);
//                    abrirDocumentoWord(activity, file);
//                } else {
//                    showZoomImage(activity, stringBase64DocumentoImganen, mPath, extension);
//                }
//                break;
//
//            case R.id.textViewMostrarOcultarCLI:
//                if (mostrarListaImputados == 0) {
//                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_ocultar));
//                    recyclerViewListaImputadosCLI.setVisibility(View.VISIBLE);
//                    mostrarListaImputados = 1;
//                } else {
//                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_mostrar));
//                    recyclerViewListaImputadosCLI.setVisibility(View.GONE);
//                    mostrarListaImputados = 0;
//                }
//                break;
//
//            case R.id.buttonInicioFaseIFF:
//                if (idSubFase.equals("1")) {
//                    if (idCaso.isEmpty()) {
//                        Utils.messageShort(activity, getString(R.string.text_label_id_caso));
//                    } else if (String.valueOf(idCasoFase).isEmpty()) {
//                        Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
//                    } else if (String.valueOf(idIntegracionDoc).isEmpty() || idIntegracionDoc == 0) {
//                        textViewAlertErrorCSS.setVisibility(View.VISIBLE);
//                        imageViewAlertErrorCSS.setVisibility(View.VISIBLE);
//                        textViewAlertErrorCSS.setText(getString(R.string.text_label_seleccione_al_menos_un_tipo_de_documento));
//                        Utils.messageShort(activity, getString(R.string.text_label_seleccione_al_menos_un_tipo_de_documento));
//                    } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
//                        Utils.messageShort(activity, getString(R.string.text_label_agrege_al_menos_un_de_documento));
//                    } else {
//                        if (Functions.isNetworkAvailable(activity)) {
//                            showAlertDialogInicioSubFase(activity, getString(R.string.text_label_inicio_de_subfase), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
//                                    valorDeConfiguraciontipoAppMovil, Integer.parseInt(idCasoFase), listDocumentosSelectos, listResposablesPresentacion);
//                        } else {
//                            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
//                        }
//                    }
//                } else /*if (idSubFase.equals("2"))*/ {
//                    if (idCaso.isEmpty()) {
//                        Utils.messageShort(activity, getString(R.string.text_label_id_caso));
//                    } else if (String.valueOf(idCasoFase).isEmpty()) {
//                        Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
//                    } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
//                        Utils.messageShort(activity, getString(R.string.text_label_agrege_al_menos_un_de_documento));
//                    } else {
//                        if (Functions.isNetworkAvailable(activity)) {
//                            showAlertDialogInicioSubFase(activity, getString(R.string.text_label_inicio_de_subfase), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
//                                    valorDeConfiguraciontipoAppMovil, Integer.parseInt(idCasoFase), listDocumentosSelectos, listResposablesPresentacion);
//                        } else {
//                            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
//                        }
//                    }
//                }
//
//
//                break;
//
//            default:
//                break;
//        }
//    }

    /*public void showAlertDialogSeleccionarEvidencia(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, int tipoDocumento, View view) {
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
                    listDocumentosSelectos.clear();
                    showDialogfotos(activity);
                }
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }*/

    /*public void stringsToNull() {
        uriImagenOrPdf = null;
        mPath = null;
        nombreDeArchivoFoto = null;
        extension = null;
        stringBase64DocumentoImganen = null;
        listDocumentosSelectos.clear();
    }*/

    /*public void showAlertDialogInicioSubFase(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, String tipoApp, int idCasoFase, List<Documentos> documentos, List<ResponsablesPresentacionRequest> listResposablesPresentacion) {
        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                setIniciarSubFase(activity, tipoApp, idCasoFase, documentos, listResposablesPresentacion);
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }*/

    /*public void setIniciarSubFase(Activity activity, String tipoApp, int idCasoFase, List<Documentos> documentos, List<ResponsablesPresentacionRequest> listResposablesPresentacion) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new envioRequest(Integer.parseInt(tipoApp), new FaseRequest(idCasoFase), listResposablesPresentacion, documentos));
                //Log.d("estosSonLosParamsQueSe", "setTerminarFase: " + params);
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        //RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        Serial serial = gson.fromJson(result, Serial.class);
                        if (serial.getIniciarFaseResult().getExito().equals(Constantes.exitoTrue)) {
                            showDialogIniciarSubFaseConExito(activity, getString(R.string.text_label_exito), getString(R.string.text_label_se_ha_inciado_esta_subfase_exitosamente), getString(R.string.text_label_aceptar), String.valueOf(serial.getIniciarFaseResult().getIdCaso()));
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
    }*/

    /*public void showDialogIniciarSubFaseConExito(Activity activity, String titulo, String mensaje, String butonText, String idCaso) {
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
                Navigation.findNavController(IniciarSubFasesFragment.this.view).navigate(R.id.action_navigation_iniciar_fase_fragment_to_navigation_proceso_fase_denuncia_fragment, bundle);
            }
        });

        dialogTipoEmpleado.show();
    }*/

    /*public void showAlertDialogEliminarResponsable(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if (!listDocumentosSelectos.isEmpty()) {
                    listDocumentosSelectos.remove(position);
                    documentosInicioSubfaseAdapter.notifyDataSetChanged();
                    if (listDocumentosSelectos.size() == 0) {
                        textViewListaDocumentosCDI.setVisibility(View.GONE);
                        recyclerViewDocumentosCDI.setVisibility(View.GONE);
                    }
                } else {
                    textViewListaDocumentosCDI.setVisibility(View.GONE);
                    recyclerViewDocumentosCDI.setVisibility(View.GONE);
                }
            }
        });

        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }*/

//    public void abrirDocumentoWord(Activity activity, File url) {
//
//        //Uri uri = Uri.fromFile(url);
//        Uri uri = FileProvider.getUriForFile(activity, "com.auditorias.fuerzasespeciales" + ".fileprovider", url);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        /*if (url.toString().contains(".docx")) {
//            // Word document
//            intent.setDataAndType(uri, "application/application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        } else*/
//        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
//            // Word document
//            intent.setDataAndType(uri, "application/msword");
//        } else if (url.toString().contains(".pdf")) {
//            // PDF file
//            intent.setDataAndType(uri, "application/pdf");
//        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
//            // Powerpoint file
//            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
//        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
//            // Excel file
//            intent.setDataAndType(uri, "application/vnd.ms-excel");
//        } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
//            // WAV audio file
//            intent.setDataAndType(uri, "application/x-wav");
//        } else if (url.toString().contains(".rtf")) {
//            // RTF file
//            intent.setDataAndType(uri, "application/rtf");
//        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
//            // WAV audio file
//            intent.setDataAndType(uri, "audio/x-wav");
//        } else if (url.toString().contains(".gif")) {
//            // GIF file
//            intent.setDataAndType(uri, "image/gif");
//        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
//            // JPG file
//            intent.setDataAndType(uri, "image/jpeg");
//        } else if (url.toString().contains(".txt")) {
//            // Text file
//            intent.setDataAndType(uri, "text/plain");
//        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
//            // Video files
//            intent.setDataAndType(uri, "video/*");
//        } else {
//            intent.setDataAndType(uri, "*/*");
//        }
//
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        activity.startActivityForResult(Intent.createChooser(intent, "Selecciona app de imagen"), Constantes.RESPUESTA_DE_ALMACENAMIENTO);
//
//    }

    //TODO: este es el dialog para poder visualizar la imagen de manera grande
//    public void showZoomImage(Activity activity, String documentoEnBase64, String pathDocumento, String extension) {
//        Dialog dialogAdjuntarDocumentos = new Dialog(activity, R.style.CustomDialogTheme);
//        dialogAdjuntarDocumentos.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialogAdjuntarDocumentos.setCancelable(false);
//        dialogAdjuntarDocumentos.setContentView(R.layout.dialog_zoom_image);
//        Objects.requireNonNull(dialogAdjuntarDocumentos.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        Button buttonCerrarDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonCerrarDZI);
//        Button buttonEliminarEvidenciaDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonEliminarEvidenciaDZI);
//        ImageView imageViewViewImageDZI = dialogAdjuntarDocumentos.findViewById(R.id.imageViewViewImageDZI);
//        PDFView pdfView = dialogAdjuntarDocumentos.findViewById(R.id.pdfView);
//
//        buttonEliminarEvidenciaDZI.setVisibility(View.GONE);
//        if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
//            pdfView.setVisibility(View.GONE);
//            imageViewViewImageDZI.setVisibility(View.VISIBLE);
//            pdfView.setVisibility(View.GONE);
//            imageViewViewImageDZI.setVisibility(View.VISIBLE);
//            if (documentoEnBase64 != null) {
//                Glide.with(activity).load(Utils.base64ToBitmap(documentoEnBase64)).fitCenter().into(imageViewViewImageDZI);
//            }
//            /*String imagenDecompres = null;
//            try {
//                imagenDecompres = Utils.decompressBase64(documentoEnBase64);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Bitmap imagenSinBase64 = Utils.base64ToBitmap(imagenDecompres);
//            Glide.with(activity).load(*//*Utils.rotateImage(*//*imagenSinBase64*//*, -90)*//*).fitCenter().into(imageViewViewImageDZI);*/
//
//        } else if (extension.equals(".pdf") || extension.equals("pdf")) {
//            pdfView.setVisibility(View.VISIBLE);
//            imageViewViewImageDZI.setVisibility(View.GONE);
//            File file = new File(pathDocumento);
//            pdfView.fromFile(file).load();
//        }
//
//        buttonCerrarDZI.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialogAdjuntarDocumentos.dismiss();
//            }
//        });
//
//        dialogAdjuntarDocumentos.show();
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//
//            if (requestCode == Constantes.RESPUESTA_DE_GALERIA) {
//                uriImagenOrPdf = data.getData();
//
//                if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {
//
//                    mPath = uriImagenOrPdf.getPath();
//                    try {
//                        bitmapImageFoto = Utils.getUriToBitmapImagen(activity, uriImagenOrPdf);
//                        //bitmapImageFoto = Utils.cambiarPosicionImage(bitmapImageFoto);
//                        uriImagenOrPdf = Utils.resizeImage(activity, bitmapImageFoto);
//                        nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
//                        extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
//                        stringBase64DocumentoImganen = Utils.bitmapToBase64(bitmapImageFoto, extension);
//                        stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);
//                        if (valorDeConfiguraciontipoAppMovil.equals("1")) {
//                            listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64DocumentoImganen, mPath, tipoDocumento));
//                        } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
//                            listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumentoImagen, mPath, tipoDocumento));
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if (idSubFase.equals("1")) {
//                        textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
//                        recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
//                        documentosInicioSubfaseAdapter.notifyDataSetChanged();
//                    } else /*if (idSubFase.equals("2"))*/ {
//                        imageViewVerGaleriaCAE.setVisibility(View.VISIBLE);
//                        imageViewVerCamaraCAE.setVisibility(View.GONE);
//                        imageViewVerDocumentoCAE.setVisibility(View.GONE);
//                    }
//                } else {
//                    Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
//                }
//
//            } else if (requestCode == Constantes.RESPUESTA_DE_ALMACENAMIENTO) {
//
//                if (data != null) {
//                    uriImagenOrPdf = data.getData();
//
//                    if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {
//
//                        mPath = getPath(activity, uriImagenOrPdf);
//
//                        try {
//                            nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
//                            extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
//                            stringBase64DocumentoImganen = Utils.fileToBase64(activity, uriImagenOrPdf);
//                            stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);
//
//                            if (valorDeConfiguraciontipoAppMovil.equals("1")) {
//                                listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64DocumentoImganen, mPath, tipoDocumento));
//                            } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
//                                listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumentoImagen, mPath, tipoDocumento));
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        if (idSubFase.equals("1")) {
//                            textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
//                            recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
//                            documentosInicioSubfaseAdapter.notifyDataSetChanged();
//                        } else /*if (idSubFase.equals("2"))*/ {
//                            imageViewVerGaleriaCAE.setVisibility(View.GONE);
//                            imageViewVerCamaraCAE.setVisibility(View.GONE);
//                            imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);
//                        }
//                    } else {
//                        Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
//                    }
//                }
//            } else if (requestCode == Constantes.RESPUESTA_DE_CAMARA) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    galleryAddPic();
//                    setPic();
//
//                } else {
//                    MediaScannerConnection.scanFile(activity,
//                            new String[]{mPath}, null,
//                            new MediaScannerConnection.OnScanCompletedListener() {
//                                @Override
//                                public void onScanCompleted(String path, Uri uri) {
//                                    Log.i("ExternalStorage", "Scanned " + path + ":");
//                                    Log.i("ExternalStorage", "-> Uri = " + uri);
//                                }
//                            });
//
//                    Bitmap bitmapImageFoto = BitmapFactory.decodeFile(mPath);
//
//                    folderEmpty.setVisibility(View.GONE);
//
//                    try {
//                        uri = Utils.resizeImage(activity, bitmapImageFoto);
//                        imagen = Utils.fileToBase64(activity, uri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    galeriaFotosAdapter.getList().add(imagen);
//                    galeriaFotosAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }

//    public void cargarImagenGaleria(String extensionPng) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//        String png = null;
//        if (extensionPng.equals("png") || extensionPng.equals(".png")) {
//            png = "image/png";
//        }
//        String[] mimetypes = {png, "image/jpg", "image/jpeg"};
//        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
//        startActivityForResult(Intent.createChooser(intent, "Selecciona el archivo"), Constantes.RESPUESTA_DE_GALERIA);
//    }

//    public void cargarArchivo() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        //intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//        String[] mimetypes = {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/pdf"};
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
//        startActivityForResult(Intent.createChooser(intent, "Selecciona el archivo"), Constantes.RESPUESTA_DE_ALMACENAMIENTO);
//    }

//    public void showDialogfotos(Activity activity) {
//        Dialog dialog = new Dialog(activity, R.style.CustomDialogTheme);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.fragment_gelria_tomar_fotos);
//        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        folderEmpty = dialog.findViewById(R.id.linearLayoutAdvertenciaFotosGTFF);
//        imagenfeo = dialog.findViewById(R.id.imagenViewAdvertenciaFotosGTFF);
//
//        //TODO: son todos los botones del fragment
//        ImageButton buttonAgregarFotoGTFF = dialog.findViewById(R.id.buttonAgregarFotoGTFF);
//        buttonAgregarFotoGTFF.setOnClickListener(this);
//
//        ImageButton buttonSavePDFGTFF = dialog.findViewById(R.id.buttonGuardarPDFGTFF);
//        buttonSavePDFGTFF.setOnClickListener(this);
//
//        RecyclerView recyclerViewGaleria = dialog.findViewById(R.id.recyclerViewGaleria);
//        galeriaFotosAdapter = new GaleriaFotosAdapter(activity, listFotos, new GaleriaFotosAdapter.OnListener() {
//            @Override
//            public void onItemSelectedListener(String fotoString, int posicion) {
//                showAlertDialogEliminarEnvidencia(activity, getString(R.string.text_label_liminar_evidencia), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), posicion);
//            }
//        });
//        recyclerViewGaleria.setHasFixedSize(false);
//        RecyclerView.LayoutManager layoutManagerCategory = new GridLayoutManager(activity, 3);
//        recyclerViewGaleria.setLayoutManager(layoutManagerCategory);
//        recyclerViewGaleria.setNestedScrollingEnabled(false);
//        recyclerViewGaleria.setAdapter(galeriaFotosAdapter);
//
//        buttonAgregarFotoGTFF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listFotos.size() < valorDeConfiguracionPhotoNumber) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                        dispatchTakePictureIntent();
//                    } else {
//                        //para versiones inferiores a android 10.0.
//                        //openCamera(activity);
//                    }
//
//                } else {
//                    Utils.messageShort(activity, descripcionConfiguracionPhotoNumber);
//                }
//            }
//        });
//        buttonSavePDFGTFF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listFotos.isEmpty() || listFotos == null) {
//                    Utils.messageShort(activity, getString(R.string.text_label_falta_tomar_una_foto));
//                } else {
//                    GeneratePDF(dialog);
//                }
//            }
//        });
//        dialog.show();
//    }

/*    public void GeneratePDF(Dialog dialog) {
        if (write()) {
            dialog.dismiss();
            Toast.makeText(activity, "Archivo PDF creado correctamente", Toast.LENGTH_LONG).show();
            File file = new File(mPath);
            try {
                stringBase64DocumentoImganen = Utils.fileToBase64(activity, Uri.fromFile(file));
                stringCompressDocumentoImagen = Utils.compressBase64(stringBase64DocumentoImganen);
                if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, String.valueOf(idIntegracionDoc), extension, Integer.parseInt(tamanio), stringBase64DocumentoImganen, mPath, tipoDocumento));
                } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, String.valueOf(idIntegracionDoc), extension, Integer.parseInt(tamanio), stringCompressDocumentoImagen, mPath, tipoDocumento));
                }
                listFotos.clear();
                if (idSubFase.equals("1")) {
                    textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                    recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                    documentosInicioSubfaseAdapter.notifyDataSetChanged();
                } else if (idSubFase.equals("2")) {
                    imageViewVerGaleriaCAE.setVisibility(View.GONE);
                    imageViewVerCamaraCAE.setVisibility(View.VISIBLE);
                    imageViewVerDocumentoCAE.setVisibility(View.GONE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(activity, getString(R.string.text_label_no_se_creo_el_archivo_pdf), Toast.LENGTH_SHORT).show();
        }
    }*/

//    public void showAlertDialogEliminarEnvidencia(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
//        androidx.appcompat.app.AlertDialog.Builder dialogo1 = new androidx.appcompat.app.AlertDialog.Builder(activity);
//        dialogo1.setTitle(titulo);
//        dialogo1.setMessage(mensage);
//        dialogo1.setCancelable(false);
//        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogo1, int id) {
//                if (!listFotos.isEmpty()) {
//                    listFotos.remove(position);
//                    galeriaFotosAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogo1, int id) {
//
//            }
//        });
//        dialogo1.show();
//    }

    //TODO: funcion para abrir la camara con un intent en la version de android x o superior
/*    @SuppressLint("QueryPermissionsNeeded")
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
    }*/

    //TODO: funcion para poder crear el archivo de la imagen que se tomo con la camara en la version de android x o superior
//    private File createImageFile() throws IOException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
//        Date date = new Date();
//        nombreFoto = dateFormat.format(date);
//        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(nombreFoto, ".jpg", storageDir);
//        mPath = image.getAbsolutePath();
//        //currentPhotoPath = image.getAbsolutePath();
//        return image;
//    }

    //TODO: funcion para poder agregar la foto tomada con la camara a la galeria en la version de android x o superior
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mPath);
//        uri = Uri.fromFile(f);
//        mediaScanIntent.setData(uri);
//        activity.sendBroadcast(mediaScanIntent);
//    }

    //TODO: funcion para enviar la foto tomanda con la camara a un imageview oara poder mostra la foto que se tomo en la version de android x o superior
//    @SuppressLint("UseCompatLoadingForDrawables")
//    private void setPic() {
//        float targetW = imagenfeo.getWidth();
//        float targetH = imagenfeo.getHeight();
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//        float scaleFactor = Math.min(photoW / targetW, photoH / targetH);
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = (int) scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        folderEmpty.setVisibility(View.GONE);
//        Bitmap bitmap = null;
//        try {
//            bitmap = Utils.getBitmap(activity, uri);
//            uri = Utils.resizeImage(activity, Utils.cambiarPosicionImage(bitmap));
//            imagen = Utils.fileToBase64(activity, uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        galeriaFotosAdapter.getList().add(imagen);
//        galeriaFotosAdapter.notifyDataSetChanged();
//
//    }

//    public Boolean write() {
//
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS", Locale.getDefault());
//            Date date = new Date();
//            nombreFoto = dateFormat.format(date);
//
//            String path = Environment.getExternalStorageDirectory() + "/sifra/";
//
//            File dir = new File(path);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//
//            File newFile = new File(dir, nombreFoto + ".pdf");
//            Document document = new Document();
//
//            if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(String.valueOf(newFile.length()))) {
//
//                if (listFotos != null || listFotos.size() != 0) {
//
//                    PdfWriter.getInstance(document, new FileOutputStream(newFile.getAbsoluteFile()));
//                    document.open();
//
//                    for (int i = 0; i < listFotos.size(); i++) {
//                        Image foto = Image.getInstance(Base64.decode(listFotos.get(i)/*.getDocumento()*/, Base64.NO_WRAP));
//                        foto.scaleAbsolute(500, 350);
//
//                        document.add(foto);
//                        document.add(new Phrase(Chunk.NEWLINE));
//                    }
//                    document.close();
//                    nombreDeArchivoFoto = Utils.getNombreDocumentos(String.valueOf(newFile));
//                    extension = "pdf";
//                    tamanio = String.valueOf(newFile.length());
//                    mPath = newFile.getPath();
//
//                } else {
//                    document.close();
//                    Utils.messageShort(activity, "No hay fotos");
//                }
//
//            } else {
//                document.close();
//                Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
//            }
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        } catch (DocumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        }
//    }
}