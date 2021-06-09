package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.holders.EstatusResponsablesCerrarViewHolder;

import java.util.List;

public class EstatusResponsablesCerrarAdapter extends RecyclerView.Adapter<EstatusResponsablesCerrarViewHolder> {

    private final Activity activity;
    private final List<DatosDenunciaResponsable> listResposablesCerrar;
    private final LayoutInflater inflater;
    private final OnItemSelectedListener itemSelectedListenerCerrar;
    private final List<EstatusResponsableFase> listEstatusResposablesCerrar;

    public EstatusResponsablesCerrarAdapter(Activity activity, List<DatosDenunciaResponsable> listResposablesCerrar,/* FragmentManager fm,*/ List<EstatusResponsableFase> listEstatusResposablesCerrar, OnItemSelectedListener itemSelectedListenerCerrar) {
        this.activity = activity;
        this.listResposablesCerrar = listResposablesCerrar;
        this.listEstatusResposablesCerrar = listEstatusResposablesCerrar;
        this.itemSelectedListenerCerrar = itemSelectedListenerCerrar;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public EstatusResponsablesCerrarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_imputados_estatus_item, parent, false);
        return new EstatusResponsablesCerrarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EstatusResponsablesCerrarViewHolder holder, final int position) {
        holder.bind(activity, listResposablesCerrar.get(position), listEstatusResposablesCerrar, itemSelectedListenerCerrar);
    }

    @Override
    public int getItemCount() {
        return listResposablesCerrar.size();
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int IdCasoResponsable, int idStatusResponsable);
    }
}
