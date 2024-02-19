package com.example.roomwwthspinner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PointLocation.class},version=1)
public abstract class DBPointLocation extends RoomDatabase {

    public abstract DAOPL getDao();
    static DBPointLocation INSTANCE;
    public static DBPointLocation getDB(Context context){
        if (INSTANCE==null){
            synchronized (DAOPL.class){
                if (INSTANCE==null){
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),DBPointLocation.class,"DB"
                            ).build();
                }
            }
        }
        return INSTANCE;
    }
}