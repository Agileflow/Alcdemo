package com.alc.alcdemo.view.developer;

import android.net.Uri;

import com.alc.alcdemo.controller.Developer;

/**
 * Created by agileflow on 4/21/2017.
 */

public class DeveloperPresenter implements DeveloperContract.UserActionListener {

    private final DeveloperContract.View mDevDetailsView;

    public DeveloperPresenter(DeveloperContract.View view){
        mDevDetailsView = view;
    }

    @Override
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        mDevDetailsView.openInBrowser(webpage);
    }

    @Override
    public void openDevDetails(Developer developer) {
        mDevDetailsView.showProfilePhoto(developer.getAvatarUrl());
        mDevDetailsView.showUsername(developer.getUsername());
        mDevDetailsView.showProfileUrl(developer.getGithubUrl());
    }

    @Override
    public void shareGithubProfile(String username, String profileUrl) {
        mDevDetailsView.launchShareIntent(username,profileUrl);
    }
}
