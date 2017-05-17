package com.hmelizarraraz.petagram.presentador;

import android.content.Context;

import com.hmelizarraraz.petagram.db.ConstructorMascotasFavoritas;
import com.hmelizarraraz.petagram.fragment.IPerfilFragmentView;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorMascotasFavoritas constructorMascotasFavoritas;
    private ArrayList<Mascota> mascotas;

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
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
        iPerfilFragmentView.inicializarAdapatadorMascotasFavs(iPerfilFragmentView.crearAdaptadorMascotasFavs(mascotas));
        iPerfilFragmentView.generarGridLayout(3);
    }
}
