package com.hmelizarraraz.petagram.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 03/05/17.
 */

public class ListaMascotasFragment extends Fragment {

    private RecyclerView listaMascotas;
    private ArrayList<Mascota> mascotas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_mascotas, container, false);

        listaMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();


        return view;
    }

    public void inicializarMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Catty", 5, R.drawable.gato_1));
        mascotas.add(new Mascota("Nico", 10, R.drawable.gato_2));
        mascotas.add(new Mascota("Puppy", 4, R.drawable.perro_1));
        mascotas.add(new Mascota("Feo", 2, R.drawable.perro_2));
        mascotas.add(new Mascota("Güero", 1, R.drawable.perro_3));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador mascotaAdaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(mascotaAdaptador);
    }


}
