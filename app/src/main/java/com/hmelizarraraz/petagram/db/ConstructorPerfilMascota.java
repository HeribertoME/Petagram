package com.hmelizarraraz.petagram.db;

import android.content.Context;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class ConstructorPerfilMascota {

    private Context context;

    public ConstructorPerfilMascota(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        ArrayList<Mascota> mascotas = new ArrayList<>();


        mascotas.add(new Mascota());

        return mascotas;

    }
}
