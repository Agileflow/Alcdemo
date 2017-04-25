package com.alc.alcdemo.model;

import com.alc.alcdemo.support.Helper;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.http.GET;

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

    @SerializedName("followers_url")
    private String followers;

    @SerializedName("repos_url")
    private String repository;

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFollowers() {
        return "-";
    }

    public String getRepository() {
        return "-";
    }
}
