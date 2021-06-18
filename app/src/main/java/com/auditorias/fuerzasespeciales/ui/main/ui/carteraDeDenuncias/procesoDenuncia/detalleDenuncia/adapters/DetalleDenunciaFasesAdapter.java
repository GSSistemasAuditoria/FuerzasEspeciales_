package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders.DetalleDenunciaFasesViewHolder;

import java.util.List;

public class DetalleDenunciaFasesAdapter extends RecyclerView.Adapter<DetalleDenunciaFasesViewHolder> {

    private final Activity activity;
    private final List<DetalleDenunciaFase> listDetalleFases;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaFasesAdapter(Activity activity, List<DetalleDenunciaFase> listDetalleFases, OnListener listener) {
        this.activity = activity;
        this.listDetalleFases = listDetalleFases;
        this.listener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaFasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_fases_item, parent, false);
        return new DetalleDenunciaFasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaFasesViewHolder holder, final int position) {
        holder.bind(activity, listDetalleFases.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listDetalleFases.size();
    }

    public interface OnListener {
        void onClickDetalleSubfases(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDenunciaSubFase> listSubfases);

        void onClickDetalleReprogramaciones(DetalleDenunciaFase detalleDenunciaFase, int positio, List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones);
    }
}