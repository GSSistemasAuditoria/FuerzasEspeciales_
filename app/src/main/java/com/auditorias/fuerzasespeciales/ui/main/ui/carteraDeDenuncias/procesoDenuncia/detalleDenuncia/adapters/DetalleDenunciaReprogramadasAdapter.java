package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders.DetalleDenunciaReprogramadasViewHolder;

import java.util.List;

public class DetalleDenunciaReprogramadasAdapter extends RecyclerView.Adapter<DetalleDenunciaReprogramadasViewHolder> {

    private final Activity activity;
    private final List<DetalleDenunciaFaseReprogramaciones> detalleDenunciaReprogramaciones;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaReprogramadasAdapter(Activity activity, List<DetalleDenunciaFaseReprogramaciones> detalleDenunciaReprogramaciones, OnListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.detalleDenunciaReprogramaciones = detalleDenunciaReprogramaciones;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaReprogramadasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_reprogramaciones_item, parent, false);
        return new DetalleDenunciaReprogramadasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaReprogramadasViewHolder holder, final int position) {
        holder.bind(activity, detalleDenunciaReprogramaciones.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return detalleDenunciaReprogramaciones.size();
    }

    public interface OnListener {
        void onItemClick(DetalleDenunciaFaseReprogramaciones detalleDenunciaFaseReprogramaciones, int position);
    }
}