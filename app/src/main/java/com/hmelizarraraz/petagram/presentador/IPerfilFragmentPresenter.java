package com.hmelizarraraz.petagram.presentador;

import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public interface IPerfilFragmentPresenter {

    void obtenerMascotasPerfilBD();
    void obtenerMascotasPerfilWS();
    boolean checarConfiguracion();
    String obtenerSharedPreference();
    void obtenerUsuarioByUsername(String username);
    void obtenerMediaUser(String userId);
    void agregarMascotas(ArrayList<Mascota> listaMascotas);
    void mostrarMascotasPerfilRV();

}
