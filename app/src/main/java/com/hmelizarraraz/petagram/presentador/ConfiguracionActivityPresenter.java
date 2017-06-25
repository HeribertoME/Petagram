package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.hmelizarraraz.petagram.view.IConfiguracionActivityView;
import com.hmelizarraraz.petagram.view.MainActivity;

/**
 * Created by heriberto on 06/06/17.
 */

public class ConfiguracionActivityPresenter implements IConfiguracionActivityPresenter{
    public static final String PREFERENCE = "Configuracion";
    public static final String USERNAME = "username";
    public static final String IDUSER = "id_usuario_instagram";
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
        SharedPreferences mConfig = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mConfig.edit();
        editor.putString(USERNAME, username);
        editor.commit();
        //Toast.makeText(context, "El usuario: " + username + " se ha guardado correctamente!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
