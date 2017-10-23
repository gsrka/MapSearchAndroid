package com.example.tanut.mapsearch.dagger;

import com.example.tanut.mapsearch.MapSearchApp;
import com.example.tanut.mapsearch.ui.main.MainFragment;
import com.example.tanut.mapsearch.ui.map.MapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tanut on 10/18/2017.
 */

@Singleton
@Component(modules = {AppModule.class,MapModule.class})
public interface AppComponent {
    void inject(MapSearchApp application);
    void inject(MapFragment mapFragment);
    void inject(MainFragment mainFragment);
}