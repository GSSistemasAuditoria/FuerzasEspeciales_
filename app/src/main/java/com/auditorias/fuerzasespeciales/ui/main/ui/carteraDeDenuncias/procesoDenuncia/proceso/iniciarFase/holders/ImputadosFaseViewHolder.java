package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.holders;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.ImputadosFaseAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;

import java.util.List;

public class ImputadosFaseViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoResponsableCIE;
    TextView textViewNombreResponsableCIE;
    TextView textViewTipoEmpleadoResponsableCIE;
    TextView textViewTipoResponsableCIE;
    Spinner spinnerEstatusResponsableCIE;


    public ImputadosFaseViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoResponsableCIE = view.findViewById(R.id.textViewNumeroEmpledoResponsableCIE);
        textViewNombreResponsableCIE = view.findViewById(R.id.textViewNombreResponsableCIE);
        textViewTipoEmpleadoResponsableCIE = view.findViewById(R.id.textViewTipoEmpleadoResponsableCIE);
        textViewTipoResponsableCIE = view.findViewById(R.id.textViewTipoResponsableCIE);
        spinnerEstatusResponsableCIE = view.findViewById(R.id.spinnerEstatusResponsableCIE);
    }

    //TODO: llenado de los items que se encuentran dentro del recyclerview
    public void bind(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listStatusResponsableFase, ImputadosFaseAdapter.OnItemSelectedListener itemSelectedListener) {
        if (datosDenunciaResponsable.getIdEmpleado() == null) {
            textViewNumeroEmpledoResponsableCIE.setVisibility(View.GONE);
        } else {
            textViewNumeroEmpledoResponsableCIE.setVisibility(View.VISIBLE);
            textViewNumeroEmpledoResponsableCIE.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
        }

        textViewNombreResponsableCIE.setText(datosDenunciaResponsable.getNombre());
        textViewTipoEmpleadoResponsableCIE.setText(datosDenunciaResponsable.getTipoEmpleado());
        textViewTipoResponsableCIE.setText(datosDenunciaResponsable.getTipoResponsable());
        setEstatusImputado(activity, datosDenunciaResponsable, listStatusResponsableFase, spinnerEstatusResponsableCIE, itemSelectedListener);
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