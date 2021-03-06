package com.alc.alcdemo;


import com.alc.alcdemo.model.Developer;
import com.alc.alcdemo.controller.GithubSearchAPI;
import com.alc.alcdemo.model.QueryResult;

import com.alc.alcdemo.support.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by agileflow on 4/21/2017.
 */

public class DeveloperMonitor {

    public interface OnDevelopersLoadedListener{
        void onDevelopersLoaded(List<Developer> developers);

        void onDevelopersLoadFailure(String errorMessage);
    }

    public static void getDevelopers(String query, final OnDevelopersLoadedListener developersLoadedListener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubSearchAPI apiService = retrofit.create(GithubSearchAPI.class);

        Call<QueryResult> call = apiService.getDevelopers(query,Constants.PER_PAGE);

        call.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                developersLoadedListener.onDevelopersLoaded(response.body().getDevelopers());
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
                developersLoadedListener.onDevelopersLoadFailure(t.getMessage());
            }
        });
    }
}
