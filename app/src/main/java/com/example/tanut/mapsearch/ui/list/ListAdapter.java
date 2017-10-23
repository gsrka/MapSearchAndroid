package com.example.tanut.mapsearch.ui.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;
import com.example.tanut.mapsearch.ui.map.Adapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abhimanyu on 10/22/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{

    private List<MapItem> mapItems;
    private Context context;

    public ListAdapter(List<MapItem> items) {

        this.mapItems = items;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item,null);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        MapItem myItem = mapItems.get(position);
        Log.e("item",myItem.getTitle());
        holder.textViewHeading.setText(myItem.getTitle());
        holder.textViewAddress.setText(myItem.getFormattedAddress());
        Picasso.with(context).load(myItem.getIcon()).into(holder.imageView);
        holder.textViewLatContent.setText((myItem.getGeometry().getLocation().getLat().toString()));
        holder.textViewLongContent.setText((myItem.getGeometry().getLocation().getLng().toString()));
        holder.textViewRating.setText(myItem.getTitle());
    }

    @Override
    public int getItemCount() {

        return mapItems.size();
    }
}
