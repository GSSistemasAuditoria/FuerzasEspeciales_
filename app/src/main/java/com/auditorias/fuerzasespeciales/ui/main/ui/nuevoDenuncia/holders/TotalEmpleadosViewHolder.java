package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters.TotalEmpleadosAdapter;

public class TotalEmpleadosViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNumeroEmpledoResponsablesCTRI;
    TextView textViewNombreResponsablesCTRI;
    TextView textViewTipoResponsableCTRI;
    ImageView imageViewBorrarResponsablesCTRI;

    public TotalEmpleadosViewHolder(View view) {
        super(view);
        textViewNumeroEmpledoResponsablesCTRI = view.findViewById(R.id.textViewNumeroEmpledoResponsablesCTRI);
        textViewNombreResponsablesCTRI = view.findViewById(R.id.textViewNombreResponsablesCTRI);
        textViewTipoResponsableCTRI = view.findViewById(R.id.textViewTipoResponsableCTRI);
        imageViewBorrarResponsablesCTRI = view.findViewById(R.id.imageViewBorrarResponsablesCTRI);
    }

    public void bind(final ResponsablesResquest responsablesResquest, final TotalEmpleadosAdapter.OnClickListener itemClickListener) {
        textViewNumeroEmpledoResponsablesCTRI.setText(String.valueOf(responsablesResquest.getIdEmpleado()));
        textViewNombreResponsablesCTRI.setText(responsablesResquest.getNombre());

        if (String.valueOf(responsablesResquest.getIdEmpleado()).isEmpty() || responsablesResquest.getIdEmpleado() == null) {
            textViewNumeroEmpledoResponsablesCTRI.setVisibility(View.GONE);
            if (responsablesResquest.getIdTipoEmpleado() == 1) {
                textViewTipoResponsableCTRI.setText("Interno");
            } else if (responsablesResquest.getIdTipoEmpleado() == 2) {
                textViewTipoResponsableCTRI.setText("Externo");
            }
        } else {
            textViewNumeroEmpledoResponsablesCTRI.setVisibility(View.VISIBLE);
            textViewTipoResponsableCTRI.setVisibility(View.VISIBLE);
            if (responsablesResquest.getIdTipoEmpleado() == 1) {
                textViewTipoResponsableCTRI.setText("Interno");
            } else if (responsablesResquest.getIdTipoEmpleado() == 2) {
                textViewTipoResponsableCTRI.setText("Externo");
            }
        }

        imageViewBorrarResponsablesCTRI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClickDelete(responsablesResquest, getAdapterPosition());
            }
        });
    }


}