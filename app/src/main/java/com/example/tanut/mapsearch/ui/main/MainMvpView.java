package com.example.tanut.mapsearch.ui.main;

import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.ui.base.MvpView;

import java.util.List;

/**
 * Created by tanut on 10/22/2017.
 */

public interface MainMvpView extends MvpView {


    public void manageLocalData(List<MyItem> items);

    public void manageData(List<MapItem> items);
}
