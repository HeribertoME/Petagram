package com.hmelizarraraz.petagram.restApi;

import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by heriberto on 05/06/17.
 */

public interface EndpointsApi {
    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY)
    Call<MascotaResponse> getFollowedBy();

    @GET(ConstantesRestApi.URL_GET_MEDIA_RECENT_USER)
    Call<MascotaResponse> getRecentMediaByUserId(@Path("user-id") String userId);
}
