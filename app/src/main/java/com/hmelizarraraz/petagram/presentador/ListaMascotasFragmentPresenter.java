package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hmelizarraraz.petagram.db.ConstructorMascotas;
import com.hmelizarraraz.petagram.fragment.IListaMascotasFragmentView;
import com.hmelizarraraz.petagram.pojo.Follower;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.restApi.EndpointsApi;
import com.hmelizarraraz.petagram.restApi.adapter.RestApiAdapter;
import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heriberto on 17/05/17.
 */

public class ListaMascotasFragmentPresenter implements IListaMascotasFragmentPresenter {

    private IListaMascotasFragmentView iListaMascotasFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Follower> followers;

    public ListaMascotasFragmentPresenter(IListaMascotasFragmentView iListaMascotasFragmentView, Context context) {
        this.iListaMascotasFragmentView = iListaMascotasFragmentView;
        this.context = context;
        //obtenerMascotasBD();
        obtenerFollowers();
    }

    @Override
    public void obtenerMascotasBD() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iListaMascotasFragmentView.inicializarAdaptador(iListaMascotasFragmentView.crearAdaptador(mascotas));
        iListaMascotasFragmentView.generarLinearLayout();
    }

    @Override
    public void obtenerFollowers() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollowers = restApiAdapter.construyeGsonDeserializadorFollowersUser();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowers);
        Call<FollowerResponse> followerResponseCall = endpointsApi.getFollowedBy();
        followerResponseCall.enqueue(new Callback<FollowerResponse>() {
            @Override
            public void onResponse(Call<FollowerResponse> call, Response<FollowerResponse> response) {
                FollowerResponse followerResponse = response.body();
                followers = followerResponse.getFollowers();

                if (followers.isEmpty()) {
                    Toast.makeText(context, "No tienes followers aún :(", Toast.LENGTH_SHORT).show();
                    //Log.i("VACIO", "No hay followers");
                } else {

                    for (Follower follower: followers) {
                        obtenerMediaFollower(follower.getId());
                    }
                    
                }
            }

            @Override
            public void onFailure(Call<FollowerResponse> call, Throwable t) {
                Toast.makeText(context, "Ups! algo salió mal. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
                Log.e("FALLÓ LA CONEXIÓN", t.toString());
            }
        });


    }

    @Override
    public void obtenerMediaFollower(String id) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaUsers = restApiAdapter.construyeGsonDeserializadorMediaUsers();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaUsers);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaByUserId(id);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                Log.d("RESPUESTA", mascotas.toString());
                mostrarMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }
}
