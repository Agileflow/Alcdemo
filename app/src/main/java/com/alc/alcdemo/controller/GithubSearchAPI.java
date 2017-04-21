package com.alc.alcdemo.controller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by agileflow on 4/21/2017.
 */

public interface GithubSearchAPI {

    @GET("/search/users")
    Call<QueryResult> getDevelopers(@Query(value = "q",encoded = true) String userSearchQualifiers, @Query("per_page") String pageCount, @Query(value ="access_token",encoded = true) String token);
}
