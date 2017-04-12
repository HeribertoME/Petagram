package com.hmelizarraraz.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.model.Mascota;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotasFavoritas;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        toolbar = (Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        inicializarMascotasFavoritas();
        inicializarAdaptadorMascotasFavoritas();

    }

    public void inicializarMascotasFavoritas(){
        mascotasFavoritas = new ArrayList<Mascota>();

        mascotasFavoritas.add(new Mascota("Nico", 10, R.drawable.gato_2));
        mascotasFavoritas.add(new Mascota("Catty", 5, R.drawable.gato_1));
        mascotasFavoritas.add(new Mascota("Puppy", 4, R.drawable.perro_1));
        mascotasFavoritas.add(new Mascota("Feo", 2, R.drawable.perro_2));
        mascotasFavoritas.add(new Mascota("Güero", 1, R.drawable.perro_3));
    }

    public void inicializarAdaptadorMascotasFavoritas(){

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFavoritas, this);
        recyclerView.setAdapter(adaptador);

    }
}
