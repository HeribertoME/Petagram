package com.hmelizarraraz.petagram.pojo;

/**
 * Created by heriberto on 06/06/17.
 */

public class Follower {
    private String id;

    public Follower() {
    }

    public Follower(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id='" + id + '\'' +
                '}';
    }
}
