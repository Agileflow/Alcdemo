package com.alc.alcdemo.view.main;

import com.alc.alcdemo.DeveloperMonitor;
import com.alc.alcdemo.controller.Developer;

import java.util.List;

/**
 * Created by agileflow on 4/21/2017.
 */

public class MainPresenter implements MainContract.UserActionListener  {

    private MainContract.View developers_view;

    MainPresenter(MainContract.View developersView){
        this.developers_view = developersView;
    }

    @Override
    public void showDevDetail(Developer dev) {
        developers_view.openDevDetails(dev);
    }

    @Override
    public void loadDevelopers(String searchQualifiers) {
        if(developers_view.checkNetworkConnection()){
            DeveloperMonitor.getDevelopers(searchQualifiers,loadedListener);
        }else {
            developers_view.hideProgressIndicator();
            developers_view.showErrorMessage("No Internet Connectivity");
        }
    }

    private DeveloperMonitor.OnDevelopersLoadedListener loadedListener = new DeveloperMonitor.OnDevelopersLoadedListener() {
        @Override
        public void onDevelopersLoaded(List<Developer> developers) {
            developers_view.hideProgressIndicator();
            developers_view.showDevelopers(developers);
        }

        @Override
        public void onDevelopersLoadFailure(String errorMessage) {
            developers_view.hideProgressIndicator();
            developers_view.showErrorMessage(errorMessage);
        }
    };
}
