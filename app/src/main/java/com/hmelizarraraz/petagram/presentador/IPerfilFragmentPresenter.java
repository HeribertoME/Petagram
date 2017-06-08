package com.hmelizarraraz.petagram.presentador;

/**
 * Created by heriberto on 17/05/17.
 */

public interface IPerfilFragmentPresenter {

    void obtenerMascotasPerfilBD();
    void mostrarMascotasPerfilRV();
    void obtenerUsuarioByUsername();
    String obtenerSharedPreference();
}
