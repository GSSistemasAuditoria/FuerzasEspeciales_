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
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusResponsablesInicioAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EstatusResponsablesInicioViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoResponsableCIE;
    TextView textViewNombreResponsableCIE;
    TextView textViewTipoEmpleadoResponsableCIE;
    TextView textViewTipoResponsableCIE;
    Spinner spinnerEstatusResponsableCIE;

    public EstatusResponsablesInicioViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoResponsableCIE = view.findViewById(R.id.textViewNumeroEmpledoResponsableCIE);
        textViewNombreResponsableCIE = view.findViewById(R.id.textViewNombreResponsableCIE);
        textViewTipoEmpleadoResponsableCIE = view.findViewById(R.id.textViewTipoEmpleadoResponsableCIE);
        textViewTipoResponsableCIE = view.findViewById(R.id.textViewTipoResponsableCIE);
        spinnerEstatusResponsableCIE = view.findViewById(R.id.spinnerEstatusResponsableCIE);
    }

    public void bind(Activity activity, @NotNull DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listStatusResponsableFase, EstatusResponsablesInicioAdapter.OnItemSelectedListener itemSelectedListener) {
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

    public void setEstatusImputado(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listEstatusImputadoFaseList, @NotNull Spinner spinner, EstatusResponsablesInicioAdapter.OnItemSelectedListener itemSelectedListener) {
        ArrayAdapter<EstatusResponsableFase> myAdapter = new EstatusImputadoArrayAdapter(activity, R.layout.cell_spinner_item, listEstatusImputadoFaseList);
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