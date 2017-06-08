package com.hmelizarraraz.petagram.restApi;

import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
