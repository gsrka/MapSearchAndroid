package com.example.tanut.mapsearch.data.db.backend;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tanut.mapsearch.data.db.network.model.MapItem;

/**
 * Created by tanut on 8/4/2017.
 */


@Database(entities = {MapItem.class}, version = 1,exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "MAPLIST" ;
    public static final String TABLE_NAME = "items" ;
    private static AppDatabase INSTANCE;

    public abstract MapItemDao itemModel();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
