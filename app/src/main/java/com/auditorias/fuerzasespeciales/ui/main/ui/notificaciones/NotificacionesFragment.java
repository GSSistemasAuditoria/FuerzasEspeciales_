package com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.auditorias.fuerzasespeciales.models.notificaciones.DataNotificacion;
import com.auditorias.fuerzasespeciales.request.notificaciones.ActualizarNotificacionRequest;
import com.auditorias.fuerzasespeciales.request.notificaciones.NotificacionLeida;
import com.auditorias.fuerzasespeciales.request.notificaciones.Notificaciones;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters.DenunciasPorVencerAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters.ReprogramacionesAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Bundle args;
    private View view;
    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;
    private TextView textViewSubTiutuloCST;
    private TextView textViewDenunciasPorVencerNF;
    private TextView textViewDenunciasPorVencerMostrarNF;
    private TextView textViewReprogramacionesNF;
    private TextView textViewReprogramacionesMostrarNF;
    private SwipeRefreshLayout swipeRefreshLayoutNF;
    private RecyclerView recyclerViewDenunciasPorVencerNF;
    private RecyclerView recyclerViewReprogramacionesNF;
    private String mParam1;
    private String mParam2;
    private String valorDeConfiguraciontipoAppMovil;
    private String descripcionConfiguraciontipoAppMovil;

    private List<DataNotificacion> listDenunciasPorVencer = new ArrayList<>();
    private List<DataNotificacion> listReprogramaciones = new ArrayList<>();

    private DenunciasPorVencerAdapter denunciasAdapter;
    private ReprogramacionesAdapter reprogramacionesAdapter;

    private int banderaDenuncias = 1;
    private int banderaReprogramaciones = 1;


    public NotificacionesFragment() {
        // Required empty public constructor
    }

    /*public static notificacionesFragment newInstance(String param1, String param2) {
        notificacionesFragment fragment = new notificacionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notificaciones, container, false);
        activity = getActivity();
        context = getContext();
        fragmentManager = getFragmentManager();

        getEnlaces(view);

        getObtenerConfiguracionTipoAppMovil(activity);
        textViewSubTiutuloCST.setText(getString(R.string.title_notificaciones));

        setObtenerNotificacionesUsuario(activity, Integer.parseInt(TableDataUser.getIdEmpleado(activity)), Integer.parseInt("2"));





        //new Section
        return view;
    }

    public void getEnlaces(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);

        textViewDenunciasPorVencerNF = view.findViewById(R.id.textViewDenunciasPorVencerNF);
        textViewDenunciasPorVencerMostrarNF = view.findViewById(R.id.textViewDenunciasPorVencerMostrarNF);
        textViewDenunciasPorVencerMostrarNF.setOnClickListener(this);
        textViewReprogramacionesNF = view.findViewById(R.id.textViewReprogramacionesNF);
        textViewReprogramacionesMostrarNF = view.findViewById(R.id.textViewReprogramacionesMostrarNF);
        textViewReprogramacionesMostrarNF.setOnClickListener(this);
        recyclerViewDenunciasPorVencerNF = view.findViewById(R.id.recyclerViewDenunciasPorVencerNF);
        recyclerViewReprogramacionesNF = view.findViewById(R.id.recyclerViewReprogramacionesNF);

        swipeRefreshLayoutNF = view.findViewById(R.id.swipeRefreshLayoutNF);
        swipeRefreshLayoutNF.setOnRefreshListener(this);
        swipeRefreshLayoutNF.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
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
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerConfiguracion.concat(Constantes.signoInterrogacion).concat(Constantes.clave).concat(Constantes.signoIgual).concat(Constantes.tipoAppMovil)).get();
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setObtenerNotificacionesUsuario(Activity activity, int idUsuario, int idTipoApp) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();

                String params = gsonParams.toJson(new Notificaciones(idTipoApp, idUsuario));
                //
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getNotificacionesUsuario().getExito().equals(Constantes.exitoTrue)) {
                            for (int x = 0; x < respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().size(); x++) {
                                if (respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x).getIdTipoNotificacion().equals(2)) {
                                    textViewDenunciasPorVencerNF.setText(respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x).getTipoNotificacion());
                                    listDenunciasPorVencer.add(respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x));
                                } else if (respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x).getIdTipoNotificacion().equals(1)) {
                                    textViewReprogramacionesNF.setText(respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x).getTipoNotificacion());
                                    listReprogramaciones.add(respuestaGeneral.getNotificacionesUsuario().getDataNotificacions().get(x));
                                }
                            }
                            denunciasAdapter = new DenunciasPorVencerAdapter(activity, fragmentManager, listDenunciasPorVencer, new DenunciasPorVencerAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(DataNotificacion notificacion, int position) {
                                    setActualizarNotificacion(activity, notificacion.getId(), notificacion.getIdCaso(), notificacion.getIdTipoNotificacion());
                                }
                            });
                            recyclerViewDenunciasPorVencerNF.setHasFixedSize(false);
                            recyclerViewDenunciasPorVencerNF.setNestedScrollingEnabled(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                            recyclerViewDenunciasPorVencerNF.setLayoutManager(layoutManagerCategory);
                            recyclerViewDenunciasPorVencerNF.setAdapter(denunciasAdapter);

                            reprogramacionesAdapter = new ReprogramacionesAdapter(activity, fragmentManager, listReprogramaciones, new ReprogramacionesAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(DataNotificacion notificacion, int position) {
                                    setActualizarNotificacion(activity, notificacion.getId(), notificacion.getIdCaso(), notificacion.getIdTipoNotificacion());
                                }
                            });
                            recyclerViewReprogramacionesNF.setHasFixedSize(false);
                            recyclerViewReprogramacionesNF.setNestedScrollingEnabled(false);
                            RecyclerView.LayoutManager layoutManagerCategory2 = new LinearLayoutManager(activity);
                            recyclerViewReprogramacionesNF.setLayoutManager(layoutManagerCategory2);
                            recyclerViewReprogramacionesNF.setAdapter(reprogramacionesAdapter);

                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getNotificacionesUsuario().getError());
                        }

                        swipeRefreshLayoutNF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.obtenerNotificacionesUsuario, params);
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActualizarNotificacion(Activity activity, int idNotificacion, int idDenuncia, int IdTipoNotificacion) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();
                String params = gsonParams.toJson(new NotificacionLeida(new ActualizarNotificacionRequest(idNotificacion)));
                //
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getActualizarNotificacion().getExito().equals(Constantes.exitoTrue)) {
                            //action_navigation_notificaciones_fragment_to_navigation_proceso_fase_denuncia_fragment
                            Bundle bundle = new Bundle();
                            bundle.putString("idCaso", String.valueOf(idDenuncia));
                            //bundle.putString("idCaso", idDenuncia);
                            Navigation.findNavController(view).navigate(R.id.action_navigation_notificaciones_fragment_to_navigation_proceso_fase_denuncia_fragment, bundle);
                        } else {
                            Utils.messageShort(activity, respuestaGeneral.getActualizarNotificacion().getError());
                        }

                        swipeRefreshLayoutNF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_POST, Constantes.actualizarNotificacion, params);
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
            case R.id.textViewDenunciasPorVencerMostrarNF:
                if (banderaDenuncias == 0) {
                    textViewDenunciasPorVencerMostrarNF.setText(getString(R.string.text_label_ocultar));
                    recyclerViewDenunciasPorVencerNF.setVisibility(View.VISIBLE);
                    banderaDenuncias = 1;
                } else {
                    textViewDenunciasPorVencerMostrarNF.setText(getString(R.string.text_label_mostrar));
                    recyclerViewDenunciasPorVencerNF.setVisibility(View.GONE);
                    banderaDenuncias = 0;
                }
                break;

            case R.id.textViewReprogramacionesMostrarNF:
                if (banderaReprogramaciones == 0) {
                    textViewReprogramacionesMostrarNF.setText(getString(R.string.text_label_ocultar));
                    recyclerViewReprogramacionesNF.setVisibility(View.VISIBLE);
                    banderaReprogramaciones = 1;
                } else {
                    textViewReprogramacionesMostrarNF.setText(getString(R.string.text_label_mostrar));
                    recyclerViewReprogramacionesNF.setVisibility(View.GONE);
                    banderaReprogramaciones = 0;
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (Utils.isNetworkAvailable(activity)) {
            setObtenerNotificacionesUsuario(activity, Integer.parseInt(TableDataUser.getIdEmpleado(activity)), Integer.parseInt(valorDeConfiguraciontipoAppMovil));
        } else {
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }
}