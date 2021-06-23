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
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusResponsablesInicioAdapter;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EstatusResponsablesInicioViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoResponsableCIE;
    TextView textViewNombreResponsableCIE;
    TextView textViewTipoEmpleadoResponsableCIE;
    Spinner spinnerEstatusResponsableCIE;

    public EstatusResponsablesInicioViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoResponsableCIE = view.findViewById(R.id.textViewNumeroEmpledoResponsableCIE);
        textViewNombreResponsableCIE = view.findViewById(R.id.textViewNombreResponsableCIE);
        textViewTipoEmpleadoResponsableCIE = view.findViewById(R.id.textViewTipoEmpleadoResponsableCIE);
        spinnerEstatusResponsableCIE = view.findViewById(R.id.spinnerEstatusResponsableCIE);
    }

    public void bind(Activity activity, int idFase,  @NotNull DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listStatusResponsableFase, EstatusResponsablesInicioAdapter.OnItemSelectedListener itemSelectedListener) {
        if (datosDenunciaResponsable.getIdEmpleado() != null) {
            textViewNumeroEmpledoResponsableCIE.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
        } else {
            textViewNumeroEmpledoResponsableCIE.setVisibility(View.GONE);
        }

        if (datosDenunciaResponsable.getNombre() != null) {
            textViewNombreResponsableCIE.setText(datosDenunciaResponsable.getNombre());
        } else {
            textViewNombreResponsableCIE.setVisibility(View.GONE);
        }

        if (datosDenunciaResponsable.getTipoEmpleado() != null) {
            textViewTipoEmpleadoResponsableCIE.setText(datosDenunciaResponsable.getTipoEmpleado());
        } else {
            textViewTipoEmpleadoResponsableCIE.setVisibility(View.GONE);
        }

        List<EstatusResponsableFase> listEstatusDeResposable = new ArrayList<>();
        if (idFase == 2 ){
            listEstatusDeResposable.add(new EstatusResponsableFase(Constantes.selecionar, "", 0, 0));
            listEstatusDeResposable.addAll(listStatusResponsableFase);
        }else {
            for (int x = 0; x < listStatusResponsableFase.size(); x++) {
                if (!datosDenunciaResponsable.getIdStatusResponsable().equals(listStatusResponsableFase.get(x).getId())) {
                    listEstatusDeResposable.add(listStatusResponsableFase.get(x));
                }
            }
        }
        setEstatusImputado(activity, datosDenunciaResponsable, listEstatusDeResposable, spinnerEstatusResponsableCIE, itemSelectedListener);
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