package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaResponsables;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders.DetalleEmpleadosResponsablesViewHolder;

import java.util.List;

public class DetalleEmpleadosResponsablesAdapter extends RecyclerView.Adapter<DetalleEmpleadosResponsablesViewHolder> {

    private final Activity activity;
    private final List<DetalleDenunciaResponsables> list;
    private final LayoutInflater inflater;

    public DetalleEmpleadosResponsablesAdapter(Activity activity, List<DetalleDenunciaResponsables> list) {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleEmpleadosResponsablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_responsables_item, parent, false);
        return new DetalleEmpleadosResponsablesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleEmpleadosResponsablesViewHolder holder, final int position) {
        holder.bind(activity, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
