package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.catalogos.estatusSentencia.DataEstatusSentencia;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EstatusSentenciaArrayAdapter  extends ArrayAdapter<DataEstatusSentencia> {

    private final List<DataEstatusSentencia> listEstatusImputados;
    private final Context context;

    public EstatusSentenciaArrayAdapter(Context context, int resourceId, List<DataEstatusSentencia> listEstatusImputados) {
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
        View row = inflater.inflate(R.layout.cell_spinner_item, parent, false);
        TextView label = row.findViewById(R.id.textViewTextoCSI);
        label.setText(listEstatusImputados.get(position).getDescripcion());
        return row;
    }

}