package com.hmelizarraraz.petagram.fragment;

import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 15/05/17.
 */

public interface IListaMascotasFragmentView {

    void generarLinearLayout();
    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptador(MascotaAdaptador mascotaAdaptador);

}
