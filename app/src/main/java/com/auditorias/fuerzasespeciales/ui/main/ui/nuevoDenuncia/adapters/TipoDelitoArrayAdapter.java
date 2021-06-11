package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.tipoFraude.TipoFraude;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TipoDelitoArrayAdapter extends ArrayAdapter<TipoFraude> {

    private final List<TipoFraude> list;
    private final Context context;

    public TipoDelitoArrayAdapter(Context context, int resourceId, List<TipoFraude> list) {
        super(context, resourceId, list);
        this.list = list;
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
        label.setText(list.get(position).getDescripcion());
        return row;
    }

}