package com.hmelizarraraz.petagram.presentador;

import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public interface IListaMascotasFragmentPresenter {

    void obtenerMascotasBD();
    void mostrarMascotasRV();
    void obtenerFollowers();
    void obtenerMediaFollower(String id);
    void agregarMascotas(ArrayList<Mascota> listaMascotas);
}
