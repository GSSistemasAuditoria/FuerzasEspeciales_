package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.holders;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters.ImputadosFaseAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;

import java.util.List;

public class ImputadosFaseViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoImputadoCIE;
    TextView textViewNombreImputadoCIE;
    TextView textViewTipoImputadoCIE;
    Spinner spinnerEstatusImputadoCIE;

    public ImputadosFaseViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoImputadoCIE = view.findViewById(R.id.textViewNumeroEmpledoImputadoCIE);
        textViewNombreImputadoCIE = view.findViewById(R.id.textViewNombreImputadoCIE);
        textViewTipoImputadoCIE = view.findViewById(R.id.textViewTipoImputadoCIE);
        spinnerEstatusImputadoCIE = view.findViewById(R.id.spinnerEstatusImputadoCIE);
    }

    //TODO: llenado de los items que se encuentran dentro del recyclerview
    public void bind(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listStatusResponsableFase, ImputadosFaseAdapter.OnItemSelectedListener itemSelectedListener) {
        if (datosDenunciaResponsable.getIdEmpleado() == null) {
            textViewNumeroEmpledoImputadoCIE.setVisibility(View.GONE);
        } else {
            textViewNumeroEmpledoImputadoCIE.setVisibility(View.VISIBLE);
            textViewNumeroEmpledoImputadoCIE.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
        }

        textViewNombreImputadoCIE.setText(datosDenunciaResponsable.getNombre());
        textViewTipoImputadoCIE.setText(datosDenunciaResponsable.getTipoEmpleado());

        setEstatusImputado(activity, datosDenunciaResponsable, listStatusResponsableFase, spinnerEstatusImputadoCIE, itemSelectedListener);
    }


    //TODO: llenado del spinner de status de cada uno de los responsables dentro de la lista de responsables
    public void setEstatusImputado(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listEstatusImputadoFaseList, Spinner spinner, ImputadosFaseAdapter.OnItemSelectedListener itemSelectedListener) {
        ArrayAdapter<EstatusResponsableFase> myAdapter = new EstatusImputadoArrayAdapter(activity, R.layout.cell_estatus_responsable_spinner_item, listEstatusImputadoFaseList);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelectedListener.onItemSelectedListener(datosDenunciaResponsable, datosDenunciaResponsable.getIdCasoFase(), datosDenunciaResponsable.getIdCasoResponsable(), listEstatusImputadoFaseList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}