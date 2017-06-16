package com.hmelizarraraz.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hmelizarraraz.petagram.restApi.ConstantesRestApi;
import com.hmelizarraraz.petagram.restApi.EndpointsApi;
import com.hmelizarraraz.petagram.restApi.deserializador.FollowerDeserializador;
import com.hmelizarraraz.petagram.restApi.deserializador.MascotaDeserializador;
import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heriberto on 05/06/17.
 */

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionRestApiInstagram() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public EndpointsApi establecerConexionServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorFollowersUser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowerResponse.class, new FollowerDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorMediaUsers() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();
    }
}
