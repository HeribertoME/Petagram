package com.hmelizarraraz.petagram.presentador;

/**
 * Created by heriberto on 16/06/17.
 */

public interface IMainActivityPresenter {

    void enviarTokenRegistro();
    boolean checarConfiguracion();
    String obtenerIdUsuario();
    void registrarUsuario(String token, String idUser);
}
