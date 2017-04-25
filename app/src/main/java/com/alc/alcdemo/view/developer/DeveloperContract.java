package com.alc.alcdemo.view.developer;

import android.net.Uri;

import com.alc.alcdemo.model.Developer;

/**
 * Created by agileflow on 4/21/2017.
 */

public class DeveloperContract {
    interface View{
        void openInBrowser(Uri pageUri);

        void showProfileUrl(String profileUrl);

        void showProfilePhoto(String profilePhotoUrl);

        void showUsername(String username);

        void showFollowers(String followers);

        void showRepository(String repos);

        void launchShareIntent(String username,String profileUrl);

    }

    interface UserActionListener{
        void openWebPage(String url);

        void openDevDetails(Developer developer);

        void shareGithubProfile(String username,String profileUrl);
    }
}
