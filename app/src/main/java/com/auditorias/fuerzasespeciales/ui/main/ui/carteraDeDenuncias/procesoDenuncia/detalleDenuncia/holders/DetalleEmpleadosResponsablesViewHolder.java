package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.holders;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaResponsables;

public class DetalleEmpleadosResponsablesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNombreEmpleadoTextCDR;
    TextView textViewNombreEmpleadoCDR;
    TextView textViewNumeroEmpleadoTextCDR;
    TextView textViewNumeroEmpleadoCDR;
    TextView textViewCecoEmpleadoTextCDR;
    TextView textViewCecoEmpleadoCDR;
    TextView textViewFaseAgregadoCDR;
    TextView textViewEstatusResponsableCDR;
    TextView textViewTipoEmpleadoCDF;
    TextView textViewTipoResponsableCDR;

    public DetalleEmpleadosResponsablesViewHolder(View view) {
        super(view);
        textViewNombreEmpleadoTextCDR = view.findViewById(R.id.textViewNombreEmpleadoTextCDR);
        textViewNombreEmpleadoCDR = view.findViewById(R.id.textViewNombreEmpleadoCDR);
        textViewNumeroEmpleadoTextCDR = view.findViewById(R.id.textViewNumeroEmpleadoTextCDR);
        textViewNumeroEmpleadoCDR = view.findViewById(R.id.textViewNumeroEmpleadoCDR);
        textViewCecoEmpleadoTextCDR = view.findViewById(R.id.textViewCecoEmpleadoTextCDR);
        textViewCecoEmpleadoCDR = view.findViewById(R.id.textViewCecoEmpleadoCDR);
        textViewFaseAgregadoCDR = view.findViewById(R.id.textViewFaseAgregadoCDR);
        textViewEstatusResponsableCDR = view.findViewById(R.id.textViewEstatusResponsableCDR);
        textViewTipoEmpleadoCDF = view.findViewById(R.id.textViewTipoEmpleadoCDF);
        textViewTipoResponsableCDR = view.findViewById(R.id.textViewTipoResponsableCDR);
    }

    public void bind(Activity activity, DetalleDenunciaResponsables detalleDenunciaResponsables) {
        textViewNombreEmpleadoCDR.setText(detalleDenunciaResponsables.getNombre());

        if (detalleDenunciaResponsables.getIdEmpleado() != null) {
            textViewNumeroEmpleadoCDR.setText(String.valueOf(detalleDenunciaResponsables.getIdEmpleado()));
        } else {
            textViewNumeroEmpleadoTextCDR.setVisibility(View.GONE);
            textViewNumeroEmpleadoCDR.setVisibility(View.GONE);
        }

        if (detalleDenunciaResponsables.getCeco() != null) {
            textViewCecoEmpleadoCDR.setText(String.valueOf(detalleDenunciaResponsables.getCeco()));
        } else {
            textViewCecoEmpleadoTextCDR.setVisibility(View.GONE);
            textViewCecoEmpleadoCDR.setVisibility(View.GONE);
        }

        textViewFaseAgregadoCDR.setText(detalleDenunciaResponsables.getFaseAgregado());
        textViewEstatusResponsableCDR.setText(detalleDenunciaResponsables.getStatusResponsable());
        textViewTipoEmpleadoCDF.setText(detalleDenunciaResponsables.getTipoEmpleado());

    }
}