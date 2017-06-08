package com.hmelizarraraz.petagram.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hmelizarraraz.petagram.db.ConstructorPerfilMascota;
import com.hmelizarraraz.petagram.fragment.IPerfilFragmentView;
import com.hmelizarraraz.petagram.pojo.Follower;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.restApi.EndpointsApi;
import com.hmelizarraraz.petagram.restApi.adapter.RestApiAdapter;
import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heriberto on 17/05/17.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorPerfilMascota constructorPerfilMascota;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Follower> users;
    public static final String TAG = PerfilFragmentPresenter.class.getSimpleName();

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        //obtenerMascotasPerfilBD();
        obtenerUsuarioByUsername();
    }

    @Override
    public void obtenerMascotasPerfilBD() {
        constructorPerfilMascota = new ConstructorPerfilMascota(context);
        mascotas = constructorPerfilMascota.obtenerDatos();
        mostrarMascotasPerfilRV();
    }

    @Override
    public void mostrarMascotasPerfilRV() {
        iPerfilFragmentView.inicializarAdapatadorPerfilMascota(iPerfilFragmentView.crearAdaptadorPerfilMascota(mascotas));
        iPerfilFragmentView.generarGridLayout(3);
    }

    @Override
    public void obtenerUsuarioByUsername() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUser = restApiAdapter.construyeGsonDeserializadorFollowersUser();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUser);
        Call<FollowerResponse> userResponseCall = endpointsApi.getSearchUser("32547802");
        userResponseCall.enqueue(new Callback<FollowerResponse>() {
            @Override
            public void onResponse(Call<FollowerResponse> call, Response<FollowerResponse> response) {
                FollowerResponse userResponse = response.body();

                users = userResponse.getFollowers();

                if (users.isEmpty()) {
                    mascotas = new ArrayList<Mascota>();
                } else {
                    //mascotas = new ArrayList<Mascota>();
                    Log.i(TAG, "El id es: " + users.get(0).getId());
                }

            }

            @Override
            public void onFailure(Call<FollowerResponse> call, Throwable t) {
                Toast.makeText(context, "Algo saló mal", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public String obtenerSharedPreference() {
        SharedPreferences mUser = context.getSharedPreferences(ConfiguracionActivityPresenter.PREFERENCE, Context.MODE_PRIVATE);
        String username = mUser.getString(ConfiguracionActivityPresenter.USERNAME, "jack");
        return username;
    }
}
