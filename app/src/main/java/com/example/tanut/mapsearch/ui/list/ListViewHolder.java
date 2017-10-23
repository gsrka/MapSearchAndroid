package com.example.tanut.mapsearch.ui.list;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanut.mapsearch.R;
import com.example.tanut.mapsearch.ui.main.MainFragment;
import com.example.tanut.mapsearch.ui.map.MapFragment;

/**
 * Created by Abhimanyu on 10/22/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imageView;
    public TextView textViewHeading,textViewLat,textViewLatContent,textViewLong,textViewLongContent,textViewAddress,textViewRating;


    public ListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textViewHeading = (TextView)itemView.findViewById(R.id.textViewHeading);
        textViewLat = (TextView)itemView.findViewById(R.id.textViewLat);
        textViewLatContent = (TextView)itemView.findViewById(R.id.textViewLatContent);
        textViewLong = (TextView)itemView.findViewById(R.id.textViewLong);
        textViewLongContent = (TextView)itemView.findViewById(R.id.textViewLongContent);
        textViewAddress = (TextView)itemView.findViewById(R.id.textViewAddress);
        textViewRating = (TextView)itemView.findViewById(R.id.textViewRating);
    }

    @Override
    public void onClick(View v) {

//        // Load ListDetailsFragment
//        getChildFragmentManager().beginTransaction()
//                .add(R.id.container, ListDetailsFragment.newInstance(), ListDetailsFragment.TAG).addToBackStack(ListDetailsFragment.TAG).commit();
//

    }

}
