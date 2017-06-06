package com.hmelizarraraz.petagram.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 04/05/17.
 */

public class PerfilMascotaAdapter extends RecyclerView.Adapter<PerfilMascotaAdapter.PerfilMascotaViewHolder>{

    private ArrayList<Mascota> mascotas;

    public PerfilMascotaAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_mascota, parent, false);
        return new PerfilMascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilMascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        //holder.imgFotoPerfilCV.setImageResource(mascota.getFoto());
        //holder.tvRatingMascotaPerfilCV.setText(mascota.getRating()+"");

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFotoPerfilCV;
        private TextView tvRatingMascotaPerfilCV;


        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoPerfilCV         = (ImageView) itemView.findViewById(R.id.imgFotoPerfilCV);
            tvRatingMascotaPerfilCV = (TextView) itemView.findViewById(R.id.tvRatingMascotaPerfilCV);
        }
    }
}
