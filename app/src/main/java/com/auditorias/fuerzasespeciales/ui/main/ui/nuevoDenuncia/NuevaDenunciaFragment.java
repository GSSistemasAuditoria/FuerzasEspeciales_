package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoDenuncia.DataTipoDenuncia;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoEmpleado.TipoEmpleado;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoFraude.TipoFraude;
import com.auditorias.fuerzasespeciales.models.catalogos.unidadDeNegocio.UnidadDeNegocio;
import com.auditorias.fuerzasespeciales.models.datosUsuario.Empleado;
import com.auditorias.fuerzasespeciales.request.CasoRequest;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.auditorias.fuerzasespeciales.request.denuncia.NuevaDenuncia;
import com.auditorias.fuerzasespeciales.request.empleados.empledos;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.AutocompleteBaseAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.TipoDelitoArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.TipoDenunciaArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.TotalEmpleadosAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.UnidadNegocioArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.dialogs.DatePickerDialogFragment;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NuevaDenunciaFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = NuevaDenunciaFragment.class.getName();

    private final List<UnidadDeNegocio> listUnidadNegocio = new ArrayList<>();
    private final List<TipoFraude> listTipoFraude = new ArrayList<>();
    private final List<ResponsablesResquest> listNombreResponsables = new ArrayList<>();
    private final List<TipoEmpleado> listTipoEmpleado = new ArrayList<>();

    private final List<DataTipoDenuncia> listTipoDenuncia = new ArrayList<>();

    private TextView textViewSubTiutuloCST;

    private TextView textViewNombreAbogadoNDF;
    private TextView textViewCecoAbogadoNDF;
    private TextView textViewRegionAbogadoNDF;
    private TextView textViewZonaAbogadoNDF;

    private TextView textViewFechaCompromisoTextCFC;
    private TextView textViewFechaCompromisoCFC;
    private ImageButton imageButtonFechaCompromisoCFC;
    private TextView textViewFechaCompromisoAlertErrorCFC;
    private ImageView imageViewFechaCompromisoAlertErrorCFC;

    private TextView textViewTextCSS;
    private Spinner spinnerTipoDenunciaCSS;
    private TextView textViewTipoDenunciaAlertErrorCSS;
    private ImageView imageViewTipoDenunciaAlertErrorCSS;

    private Spinner spinnerUnidadNegocioNDF;
    private TextView textViewUnidadNegocioAlertaErrorNDF;
    private ImageView imageViewUnidadNegocioAlertaErrorNDF;
    private TextView textViewCecoUnidadNegicioTextNDF;
    private TextView textViewCecoUnidadNegocioNDF;

    private Spinner spinnerTipoFraudeNDF;
    private TextView textViewTipoFraudeAlertErrorNDF;
    private ImageView imageViewTipoFraudeAlertaErrorNDF;

    private TextInputEditText textInputEditTextNombreDenunciaNDF;
    private TextInputEditText textInputEditTextDescripcionNDF;

    private TextInputEditText textInputEditTextImporteNDF;
    private TextInputEditText textInputEditTextRecuperadoNDF;

    private TextView textViewResponsablesTextCNR;
    private TextView textViewResponsableCNR;
    private ImageButton imageButtonResponsableCNR;
    private TextView textViewResponsableAlertErrorCNR;
    private ImageView imageViewResponsableAlertErrorCNR;

    private TextView textViewListaResponsablesNDF;
    private RecyclerView recyclerViewListaRespondablesNDF;

    private TextView textViewTotalResponsablesTextNDF;
    private TextView textViewTotalResponsablesNDF;

    private Button buttonGuardarDenunciaNDF;

    private View view;

    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;

    private DatePickerDialogFragment datePickerDialogFragment;

    private TotalEmpleadosAdapter totalEmpleadosAdapter;
    private AutocompleteBaseAdapter autocompleteBaseAdapter;
    private int idUdN;
    private int idTipoFraude;
    private int idTipoDenuncia;
    private String etiquetaResponsables;
    private String tipoResponsable;
    private int idUsuario;
    private int idRegion;
    private String valorDeConfiguracionFaseInicialCaso;
    private String descripcionConfiguracionFaseInicialCaso;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nueva_denuncia, container, false);
        context = getContext();
        activity = getActivity();
        fragmentManager = getFragmentManager();
        datePickerDialogFragment = new DatePickerDialogFragment();

        refereciasConInterface(view);
        ocultarElementos();

        textViewSubTiutuloCST.setText(getString(R.string.title_nuevas_denuncias));
        textViewFechaCompromisoTextCFC.setText(getString(R.string.text_label_fecha_reporte_dos_puntos));
        textViewTextCSS.setText(getString(R.string.text_label_tipo_de_denuncia_dos_puntos));

        servicios(activity, view);
        cargarRecyclerView(activity, listNombreResponsables);

        textInputEditTextImporteNDF.addTextChangedListener(Utils.amount(activity, textInputEditTextImporteNDF));
        textInputEditTextRecuperadoNDF.addTextChangedListener(Utils.amount(activity, textInputEditTextRecuperadoNDF));

        return view;
    }

    public void refereciasConInterface(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);

        textViewNombreAbogadoNDF = view.findViewById(R.id.textViewNombreAbogadoNDF);
        textViewCecoAbogadoNDF = view.findViewById(R.id.textViewCecoAbogadoNDF);
        textViewRegionAbogadoNDF = view.findViewById(R.id.textViewRegionAbogadoNDF);
        textViewZonaAbogadoNDF = view.findViewById(R.id.textViewZonaAbogadoNDF);

        textViewFechaCompromisoTextCFC = view.findViewById(R.id.textViewFechaCompromisoTextCFC);
        textViewFechaCompromisoCFC = view.findViewById(R.id.textViewFechaCompromisoCFC);
        textViewFechaCompromisoCFC.setOnClickListener(this);
        imageButtonFechaCompromisoCFC = view.findViewById(R.id.imageButtonFechaCompromisoCFC);
        imageButtonFechaCompromisoCFC.setOnClickListener(this);
        textViewFechaCompromisoAlertErrorCFC = view.findViewById(R.id.textViewFechaCompromisoAlertErrorCFC);
        imageViewFechaCompromisoAlertErrorCFC = view.findViewById(R.id.imageViewFechaCompromisoAlertErrorCFC);

        textViewTextCSS = view.findViewById(R.id.textViewTextCSS);
        spinnerTipoDenunciaCSS = view.findViewById(R.id.spinnerCSS);
        textViewTipoDenunciaAlertErrorCSS = view.findViewById(R.id.textViewAlertErrorCSS);
        imageViewTipoDenunciaAlertErrorCSS = view.findViewById(R.id.imageViewAlertErrorCSS);

        spinnerUnidadNegocioNDF = view.findViewById(R.id.spinnerUnidadNegocioNDF);
        textViewUnidadNegocioAlertaErrorNDF = view.findViewById(R.id.textViewUnidadNegocioAlertaErrorNDF);
        imageViewUnidadNegocioAlertaErrorNDF = view.findViewById(R.id.imageViewUnidadNegocioAlertaErrorNDF);
        textViewCecoUnidadNegicioTextNDF = view.findViewById(R.id.textViewCecoUnidadNegicioTextNDF);
        textViewCecoUnidadNegocioNDF = view.findViewById(R.id.textViewCecoUnidadNegocioNDF);

        spinnerTipoFraudeNDF = view.findViewById(R.id.spinnerTipoFraudeNDF);
        textViewTipoFraudeAlertErrorNDF = view.findViewById(R.id.textViewTipoFraudeAlertErrorNDF);
        imageViewTipoFraudeAlertaErrorNDF = view.findViewById(R.id.imageViewTipoFraudeAlertaErrorNDF);

        textInputEditTextNombreDenunciaNDF = view.findViewById(R.id.textInputEditTextNombreDenunciaNDF);
        textInputEditTextDescripcionNDF = view.findViewById(R.id.textInputEditTextDescripcionNDF);

        textInputEditTextImporteNDF = view.findViewById(R.id.textInputEditTextImporteNDF);
        textInputEditTextRecuperadoNDF = view.findViewById(R.id.textInputEditTextRecuperadoNDF);

        textViewResponsablesTextCNR = view.findViewById(R.id.textViewResponsablesTextCNR);
        textViewResponsableCNR = view.findViewById(R.id.textViewResponsableCNR);
        textViewResponsableCNR.setOnClickListener(this);
        imageButtonResponsableCNR = view.findViewById(R.id.imageButtonResponsableCNR);
        imageButtonResponsableCNR.setOnClickListener(this);
        textViewResponsableAlertErrorCNR = view.findViewById(R.id.textViewResponsableAlertErrorCNR);
        imageViewResponsableAlertErrorCNR = view.findViewById(R.id.imageViewResponsableAlertErrorCNR);

        textViewListaResponsablesNDF = view.findViewById(R.id.textViewListaResponsablesNDF);
        recyclerViewListaRespondablesNDF = view.findViewById(R.id.recyclerViewListaRespondablesNDF);

        textViewTotalResponsablesTextNDF = view.findViewById(R.id.textViewTotalResponsablesTextNDF);
        textViewTotalResponsablesNDF = view.findViewById(R.id.textViewTotalResponsablesNDF);

        buttonGuardarDenunciaNDF = view.findViewById(R.id.buttonGuardarDenunciaNDF);
        buttonGuardarDenunciaNDF.setOnClickListener(this);

    }

    public void ocultarElementos() {
        textViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);
        imageViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);

        textViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);
        imageViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);

        textViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
        imageViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
        textViewCecoUnidadNegicioTextNDF.setVisibility(View.GONE);
        textViewCecoUnidadNegocioNDF.setVisibility(View.GONE);

        textViewTipoFraudeAlertErrorNDF.setVisibility(View.GONE);
        imageViewTipoFraudeAlertaErrorNDF.setVisibility(View.GONE);

        textViewResponsableCNR.setVisibility(View.GONE);
        imageButtonResponsableCNR.setVisibility(View.GONE);
        textViewResponsableAlertErrorCNR.setVisibility(View.GONE);
        imageViewResponsableAlertErrorCNR.setVisibility(View.GONE);

        textViewListaResponsablesNDF.setVisibility(View.GONE);

        textViewTotalResponsablesTextNDF.setVisibility(View.GONE);
        textViewTotalResponsablesNDF.setVisibility(View.GONE);
    }

    private void servicios(Activity activity, View view){
        getObtenerDetalleUsuario(activity, view);//ya esta con respuesta general
        getTipoEmpleado(activity, view);//ya esta con respuesta general
        getObtenerCatalogoUdN(activity, view);//ya esta con respuesta general
        getObtenerCatalogoTipoFraude(activity, view);
        getTipoDenuncia(activity, view);
        getObtenerConfiguracionFaseInicialCaso(activity);
    }

    public void cargarRecyclerView(Activity activity, List<ResponsablesResquest> list){
        recyclerViewListaRespondablesNDF.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
        recyclerViewListaRespondablesNDF.setLayoutManager(layoutManagerCategory);
        recyclerViewListaRespondablesNDF.setNestedScrollingEnabled(false);

        totalEmpleadosAdapter = new TotalEmpleadosAdapter(activity, list, new TotalEmpleadosAdapter.OnClickListener() {
            @Override
            public void onItemClick(ResponsablesResquest responsablesResquest, int position) {

            }

            @Override
            public void onClickDelete(ResponsablesResquest responsablesResquest, int position) {
                showAlertDialogEliminarResponsable(activity, getString(R.string.text_label_eleminar_responsable), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), position);
            }
        });
        recyclerViewListaRespondablesNDF.setAdapter(totalEmpleadosAdapter);
    }

    private void getObtenerDetalleUsuario(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    for (int x = 0; x < respuestaGeneral.getDetalleUsuario().size(); x++) {
                        idUsuario = Integer.parseInt(respuestaGeneral.getDetalleUsuario().get(x).getIdUsuario());
                        idRegion = respuestaGeneral.getDetalleUsuario().get(x).getIdRegion();
                        textViewNombreAbogadoNDF.setText(respuestaGeneral.getDetalleUsuario().get(x).getNombre());
                        textViewCecoAbogadoNDF.setText(respuestaGeneral.getDetalleUsuario().get(x).getCeco());
                        textViewRegionAbogadoNDF.setText(respuestaGeneral.getDetalleUsuario().get(x).getRegion());
                        textViewZonaAbogadoNDF.setText(respuestaGeneral.getDetalleUsuario().get(x).getZona());
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerDetalleUsuario.concat(Constantes.signoInterrogacion).concat(Constantes.idUsuario).concat(Constantes.signoIgual).concat(TableDataUser.getIdEmpleado(activity)));
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 1);
        }
    }

    private void getTipoDenuncia(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            Gson gsonParams = new Gson();
            String params = gsonParams.toJson(new NuevaDenuncia());

            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                    if (respuestaGeneral.getTipoDenuncia().getExito().equals(Constantes.exitoTrue)) {
                        getTipoDenunciaList(activity, respuestaGeneral.getTipoDenuncia().getLisTipoDenuncia());
                    } else {
                        Utils.messageShort(activity, respuestaGeneral.getTipoDenuncia().getError());
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerTipoDenuncia, params);
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 2);
        }
    }

    public void getTipoDenunciaList(Activity activity, List<DataTipoDenuncia> list) {
        listTipoDenuncia.clear();
        listTipoDenuncia.add(new DataTipoDenuncia(Constantes.selecionar, "", 0, 0, "", 0, ""));
        listTipoDenuncia.addAll(list);

        ArrayAdapter<DataTipoDenuncia> tipoDenunciaArrayAdapter = new TipoDenunciaArrayAdapter(activity, R.layout.cell_spinner_item, listTipoDenuncia);
        spinnerTipoDenunciaCSS.setAdapter(tipoDenunciaArrayAdapter);
        spinnerTipoDenunciaCSS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listTipoDenuncia.get(position).getId() == 0) {
                    idTipoDenuncia = listTipoDenuncia.get(position).getId();
                    etiquetaResponsables = listTipoDenuncia.get(position).getEtiquetaResponsables();
                    textViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);
                    imageViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);
                    textViewResponsablesTextCNR.setVisibility(View.GONE);
                    textViewResponsableCNR.setVisibility(View.GONE);
                    imageButtonResponsableCNR.setVisibility(View.GONE);
                } else if (listTipoDenuncia.get(position).getId() >= 1) {
                    idTipoDenuncia = listTipoDenuncia.get(position).getId();
                    etiquetaResponsables = listTipoDenuncia.get(position).getEtiquetaResponsables();
                    textViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);
                    imageViewTipoDenunciaAlertErrorCSS.setVisibility(View.GONE);
                    textViewResponsablesTextCNR.setText("Agregue ".concat(etiquetaResponsables.toLowerCase()));
                    textViewListaResponsablesNDF.setText("Lista de ".concat(etiquetaResponsables.toLowerCase()));
                    textViewResponsablesTextCNR.setVisibility(View.VISIBLE);
                    textViewResponsableCNR.setVisibility(View.VISIBLE);
                    imageButtonResponsableCNR.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getTipoEmpleado(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    if (!respuestaGeneral.getListTipoEmpleado().equals(null) || !respuestaGeneral.getListTipoEmpleado().isEmpty()) {
                        listTipoEmpleado.addAll(respuestaGeneral.getListTipoEmpleado());
                    } else {
                        Utils.messageShort(activity, getString(R.string.text_label_no_se_encontraron_datos));
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoTipoEmpleado);
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 3);
        }
    }

    private void getObtenerCatalogoUdN(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    if (!respuestaGeneral.getListUnidadDeNegocio().equals(null) || !respuestaGeneral.getListUnidadDeNegocio().isEmpty()) {
                        getUnidadDeNegocioList(activity, respuestaGeneral.getListUnidadDeNegocio());
                    } else {
                        getString(R.string.text_label_no_se_encontraron_datos);
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoUdN);
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 4);
        }
    }

    public void getUnidadDeNegocioList(Activity activity, List<UnidadDeNegocio> list) {
        listUnidadNegocio.clear();
        listUnidadNegocio.add(new UnidadDeNegocio(Constantes.selecionar, "", 0, 0, "", ""));
        listUnidadNegocio.addAll(list);

        ArrayAdapter<UnidadDeNegocio> myAdapter = new UnidadNegocioArrayAdapter(activity, R.layout.cell_spinner_item, listUnidadNegocio);
        spinnerUnidadNegocioNDF.setAdapter(myAdapter);
        spinnerUnidadNegocioNDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listUnidadNegocio.get(position).getId() == 0) {
                    idUdN = listUnidadNegocio.get(position).getId();
                    textViewCecoUnidadNegicioTextNDF.setVisibility(View.GONE);
                    textViewCecoUnidadNegocioNDF.setVisibility(View.GONE);
                    textViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
                    imageViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
                } else if (listUnidadNegocio.get(position).getId() >= 1) {
                    textViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
                    imageViewUnidadNegocioAlertaErrorNDF.setVisibility(View.GONE);
                    idUdN = listUnidadNegocio.get(position).getId();
                    textViewCecoUnidadNegicioTextNDF.setVisibility(View.VISIBLE);
                    textViewCecoUnidadNegocioNDF.setText(listUnidadNegocio.get(position).getCeco());
                    textViewCecoUnidadNegocioNDF.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getObtenerCatalogoTipoFraude(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    if (!respuestaGeneral.getListTipoFraude().equals(null) || !respuestaGeneral.getListTipoFraude().isEmpty()) {
                        getTipoFraudeList(activity, respuestaGeneral.getListTipoFraude());
                    } else {
                        getString(R.string.text_label_no_se_encontraron_datos);
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoTipoFraude);
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 5);
        }
    }

    public void getTipoFraudeList(Activity activity, List<TipoFraude> list) {
        listTipoFraude.clear();
        listTipoFraude.add(new TipoFraude(Constantes.selecionar, "", 0, 0));
        listTipoFraude.addAll(list);

        ArrayAdapter<TipoFraude> myAdapter = new TipoDelitoArrayAdapter(activity, R.layout.cell_spinner_item, listTipoFraude);
        spinnerTipoFraudeNDF.setAdapter(myAdapter);
        spinnerTipoFraudeNDF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listTipoFraude.get(position).getId() == 0) {
                    textViewTipoFraudeAlertErrorNDF.setVisibility(View.GONE);
                    imageViewTipoFraudeAlertaErrorNDF.setVisibility(View.GONE);
                    idTipoFraude = listTipoFraude.get(position).getId();
                } else if (listTipoFraude.get(position).getId() >= 1) {
                    textViewTipoFraudeAlertErrorNDF.setVisibility(View.GONE);
                    imageViewTipoFraudeAlertaErrorNDF.setVisibility(View.GONE);
                    idTipoFraude = listTipoFraude.get(position).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getObtenerConfiguracionFaseInicialCaso(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getConfiguracionData() != null || !respuestaGeneral.getConfiguracionData().toString().isEmpty()) {
                            valorDeConfiguracionFaseInicialCaso = respuestaGeneral.getConfiguracionData().getValor();
                            descripcionConfiguracionFaseInicialCaso = respuestaGeneral.getConfiguracionData().getDescripcion();
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.faseInicialCaso));
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
            case R.id.textViewFechaCompromisoCFC:
            case R.id.imageButtonFechaCompromisoCFC:
                datePickerDialogFragment.setTargetFragment(NuevaDenunciaFragment.this, 0);
                datePickerDialogFragment.show(fragmentManager, "editText");
                textViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);
                imageViewFechaCompromisoAlertErrorCFC.setVisibility(View.GONE);
                break;

            case R.id.buttonGuardarDenunciaNDF:
                clearFocusEditText(activity);
                String nombreCaso = Objects.requireNonNull(textInputEditTextNombreDenunciaNDF.getText()).toString().trim();
                String descripcionCaso = Objects.requireNonNull(textInputEditTextDescripcionNDF.getText()).toString().trim();
                String importe = textInputEditTextImporteNDF.getText().toString().trim().replace("$", "").replace(" ", "").replace(",", "");
                String montoRecuperado = textInputEditTextRecuperadoNDF.getText().toString().trim().replace("$", "").replace(" ", "").replace(",", "");

                String fechaReporte = textViewFechaCompromisoCFC.getText().toString().trim();

                if (fechaReporte.isEmpty()) {
                    textViewFechaCompromisoAlertErrorCFC.setVisibility(View.VISIBLE);
                    imageViewFechaCompromisoAlertErrorCFC.setVisibility(View.VISIBLE);
                    textViewFechaCompromisoAlertErrorCFC.setText(getString(R.string.text_label_error_la_fecha_reporte_esta_vacia));
                    Utils.messageShort(context, getString(R.string.text_label_error_la_fecha_reporte_esta_vacia));
                    clearFocusEditText(activity);
                } else if (String.valueOf(idTipoDenuncia).isEmpty() || idTipoDenuncia == 0) {
                    textViewTipoDenunciaAlertErrorCSS.setVisibility(View.VISIBLE);
                    imageViewTipoDenunciaAlertErrorCSS.setVisibility(View.VISIBLE);
                    textViewTipoDenunciaAlertErrorCSS.setText(getString(R.string.text_label_error_no_se_ha_seleccionado_un_tipo_de_denuncia));
                    Utils.messageShort(context, getString(R.string.text_label_error_no_se_ha_seleccionado_un_tipo_de_denuncia));
                    clearFocusEditText(activity);
                } else if (String.valueOf(idUdN).isEmpty() || idUdN == 0) {
                    textViewUnidadNegocioAlertaErrorNDF.setVisibility(View.VISIBLE);
                    imageViewUnidadNegocioAlertaErrorNDF.setVisibility(View.VISIBLE);
                    textViewUnidadNegocioAlertaErrorNDF.setText(getString(R.string.text_label_error_no_se_ha_selecionado_unidad_negocio));
                    Utils.messageShort(context, getString(R.string.text_label_error_no_se_ha_selecionado_unidad_negocio));
                    clearFocusEditText(activity);
                } else if (String.valueOf(idTipoFraude).isEmpty() || idTipoFraude == 0) {
                    textViewTipoFraudeAlertErrorNDF.setVisibility(View.VISIBLE);
                    imageViewTipoFraudeAlertaErrorNDF.setVisibility(View.VISIBLE);
                    textViewTipoFraudeAlertErrorNDF.setText(getString(R.string.text_label_selecciona_un_tipo_de_delito));
                    Utils.messageShort(context, getString(R.string.text_label_selecciona_un_tipo_de_delito));
                    clearFocusEditText(activity);
                } else if (nombreCaso.isEmpty()) {
                    Utils.errorEditText(textInputEditTextNombreDenunciaNDF, getString(R.string.text_label_nombre_de_la_denuncia_esta_vacia));
                } else if (nombreCaso.length() <= 9) {
                    Utils.errorEditText(textInputEditTextNombreDenunciaNDF, getString(R.string.text_label_nombre_de_la_denuncia_ee_menor_a_diez_caracteres));
                } else if (descripcionCaso.isEmpty()) {
                    Utils.errorEditText(textInputEditTextDescripcionNDF, getString(R.string.text_label_la_descripcio_de_la_denucnia_esta_vacia));
                } else if (descripcionCaso.length() <= 9) {
                    Utils.errorEditText(textInputEditTextDescripcionNDF, getString(R.string.text_label_la_descripcio_de_la_denucnia_es_menor_a_diez_caracteres));
                } else if (importe.isEmpty()) {
                    Utils.errorEditText(textInputEditTextImporteNDF, getString(R.string.text_label_el_importe_de_la_denuncia_esta_vacio));
                } else if (montoRecuperado.isEmpty()) {
                    Utils.errorEditText(textInputEditTextRecuperadoNDF, getString(R.string.text_label_monto_recuperado_esta_vacio));
                } else if (Double.parseDouble(montoRecuperado) > Double.parseDouble(importe)) {
                    Utils.message(context, getString(R.string.text_label_monto_no_puede_ser_mayor));
                } else if (String.valueOf(idUsuario).isEmpty()) {
                    Utils.messageShort(context, getString(R.string.text_label_el_id_abogado_esta_vacio));
                } else if (String.valueOf(idRegion).isEmpty()) {
                    Utils.messageShort(context, getString(R.string.text_label_id_de_la_region_esta_vacia));
                } else if (String.valueOf(valorDeConfiguracionFaseInicialCaso).isEmpty()) {
                    Utils.messageShort(context, descripcionConfiguracionFaseInicialCaso.concat(" es incorrecto"));
                } else if (listNombreResponsables.isEmpty()) {
                    imageViewResponsableAlertErrorCNR.setVisibility(View.VISIBLE);
                    textViewResponsableAlertErrorCNR.setVisibility(View.VISIBLE);
                    textViewResponsableAlertErrorCNR.setText(getString(R.string.text_label_selecciona_un_imputado));
                } else {
                    clearFocusEditText(activity);
                    showAlertDialogNuevaDenuncia(activity, v, getString(R.string.text_label_guardar_denucnai), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                            idTipoDenuncia, idUdN, idTipoFraude, idUsuario, Integer.parseInt(valorDeConfiguracionFaseInicialCaso), nombreCaso, descripcionCaso, Double.parseDouble(importe), Double.parseDouble(montoRecuperado), fechaReporte, idRegion, listNombreResponsables);
                    //      idTipoDenuncia  IdUdN  IdTipoFraude  IdAbogado                         IdEtapaCaso                     Nombre      Descripcion               Importe                      MontoRecuperado             FechaReporte  IdRegion  listaDeResponsables
                }
                break;

            case R.id.textViewResponsableCNR:
            case R.id.imageButtonResponsableCNR:
                //clearFocusEditText(activityNCF);
                showDialogTipoEmpleado(activity, listTipoEmpleado, etiquetaResponsables);
                break;

            default:
                break;
        }
    }

    public void clearFocusEditText(Activity activity) {
        Functions.hideTheKeyboard(activity, textInputEditTextNombreDenunciaNDF);
        Functions.hideTheKeyboard(activity, textInputEditTextDescripcionNDF);
        Functions.hideTheKeyboard(activity, textInputEditTextImporteNDF);
        Functions.hideTheKeyboard(activity, textInputEditTextRecuperadoNDF);
    }

    public void showDialogTipoEmpleado(Activity activity, List<TipoEmpleado> getTipoDeEmpleadoList, String etiquetaResponsables) {
        Dialog dialogTipoEmpleado = new Dialog(activity, R.style.CustomDialogTheme);
        dialogTipoEmpleado.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTipoEmpleado.setCancelable(false);
        dialogTipoEmpleado.setContentView(R.layout.dialog_tipo_de_empleado);
        Objects.requireNonNull(dialogTipoEmpleado.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RadioGroup radioGroupTipoEmpleadoDTE = dialogTipoEmpleado.findViewById(R.id.radioGroupTipoEmpleadoDTE);
        TextView textViewCerrarDTE = dialogTipoEmpleado.findViewById(R.id.textViewCerrarDTE);
        TextView textViewTituloDTE = dialogTipoEmpleado.findViewById(R.id.textViewTituloDTE);
        textViewTituloDTE.setText("Agregar tipo de ".concat(etiquetaResponsables.toLowerCase()));

        textViewCerrarDTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTipoEmpleado.dismiss();
            }
        });

        for (int x = 0; x < getTipoDeEmpleadoList.size(); x++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(getTipoDeEmpleadoList.get(x).getDescripcion());
            radioButton.setId(getTipoDeEmpleadoList.get(x).getId());
            radioGroupTipoEmpleadoDTE.addView(radioButton);
        }

        radioGroupTipoEmpleadoDTE.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = radioGroupTipoEmpleadoDTE.getCheckedRadioButtonId();
                RadioButton radioBtn = dialogTipoEmpleado.findViewById(checkedRadioButtonId);
                if (String.valueOf(radioBtn.getId()).equals("1")) {
                    dialogTipoEmpleado.dismiss();
                    showDialogEmpleadoInterno(activity, radioBtn.getId(), etiquetaResponsables);
                } else if (String.valueOf(radioBtn.getId()).equals("2")) {
                    dialogTipoEmpleado.dismiss();
                    showDialogEmpledoExterno(activity, radioBtn.getId());
                }
            }
        });

        dialogTipoEmpleado.show();
    }

    public void showDialogEmpleadoInterno(Activity activity, int tipoEmpleado, String etiquetaResponsables) {
        Dialog dialogEmpleadoInterno = new Dialog(activity, R.style.CustomDialogTheme);
        dialogEmpleadoInterno.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEmpleadoInterno.setCancelable(false);
        dialogEmpleadoInterno.setContentView(R.layout.dialog_empleado_interno);
        Objects.requireNonNull(dialogEmpleadoInterno.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        EditText editTextBusqueda = dialogEmpleadoInterno.findViewById(R.id.editTextNumeroEmpleado);
        ImageView imageViewBusquedaEmpleadoDEI = dialogEmpleadoInterno.findViewById(R.id.imageViewBusquedaEmpleadoDEI);
        TextView textViewCerrarCerrarDEI = dialogEmpleadoInterno.findViewById(R.id.textViewCerrarCerrarDEI);
        ListView listViewEmpleadosDEI = dialogEmpleadoInterno.findViewById(R.id.listViewEmpleadosDEI);
        listViewEmpleadosDEI.setVisibility(View.GONE);

        List<Empleado> listEmpleadosDEI = new ArrayList<>();
        autocompleteBaseAdapter = new AutocompleteBaseAdapter(activity, listEmpleadosDEI);
        listViewEmpleadosDEI.setAdapter(autocompleteBaseAdapter);

        textViewCerrarCerrarDEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEmpleadoInterno.dismiss();
                showDialogTipoEmpleado(activity, listTipoEmpleado, etiquetaResponsables);
            }
        });

        imageViewBusquedaEmpleadoDEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmpleadosDEI.clear();
                editTextBusqueda.clearFocus();
                clearFocusEditText(activity);
                String texto = editTextBusqueda.getText().toString().trim();
                if (!Normalizer.normalize(texto, Normalizer.Form.NFD).equals("") && !Normalizer.normalize(texto, Normalizer.Form.NFD).contains("+") && !Normalizer.normalize(texto, Normalizer.Form.NFD).contains(",")) {
                    Gson gsonParams = new Gson();
                    String params = gsonParams.toJson(new empledos(editTextBusqueda.getText().toString()));
                    //                                                                  empledos
                    new AsyncTaskGral(activity, new Delegate() {
                        @Override
                        public void getDelegate(String result) {
                            Gson gson = new Gson();
                            RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                            listEmpleadosDEI.addAll(respuestaGeneral.getListEmpleado());
                            listViewEmpleadosDEI.setVisibility(View.VISIBLE);
                            autocompleteBaseAdapter = new AutocompleteBaseAdapter(activity, listEmpleadosDEI);
                            listViewEmpleadosDEI.setAdapter(autocompleteBaseAdapter);
                        }

                        @Override
                        public void executeInBackground(String result, String header) {

                        }
                    }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerDatosEmpleadoDatosAuxiliares, params);
                } else {
                    editTextBusqueda.setError(getString(R.string.text_label_el_numero_empleado_esta_vacio));
                    editTextBusqueda.requestFocus();
                }
            }
        });

        listViewEmpleadosDEI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                autocompleteBaseAdapter = (AutocompleteBaseAdapter) listViewEmpleadosDEI.getAdapter();
                Empleado empleado = autocompleteBaseAdapter.getListEmpleados().get(position);
                if (!empleado.getVista().contains(getString(R.string.text_label_no_se_econtraron_resultados))) {
                    if (!Utils.validaSiExisteResponsableInternoOExterno(totalEmpleadosAdapter.getResponsablesResquest(), empleado.getNombre())) {
                        textViewResponsableAlertErrorCNR.setVisibility(View.GONE);
                        imageViewResponsableAlertErrorCNR.setVisibility(View.GONE);
                        totalEmpleadosAdapter.getResponsablesResquest().add(new ResponsablesResquest(empleado.getNombre(), tipoEmpleado, 1, Integer.parseInt(empleado.getNumEmpleado())));
                        //                                                                                 nombre         idTipoEmpleado  idStatusResposable             numeroEmpleado
                        totalEmpleadosAdapter.notifyDataSetChanged();
                        textViewListaResponsablesNDF.setVisibility(View.VISIBLE);
                        dialogEmpleadoInterno.dismiss();
                    } else {
                        Utils.message(context, getString(R.string.text_label_error_el_responsable_esta_en_la_lista));
                        //Utils.message(context, "Este ".concat(etiquetaResponsables));
                    }
                } else {
                    Utils.message(activity, getString(R.string.text_label_no_hay_contacto_valido_para_agregar));
                }
            }
        });

        dialogEmpleadoInterno.show();
    }

    public void showDialogEmpledoExterno(Activity activity, int tipoEmpleado) {
        Dialog dialogEmpleadoExterno = new Dialog(activity, R.style.CustomDialogTheme);
        dialogEmpleadoExterno.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEmpleadoExterno.setCancelable(false);
        dialogEmpleadoExterno.setContentView(R.layout.dialog_empleado_externo);
        Objects.requireNonNull(dialogEmpleadoExterno.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView textViewCerrarCerrarDEE = dialogEmpleadoExterno.findViewById(R.id.textViewCerrarCerrarDEE);
        TextInputEditText textInputEditTextEmpleadoDEE = dialogEmpleadoExterno.findViewById(R.id.textInputEditTextEmpleadoDEE);
        Button buttonAgregarDEE = dialogEmpleadoExterno.findViewById(R.id.buttonAgregarDEE);

        buttonAgregarDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textInputEditTextEmpleadoDEE.clearFocus();
                clearFocusEditText(activity);
                String nombreEmpleado = Objects.requireNonNull(textInputEditTextEmpleadoDEE.getText()).toString().trim();

                if (!nombreEmpleado.equals("")) {
                    if (!Utils.validaSiExisteResponsableInternoOExterno(totalEmpleadosAdapter.getResponsablesResquest(), nombreEmpleado)) {
                        textViewResponsableAlertErrorCNR.setVisibility(View.GONE);
                        imageViewResponsableAlertErrorCNR.setVisibility(View.GONE);

                        totalEmpleadosAdapter.getResponsablesResquest().add(new ResponsablesResquest(nombreEmpleado, tipoEmpleado, 1, null));
                        //                                                                                nombre    idTipoEmpleado  idStatusResposable       numeroEmpleado
                        totalEmpleadosAdapter.notifyDataSetChanged();
                        textViewListaResponsablesNDF.setVisibility(View.VISIBLE);
                        dialogEmpleadoExterno.dismiss();

                    } else {
                        Utils.message(context, getString(R.string.text_label_error_el_responsable_esta_en_la_lista));
                    }
                } else {
                    textInputEditTextEmpleadoDEE.setError(getString(R.string.text_label_nombre_empleado_vacio));
                    textInputEditTextEmpleadoDEE.requestFocus();
                }

            }
        });

        textViewCerrarCerrarDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEmpleadoExterno.dismiss();
                showDialogTipoEmpleado(activity, listTipoEmpleado, etiquetaResponsables);
            }
        });

        dialogEmpleadoExterno.show();
    }

    public void showDialogErrorConeccion(Activity activity, View view, String titulo, String mensaje, String butonText, int bandera) {
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
                if (bandera == 1) {
                    getObtenerDetalleUsuario(activity, view);
                } else if (bandera == 2) {
                    getTipoDenuncia(activity, view);
                } else if (bandera == 3) {
                    getTipoEmpleado(activity, view);
                } else if (bandera == 4) {
                    getObtenerCatalogoUdN(activity, view);
                } else if (bandera == 5) {
                    getObtenerCatalogoTipoFraude(activity, view);
                }
            }
        });

        dialogTipoEmpleado.show();
    }

    public void showAlertDialogNuevaDenuncia(Activity activity, View view, String titulo, String mensage, String positivoMensage, String negativoMensage, int idTipoDenuncia, int idUdN, int idTipoFraude, int idAbogado, int idEtapaCaso, String nombre, String descripcion, double importe, double montoRecuperado, String fechaReporte, int idRegion, List<ResponsablesResquest> listResponsables) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new NuevaDenuncia(new CasoRequest(idTipoDenuncia, idUdN, idTipoFraude, idAbogado, idEtapaCaso, nombre, descripcion, importe, montoRecuperado, fechaReporte, idRegion), listResponsables));
                //                                                                  idTipoDenuncia  IdUdN  IdTipoFraude  IdAbogado  IdEtapaCaso  Nombre  Descripcion  Importe  MontoRecuperado  FechaReporte  IdRegion   listResponsables
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getGuardarDenuncia().getExito().equals(Constantes.exitoTrue)) {
                            showDialogNuevaDenunciaConExito(activity, view, getString(R.string.text_label_guardado), getString(R.string.text_label_se_ha_guardado_con_exito_la_nueva_denuncia), respuestaGeneral.getGuardarDenuncia().getFolio()/*cambiarNegritasString(respuestaGeneral.getGuardarDenuncia().getCasoNombre()))*/, getString(R.string.text_label_aceptar), String.valueOf(respuestaGeneral.getGuardarDenuncia().getIdCaso()));
                        } else {
                            Utils.message(context, respuestaGeneral.getGuardarDenuncia().getError());
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.guardaCatalogoCaso, params);
            }
        });

        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    private static String cambiarNegritasString(String palabra){
        SpannableString miTexto = new SpannableString(palabra);
        //StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
        StyleSpan boldSpan2 = new StyleSpan(Typeface.BOLD);
        //miTexto.setSpan(boldSpan1, 5, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        miTexto.setSpan(boldSpan2, 30, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return miTexto.toString();
    }

    public void showDialogNuevaDenunciaConExito(Activity activity, View view, String titulo, String mensaje, String nombreDenuncia,String butonText, String idCaso) {
        Dialog dialogTipoEmpleado = new Dialog(activity, R.style.CustomDialogTheme);
        dialogTipoEmpleado.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTipoEmpleado.setCancelable(false);
        dialogTipoEmpleado.setContentView(R.layout.dialog_error_conexion_internet);
        Objects.requireNonNull(dialogTipoEmpleado.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView imageViewLogoDRCI = dialogTipoEmpleado.findViewById(R.id.imageViewLogoDRCI);

        TextView textViewTituloDECI = dialogTipoEmpleado.findViewById(R.id.textViewTituloDECI);
        textViewTituloDECI.setText(titulo);

        TextView textViewMensageDECI = dialogTipoEmpleado.findViewById(R.id.textViewMensageDECI);
        textViewMensageDECI.setText(cambiarNegritasString(mensaje.concat(" ").concat(nombreDenuncia)));

        Button buttonIntentarDECI = dialogTipoEmpleado.findViewById(R.id.buttonIntentarDECI);
        buttonIntentarDECI.setText(butonText);

        buttonIntentarDECI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTipoEmpleado.dismiss();
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                Navigation.findNavController(view).navigate(R.id.action_navigation_alta_nuevo_caso_fragment_to_navigation_proceso_fase_del_caso_fragment, bundle);
            }
        });

        dialogTipoEmpleado.show();
    }

    public void showAlertDialogEliminarResponsable(Activity activity, String titulo, String mensage, String positivoMensage, String negativoMensage, int position) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                clearFocusEditText(activity);
                if (!listNombreResponsables.isEmpty()) {
                    listNombreResponsables.remove(position);
                    totalEmpleadosAdapter.notifyDataSetChanged();
                } else {
                    textViewTotalResponsablesNDF.setVisibility(View.GONE);
                    textViewListaResponsablesNDF.setVisibility(View.GONE);
                    textViewTotalResponsablesTextNDF.setVisibility(View.GONE);
                    recyclerViewListaRespondablesNDF.setVisibility(View.GONE);
                }
            }
        });

        dialogo1.setNegativeButton(negativoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (view.getTag().equals("editText")) {
            textViewFechaCompromisoCFC.setText(Utils.formatoAnioMesDia(String.valueOf(year).concat("/").concat(String.valueOf(monthOfYear + 1)).concat("/").concat(String.valueOf(dayOfMonth))));
        }
    }

}