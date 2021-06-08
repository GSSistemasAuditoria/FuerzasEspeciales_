package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaFase;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaSubFase;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetalleDenunciaSubfasesAdapter extends RecyclerView.Adapter<DetalleDenunciaSubfasesAdapter.DetalleDenunciaSubfasesViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDenunciaSubFase> listDetalleSubFases;
    private final LayoutInflater inflater;
    private final OnListener listener;

    public DetalleDenunciaSubfasesAdapter(Activity activity, List<DetalleDenunciaSubFase> listDetalleSubFasess/*, FragmentManager fm*/, OnListener listener) {
        this.activity = activity;
        //this.fm = fm;
        this.listDetalleSubFases = listDetalleSubFasess;
        this.listener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDenunciaSubfasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_subfases_item, parent, false);
        return new DetalleDenunciaSubfasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDenunciaSubfasesViewHolder holder, final int position) {
        holder.bind(activity, listDetalleSubFases.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listDetalleSubFases.size();
    }

    public interface OnListener {
        //void onItemClick(DetalleCasoDocumentoModel detalleCasoDocumentoModel, int position, Bitmap imageBitmap);
        void onItemClick(DetalleDenunciaFase detalleDenunciaFase, int position, String imagenString, String tipoArchivo);
        //void onClickDetalleDocumentos(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDocumento> documentos);
        //void onClickDetalleSubfases(DetalleDenunciaFase detalleDenunciaFase, int position, List<DetalleDenunciaSubFase> listSubfases);
    }

    static class DetalleDenunciaSubfasesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIconoSubfaseCDSF;
        TextView textViewNombreSubfaseCDSF;
        TextView textViewEstatusSubfaseColorCDSF;
        TextView textViewPorcentajeSubfaseCDSF;
        TextView textViewFechaInicioSubfaseCDSF;
        TextView textViewFechaCierreSubfaseCDSF;
        TextView textViewFechaRegistroSubfaseCDSF;
        TextView textViewFechaCompromisoSubfaseCDSF;


        DetalleDenunciaSubfasesViewHolder(View view) {
            super(view);
            imageViewIconoSubfaseCDSF = view.findViewById(R.id.imageViewIconoSubfaseCDSF);
            textViewNombreSubfaseCDSF = view.findViewById(R.id.textViewNombreSubfaseCDSF);
            textViewEstatusSubfaseColorCDSF = view.findViewById(R.id.textViewEstatusSubfaseColorCDSF);
            textViewPorcentajeSubfaseCDSF = view.findViewById(R.id.textViewPorcentajeSubfaseCDSF);
            textViewFechaInicioSubfaseCDSF = view.findViewById(R.id.textViewFechaInicioSubfaseCDSF);
            textViewFechaCierreSubfaseCDSF = view.findViewById(R.id.textViewFechaCierreSubfaseCDSF);
            textViewFechaRegistroSubfaseCDSF = view.findViewById(R.id.textViewFechaRegistroSubfaseCDSF);
            textViewFechaCompromisoSubfaseCDSF = view.findViewById(R.id.textViewFechaCompromisoSubfaseCDSF);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(Activity activity, DetalleDenunciaSubFase detalleDenunciaSubFase, OnListener listener) {
            Picasso.get().load(detalleDenunciaSubFase.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewIconoSubfaseCDSF);
            textViewNombreSubfaseCDSF.setText(detalleDenunciaSubFase.getNombreFase());
            textViewEstatusSubfaseColorCDSF.setText(detalleDenunciaSubFase.getEtapaFase());
            textViewEstatusSubfaseColorCDSF.setBackground(Utils.cambiarColorTextView(detalleDenunciaSubFase.getColorEtapaFase()));

            //textViewPorcentajeSubfaseCDSF.setText(String.valueOf(detalleDenunciaSubFase.getPorcentajeAvanceGeneral())+ " %");
            textViewPorcentajeSubfaseCDSF.setText(Utils.setFormatoNumeroEnteroPorcentaje(detalleDenunciaSubFase.getPorcentajeAvanceGeneral()));

            if (detalleDenunciaSubFase.getFechaRegistro() != null ){
                textViewFechaRegistroSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaRegistro()));
            }else {
                textViewFechaRegistroSubfaseCDSF.setText("");
            }

            if (detalleDenunciaSubFase.getFechaCompromiso() != null ){
                textViewFechaCompromisoSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaCompromiso()));
            }else {
                textViewFechaCompromisoSubfaseCDSF.setText("");
            }

            if (detalleDenunciaSubFase.getFechaInicio() != null ){
                textViewFechaInicioSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaInicio()));
            }else {
                textViewFechaInicioSubfaseCDSF.setText("");
            }

            if (detalleDenunciaSubFase.getFechaCierre() != null ){
                textViewFechaCierreSubfaseCDSF.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDenunciaSubFase.getFechaCierre()));
            }else {
                textViewFechaCierreSubfaseCDSF.setText("");
            }

           /* buttonSubfasesCDF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //void onClickDocumentos(DetalleDenunciaFase detalleDenunciaSubFase, int position, int idDocumento);
                    //listener.onClickDocumentos(detalleDenunciaSubFase,getAdapterPosition(), detalleDenunciaSubFase.getDocumentos());
                    //listener.onClickDetalleSubfases(detalleDenunciaSubFase, getAdapterPosition(), detalleDenunciaSubFase.getSubfases());
                }
            });*/

        }

    }
}