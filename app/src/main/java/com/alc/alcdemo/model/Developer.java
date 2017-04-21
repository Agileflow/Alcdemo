package com.alc.alcdemo.model;

import android.graphics.Bitmap;

/**
 * Created by agileflow on 4/21/2017.
 */

public class Developer {

    private final String username;
    private final Bitmap avatar;
    private final String githubUrl;

    public Developer(String username, Bitmap avatar, String githubUrl){
        this.username = username;
        this.avatar = avatar;
        this.githubUrl = githubUrl;
    }

    public String getUsername(){
        return username;
    }

    public Bitmap getAvatar(){
        return avatar;
    }

    public String getGithubUrl(){
        return githubUrl;
    }
}
