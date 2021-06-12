package com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.models.notificaciones.DataNotificacion;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.inicioAdapters.DenunciasAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.inicioHolders.DenunciasViewHolder;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.holders.NotificacionesHolder;

import java.util.List;

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesHolder> {

    private final Activity activityCA;
    private final List<DataNotificacion> listNotificaciones;
    private final LayoutInflater inflater;
    private final OnClickListener itemClickListener;
    private final FragmentManager fragmentManager;

    public NotificacionesAdapter(Activity activityCA, FragmentManager fragmentManager, List<DataNotificacion> listNotificaciones, OnClickListener itemClickListener) {
        this.activityCA = activityCA;
        this.fragmentManager = fragmentManager;
        this.listNotificaciones = listNotificaciones;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    @NonNull
    @Override
    public NotificacionesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_notificaciones_item, parent, false);
        return new NotificacionesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificacionesHolder holder, final int position) {
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
