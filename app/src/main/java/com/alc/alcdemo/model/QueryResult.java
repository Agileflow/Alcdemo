package com.alc.alcdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by agileflow on 4/21/2017.
 */

public class QueryResult {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("items")
    private List<Developer> developers;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

}
