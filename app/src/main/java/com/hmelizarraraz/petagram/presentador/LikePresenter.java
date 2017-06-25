package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.hmelizarraraz.petagram.pojo.Like;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.restApi.EndpointsApi;
import com.hmelizarraraz.petagram.restApi.adapter.RestApiAdapter;
import com.hmelizarraraz.petagram.restApi.model.UsuarioResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heriberto on 17/06/17.
 */

public class LikePresenter implements ILikePresenter{
    private Context context;
    private String idFoto;
    private String idUser;

    public LikePresenter(Context context, String idFoto, String idUser) {
        this.context = context;
        this.idFoto = idFoto;
        this.idUser = idUser;
        init();
    }


    @Override
    public void init() {
        darLikeInstagram(idFoto);
        buscarUsuario(idUser);
        registrarLikeFirebase(idFoto, idUser);
    }

    @Override
    public void darLikeInstagram(String idFoto) {
        //Toast.makeText(context, "Like a: " + idFoto, Toast.LENGTH_SHORT).show();
        Log.i("IDFOTO", " " + idFoto);
        RestApiAdapter restApi = new RestApiAdapter();
        Gson gsonMediaUsers = restApi.construyeGsonDeserializadorMediaUsers();
        EndpointsApi endpointApi = restApi.establecerConexionRestApiInstagram();
        Call<Mascota> mascotaCall = endpointApi.setLike(idFoto);
        mascotaCall.enqueue(new Callback<Mascota>() {
            @Override
            public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                Log.i("LIKE", "Resp: " + response);
                if (response.code() == 200){
                    Log.i("LIKE", "Se dió like");
                }
            }

            @Override
            public void onFailure(Call<Mascota> call, Throwable t) {
                //Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.e("ERROR_LIKE", t.toString());
            }
        });

    }

    @Override
    public void buscarUsuario(final String idUser) {
        // Traer datos de servidor
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUsuarios = restApiAdapter.construyeGsonDeserializadorUsersFirebase();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer(gsonUsuarios);
        Call<List<UsuarioResponse>> usuariosResponseCall = endpointsApi.getUsers();
        usuariosResponseCall.enqueue(new Callback<List<UsuarioResponse>>() {
            @Override
            public void onResponse(Call<List<UsuarioResponse>> call, Response<List<UsuarioResponse>> response) {
                if (response.code() == 200){
                    List<UsuarioResponse> usuarioResponse = response.body();

                    for (UsuarioResponse usuario: usuarioResponse) {
                        if (idUser.equals(usuario.getId_usuario_instagram())){
                            enviarNotificacion(usuario.getId());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<UsuarioResponse>> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.d("USUARIOS_ERROR", "Error: " + t.toString());
            }
        });

        // validar el iduser con el token
    }

    @Override
    public void enviarNotificacion(String idDispositivo) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer();
        Call<Like> likeCall = endpointsApi.mandarToken(idDispositivo);
        likeCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                Log.i("NOTIFICACION", "Se envió la notificación");
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal.", Toast.LENGTH_SHORT).show();
                Log.e("NOTIFICACION", "ERROR: " + t.toString());
            }
        });

    }

    @Override
    public void registrarLikeFirebase(String idFoto, String idUser) {
        String token = FirebaseInstanceId.getInstance().getToken();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer();
        Call<Like> likeResponseCall = endpointsApi.registrarLike(new Like(idFoto, idUser, token));
        likeResponseCall.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                Like likeResponse = response.body();
                //Log.i("INFO", "ID: " + likeResponse.getId());
                //Log.i("INFO", "ID_FOTO_INSTA: " + likeResponse.getId_foto_instagram());
                //Log.i("INFO", "ID_USUARIO_INSTA: " + likeResponse.getId_usuario_instagram());
                //Log.i("INFO", "ID_DISPO: " + likeResponse.getId_dispositivo());
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.i("INFO", "Error: " + t.toString());

            }
        });
    }

}
