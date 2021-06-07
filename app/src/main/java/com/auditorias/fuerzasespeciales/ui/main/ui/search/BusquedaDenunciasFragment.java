package com.auditorias.fuerzasespeciales.ui.main.ui.search;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.inicioAdapters.DenunciasAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BusquedaDenunciasFragment extends Fragment implements View.OnClickListener {

    //private NotificationsViewModel notificationsViewModel;

    private final List<CasosAbogado> listCasosAbogado = new ArrayList<>();

    RecyclerView rv;

    private ImageView imageViewBusquedaBusquedaBDF;
    private RecyclerView recyclerViewBusquedaBDF;
    private TextInputEditText textInputEditTextBusquedaBDF;
    private TextInputLayout textInputLayoutBusquedaBDF;
    private TextView textViewSubTiutuloCST;
    private Bundle args;
    private View view;
    private Context mContextCDF;
    private Activity activityCDF;
    private FragmentManager mFragmentManagerCDF;

    private String textBusqueda = "";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        view = inflater.inflate(R.layout.fragment_busqueda_denuncias, container, false);
        activityCDF = getActivity();
        mContextCDF = getContext();
        mFragmentManagerCDF = getFragmentManager();

        refereciasConInterface(view);
        textViewSubTiutuloCST.setText(getString(R.string.title_search));

        args = getArguments();
        if (args != null) {
            textBusqueda = savedInstanceState.getString("textBusqueda");
            //listCasosAbogado = savedInstanceState.getParcelableArrayList("listCasosAbogado");
        } else {
            //textBusqueda = notificationsViewModel.getText();//generateRandomGoodDeed();
        }

        if (!textBusqueda.isEmpty()) {
            textInputEditTextBusquedaBDF.setText(textBusqueda);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putBoolean(IS_EDITING_KEY, isEditing);
        outState.putString("textBusqueda", textBusqueda);
        //outState.putParcelableArray("listCasosAbogado", listCasosAbogado);
    }

    public void refereciasConInterface(View view) {
        recyclerViewBusquedaBDF = view.findViewById(R.id.recyclerViewBusquedaBDF);
        textInputEditTextBusquedaBDF = view.findViewById(R.id.textInputEditTextBusquedaBDF);
        textInputLayoutBusquedaBDF = view.findViewById(R.id.textInputLayoutBusquedaBDF);
        textViewSubTiutuloCST = view.findViewById(R.id.textViewSubTiutuloCST);
        imageViewBusquedaBusquedaBDF = view.findViewById(R.id.imageViewBusquedaBusquedaBDF);
        imageViewBusquedaBusquedaBDF.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewBusquedaBusquedaBDF) {
            textBusqueda = textInputEditTextBusquedaBDF.getText().toString().trim();
            if (textBusqueda.isEmpty()) {
                Utils.messageShort(activityCDF, "no tiene ninguna busqueda");
            } else {
                getCasosAbogado(activityCDF, v, textBusqueda);
            }
        }
    }

    public void getCasosAbogado(Activity activity, View view, String textBusqueda) {
        try {
            if (Utils.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        if (respuestaGeneral.getListCasosAbogado() != null || !respuestaGeneral.getListCasosAbogado().isEmpty()) {
                            DenunciasAdapter denunciasAdapter = new DenunciasAdapter(activity, mFragmentManagerCDF, respuestaGeneral.getListCasosAbogado(), new DenunciasAdapter.OnClickListener() {
                                @Override
                                public void onItemClick(CasosAbogado casosAbogado, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("idCaso", String.valueOf(casosAbogado.getId()));
                                    Navigation.findNavController(view).navigate(R.id.action_navigation_inicio_fragment_to_navigation_procesos_fase_caso_fragment, bundle);
                                    //Log.i(TAG, "onItemClick: " +TablaConfiguraciones.getClaveConfiguraciones(activity));
                                }
                            });
                            recyclerViewBusquedaBDF.setHasFixedSize(false);
                            RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activity);
                            recyclerViewBusquedaBDF.setLayoutManager(layoutManagerCategory);
                            recyclerViewBusquedaBDF.setAdapter(denunciasAdapter);
                        } else {
                            Utils.messageShort(activity, "No hay datos");
                        }
                        //mSwipeRefreshLayoutIF.setRefreshing(false);
                    }

                    @Override
                    public void executeInBackground(String result, String header) {
                        TableDataUser.updateJWT(activity, header);
                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET,
                        Constantes.ObtenerCasosAbogado.concat(Constantes.signoInterrogacion).concat(Constantes.idAbogado).concat(Constantes.signoIgual).concat(Functions.getIdUser(activity)).concat(Constantes.signoAnd).concat(Constantes.filtro).concat(Constantes.signoIgual).concat(textBusqueda));
            } else {
                Utils.messageShort(activity, getString(R.string.text_label_error_de_conexion));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}