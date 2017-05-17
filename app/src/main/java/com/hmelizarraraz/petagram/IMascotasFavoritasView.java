package com.hmelizarraraz.petagram;

import com.hmelizarraraz.petagram.adapter.MascotaAdaptador;
import com.hmelizarraraz.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by heriberto on 17/05/17.
 */

public interface IMascotasFavoritasView {

    void generarToolbar();
    void generarLinearLayoutVertical();
    MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    void inicializarAdaptador(MascotaAdaptador adaptador);
}
