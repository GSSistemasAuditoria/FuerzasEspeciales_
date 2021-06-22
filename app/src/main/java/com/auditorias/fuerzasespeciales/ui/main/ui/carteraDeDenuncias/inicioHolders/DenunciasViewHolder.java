package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.inicioHolders;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.casos.CasosAbogado;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.inicioAdapters.DenunciasAdapter;
import com.auditorias.fuerzasespeciales.utils.Utils;

import java.text.ParseException;

public class DenunciasViewHolder extends RecyclerView.ViewHolder {

    LinearLayout linearLayoutEtapaDenunciaCAA;
    TextView textViewFolioDenunciaCAA;
    TextView textViewEtapaFaseCAA;
    TextView textViewColorEtapaFaseCAA;
    TextView textViewNombreDenunciaCAA;
    TextView textViewEtapaSubFaseCAA;
    TextView textViewColorEtapaSubFaseCAA;
    TextView textViewUnidadNegocioCAA;
    TextView textViewFechaCompromisoCAA;
    TextView textViewEstatusAutorizacionCAA;
    LinearLayout linearLayoutDenunciaCAA;

    public DenunciasViewHolder(View view) {
        super(view);
        linearLayoutEtapaDenunciaCAA = view.findViewById(R.id.linearLayoutEtapaDenunciaCAA);
        textViewFolioDenunciaCAA = view.findViewById(R.id.textViewFolioDenunciaCAA);
        textViewEtapaFaseCAA = view.findViewById(R.id.textViewEtapaFaseCAA);
        textViewColorEtapaFaseCAA = view.findViewById(R.id.textViewColorEtapaFaseCAA);
        textViewNombreDenunciaCAA = view.findViewById(R.id.textViewNombreDenunciaCAA);
        textViewEtapaSubFaseCAA = view.findViewById(R.id.textViewEtapaSubFaseCAA);
        textViewColorEtapaSubFaseCAA = view.findViewById(R.id.textViewColorEtapaSubFaseCAA);
        textViewUnidadNegocioCAA = view.findViewById(R.id.textViewUnidadNegocioCAA);
        textViewFechaCompromisoCAA = view.findViewById(R.id.textViewFechaCompromisoCAA);
        textViewEstatusAutorizacionCAA = view.findViewById(R.id.textViewEstatusAutorizacionCAA);
        linearLayoutDenunciaCAA = view.findViewById(R.id.linearLayoutDenunciaCAA);
    }

    public void bind(Activity activity, final CasosAbogado casosAbogado, final DenunciasAdapter.OnClickListener listener) {

        if (casosAbogado.getColorEtapaCaso() !=null){
            linearLayoutEtapaDenunciaCAA.setBackgroundColor(Color.parseColor(casosAbogado.getColorEtapaCaso()));
        }else {
            linearLayoutEtapaDenunciaCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getFolio() != null){
            textViewFolioDenunciaCAA.setText(casosAbogado.getFolio());
        }else {
            textViewFolioDenunciaCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getFase() != null){
            Utils.setTextViewLetraYFondoBlue(activity, textViewEtapaFaseCAA);
            Utils.setTextViewLetraYFondoBlue(activity, textViewEtapaFaseCAA);
            textViewEtapaFaseCAA.setText(casosAbogado.getFase());
        }else {
            textViewEtapaFaseCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getColorFase() != null){
            textViewColorEtapaFaseCAA.setBackground(Utils.cambiarColorTextView(casosAbogado.getColorFase()));
        }else {
            textViewColorEtapaFaseCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getNombre() != null){
            textViewNombreDenunciaCAA.setText(casosAbogado.getNombre());
        }else {
            textViewNombreDenunciaCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getSubFase() != null) {
            textViewEtapaSubFaseCAA.setText(casosAbogado.getSubFase());
        } else {
            textViewEtapaSubFaseCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getColorSubFase() != null) {
            textViewColorEtapaSubFaseCAA.setBackground(Utils.cambiarColorTextView(casosAbogado.getColorSubFase()));
        } else {
            textViewColorEtapaSubFaseCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getUdN() != null){
            textViewUnidadNegocioCAA.setText(casosAbogado.getUdN());
        }else {
            textViewUnidadNegocioCAA.setVisibility(View.GONE);
        }

        if (casosAbogado.getFechaCompromiso() != null) {
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(casosAbogado.getFechaCompromiso()))) {
                    if (casosAbogado.getStatusAutorizacion() != null) {
                        textViewEstatusAutorizacionCAA.setText(casosAbogado.getStatusAutorizacion());
                        textViewEstatusAutorizacionCAA.setVisibility(View.VISIBLE);
                    } else {
                        textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
                    }
                    if (casosAbogado.getColorAutorizacion1() != null) {
                        textViewEstatusAutorizacionCAA.setBackground(Utils.cambiarColorTextView(casosAbogado.getColorAutorizacion1()));
                    }
                } else {
                    textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            textViewFechaCompromisoCAA.setText(Utils.SetCambioFormatoFechaDiaMesAnio(casosAbogado.getFechaCompromiso()));
        } else {
            textViewFechaCompromisoCAA.setText("");
            textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
        }

        linearLayoutDenunciaCAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(casosAbogado, getAdapterPosition());
            }
        });
    }
}