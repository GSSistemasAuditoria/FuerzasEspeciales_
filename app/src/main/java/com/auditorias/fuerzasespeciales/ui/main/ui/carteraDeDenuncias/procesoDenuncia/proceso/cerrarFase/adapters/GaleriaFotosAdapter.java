package com.auditorias.fuerzasespeciales.ui.main.ui.carteraDeDenuncias.procesoDenuncia.proceso.cerrarFase.adapters;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auditorias.fuerzasespeciales.R;
import com.auditorias.fuerzasespeciales.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

public class GaleriaFotosAdapter extends RecyclerView.Adapter<GaleriaFotosAdapter.GaleriaFotosViewHolder> {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final OnListener onListener;
    private FragmentManager fm;
    private List<String> getList;

    public GaleriaFotosAdapter(Activity activity, List<String> list, OnListener onListener) {
        this.activity = activity;
        this.getList = list;
        this.onListener = onListener;
        inflater = LayoutInflater.from(activity);
    }

    public List<String> getList() {
        return getList;
    }

    @NonNull
    @Override
    public GaleriaFotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cell_galeria_fotos_item, parent, false);
        return new GaleriaFotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaFotosViewHolder holder, int position) {
        holder.bind(activity, getList.get(position), onListener);
    }

    @Override
    public int getItemCount() {
        return getList.size();
    }

    public interface OnListener {
        void onItemSelectedListener(String fotoString , int posicion);
    }

    static class GaleriaFotosViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView3;
        GaleriaFotosViewHolder(View view) {
            super(view);
            imageView3 = view.findViewById(R.id.imageView3);
        }

        //TODO: llenado de los items que se encuentran dentro del recyclerview
        public void bind(Activity activity, String string, OnListener onListener) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Glide.with(activity).load(Utils.base64ToBitmap(string)).fitCenter().into(imageView3);
            }else {
                Glide.with(activity).load(Utils.base64ToBitmap(string)).fitCenter().into(imageView3);
            }
            imageView3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onListener.onItemSelectedListener(string, getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
