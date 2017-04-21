package com.alc.alcdemo.controller;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agileflow on 4/21/2017.
 */

public class Developer implements Serializable {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String githubUrl;

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUsername() {
        return username;
    }
}
