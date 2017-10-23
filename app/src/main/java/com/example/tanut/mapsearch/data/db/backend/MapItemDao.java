package com.example.tanut.mapsearch.data.db.backend;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tanut.mapsearch.data.db.network.model.MapItem;

import java.util.List;

/**
 * Created by tanut on 10/22/2017.
 */
@Dao
public  interface MapItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(MapItem task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItemList(List<MapItem> items);

    @Query("select * from "+ AppDatabase.TABLE_NAME )
    public List<MapItem> getAllItem();

    @Query("select * from "+ AppDatabase.TABLE_NAME +" where tag = :tag")
    public List<MapItem> getItemForTag(String tag);

    @Query("delete from "+ AppDatabase.TABLE_NAME +" where tag = :tag")
    void removeItemForTag(String tag);

    @Query("delete from "+ AppDatabase.TABLE_NAME)
    void removeAllItem();
}
