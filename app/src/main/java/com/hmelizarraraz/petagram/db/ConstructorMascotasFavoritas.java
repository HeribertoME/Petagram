package com.hmelizarraraz.petagram.db;

import android.content.Context;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class ConstructorMascotasFavoritas {

    private Context context;

    public ConstructorMascotasFavoritas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        mascotas.add(new Mascota(1, "Nico", 10, R.drawable.gato_2));
        mascotas.add(new Mascota(2, "Catty", 5, R.drawable.gato_1));
        mascotas.add(new Mascota(3, "Puppy", 4, R.drawable.perro_1));
        mascotas.add(new Mascota(4, "Feo", 2, R.drawable.perro_2));
        mascotas.add(new Mascota(5, "Güero", 1, R.drawable.perro_3));


        return mascotas;
    }
}
