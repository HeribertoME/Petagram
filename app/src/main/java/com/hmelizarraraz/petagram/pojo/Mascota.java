package com.hmelizarraraz.petagram.pojo;

/**
 * Created by heriberto on 11/04/17.
 */

public class Mascota {

    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes;
    private String textoFoto;
    private String profilePicture;
    private String idFoto;

    public Mascota() {
    }

    public Mascota(String id, String nombreCompleto, String urlFoto, int likes, String textoFoto, String profilePicture, String idFoto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.urlFoto = urlFoto;
        this.likes = likes;
        this.textoFoto = textoFoto;
        this.profilePicture = profilePicture;
        this.idFoto = idFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTextoFoto() {
        return textoFoto;
    }

    public void setTextoFoto(String textoFoto) {
        this.textoFoto = textoFoto;
    }

    public String getProfilePicture() {
        return  profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id='" + id + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                ", likes=" + likes +
                ", textoFoto='" + textoFoto + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", idFoto='" + idFoto + '\'' +
                '}';
    }
}
