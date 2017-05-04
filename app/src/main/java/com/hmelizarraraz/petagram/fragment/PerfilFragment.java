package com.hmelizarraraz.petagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.PerfilMascotaAdapter;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView recyclerView;
    private GridLayoutManager glm;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvPerfil);

        glm = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(glm);

        inicializarMascotas();
        inicializarAdaptador();

        return view;
    }

    private void inicializarMascotas() {
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Catty", 5, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 0, R.drawable.gato_1));
        mascotas.add(new Mascota("Catty", 3, R.drawable.gato_1));
        mascotas.add(new Mascota("Catty", 10, R.drawable.gato_1));
        mascotas.add(new Mascota("Catty", 2, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 3, R.drawable.gato_1));
        mascotas.add(new Mascota("Catty", 6, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 7, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 8, R.drawable.gato_1));
        mascotas.add(new Mascota("Catty", 12, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 4, R.drawable.gato_2));
        mascotas.add(new Mascota("Catty", 1, R.drawable.gato_1));

    }

    private void inicializarAdaptador() {
        PerfilMascotaAdapter adapter = new PerfilMascotaAdapter(mascotas);
        recyclerView.setAdapter(adapter);
    }

}