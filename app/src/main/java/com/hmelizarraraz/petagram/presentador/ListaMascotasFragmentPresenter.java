package com.hmelizarraraz.petagram.presentador;

import android.content.Context;

import com.hmelizarraraz.petagram.db.ConstructorMascotas;
import com.hmelizarraraz.petagram.fragment.IListaMascotasFragmentView;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public class ListaMascotasFragmentPresenter implements IListaMascotasFragmentPresenter {

    private IListaMascotasFragmentView iListaMascotasFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ListaMascotasFragmentPresenter(IListaMascotasFragmentView iListaMascotasFragmentView, Context context) {
        this.iListaMascotasFragmentView = iListaMascotasFragmentView;
        this.context = context;
        obtenerContactosBD();
    }

    @Override
    public void obtenerContactosBD() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iListaMascotasFragmentView.inicializarAdaptador(iListaMascotasFragmentView.crearAdaptador(mascotas));
        iListaMascotasFragmentView.generarLinearLayout();
    }
}
