package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.EstatusResponsableFase;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenunciaResponsable;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.iniciarFase.holders.ImputadosFaseViewHolder;

import java.util.List;

public class ImputadosFaseAdapter extends RecyclerView.Adapter<ImputadosFaseViewHolder> {

    private final Activity activity;
    private final List<DatosDenunciaResponsable> listImputados;
    private final LayoutInflater inflater;
    private final OnItemSelectedListener itemSelectedListener;
    private FragmentManager fm;
    private List<EstatusResponsableFase> listEstatusDeResposablesSelecionar;

    public ImputadosFaseAdapter(Activity activity, List<DatosDenunciaResponsable> listImputados, FragmentManager fm, List<EstatusResponsableFase> listEstatusDeResposablesSelecionar, OnItemSelectedListener itemSelectedListener) {
        this.activity = activity;
        this.fm = fm;
        this.listImputados = listImputados;
        this.listEstatusDeResposablesSelecionar = listEstatusDeResposablesSelecionar;
        this.itemSelectedListener = itemSelectedListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ImputadosFaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_imputados_estatus_item, parent, false);
        return new ImputadosFaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImputadosFaseViewHolder holder, final int position) {
        holder.bind(activity, listImputados.get(position), listEstatusDeResposablesSelecionar, itemSelectedListener);
    }

    @Override
    public int getItemCount() {
        return listImputados.size();
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(DatosDenunciaResponsable datosDenunciaResponsable, int idCasoFase, int IdCasoResponsable, int idStatusResponsable);
    }


}
