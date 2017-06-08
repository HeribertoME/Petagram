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
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

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
    private ArrayList<Mascota> listaMascotas;
    private String userId;

    public static final String TAG = "USERS";

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
        //obtenerMascotasPerfilBD();
        obtenerMascotasPerfilWS();
    }

    @Override
    public void obtenerMascotasPerfilBD() {
        constructorPerfilMascota = new ConstructorPerfilMascota(context);
        mascotas = constructorPerfilMascota.obtenerDatos();
        mostrarMascotasPerfilRV();
    }

    @Override
    public void obtenerMascotasPerfilWS() {
        boolean statusConfig = checarConfiguracion();
        if (statusConfig){
            String username = obtenerSharedPreference();
            obtenerUsuarioByUsername(username);
        }
    }

    @Override
    public boolean checarConfiguracion() {
        SharedPreferences miUser = context.getSharedPreferences(ConfiguracionActivityPresenter.PREFERENCE, Context.MODE_PRIVATE);
        boolean keyName = miUser.contains(ConfiguracionActivityPresenter.USERNAME);

        if (!keyName) {
            // TODO: Cambiar por Snackbar
            Toast.makeText(context, "Configurar perfil", Toast.LENGTH_SHORT).show();
        }
        return keyName;
    }

    @Override
    public String obtenerSharedPreference() {
        SharedPreferences mUser = context.getSharedPreferences(ConfiguracionActivityPresenter.PREFERENCE, Context.MODE_PRIVATE);
        String username = mUser.getString(ConfiguracionActivityPresenter.USERNAME, "jack");
        return username;
    }

    @Override
    public void obtenerUsuarioByUsername(String username) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUser = restApiAdapter.construyeGsonDeserializadorFollowersUser();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUser);
        Call<FollowerResponse> userResponseCall = endpointsApi.getSearchUser(username);
        userResponseCall.enqueue(new Callback<FollowerResponse>() {
            @Override
            public void onResponse(Call<FollowerResponse> call, Response<FollowerResponse> response) {
                if (response.code() == 200) {
                    FollowerResponse userResponse = response.body();

                    users = userResponse.getFollowers();

                    if (users.isEmpty()) {
                        userId = "";
                    } else {
                        for (Follower user: users) {
                            userId = user.getId();
                        }
                    }
                } else {
                    userId = "";
                }

                if (userId.equals("") || userId == null) {
                    Toast.makeText(context, "El perfil no se ha configurado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    obtenerMediaUser(userId);
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
    public void obtenerMediaUser(String userId) {
        mascotas = new ArrayList<>();
        RestApiAdapter adapter = new RestApiAdapter();
        Gson gsonMediaUsers = adapter.construyeGsonDeserializadorMediaUsers();
        EndpointsApi endpintsApi = adapter.establecerConexionRestApiInstagram(gsonMediaUsers);
        Call<MascotaResponse> mascotaResponseCall = endpintsApi.getRecentMediaProfile(userId);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                listaMascotas = mascotaResponse.getMascotas();
                iPerfilFragmentView.cambiarNombrePerfil(listaMascotas.get(0).getNombreCompleto());
                iPerfilFragmentView.cambiarFotoPerfil(listaMascotas.get(0).getProfilePicture());

                agregarMascotas(listaMascotas);
                mostrarMascotasPerfilRV();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo salió mal", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });
        //Log.i(TAG, "Consultar media");
        //iPerfilFragmentView.cambiarNombrePerfil("Nombrenuevo");

    }

    @Override
    public void agregarMascotas(ArrayList<Mascota> listaMascotas) {

        for (int i = 0; i < listaMascotas.size(); i++) {
            Mascota mascotaActual = new Mascota();

            mascotaActual.setUrlFoto(listaMascotas.get(i).getUrlFoto());
            mascotaActual.setLikes(listaMascotas.get(i).getLikes());

            mascotas.add(mascotaActual);
        }

    }

    @Override
    public void mostrarMascotasPerfilRV() {
        iPerfilFragmentView.inicializarAdapatadorPerfilMascota(iPerfilFragmentView.crearAdaptadorPerfilMascota(mascotas));
        iPerfilFragmentView.generarGridLayout(3);
    }


}
