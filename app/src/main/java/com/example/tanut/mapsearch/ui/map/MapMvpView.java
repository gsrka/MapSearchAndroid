package com.example.tanut.mapsearch.ui.map;

import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.ui.base.MvpView;

import java.util.List;

/**
 * Created by tanut on 10/18/2017.
 */

public interface MapMvpView extends MvpView {

    public void removeMarkers();

    public void showMarkerAt(float latitude, float longitude);

    public void showSnippet();


}
