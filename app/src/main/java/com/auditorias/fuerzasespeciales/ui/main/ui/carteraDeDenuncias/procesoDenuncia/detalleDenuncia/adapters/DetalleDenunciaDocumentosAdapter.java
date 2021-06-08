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
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDocumento;
import com.auditorias.fuerzasespeciales.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class DetalleDenunciaDocumentosAdapter extends RecyclerView.Adapter<DetalleDenunciaDocumentosAdapter.DetalleDocuemtosViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDocumento> list;
    private final LayoutInflater inflater;
    private final OnItemClickListener listener;

    public DetalleDenunciaDocumentosAdapter(Activity activity, List<DetalleDocumento> list/*, FragmentManager fm*/, OnItemClickListener listener) {
        this.activity = activity;
        //this.fm = fm;
        this.list = list;
        this.listener = listener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleDocuemtosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_documentos_item, parent, false);
        return new DetalleDocuemtosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleDocuemtosViewHolder holder, final int position) {
        holder.bind(activity, list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        //void onItemClick(DetalleCasoDocumentoModel detalleCasoDocumentoModel, int position, Bitmap imageBitmap);
        void onItemClick(DetalleDocumento detalleDocumento, int position, String imagenString, String tipoArchivo);
    }

    static class DetalleDocuemtosViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPrevioDocumentoCDD;
        TextView textViewNombreDocumentosCDD;
        TextView textViewTamanioDocumentoTextCDD;
        TextView textViewTamanioDocumentoCDD;
        TextView textViewTipoDocumentoTextCDD;
        TextView textViewTipoDocumentoCDD;
        TextView textViewFechaAltaDocumentoTextCDD;
        TextView textViewFechaAltaDocumentoCDD;
        TextView textViewFaseAgregadoDocumentoTextCDD;
        TextView textViewFaseAgregadoDocumentoCDD;
        TextView textViewetapaAgregadoDocumentoTextCDD;
        TextView textViewEstatusAgregadoDocumentoCDD;
        TextView textViewTipoIntegracionDocumntoCDD;
        CardView cardViewDetalleDocumentosCDD;

        DetalleDocuemtosViewHolder(View view) {
            super(view);
            imageViewPrevioDocumentoCDD = view.findViewById(R.id.imageViewPrevioDocumentoCDD);
            textViewNombreDocumentosCDD = view.findViewById(R.id.textViewNombreDocumentosCDD);
            textViewTamanioDocumentoTextCDD = view.findViewById(R.id.textViewTamanioDocumentoTextCDD);
            textViewTamanioDocumentoCDD = view.findViewById(R.id.textViewTamanioDocumentoCDD);
            textViewTipoDocumentoTextCDD = view.findViewById(R.id.textViewTipoDocumentoTextCDD);
            textViewTipoDocumentoCDD = view.findViewById(R.id.textViewTipoDocumentoCDD);
            textViewFechaAltaDocumentoTextCDD = view.findViewById(R.id.textViewFechaAltaDocumentoTextCDD);
            textViewFechaAltaDocumentoCDD = view.findViewById(R.id.textViewFechaAltaDocumentoCDD);
            textViewFaseAgregadoDocumentoTextCDD = view.findViewById(R.id.textViewFaseAgregadoDocumentoTextCDD);
            textViewFaseAgregadoDocumentoCDD = view.findViewById(R.id.textViewFaseAgregadoDocumentoCDD);
            textViewetapaAgregadoDocumentoTextCDD = view.findViewById(R.id.textViewetapaAgregadoDocumentoTextCDD);
            textViewEstatusAgregadoDocumentoCDD = view.findViewById(R.id.textViewEstatusAgregadoDocumentoCDD);
            textViewTipoIntegracionDocumntoCDD = view.findViewById(R.id.textViewTipoIntegracionDocumntoCDD);
            cardViewDetalleDocumentosCDD = view.findViewById(R.id.cardViewDetalleDocumentosCDD);

        }

        public static String getFileSize(long size) {
            if (size <= 0) {
                return "0";
            }
            final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
            int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
            return new DecimalFormat("##0").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
            //return new DecimalFormat("#,##0").format(/*size / */Math.pow(1024, digitGroups)) + " " + units[digitGroups];
            //return new DecimalFormat("#,##0.#").format(/*size /*/ Math.pow(1024, digitGroups)) + " " + units[digitGroups];
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(Activity activity, DetalleDocumento detalleDocumento, OnItemClickListener listener) {
            if (detalleDocumento.getTipoArchivo().equals(".png") || detalleDocumento.getTipoArchivo().equals(".jpg") || detalleDocumento.getTipoArchivo().equals(".jpeg")
                    || detalleDocumento.getTipoArchivo().equals("png") || detalleDocumento.getTipoArchivo().equals("jpg") || detalleDocumento.getTipoArchivo().equals("jpeg")) {
                imageViewPrevioDocumentoCDD.setImageDrawable(activity.getDrawable(R.drawable.ic_image));
            } else if (detalleDocumento.getTipoArchivo().equals(".pdf") || detalleDocumento.getTipoArchivo().equals("pdf")) {
                imageViewPrevioDocumentoCDD.setImageDrawable(activity.getDrawable(R.drawable.ic_pdf_box));
            } else if (detalleDocumento.getTipoArchivo().equals("doc") || detalleDocumento.getTipoArchivo().equals("docx")
                    || detalleDocumento.getTipoArchivo().equals(".doc") || detalleDocumento.getTipoArchivo().equals(".docx")) {
                imageViewPrevioDocumentoCDD.setImageDrawable(activity.getDrawable(R.drawable.ic_word));
            }
            cardViewDetalleDocumentosCDD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(detalleDocumento, getAdapterPosition(), detalleDocumento.getStringArchivo(), detalleDocumento.getTipoArchivo());
                }
            });

            textViewNombreDocumentosCDD.setText(detalleDocumento.getDescripcion());
            textViewTamanioDocumentoCDD.setText(getFileSize(Long.parseLong(String.valueOf(detalleDocumento.getTamArchivo()))));
            textViewTipoDocumentoCDD.setText(detalleDocumento.getTipoArchivo());
            textViewFechaAltaDocumentoCDD.setText(Utils.SetCambioFormatoFechaDiaMesAnio(detalleDocumento.getFechaMod()));
            textViewFaseAgregadoDocumentoCDD.setText(detalleDocumento.getFase());
            textViewEstatusAgregadoDocumentoCDD.setText(detalleDocumento.getEtapaFase());
            textViewTipoIntegracionDocumntoCDD.setText(detalleDocumento.getTipoIntegracion());

        }
    }
}