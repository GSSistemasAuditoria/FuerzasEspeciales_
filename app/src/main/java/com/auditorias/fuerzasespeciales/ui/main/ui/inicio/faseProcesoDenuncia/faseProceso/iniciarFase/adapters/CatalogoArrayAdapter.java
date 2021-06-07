package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.faseProceso.iniciarFase.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.StatusResponsableFaseModel;

import java.util.List;

public class CatalogoArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<StatusResponsableFaseModel> listItems;
    private final int idResource;
    private final LayoutInflater inflater;

    public CatalogoArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, 0, objects);
        this.context = context;
        this.idResource = resource;
        this.listItems = objects;
        this.inflater = LayoutInflater.from(context);
    }

    public List<StatusResponsableFaseModel> getListItems() {
        return listItems;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view = null;
        try {
            view = inflater.inflate(idResource, parent, false);
            TextView textViewItem = view.findViewById(R.id.textViewItem);
            StatusResponsableFaseModel catalogoItem = listItems.get(position);
            textViewItem.setText(catalogoItem.getDescripcion());
        } catch (RuntimeException e) {
            Log.e("AGU", "", e);
            Toast.makeText(context, "Error en la aplicación", Toast.LENGTH_LONG).show();
        } catch (Error e) {
            Log.e("AGU", "", e);
            Toast.makeText(context, "Error en la aplicación", Toast.LENGTH_LONG).show();
        }
        return view;
    }
}
