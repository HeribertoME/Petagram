package com.hmelizarraraz.petagram.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by heriberto on 15/06/17.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN_RECIBIDO", token);
    }
}
