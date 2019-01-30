package com.hmelizarraraz.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.presentador.ILikePresenter;
import com.hmelizarraraz.petagram.presentador.LikePresenter;
import com.hmelizarraraz.petagram.presentador.ListaMascotasFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by heriberto on 11/04/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;
    private ILikePresenter presenter;

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
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);

        //holder.imgFotoCV.setImageResource(mascota.getFoto());
        Picasso.get().load(mascota.getUrlFoto()).into(holder.imgFotoCV);
        holder.tvNombreMascotaCV.setText(mascota.getTextoFoto());
        holder.tvRatingMascotaCV.setText(String.valueOf(mascota.getLikes()));

        holder.imgRatingMascotaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, "Id user: " + mascota.getId(), Toast.LENGTH_SHORT).show();
                holder.tvRatingMascotaCV.setText(String.valueOf(mascota.getLikes() + 1));
                presenter = new LikePresenter(activity.getApplicationContext(), mascota.getIdFoto(), mascota.getId());
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
        private ImageView imgRatingMascotaCV;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoCV           = (ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvNombreMascotaCV   = (TextView) itemView.findViewById(R.id.tvNombreMascotaCV);
            tvRatingMascotaCV   = (TextView) itemView.findViewById(R.id.tvRatingMascotaCV);
            imgRatingMascotaCV  = (ImageView) itemView.findViewById(R.id.imgRatingMascotaCV);
        }
    }
}
