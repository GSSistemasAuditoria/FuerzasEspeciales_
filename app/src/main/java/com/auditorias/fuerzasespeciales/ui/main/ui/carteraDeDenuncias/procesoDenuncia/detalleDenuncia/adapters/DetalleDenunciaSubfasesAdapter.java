package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders.DetalleDenunciaSubfasesViewHolder;

import java.util.List;

public class DetalleDenunciaSubfasesAdapter extends RecyclerView.Adapter<DetalleDenunciaSubfasesViewHolder> {

    private final Activity activity;
    private final List<DetalleDenunciaSubFase> listDetalleSubFases;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaSubfasesAdapter(Activity activity, List<DetalleDenunciaSubFase> listDetalleSubFasess, OnListener listener) {
        this.activity = activity;
        this.listDetalleSubFases = listDetalleSubFasess;
        this.listener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaSubfasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_subfases_item, parent, false);
        return new DetalleDenunciaSubfasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaSubfasesViewHolder holder, final int position) {
        holder.bind(activity, listDetalleSubFases.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listDetalleSubFases.size();
    }

    public interface OnListener {
        void onClickCardViewReprogramaciones(DetalleDenunciaSubFase detalleDenunciaSubFase, int posicion, List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones);
    }
}