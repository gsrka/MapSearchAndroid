package com.example.tanut.mapsearch.ui.main;

import android.util.Log;

import com.example.tanut.mapsearch.data.db.MyItemReader;
import com.example.tanut.mapsearch.data.db.backend.AppDatabase;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.data.db.network.model.MapResult;
import com.example.tanut.mapsearch.services.GoogleMapWebService;
import com.example.tanut.mapsearch.ui.map.MapMvpView;
import com.example.tanut.mapsearch.ui.map.MapPresenter;
import com.example.tanut.mapsearch.utils.Utils;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tanut on 10/22/2017.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainMvpView view;
    private List<MyItem> items = null;
    private List<MapItem> mapItems = null;
    private MyItemReader myItemReader;
    private  AppDatabase database;

    public MainPresenterImpl(MainMvpView view, MyItemReader myItemReader, AppDatabase database) {
        this.view = view;
        this.myItemReader = myItemReader;
        this.database = database;
    }


    @Override
    public void getGeoPlaceData(String place, InputStream inputStream) {

        try {
            items = myItemReader.read(inputStream);
        } catch (Exception f) {
            view.onError("ON ERROR");
        }

        if (!items.isEmpty()) {
            view.manageLocalData(items);
        } else {
            view.showMessage("NO ITEM");
        }
    }

    @Override
    public void getDataFromService(GoogleMapWebService mapWebService,String querry) {

        try {
            mapItems = database.itemModel().getItemForTag(querry);
        }
        catch (Exception f){
            view.onError("ON ERROR");
        }

        if(mapItems.isEmpty()) {
            Call<MapResult> call = mapWebService.getNearbyPlaces(querry, Integer.parseInt(Utils.RADIUS), Utils.KEY);


            call.enqueue(new Callback<MapResult>() {
                @Override
                public void onResponse(Call<MapResult> call, Response<MapResult> response) {
                    if (response.isSuccessful()) {
                        mapItems = response.body().getResults();
                        if(!mapItems.isEmpty()) {
                            view.manageData(mapItems);
                        }
                        else{
                            view.showMessage("NO DATA");
                        }
                    } else {
                        Log.d("failure", "ON ERROR1");
                        view.onError("ON ERROR");
                    }
                }

                @Override
                public void onFailure(Call<MapResult> call, Throwable t) {
                    Log.d("failure", t.getCause().getMessage());
                    view.onError("ON ERROR");
                }
            });
        }

        else{

            Log.d("LOCAL",mapItems.size()+" ");
            view.manageData(mapItems);
        }
    }
}
