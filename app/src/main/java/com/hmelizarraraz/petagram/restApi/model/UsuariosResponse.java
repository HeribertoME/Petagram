package com.hmelizarraraz.petagram.restApi.model;

import java.util.List;

/**
 * Created by heriberto on 17/06/17.
 */

public class UsuariosResponse {

    private List<UsuarioResponse> usuarios;

    public List<UsuarioResponse> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioResponse> usuarios) {
        this.usuarios = usuarios;
    }
}
