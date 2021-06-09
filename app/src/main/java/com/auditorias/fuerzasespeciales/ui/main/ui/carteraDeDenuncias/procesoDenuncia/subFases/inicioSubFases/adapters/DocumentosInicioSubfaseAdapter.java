package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.inicioSubFases.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.request.DocumentoRequest;

import java.util.List;

public class DocumentosInicioSubfaseAdapter extends RecyclerView.Adapter<DocumentosInicioSubfaseAdapter.DocumentosInicioSubfaseViewHolder> {

    private final Activity activity;
    private final List<DocumentoRequest> listResposablesCerrar;
    private final LayoutInflater inflater;
    private final OnItemSelectedListener itemSelectedListener;

    public DocumentosInicioSubfaseAdapter(Activity activity, List<DocumentoRequest> listResposablesCerrar, OnItemSelectedListener itemSelectedListener) {
        this.activity = activity;

        this.listResposablesCerrar = listResposablesCerrar;
        this.itemSelectedListener = itemSelectedListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DocumentosInicioSubfaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_documentos_inicio_subfase, parent, false);
        return new DocumentosInicioSubfaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DocumentosInicioSubfaseViewHolder holder, final int position) {
        holder.bind(activity, listResposablesCerrar.get(position), itemSelectedListener);
    }

    @Override
    public int getItemCount() {
        return listResposablesCerrar.size();
    }

    public List<DocumentoRequest> getResponsablesResquest() {
        return listResposablesCerrar;
    }

    public interface OnItemSelectedListener {
        void onEliminarListener(DocumentoRequest datosDenunciaResponsable, int position);

        void onVerListener(DocumentoRequest datosDenunciaResponsable, int position);
    }

    static class DocumentosInicioSubfaseViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewLogoDocumentoInicioSubfase;
        TextView textViewNombreDocumentoInicioFase;
        TextView textViewTamanioDocumentoInicioSubfase;
        TextView textViewExtensionDocumentoInicioSubfae;
        TextView textViewTipoDocumentoInicioSubfae;
        ImageView imageViewEliminarDocumentoLista;
        CardView cardViewDocumentosInicioSubfase;

        DocumentosInicioSubfaseViewHolder(View view) {
            super(view);
            imageViewLogoDocumentoInicioSubfase = view.findViewById(R.id.imageViewLogoDocumentoInicioSubfase);
            textViewNombreDocumentoInicioFase = view.findViewById(R.id.textViewColorEtapaFaseCAA);
            textViewTamanioDocumentoInicioSubfase = view.findViewById(R.id.textViewTamanioDocumentoInicioSubfase);
            textViewExtensionDocumentoInicioSubfae = view.findViewById(R.id.textViewExtensionDocumentoInicioSubfae);
            textViewTipoDocumentoInicioSubfae = view.findViewById(R.id.textViewTipoDocumentoInicioSubfae);
            cardViewDocumentosInicioSubfase = view.findViewById(R.id.cardViewDocumentosInicioSubfase);
            imageViewEliminarDocumentoLista = view.findViewById(R.id.imageViewEliminarDocumentoLista);
        }

        //TODO: llenado de los items que se encuentran dentro del recyclerview
        public void bind(Activity activity, DocumentoRequest documentoRequest, OnItemSelectedListener itemSelectedListener) {
            textViewNombreDocumentoInicioFase.setText(documentoRequest.getDescripcion());
            textViewExtensionDocumentoInicioSubfae.setText(documentoRequest.getTipoArchivo());
            textViewTamanioDocumentoInicioSubfase.setText(String.valueOf(documentoRequest.getTamArhivo()));
            textViewTipoDocumentoInicioSubfae.setText(documentoRequest.getTipoDocumento());

            if (documentoRequest.getTipoArchivo().equals(".png") || documentoRequest.getTipoArchivo().equals(".jpg") || documentoRequest.getTipoArchivo().equals(".jpeg")
                    || documentoRequest.getTipoArchivo().equals("png") || documentoRequest.getTipoArchivo().equals("jpg") || documentoRequest.getTipoArchivo().equals("jpeg")) {
                imageViewLogoDocumentoInicioSubfase.setImageDrawable(activity.getDrawable(R.drawable.ic_image));
            } else if (documentoRequest.getTipoArchivo().equals(".pdf") || documentoRequest.getTipoArchivo().equals("pdf")) {
                imageViewLogoDocumentoInicioSubfase.setImageDrawable(activity.getDrawable(R.drawable.ic_pdf_box));
            } else if (documentoRequest.getTipoArchivo().equals("doc") || documentoRequest.getTipoArchivo().equals("docx")
                    || documentoRequest.getTipoArchivo().equals(".doc") || documentoRequest.getTipoArchivo().equals(".docx")) {
                imageViewLogoDocumentoInicioSubfase.setImageDrawable(activity.getDrawable(R.drawable.ic_word));
            }

            imageViewEliminarDocumentoLista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelectedListener.onEliminarListener(documentoRequest, getAdapterPosition());
                }
            });

            cardViewDocumentosInicioSubfase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelectedListener.onVerListener(documentoRequest, getAdapterPosition());
                }
            });
        }


    }
}