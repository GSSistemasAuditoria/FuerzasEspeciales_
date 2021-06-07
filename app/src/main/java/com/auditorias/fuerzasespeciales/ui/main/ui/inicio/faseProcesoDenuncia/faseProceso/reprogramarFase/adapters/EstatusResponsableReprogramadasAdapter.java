package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.reprogramarFase.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.StatusResponsableFaseModel;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.terminarFase.adapters.EstatusResponsableFaseCerrarAdapter;

import java.util.ArrayList;
import java.util.List;

public class EstatusResponsableReprogramadasAdapter extends RecyclerView.Adapter<EstatusResponsableReprogramadasAdapter.EstatusResponsableReprogramadasViewHolder> {

    private final Activity activity;
    private final List<DatosDenunciaResponsable> listResposablesCerrar;
    private final LayoutInflater inflater;
    private final EstatusResponsableFaseCerrarAdapter.OnItemSelectedListener itemSelectedListenerCerrar;
    private FragmentManager fm;
    private List<StatusResponsableFaseModel> listEstatusDeResposablesSelecionar;

    public EstatusResponsableReprogramadasAdapter(Activity activity, List<DatosDenunciaResponsable> listResposablesCerrar, FragmentManager fm, List<StatusResponsableFaseModel> listEstatusDeResposablesSelecionar, EstatusResponsableFaseCerrarAdapter.OnItemSelectedListener itemSelectedListenerCerrar) {
        this.activity = activity;
        this.fm = fm;
        this.listResposablesCerrar = listResposablesCerrar;
        this.listEstatusDeResposablesSelecionar = listEstatusDeResposablesSelecionar;
        this.itemSelectedListenerCerrar = itemSelectedListenerCerrar;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public EstatusResponsableReprogramadasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_imputados_estatus_item, parent, false);
        return new EstatusResponsableReprogramadasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EstatusResponsableReprogramadasViewHolder holder, final int position) {
        holder.bind(activity, listResposablesCerrar.get(position), listEstatusDeResposablesSelecionar, itemSelectedListenerCerrar);
    }

    @Override
    public int getItemCount() {
        return listResposablesCerrar.size();
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int IdCasoResponsable, int idStatusResponsable);
    }

    static class EstatusResponsableReprogramadasViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNumeroEmpeadoTERCA;
        TextView textViewNombreEmpeadoTERCA;
        TextView textViewTipoEmpeadoTERCA;
        Spinner spinnerEstatusResponsable;

        EstatusResponsableReprogramadasViewHolder(View view) {
            super(view);
            textViewNumeroEmpeadoTERCA = view.findViewById(R.id.textViewNumeroEmpledoImputadoCIE);
            textViewNombreEmpeadoTERCA = view.findViewById(R.id.textViewNombreImputadoCIE);
            textViewTipoEmpeadoTERCA = view.findViewById(R.id.textViewTipoImputadoCIE);
            spinnerEstatusResponsable = view.findViewById(R.id.spinnerEstatusImputadoCIE);
        }

        //TODO: llenado de los items que se encuentran dentro del recyclerview
        public void bind(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<StatusResponsableFaseModel> listStatusResponsableFase, EstatusResponsableFaseCerrarAdapter.OnItemSelectedListener itemSelectedListener) {
            if (datosDenunciaResponsable.getIdEmpleado() == null /*|| String.valueOf(responsableModel.getIdEmpleado()).isEmpty() || responsableModel.getIdEmpleado().equals(0)*/) {
                textViewNumeroEmpeadoTERCA.setVisibility(View.GONE);
                textViewNumeroEmpeadoTERCA.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
            } else {
                textViewNumeroEmpeadoTERCA.setVisibility(View.VISIBLE);
                textViewNumeroEmpeadoTERCA.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
            }

            textViewNombreEmpeadoTERCA.setText(datosDenunciaResponsable.getNombre());
            textViewTipoEmpeadoTERCA.setText(datosDenunciaResponsable.getTipoEmpleado());
            List<StatusResponsableFaseModel> listEstatusDeResposable = new ArrayList<>();
            listEstatusDeResposable.add(new StatusResponsableFaseModel(datosDenunciaResponsable.getStatusResponsable(), "", datosDenunciaResponsable.getIdStatusResponsable(), 0));
            for (int x = 0; x < listStatusResponsableFase.size(); x++) {
                if (!datosDenunciaResponsable.getIdStatusResponsable().equals(listStatusResponsableFase.get(x).getId())) {
                    listEstatusDeResposable.add(listStatusResponsableFase.get(x));
                }
            }

            //getStatusResponsableFaseList(activity, datosDenunciaResponsable, listEstatusDeResposable, spinnerEstatusResponsable, itemSelectedListener);
        }


        //TODO: llenado del spinner de status de cada uno de los responsables dentro de la lista de responsables
        /*public void getStatusResponsableFaseList(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<StatusResponsableFaseModel> statusResponsableFaseList, Spinner spinner, EstatusResponsableFaseCerrarAdapter.OnItemSelectedListener itemSelectedListener) {
            ArrayAdapter<StatusResponsableFaseModel> myAdapter = new EstatusImputadoArrayAdapter(activity, R.layout.cell_estatus_responsable_spinner_item, statusResponsableFaseList);
            spinner.setAdapter(myAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    itemSelectedListener.onItemSelectedListener(datosDenunciaResponsable, datosDenunciaResponsable.getIdCasoFase(), datosDenunciaResponsable.getIdCasoResponsable(), statusResponsableFaseList.get(position).getId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }*/
    }
}