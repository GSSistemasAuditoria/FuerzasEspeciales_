package com.auditorias.fuerzasespeciales.ui.main.ui.finished;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.SQLite.TableDataUser;
import com.auditorias.fuerzasespeciales.models.RespuestaGeneral;
import com.auditorias.fuerzasespeciales.models.denunciaTerminada.DataCasosTerminados;
import com.auditorias.fuerzasespeciales.ui.main.ui.finished.adapters.CasosTermidasAdapter;
import com.auditorias.fuerzasespeciales.utils.AsyncTaskGral;
import com.auditorias.fuerzasespeciales.utils.Delegate;
import com.auditorias.fuerzasespeciales.utils.Functions;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class FinishedFragment extends Fragment {

    //TODO:variables generales de un fragment
    private View viewHF;
    //TODO: Context del fragment
    private Context contextHF;
    //TODO: Activity del fragment
    private Activity activityHF;
    //TODO: el fragmentManages del fragment para poder hacer transsactiones
    private FragmentManager fragmentManagerHF;
    //TODO: clase general para poder hacer funcionalidades por medio de objetos en el fragment
    private Functions functions;
    //TODO: todos los recyclerview
    private RecyclerView recyclerViewCasosAbogadoIF;

    //TODO: listas del fragment
    private final List<DataCasosTerminados> listCasoTerminados = new ArrayList<>();

    private TextView textViewSubTiutuloCST;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewHF = inflater.inflate(R.layout.fragment_finished, container, false);
        contextHF = getContext();
        activityHF = getActivity();
        fragmentManagerHF = getFragmentManager();
        functions = new Functions(contextHF);

        textViewSubTiutuloCST = viewHF.findViewById(R.id.textViewSubTiutuloCST);
        textViewSubTiutuloCST.setText(getString(R.string.title_complaints_finished));
        recyclerViewCasosAbogadoIF = viewHF.findViewById(R.id.recyclerViewDenunciasAbogadoIF);
        recyclerViewCasosAbogadoIF.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(activityHF);
        recyclerViewCasosAbogadoIF.setLayoutManager(layoutManagerCategory);

        if (Functions.isNetworkAvailable(contextHF)) {
            getCasosAbogado(activityHF, viewHF);
        } else {
            Utils.message(activityHF, getString(R.string.text_label_error_de_conexion));
        }

        return viewHF;
    }

    public void getCasosAbogado(Activity activity, View view) {

        try {
            listCasoTerminados.clear();
            if (Functions.isNetworkAvailable(activity)) {
                new AsyncTaskGral(activity, new Delegate() {
                    @Override
                    public void getDelegate(String result) {
                        Gson gson = new Gson();
                        //Serial serial = gson.fromJson(result, Serial.class);
                        RespuestaGeneral respuestaGeneral = gson.fromJson(result, RespuestaGeneral.class);

                        listCasoTerminados.addAll(respuestaGeneral.getCasosTerminados().getDataCasosTerminados());

                        CasosTermidasAdapter casosAbogadoAdapter = new CasosTermidasAdapter(activity, fragmentManagerHF, listCasoTerminados, new CasosTermidasAdapter.OnListenerCasosTerminados() {
                            @Override
                            public void onItemClick(DataCasosTerminados casosTerminados, int position) {
                                //action_navigation_finished_to_navigation_detalle_del_caso_fragment
                                Bundle bundle = new Bundle();
                                bundle.putString("idCaso", String.valueOf(casosTerminados.getId()));
                                Navigation.findNavController(view).navigate(R.id.action_navigation_finished_to_navigation_detalle_del_caso_fragment, bundle);
                            }
                        });
                        recyclerViewCasosAbogadoIF.setAdapter(casosAbogadoAdapter);
                        recyclerViewCasosAbogadoIF.setHasFixedSize(true);
                        recyclerViewCasosAbogadoIF.setLayoutManager(new LinearLayoutManager(activity));
                    }

                    @Override
                    public void executeInBackground(String result, String header) {

                    }
                }, getString(R.string.text_label_cargando)).execute(Constantes.METHOD_GET, Constantes.obtenerCasosTerminadosAbogado.concat(Constantes.signoInterrogacion).concat(Constantes.idAbogado).concat(Constantes.signoIgual).concat(functions.getIdUser(activity)));//.concat(String.valueOf(10031124)));//.concat(functions.getIdUser()));
            } else {
                Utils.message(activity, getString(R.string.text_label_error_de_conexion));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}