package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.faseAdapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.FaseActivaDatos;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.faseHolders.FasesViewHolder;

import java.util.List;

import static com.auditorias.fuerzasespeciales.R.layout;

public class FasesAdapter extends RecyclerView.Adapter<FasesViewHolder> {

    private final Activity activity;
    private final List<FaseActivaDatos> listDataFaseActiva;
    private final LayoutInflater inflater;
    private final FragmentManager fm;
    private final OnItemClickListener itemClickListener;
    private final DatosDenuncia datosDenuncia;

    public FasesAdapter(Activity activity, FragmentManager fm, List<FaseActivaDatos> listDataFaseActiva, DatosDenuncia datosDenuncia, OnItemClickListener listener) {
        this.activity = activity;
        this.fm = fm;
        this.listDataFaseActiva = listDataFaseActiva;
        this.datosDenuncia = datosDenuncia;
        this.itemClickListener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public FasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout.cell_fases_item, parent, false);
        return new FasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FasesViewHolder holder, final int position) {
        holder.bind(activity, listDataFaseActiva.get(position), datosDenuncia, itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listDataFaseActiva.size();
    }

    public interface OnItemClickListener {

        void onIniciarFase(FaseActivaDatos faseActivaDatos, int position);

        void onReprogramarFase(FaseActivaDatos faseActivaDatos, int position);

        void onCerrarFase(FaseActivaDatos faseActivaDatos, int position);

    }
}



