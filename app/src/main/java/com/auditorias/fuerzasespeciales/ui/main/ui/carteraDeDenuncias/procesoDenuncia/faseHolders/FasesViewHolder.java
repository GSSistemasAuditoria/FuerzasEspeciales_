package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.faseHolders;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.FaseActivaDatos;
import com.auditorias.fuerzasespeciales.models.catalogos.faseActiva.SubfaseActiva;
import com.auditorias.fuerzasespeciales.models.denucia.DatosDenuncia;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.faseAdapters.FasesAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.subFases.adapters.SubFaseAdapter;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class FasesViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewNombreFaseCFI;
    private final TextView textViewReporgramarFaseCFI;
    private final ImageView imageViewIconoFaseCFI;
    private final ImageView imageViewCerrarFaseCFI;
    private final CardView cardViewFaseCFI;
    private final RecyclerView recyclerViewSubFaceCFI;
    private final View view;
    private int activado = 1;

    public FasesViewHolder(View view) {
        super(view);
        textViewNombreFaseCFI = view.findViewById(R.id.textViewNombreFaseCF);
        textViewReporgramarFaseCFI = view.findViewById(R.id.textViewReporgramarFaseCF);
        imageViewIconoFaseCFI = view.findViewById(R.id.imageViewIconoFaseCF);
        imageViewCerrarFaseCFI = view.findViewById(R.id.imageViewCerrarFaseCF);
        cardViewFaseCFI = view.findViewById(R.id.cardViewFaseCF);
        recyclerViewSubFaceCFI = view.findViewById(R.id.recyclerViewSubFaceCFI);
        this.view = view;
    }

    public void bind(Activity activity, final FaseActivaDatos faseActivaDatos, DatosDenuncia datosDenuncia, final FasesAdapter.OnItemClickListener listener) {
        textViewNombreFaseCFI.setText(faseActivaDatos.getDescripcion());

        if (faseActivaDatos.getImagenUrl() != null) {
            Picasso.get().load(faseActivaDatos.getImagenUrl().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageViewIconoFaseCFI);
        } else {

        }

        recyclerViewSubFaceCFI.setVisibility(View.GONE);
        recyclerViewSubFaceCFI.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        SubFaseAdapter subFaseAdapter = new SubFaseAdapter(activity, faseActivaDatos.getListSubfasesActivas(), datosDenuncia, new SubFaseAdapter.OnItemClickListener() {
            @Override
            public void onIniciarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso) {
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                bundle.putString("idSubFase", String.valueOf(subfaseActiva.getId()));
                bundle.putString("idCasoFase", String.valueOf(subfaseActiva.getIdCasoFase()));
                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_del_caso_fragment_to_navigation_iniciar_sub_fases_fragment, bundle);
            }

            @Override
            public void onReprogramarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso) {
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                bundle.putString("idCasoFase", String.valueOf(subfaseActiva.getIdCasoFase()));
                bundle.putString("IdStatusAutorizacion", String.valueOf(subfaseActiva.getIdStatusAutorizacion()));
                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_denuncia_fragment_to_navigation_reprogramar_fase_fragment, bundle);
            }

            @Override
            public void onCerrarSubfase(SubfaseActiva subfaseActiva, int position, String idCaso) {
                Bundle bundle = new Bundle();
                bundle.putString("idCaso", idCaso);
                bundle.putString("idCasoFase", String.valueOf(subfaseActiva.getIdCasoFase()));
                Navigation.findNavController(view).navigate(R.id.action_navigation_proceso_fase_del_caso_fragment_to_navigation_terminar_presentacion_denuncia_fragment, bundle);
            }
        });
        recyclerViewSubFaceCFI.setAdapter(subFaseAdapter);

        setFaseActiva(activity, textViewReporgramarFaseCFI, imageViewCerrarFaseCFI, cardViewFaseCFI, recyclerViewSubFaceCFI, faseActivaDatos, datosDenuncia, listener);
    }

    private void setFaseActiva(Activity activity, TextView textViewReprogramar, ImageView imageView, CardView cardView, RecyclerView recyclerView, FaseActivaDatos faseActivaDatos, DatosDenuncia datosDenuncia, final FasesAdapter.OnItemClickListener listener) {
        //TODO: si la fase no esta activa se envia null en señal de que no esta activa
        textViewReprogramar.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        if (faseActivaDatos.getActivaoUltima() == null) {
            textViewNombreFaseCFI.setTextColor(activity.getColor(R.color.BlueGrey500));
            cardView.setCardBackgroundColor(activity.getColor(R.color.blueGrey50));
            //cardView.setCardBackgroundColor(Color.parseColor(String.valueOf(faseActivaDatos.getColorFase())));
            //TODO: si la fase está activa se envia true en señal de que está activa
        } else if (faseActivaDatos.getActivaoUltima().equals(true)) {
            cardView.setCardBackgroundColor(activity.getColor(R.color.bluePrimary));
            if (!faseActivaDatos.getListSubfasesActivas().isEmpty()) {
                textViewReprogramar.setVisibility(View.VISIBLE);
                textViewReprogramar.setText(faseActivaDatos.getEtapaFase());
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (activado == 1) {
                            activado = 2;
                            recyclerView.setVisibility(View.VISIBLE);
                        } else if (activado == 2) {
                            activado = 1;
                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                });
            } else {
                try {
                    if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                        reprogramacionAntesDeIniciarFase(textViewReprogramar, imageView, cardView, faseActivaDatos, datosDenuncia, listener);
                    } else {
                        avanceIniciarFase(textViewReprogramar, imageView, cardView, faseActivaDatos, datosDenuncia, listener);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else if (faseActivaDatos.getActivaoUltima().equals(false)) {
            cardView.setCardBackgroundColor(Color.parseColor(String.valueOf(faseActivaDatos.getColorFase())));
            textViewReprogramar.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        }
    }

    public void reprogramacionAntesDeIniciarFase(TextView textView, ImageView imageView, CardView cardView, FaseActivaDatos faseActivaDatos, DatosDenuncia datosDenuncia, final FasesAdapter.OnItemClickListener listener) {
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReprogramarFase(faseActivaDatos, getAdapterPosition());
            }
        });
        //TODO: validacion de autorizacion de reenevio
        if (datosDenuncia.getIdStatusAutorizacion() == 0 || datosDenuncia.getIdStatusAutorizacion() == 3) {
            //Sin estatus 0
            //Autorizada 3
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    //textView.setText("Reprogramar");
                    textView.setText(datosDenuncia.getStatusAutorizacion());
                } else {
                    cerraFase(cardView, faseActivaDatos, listener);
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

    public void avanceIniciarFase(TextView textView, ImageView imageView, CardView cardView, FaseActivaDatos faseActivaDatos, DatosDenuncia datosDenuncia, final FasesAdapter.OnItemClickListener listener) {
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        if (faseActivaDatos.getIdEtapa().equals(1)) {
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    reprogramacionAntesDeIniciarFase(textView, imageView, cardView, faseActivaDatos, datosDenuncia, listener);
                } else {
                    iniciarFase(cardView, faseActivaDatos, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (faseActivaDatos.getIdEtapa().equals(2)) {
            imageView.setVisibility(View.VISIBLE);
            //textView.setVisibility(View.GONE);
            try {
                if (Utils.isValidDate(Utils.cambiarFechayyyyMMdd(datosDenuncia.getFechaCompromiso()))) {
                    reprogramacionAntesDeIniciarFase(textView, imageView, cardView, faseActivaDatos, datosDenuncia, listener);
                } else {
                    cerraFase(cardView, faseActivaDatos, listener);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (faseActivaDatos.getIdEtapa().equals(3)) {
            textView.setVisibility(View.GONE);
        }
    }

    public void iniciarFase(CardView cardView, FaseActivaDatos faseActivaDatos, final FasesAdapter.OnItemClickListener listener) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIniciarFase(faseActivaDatos, getAdapterPosition());
            }
        });
    }

    public void cerraFase(CardView cardView, FaseActivaDatos faseActivaDatos, final FasesAdapter.OnItemClickListener listener) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCerrarFase(faseActivaDatos, getAdapterPosition());
            }
        });
    }
}
