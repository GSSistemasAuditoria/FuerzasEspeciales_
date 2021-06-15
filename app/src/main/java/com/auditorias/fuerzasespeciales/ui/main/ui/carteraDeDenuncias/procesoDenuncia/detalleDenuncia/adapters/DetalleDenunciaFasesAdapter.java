package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDocumento;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders.DetalleDenunciaFasesViewHolder;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetalleDenunciaFasesAdapter extends RecyclerView.Adapter<DetalleDenunciaFasesViewHolder> {

    private final Activity activity;
    private final List<DetalleDenunciaFase> listDetalleFases;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaFasesAdapter(Activity activity, List<DetalleDenunciaFase> listDetalleFases, OnListener listener) {
        this.activity = activity;
        this.listDetalleFases = listDetalleFases;
        this.listener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaFasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_fases_item, parent, false);
        return new DetalleDenunciaFasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaFasesViewHolder holder, final int position) {
        holder.bind(activity, listDetalleFases.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listDetalleFases.size();
    }

    public interface OnListener {
        //void onItemClick(DetalleDenunciaFase detalleDenunciaFase, int position, String imagenString, String tipoArchivo);

        //void onClickDetalleDocumentos(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDocumento> documentos);

        void onClickDetalleSubfases(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDenunciaSubFase> listSubfases);

        void onClickDetalleReprogramaciones(DetalleDenunciaFase detalleDenunciaFase, int positio, List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones);
    }

}