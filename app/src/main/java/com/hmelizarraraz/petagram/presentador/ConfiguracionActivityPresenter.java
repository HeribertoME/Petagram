package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.hmelizarraraz.petagram.IConfiguracionActivityView;

/**
 * Created by heriberto on 06/06/17.
 */

public class ConfiguracionActivityPresenter implements IConfiguracionActivityPresenter{
    private IConfiguracionActivityView iConfiguracionActivityView;
    private Context context;

    public ConfiguracionActivityPresenter(IConfiguracionActivityView iConfiguracionActivityView, Context context) {
        this.iConfiguracionActivityView = iConfiguracionActivityView;
        this.context = context;
        generarToolbar();
    }

    @Override
    public void generarToolbar() {
        iConfiguracionActivityView.generarToolbar();
    }

    @Override
    public void guardarPreferencia(String username) {
        SharedPreferences mConfig = context.getSharedPreferences("Configuracion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mConfig.edit();
        editor.putString("username", username);
        editor.commit();
        Toast.makeText(context, "El usuario: " + username + " se ha guardado correctamente!", Toast.LENGTH_SHORT).show();
    }
}
