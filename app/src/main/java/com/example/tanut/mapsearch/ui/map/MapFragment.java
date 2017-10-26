package com.example.tanut.mapsearch.ui.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.ui.base.BaseFragment;
import com.example.tanut.mapsearch.ui.list.ListDetailsFragment;
import com.example.tanut.mapsearch.ui.main.MainFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanut on 10/18/2017.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MapMvpView, ClusterManager.OnClusterInfoWindowClickListener<MapItem>, MainFragment.onDataLoadedListener {

    public static final String TAG = "MapFragment";

    private GoogleMap googleMap;
    private MapView mapView;
    private ClusterManager<MyItem> mClusterManagerLocal;
    private ClusterManager<MapItem> mClusterManager;
    private ArrayList<MapItem> mapList;

    private RecyclerView mRecyclerView;

    private static MapFragment mapFragment;


    public static MapFragment newInstance() {


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
        updateWithData();

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

            mapList = (ArrayList<MapItem>) items;

    }

    private void updateWithData() {

       /* if(googleMap==null){
            Log.d("GOOGLEMAP",mapList.get(0).getLat()+"");
        }
        // Clustering
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapList.get(0).getLat(), mapList.get(0).getLng()), 10));*/
        mClusterManager = new ClusterManager<MapItem>(getActivity(), googleMap);
        googleMap.setOnCameraIdleListener(mClusterManager);
        mClusterManager.addItems(mapList);

        // RecyclerView
        Adapter adapter = new Adapter(getContext(), mapList);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        final MyItemRenderer renderer = new MyItemRenderer();
        mClusterManager.setRenderer(renderer);

        googleMap.setOnMarkerClickListener(mClusterManager);

       mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MapItem>() {
           @Override
           public boolean onClusterClick(Cluster<MapItem> cluster) {
               LatLngBounds.Builder builder = LatLngBounds.builder();
               for (ClusterItem item : cluster.getItems()) {
                   builder.include(item.getPosition());
               }
               final LatLngBounds bounds = builder.build();

               try {

                   googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
               } catch (Exception e) {

                   Log.d("EXCEPTION FOUND" , e.toString());
               }

               return true;           }
       });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MapItem>() {
            @Override
            public boolean onClusterItemClick(MapItem mapItem) {

                renderer.getMarker(mapItem).showInfoWindow();
                return false;
            }
        });


        mClusterManager.getMarkerCollection()
                .setOnInfoWindowAdapter(new CustomInfoViewAdapter(LayoutInflater.from(getActivity())));


        googleMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        googleMap.setOnInfoWindowClickListener(mClusterManager);

        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MapItem>() {
            @Override
            public void onClusterItemInfoWindowClick(MapItem mapItem) {
                Log.d("ON CLUSTER","ON CLUSTER ITEM INFO WINDOW");
                FragmentManager fm = getChildFragmentManager();
                ListDetailsFragment listDetailsFragment = ListDetailsFragment.newInstance(mapItem);
                listDetailsFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
                listDetailsFragment.show(fm, "DetailFragment");

            }
        });

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                LatLngBounds.Builder builder = LatLngBounds.builder();
                for (MapItem item : mapList) {
                    builder.include(new LatLng(item.getLat(),item.getLng()));
                }
                final LatLngBounds bounds1 = builder.build();

                try {

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds1, 100));
                } catch (Exception e) {

                    Log.d("EXCEPTION FOUND" , e.toString());
                }
            }
        });

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


    @Override
    public void onClusterInfoWindowClick(Cluster<MapItem> cluster) {

    }

    private class MyItemRenderer extends DefaultClusterRenderer<MapItem> {
        private final IconGenerator mIconGenerator;
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getContext());
        private final ImageView mImageView;
        //  private final ImageView mClusterImageView;
        private final int mDimension;

        private PicassoMarker target;



        public MyItemRenderer() {
            super(getContext(), googleMap, mClusterManager);

            mIconGenerator = new IconGenerator(getActivity().getApplicationContext());

         /*   mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image); */

            mImageView = new ImageView(getActivity());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);


        }

        @Override
        protected void onBeforeClusterItemRendered(MapItem item, MarkerOptions markerOptions) {


            mImageView.setImageResource(R.drawable.user_placeholder);
            Bitmap icon = mIconGenerator.makeIcon();

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(item.getTitle());




        }

        @Override
        protected void onClusterItemRendered(final MapItem clusterItem, final Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);

          /*  PicassoMarker picassoMarker = new PicassoMarker(marker);

            Picasso.with(getContext()).load(clusterItem.getIcon()).into(new PicassoMarker(marker){
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        mImageView.setImageBitmap(bitmap);
                    Bitmap icon = mIconGenerator.makeIcon();
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));

                }
            });  */

            Glide.with(getContext())
                    .load(clusterItem.getIcon())
                    .thumbnail(0.1f)
                    .into(new SimpleTarget<Drawable>(){

                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            mImageView.setImageDrawable(resource);
                            Bitmap icon = mIconGenerator.makeIcon();
                            marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
                        }
                    } );
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MapItem> cluster, MarkerOptions markerOptions) {

            Drawable marker;
            int clusterSize = cluster.getSize();


            View multiProfile = getLayoutInflater(null).inflate(R.layout.marker_layout, null);
            mClusterIconGenerator.setContentView(multiProfile);

            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));


        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    public class PicassoMarker implements Target {
        Marker mMarker;

        PicassoMarker(Marker marker) {
            mMarker = marker;
        }

        @Override
        public int hashCode() {
            return mMarker.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof PicassoMarker) {
                Marker marker = ((PicassoMarker) o).mMarker;
                return mMarker.equals(marker);
            } else {
                return false;
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
           // mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }
}
