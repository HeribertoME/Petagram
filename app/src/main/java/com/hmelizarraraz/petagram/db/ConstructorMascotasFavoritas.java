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
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFavoritas();
    }
}
