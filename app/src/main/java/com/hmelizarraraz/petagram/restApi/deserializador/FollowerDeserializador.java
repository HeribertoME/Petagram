package com.hmelizarraraz.petagram.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hmelizarraraz.petagram.pojo.Follower;
import com.hmelizarraraz.petagram.restApi.JsonKeys;
import com.hmelizarraraz.petagram.restApi.model.FollowerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by heriberto on 05/06/17.
 */

public class FollowerDeserializador implements JsonDeserializer<FollowerResponse> {
    @Override
    public FollowerResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowerResponse followerResponse = gson.fromJson(json, FollowerResponse.class);
        JsonArray followerResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.FOLLOWERS_RESPONSE_ARRAY);
        followerResponse.setFollowers(deserealizarFollowerDeJson(followerResponseData));

        return followerResponse;
    }

    private ArrayList<Follower> deserealizarFollowerDeJson(JsonArray followerResponseData) {
        ArrayList<Follower> followers = new ArrayList<>();

        for (int i = 0; i < followerResponseData.size(); i++){
            JsonObject followerResponseDataObject = followerResponseData.get(i).getAsJsonObject();

            String id = followerResponseDataObject.get(JsonKeys.FOLLOWER_ID).getAsString();

            Follower followerActual = new Follower();
            followerActual.setId(id);

            followers.add(followerActual);
        }

        return followers;

    }
}
