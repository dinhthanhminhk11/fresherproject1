package com.example.moneyapp.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moneyapp.data.model.Dao;
import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.data.model.RevenueSource;
import com.example.moneyapp.data.model.User;

@androidx.room.Database(entities = {User.class , RevenueSource.class , Revenue.class} , version = 1)
public abstract class Database extends RoomDatabase {
    public static Database INSTANCE;
    public abstract Dao dao();
    public static Database getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "DatabaseVer3")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
