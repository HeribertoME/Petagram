package com.hmelizarraraz.petagram.restApi;

import com.hmelizarraraz.petagram.pojo.Like;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;
import com.hmelizarraraz.petagram.restApi.model.UsuarioResponse;
import com.hmelizarraraz.petagram.restApi.model.UsuariosResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by heriberto on 05/06/17.
 */

public interface EndpointsApi {
    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY)
    Call<FollowerResponse> getFollowedBy();

    @GET(ConstantesRestApi.URL_GET_MEDIA_RECENT_USER)
    Call<MascotaResponse> getRecentMediaByUserId(@Path("user-id") String userId);

    @GET(ConstantesRestApi.URL_GET_MEDIA_RECENT_PROFILE)
    Call<MascotaResponse> getRecentMediaProfile(@Path("user-id") String userId);

    @GET(ConstantesRestApi.URL_GET_SEARCH_USER)
    Call<FollowerResponse> getSearchUser(@Query("q") String username);

    @POST(ConstantesRestApi.URL_POST_LIKES)
    Call<Mascota> setLike(@Path("media-id") String idMedia);

    @Headers({"Content-Type: application/json"})
    @POST(ConstantesRestApi.KEY_POST_REGISTRAR_USUARIO)
    Call<UsuarioResponse> registrarUsuario(@Body UsuarioResponse usuario);

    @GET(ConstantesRestApi.KEY_GET_USUARIOS)
    Call<List<UsuarioResponse>> getUsers();

    @Headers({"Content-Type: application/json"})
    @POST(ConstantesRestApi.URL_REGISTRAR_LIKES)
    Call<Like> registrarLike(@Body Like like);

    @GET(ConstantesRestApi.KEY_TOKEN_LIKE)
    Call<Like> mandarToken(@Path("token") String token);

}
