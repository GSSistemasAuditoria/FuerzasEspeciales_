package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.detalleDenuncia.adapters;

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

public class TotalEmpleadosResponsablesDCAadpter extends RecyclerView.Adapter<TotalEmpleadosResponsablesDCAadpter.TotalEmpleadosResponsablesViewHolder> {

    private final Activity activity;
    //private final FragmentManager fm;
    private final List<DetalleDenunciaResponsables> list;
    private final LayoutInflater inflater;

    public TotalEmpleadosResponsablesDCAadpter(Activity activity, List<DetalleDenunciaResponsables> list/*, FragmentManager fm*/) {
        this.activity = activity;
        //this.fm = fm;
        this.list = list;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public TotalEmpleadosResponsablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_total_empleados_responsables_detalle_caso_adapter, parent, false);
        return new TotalEmpleadosResponsablesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TotalEmpleadosResponsablesViewHolder holder, final int position) {
        if (list.get(position).getCeco() == null || String.valueOf(list.get(position).getCeco()).isEmpty()){
            holder.textViewNumeroEmpeadoTERCA.setVisibility(View.GONE);
            holder.textViewNumeroEmpeadoTERCA.setText(String.valueOf(list.get(position).getCeco()));
        }else {
            holder.textViewNumeroEmpeadoTERCA.setVisibility(View.VISIBLE);
            holder.textViewNumeroEmpeadoTERCA.setText(String.valueOf(list.get(position).getCeco()));
        }

        holder.textViewNombreEmpeadoTERCA.setText(list.get(position).getNombre());
        holder.textViewTipoEmpeadoTERCA.setText(list.get(position).getTipoEmpleado());

        /*holder.textView5.setText(list.get(position).getDescripcion());
        if (list.get(position).getId() == 1){
            holder.cardViewLegalProcess.setCardBackgroundColor(activity.getColor(R.color.bluePrimary));
            holder.button.setVisibility(View.GONE);
        }else {
            holder.button.setVisibility(View.GONE);
            holder.textView5.setTextColor(activity.getColor(R.color.BlueGrey500));
            holder.cardViewLegalProcess.setCardBackgroundColor(activity.getColor(R.color.blueGrey50));
        }

        holder.cardViewLegalProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("complaintsModel", list.get(position));
                Navigation.findNavController(v).navigate(R.id.action_StatusComplaintFragment_to_reporgramadasFragment, bundle);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TotalEmpleadosResponsablesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumeroEmpeadoTERCA;
        TextView textViewNombreEmpeadoTERCA;
        TextView textViewTipoEmpeadoTERCA;

        TotalEmpleadosResponsablesViewHolder(View view) {
            super(view);
            textViewNumeroEmpeadoTERCA = view.findViewById(R.id.textViewNumeroEmpledoImputadoCIE);
            textViewNombreEmpeadoTERCA = view.findViewById(R.id.textViewNombreImputadoCIE);
            textViewTipoEmpeadoTERCA = view.findViewById(R.id.textViewTipoImputadoCIE);
        }
    }
}
