package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.holders.EstatusResponsablesInicioViewHolder;

import java.util.List;

public class EstatusResponsablesInicioAdapter extends RecyclerView.Adapter<EstatusResponsablesInicioViewHolder> {

    private final Activity activity;
    private final List<DatosDenunciaResponsable> listDatosDenunciaResponsables;
    private final LayoutInflater inflater;
    private final OnItemSelectedListener itemSelectedListener;
    private final List<EstatusResponsableFase> listEstatusResponsablesFase;

    public EstatusResponsablesInicioAdapter(Activity activity, List<DatosDenunciaResponsable> listDatosDenunciaResponsables, /*FragmentManager fm,*/ List<EstatusResponsableFase> listEstatusResponsablesFase, OnItemSelectedListener itemSelectedListener) {
        this.activity = activity;
        this.listDatosDenunciaResponsables = listDatosDenunciaResponsables;
        this.listEstatusResponsablesFase = listEstatusResponsablesFase;
        this.itemSelectedListener = itemSelectedListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public EstatusResponsablesInicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_imputados_estatus_item, parent, false);
        return new EstatusResponsablesInicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EstatusResponsablesInicioViewHolder holder, final int position) {
        holder.bind(activity, listDatosDenunciaResponsables.get(position), listEstatusResponsablesFase, itemSelectedListener);
    }

    @Override
    public int getItemCount() {
        return listDatosDenunciaResponsables.size();
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int IdCasoResponsable, int idStatusResponsable);
    }
}
