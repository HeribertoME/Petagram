package com.hmelizarraraz.petagram.presentador;

import android.content.Context;

import com.hmelizarraraz.petagram.db.ConstructorPerfilMascota;
import com.hmelizarraraz.petagram.fragment.IPerfilFragmentView;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorPerfilMascota constructorPerfilMascota;
    private ArrayList<Mascota> mascotas;

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        obtenerMascotasPerfilBD();
    }

    @Override
    public void obtenerMascotasPerfilBD() {
        constructorPerfilMascota = new ConstructorPerfilMascota(context);
        mascotas = constructorPerfilMascota.obtenerDatos();
        mostrarMascotasPerfilRV();
    }

    @Override
    public void mostrarMascotasPerfilRV() {
        iPerfilFragmentView.inicializarAdapatadorPerfilMascota(iPerfilFragmentView.crearAdaptadorPerfilMascota(mascotas));
        iPerfilFragmentView.generarGridLayout(3);
    }
}
