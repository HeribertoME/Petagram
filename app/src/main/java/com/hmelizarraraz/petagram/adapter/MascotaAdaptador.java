package com.hmelizarraraz.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.model.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 11/04/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        holder.imgFotoCV.setImageResource(mascota.getFoto());
        holder.tvNombreMascotaCV.setText(mascota.getNombre());
        holder.tvRatingMascotaCV.setText(mascota.getRating()+"");
        
        holder.icRateMascotaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Liked to " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoCV;
        private TextView tvNombreMascotaCV;
        private TextView tvRatingMascotaCV;
        private ImageView icRateMascotaCV;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoCV           = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvNombreMascotaCV   = (TextView) itemView.findViewById(R.id.tvNombreMascotaCV);
            tvRatingMascotaCV   = (TextView) itemView.findViewById(R.id.tvRatingMascotaCV);
            icRateMascotaCV     = (ImageView) itemView.findViewById(R.id.icRateMascotaCV);
        }
    }
}
