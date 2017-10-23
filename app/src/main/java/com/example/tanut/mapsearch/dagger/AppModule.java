package com.example.tanut.mapsearch.dagger;

import android.content.Context;

import com.example.tanut.mapsearch.MapSearchApp;
import com.example.tanut.mapsearch.data.db.backend.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tanut on 10/18/2017.
 */

@Module
public class AppModule {

    private final MapSearchApp app;

    public AppModule(MapSearchApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context getApplicationContext() {
        return app;
    }

}
