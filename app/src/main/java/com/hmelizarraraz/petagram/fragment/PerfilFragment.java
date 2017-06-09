package com.hmelizarraraz.petagram.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.PerfilMascotaAdapter;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.presentador.IPerfilFragmentPresenter;
import com.hmelizarraraz.petagram.presentador.PerfilFragmentPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView{

    private RecyclerView recyclerView;
    private TextView tvNombrePerfil;
    private ImageView imgProfile;
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
        View vSnack = getActivity().findViewById(android.R.id.content);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvPerfil);
        tvNombrePerfil = (TextView) view.findViewById(R.id.tvNombrePerfil);
        imgProfile = (ImageView) view.findViewById(R.id.imgProfile);

        presenter = new PerfilFragmentPresenter(this, getContext(), vSnack);

        return view;
    }

    @Override
    public void generarGridLayout(int columnas) {
        glm = new GridLayoutManager(getActivity(), columnas);
        recyclerView.setLayoutManager(glm);
    }

    @Override
    public PerfilMascotaAdapter crearAdaptadorPerfilMascota(ArrayList<Mascota> mascotas) {
        adapter = new PerfilMascotaAdapter(mascotas, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdapatadorPerfilMascota(PerfilMascotaAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void cambiarNombrePerfil(String username) {
        tvNombrePerfil.setText(username);
    }

    @Override
    public void cambiarFotoPerfil(String url) {
        Picasso.with(getContext()).load(url).into(imgProfile);
    }


}
