package com.auditorias.fuerzasespeciales.ui.main.ui.busqueda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.ui.main.ui.busqueda.ViewUtils;
import com.auditorias.fuerzasespeciales.ui.main.ui.busqueda.modela.DataModal;
import com.auditorias.fuerzasespeciales.ui.main.ui.busqueda.modela.Level;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    List<DataModal> data = new ArrayList<>();
    Context mContext;

    public RvAdapter(Context con) {
        mContext = con;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RvViewHolder(LayoutInflater.from(mContext).inflate(R.layout.example_cell_multiple, null));
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        DataModal dataModal = data.get(position);
        holder.tv.setText(dataModal.getName());
        holder.setLevel(dataModal.getLevel());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(DataModal item) {
        data.add(item);
    }

    class RvViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        View itemView;
        View marker;

        public RvViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv = (TextView) itemView.findViewById(R.id.rv_item_tv);
            marker = itemView.findViewById(R.id.marker);
        }

        public void setLevel(int level) {
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT
            );

            if (level == Level.LEVEL_TWO) {
                params.setMarginStart(ViewUtils.getLevelOneMargin());
                marker.setBackground(ContextCompat.getDrawable(mContext, R.drawable.marker_c));
            } else if (level == Level.LEVEL_THREE) {
                params.setMarginStart(ViewUtils.getLevelTwoMargin());
                marker.setBackground(ContextCompat.getDrawable(mContext, R.drawable.marker_cc));
            }

            itemView.setLayoutParams(params);
        }
    }
}

