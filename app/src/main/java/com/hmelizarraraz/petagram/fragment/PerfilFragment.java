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
import com.hmelizarraraz.petagram.presentador.IPerfilFragmentPresenter;
import com.hmelizarraraz.petagram.presentador.PerfilFragmentPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView{

    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private PerfilMascotaAdapter adapter;
    private IPerfilFragmentPresenter presenter;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvPerfil);

        presenter = new PerfilFragmentPresenter(this, getContext());

        return view;
    }

    @Override
    public void generarGridLayout(int columnas) {
        glm = new GridLayoutManager(getActivity(), columnas);
        recyclerView.setLayoutManager(glm);
    }

    @Override
    public PerfilMascotaAdapter crearAdaptadorPerfilMascota(ArrayList<Mascota> mascotas) {
        adapter = new PerfilMascotaAdapter(mascotas);
        return adapter;
    }

    @Override
    public void inicializarAdapatadorPerfilMascota(PerfilMascotaAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
