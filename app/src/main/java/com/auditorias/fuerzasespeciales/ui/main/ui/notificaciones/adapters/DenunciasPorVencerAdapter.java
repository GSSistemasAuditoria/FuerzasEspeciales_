package com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.notificaciones.DataNotificacion;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.holders.DenunciasPorVencerHolder;

import java.util.List;

public class DenunciasPorVencerAdapter extends RecyclerView.Adapter<DenunciasPorVencerHolder> {

    private final Activity activityCA;
    private final List<DataNotificacion> listNotificaciones;
    private final LayoutInflater inflater;
    private final OnClickListener itemClickListener;
    private final FragmentManager fragmentManager;

    public DenunciasPorVencerAdapter(Activity activityCA, FragmentManager fragmentManager, List<DataNotificacion> listNotificaciones, OnClickListener itemClickListener) {
        this.activityCA = activityCA;
        this.fragmentManager = fragmentManager;
        this.listNotificaciones = listNotificaciones;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    @NonNull
    @Override
    public DenunciasPorVencerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_notificaciones_item, parent, false);
        return new DenunciasPorVencerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DenunciasPorVencerHolder holder, final int position) {
        holder.bind(activityCA, listNotificaciones.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listNotificaciones.size();
    }

    public interface OnClickListener {
        void onItemClick(DataNotificacion notificacion, int position);
    }
}
