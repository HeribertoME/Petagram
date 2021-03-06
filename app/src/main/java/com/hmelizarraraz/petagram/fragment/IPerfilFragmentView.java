package com.hmelizarraraz.petagram.fragment;

import com.hmelizarraraz.petagram.adapter.PerfilMascotaAdapter;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public interface IPerfilFragmentView {

    void generarGridLayout(int columnas);
    PerfilMascotaAdapter crearAdaptadorPerfilMascota(ArrayList<Mascota> mascotas);
    void inicializarAdapatadorPerfilMascota(PerfilMascotaAdapter adapter);
    void cambiarNombrePerfil(String username);
    void cambiarFotoPerfil(String url);

}
