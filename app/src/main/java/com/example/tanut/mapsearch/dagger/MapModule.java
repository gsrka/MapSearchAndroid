package com.example.tanut.mapsearch.dagger;

import android.content.Context;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.backend.AppDatabase;
import com.example.tanut.mapsearch.services.ApiClient;
import com.example.tanut.mapsearch.services.GoogleMapWebService;

import java.io.InputStream;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tanut on 10/18/2017.
 */

@Module
public class MapModule {

    private Context mContext;
    private ApiClient mApiClient;

    public MapModule(Context mContext) {
        this.mContext = mContext;
    }


    @Provides
    @Singleton
    InputStream getInputStream() {
        return mContext.getResources().openRawResource(R.raw.radar_search);
    }

    @Provides
    @Singleton
    ApiClient getClient() {
        mApiClient = new ApiClient(mContext);
        return mApiClient;
    }

    @Provides
    @Singleton
    GoogleMapWebService getMapWebService() {
        return mApiClient.getMapService();
    }

    @Provides
    @Singleton
    AppDatabase getAppDatabase() {
        return AppDatabase.getDatabase(mContext);
    }
}
