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
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters.DetalleDenunciaFasesAdapter;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

public class DetalleDenunciaFasesViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewImagenFaseCDF;
    TextView textViewNombreFaseCDF;
    TextView textViewEtapaFaseColorCDF;
    TextView textViewPorcentajeFaseCDF;
    TextView textViewFechaRegistroCDF;
    TextView textViewFechaCompromisoCDF;
    TextView textViewFechaInicioFaseCDF;
    TextView textViewFechaCierreFaseCDF;
    CardView cardViewSubfasesCDF;
    CardView cradViewReprogramacionesCDF;
    CardView cardViewDetalleDocumentosCDD;

    public DetalleDenunciaFasesViewHolder(View view) {
        super(view);
        imageViewImagenFaseCDF = view.findViewById(R.id.imageViewImagenFaseCDF);
        textViewNombreFaseCDF = view.findViewById(R.id.textViewNombreFaseCDF);
        textViewEtapaFaseColorCDF = view.findViewById(R.id.textViewEtapaFaseColorCDF);
        textViewPorcentajeFaseCDF = view.findViewById(R.id.textViewPorcentajeFaseCDF);
        textViewFechaRegistroCDF = view.findViewById(R.id.textViewFechaRegistroCDF);
        textViewFechaCompromisoCDF = view.findViewById(R.id.textViewFechaCompromisoCDF);
        textViewFechaInicioFaseCDF = view.findViewById(R.id.textViewFechaInicioFaseCDF);
        textViewFechaCierreFaseCDF = view.findViewById(R.id.textViewFechaCierreFaseCDF);
        cardViewSubfasesCDF = view.findViewById(R.id.cardViewSubfasesCDF);
        cradViewReprogramacionesCDF = view.findViewById(R.id.cradViewReprogramacionesCDF);
        cardViewDetalleDocumentosCDD = view.findViewById(R.id.cardViewDetalleDocumentosCDD);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void bind(Activity activity, DetalleDenunciaFase detalleDenunciaFase, DetalleDenunciaFasesAdapter.OnListener listener) {

        Picasso.get().load(detalleDenunciaFase.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewImagenFaseCDF);

        if (detalleDenunciaFase.getNombreFase() != null) {
            textViewNombreFaseCDF.setText(detalleDenunciaFase.getNombreFase());
        } else {
            textViewNombreFaseCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getEtapaFase() != null) {
            textViewEtapaFaseColorCDF.setText(detalleDenunciaFase.getEtapaFase());
        } else {
            textViewEtapaFaseColorCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getColorEtapaFase() != null) {
            textViewEtapaFaseColorCDF.setBackground(Utils.cambiarColorTextView(detalleDenunciaFase.getColorEtapaFase()));
        } else {
            textViewEtapaFaseColorCDF.setTextColor(activity.getColor(R.color.dark));
            textViewEtapaFaseColorCDF.setText(activity.getString(R.string.text_label_no_aplica));
            textViewEtapaFaseColorCDF.setBackgroundColor(Color.TRANSPARENT);
        }

        if (detalleDenunciaFase.getPorcentajeAvanceGeneral() != null) {
            textViewPorcentajeFaseCDF.setText(Utils.setFormatoNumeroEnteroPorcentaje(detalleDenunciaFase.getPorcentajeAvanceGeneral()));
        } else {
            textViewPorcentajeFaseCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getFechaRegistro() != null) {
            textViewFechaRegistroCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaRegistro().trim()));
        } else {
            textViewFechaRegistroCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getFechaCompromiso() != null) {
            textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaCompromiso().trim()));
        } else {
            textViewFechaCompromisoCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getFechaInicio() != null) {
            textViewFechaInicioFaseCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaInicio().trim()));
        } else {
            textViewFechaInicioFaseCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (detalleDenunciaFase.getFechaCierre() != null) {
            textViewFechaCierreFaseCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaCierre().trim()));
        } else {
            textViewFechaCierreFaseCDF.setText(activity.getString(R.string.text_label_no_aplica));
        }

        if (!detalleDenunciaFase.getSubfases().isEmpty()) {
            cardViewSubfasesCDF.setCardBackgroundColor(activity.getColor(R.color.green_secondary));
            cardViewSubfasesCDF.setActivated(true);
        } else {
            cardViewSubfasesCDF.setCardBackgroundColor(activity.getColor(R.color.Grey500));
            cardViewSubfasesCDF.setActivated(false);
        }

        if (!detalleDenunciaFase.getReprogramaciones().isEmpty()) {
            cradViewReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.green_secondary));
            cradViewReprogramacionesCDF.setActivated(true);
        } else {
            cradViewReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.Grey500));
            cradViewReprogramacionesCDF.setActivated(false);
        }

        cardViewSubfasesCDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDetalleSubfases(detalleDenunciaFase, getAdapterPosition(), detalleDenunciaFase.getSubfases());
            }
        });

        cradViewReprogramacionesCDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDetalleReprogramaciones(detalleDenunciaFase, getAdapterPosition(), detalleDenunciaFase.getReprogramaciones());
            }
        });
    }
}