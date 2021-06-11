package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.SubfaseActiva;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.holders.SubFaseViewHolder;

import java.util.List;

public class SubFaseAdapter extends RecyclerView.Adapter<SubFaseViewHolder> {

    private final Activity activity;
    private final List<SubfaseActiva> listDataFaseActiva;
    private final LayoutInflater inflater;
    private final OnItemClickListener itemClickListener;
    private DatosDenuncia datosDenuncia;

    public SubFaseAdapter(Activity activity, List<SubfaseActiva> listDataFaseActiva, DatosDenuncia datosDenuncia, OnItemClickListener listener) {
        this.activity = activity;
        this.listDataFaseActiva = listDataFaseActiva;
        this.datosDenuncia = datosDenuncia;
        this.itemClickListener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public SubFaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_sub_fases_item, parent, false);
        return new SubFaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubFaseViewHolder holder, final int position) {
        holder.bind(activity, listDataFaseActiva.get(position), datosDenuncia, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listDataFaseActiva.size();
    }

    public interface OnItemClickListener {

        void onIniciarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso);

        void onReprogramarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso);

        void onCerrarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso);

    }
}