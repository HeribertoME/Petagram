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

    // Get the list this user is followed by
    public static final String KEY_GET_FOLLOWED_BY = "users/self/followed-by";

    // Get the media recent by user id
    public static final String KEY_GET_MEDIA_RECENT_USER = "users/{user-id}/media/recent/";

    // Get urls
    public static final String URL_GET_FOLLOWED_BY = KEY_GET_FOLLOWED_BY + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_MEDIA_RECENT_USER = KEY_GET_MEDIA_RECENT_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
