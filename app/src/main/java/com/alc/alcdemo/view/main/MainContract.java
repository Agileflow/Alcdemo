package com.alc.alcdemo.view.main;

import com.alc.alcdemo.controller.Developer;
import java.util.List;

/**
 * Created by agileflow on 4/21/2017.
 */

public class MainContract {

    interface View{
        void showDevelopers(List<Developer> developers);

        void openDevDetails(Developer developer);

        void hideProgressIndicator();

        boolean checkNetworkConnection();

        void showErrorMessage(String errorMessage);
    }

    interface UserActionListener{
        void showDevDetail(Developer developer);

        void loadDevelopers(String searchQualifiers);
    }
}

