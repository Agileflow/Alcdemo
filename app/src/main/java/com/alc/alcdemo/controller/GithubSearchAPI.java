package com.alc.alcdemo.controller;

import com.alc.alcdemo.model.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by agileflow on 4/21/2017.
 */

public interface GithubSearchAPI {

    @GET("/search/users")
    Call<QueryResult> getDevelopers(@Query(value = "q",encoded = true) String userSearchQualifiers, @Query("per_page") String pageCount);
}
