package com.example.tanut.mapsearch.ui.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tanut.mapsearch.MapSearchApp;
import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.MyItemReader;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.services.ApiClient;
import com.example.tanut.mapsearch.services.GoogleMapWebService;
import com.example.tanut.mapsearch.ui.base.BaseFragment;
import com.example.tanut.mapsearch.ui.list.ListFragment;
import com.example.tanut.mapsearch.ui.main.MainFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by tanut on 10/18/2017.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MapMvpView, MainFragment.onDataLoadedListener {

    public static final String TAG = "AboutFragment";

    private GoogleMap googleMap = null;
    private MapView mapView;
    private ClusterManager<MyItem> mClusterManagerLocal;
    private ClusterManager<MapItem> mClusterManager;

    private RecyclerView mRecyclerView;

    private static MapFragment mapFragment;


    public static MapFragment newInstance() {
        //we can use this if we are getting argument from Avtivity
       /*
        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);*/

       if(mapFragment == null){
           mapFragment = new MapFragment();
       }

        return mapFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvSearchResult);
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void removeMarkers() {

    }

    @Override
    public void showMarkerAt(float latitude, float longitude) {

    }


    @Override
    public void showSnippet() {
        //show snippet
    }


    @Override
    public void onDataLoaded(List<MapItem> items) {

            // Clustering
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(items.get(0).getPosition().latitude, items.get(0).getPosition().longitude), 10));
            mClusterManager = new ClusterManager<MapItem>(getActivity(), googleMap);
            googleMap.setOnCameraIdleListener(mClusterManager);
            mClusterManager.addItems(items);

        // RecyclerView
        Adapter adapter = new Adapter(getContext(), items);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onLocalDataLoaded(List<MyItem> items) {
        // 1 clustering
        if(googleMap!=null){

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(items.get(0).getPosition().latitude, items.get(0).getPosition().longitude), 10));
        mClusterManagerLocal = new ClusterManager<MyItem>(getActivity(), googleMap);
        googleMap.setOnCameraIdleListener(mClusterManagerLocal);
        mClusterManagerLocal.addItems(items);

        }

    }
}
