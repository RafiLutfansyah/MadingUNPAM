package com.rafilutfansyah.madingunpam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModelResult {

    @SerializedName("user")
    @Expose
    private List<HomeModel> user = null;

    public List<HomeModel> getUser() {
        return user;
    }

    public void setUser(List<HomeModel> user) {
        this.user = user;
    }

}