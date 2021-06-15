package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaReprogramadasAdapter;

public class DetalleDenunciaReprogramadasViewHolder extends RecyclerView.ViewHolder {

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

    public DetalleDenunciaReprogramadasViewHolder(View view) {
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
    public void bind(Activity activity, DetalleDenunciaFaseReprogramaciones detalleDenunciaFaseReprogramaciones, DetalleDenunciaReprogramadasAdapter.OnListener listener) {
        if (detalleDenunciaFaseReprogramaciones.getTipoArchivo() != null) {
            switch (detalleDenunciaFaseReprogramaciones.getTipoArchivo()) {
                case "png":
                case "jpg":
                case "jpeg":
                case ".png":
                case ".jpg":
                case ".jpeg":
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_image));
                    break;
                case "pdf":
                case ".pdf":
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_pdf_box));
                    break;
                case "doc":
                case "docx":
                case ".doc":
                case ".docx":
                    imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.ic_word));
                    break;
            }
        } else {
            imageViewdocumentpReprogramacionesCDR.setImageDrawable(activity.getDrawable(R.drawable.folder_image));
        }

        if (detalleDenunciaFaseReprogramaciones.getFechaCompromisoAnterior() != null) {
            textViewFechaAnteriorCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaCompromisoAnterior());
        } else {
            textViewFechaAnteriorCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getFechaCompromisoNueva() != null) {
            textViewFechaNuevaCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaCompromisoNueva());
        } else {
            textViewFechaNuevaCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getFechaSolicitud() != null) {
            textViewFechaSolicitudCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaSolicitud());
        } else {
            textViewFechaSolicitudCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getFechaRespuesta() != null) {
            textViewFechaRespuestaCDR.setText(detalleDenunciaFaseReprogramaciones.getFechaRespuesta());
        } else {
            textViewFechaRespuestaCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getMotivoSolicitud() != null) {
            textViewMotivoSolicitudCDR.setText(detalleDenunciaFaseReprogramaciones.getMotivoSolicitud());
        } else {
            textViewMotivoSolicitudCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getStatusAutorizacion() != null) {
            textViewEstatusSolicitiudCDR.setText(detalleDenunciaFaseReprogramaciones.getStatusAutorizacion());
        } else {
            textViewEstatusSolicitiudCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getFase() != null) {
            textViewFaseCDR.setText(detalleDenunciaFaseReprogramaciones.getFase());
        } else {
            textViewFaseCDR.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFaseReprogramaciones.getMotivoRechazo() != null) {
            textViewMotivoRechazoTextCDR.setText(detalleDenunciaFaseReprogramaciones.getMotivoRechazo());
        } else {
            textViewMotivoRechazoTextCDR.setText(activity.getString(R.string.text_label_no_aplica));
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