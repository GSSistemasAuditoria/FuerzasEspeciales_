package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaSubfasesAdapter;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

public class DetalleDenunciaSubfasesViewHolder extends RecyclerView.ViewHolder {
    ImageView imageViewIconoSubfaseCDSF;
    TextView textViewNombreSubfaseCDSF;
    TextView textViewEstatusSubfaseColorCDSF;
    TextView textViewPorcentajeSubfaseCDSF;
    TextView textViewFechaInicioSubfaseCDSF;
    TextView textViewFechaCierreSubfaseCDSF;
    TextView textViewFechaRegistroSubfaseCDSF;
    TextView textViewFechaCompromisoSubfaseCDSF;
    CardView cardViewSubfasesReprogramacionesCDF;

    public DetalleDenunciaSubfasesViewHolder(View view) {
        super(view);
        imageViewIconoSubfaseCDSF = view.findViewById(R.id.imageViewIconoSubfaseCDSF);
        textViewNombreSubfaseCDSF = view.findViewById(R.id.textViewNombreSubfaseCDSF);
        textViewEstatusSubfaseColorCDSF = view.findViewById(R.id.textViewEstatusSubfaseColorCDSF);
        textViewPorcentajeSubfaseCDSF = view.findViewById(R.id.textViewPorcentajeSubfaseCDSF);
        textViewFechaInicioSubfaseCDSF = view.findViewById(R.id.textViewFechaInicioSubfaseCDSF);
        textViewFechaCierreSubfaseCDSF = view.findViewById(R.id.textViewFechaCierreSubfaseCDSF);
        textViewFechaRegistroSubfaseCDSF = view.findViewById(R.id.textViewFechaRegistroSubfaseCDSF);
        textViewFechaCompromisoSubfaseCDSF = view.findViewById(R.id.textViewFechaCompromisoSubfaseCDSF);
        cardViewSubfasesReprogramacionesCDF = view.findViewById(R.id.cardViewSubfasesReprogramacionesCDF);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void bind(Activity activity, DetalleDenunciaSubFase detalleDenunciaSubFase, DetalleDenunciaSubfasesAdapter.OnListener listener) {
        Picasso.get().load(detalleDenunciaSubFase.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewIconoSubfaseCDSF);

        if (detalleDenunciaSubFase.getNombreFase() != null) {
            textViewNombreSubfaseCDSF.setText(detalleDenunciaSubFase.getNombreFase());
        } else {
            textViewNombreSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getEtapaFase() != null) {
            textViewEstatusSubfaseColorCDSF.setText(detalleDenunciaSubFase.getEtapaFase());
        } else {
            textViewEstatusSubfaseColorCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getColorEtapaFase() != null) {
            textViewEstatusSubfaseColorCDSF.setBackground(Utils.cambiarColorTextView(detalleDenunciaSubFase.getColorEtapaFase()));
        } else {
            textViewEstatusSubfaseColorCDSF.setTextColor(activity.getColor(R.color.dark));
            textViewEstatusSubfaseColorCDSF.setText(activity.getString(R.string.text_label_guion));
            textViewEstatusSubfaseColorCDSF.setBackgroundColor(Color.TRANSPARENT);
        }

        if (detalleDenunciaSubFase.getPorcentajeAvanceGeneral() != null) {
            textViewPorcentajeSubfaseCDSF.setText(Utils.setFormatoNumeroEnteroPorcentaje(detalleDenunciaSubFase.getPorcentajeAvanceGeneral()));
        } else {
            textViewPorcentajeSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getFechaRegistro() != null) {
            textViewFechaRegistroSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaRegistro()));
        } else {
            textViewFechaRegistroSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getFechaCompromiso() != null) {
            textViewFechaCompromisoSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaCompromiso()));
        } else {
            textViewFechaCompromisoSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getFechaInicio() != null) {
            textViewFechaInicioSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaInicio()));
        } else {
            textViewFechaInicioSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (detalleDenunciaSubFase.getFechaCierre() != null) {
            textViewFechaCierreSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaCierre()));
        } else {
            textViewFechaCierreSubfaseCDSF.setText(activity.getString(R.string.text_label_guion));
        }

        if (!detalleDenunciaSubFase.getListReprogramaciones().isEmpty()) {
            cardViewSubfasesReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.green_secondary));
            cardViewSubfasesReprogramacionesCDF.setActivated(true);
        } else {
            cardViewSubfasesReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.Grey500));
            cardViewSubfasesReprogramacionesCDF.setActivated(false);
        }

        cardViewSubfasesReprogramacionesCDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickCardViewReprogramaciones(detalleDenunciaSubFase, getAdapterPosition(), detalleDenunciaSubFase.getListReprogramaciones());
            }
        });

    }

}