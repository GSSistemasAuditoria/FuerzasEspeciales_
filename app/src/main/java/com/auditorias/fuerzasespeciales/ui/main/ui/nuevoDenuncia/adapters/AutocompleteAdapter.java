package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.Empleado;

import java.util.List;

public class AutocompleteAdapter extends BaseAdapter {

    private Activity activity;
    private List<Empleado> listEmpleados;

    public AutocompleteAdapter(Activity activity,List<Empleado> listEmpleados){
        this.activity = activity;
        this.listEmpleados = listEmpleados;
    }

    public List<Empleado> getListEmpleados() {
        return listEmpleados;
    }

    @Override
    public int getCount() {
        return listEmpleados.size();
    }

    @Override
    public Object getItem(int position) {
        return listEmpleados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = null;
        try {
            item = new View(activity);
            Empleado empleado = listEmpleados.get(position);
            LayoutInflater inflater = activity.getLayoutInflater();
            item = inflater.inflate(R.layout.item_empleado, parent, false);

            TextView textViewEmpleado = (TextView) item.findViewById(R.id.textViewEmpleado);
            textViewEmpleado.setText(empleado.getVista());
        }catch (RuntimeException e){
            Log.e("AGU","",e);
            Toast.makeText(activity,"Error en la aplicación",Toast.LENGTH_LONG).show();
        }catch (Error e){
            Log.e("AGU","",e);
            Toast.makeText(activity,"Error en la aplicación",Toast.LENGTH_LONG).show();
        }
        return item;
    }
}
