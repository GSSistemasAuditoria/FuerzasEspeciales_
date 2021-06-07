package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.detalleDenuncia.adapters;

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
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFaseReprogramaciones;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDocumento;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetalleDenunciaFasesAdapter extends RecyclerView.Adapter<DetalleDenunciaFasesAdapter.DetalleDenunciaFasesViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDenunciaFase> listDetalleFases;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaFasesAdapter(Activity activity, List<DetalleDenunciaFase> listDetalleFases/*, FragmentManager fm*/, OnListener listener) {
        this.activity = activity;
        //this.fm = fm;
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
        void onItemClick(DetalleDenunciaFase detalleDenunciaFase, int position, String imagenString, String tipoArchivo);

        void onClickDetalleDocumentos(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDocumento> documentos);

        void onClickDetalleSubfases(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDenunciaSubFase> listSubfases);

        void onClickDetalleReprogramaciones(DetalleDenunciaFase detalleDenunciaFase, int positio, List<DetalleDenunciaFaseReprogramaciones> listReprogramaciones);
    }

    static class DetalleDenunciaFasesViewHolder extends RecyclerView.ViewHolder {
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

        DetalleDenunciaFasesViewHolder(View view) {
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
            //buttn = view.findViewById(R.id.buttn);
            //buttonSubfasesCDF = view.findViewById(R.id.buttonSubfasesCDF);
            //buttonReporgramarCDF = view.findViewById(R.id.buttonReporgramarCDF);

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(Activity activity, DetalleDenunciaFase detalleDenunciaFase, OnListener listener) {
            Picasso.get().load(detalleDenunciaFase.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewImagenFaseCDF);
            textViewNombreFaseCDF.setText(detalleDenunciaFase.getNombreFase());
            textViewEtapaFaseColorCDF.setText(detalleDenunciaFase.getEtapaFase());
            textViewEtapaFaseColorCDF.setBackground(Utils.cambiarColorTextView(detalleDenunciaFase.getColorEtapaFase()));
            //textViewEtapaFaseColorCDF.setText();
            textViewPorcentajeFaseCDF.setText(Utils.setFormatoNumeroEnteroPorcentaje(detalleDenunciaFase.getPorcentajeAvanceGeneral()));
            //textViewPorcentajeFaseCDF.setText(String.valueOf(detalleDenunciaFase.getPorcentajeAvanceGeneral()) + " %");

            if (detalleDenunciaFase.getFechaRegistro() != null) {
                textViewFechaRegistroCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaRegistro().trim()));
            } else {
                textViewFechaRegistroCDF.setText("");
            }

            if (detalleDenunciaFase.getFechaCompromiso() != null) {
                textViewFechaCompromisoCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaCompromiso().trim()));
            } else {
                textViewFechaCompromisoCDF.setText("");
            }

            if (detalleDenunciaFase.getFechaInicio() != null) {
                textViewFechaInicioFaseCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaInicio().trim()));
            } else {
                textViewFechaInicioFaseCDF.setText("");
            }

            if (detalleDenunciaFase.getFechaCierre() != null) {
                textViewFechaCierreFaseCDF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaFase.getFechaCierre().trim()));
            } else {
                textViewFechaCierreFaseCDF.setText("");
            }

            if (!detalleDenunciaFase.getSubfases().isEmpty()) {

                cardViewSubfasesCDF.setCardBackgroundColor(activity.getColor(R.color.green_secondary));
                cardViewSubfasesCDF.setActivated(true);
            } else {
                cardViewSubfasesCDF.setCardBackgroundColor(activity.getColor(R.color.Grey500));
                cardViewSubfasesCDF.setActivated(false);
            }

            if (!detalleDenunciaFase.getReprogramaciones().isEmpty()) {
                //buttonReporgramarCDF.setEnabled(true);
                //cardViewSubfasesCDF.setActivated(true);
                //buttonReporgramarCDF.setBackground();
                //buttonSubfasesCDF.setBackground();
                cradViewReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.green_secondary));
                cradViewReprogramacionesCDF.setActivated(true);
            } else {
                //buttonReporgramarCDF.setEnabled(false);
                //cardViewSubfasesCDF.setActivated(false);
                cradViewReprogramacionesCDF.setCardBackgroundColor(activity.getColor(R.color.Grey500));
                cradViewReprogramacionesCDF.setActivated(false);
            }

            cardViewSubfasesCDF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //void onClickDocumentos(DetalleDenunciaFase detalleDenunciaFase, int position, int idDocumento);
                    //listener.onClickDocumentos(detalleDenunciaFase,getAdapterPosition(), detalleDenunciaFase.getDocumentos());
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
}