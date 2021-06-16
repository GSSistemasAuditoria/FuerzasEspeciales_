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
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.holders.ReprogramacionesHolder;

import java.util.List;

public class ReprogramacionesAdapter extends RecyclerView.Adapter<ReprogramacionesHolder> {

    private final Activity activityCA;
    private final List<DataNotificacion> listNotificaciones;
    private final LayoutInflater inflater;
    private final OnClickListener itemClickListener;
    private final FragmentManager fragmentManager;

    public ReprogramacionesAdapter(Activity activityCA, FragmentManager fragmentManager, List<DataNotificacion> listNotificaciones, OnClickListener itemClickListener) {
        this.activityCA = activityCA;
        this.fragmentManager = fragmentManager;
        this.listNotificaciones = listNotificaciones;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    @NonNull
    @Override
    public ReprogramacionesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_notificaciones_item, parent, false);
        return new ReprogramacionesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReprogramacionesHolder holder, final int position) {
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
