package com.hmelizarraraz.petagram.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.presentador.IMascotasFavoritasPresenter;
import com.hmelizarraraz.petagram.presentador.MascotasFavoritasPresenter;
import com.hmelizarraraz.petagram.view.IMascotasFavoritasView;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritasView {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MascotaAdaptador adaptador;
    private IMascotasFavoritasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        toolbar = (Toolbar) findViewById(R.id.miActionbar);
        recyclerView = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);

        presenter = new MascotasFavoritasPresenter(this, getApplicationContext());
    }


    @Override
    public void generarToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador adaptador) {
        recyclerView.setAdapter(adaptador);
    }
}
