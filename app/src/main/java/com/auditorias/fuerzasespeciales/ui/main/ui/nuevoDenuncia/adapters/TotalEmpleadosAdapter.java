package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;

import java.util.List;

public class TotalEmpleadosAdapter extends RecyclerView.Adapter<TotalEmpleadosAdapter.TotalEmpleadosViewHolder> {

    private final Activity activityCA;
    private final List<ResponsablesResquest> listCA;
    private final LayoutInflater inflater;
    private OnClickListener itemClickListener;

    public TotalEmpleadosAdapter(Activity activityCA, List<ResponsablesResquest> listCA, OnClickListener itemClickListener) {
        this.activityCA = activityCA;
        this.listCA = listCA;
        this.itemClickListener  = itemClickListener;
        inflater = LayoutInflater.from(activityCA);
    }

    public List<ResponsablesResquest> getResponsablesResquest() {
        return listCA;
    }

    @NonNull
    @Override
    public TotalEmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_total_empleados_responsables_detalle_caso_adapter, parent, false);
        return new TotalEmpleadosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TotalEmpleadosViewHolder holder, final int position) {
        holder.bind(listCA.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listCA.size();
    }

    class TotalEmpleadosViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNumeroEmpeadoTERC;
        TextView textViewNombreEmpeadoTERC;
        TextView textViewTipoEmpeadoTERC;
        ImageView imageViewDeleteTERC;

        TotalEmpleadosViewHolder(View view) {
            super(view);
            textViewNumeroEmpeadoTERC = view.findViewById(R.id.textViewNumeroEmpledoImputadoCIE);
            textViewNombreEmpeadoTERC = view.findViewById(R.id.textViewNombreImputadoCIE);
            textViewTipoEmpeadoTERC = view.findViewById(R.id.textViewTipoImputadoCIE);
            imageViewDeleteTERC = view.findViewById(R.id.imageViewDeleteTERC);
        }

        public void bind(final ResponsablesResquest responsablesResquest, final OnClickListener itemClickListener) {
            textViewNumeroEmpeadoTERC.setText(String.valueOf(responsablesResquest.getIdEmpleado()));
            textViewNombreEmpeadoTERC.setText(responsablesResquest.getNombre());

            if (String.valueOf(responsablesResquest.getIdEmpleado()).isEmpty() || responsablesResquest.getIdEmpleado() == null) {
                textViewNumeroEmpeadoTERC.setVisibility(View.GONE);
                if (responsablesResquest.getIdTipoEmpleado() == 1) {
                    textViewTipoEmpeadoTERC.setText("Interno");
                } else if (responsablesResquest.getIdTipoEmpleado() == 2) {
                    textViewTipoEmpeadoTERC.setText("Externo");
                }
            } else {
                textViewNumeroEmpeadoTERC.setVisibility(View.VISIBLE);
                textViewTipoEmpeadoTERC.setVisibility(View.VISIBLE);
                if (responsablesResquest.getIdTipoEmpleado() == 1) {
                    textViewTipoEmpeadoTERC.setText("Interno");
                } else if (responsablesResquest.getIdTipoEmpleado() == 2) {
                    textViewTipoEmpeadoTERC.setText("Externo");
                }
            }

            imageViewDeleteTERC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickDelete(responsablesResquest, getAdapterPosition());
                }
            });
        }


    }

    public interface OnClickListener {
        void onItemClick(ResponsablesResquest responsablesResquest, int position);
        void onClickDelete(ResponsablesResquest responsablesResquest, int position);
    }
}