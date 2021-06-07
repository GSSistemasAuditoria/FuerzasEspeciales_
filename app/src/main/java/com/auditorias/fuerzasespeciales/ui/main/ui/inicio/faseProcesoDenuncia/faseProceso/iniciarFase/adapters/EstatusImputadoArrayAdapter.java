package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EstatusImputadoArrayAdapter extends ArrayAdapter<EstatusResponsableFase> {

    private final List<EstatusResponsableFase> listEstatusImputados;
    private final Context context;

    public EstatusImputadoArrayAdapter(Context context, int resourceId, List<EstatusResponsableFase> listEstatusImputados) {
        super(context, resourceId, listEstatusImputados);
        this.listEstatusImputados = listEstatusImputados;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    public View getCustomView(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.cell_estatus_responsable_spinner_item, parent, false);
        TextView label = row.findViewById(R.id.textViewEstatusCERS);
        label.setText(listEstatusImputados.get(position).getDescripcion());
        return row;
    }

}