package com.auditorias.fuerzasespeciales.ui.main.ui.inicio.faseProcesoDenuncia.detalleDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.models.detalleDenuncia.DetalleDenunciaResponsables;

import java.util.List;

public class DetalleEmpleadosResponsablesAdapter extends RecyclerView.Adapter<DetalleEmpleadosResponsablesAdapter.DetalleEmpleadosResponsablesViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDenunciaResponsables> list;
    private final LayoutInflater inflater;

    public DetalleEmpleadosResponsablesAdapter(Activity activity, List<DetalleDenunciaResponsables> list/*, FragmentManager fm*/) {
        this.activity = activity;
        //this.fm = fm;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DetalleEmpleadosResponsablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_detalle_responsables_item, parent, false);
        return new DetalleEmpleadosResponsablesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetalleEmpleadosResponsablesViewHolder holder, final int position) {
        holder.bind(activity, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DetalleEmpleadosResponsablesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreEmpleadoTextCDR;
        TextView textViewNombreEmpleadoCDR;
        TextView textViewNumeroEmpleadoTextCDR;
        TextView textViewNumeroEmpleadoCDR;
        TextView textViewCecoEmpleadoTextCDR;
        TextView textViewCecoEmpleadoCDR;
        TextView textViewFaseAgregadoCDR;
        TextView textViewEstatusResponsableCDR;
        TextView textViewTipoEmpleadoCDF;

        DetalleEmpleadosResponsablesViewHolder(View view) {
            super(view);
            textViewNombreEmpleadoTextCDR = view.findViewById(R.id.textViewNombreEmpleadoTextCDR);
            textViewNombreEmpleadoCDR = view.findViewById(R.id.textViewNombreEmpleadoCDR);
            textViewNumeroEmpleadoTextCDR = view.findViewById(R.id.textViewNumeroEmpleadoTextCDR);
            textViewNumeroEmpleadoCDR = view.findViewById(R.id.textViewNumeroEmpleadoCDR);
            textViewCecoEmpleadoTextCDR = view.findViewById(R.id.textViewCecoEmpleadoTextCDR);
            textViewCecoEmpleadoCDR = view.findViewById(R.id.textViewCecoEmpleadoCDR);
            textViewFaseAgregadoCDR = view.findViewById(R.id.textViewFaseAgregadoCDR);
            textViewEstatusResponsableCDR = view.findViewById(R.id.textViewEstatusResponsableCDR);
            textViewTipoEmpleadoCDF = view.findViewById(R.id.textViewTipoEmpleadoCDF);
        }

        public void bind(Activity activity, DetalleDenunciaResponsables detalleDenunciaResponsables) {
            textViewNombreEmpleadoCDR.setText(detalleDenunciaResponsables.getNombre());

            if (detalleDenunciaResponsables.getIdEmpleado() != null){
                textViewNumeroEmpleadoCDR.setText(String.valueOf(detalleDenunciaResponsables.getIdEmpleado()));
            }else {
                textViewNumeroEmpleadoTextCDR.setVisibility(View.GONE);
                textViewNumeroEmpleadoCDR.setVisibility(View.GONE);
            }

            if (detalleDenunciaResponsables.getCeco() != null){
                textViewCecoEmpleadoCDR.setText(String.valueOf(detalleDenunciaResponsables.getCeco()));
            }else {
                textViewCecoEmpleadoTextCDR.setVisibility(View.GONE);
                textViewCecoEmpleadoCDR.setVisibility(View.GONE);
            }

            textViewFaseAgregadoCDR.setText(detalleDenunciaResponsables.getFaseAgregado());
            textViewEstatusResponsableCDR.setText(detalleDenunciaResponsables.getStatusResponsable());
            textViewTipoEmpleadoCDF.setText(detalleDenunciaResponsables.getTipoEmpleado());

        }
    }
}
