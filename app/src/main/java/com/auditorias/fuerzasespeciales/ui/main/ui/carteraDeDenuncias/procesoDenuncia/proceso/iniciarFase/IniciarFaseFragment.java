package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.catalogos.estatusSentencia.DataEstatusSentencia;
import com.auditorias.fuerzasespeciales.models.catalogos.integracion.IntegracionDocData;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.request.denuncia.DatosDenunciaRequest;
import com.auditorias.fuerzasespeciales.request.inicioFase.Denuncia;
import com.auditorias.fuerzasespeciales.request.inicioFase.Fase;
import com.auditorias.fuerzasespeciales.request.inicioFase.InicioFase;
import com.auditorias.fuerzasespeciales.request.inicioFase.ResponsablesFase;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.Documentos;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.InicioSubFase;
import com.auditorias.fuerzasespeciales.request.inicioSubFase.SubFase;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters.GaleriaFotosAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.DocumentosInicioSubfaseAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusResponsablesInicioAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusSentenciaArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.IntegracionDocArrayAdapter;
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

public class IniciarFaseFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = IniciarFaseFragment.class.getName();

    private final List<EstatusResponsableFase> listEstatusResponsableFase = new ArrayList<>();
    private final List<ResponsablesFase> listResponsablesFase = new ArrayList<>();

    private final List<IntegracionDocData> listIntegracionDoc = new ArrayList<>();
    private final ArrayList<Documentos> listDocumentosSelectos = new ArrayList<>();
    private final List<String> listGaleriaFotos = new ArrayList<>();
    private List<DataEstatusSentencia> listDataEstatusSentencia = new ArrayList<>();

    private DocumentosInicioSubfaseAdapter documentosInicioSubfaseAdapter;

    private EstatusResponsablesInicioAdapter estatusResponsablesInicioAdapter;

    private String idCasoFase;
    private String idCaso;
    private String idSubFase;
    private String idFase;
    private String fechaCompromiso;

    private int idIntegracionDoc;
    private String idStatusSentencia;
    private String tipoDocumento;

    private String[] partsImg;
    private String valorDeConfiguracionFormatImages;
    private String descripcionConfiguracionFormatImages;
    private String png;
    private String jpg;
    private String jpeg;

    private Double valorDeConfiguracionFileMaxSize;
    private String descripcionConfiguracionFileMaxSize;

    private String valorDeConfiguracionFormatDocuments;
    private String descripcionConfiguracionFormatDocuments;
    private String[] partsDocs;
    private String doc;
    private String docx;
    private String pdf;

    private String valorDeConfiguraciontipoAppMovil;
    private String descripcionConfiguraciontipoAppMovil;

    private Integer valorDeConfiguracionPhotoNumber;
    private String descripcionConfiguracionPhotoNumber;

    private String stringBase64Documento;
    private String stringCompressDocumento;
    private String mPath = "";
    private String extension;
    //private Uri uriImagenOrPdf;
    private String nombreDeArchivoFoto;
    private String nombreFoto;
    private GaleriaFotosAdapter galeriaFotosAdapter;
    private Uri uriImagenOrPdf;
    private String imagen;
    private String tamanio;
    private Bitmap bitmapImageFoto;
    private ImageView imagenViewAdvertenciaFotosGTFF;

    private LinearLayout linearLayoutAdvertenciaFotosGTFF;

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

    private View custumCardViewDatosFaseIFF;
    private TextView textViewFaseEtapaCDF;
    private TextView textViewFaseEtapaColorCDF;
    private TextView textViewFechaCompromisoCDF;

    private View custumFechaCompromisoIFF;
    private TextView textViewFechaCompromisoCFC;
    private TextView textViewFechaCompromisoAlertErrorCFC;
    private ImageView imageViewFechaCompromisoAlertErrorCFC;

    private View custumDatosDenuciaAndDatosAgenciaIFF;
    private TextInputEditText textInputEditTextDatosDenunciaCDDDA;
    private TextInputEditText textInputEditTextDatosAgenciaCDDDA;

    private View custumSpinnerSelectIFF;
    private TextView textViewTextCSS;
    private Spinner spinnerCSS;
    private TextView textViewAlertErrorCSS;
    private ImageView imageViewAlertErrorCSS;

    private View custumSpinnerSelectIFF_2;
    private TextView textViewTextCSS_2;
    private Spinner spinnerCSS_2;
    private TextView textViewAlertErrorCSS_2;
    private ImageView imageViewAlertErrorCSS_2;

    private View custumCardViewAdjuntarDocumentosIFF;
    private TextView textViewAdjuntarEvidenciaCAE;
    private ImageView imageViewDocumentoCAE;
    private TextView textViewDocumentoCAE;
    private ImageView imageViewVerDocumentoCAE;

    private ImageView imageViewCamaraCAE;
    private TextView textViewCamaraCAE;
    private ImageView imageViewVerCamaraCAE;

    private ImageView imageViewGaleriaCAE;
    private TextView textViewGaleriaCAE;
    private ImageView imageViewVerGaleriaCAE;

    private View custumDocumentosIntegracionIFF;
    private TextView textViewListaDocumentosCDI;
    private RecyclerView recyclerViewDocumentosCDI;

    private View customListaResponsablesIFF;
    private TextView textViewListaImputadosTextCLI;
    private TextView textViewMostrarOcultarCLI;
    private RecyclerView recyclerViewListaResponsablesCLI;
    private TextView textViewTotalResponsablesTextCLI;
    private TextView textViewTotalResponsablesCLI;

    private Button buttonInicioFaseIFF;

    private View view;

    private Context context;

    private Activity activity;

    private Bundle args;

    private int mostrarListaImputados = 0;
    //private int mostrarImagenBandera = 0;

    public IniciarFaseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_iniciar_fases, container, false);
        activity = getActivity();
        context = getContext();

        refereciasConInterface(view);
        ocultarElementos();

        textViewTextCSS.setText(getString(R.string.text_label_tipo_de_documento));
        textViewAdjuntarEvidenciaCAE.setText(getString(R.string.text_label_adjuntar_evidencia));

        args = getArguments();
        if (args != null) {
            idCaso = args.getString("idCaso");
            activaServicios(activity, Integer.parseInt(idCaso));
        }
        llenadoRecyclerView(activity, listDocumentosSelectos, recyclerViewDocumentosCDI);

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

        custumCardViewDatosFaseIFF = view.findViewById(R.id.custumCardViewDatosFaseIFF);
        textViewFaseEtapaCDF = view.findViewById(R.id.textViewFaseEtapaCDF);
        textViewFaseEtapaColorCDF = view.findViewById(R.id.textViewFaseEtapaColorCDF);
        textViewFechaCompromisoCDF = view.findViewById(R.id.textViewFechaCompromisoCDF);

        custumFechaCompromisoIFF = view.findViewById(R.id.custumFechaCompromisoIFF);
        textViewFechaCompromisoCFC = view.findViewById(R.id.textViewFechaCompromisoCFC);
        textViewFechaCompromisoAlertErrorCFC = view.findViewById(R.id.textViewFechaCompromisoAlertErrorCFC);
        imageViewFechaCompromisoAlertErrorCFC = view.findViewById(R.id.imageViewFechaCompromisoAlertErrorCFC);

        custumDatosDenuciaAndDatosAgenciaIFF = view.findViewById(R.id.custumDatosDenuciaAndDatosAgenciaIFF);
        textInputEditTextDatosDenunciaCDDDA = view.findViewById(R.id.textInputEditTextDatosDenunciaCDDDA);
        textInputEditTextDatosAgenciaCDDDA = view.findViewById(R.id.textInputEditTextDatosAgenciaCDDDA);

        custumSpinnerSelectIFF = view.findViewById(R.id.custumSpinnerSelectIFF);
        textViewTextCSS = view.findViewById(R.id.textViewTextCSS);
        spinnerCSS = view.findViewById(R.id.spinnerCSS);
        textViewAlertErrorCSS = view.findViewById(R.id.textViewAlertErrorCSS);
        imageViewAlertErrorCSS = view.findViewById(R.id.imageViewAlertErrorCSS);

        custumSpinnerSelectIFF_2 = view.findViewById(R.id.custumSpinnerSelectIFF_2);
        textViewTextCSS_2 = view.findViewById(R.id.textViewTextCSS_2);
        spinnerCSS_2 = view.findViewById(R.id.spinnerCSS_2);
        textViewAlertErrorCSS_2 = view.findViewById(R.id.textViewAlertErrorCSS_2);
        imageViewAlertErrorCSS_2 = view.findViewById(R.id.imageViewAlertErrorCSS_2);

        custumCardViewAdjuntarDocumentosIFF = view.findViewById(R.id.custumCardViewAdjuntarDocumentosIFF);
        textViewAdjuntarEvidenciaCAE = view.findViewById(R.id.textViewAdjuntarEvidenciaCAE);

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

        custumDocumentosIntegracionIFF = view.findViewById(R.id.custumDocumentosIntegracionIFF);
        textViewListaDocumentosCDI = view.findViewById(R.id.textViewListaDocumentosCDI);
        recyclerViewDocumentosCDI = view.findViewById(R.id.recyclerViewDocumentosCDI);

        customListaResponsablesIFF = view.findViewById(R.id.customListaResponsablesIFF);
        textViewListaImputadosTextCLI = view.findViewById(R.id.textViewListaImputadosTextCLI);
        textViewMostrarOcultarCLI = view.findViewById(R.id.textViewMostrarOcultarCLI);
        textViewMostrarOcultarCLI.setOnClickListener(this);
        recyclerViewListaResponsablesCLI = view.findViewById(R.id.recyclerViewListaResponsablesCLI);
        textViewTotalResponsablesTextCLI = view.findViewById(R.id.textViewTotalResponsablesTextCLI);
        textViewTotalResponsablesCLI = view.findViewById(R.id.textViewTotalResponsablesCLI);

        buttonInicioFaseIFF = view.findViewById(R.id.buttonInicioFaseIFF);
        buttonInicioFaseIFF.setOnClickListener(this);
    }

    public void ocultarElementos() {
        textViewPorcentajeGeneralDenunciaCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
        textViewFechaCompromisoFaseCDD.setVisibility(View.GONE);
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);

        custumCardViewDatosFaseIFF.setVisibility(View.GONE);

        textViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);
        imageViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);

        custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);

        custumSpinnerSelectIFF.setVisibility(View.GONE);

        custumSpinnerSelectIFF_2.setVisibility(View.GONE);

        custumCardViewAdjuntarDocumentosIFF.setVisibility(View.GONE);
        imageViewVerDocumentoCAE.setVisibility(View.GONE);
        imageViewVerCamaraCAE.setVisibility(View.GONE);
        imageViewVerGaleriaCAE.setVisibility(View.GONE);

        custumDocumentosIntegracionIFF.setVisibility(View.GONE);
        textViewListaDocumentosCDI.setVisibility(View.GONE);

        recyclerViewListaResponsablesCLI.setVisibility(View.GONE);
    }

    public void activaServicios(Activity activity, int idCaso) {
        getEstatusResponsable(activity);
        getDatosCasos(activity, idCaso);
        getIntegracionDoc(activity);
        getStatusSentencia(activity);
        getObtenerConfiguracionFormatImages(activity);
        getObtenerConfiguracionFileMaxSize(activity);
        getObtenerConfiguracionFormatDocuments(activity);
        getObtenerConfiguracionTipoAppMovil(activity);
        getObtenerConfiguracionPhotoNumber(activity);
    }

    private void llenadoRecyclerView(Activity activity, ArrayList<Documentos> list, RecyclerView recyclerView) {
        documentosInicioSubfaseAdapter = new DocumentosInicioSubfaseAdapter(activity, list, new DocumentosInicioSubfaseAdapter.OnItemSelectedListener() {
            @Override
            public void onEliminarListener(Documentos documentoRequest, int position) {
                showAlertDialogEliminarDocumentos(activity, getString(R.string.text_label_liminar_documento), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), position);
            }

            @Override
            public void onVerListener(Documentos documentoRequest, int position) {
                if (/*extension.equals(doc) || extension.equals(docx)*/ documentoRequest.getTipoArchivo().equals(".docx") || documentoRequest.getTipoArchivo().equals("docx") || documentoRequest.getTipoArchivo().equals(".doc") || documentoRequest.getTipoArchivo().equals("doc")) {
                    //verDocumentos(activityTPDF, mPath);
                    File file = new File(documentoRequest.getmPath());
                    abrirDocumentoWord(activity, file);
                } else {
                    //mostrarImagenBandera= 1;
                    showZoomImage(activity, documentoRequest.getStringArchivo(), documentoRequest.getmPath(), documentoRequest.getTipoArchivo(), 1);
                }
            }
        });

        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManagerCategory);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(documentosInicioSubfaseAdapter);
    }

    private void getDatosCasos(Activity activity, int idCaso) {
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
                            getDatosDenuncia(respuestaGeneral.getDatosDenuncia());
                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getDatosDenuncia().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.ObtenerDatosCaso, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDatosDenuncia(DatosDenuncia datosDenuncia) {

        idCasoFase = String.valueOf(datosDenuncia.getIdCasoFase());
        idFase = String.valueOf(datosDenuncia.getIdFase());

        if (datosDenuncia.getIdSubFase() != null) {
            idSubFase = String.valueOf(datosDenuncia.getIdSubFase());
        } else {
            idSubFase = null;
        }

        if (datosDenuncia.getIdFase().equals(2)) {
            custumCardViewDatosFaseIFF.setVisibility(View.GONE);
            custumFechaCompromisoIFF.setVisibility(View.VISIBLE);
            custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.VISIBLE);
        } else if (datosDenuncia.getIdFase().equals(5)) {
            custumCardViewDatosFaseIFF.setVisibility(View.VISIBLE);
            custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
            custumSpinnerSelectIFF_2.setVisibility(View.VISIBLE);
            custumFechaCompromisoIFF.setVisibility(View.GONE);
            custumDatosDenuciaAndDatosAgenciaIFF.setVisibility(View.GONE);
        } /*else {
            custumFechaCompromisoIFF.setVisibility(View.VISIBLE);
        }*/

        if (datosDenuncia.getSubFase() != null) {
            textViewSubTiutuloCST.setText(getString(R.string.text_label_inicio).concat(" - ").concat(datosDenuncia.getSubFase()));
        } else {
            textViewSubTiutuloCST.setText(getString(R.string.text_label_inicio).concat(" - ").concat(datosDenuncia.getFase()));
        }

        linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(datosDenuncia.getColorEtapaCaso()));
        textViewFolioDenunciaCDD.setText(datosDenuncia.getFolio());
        textViewNombreDenunciaCDD.setText(datosDenuncia.getNombre());
        textViewTipoDelitoCDD.setText(datosDenuncia.getTipoFraude());
        textViewTipoDenunciaCDD.setText(datosDenuncia.getTipoDenuncia());
        textViewUnidadNegocioCDD.setText(datosDenuncia.getUdN().concat(" - ").concat(datosDenuncia.getUdNCeco()));
        textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(datosDenuncia.getFechaRegistro()));
        textViewZonaCDD.setText(datosDenuncia.getRegion().concat(" - ").concat(datosDenuncia.getZona()));

        if (datosDenuncia.getIdSubFase() != null) {
            custumCardViewDatosFaseIFF.setVisibility(View.VISIBLE);
            custumFechaCompromisoIFF.setVisibility(View.GONE);

            if (datosDenuncia.getIdSubFase().equals(1)) {
                custumSpinnerSelectIFF.setVisibility(View.VISIBLE);
            } else {
                custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
            }
        }

        if (datosDenuncia.getSubFase() != null) {
            textViewFaseEtapaCDF.setText(datosDenuncia.getSubFase());
        } else {
            textViewFaseEtapaCDF.setText(datosDenuncia.getFase());
        }

        if (datosDenuncia.getColorSubFase() != null) {
            textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(datosDenuncia.getColorSubFase()));
        } else {
            textViewFaseEtapaColorCDF.setBackground(Utils.cambiarColorTextView(datosDenuncia.getColorFase()));
        }

        if (datosDenuncia.getEtapaSubFase() != null) {
            textViewFaseEtapaColorCDF.setText(datosDenuncia.getEtapaSubFase());
        } else {
            textViewFaseEtapaColorCDF.setText(datosDenuncia.getEtapaFase());
        }

        if (datosDenuncia.getFechaCompromiso() != null) {
            try {
                fechaCompromiso = Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(datosDenuncia.getFechaCompromiso())));
            textViewFechaCompromisoCDF.setTextColor(activity.getColor(R.color.green_secondary));

            textViewFechaCompromisoCFC.setText(Utils.SetCambioFormatoFechaDiaMesAnio(datosDenuncia.getFechaCompromiso()));
        } else {
            fechaCompromiso = null;
            textViewFechaCompromisoCDF.setVisibility(View.GONE);

            textViewFechaCompromisoCFC.setVisibility(View.GONE);
        }
        textViewListaImputadosTextCLI.setText("Lista de ".concat(datosDenuncia.getEtiquetaResponsables()));
        textViewTotalResponsablesTextCLI.setText("Total de ".concat(datosDenuncia.getEtiquetaResponsables()));
        textViewTotalResponsablesCLI.setText(String.valueOf(datosDenuncia.getTotalResponsables()));

        estatusResponsablesInicioAdapter = new EstatusResponsablesInicioAdapter(activity, datosDenuncia.getListResponsables(), listEstatusResponsableFase, new EstatusResponsablesInicioAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int idCasoResponsable, int idStatusResponsable) {
                if (idStatusResponsable != 0) {
                    listResponsablesFase.add(new ResponsablesFase(idCasoFase, idCasoResponsable, idStatusResponsable));
                    //                                            IdCasoFase, IdCasoResponsable, IdStatusResponsable
                }
            }
        });
        recyclerViewListaResponsablesCLI.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
        recyclerViewListaResponsablesCLI.setLayoutManager(layoutManagerCategory);
        recyclerViewListaResponsablesCLI.setNestedScrollingEnabled(false);
        recyclerViewListaResponsablesCLI.setAdapter(estatusResponsablesInicioAdapter);
    }

    private void getIntegracionDoc(Activity activity) {
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
    }

    public void llenadoSpinnerIntegracionDoc(Activity activity, List<IntegracionDocData> integracionDocList) {
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
                    custumDocumentosIntegracionIFF.setVisibility(View.GONE);
                    idIntegracionDoc = listIntegracionDoc.get(position).getId();
                    tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                } else if (listIntegracionDoc.get(position).getId() >= 1) {
                    textViewAlertErrorCSS.setVisibility(View.GONE);
                    imageViewAlertErrorCSS.setVisibility(View.GONE);
                    custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
                    custumDocumentosIntegracionIFF.setVisibility(View.VISIBLE);
                    idIntegracionDoc = listIntegracionDoc.get(position).getId();
                    tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getEstatusResponsable(Activity activity) {
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

    private void getStatusSentencia(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        llenadoSpinnerStatusSentencia(activity, respuestaGeneral.getEstatusSentencia().getListDataEstatusSentencia());
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoStatusSentencia);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void llenadoSpinnerStatusSentencia(Activity activity, List<DataEstatusSentencia> list) {
        listDataEstatusSentencia.clear();
        listDataEstatusSentencia.add(new DataEstatusSentencia(Constantes.selecionar, "", 0, 0));
        listDataEstatusSentencia.addAll(list);

        ArrayAdapter<DataEstatusSentencia> myAdapter = new EstatusSentenciaArrayAdapter(activity, R.layout.cell_spinner_item, listDataEstatusSentencia);
        spinnerCSS_2.setAdapter(myAdapter);
        spinnerCSS_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listIntegracionDoc.get(position).getId() == 0) {
                    textViewAlertErrorCSS_2.setVisibility(View.GONE);
                    imageViewAlertErrorCSS_2.setVisibility(View.GONE);
                    //textViewAlertErrorCSS_2.setVisibility(View.GONE);
                    //custumDocumentosIntegracionIFF.setVisibility(View.GONE);
                    idStatusSentencia = String.valueOf(listDataEstatusSentencia.get(position).getId());
                    //tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                } else if (listIntegracionDoc.get(position).getId() >= 1) {
                    textViewAlertErrorCSS_2.setVisibility(View.GONE);
                    imageViewAlertErrorCSS_2.setVisibility(View.GONE);
                    custumCardViewAdjuntarDocumentosIFF.setVisibility(View.VISIBLE);
                    //custumDocumentosIntegracionIFF.setVisibility(View.VISIBLE);
                    idStatusSentencia = String.valueOf(listDataEstatusSentencia.get(position).getId());
                    //tipoDocumento = listIntegracionDoc.get(position).getDescripcion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewMostrarOcultarCLI:
                if (mostrarListaImputados == 0) {
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_ocultar));
                    recyclerViewListaResponsablesCLI.setVisibility(View.VISIBLE);
                    mostrarListaImputados = 1;
                } else {
                    textViewMostrarOcultarCLI.setText(getString(R.string.text_label_mostrar));
                    recyclerViewListaResponsablesCLI.setVisibility(View.GONE);
                    mostrarListaImputados = 0;
                }
                break;

            case R.id.imageViewDocumentoCAE:
            case R.id.textViewDocumentoCAE:
                if (idSubFase != null) {
                    if (idSubFase.equals("1")) {
                        cargarArchivo();
                    } else {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 2, v);
                        } else {
                            cargarArchivo();
                        }
                    }
                } else {
                    if (idFase.equals("2")) {

                    } else if (idFase.equals("5")) {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 1, v);
                        } else {
                            cargarImagenGaleria("png");
                        }
                    }
                }

                break;

            case R.id.imageViewCamaraCAE:
            case R.id.textViewCamaraCAE:
                if (idSubFase != null) {
                    if (idSubFase.equals("1")) {
                        showDialogfotos(activity);
                    } else {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 3, v);
                        } else {
                            listDocumentosSelectos.clear();
                            showDialogfotos(activity);
                        }
                    }
                } else {
                    if (idFase.equals("2")) {

                    } else if (idFase.equals("5")) {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 3, v);
                        } else {
                            listDocumentosSelectos.clear();
                            showDialogfotos(activity);
                        }
                    }
                }

                break;

            case R.id.imageViewGaleriaCAE:
            case R.id.textViewGaleriaCAE:
                if (idSubFase != null) {
                    if (idSubFase.equals("1")) {
                        cargarImagenGaleria("png");
                    } else {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 1, v);
                        } else {
                            cargarImagenGaleria("png");
                        }
                    }
                } else {
                    if (idFase.equals("2")) {

                    } else if (idFase.equals("5")) {
                        if (stringBase64Documento != null) {
                            showAlertDialogSeleccionarEvidencia(activity, getString(R.string.text_label_evidencia), getString(R.string.text_label_se_eleminara_la_evidencia_anterior).concat(getString(R.string.text_label_pregunta_general)), getString(R.string.text_label_si), getString(R.string.text_label_no), 1, v);
                        } else {
                            cargarImagenGaleria("png");
                        }
                    }
                }

                break;

            case R.id.imageViewVerGaleriaCAE:
            case R.id.imageViewVerCamaraCAE:
            case R.id.imageViewVerDocumentoCAE:
                if (extension.equals(".docx") || extension.equals("docx") || extension.equals(".doc") || extension.equals("doc")) {
                    //verDocumentos(activityTPDF, mPath);
                    File file = new File(mPath);
                    abrirDocumentoWord(activity, file);
                } else {
                    showZoomImage(activity, stringBase64Documento, mPath, extension, 2);
                }
                break;

            case R.id.buttonInicioFaseIFF:
                if (idSubFase != null) {
                    if (idSubFase.equals("1")) {
                        if (idCaso.isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso));
                        } else if (String.valueOf(idCasoFase).isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
                        } else if (String.valueOf(idIntegracionDoc).isEmpty() || idIntegracionDoc == 0) {
                            textViewAlertErrorCSS.setVisibility(View.VISIBLE);
                            imageViewAlertErrorCSS.setVisibility(View.VISIBLE);
                            textViewAlertErrorCSS.setText(getString(R.string.text_label_seleccione_al_menos_un_tipo_de_documento));
                            Utils.messageShort(activity, getString(R.string.text_label_seleccione_al_menos_un_tipo_de_documento));
                        } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
                            Utils.messageShort(activity, getString(R.string.text_label_agrege_al_menos_un_de_documento));
                        } else {
                            if (Functions.isNetworkAvailable(activity)) {
                                showAlertDialogInicioSubFase(activity, getString(R.string.text_label_inicio_de_subfase), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                        valorDeConfiguraciontipoAppMovil, Integer.parseInt(idCasoFase), listDocumentosSelectos, listResponsablesFase);
                            } else {
                                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                            }
                        }
                    } else /*if (idSubFase.equals("2"))*/ {
                        if (idCaso.isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso));
                        } else if (String.valueOf(idCasoFase).isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
                        } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
                            Utils.messageShort(activity, getString(R.string.text_label_agrege_al_menos_un_de_documento));
                        } else {
                            if (Functions.isNetworkAvailable(activity)) {
                                showAlertDialogInicioSubFase(activity, getString(R.string.text_label_inicio_de_subfase), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                        valorDeConfiguraciontipoAppMovil, Integer.parseInt(idCasoFase), listDocumentosSelectos, listResponsablesFase);
                            } else {
                                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                            }
                        }
                    }
                } else {
                    if (idFase.equals("2")) {
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
                        } else {
                            if (Functions.isNetworkAvailable(activity)) {
                                showAlertDialogInicioFase(activity, view, getString(R.string.text_label_inicio_de_presentacion), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                        valorDeConfiguraciontipoAppMovil, Integer.parseInt(idCaso), idFase, Integer.parseInt(idCasoFase), null, datosDemanda, datosAgencia, fechaCompromiso, listResponsablesFase, null);
                                //                               tipoApp                   idCaso   idFase                    idCasoFase   idStatusSentencia  datosDemanda  datosAgencia  fechaCompromiso  listResposables        listDocumentos
                            } else {
                                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                            }
                        }
                    } else if (idFase.equals("5")) {
                        /*if (idStatusSentencia.isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso));
                        } else*/
                        if (String.valueOf(idStatusSentencia).isEmpty() || idStatusSentencia.equals("0")) {
                            textViewAlertErrorCSS_2.setVisibility(View.VISIBLE);
                            imageViewAlertErrorCSS_2.setVisibility(View.VISIBLE);
                            textViewAlertErrorCSS_2.setText(getString(R.string.text_label_seleccione_al_menos_una_sentencia));
                            Utils.messageShort(activity, getString(R.string.text_label_seleccione_al_menos_una_sentencia));
                        } else if (String.valueOf(idCasoFase).isEmpty()) {
                            Utils.messageShort(activity, getString(R.string.text_label_id_caso_face));
                        } else if (listDocumentosSelectos.isEmpty() || listDocumentosSelectos == null) {
                            Utils.messageShort(activity, getString(R.string.text_label_agrege_al_menos_un_de_documento));
                        } else {
                            if (Functions.isNetworkAvailable(activity)) {
                                showAlertDialogInicioFase(activity, v, getString(R.string.text_label_inicio_de_presentacion), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                                        valorDeConfiguraciontipoAppMovil, 0, idFase, Integer.parseInt(idCasoFase), idStatusSentencia, null, null, null, listResponsablesFase, listDocumentosSelectos);
                                //        tipoApp                          idCaso  idFase                    idCasoFase   idStatusSentencia      datosDemanda     datosAgencia     fechaCompromiso       listResposables          listDocumentos
                            } else {
                                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
                            }
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
    }

    public void stringsToNull() {
        uriImagenOrPdf = null;
        mPath = null;
        nombreDeArchivoFoto = null;
        extension = null;
        stringBase64Documento = null;
        listDocumentosSelectos.clear();
    }


    public void showAlertDialogInicioFase(Activity activity, View view, String titulo, String mensaje, String positivoMensaje, String negativoMensaje, String tipoApp, int idCaso, String idFase, int idCasoFase, String idStatusSentencia, String datosDemanda, String datosAgencia, String fechaCompromiso, List<ResponsablesFase> listResponsablesFase, List<Documentos> documentos) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                setIniciarFase(activity, view, tipoApp, idCaso, idFase, idCasoFase, idStatusSentencia, datosDemanda, datosAgencia, fechaCompromiso, listResponsablesFase, documentos);
                //                             tipoApp  idCaso  idFase  idCasoFase  idStatusSentencia  datosDemanda  datosAgencia  fechaCompromiso  listResponsablesFase listDocumentos
            }
        });

        dialogo1.setNegativeButton(negativoMensaje, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void setIniciarFase(Activity activity, View view, String tipoApp, int idCaso, String idFase, int idCasoFase, String idStatusSentencia, String datosDemanda, String datosAgencia, String fechaCompromiso, List<ResponsablesFase> listRespondablesFase, List<Documentos> documentos) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = null;
                if (idFase.equals("2")) {
                    params = gsonParams.toJson(new InicioFase(new Denuncia(idCaso, datosDemanda, datosAgencia), new Fase(idCasoFase, fechaCompromiso), listRespondablesFase));
                    //                                                     idCaso  datosDemanda  datosAgencia            idCasoFase  fechaCompromiso   listRespondablesFase
                } else if (idFase.equals("5")) {
                    params = gsonParams.toJson(new InicioFase(Integer.parseInt(tipoApp), new Denuncia(Integer.parseInt(idStatusSentencia)), new Fase(idCasoFase), listRespondablesFase, documentos));
                    //                                                                                                 idStatusSentencia             idCasoFase   listRespondablesFase
                }
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        //Serial serial = gson.fromJson(result, Serial.class);
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getIniciarFase().getExito().equals(Constantes.exitoTrue)) {
                            showDialogInicioFaseConExito(activity, view, getString(R.string.text_label_inicio), getString(R.string.text_label_se_ha_iniciado_esta_fase_con_exito), getString(R.string.text_label_aceptar), String.valueOf(respuestaGeneral.getIniciarFase().getIdCaso()));
                        } else {
                            Utils.message(activity, respuestaGeneral.getIniciarFase().getError());
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

    public void showDialogInicioFaseConExito(Activity activity, View view, String titulo, String mensaje, String butonText, String idCaso) {
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

    public void showAlertDialogInicioSubFase(Activity activity, String titulo, String mensaje, String positivoMensaje, String negativoMensaje,
                                             String tipoApp, int idCasoFase, List<Documentos> documentos, List<ResponsablesFase> listResposablesPresentacion) {
        //                                          tipoApp      idCasoFase                   documentos                         listResposables
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
    }

    public void setIniciarSubFase(Activity activity, String tipoApp, int idCasoFase, List<Documentos> documentos, List<ResponsablesFase> listResposablesPresentacion) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new InicioSubFase(Integer.parseInt(tipoApp), new SubFase(idCasoFase), listResposablesPresentacion, documentos));
                //                                                                   tipoApp               idCasoFase   listRespondablesFase         listDocumentos
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        //Serial serial = gson.fromJson(result, Serial.class);
                        if (respuestaGeneral.getIniciarFase().getExito().equals(Constantes.exitoTrue)) {
                            showDialogInicioFaseConExito(activity, view, getString(R.string.text_label_exito), getString(R.string.text_label_se_ha_inciado_esta_subfase_exitosamente), getString(R.string.text_label_aceptar), String.valueOf(respuestaGeneral.getIniciarFase().getIdCaso()));
                        } else {
                            Utils.message(activity, respuestaGeneral.getIniciarFase().getError());
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

    public void showAlertDialogEliminarDocumentos(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
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

    public void showZoomImage(Activity activity, String documentoEnBase64, String pathDocumento, String extension, int bandera) {
        Dialog dialogAdjuntarDocumentos = new Dialog(activity, R.style.CustomDialogTheme);
        dialogAdjuntarDocumentos.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAdjuntarDocumentos.setCancelable(false);
        dialogAdjuntarDocumentos.setContentView(R.layout.dialog_zoom_image);
        Objects.requireNonNull(dialogAdjuntarDocumentos.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Button buttonCerrarDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonCerrarDZI);
        Button buttonEliminarEvidenciaDZI = dialogAdjuntarDocumentos.findViewById(R.id.buttonEliminarEvidenciaDZI);
        ImageView imageViewViewImageDZI = dialogAdjuntarDocumentos.findViewById(R.id.imageViewViewImageDZI);
        PDFView pdfView = dialogAdjuntarDocumentos.findViewById(R.id.pdfView);

        buttonEliminarEvidenciaDZI.setVisibility(View.GONE);
        if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {

            pdfView.setVisibility(View.GONE);
            imageViewViewImageDZI.setVisibility(View.VISIBLE);
            if (bandera == 1) {
                String imagenDecompres = null;
                try {
                    imagenDecompres = Utils.decompressBase64(documentoEnBase64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap imagenSinBase64 = Utils.base64ToBitmap(imagenDecompres);
                Glide.with(activity).load(/*Utils.rotateImage(*/imagenSinBase64/*, -90)*/).fitCenter().into(imageViewViewImageDZI);
            } else if (bandera == 2) {
                Glide.with(activity).load(Utils.base64ToBitmap(documentoEnBase64)).fitCenter().into(imageViewViewImageDZI);
            }
        } else if (extension.equals(".pdf") || extension.equals("pdf")) {
            pdfView.setVisibility(View.VISIBLE);
            imageViewViewImageDZI.setVisibility(View.GONE);
            File file = new File(pathDocumento);
            pdfView.fromFile(file).load();
        }

        buttonCerrarDZI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdjuntarDocumentos.dismiss();
            }
        });

        dialogAdjuntarDocumentos.show();
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
                        stringBase64Documento = Utils.bitmapToBase64(bitmapImageFoto, extension);
                        stringCompressDocumento = Utils.compressBase64(stringBase64Documento);
                        if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                            listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64Documento, mPath, tipoDocumento));
                        } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                            listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumento, mPath, tipoDocumento));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (idSubFase != null) {
                        if (idSubFase.equals("1")) {
                            custumDocumentosIntegracionIFF.setVisibility(View.VISIBLE);
                            textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                            recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                            documentosInicioSubfaseAdapter.notifyDataSetChanged();
                        } else /*if (idSubFase.equals("2"))*/ {
                            imageViewVerGaleriaCAE.setVisibility(View.VISIBLE);
                            imageViewVerCamaraCAE.setVisibility(View.GONE);
                            imageViewVerDocumentoCAE.setVisibility(View.GONE);
                        }
                    } else {
                        if (idFase.equals("2")) {

                        } else if (idFase.equals("5")) {
                            imageViewVerGaleriaCAE.setVisibility(View.VISIBLE);
                            imageViewVerCamaraCAE.setVisibility(View.GONE);
                            imageViewVerDocumentoCAE.setVisibility(View.GONE);
                        }
                    }

                } else {
                    Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                }

            } else if (requestCode == Constantes.RESPUESTA_DE_ALMACENAMIENTO) {

                if (data != null) {
                    uriImagenOrPdf = data.getData();

                    if (valorDeConfiguracionFileMaxSize >= Double.parseDouble(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf))) {

                        mPath = getPath(activity, uriImagenOrPdf);

                        try {
                            nombreDeArchivoFoto = Utils.getNombreUriDocumentos(context, uriImagenOrPdf);
                            extension = Utils.getExtensionArchivos(nombreDeArchivoFoto);
                            stringBase64Documento = Utils.fileToBase64(activity, uriImagenOrPdf);
                            stringCompressDocumento = Utils.compressBase64(stringBase64Documento);

                            if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                                listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringBase64Documento, mPath, tipoDocumento));
                            } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                                listDocumentosSelectos.add(new Documentos(Utils.getNombreDocumentos(nombreDeArchivoFoto), String.valueOf(idIntegracionDoc), extension, Integer.parseInt(Utils.getTamanioUriDocumentos(activity, uriImagenOrPdf)), stringCompressDocumento, mPath, tipoDocumento));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        /*if (idSubFase.equals("1")) {
                            custumDocumentosIntegracionIFF.setVisibility(View.VISIBLE);
                            textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                            recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                            documentosInicioSubfaseAdapter.notifyDataSetChanged();
                        } else *//*if (idSubFase.equals("2"))*//* {
                            imageViewVerGaleriaCAE.setVisibility(View.GONE);
                            imageViewVerCamaraCAE.setVisibility(View.GONE);
                            imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);
                        }*/
                        if (idSubFase != null) {
                            if (idSubFase.equals("1")) {
                                custumDocumentosIntegracionIFF.setVisibility(View.VISIBLE);
                                textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                                recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                                documentosInicioSubfaseAdapter.notifyDataSetChanged();
                            } else /*if (idSubFase.equals("2"))*/ {
                                imageViewVerGaleriaCAE.setVisibility(View.GONE);
                                imageViewVerCamaraCAE.setVisibility(View.GONE);
                                imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (idFase.equals("2")) {

                            } else if (idFase.equals("5")) {
                                imageViewVerGaleriaCAE.setVisibility(View.GONE);
                                imageViewVerCamaraCAE.setVisibility(View.GONE);
                                imageViewVerDocumentoCAE.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        Utils.messageShort(activity, descripcionConfiguracionFileMaxSize);
                    }
                }
            } else if (requestCode == Constantes.RESPUESTA_DE_CAMARA) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    galleryAddPic();
                    setPic();
                } else {
                    MediaScannerConnection.scanFile(activity,
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
                    galeriaFotosAdapter.notifyDataSetChanged();
                }
            }
        }
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

    public String getPath(final Context context, final Uri uri) {
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

    public void GeneratePDF(Dialog dialog) {
        if (write()) {
            dialog.dismiss();
            Toast.makeText(activity, "Archivo PDF creado correctamente", Toast.LENGTH_LONG).show();
            File file = new File(mPath);
            try {
                stringBase64Documento = Utils.fileToBase64(activity, Uri.fromFile(file));
                stringCompressDocumento = Utils.compressBase64(stringBase64Documento);
                if (valorDeConfiguraciontipoAppMovil.equals("1")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, String.valueOf(idIntegracionDoc), extension, Integer.parseInt(tamanio), stringBase64Documento, mPath, tipoDocumento));
                } else if (valorDeConfiguraciontipoAppMovil.equals("2")) {
                    listDocumentosSelectos.add(new Documentos(nombreFoto, String.valueOf(idIntegracionDoc), extension, Integer.parseInt(tamanio), stringCompressDocumento, mPath, tipoDocumento));
                }
                listGaleriaFotos.clear();
                if (idSubFase != null) {
                    if (idSubFase.equals("1")) {
                        textViewListaDocumentosCDI.setVisibility(View.VISIBLE);
                        recyclerViewDocumentosCDI.setVisibility(View.VISIBLE);
                        documentosInicioSubfaseAdapter.notifyDataSetChanged();
                    } else /*if (idSubFase.equals("2"))*/ {
                        imageViewVerGaleriaCAE.setVisibility(View.GONE);
                        imageViewVerCamaraCAE.setVisibility(View.VISIBLE);
                        imageViewVerDocumentoCAE.setVisibility(View.GONE);
                    }
                }/* else {

                }*/
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

}