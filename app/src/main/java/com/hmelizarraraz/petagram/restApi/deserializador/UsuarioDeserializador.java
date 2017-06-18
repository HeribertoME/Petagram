package com.hmelizarraraz.petagram.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hmelizarraraz.petagram.restApi.model.UsuarioResponse;
import com.hmelizarraraz.petagram.restApi.model.UsuariosResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by heriberto on 17/06/17.
 */

public class UsuarioDeserializador implements JsonDeserializer<UsuariosResponse> {

    @Override
    public UsuariosResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<UsuariosResponse>(){}.getType();
        UsuariosResponse usuariosResponse = gson.fromJson(json, collectionType);
        //UsuariosResponse
        JsonArray usuariosResponseData = json.getAsJsonArray();
        usuariosResponse.setUsuarios(deserializarUsuariosDeJson(usuariosResponseData));

        return usuariosResponse;
    }

    private ArrayList<UsuarioResponse> deserializarUsuariosDeJson(JsonArray usuariosResponseData) {
        ArrayList<UsuarioResponse> usuarios = new ArrayList<>();

        for (int i = 0; i < usuariosResponseData.size(); i++) {
            JsonObject usuarioResponseDataObject = usuariosResponseData.get(i).getAsJsonObject();

            String id = usuarioResponseDataObject.get("id").getAsString();
            String id_dispositivo = usuarioResponseDataObject.get("id_dispositivo").getAsString();
            String id_usuario_instagram = usuarioResponseDataObject.get("id_usuario_instagram").getAsString();

            UsuarioResponse usuario = new UsuarioResponse();
            usuario.setId(id);
            usuario.setId_dispositivo(id_dispositivo);
            usuario.setId_usuario_instagram(id_usuario_instagram);

            usuarios.add(usuario);
        }

        return usuarios;
    }

}
