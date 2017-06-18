package com.hmelizarraraz.petagram.presentador;

/**
 * Created by heriberto on 17/06/17.
 */

public interface ILikePresenter {

    void darLikeInstagram(String idFoto);
    void buscarUsuario(String idUser);
    void registrarLikeFirebase(String idFoto, String idUser);
}
