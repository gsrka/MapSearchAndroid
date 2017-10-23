package com.example.tanut.mapsearch.ui.main;

import com.example.tanut.mapsearch.services.GoogleMapWebService;

import java.io.InputStream;

/**
 * Created by tanut on 10/22/2017.
 */

public interface MainPresenter {

    public void getGeoPlaceData(String place, InputStream inputStream);

    public void getDataFromService(GoogleMapWebService mapWebService,String place);
}
