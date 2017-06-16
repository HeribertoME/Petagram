package com.hmelizarraraz.petagram.restApi.deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hmelizarraraz.petagram.pojo.Mascota;
import com.hmelizarraraz.petagram.restApi.JsonKeys;
import com.hmelizarraraz.petagram.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by heriberto on 05/06/17.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.USER_MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota>deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++) {
            String textoFoto;

            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject imagesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER_MEDIA_IMAGES);
            JsonObject stdResolution = imagesJson.getAsJsonObject(JsonKeys.USER_MEDIA_STANDARD_RESOLUTION);
            String url = stdResolution.get(JsonKeys.USER_MEDIA_URL).getAsString();

            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER_MEDIA_USER);
            String fullname = userJson.get(JsonKeys.USER_MEDIA_USER_FULLNAME).getAsString();
            String profile_picture = userJson.get(JsonKeys.USER_MEDIA_USER_PROFILE_PICTURE).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER_MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.USER_MEDIA_COUNT).getAsInt();

            JsonElement captionJson = mascotaResponseDataObject.getAsJsonObject().get(JsonKeys.USER_MEDIA_CAPTION);

            if (captionJson.isJsonNull()){
                textoFoto = "";
            } else {
                textoFoto = captionJson.getAsJsonObject().get(JsonKeys.USER_MEDIA_CAPTION_TEXT).getAsString();
            }

            String idFoto = mascotaResponseDataObject.get(JsonKeys.USER_MEDIA_ID).getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setUrlFoto(url);
            mascotaActual.setNombreCompleto(fullname);
            mascotaActual.setLikes(likes);
            mascotaActual.setTextoFoto(textoFoto);
            mascotaActual.setProfilePicture(profile_picture);
            mascotaActual.setIdFoto(idFoto);

            mascotas.add(mascotaActual);
        }

        return mascotas;
    }
}
