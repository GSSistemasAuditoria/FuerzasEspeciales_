package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.holders;

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
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters.EstatusResponsablesCerrarAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters.EstatusImputadoArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class EstatusResponsablesCerrarViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoResponsableCIE;
    TextView textViewNombreResponsableCIE;
    TextView textViewTipoEmpleadoResponsableCIE;
    TextView textViewTipoResponsableCIE;
    Spinner spinnerEstatusResponsableCIE;

    public EstatusResponsablesCerrarViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoResponsableCIE = view.findViewById(R.id.textViewNumeroEmpledoResponsableCIE);
        textViewNombreResponsableCIE = view.findViewById(R.id.textViewNombreResponsableCIE);
        textViewTipoEmpleadoResponsableCIE = view.findViewById(R.id.textViewTipoEmpleadoResponsableCIE);
        textViewTipoResponsableCIE = view.findViewById(R.id.textViewTipoResponsableCIE);
        spinnerEstatusResponsableCIE = view.findViewById(R.id.spinnerEstatusResponsableCIE);
    }

    public void bind(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> listEstatusResponsableCerrar, EstatusResponsablesCerrarAdapter.OnItemSelectedListener itemSelectedListener) {
        textViewNumeroEmpledoResponsableCIE.setText(String.valueOf(datosDenunciaResponsable.getIdEmpleado()));
        if (datosDenunciaResponsable.getIdEmpleado() == null) {
            textViewNumeroEmpledoResponsableCIE.setVisibility(View.GONE);
        } else {
            textViewNumeroEmpledoResponsableCIE.setVisibility(View.VISIBLE);
        }

        textViewNombreResponsableCIE.setText(datosDenunciaResponsable.getNombre());
        textViewTipoEmpleadoResponsableCIE.setText(datosDenunciaResponsable.getTipoEmpleado());
        textViewTipoResponsableCIE.setText(datosDenunciaResponsable.getTipoResponsable());
        List<EstatusResponsableFase> listEstatusDeResposable = new ArrayList<>();
        listEstatusDeResposable.add(new EstatusResponsableFase(datosDenunciaResponsable.getStatusResponsable(), "", datosDenunciaResponsable.getIdStatusResponsable(), 0));
        for (int x = 0; x < listEstatusResponsableCerrar.size(); x++) {
            if (!datosDenunciaResponsable.getIdStatusResponsable().equals(listEstatusResponsableCerrar.get(x).getId())) {
                listEstatusDeResposable.add(listEstatusResponsableCerrar.get(x));
            }
        }

        getStatusResponsableFaseList(activity, datosDenunciaResponsable, listEstatusDeResposable, spinnerEstatusResponsableCIE, itemSelectedListener);
    }

    public void getStatusResponsableFaseList(Activity activity, DatosDenunciaResponsable datosDenunciaResponsable, List<EstatusResponsableFase> statusResponsableFaseList, Spinner spinner, EstatusResponsablesCerrarAdapter.OnItemSelectedListener itemSelectedListener) {
        ArrayAdapter<EstatusResponsableFase> myAdapter = new EstatusImputadoArrayAdapter(activity, R.layout.cell_spinner_item, statusResponsableFaseList);
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