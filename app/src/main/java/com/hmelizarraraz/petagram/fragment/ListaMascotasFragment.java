package com.hmelizarraraz.petagram.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.presentador.IListaMascotasFragmentPresenter;
import com.hmelizarraraz.petagram.presentador.ListaMascotasFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by heriberto on 03/05/17.
 */

public class ListaMascotasFragment extends Fragment implements IListaMascotasFragmentView{

    private RecyclerView listaMascotas;
    private MascotaAdaptador adaptador;
    private SwipeRefreshLayout srlMiSwipe;
    private IListaMascotasFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_mascotas, container, false);

        listaMascotas = (RecyclerView) view.findViewById(R.id.rvMascotas);
        srlMiSwipe = (SwipeRefreshLayout) view.findViewById(R.id.srlMiSwipe);

        presenter = new ListaMascotasFragmentPresenter(this, getContext());

        srlMiSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Toast.makeText(view.getContext(), "Do something", Toast.LENGTH_SHORT).show();
                presenter.obtenerFollowers();
                srlMiSwipe.setRefreshing(false);
            }
        });

        return view;
    }


    @Override
    public void generarLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        adaptador = new MascotaAdaptador(mascotas, getActivity());

        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador mascotaAdaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
