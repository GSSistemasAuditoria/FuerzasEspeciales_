package com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.holders;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.notificaciones.DataNotificacion;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters.DenunciasPorVencerAdapter;
import com.auditorias.fuerzasespeciales.ui.main.ui.notificaciones.adapters.ReprogramacionesAdapter;
import com.auditorias.fuerzasespeciales.webServicies.Constantes;
import com.squareup.picasso.Picasso;

public class ReprogramacionesHolder  extends RecyclerView.ViewHolder {

    CardView cardviewNotificaciones;
    ImageView imageView2;
    TextView textView11;
    TextView textView22;

    public ReprogramacionesHolder(View view) {
        super(view);
        cardviewNotificaciones = view.findViewById(R.id.cardviewNotificaciones);
        imageView2 = view.findViewById(R.id.imageView2);
        textView11 = view.findViewById(R.id.textView11);
        textView22 = view.findViewById(R.id.textView22);

    }

    public void bind(Activity activity, final DataNotificacion notificacion, final ReprogramacionesAdapter.OnClickListener listener) {
        if (notificacion.getIcono() != null) {
            Picasso.get().load(notificacion.getIcono().replace("/..", Constantes.BASE_URL_IMAGE)).into(imageView2);
        } else {
            imageView2.setImageDrawable(activity.getDrawable(R.drawable.sifra_1));
        }
        textView11.setText(notificacion.getDescripcion());
        textView22.setText(notificacion.getFechaMod());

        cardviewNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(notificacion, getAdapterPosition());
            }
        });
    }
}