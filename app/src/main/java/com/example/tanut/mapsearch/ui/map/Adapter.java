package com.example.tanut.mapsearch.ui.map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.data.db.network.model.MapItem;

import java.util.List;

/**
 * Created by nilay on 10/22/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {

    private List<MapItem> mapItems;
    private Context context;

    public Adapter(Context context, List<MapItem> myItems){
        this.mapItems =myItems;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_recycler,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        MapItem myItem = mapItems.get(position);
        Log.e("item",myItem.getTitle());
        holder.textView.setText(myItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mapItems.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public CustomViewHolder (View view){
           super(view);
            this.textView = (TextView) view.findViewById(R.id.search_title);
        }

    }
}
