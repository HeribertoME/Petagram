package com.hmelizarraraz.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTA +"(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT," +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO+" INTEGER" +
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA+" INTEGER," +
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_NUMERO_LIKES+" INTEGER," +
                "FOREIGN KEY(" + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA + ")" +
                " REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMacotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String queryConsultarMascotas = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(queryConsultarMascotas, null);

        /*
        while (registros.moveToNext()) {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_NUMERO_LIKES + ") " +
                    " as likes FROM " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES +
                    " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA + " = " + mascotaActual.getId();

            Cursor likes = db.rawQuery(queryLikes, null);

            if (likes.moveToNext()){
                mascotaActual.setRating(likes.getInt(0));
            } else {
                mascotaActual.setRating(0);
            }

            mascotas.add(mascotaActual);
        }
        */

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, values);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_LIKES, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES +
                " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA + " = " + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas() {
        ArrayList<Mascota> favoritas = new ArrayList<>();

        String consultaFavs = "SELECT " + ConstantesBaseDatos.TABLE_MASCOTA + "." + ConstantesBaseDatos.TABLE_MASCOTA_ID + ", " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + ", " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + ", " +
                " COUNT(" + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_NUMERO_LIKES + ") as likes" +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                " INNER JOIN " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES +
                " ON " + ConstantesBaseDatos.TABLE_MASCOTA + "." + ConstantesBaseDatos.TABLE_MASCOTA_ID + "=" +
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES + "." + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA +
                " GROUP BY " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA +
                " ORDER BY likes " +
                " DESC LIMIT " + ConstantesBaseDatos.CONSULTA_FAVS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(consultaFavs, null);

        /*
        while (registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));
            mascota.setRating(registros.getInt(3));

            favoritas.add(mascota);
        }
        */

        db.close();
        return favoritas;
    }
}
