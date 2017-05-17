package com.hmelizarraraz.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.hmelizarraraz.petagram.R;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerMacotas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Catty");
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.gato_1);

        db.insertarMascota(values);

        values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Nico");
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.gato_2);

        db.insertarMascota(values);

        values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Puppy");
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro_1);

        db.insertarMascota(values);

        values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Feo");
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro_2);

        db.insertarMascota(values);

        values = new ContentValues();
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Güero");
        values.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro_3);

        db.insertarMascota(values);
    }

    public void darLike(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_LIKES_NUMERO_LIKES, LIKE);

        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

}
