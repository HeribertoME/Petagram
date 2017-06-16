package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hmelizarraraz.petagram.restApi.EndpointsApi;
import com.hmelizarraraz.petagram.restApi.adapter.RestApiAdapter;
import com.hmelizarraraz.petagram.restApi.model.UsuarioResponse;
import com.hmelizarraraz.petagram.view.ConfiguracionActivity;
import com.hmelizarraraz.petagram.view.IMainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heriberto on 16/06/17.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivity iMainActivity;
    private Context context;
    private View view;

    public MainActivityPresenter(IMainActivity iMainActivity, Context context, View view) {
        this.iMainActivity = iMainActivity;
        this.context = context;
        this.view = view;
        enviarTokenRegistro();
    }


    @Override
    public void enviarTokenRegistro() {
        boolean statusConfig = checarConfiguracion();
        if (statusConfig){
            String token = iMainActivity.obtenerToken();
            String idUser = obtenerIdUsuario();
            registrarUsuario(token, idUser);
        }


    }

    @Override
    public boolean checarConfiguracion() {
        SharedPreferences mIdUser = context.getSharedPreferences(ConfiguracionActivityPresenter.PREFERENCE, Context.MODE_PRIVATE);
        boolean keyId = mIdUser.contains(ConfiguracionActivityPresenter.IDUSER);

        if (!keyId) {
            Snackbar.make(view, "No se ha configurado un perfil.", Snackbar.LENGTH_LONG)
                    .setAction("Configurar", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ConfiguracionActivity.class);
                            context.startActivity(intent);
                        }
                    })
                    .show();

        } else {
            String id = mIdUser.getString(ConfiguracionActivityPresenter.IDUSER, "");
            if (id.equals("")){
                Snackbar.make(view, "El perfil es incorrecto.", Snackbar.LENGTH_LONG)
                        .setAction("Configurar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, ConfiguracionActivity.class);
                                context.startActivity(intent);
                            }
                        })
                        .show();
                keyId = false;
            }
        }

        return keyId;
    }

    @Override
    public String obtenerIdUsuario() {
        SharedPreferences mIdUser = context.getSharedPreferences(ConfiguracionActivityPresenter.PREFERENCE, Context.MODE_PRIVATE);
        String idUser = mIdUser.getString(ConfiguracionActivityPresenter.IDUSER, "1574083");
        return idUser;
    }

    @Override
    public void registrarUsuario(String token, String idUser) {
        //Log.d("MAINPRESENTER", token);
        //Log.d("MAINPRESENTER", idUser);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarUsuario(new UsuarioResponse(token,idUser));

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("FIREBASERESP_ID", usuarioResponse.getId());
                Log.d("FIREBASERESP_ID_DISP", usuarioResponse.getId_dispositivo());
                Log.d("FIREBASERESP_ID_USER", usuarioResponse.getId_usuario_instagram());
                //Log.d("RESP", response.toString());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal.", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", t.toString());
            }
        });

    }
}
