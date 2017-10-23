package com.example.tanut.mapsearch.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.utils.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhimanyu on 10/20/2017.
 */

public class ApiClient {

    public static final String TAG = "ApiClient";
    public static final String MY_SERVICE_MESSAGE ="myServiceMessage";
    public static final String MY_SERVICE_PAYLOAD ="myPayloadMessage";

    private GoogleMapWebService webService = null;
    private Context context;
    private Retrofit retrofit;

    public ApiClient(Context context) {
        this.context = context;
    }

    public GoogleMapWebService getMapService() {

        webService = getRetrofit().create(GoogleMapWebService.class);
        return webService;


    }

    public Retrofit getRetrofit(){
        String BASE_URL ="";
        String FEED ="";
         retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
