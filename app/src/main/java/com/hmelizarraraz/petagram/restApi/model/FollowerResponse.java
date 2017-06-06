package com.hmelizarraraz.petagram.restApi.model;

import com.hmelizarraraz.petagram.pojo.Follower;

import java.util.ArrayList;

/**
 * Created by heriberto on 06/06/17.
 */

public class FollowerResponse {
    private ArrayList<Follower> followers;

    public ArrayList<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Follower> followers) {
        this.followers = followers;
    }
}
