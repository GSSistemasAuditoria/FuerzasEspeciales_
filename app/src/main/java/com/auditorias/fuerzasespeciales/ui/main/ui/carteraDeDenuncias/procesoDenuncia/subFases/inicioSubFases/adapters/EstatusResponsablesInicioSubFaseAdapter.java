package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.inicioSubFases.adapters;

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
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class EstatusResponsablesInicioSubFaseAdapter extends RecyclerView.Adapter<EstatusResponsablesInicioSubFaseAdapter.EstatusResponsablesInicioSubFaseViewHolder> {

    private final Activity activity;
    private final List<DatosDenunciaResponsable> listResposablesCerrar;
    private final LayoutInflater inflater;
    private final OnItemSelectedListener itemSelectedListenerCerrar;
    private FragmentManager fm;
    private List<EstatusResponsableFase> listEstatusDeResposablesSelecionar;

    public EstatusResponsablesInicioSubFaseAdapter(Activity activity, List<DatosDenunciaResponsable> listResposablesCerrar, FragmentManager fm, List<EstatusResponsableFase> listEstatusDeResposablesSelecionar, OnItemSelectedListener itemSelectedListenerCerrar) {
        this.activity = activity;
        this.fm = fm;
        this.listResposablesCerrar = listResposablesCerrar;
        this.listEstatusDeResposablesSelecionar = listEstatusDeResposablesSelecionar;
        this.itemSelectedListenerCerrar = itemSelectedListenerCerrar;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public EstatusResponsablesInicioSubFaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_imputados_estatus_item, parent, false);
        return new EstatusResponsablesInicioSubFaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EstatusResponsablesInicioSubFaseViewHolder holder, final int position) {
        holder.bind(activity, listResposablesCerrar.get(position), listEstatusDeResposablesSelecionar, itemSelectedListenerCerrar);
    }

    @Override
    public int getItemCount() {
        return listResposablesCerrar.size();
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int idCasoResponsable, int idStatusResponsable);
    }

    static class EstatusResponsablesInicioSubFaseViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNumeroEmpeadoTERCA;
        TextView textViewNombreEmpeadoTERCA;
        TextView textViewTipoEmpeadoTERCA;
        Spinner spinnerEstatusResponsable;

        EstatusResponsablesInicioSubFaseViewHolder(View view) {
            super(view);
            textViewNumeroEmpeadoTERCA = view.findViewById(R.id.textViewNumeroEmpledoResponsableCIE);
            textViewNombreEmpeadoTERCA = view.findViewById(R.id.textViewNombreResponsableCIE);
            textViewTipoEmpeadoTERCA = view.findViewById(R.id.textViewTipoImputadoCIE);
            spinnerEstatusResponsable = view.findViewById(R.id.spinnerEstatusResponsableCIE);
        }

        //TODO: llenado de los items que se encuentran dentro del recyclerview
        public void bind(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listStatusResponsableFase, OnItemSelectedListener itemSelectedListener) {
            if (datosDenunciaResponsable.getIdEmpleado() == null /*|| String.valueOf(responsableModel.getIdEmpleado()).isEmpty() || responsableModel.getIdEmpleado().equals(0)*/) {
                textViewNumeroEmpeadoTERCA.setVisibility(View.GONE);
                textViewNumeroEmpeadoTERCA.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
            } else {
                textViewNumeroEmpeadoTERCA.setVisibility(View.VISIBLE);
                textViewNumeroEmpeadoTERCA.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
            }

            textViewNombreEmpeadoTERCA.setText(datosDenunciaResponsable.getNombre());
            textViewTipoEmpeadoTERCA.setText(datosDenunciaResponsable.getTipoEmpleado());
            List<EstatusResponsableFase> listEstatusDeResposable = new ArrayList<>();
            listEstatusDeResposable.add(new EstatusResponsableFase(datosDenunciaResponsable.getStatusResponsable(), "", datosDenunciaResponsable.getIdStatusResponsable(), 0));
            for (int x = 0; x < listStatusResponsableFase.size(); x++) {
                if (!datosDenunciaResponsable.getIdStatusResponsable().equals(listStatusResponsableFase.get(x).getId())) {
                    listEstatusDeResposable.add(listStatusResponsableFase.get(x));
                }
            }

            getStatusResponsableFaseList(activity, datosDenunciaResponsable, listEstatusDeResposable, spinnerEstatusResponsable, itemSelectedListener);
        }


        //TODO: llenado del spinner de status de cada uno de los responsables dentro de la lista de responsables
        public void getStatusResponsableFaseList(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> statusResponsableFaseList, Spinner spinner, OnItemSelectedListener itemSelectedListener) {
            ArrayAdapter<EstatusResponsableFase> myAdapter = new EstatusImputadoArrayAdapter(activity, R.layout.cell_estatus_responsable_spinner_item, statusResponsableFaseList);
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
        }
    }
}