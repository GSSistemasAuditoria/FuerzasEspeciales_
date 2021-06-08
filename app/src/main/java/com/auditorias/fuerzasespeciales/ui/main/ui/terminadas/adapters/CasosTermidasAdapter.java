package com.auditorias.fuerzasespeciales.ui.main.ui.terminadas.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.denunciaTerminada.DataCasosTerminados;
import com.auditorias.fuerzasespeciales.utils.Utils;

import java.text.ParseException;
import java.util.List;

public class CasosTermidasAdapter extends RecyclerView.Adapter<CasosTermidasAdapter.CasosTermidasHolder> {

    private final Activity activityCA;
    private final List<DataCasosTerminados> listCasoTerminados;
    private final LayoutInflater inflater;
    private OnListenerCasosTerminados itemClickListener;
    private FragmentManager fragmentManager;

    public CasosTermidasAdapter(Activity activityCA, FragmentManager fragmentManager, List<DataCasosTerminados> listCasoTerminados, OnListenerCasosTerminados itemClickListener) {
        this.activityCA = activityCA;
        this.fragmentManager = fragmentManager;
        this.listCasoTerminados = listCasoTerminados;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    @NonNull
    @Override
    public CasosTermidasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_casos_abogado_item, parent, false);
        return new CasosTermidasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CasosTermidasHolder holder, final int position) {
        holder.bind(listCasoTerminados.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listCasoTerminados.size();
    }

    public interface OnListenerCasosTerminados {
        void onItemClick(DataCasosTerminados dataCasosTerminados, int position);
    }

    class CasosTermidasHolder extends RecyclerView.ViewHolder {

        TextView textViewFolioCasoAbogadoCAA;
        TextView textView;
        TextView textViewStatusFaseCasoCAA;
        TextView textViewNombreCasoCAA;
        TextView textViewUnidadNegocioCAA;
        //TextView textViewFechaCompromisoCAA;
        TextView textViewEstatusAutorizacionCAA;
        TextView textViewFechaRegistroCAA;
        LinearLayout linearLayoutFaseColorCasoCAA;
        LinearLayout linearLayoutCasosAbogadoCAA;

        CasosTermidasHolder(View view) {
            super(view);
            textViewFolioCasoAbogadoCAA = view.findViewById(R.id.textViewFolioDenunciaCAA);
            textView = view.findViewById(R.id.textViewColorEtapaFaseCAA);
            textViewStatusFaseCasoCAA = view.findViewById(R.id.textViewEtapaFaseCAA);
            textViewNombreCasoCAA = view.findViewById(R.id.textViewNombreDenunciaCAA);
            textViewUnidadNegocioCAA = view.findViewById(R.id.textViewUnidadNegocioCAA);
            //textViewFechaCompromisoCAA = view.findViewById(R.id.textViewFechaReporteCAA);
            linearLayoutFaseColorCasoCAA = view.findViewById(R.id.linearLayoutEtapaDenunciaCAA);
            linearLayoutCasosAbogadoCAA = view.findViewById(R.id.linearLayoutDenunciaCAA);
            textViewEstatusAutorizacionCAA = view.findViewById(R.id.textViewEstatusAutorizacionCAA);
            textViewFechaRegistroCAA = view.findViewById(R.id.textViewFechaCompromisoCAA);
        }


        public void bind(final DataCasosTerminados dataCasosTerminados, final OnListenerCasosTerminados listener) {

            Utils.setTextViewLetraYFondoBlue(activityCA, textViewStatusFaseCasoCAA);
            textViewFolioCasoAbogadoCAA.setText(dataCasosTerminados.getFolio());
            //textViewStatusFaseCasoCAA.setTextColor(activityCA.getColor(R.color.white));
            textViewStatusFaseCasoCAA.setText(dataCasosTerminados.getFase());
            //textViewStatusFaseCasoCAA.setBackground(Utils.cambiarColorTextView(dataCasosTerminados.getColorEtapaCaso()));
            textViewNombreCasoCAA.setText(dataCasosTerminados.getNombre());
            textViewUnidadNegocioCAA.setText(dataCasosTerminados.getUdN());
            linearLayoutFaseColorCasoCAA.setBackgroundColor(Color.parseColor(dataCasosTerminados.getColorEtapaCaso()));
            textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
            textView.setBackgroundColor(Color.parseColor(dataCasosTerminados.getColorEtapaCaso()));
            if (dataCasosTerminados.getFechaCompromiso() != null) {
                try {
                    if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(dataCasosTerminados.getFechaCompromiso()))) {
                        textViewEstatusAutorizacionCAA.setText(dataCasosTerminados.getStatusAutorizacion());
                        Utils.setTextViewLetraYFondo(activityCA, textViewEstatusAutorizacionCAA, dataCasosTerminados.getColorAutorizacion());
                    }else {
                        textViewEstatusAutorizacionCAA.setText("");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                textViewFechaRegistroCAA.setText(Utils.SetCambioFormatoFechaDiaMesAnio(dataCasosTerminados.getFechaCompromiso()));
            } else {
                textViewFechaRegistroCAA.setText("");
                textViewEstatusAutorizacionCAA.setText("");
            }


            linearLayoutCasosAbogadoCAA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dataCasosTerminados, getAdapterPosition());
                }
            });
        }
    }
}
