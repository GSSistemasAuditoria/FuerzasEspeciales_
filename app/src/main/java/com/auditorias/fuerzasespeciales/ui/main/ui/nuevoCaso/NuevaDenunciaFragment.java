package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.auditorias.fuerzasespeciales.models.Empleado;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.Serial;
import com.auditorias.fuerzasespeciales.models.TipoDeEmpleadoModel;
import com.auditorias.fuerzasespeciales.models.TipoFraudeModel;
import com.auditorias.fuerzasespeciales.models.UnidaDeNegocioModel;
import com.auditorias.fuerzasespeciales.request.CasoRequest;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.auditorias.fuerzasespeciales.request.SendRequest;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.adapters.AutocompleteAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.adapters.TipoDeFraudeArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.adapters.TotalEmpleadosAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.adapters.UnidadDeNegocioArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.dialogs.DatePickerDialogFragment;
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

    //TODO: son todas las listas que se ocupan en este fragment
    private final List<UnidaDeNegocioModel> listUnidadNegocio = new ArrayList<>();
    private final List<TipoFraudeModel> listTipoFraude = new ArrayList<>();
    private final List<ResponsablesResquest> listNombreResponsables = new ArrayList<>();
    private final List<TipoDeEmpleadoModel> listTipoEmpleado = new ArrayList<>();

    //TODO: son son todos los textView
    private TextView textViewSubTiutuloCST;
    private TextView textViewNombreAbogadoANCF;
    private TextView textViewCecoAbogadoANCF;
    private TextView textViewZonaANCF;
    private TextView textViewFechaCompromisoCFC;
    private TextView textViewCecoUnidadNegicioTextANCF;
    private TextView textViewCecoUnidadNegocioANCF;
    private TextView textViewListaResponsablesTextANCF;
    private TextView textViewTotalResponsablesANCF;
    private TextView textViewTotalResponsablesTextANCF;
    private TextView textViewAlertErrorCFC;
    private TextView textViewUNidadNegocioAlertErrorANCF;
    private TextView textViewAlertErrorTipoFraudeANCF;
    private TextView textViewNombreResponsableAlertErrorANCF;
    private TextView textViewRegionANCF;
    private TextView textViewFechaCompromisoTextCFC;

    //TODO: son son todos los TextInputLayout

    //TODO: son son todos los TextInputEditText
    private TextInputEditText textInputEditTextNombreCasoANCF;
    private TextInputEditText textInputEditTextDescripcionANCF;
    private TextInputEditText textInputEditTextImporteANCF;
    private TextInputEditText textInputEditTextImporteRecuperadoANCF;

    //TODO: son son todos los ImageButton
    private ImageButton imageButtonFechaCompromisoCFC;
    private ImageButton imageButtonNombreResponsableANCF;

    //TODO: son todas los imagevieew del fagment
    private ImageView imageViewAlertErrorCFC;
    private ImageView imageViewUnidadNegocioAlertErrorCFC;
    private ImageView imageViewAlertErrorTipoFraudeANCF;
    private ImageView imageViewNombreResponsableAlertErrorANCF;

    //TODO: son son todos los Spinner
    private Spinner spinnerUnidadNegocioANCF;
    private Spinner spinnerTipoFraudeANCF;

    private RadioGroup radioGroupTipoDenunciaNDF;

    //TODO: son son todos los Button
    private Button buttonGuardarCasoANCF;

    //TODO: recyclerview
    private RecyclerView recyclerViewTotalRespondablesANCF;

    //TODO: son son todos los Variables indispoensables de un fragment
    private View view;
    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;

    private DatePickerDialogFragment datePickerDialogFragment;

    private TotalEmpleadosAdapter totalEmpleadosAdapter;
    private AutocompleteAdapter autocompleteAdapter;
    private int idUdN;
    private int idTipoFraude;
    private int idTipoDenuncia;

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
        textViewFechaCompromisoTextCFC.setText(getString(R.string.text_label_report_date));

        /*if (!TableDataUser.getNombreAbodago(activity).isEmpty()) {
            textViewNombreAbogadoANCF.setText(TableDataUser.getNombreAbodago(activity));
            textViewCecoAbogadoANCF.setText(TableDataUser.getCecoAbodago(activity));
            textViewZonaANCF.setText(TableDataUser.getZonaAbodago(activity));
            textViewRegionANCF.setText(TableDataUser.getRegionAbodago(activity));
        }*/
        getObtenerDetalleUsuario(activity);
        getTipoEmpleado(activity, view);
        getObtenerCatalogoUdN(activity, view);
        getObtenerCatalogoTipoFraude(activity, view);
        getTipoDenuncia(activity, view);
        recyclerViewTotalRespondablesANCF.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
        recyclerViewTotalRespondablesANCF.setLayoutManager(layoutManagerCategory);
        recyclerViewTotalRespondablesANCF.setNestedScrollingEnabled(false);

        totalEmpleadosAdapter = new TotalEmpleadosAdapter(activity, listNombreResponsables, new TotalEmpleadosAdapter.OnClickListener() {
            @Override
            public void onItemClick(ResponsablesResquest responsablesResquest, int position) {

            }

            @Override
            public void onClickDelete(ResponsablesResquest responsablesResquest, int position) {
                showAlertDialogEliminarResponsable(activity, getString(R.string.text_label_eleminar_responsable), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no), position);
            }
        });
        recyclerViewTotalRespondablesANCF.setAdapter(totalEmpleadosAdapter);

        //Utils.setCaracteresEntreDiezAndVeinte(activityNCF, textInputEditTextNombreCasoANCF);
        //Utils.setCaracteresEntreDiezAndCincuenta(activityNCF, textInputEditTextDescripcionANCF);
        textInputEditTextImporteANCF.addTextChangedListener(Utils.amount(activity, textInputEditTextImporteANCF));
        textInputEditTextImporteRecuperadoANCF.addTextChangedListener(Utils.amount(activity, textInputEditTextImporteRecuperadoANCF));

        return view;
    }


    //TODO: aqui se hace el elace con los elementos xml del fragment
    public void refereciasConInterface(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        textViewNombreAbogadoANCF = view.findViewById(R.id.textViewNombreAbogadoANCF);
        textViewCecoAbogadoANCF = view.findViewById(R.id.textViewCecoAbogadoANCF);
        textViewZonaANCF = view.findViewById(R.id.textViewZonaANCF);
        textViewRegionANCF = view.findViewById(R.id.textViewRegionANCF);
        textViewFechaCompromisoTextCFC = view.findViewById(R.id.textViewFechaCompromisoTextCFC);
        textViewFechaCompromisoCFC = view.findViewById(R.id.textViewFechaCompromisoCFC);
        spinnerUnidadNegocioANCF = view.findViewById(R.id.spinnerUnidadNegocioANCF);
        textViewCecoUnidadNegicioTextANCF = view.findViewById(R.id.textViewCecoUnidadNegicioTextANCF);
        textViewCecoUnidadNegocioANCF = view.findViewById(R.id.textViewCecoUnidadNegocioANCF);
        spinnerTipoFraudeANCF = view.findViewById(R.id.spinnerTipoFraudeANCF);
        textInputEditTextNombreCasoANCF = view.findViewById(R.id.textInputEditTextNombreCasoANCF);
        textInputEditTextDescripcionANCF = view.findViewById(R.id.textInputEditTextDescripcionANCF);
        textInputEditTextImporteANCF = view.findViewById(R.id.textInputEditTextImporteANCF);
        textInputEditTextImporteRecuperadoANCF = view.findViewById(R.id.textInputEditTextImporteRecuperadoANCF);
        textViewListaResponsablesTextANCF = view.findViewById(R.id.textViewListaResponsablesTextANCF);
        recyclerViewTotalRespondablesANCF = view.findViewById(R.id.recyclerViewTotalRespondablesANCF);
        textViewTotalResponsablesTextANCF = view.findViewById(R.id.textViewTotalResponsablesTextANCF);
        textViewTotalResponsablesANCF = view.findViewById(R.id.textViewTotalResponsablesANCF);
        textViewAlertErrorCFC = view.findViewById(R.id.textViewAlertErrorCFC);
        imageViewAlertErrorCFC = view.findViewById(R.id.imageViewAlertErrorCFC);
        textViewUNidadNegocioAlertErrorANCF = view.findViewById(R.id.textViewUNidadNegocioAlertErrorANCF);
        imageViewUnidadNegocioAlertErrorCFC = view.findViewById(R.id.imageViewUnidadNegocioAlertErrorCFC);
        textViewAlertErrorTipoFraudeANCF = view.findViewById(R.id.textViewAlertErrorTipoFraudeANCF);
        imageViewAlertErrorTipoFraudeANCF = view.findViewById(R.id.imageViewAlertErrorTipoFraudeANCF);
        imageViewNombreResponsableAlertErrorANCF = view.findViewById(R.id.imageViewNombreResponsableAlertErrorANCF);
        textViewNombreResponsableAlertErrorANCF = view.findViewById(R.id.textViewNombreResponsableAlertErrorANCF);
        radioGroupTipoDenunciaNDF = view.findViewById(R.id.radioGroupTipoDenunciaNDF);

        imageButtonFechaCompromisoCFC = view.findViewById(R.id.imageButtonFechaCompromisoCFC);
        imageButtonFechaCompromisoCFC.setOnClickListener(this);

        imageButtonNombreResponsableANCF = view.findViewById(R.id.imageButtonNombreResponsableANCF);
        imageButtonNombreResponsableANCF.setOnClickListener(this);

        buttonGuardarCasoANCF = view.findViewById(R.id.buttonGuardarCasoANCF);
        buttonGuardarCasoANCF.setOnClickListener(this);

    }

    //TODO: los elementos que se ocultan al iniciar el fragment
    public void ocultarElementos() {
        textViewCecoUnidadNegicioTextANCF.setVisibility(View.GONE);
        textViewCecoUnidadNegocioANCF.setVisibility(View.GONE);
        textViewListaResponsablesTextANCF.setVisibility(View.GONE);
        textViewTotalResponsablesTextANCF.setVisibility(View.GONE);
        textViewTotalResponsablesANCF.setVisibility(View.GONE);
        textViewAlertErrorCFC.setVisibility(View.GONE);
        imageViewAlertErrorCFC.setVisibility(View.GONE);
        textViewUNidadNegocioAlertErrorANCF.setVisibility(View.GONE);
        imageViewUnidadNegocioAlertErrorCFC.setVisibility(View.GONE);
        textViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
        imageViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
        imageViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);
        textViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);
    }


    //TODO: consumo de servicio para el llenado del informacion del empleado
    private void getObtenerDetalleUsuario(Activity activity) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                    for (int x = 0; x < respuestaGeneral.getDetalleUsuario().size(); x++) {
                        //showDialogDetallePerfil(activity, "Abogado", respuestaGeneral.getDetalleUsuario().get(x));
                        textViewNombreAbogadoANCF.setText(respuestaGeneral.getDetalleUsuario().get(x).getNombre());
                        textViewCecoAbogadoANCF.setText(respuestaGeneral.getDetalleUsuario().get(x).getCeco());
                        textViewZonaANCF.setText(respuestaGeneral.getDetalleUsuario().get(x).getZona());
                        textViewRegionANCF.setText(respuestaGeneral.getDetalleUsuario().get(x).getRegion());
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

    //TODO: consumo de servicio para el llenado del spinner de tipo de empleado
    private void getTipoDenuncia(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                    for (int x = 0; x < respuestaGeneral.getTipoDenuncia().getLisTipoDenuncia().size(); x++) {
                        RadioButton radioButton = new RadioButton(getContext());
                        radioButton.setText(respuestaGeneral.getTipoDenuncia().getLisTipoDenuncia().get(x).getDescripcion());
                        radioButton.setId(respuestaGeneral.getTipoDenuncia().getLisTipoDenuncia().get(x).getId());
                        radioGroupTipoDenunciaNDF.addView(radioButton);
                    }

                    radioGroupTipoDenunciaNDF.setOnCheckedChangeListener((group, checkedId) -> {
                        int checkedRadioButtonId = radioGroupTipoDenunciaNDF.getCheckedRadioButtonId();
                        RadioButton radioBtn = view.findViewById(checkedRadioButtonId);
                        if (String.valueOf(radioBtn.getId()).equals("1")) {
                            idTipoDenuncia = radioBtn.getId();
                            //dialogTipoEmpleado.dismiss();
                            //showDialogEmpleadoInterno(activity, radioBtn.getId());
                        } else if (String.valueOf(radioBtn.getId()).equals("2")) {
                            idTipoDenuncia = radioBtn.getId();
                            //dialogTipoEmpleado.dismiss();
                            //showDialogEmpledoExterno(activity, radioBtn.getId());
                        }
                    });

                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerTipoDenuncia, "");
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 1);
            //Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    //TODO: consumo de servicio para el llenado del spinner de tipo de empleado
    private void getTipoEmpleado(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    Serial serial = gson.fromJson(result, Serial.class);
                    if (!serial.gettipoDeEmpleadoList().equals(null) || !serial.gettipoDeEmpleadoList().isEmpty()) {
                        listTipoEmpleado.addAll(serial.gettipoDeEmpleadoList());
                    } else {
                        Utils.messageShort(activity, getString(R.string.text_label_no_se_encontraron_datos));
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoTipoEmpleado);
        } else {
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 1);
            //Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    //TODO: consumo de servicio para el llenado del spinner de la unidad de negocio
    private void getObtenerCatalogoUdN(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    Serial serial = gson.fromJson(result, Serial.class);
                    if (!serial.getUnidadDeNegocioList().equals(null) || !serial.getUnidadDeNegocioList().isEmpty()) {
                        getUnidadDeNegocioList(activity, serial.getUnidadDeNegocioList());
                    } else {
                        getString(R.string.text_label_no_se_encontraron_datos);
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoUdN);
        } else {
            //Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 2);
        }
    }

    //TODO: llenado de lista de unidad de negocio y funcionalidad
    public void getUnidadDeNegocioList(Activity activity, List<UnidaDeNegocioModel> list) {
        listUnidadNegocio.clear();
        listUnidadNegocio.add(new UnidaDeNegocioModel(Constantes.selecionar, "", 0, 0, "", ""));
        listUnidadNegocio.addAll(list);

        ArrayAdapter<UnidaDeNegocioModel> myAdapter = new UnidadDeNegocioArrayAdapter(activity, R.layout.cell_estatus_responsable_spinner_item, listUnidadNegocio);
        spinnerUnidadNegocioANCF.setAdapter(myAdapter);
        spinnerUnidadNegocioANCF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listUnidadNegocio.get(position).getId() == 0) {
                    idUdN = listUnidadNegocio.get(position).getId();
                    textViewCecoUnidadNegicioTextANCF.setVisibility(View.GONE);
                    textViewCecoUnidadNegocioANCF.setVisibility(View.GONE);
                    textViewUNidadNegocioAlertErrorANCF.setVisibility(View.GONE);
                    imageViewUnidadNegocioAlertErrorCFC.setVisibility(View.GONE);
                    textViewCecoUnidadNegocioANCF.setText("");
                } else if (listUnidadNegocio.get(position).getId() >= 1) {
                    textViewUNidadNegocioAlertErrorANCF.setVisibility(View.GONE);
                    imageViewUnidadNegocioAlertErrorCFC.setVisibility(View.GONE);
                    idUdN = listUnidadNegocio.get(position).getId();
                    textViewCecoUnidadNegicioTextANCF.setVisibility(View.VISIBLE);
                    textViewCecoUnidadNegocioANCF.setText(listUnidadNegocio.get(position).getCeco());
                    textViewCecoUnidadNegocioANCF.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //TODO: consumo de servicio para el llenado del spinner de tipo de fraude
    private void getObtenerCatalogoTipoFraude(Activity activity, View view) {
        if (Functions.isNetworkAvailable(activity)) {
            new AsyncTaskGral(activity, new Delegate() {
                @Override
                public void getDelegate(String result) {
                    Gson gson = new Gson();
                    Serial serial = gson.fromJson(result, Serial.class);
                    if (!serial.getTipoFraudeList().equals(null) || !serial.getTipoFraudeList().isEmpty()) {
                        getTipoFraudeList(activity, serial.getTipoFraudeList());
                    } else {
                        getString(R.string.text_label_no_se_encontraron_datos);
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoTipoFraude);
        } else {
            //Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 3);
        }
    }

    //TODO: llenado de lista de tipo de fraude y funcionalidad
    public void getTipoFraudeList(Activity activity, List<TipoFraudeModel> list) {
        listTipoFraude.clear();
        listTipoFraude.add(new TipoFraudeModel(Constantes.selecionar, "", 0, 0));
        listTipoFraude.addAll(list);

        ArrayAdapter<TipoFraudeModel> myAdapter = new TipoDeFraudeArrayAdapter(activity, R.layout.cell_estatus_responsable_spinner_item, listTipoFraude);
        spinnerTipoFraudeANCF.setAdapter(myAdapter);
        spinnerTipoFraudeANCF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listTipoFraude.get(position).getId() == 0) {
                    textViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
                    imageViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
                    idTipoFraude = listTipoFraude.get(position).getId();
                } else if (listTipoFraude.get(position).getId() >= 1) {
                    textViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
                    imageViewAlertErrorTipoFraudeANCF.setVisibility(View.GONE);
                    idTipoFraude = listTipoFraude.get(position).getId();
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
            case R.id.imageButtonFechaCompromisoCFC:
                datePickerDialogFragment.setTargetFragment(NuevaDenunciaFragment.this, 0);
                datePickerDialogFragment.show(fragmentManager, "editText");
                textViewAlertErrorCFC.setVisibility(View.GONE);
                imageViewAlertErrorCFC.setVisibility(View.GONE);
                break;

            case R.id.buttonGuardarCasoANCF:
                clearFocusEditText(activity);
                int idAbogado = Integer.parseInt(Functions.getIdUser(activity));
                int idRegion = Integer.parseInt(Functions.getIdRegion(activity));
                String nombreCaso = Objects.requireNonNull(textInputEditTextNombreCasoANCF.getText()).toString().trim();
                String descripcionCaso = Objects.requireNonNull(textInputEditTextDescripcionANCF.getText()).toString().trim();
                String importe = textInputEditTextImporteANCF.getText().toString().trim().replace("$", "").replace(" ", "").replace(",", "");
                String montoRecuperado = textInputEditTextImporteRecuperadoANCF.getText().toString().trim().replace("$", "").replace(" ", "").replace(",", "");

                String fechaReporte = textViewFechaCompromisoCFC.getText().toString().trim();

                if (fechaReporte.isEmpty()) {
                    textViewAlertErrorCFC.setVisibility(View.VISIBLE);
                    imageViewAlertErrorCFC.setVisibility(View.VISIBLE);
                    textViewAlertErrorCFC.setText(getString(R.string.text_label_error_la_fecha_reporte_esta_vacia));
                    Utils.messageShort(context, getString(R.string.text_label_error_la_fecha_reporte_esta_vacia));
                    clearFocusEditText(activity);
                } else if (String.valueOf(idUdN).isEmpty() || idUdN == 0) {
                    textViewUNidadNegocioAlertErrorANCF.setVisibility(View.VISIBLE);
                    imageViewUnidadNegocioAlertErrorCFC.setVisibility(View.VISIBLE);
                    textViewUNidadNegocioAlertErrorANCF.setText(getString(R.string.text_label_error_no_se_ha_selecionado_unidad_negocio));
                    Utils.messageShort(context, getString(R.string.text_label_error_no_se_ha_selecionado_unidad_negocio));
                    clearFocusEditText(activity);
                } else if (String.valueOf(idTipoFraude).isEmpty() || idTipoFraude == 0) {
                    textViewAlertErrorTipoFraudeANCF.setVisibility(View.VISIBLE);
                    imageViewAlertErrorTipoFraudeANCF.setVisibility(View.VISIBLE);
                    textViewAlertErrorTipoFraudeANCF.setText(getString(R.string.text_label_selecciona_un_tipo_de_delito));
                    Utils.messageShort(context, getString(R.string.text_label_selecciona_un_tipo_de_delito));
                    clearFocusEditText(activity);
                } else if (nombreCaso.isEmpty()) {
                    Utils.errorEditText(textInputEditTextNombreCasoANCF, getString(R.string.text_label_nombre_de_la_denuncia_esta_vacia));
                } else if (nombreCaso.length() <= 9) {
                    Utils.errorEditText(textInputEditTextNombreCasoANCF, getString(R.string.text_label_nombre_de_la_denuncia_ee_menor_a_diez_caracteres));
                } else if (descripcionCaso.isEmpty()) {
                    Utils.errorEditText(textInputEditTextDescripcionANCF, getString(R.string.text_label_la_descripcio_de_la_denucnia_esta_vacia));
                } else if (descripcionCaso.length() <= 9) {
                    Utils.errorEditText(textInputEditTextDescripcionANCF, getString(R.string.text_label_la_descripcio_de_la_denucnia_es_menor_a_diez_caracteres));
                } else if (importe.isEmpty()) {
                    Utils.errorEditText(textInputEditTextImporteANCF, getString(R.string.text_label_el_importe_de_la_denuncia_esta_vacio));
                } else if (montoRecuperado.isEmpty()) {
                    Utils.errorEditText(textInputEditTextImporteRecuperadoANCF, getString(R.string.text_label_monto_recuperado_esta_vacio));
                } else if (Double.parseDouble(montoRecuperado) > Double.parseDouble(importe)) {
                    Utils.message(context, getString(R.string.text_label_monto_no_puede_ser_mayor));
                } else if (String.valueOf(idAbogado).isEmpty()) {
                    Utils.messageShort(context, getString(R.string.text_label_el_id_abogado_esta_vacio));
                } else if (String.valueOf(idRegion).isEmpty()) {
                    Utils.messageShort(context, getString(R.string.text_label_id_de_la_region_esta_vacia));
                } else if (listNombreResponsables.isEmpty()) {
                    textViewNombreResponsableAlertErrorANCF.setVisibility(View.VISIBLE);
                    imageViewNombreResponsableAlertErrorANCF.setVisibility(View.VISIBLE);
                    textViewNombreResponsableAlertErrorANCF.setText(getString(R.string.text_label_selecciona_un_imputado));
                } else {
                    clearFocusEditText(activity);
                    int idEtapaCaso = 1;
                    showAlertDialogNuevaDenuncia(activity, v, getString(R.string.text_label_guardar_denucnai), getString(R.string.text_label_pregunta_general), getString(R.string.text_label_si), getString(R.string.text_label_no),
                            idUdN, idTipoFraude, idAbogado, idEtapaCaso, nombreCaso, descripcionCaso, Double.parseDouble(importe), Double.parseDouble(montoRecuperado), fechaReporte, idRegion, listNombreResponsables);
                    //      IdUdN   IdTipoFraude  IdAbogado IdEtapaCaso     Nombre      Descripcion               Importe                      MontoRecuperado          FechaReporte  IdRegion  listaDeResponsables
                }
                break;

            case R.id.imageButtonNombreResponsableANCF:
                //clearFocusEditText(activityNCF);
                showDialogTipoEmpleado(activity, listTipoEmpleado, idTipoDenuncia);
                break;

            default:
                break;
        }
    }

    public void clearFocusEditText(Activity activity) {
        Functions.hideTheKeyboard(activity, textInputEditTextNombreCasoANCF);
        Functions.hideTheKeyboard(activity, textInputEditTextDescripcionANCF);
        Functions.hideTheKeyboard(activity, textInputEditTextImporteANCF);
        Functions.hideTheKeyboard(activity, textInputEditTextImporteRecuperadoANCF);
    }

    public void showDialogTipoEmpleado(Activity activity, List<TipoDeEmpleadoModel> getTipoDeEmpleadoList, int idTipoDenuncia) {
        Dialog dialogTipoEmpleado = new Dialog(activity, R.style.CustomDialogTheme);
        dialogTipoEmpleado.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTipoEmpleado.setCancelable(false);
        dialogTipoEmpleado.setContentView(R.layout.dialog_tipo_de_empleado);
        Objects.requireNonNull(dialogTipoEmpleado.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RadioGroup radioGroupTipoEmpleadoDTE = dialogTipoEmpleado.findViewById(R.id.radioGroupTipoEmpleadoDTE);
        TextView textViewCerrarDTE = dialogTipoEmpleado.findViewById(R.id.textViewCerrarDTE);
        TextView textViewTituloDTE = dialogTipoEmpleado.findViewById(R.id.textViewTituloDTE);
        if (idTipoDenuncia == 1){
            textViewTituloDTE.setText("Agregar tipo de defendidos");
        }else {
            textViewTituloDTE.setText("Agregar tipo de imputados");
        }

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

        radioGroupTipoEmpleadoDTE.setOnCheckedChangeListener((group, checkedId) -> {
            int checkedRadioButtonId = radioGroupTipoEmpleadoDTE.getCheckedRadioButtonId();
            RadioButton radioBtn = dialogTipoEmpleado.findViewById(checkedRadioButtonId);
            if (String.valueOf(radioBtn.getId()).equals("1")) {
                dialogTipoEmpleado.dismiss();
                showDialogEmpleadoInterno(activity, radioBtn.getId());
            } else if (String.valueOf(radioBtn.getId()).equals("2")) {
                dialogTipoEmpleado.dismiss();
                showDialogEmpledoExterno(activity, radioBtn.getId());
            }
        });

        dialogTipoEmpleado.show();
    }

    public void showDialogEmpleadoInterno(Activity activity, int tipoEmpleado) {
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
        autocompleteAdapter = new AutocompleteAdapter(activity, listEmpleadosDEI);
        listViewEmpleadosDEI.setAdapter(autocompleteAdapter);

        textViewCerrarCerrarDEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEmpleadoInterno.dismiss();
                showDialogTipoEmpleado(activity, listTipoEmpleado, idTipoDenuncia);
            }
        });

        imageViewBusquedaEmpleadoDEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEmpleadosDEI.clear();
                editTextBusqueda.clearFocus();
                clearFocusEditText(activity);
                String texto = editTextBusqueda.getText().toString().trim();
                if (!Normalizer.normalize(texto, Normalizer.Form.NFD).equals("") && !Normalizer.normalize(texto, Normalizer.Form.NFD).contains("+") &&
                        !Normalizer.normalize(texto, Normalizer.Form.NFD).contains(",")) {
                    new AsyncTaskGral(NuevaDenunciaFragment.this.activity, new Delegate() {
                        @Override
                        public void getDelegate(String result) {
                            Gson gson = new Gson();
                            Serial serial = gson.fromJson(result, Serial.class);

                            listEmpleadosDEI.addAll(serial.getObtenerDatosEmpleadoDatosAuxiliaresResult());
                            listViewEmpleadosDEI.setVisibility(View.VISIBLE);
                            autocompleteAdapter = new AutocompleteAdapter(activity, listEmpleadosDEI);
                            listViewEmpleadosDEI.setAdapter(autocompleteAdapter);
                        }

                        @Override
                        public void executeInBackground(String result, String header) {

                        }
                    }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerDatosEmpleadoDatosAuxiliares.concat(Constantes.signoInterrogacion).concat(Constantes.empleado).concat(Constantes.signoIgual).concat(editTextBusqueda.getText().toString()));
                } else {
                    editTextBusqueda.setError(getString(R.string.text_label_el_numero_empleado_esta_vacio));
                    editTextBusqueda.requestFocus();
                }
            }
        });

        listViewEmpleadosDEI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                autocompleteAdapter = (AutocompleteAdapter) listViewEmpleadosDEI.getAdapter();
                Empleado empleado = autocompleteAdapter.getListEmpleados().get(position);
                if (!empleado.getVista().contains(getString(R.string.text_label_no_se_econtraron_resultados))) {
                    if (!Utils.validaSiExisteResponsableInternoOExterno(totalEmpleadosAdapter.getResponsablesResquest(), empleado.getNombre())) {
                        imageViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);
                        textViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);
                        totalEmpleadosAdapter.getResponsablesResquest().add(new ResponsablesResquest(empleado.getNombre(), tipoEmpleado, 1, Integer.parseInt(empleado.getNumEmpleado())));
                        //                                                                                 nombre         idTipoEmpleado  idStatusResposable             numeroEmpleado
                        totalEmpleadosAdapter.notifyDataSetChanged();
                        textViewListaResponsablesTextANCF.setVisibility(View.VISIBLE);
                        dialogEmpleadoInterno.dismiss();
                    } else {
                        Utils.message(context, getString(R.string.text_label_error_el_responsable_esta_en_la_lista));
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
                        imageViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);
                        textViewNombreResponsableAlertErrorANCF.setVisibility(View.GONE);

                        totalEmpleadosAdapter.getResponsablesResquest().add(new ResponsablesResquest(nombreEmpleado, tipoEmpleado, 1, null));
                        //                                                                                nombre    idTipoEmpleado  idStatusResposable       numeroEmpleado
                        totalEmpleadosAdapter.notifyDataSetChanged();
                        textViewListaResponsablesTextANCF.setVisibility(View.VISIBLE);
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
                showDialogTipoEmpleado(activity, listTipoEmpleado, idTipoDenuncia);
            }
        });

        dialogEmpleadoExterno.show();
    }

    public void showDialogNuevaDenunciaConExito(Activity activity, View view, String titulo, String mensaje, String butonText, String idCaso) {
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
                Navigation.findNavController(view).navigate(R.id.action_navigation_alta_nuevo_caso_fragment_to_navigation_proceso_fase_del_caso_fragment, bundle);
            }
        });

        dialogTipoEmpleado.show();
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
                if (bandera == 1){
                    getTipoEmpleado(activity, view);
                }else if (bandera ==2){
                    getObtenerCatalogoUdN(activity, view);
                }else if (bandera ==3){
                    getObtenerCatalogoTipoFraude(activity, view);
                }
            }
        });

        dialogTipoEmpleado.show();
    }

    public void showAlertDialogNuevaDenuncia(Activity activity, View view, String titulo, String mensage, String positivoMensage, String negativoMensage, int idUdN, int idTipoFraude, int idAbogado, int idEtapaCaso, String nombre, String descripcion, double importe, double montoRecuperado, String fechaReporte, int idRegion, List<ResponsablesResquest> listResponsables) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensage);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton(positivoMensage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new SendRequest(new CasoRequest(idUdN, idTipoFraude, idAbogado, idEtapaCaso, nombre, descripcion, importe, montoRecuperado, fechaReporte, idRegion), listResponsables));
                //                                                                IdUdN  IdTipoFraude  IdAbogado  IdEtapaCaso  Nombre  Descripcion  Importe  MontoRecuperado  FechaReporte  IdRegion   listResponsables
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        Serial serial = gson.fromJson(result, Serial.class);
                        if (serial.getGuardaCatalogoCasoResult().getExito().equals(Constantes.exitoTrue)) {
                            showDialogNuevaDenunciaConExito(activity, view, getString(R.string.text_label_guardado), getString(R.string.text_label_se_ha_guardado_con_exito_la_nueva_denuncia), getString(R.string.text_label_aceptar), String.valueOf(serial.getGuardaCatalogoCasoResult().getIdCaso()));
                        } else {
                            Utils.message(context, serial.getGuardaCatalogoCasoResult().getError());
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
                    textViewTotalResponsablesANCF.setVisibility(View.GONE);
                    textViewListaResponsablesTextANCF.setVisibility(View.GONE);
                    textViewTotalResponsablesTextANCF.setVisibility(View.GONE);
                    recyclerViewTotalRespondablesANCF.setVisibility(View.GONE);
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