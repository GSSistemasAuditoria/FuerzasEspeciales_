package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;

import java.util.List;

public class DetalleDenunciaReprogramadasAdapter extends RecyclerView.Adapter<DetalleDenunciaReprogramadasAdapter.DetalleDenunciaReprogramadasViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDenunciaFaseReprogramaciones> detalleDenunciaReprogramaciones;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaReprogramadasAdapter(Activity activity, List<DetalleDenunciaFaseReprogramaciones> detalleDenunciaReprogramaciones, OnListener listener/*, FragmentManager fm*/) {
        this.activity = activity;
        this.listener = listener;
        this.detalleDenunciaReprogramaciones = detalleDenunciaReprogramaciones;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaReprogramadasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_reprogramaciones_item, parent, false);
        return new DetalleDenunciaReprogramadasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaReprogramadasViewHolder holder, final int position) {
        holder.bind(activity, detalleDenunciaReprogramaciones.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return detalleDenunciaReprogramaciones.size();
    }

    public interface OnListener {
        void onItemClick(DetalleDenunciaFaseReprogramaciones detalleDenunciaFaseReprogramaciones, int position);
    }

    static class DetalleDenunciaReprogramadasViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreEmpleadoTextCDR;
        TextView textViewFechaAnteriorCDR;
        TextView textViewFechaNuevaCDR;
        TextView textViewFechaSolicitudCDR;
        TextView textViewFechaRespuestaCDR;
        TextView textViewMotivoSolicitudCDR;
        TextView textViewEstatusSolicitiudCDR;
        TextView textViewFaseCDR;
        TextView textViewMotivoRechazoTextCDR;
        ImageView imageViewdocumentpReprogramacionesCDR;
        CardView cardViewDetalleReporgramadasCDR;

        DetalleDenunciaReprogramadasViewHolder(View view) {
            super(view);
            textViewFechaAnteriorCDR = view.findViewById(R.id.textViewFechaAnteriorCDR);
            textViewFechaNuevaCDR = view.findViewById(R.id.textViewFechaNuevaCDR);
            textViewFechaSolicitudCDR = view.findViewById(R.id.textViewFechaSolicitudCDR);
            textViewFechaRespuestaCDR = view.findViewById(R.id.textViewFechaRespuestaCDR);
            textViewMotivoSolicitudCDR = view.findViewById(R.id.textViewMotivoSolicitudCDR);
            textViewEstatusSolicitiudCDR = view.findViewById(R.id.textViewEstatusSolicitiudCDR);
            textViewFaseCDR = view.findViewById(R.id.textViewFaseCDR);
            textViewMotivoRechazoTextCDR = view.findViewById(R.id.textViewMotivoRechazoTextCDR);
            imageViewdocumentpReprogramacionesCDR = view.findViewById(R.id.imageViewdocumentpReprogramacionesCDR);
            cardViewDetalleReporgramadasCDR = view.findViewById(R.id.cardViewDetalleReporgramadasCDR);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(Activity activity, DetalleDenunciaFaseReprogramaciones detalleDenunciaFaseReprogramaciones, OnListener listener) {
            if (detalleDenunciaFaseReprogramaciones.getTipoArchivo() != null) {
                if (detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("png") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("jpg") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("jpeg")
                || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".png") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".jpg") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".jpeg")) {
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_image));
                } else if (detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("pdf") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".pdf")) {
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_pdf_box));
                } else if (detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("doc") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals("docx")
                || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".doc") || detalleDenunciaFaseReprogramaciones.getTipoArchivo().equals(".docx")) {
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_word));
                }
            } else {
                imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.folder_image));
            }

            if (detalleDenunciaFaseReprogramaciones.getFechaCompromisoAnterior() != null) {
                textViewFechaAnteriorCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaCompromisoAnterior());
            } else {
                textViewFechaAnteriorCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getFechaCompromisoNueva() != null) {
                textViewFechaNuevaCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaCompromisoNueva());
            } else {
                textViewFechaNuevaCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getFechaSolicitud() != null) {
                textViewFechaSolicitudCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaSolicitud());
            } else {
                textViewFechaSolicitudCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getFechaRespuesta() != null) {
                textViewFechaRespuestaCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaRespuesta());
            } else {
                textViewFechaRespuestaCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getMotivoSolicitud() != null) {
                textViewMotivoSolicitudCDR.setText(detalleDenunciaFaseReprogramaciones.getMotivoSolicitud());
            } else {
                textViewMotivoSolicitudCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getStatusAutorizacion() != null) {
                textViewEstatusSolicitiudCDR.setText(detalleDenunciaFaseReprogramaciones.getStatusAutorizacion());
            } else {
                textViewEstatusSolicitiudCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getFase() != null) {
                textViewFaseCDR.setText(detalleDenunciaFaseReprogramaciones.getFase());
            } else {
                textViewFaseCDR.setText("");
            }

            if (detalleDenunciaFaseReprogramaciones.getMotivoRechazo() != null) {
                textViewMotivoRechazoTextCDR.setText(detalleDenunciaFaseReprogramaciones.getMotivoRechazo());
            } else {
                textViewMotivoRechazoTextCDR.setText("");
            }
            if (detalleDenunciaFaseReprogramaciones.getIdDocAdjunto().equals(0)){
                cardViewDetalleReporgramadasCDR.setActivated(false);
            }else{
                cardViewDetalleReporgramadasCDR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(detalleDenunciaFaseReprogramaciones, getAdapterPosition());
                    }
                });
            }

        }
    }
}