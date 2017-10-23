package com.example.tanut.mapsearch.data.db.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abhimanyu on 10/21/2017.
 */

public class MapResult {

    @SerializedName("results")
    @Expose
    private List<MapItem> results = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<MapItem> getResults() {
        return results;
    }

    public void setResults(List<MapItem> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
