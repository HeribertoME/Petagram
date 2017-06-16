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
    private ArrayList<Mascota> listaMascotas;

    public ListaMascotasFragmentPresenter(IListaMascotasFragmentView iListaMascotasFragmentView, Context context) {
        this.iListaMascotasFragmentView = iListaMascotasFragmentView;
        this.context = context;
        //obtenerMascotasBD();
        obtenerFollowers();
    }

    public ListaMascotasFragmentPresenter(Context context) {
        this.context = context;
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
                } else {
                    for (int i = 0; i < followers.size(); i++) {
                        obtenerMediaFollower(followers.get(i).getId());
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
        mascotas = new ArrayList<>();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaUsers = restApiAdapter.construyeGsonDeserializadorMediaUsers();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaUsers);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaByUserId(id);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponse = response.body();
                listaMascotas = mascotaResponse.getMascotas();
                agregarMascotas(listaMascotas);
                mostrarMascotasRV();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void agregarMascotas(ArrayList<Mascota> listaMascotas) {

        for (int i = 0; i < listaMascotas.size(); i++) {
            Mascota mascotaActual = new Mascota();

            mascotaActual.setUrlFoto(listaMascotas.get(i).getUrlFoto());
            mascotaActual.setTextoFoto(listaMascotas.get(i).getTextoFoto());
            mascotaActual.setLikes(listaMascotas.get(i).getLikes());
            mascotaActual.setIdFoto(listaMascotas.get(i).getIdFoto());

            mascotas.add(mascotaActual);

        }

    }

    @Override
    public void darLikeInstagram(String idFoto) {
        //Toast.makeText(context, "Like a: " + idFoto, Toast.LENGTH_SHORT).show();
        RestApiAdapter restApi = new RestApiAdapter();
        Gson gsonMediaUsers = restApi.construyeGsonDeserializadorMediaUsers();
        EndpointsApi endpointApi = restApi.establecerConexionRestApiInstagram(gsonMediaUsers);
        Call<MascotaResponse> mascotasResponseCall = endpointApi.setLike(idFoto);
        mascotasResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                if (response.code() == 200){
                    Log.i("LIKE", "Se dió like");
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.e("ERROR", t.toString());
            }
        });




    }

}
