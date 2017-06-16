package com.hmelizarraraz.petagram.presentador;

import android.content.Context;

import com.hmelizarraraz.petagram.view.IMascotasFavoritasView;
import com.hmelizarraraz.petagram.db.ConstructorMascotasFavoritas;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotasFavoritas constructorMascotasFavoritas;
    private ArrayList<Mascota> mascotas;

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context) {
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasFavoritasBD();
    }


    @Override
    public void obtenerMascotasFavoritasBD() {
        constructorMascotasFavoritas = new ConstructorMascotasFavoritas(context);
        mascotas = constructorMascotasFavoritas.obtenerDatos();
        mostrarMascotasFavoritasRV();
    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasView.generarToolbar();
        iMascotasFavoritasView.inicializarAdaptador(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
