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

        LinearLayout linearLayoutEtapaDenunciaCAA;
        TextView textViewFolioDenunciaCAA;
        TextView textViewEtapaFaseCAA;
        TextView textViewColorEtapaFaseCAA;
        TextView textViewNombreDenunciaCAA;
        TextView textViewEtapaSubFaseCAA;
        TextView textViewColorEtapaSubFaseCAA;

        TextView textViewUnidadNegocioCAA;
        //TextView textViewFechaCompromisoCAA;
        TextView textViewEstatusAutorizacionCAA;
        TextView textViewFechaCompromisoCAA;

        LinearLayout linearLayoutDenunciaCAA;

        CasosTermidasHolder(View view) {
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


        public void bind(final DataCasosTerminados dataCasosTerminados, final OnListenerCasosTerminados listener) {

            if (dataCasosTerminados.getColorEtapaCaso() != null){
                linearLayoutEtapaDenunciaCAA.setBackgroundColor(Color.parseColor(dataCasosTerminados.getColorEtapaCaso()));
            }else {
                linearLayoutEtapaDenunciaCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getFolio() != null){
                textViewFolioDenunciaCAA.setText(dataCasosTerminados.getFolio());
            }else {
                textViewFolioDenunciaCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getFase() != null){
                Utils.setTextViewLetraYFondoBlue(activityCA, textViewEtapaFaseCAA);
                textViewEtapaFaseCAA.setText(dataCasosTerminados.getFase());
            }else {
                textViewEtapaFaseCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getColorEtapaCaso() != null){
                textViewColorEtapaFaseCAA.setBackgroundColor(Color.parseColor(dataCasosTerminados.getColorEtapaCaso()));
            }else {
                textViewColorEtapaFaseCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getNombre() != null){
                textViewNombreDenunciaCAA.setText(dataCasosTerminados.getNombre());
            }else {
                textViewNombreDenunciaCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getEtapaSubFase() != null){
                textViewEtapaSubFaseCAA.setText(dataCasosTerminados.getEtapaSubFase());
            }else {
                textViewEtapaSubFaseCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getColorSubFase() !=null){
                textViewColorEtapaSubFaseCAA.setText(dataCasosTerminados.getColorSubFase());
            }else {
                textViewColorEtapaSubFaseCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getUdN() != null){
                textViewUnidadNegocioCAA.setText(dataCasosTerminados.getUdN());
            }else {
                textViewUnidadNegocioCAA.setVisibility(View.GONE);
            }

            if (dataCasosTerminados.getFechaCompromiso() != null) {
                try {
                    if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(dataCasosTerminados.getFechaCompromiso()))) {
                        if (dataCasosTerminados.getStatusAutorizacion() != null){
                            textViewEstatusAutorizacionCAA.setText(dataCasosTerminados.getStatusAutorizacion());
                        }else {
                            textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
                        }

                        if (dataCasosTerminados.getColorAutorizacion1() != null){
                            Utils.setTextViewLetraYFondo(activityCA, textViewEstatusAutorizacionCAA, dataCasosTerminados.getColorAutorizacion1());
                        }else {
                            textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
                        }
                    }else {
                        textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                textViewFechaCompromisoCAA.setText(Utils.SetCambioFormatoFechaDiaMesAnio(dataCasosTerminados.getFechaCompromiso()));
            } else {
                textViewFechaCompromisoCAA.setVisibility(View.GONE);
                textViewEstatusAutorizacionCAA.setVisibility(View.GONE);
            }

            linearLayoutDenunciaCAA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dataCasosTerminados, getAdapterPosition());
                }
            });
        }
    }
}
