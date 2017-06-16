package com.hmelizarraraz.petagram.restApi;

/**
 * Created by heriberto on 05/06/17.
 */

public final class ConstantesRestApi {
    // General
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5560454415.93f9d22.378c8453d76540218d71ba1c2f37dc40";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_COUNT_RECENT_MEDIA = "&count=5";

    // Server
    public static final String ROOT_URL_SERVER = "https://petagramhme.herokuapp.com/";
    public static final String KEY_POST_REGISTRAR_USUARIO = "registrar-usuario/";

    // Get the list this user is followed by
    public static final String KEY_GET_FOLLOWED_BY = "users/self/followed-by";

    // Get the media recent by user id
    public static final String KEY_GET_MEDIA_RECENT_USER = "users/{user-id}/media/recent/";

    // Get a list of users matching the query
    public static final String KEY_GET_SEARCH = "users/search";

    // Get urls
    public static final String URL_GET_FOLLOWED_BY = KEY_GET_FOLLOWED_BY + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_MEDIA_RECENT_USER = KEY_GET_MEDIA_RECENT_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN + KEY_COUNT_RECENT_MEDIA;
    public static final String URL_GET_MEDIA_RECENT_PROFILE = KEY_GET_MEDIA_RECENT_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_SEARCH_USER = KEY_GET_SEARCH + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
