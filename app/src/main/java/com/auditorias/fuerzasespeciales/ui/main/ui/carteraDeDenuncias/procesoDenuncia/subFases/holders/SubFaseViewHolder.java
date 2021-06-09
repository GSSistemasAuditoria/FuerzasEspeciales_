package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.holders;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.SubfaseActiva;
import com.auditorias.fuerzasespeciales.models.denucia.datosDenuncia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.adapters.SubFaseAdapter;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class SubFaseViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNombreSubFaseCSF;
    TextView textViewReprogramarSubFaseCSF;
    ImageView imageViewIconoSubFaseCSF;
    ImageView imageViewCerrarSubFaseCSF;
    CardView cardViewSubFaseCSF;

    public SubFaseViewHolder(View view) {
        super(view);
        textViewNombreSubFaseCSF = view.findViewById(R.id.textViewNombreSubFaseCSF);
        textViewReprogramarSubFaseCSF = view.findViewById(R.id.textViewReprogramarSubFaseCSF);
        imageViewIconoSubFaseCSF = view.findViewById(R.id.imageViewIconoSubFaseCSF);
        imageViewCerrarSubFaseCSF = view.findViewById(R.id.imageViewCerrarSubFaseCSF);
        cardViewSubFaseCSF = view.findViewById(R.id.cardViewSubFaseCSF);
    }

    public void bind(Activity activity, final SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {

        textViewNombreSubFaseCSF.setText(subfaseActiva.getDescripcion());
        if (subfaseActiva.getImagenUrl() != null) {
            Picasso.get().load(subfaseActiva.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewIconoSubFaseCSF);
        } else {

        }

        setSubFaseActiva(activity, textViewReprogramarSubFaseCSF, imageViewCerrarSubFaseCSF, cardViewSubFaseCSF, subfaseActiva, datosDenuncia, listener);

    }

    private void setSubFaseActiva(Activity activity, TextView textView, ImageView imageView, CardView cardView, SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {
        //TODO: si la fase no esta activa se envia null en señal de que no esta activa
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        if (subfaseActiva.getActivaoUltima() == null) {
            cardView.setCardBackgroundColor(Color.parseColor(String.valueOf(subfaseActiva.getColorFase())));
        } else if (subfaseActiva.getActivaoUltima().equals(true)) {
            cardView.setCardBackgroundColor(Color.parseColor(String.valueOf(subfaseActiva.getColorFase())));
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    reprogramacionDeSubFase(textView, imageView, cardView, subfaseActiva, datosDenuncia, listener);
                } else {
                    avanceIniciarSubFase(textView, imageView, cardView, subfaseActiva, datosDenuncia, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (subfaseActiva.getActivaoUltima().equals(false)) {
            cardViewSubFaseCSF.setCardBackgroundColor(Color.parseColor(String.valueOf(subfaseActiva.getColorFase())));
        }
    }

    public void reprogramacionDeSubFase(TextView textView, ImageView imageView, CardView cardView, SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReprogramarSubfase(subfaseActiva, getAdapterPosition(), String.valueOf(datosDenuncia.getId()));
            }
        });
        //TODO: validacion de autorizacion de reenevio
        if (datosDenuncia.getIdStatusAutorizacion() == 0 || datosDenuncia.getIdStatusAutorizacion() == 3) {
            //Sin estatus 0
            //Autorizada 3
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    textView.setText(datosDenuncia.getStatusAutorizacion());
                } else {
                    cerraSubFase(cardView, subfaseActiva, datosDenuncia, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (datosDenuncia.getIdStatusAutorizacion() == 1) {
            //reprogramar
            textView.setText(datosDenuncia.getStatusAutorizacion());
        } else if (datosDenuncia.getIdStatusAutorizacion() == 2) {
            //En Autorizacion
            textView.setText(datosDenuncia.getStatusAutorizacion());
            cardView.setEnabled(false);
        } else if (datosDenuncia.getIdStatusAutorizacion() == 4) {
            //Rechazada
            textView.setBackground((Utils.cambiarColorTextView(datosDenuncia.getColorAutorizacion1())));
            textView.setText(datosDenuncia.getStatusAutorizacion());
        }
    }

    public void avanceIniciarSubFase(TextView textView, ImageView imageView, CardView cardView, SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        if (subfaseActiva.getIdEtapa().equals(1)) {
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    reprogramacionDeSubFase(textView, imageView, cardView, subfaseActiva, datosDenuncia, listener);
                } else {
                    iniciarSubFase(cardView, subfaseActiva, datosDenuncia, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (subfaseActiva.getIdEtapa().equals(2)) {
            imageView.setVisibility(View.VISIBLE);
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    reprogramacionDeSubFase(textView, imageView, cardView, subfaseActiva, datosDenuncia, listener);
                } else {
                    cerraSubFase(cardView, subfaseActiva, datosDenuncia, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (subfaseActiva.getIdEtapa().equals(3)) {
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void iniciarSubFase(CardView cardView, SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIniciarSubfase(subfaseActiva, getAdapterPosition(), String.valueOf(datosDenuncia.getId()));
            }
        });
    }

    public void cerraSubFase(CardView cardView, SubfaseActiva subfaseActiva, DatosDenuncia datosDenuncia, final SubFaseAdapter.OnItemClickListener listener) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCerrarSubfase(subfaseActiva, getAdapterPosition(), String.valueOf(datosDenuncia.getId()));
            }
        });
    }
}