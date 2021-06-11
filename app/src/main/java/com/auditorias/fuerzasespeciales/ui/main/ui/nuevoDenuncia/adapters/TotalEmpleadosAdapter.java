package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.request.ResponsablesResquest;
import com.auditorias.fuerzasespeciales.ui.main.ui.nuevoDenuncia.holders.TotalEmpleadosViewHolder;

import java.util.List;

public class TotalEmpleadosAdapter extends RecyclerView.Adapter<TotalEmpleadosViewHolder> {

    private final List<ResponsablesResquest> listCA;
    private final LayoutInflater inflater;
    private final OnClickListener itemClickListener;

    public TotalEmpleadosAdapter(Activity activity, List<ResponsablesResquest> listCA, OnClickListener itemClickListener) {
        this.listCA = listCA;
        this.itemClickListener = itemClickListener;
        inflater = LayoutInflater.from(activity);
    }

    public List<ResponsablesResquest> getResponsablesResquest() {
        return listCA;
    }

    @NonNull
    @Override
    public TotalEmpleadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_total_responsables_item, parent, false);
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

    public interface OnClickListener {
        void onItemClick(ResponsablesResquest responsablesResquest, int position);

        void onClickDelete(ResponsablesResquest responsablesResquest, int position);
    }

}