package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.FaseActivaDatos;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseAdapters.FasesAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

public class ProcesoFaseDenunciaFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = ProcesoFaseDenunciaFragment.class.getName();

    //TODO: son todas las lista del fragment

    //TODO: son todos los textview del fragment
    private TextView textViewSubTiutuloCST;
    private TextView textViewFolioDenunciaCDD;
    private TextView textViewNombreDenunciaCDD;
    private TextView textViewFechaCompromisoFaseCDD;
    private TextView textViewFechaCompromisoFaseTextCDD;
    private TextView textViewUnidadNegocioCDD;
    //private TextView textViewFechaCompromisoTextCVG;
    private TextView textViewZonaCDD;
    private TextView textViewPorcentajeGeneralDenunciaCDD;
    private TextView textViewFechaRegistroTextCDD;
    private TextView textViewFechaResgistroCDD;
    private TextView textViewAutorizacionCDD;
    private TextView textViewAutorizacionTextCDD;

    private TextView textViewTipoDelitoCDD;

    //TODO: son todos los recyclerview del fragment
    private RecyclerView recyclerViewFacesDenunciaPFDF;

    //TODO: son todos los Linearlayout del fragment
    private LinearLayout linearLayoutColorEtapaDenunciaCDD;
    private LinearLayout linearLayoutDenunciaCCD;

    //TODO: son todos los view del fragment
    private View view;

    //TODO: es el context del fragment
    private Context context;

    //TODO: es el activity del fragment
    private Activity activity;

    //TODO: es el fragmentmanager del fragment
    private FragmentManager fragmentManager;

    //TODO: listas del fragment
    private Bundle args;

    //TODO: son todos los objetos del models del fragment
    private DatosDenuncia datosDenuncia;

    //TODO: son todas las listas que se necesitan en el fragment

    //TODO: son todos los adaptaters del fragment
    private FasesAdapter fasesAdapter;

    //TODO: es el swiperefreshlayout
    private SwipeRefreshLayout swipeRefreshLayoutPFDF;

    //TODO: son todas las varibles de datos
    private String idCaso;
    private String statusAutorizacion;

    public ProcesoFaseDenunciaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_proceso_fase_denuncia, container, false);
        activity = getActivity();
        context = getContext();
        fragmentManager = getFragmentManager();

        getEnlaces(view);
        ocultarElementos();

        textViewSubTiutuloCST.setText(getString(R.string.title_detalle_del_caso));

        args = getArguments();
        if (args != null) {
            idCaso = args.getString("idCaso");
            getServices(activity, idCaso);
        } else {
            getServices(activity);
        }

        return view;
    }

    public void getEnlaces(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        textViewFolioDenunciaCDD = view.findViewById(R.id.textViewFolioDenunciaCDD);
        textViewNombreDenunciaCDD = view.findViewById(R.id.textViewNombreDenunciaCDD);
        textViewPorcentajeGeneralDenunciaCDD = view.findViewById(R.id.textViewPorcentajeGeneralDenunciaCDD);
        textViewUnidadNegocioCDD = view.findViewById(R.id.textViewUnidadNegocioCDD);
        textViewZonaCDD = view.findViewById(R.id.textViewZonaCDD);
        linearLayoutColorEtapaDenunciaCDD = view.findViewById(R.id.linearLayoutColorEtapaDenunciaCDD);
        textViewFechaRegistroTextCDD = view.findViewById(R.id.textViewFechaRegistroTextCDD);
        textViewFechaResgistroCDD = view.findViewById(R.id.textViewFechaResgistroCDD);
        textViewFechaCompromisoFaseCDD = view.findViewById(R.id.textViewFechaCompromisoFaseCDD);
        textViewFechaCompromisoFaseTextCDD = view.findViewById(R.id.textViewFechaCompromisoFaseTextCDD);
        textViewAutorizacionTextCDD = view.findViewById(R.id.textViewAutorizacionTextCDD);
        textViewAutorizacionCDD = view.findViewById(R.id.textViewAutorizacionCDD);
        textViewTipoDelitoCDD = view.findViewById(R.id.textViewTipoDelitoCDD);
        recyclerViewFacesDenunciaPFDF = view.findViewById(R.id.recyclerViewFacesDenunciaPFDF);

        linearLayoutDenunciaCCD = view.findViewById(R.id.linearLayoutDenunciaCCD);
        linearLayoutDenunciaCCD.setOnClickListener(this);

        swipeRefreshLayoutPFDF = view.findViewById(R.id.swipeRefreshLayoutPFDF);
        swipeRefreshLayoutPFDF.setOnRefreshListener(this);

        swipeRefreshLayoutPFDF.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
    }

    //TODO: los elementos que se ocultan al iniciar el fragment
    public void ocultarElementos() {
        textViewAutorizacionTextCDD.setVisibility(View.GONE);
        textViewAutorizacionCDD.setVisibility(View.GONE);
    }

    private void getServices(Activity activity, String idCaso) {
        if (Functions.isNetworkAvailable(activity)) {
            getDatosDelCaso(activity);
            getCatalogoFaseActiva(activity, idCaso);
        } else {
            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    private void getServices(Activity activity) {
        if (Functions.isNetworkAvailable(activity)) {
            getDatosDelCaso(activity);
            getCatalogoFaseActiva(activity, idCaso);
        } else {
            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    public void getDatosDelCaso(Activity activity) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getDatosDenuncia() != null || !respuestaGeneral.getDatosDenuncia().toString().isEmpty()) {
                            idCaso = String.valueOf(respuestaGeneral.getDatosDenuncia().getId());
                            statusAutorizacion = respuestaGeneral.getDatosDenuncia().getStatusAutorizacion();
                            datosDenuncia = respuestaGeneral.getDatosDenuncia();

                            linearLayoutColorEtapaDenunciaCDD.setBackgroundColor(Color.parseColor(respuestaGeneral.getDatosDenuncia().getColorEtapaCaso()));
                            textViewFolioDenunciaCDD.setText(respuestaGeneral.getDatosDenuncia().getFolio());
                            textViewNombreDenunciaCDD.setText(respuestaGeneral.getDatosDenuncia().getNombre());
                            textViewPorcentajeGeneralDenunciaCDD.setText(Utils.setFormatoNumeroEnteroPorcentaje(respuestaGeneral.getDatosDenuncia().getAvanceCaso()));
                            textViewTipoDelitoCDD.setText(respuestaGeneral.getDatosDenuncia().getTipoFraude());
                            if (respuestaGeneral.getDatosDenuncia().getFechaCompromiso() != null) {
                                textViewFechaCompromisoFaseCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(respuestaGeneral.getDatosDenuncia().getFechaCompromiso())));
                            } else {
                                textViewFechaCompromisoFaseCDD.setVisibility(View.GONE);
                                textViewFechaCompromisoFaseTextCDD.setVisibility(View.GONE);
                                //textViewFechaCompromisoFaseCDD.setText("");
                            }
                            textViewUnidadNegocioCDD.setText(respuestaGeneral.getDatosDenuncia().getUdN());
                            textViewZonaCDD.setText(respuestaGeneral.getDatosDenuncia().getZona());
                            textViewFechaResgistroCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(String.valueOf(respuestaGeneral.getDatosDenuncia().getFechaRegistro())));
                            if (respuestaGeneral.getDatosDenuncia().getIdStatusAutorizacion() == 1){
                                textViewAutorizacionCDD.setVisibility(View.GONE);
                                textViewAutorizacionTextCDD.setVisibility(View.GONE);
                            }else if (respuestaGeneral.getDatosDenuncia().getIdStatusAutorizacion() == 2) {
                                textViewAutorizacionCDD.setVisibility(View.VISIBLE);
                                textViewAutorizacionTextCDD.setVisibility(View.VISIBLE);
                                textViewAutorizacionCDD.setText(statusAutorizacion);
                            } else if (respuestaGeneral.getDatosDenuncia().getIdStatusAutorizacion() == 3) {
                                textViewAutorizacionCDD.setVisibility(View.GONE);
                                textViewAutorizacionTextCDD.setVisibility(View.GONE);
                                //textViewAutorizacionCDD.setText(statusAutorizacion);
                            } else if (respuestaGeneral.getDatosDenuncia().getIdStatusAutorizacion() == 4) {
                                textViewAutorizacionCDD.setVisibility(View.VISIBLE);
                                textViewAutorizacionTextCDD.setVisibility(View.VISIBLE);
                                textViewAutorizacionCDD.setText(statusAutorizacion);
                            }

                        } else {

                        }
                        swipeRefreshLayoutPFDF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerDatosCaso.concat(Constantes.signoInterrogacion).concat(Constantes.idCaso).concat(Constantes.signoIgual).concat(idCaso));
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCatalogoFaseActiva(Activity activity, String idCaso) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        fasesAdapter = new FasesAdapter(activity, fragmentManager, respuestaGeneral.getFaseActiva().getListData(), datosDenuncia, new FasesAdapter.OnItemClickListener() {
                            @Override
                            public void onIniciarFase(FaseActivaDatos faseActivaDatos, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("idCaso", idCaso);
                                bundle.putString("idCasoFase", String.valueOf(faseActivaDatos.getIdCasoFase()));
                                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_denuncia_fragment_to_navigation_iniciar_fase_fragment, bundle);
                            }

                            @Override
                            public void onReprogramarFase(FaseActivaDatos faseActivaDatos, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("idCaso", idCaso);
                                bundle.putString("idCasoFase", String.valueOf(faseActivaDatos.getIdCasoFase()));
                                bundle.putString("IdStatusAutorizacion", String.valueOf(faseActivaDatos.getIdStatusAutorizacion()));
                                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_denuncia_fragment_to_navigation_reprogramar_fase_fragment, bundle);
                            }

                            @Override
                            public void onCerrarFase(FaseActivaDatos faseActivaDatos, int position) {
                                //navigation_terminar_presentacion_denuncia_fragment
                                Bundle bundle = new Bundle();
                                bundle.putString("idCaso", idCaso);
                                bundle.putString("idCasoFase", String.valueOf(faseActivaDatos.getIdCasoFase()));
                                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_del_caso_fragment_to_navigation_terminar_presentacion_denuncia_fragment, bundle);

                            }
                        });

                        recyclerViewFacesDenunciaPFDF.setHasFixedSize(false);
                        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                        recyclerViewFacesDenunciaPFDF.setLayoutManager(layoutManagerCategory);
                        recyclerViewFacesDenunciaPFDF.setNestedScrollingEnabled(false);
                        recyclerViewFacesDenunciaPFDF.setAdapter(fasesAdapter);
                        swipeRefreshLayoutPFDF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoFaseActiva.concat("?").concat(Constantes.idCaso).concat("=").concat(idCaso));
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayoutDenunciaCCD:
                Bundle bundle = new Bundle();
                //bundle.putParcelable("guardaCatalogoCasoModel", guardaCatalogoCasoModel);
                //bundle.putParcelable("serialDatosCaso", serialDatosCaso);
                bundle.putString("idCaso", idCaso);
                Navigation.findNavController(v).navigate(R.id.action_estatus_del_caso_fragment_to_navigation_detalle_del_caso_fragment, bundle);
                break;

            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (Utils.isNetworkAvailable(activity)) {
            getServices(activity, idCaso);
        } else {
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }
}

