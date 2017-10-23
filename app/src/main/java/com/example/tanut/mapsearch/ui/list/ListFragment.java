package com.example.tanut.mapsearch.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.tanut.mapsearch.MapSearchApp;
import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.MyItemReader;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.services.ApiClient;
import com.example.tanut.mapsearch.services.GoogleMapWebService;
import com.example.tanut.mapsearch.ui.base.BaseFragment;
import com.example.tanut.mapsearch.ui.main.MainFragment;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Abhimanyu on 10/21/2017.
 */

public class ListFragment extends BaseFragment implements MainFragment.onDataLoadedListener {


    public static final String TAG = "ListFragment";
    private ListPresenterImpl listPresenter;
    private StaggeredGridLayoutManager layoutManager;

    @Inject
    ApiClient apiClient;

    @Inject
    GoogleMapWebService mGoogleMapWebService;

    RecyclerView rv;

    public ListFragment() {

    }

    public static ListFragment newInstance() {
        //we can use this if we are getting argument from Activity
       /*
        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);*/

        return new ListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }


//    private final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
//            // code
//        }
//
//        @Override
//        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
//            // code
//        }
//    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView)view.findViewById(R.id.recyclerview);
    }

    @Override
    protected void setUp(View view) {

    }


    @Override
    public void onDataLoaded(List<MapItem> items) {

        // here you will get data from SERVICE
        Log.d(TAG, items.size()+"");
        rv.setHasFixedSize(true);
        rv.setAdapter(new ListAdapter(items));
        layoutManager = new StaggeredGridLayoutManager(2,1);
               // new GridLayoutManager(getContext(),2);
        rv.setLayoutManager(layoutManager);
        // rv.addOnScrollListener(onScrollListener);
    }

    @Override
    public void onLocalDataLoaded(List<MyItem> receivedData) {
        // here you will get data from LOCAL
    }
}
