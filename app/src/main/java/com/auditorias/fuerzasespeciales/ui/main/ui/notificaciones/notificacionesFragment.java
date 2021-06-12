package com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.notificaciones.DataNotificacion;
import com.auditorias.fuerzasespeciales.request.envioRequest;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters.NotificacionesAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

public class notificacionesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Bundle args;
    private View view;
    private Context context;
    private Activity activity;
    private FragmentManager fragmentManager;
    private TextView textViewSubTiutuloCST;
    private SwipeRefreshLayout swipeRefreshLayoutNF;
    private RecyclerView recyclerViewNotificacionesNF;
    private String mParam1;
    private String mParam2;

    public notificacionesFragment() {
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

        textViewSubTiutuloCST.setText(getString(R.string.title_notificaciones));

        setObtenerNotificacionesUsuario(activity, Integer.parseInt(TableDataUser.getIdEmpleado(activity)));

        //new Section
        return view;
    }

    public void getEnlaces(View view) {
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);

        recyclerViewNotificacionesNF = view.findViewById(R.id.recyclerViewNotificacionesNF);

        swipeRefreshLayoutNF = view.findViewById(R.id.swipeRefreshLayoutNF);
        swipeRefreshLayoutNF.setOnRefreshListener(this);
        swipeRefreshLayoutNF.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
    }

    public void setObtenerNotificacionesUsuario(Activity activity, int idUsuario) {
        try {
            if (Functions.isNetworkAvailable(activity)) {
                Gson gsonParams = new Gson();

                String params = gsonParams.toJson(new envioRequest(idUsuario));
                //
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);
                        if (respuestaGeneral.getNotificacionesUsuario().getExito().equals(Constantes.exitoTrue)) {
                            NotificacionesAdapter denunciasAdapter = new NotificacionesAdapter(activity, fragmentManager, respuestaGeneral.getNotificacionesUsuario().getDataNotificacions(), new NotificacionesAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(DataNotificacion notificacion, int position) {

                                }
                            });
                            recyclerViewNotificacionesNF.setHasFixedSize(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                            recyclerViewNotificacionesNF.setLayoutManager(layoutManagerCategory);
                            recyclerViewNotificacionesNF.setAdapter(denunciasAdapter);
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

    @Override
    public void onRefresh() {
        if (Utils.isNetworkAvailable(activity)) {
            setObtenerNotificacionesUsuario(activity, Integer.parseInt(TableDataUser.getIdEmpleado(activity)));
        } else {
            Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
        }
    }
}