package com.auditorias.fuerzasespeciales.ui.main.ui.inicio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.models.catalogos.etapa.EtapaCaso;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.inicioAdapters.DenunciasAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = InicioFragment.class.getName();

    //TODO: listas del fragment
    private List<EtapaCaso> listEtapaCaso = new ArrayList<>();

    //TODO:variables generales de un fragment
    private View view;
    //TODO: Context del fragment
    private Context context;
    //TODO: Activity del fragment
    private Activity activity;
    //TODO: el fragmentManages del fragment para poder hacer transsactiones
    private FragmentManager fragmentManager;

    //TODO: todos los recyclerview
    private RecyclerView recyclerViewDenunciasAbogadoIF;

    //TODO: es el swiperefreshlayout
    private SwipeRefreshLayout swipeRefreshLayoutIF;

    //TODO: todos los linearLayouts
    private LinearLayout linearLayoutEtapaIF;
    private TextView textViewSubTiutuloCST;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        context = getContext();
        activity = getActivity();
        fragmentManager = getFragmentManager();

        linearLayoutEtapaIF = view.findViewById(R.id.linearLayoutEtapaIF);
        recyclerViewDenunciasAbogadoIF = view.findViewById(R.id.recyclerViewDenunciasAbogadoIF);
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);

        swipeRefreshLayoutIF = view.findViewById(R.id.mSwipeRefreshLayoutIF);
        swipeRefreshLayoutIF.setOnRefreshListener(this);
        swipeRefreshLayoutIF.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);

        if (Functions.isNetworkAvailable(context)) {
            getEtapaCasoOnline(activity, linearLayoutEtapaIF);
            getCasosAbogado(activity, view);
            getObtenerDetalleUsuario(activity);

        } else {
            Utils.message(activity, getString(R.string.text_label_error_de_conexion));
        }

        return view;
    }

    public void getEtapaCasoOnline(Activity activity, LinearLayout linearLayout) {
        listEtapaCaso.clear();
        try {
            if (Utils.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getListEtapaCaso() != null || !respuestaGeneral.getListEtapaCaso().isEmpty()) {
                            listEtapaCaso.addAll(respuestaGeneral.getListEtapaCaso());
                            Utils.llenadoDeEtapa(linearLayout, listEtapaCaso, activity);
                        } else {
                            Utils.messageShort(activity, getString(R.string.text_label_no_se_encontraron_datos));
                        }
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }

                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCatalogoEtapaCaso);
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void getCasosAbogado(Activity activity, View view) {
        try {
            if (Utils.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getListCasosAbogado() != null || !respuestaGeneral.getListCasosAbogado().isEmpty()) {
                            DenunciasAdapter denunciasAdapter = new DenunciasAdapter(activity, fragmentManager, respuestaGeneral.getListCasosAbogado(), new DenunciasAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(CasosAbogado casosAbogado, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idCaso", String.valueOf(casosAbogado.getId()));
                                    Navigation.findNavController(view).navigate(R.id.action_navigation_inicio_fragment_to_navigation_procesos_fase_caso_fragment, bundle);
                                }
                            });
                            recyclerViewDenunciasAbogadoIF.setHasFixedSize(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(InicioFragment.this.activity);
                            recyclerViewDenunciasAbogadoIF.setLayoutManager(layoutManagerCategory);
                            recyclerViewDenunciasAbogadoIF.setAdapter(denunciasAdapter);
                        } else {
                            Utils.messageShort(activity, getString(R.string.text_label_no_se_encontraron_datos));
                        }
                        swipeRefreshLayoutIF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.ObtenerCasosAbogado.concat(Constantes.signoInterrogacion).concat(Constantes.idAbogado).concat(Constantes.signoIgual).concat(Functions.getIdUser(activity)));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        textViewSubTiutuloCST.setText(respuestaGeneral.getDetalleUsuario().get(x).getNombre().concat(" - ").concat(respuestaGeneral.getDetalleUsuario().get(x).getPerfil()));
                    }
                }

                @Override
                public void executeInBackground(String result, String header) {

                }
            }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerDetalleUsuario.concat(Constantes.signoInterrogacion).concat(Constantes.idUsuario).concat(Constantes.signoIgual).concat(TableDataUser.getIdEmpleado(activity)));
        } else {
            //showDialogErrorConeccion(activity, view, getString(R.string.text_label_error_de_conexion), getString(R.string.text_label_intentalo_mas_tarde), getString(R.string.text_label_intentar_de_nuevo), 1);
            //Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }

    @Override
    public void onRefresh() {
        if (Utils.isNetworkAvailable(context)) {
            //getEtapaCasoOnline(activity, linearLayoutEtapaIF);
            getCasosAbogado(activity, view);
            getObtenerDetalleUsuario(activity);
        } else {
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }
}

