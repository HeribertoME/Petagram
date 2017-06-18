package com.hmelizarraraz.petagram.presentador;

/**
 * Created by heriberto on 17/06/17.
 */

public interface ILikePresenter {

    void init();
    void darLikeInstagram(String idFoto);
    void buscarUsuario(String idUser);
    void enviarNotificacion(String idDispositivo);
    void registrarLikeFirebase(String idFoto, String idUser);

}
