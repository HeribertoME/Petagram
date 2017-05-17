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

    public ArrayList<Mascota> obtenerDatos() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        mascotas.add(new Mascota(1, "Catty", 5, R.drawable.gato_2));
        mascotas.add(new Mascota(2, "Catty", 0, R.drawable.gato_1));
        mascotas.add(new Mascota(3, "Catty", 3, R.drawable.gato_1));
        mascotas.add(new Mascota(4, "Catty", 10, R.drawable.gato_1));
        mascotas.add(new Mascota(5, "Catty", 2, R.drawable.gato_2));
        mascotas.add(new Mascota(6, "Catty", 3, R.drawable.gato_1));
        mascotas.add(new Mascota(7, "Catty", 6, R.drawable.gato_2));
        mascotas.add(new Mascota(8, "Catty", 7, R.drawable.gato_2));
        mascotas.add(new Mascota(9, "Catty", 8, R.drawable.gato_1));
        mascotas.add(new Mascota(10, "Catty", 12, R.drawable.gato_2));
        mascotas.add(new Mascota(1, "Catty", 4, R.drawable.gato_2));
        mascotas.add(new Mascota(12, "Catty", 1, R.drawable.gato_1));

        return mascotas;
    }
}
