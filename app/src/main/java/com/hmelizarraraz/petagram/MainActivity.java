package com.hmelizarraraz.petagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    Toolbar toolbar;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(toolbar);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()){

            case R.id.mFav:

                intent = new Intent(MainActivity.this, MascotasFavoritas.class);
                break;

            case R.id.mContacto:

                intent = new Intent(MainActivity.this, ContactoActivity.class);
                break;

            case R.id.mAbout:

                intent = new Intent(MainActivity.this, AcercaDeActivity.class);
                break;

            default:
                break;

        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
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
        MascotaAdaptador mascotaAdaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(mascotaAdaptador);
    }

}
