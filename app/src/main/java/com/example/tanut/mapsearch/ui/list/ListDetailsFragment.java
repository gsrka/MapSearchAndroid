package com.example.tanut.mapsearch.ui.list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.services.ApiClient;
import com.example.tanut.mapsearch.services.GoogleMapWebService;
import com.example.tanut.mapsearch.ui.base.BaseFragment;
import com.example.tanut.mapsearch.ui.main.MainFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Abhimanyu on 10/22/2017.
 */

public class ListDetailsFragment extends BaseFragment {
    //implements} View.OnClickListener{

    public static final String TAG = "ListDetailsFragment";
    public ImageView imageView;
    public TextView textViewHeading,textViewLatLong,textViewAddress;
    public Button button;


    public static ListDetailsFragment newInstance() {
        //we can use this if we are getting argument from Activity
       /*
        Bundle args = new Bundle();

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);*/

        return new ListDetailsFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textViewHeading = (TextView)view.findViewById(R.id.textViewHeading);
        textViewAddress = (TextView)view.findViewById(R.id.textViewAddress);
        textViewLatLong = (TextView)view.findViewById(R.id.textViewLatLong);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+textViewLatLong+"");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        gmmIntentUri);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void setUp(View view) {

    }

}
