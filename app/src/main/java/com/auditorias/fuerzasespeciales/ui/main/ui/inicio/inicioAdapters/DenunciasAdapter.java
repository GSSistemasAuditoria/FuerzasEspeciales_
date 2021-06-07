package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.inicioAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.ui.main.ui.inicio.inicioHolders.DenunciasViewHolder;

import java.util.List;

public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasViewHolder> {

    private final Activity activityCA;
    private final List<CasosAbogado> listCasosAbogado;
    private final LayoutInflater inflater;
    private final OnClickListener itemClickListener;
    private final FragmentManager fragmentManager;

    public DenunciasAdapter(Activity activityCA, FragmentManager fragmentManager, List<CasosAbogado> listCasosAbogado, OnClickListener itemClickListener) {
        this.activityCA = activityCA;
        this.fragmentManager = fragmentManager;
        this.listCasosAbogado = listCasosAbogado;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    @NonNull
    @Override
    public DenunciasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_casos_abogado_item, parent, false);
        return new DenunciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DenunciasViewHolder holder, final int position) {
        holder.bind(activityCA, listCasosAbogado.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listCasosAbogado.size();
    }

    public interface OnClickListener {
        void onItemClick(CasosAbogado casosAbogado, int position);
    }
}
