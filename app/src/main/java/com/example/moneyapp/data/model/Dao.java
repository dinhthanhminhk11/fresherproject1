package com.example.moneyapp.data.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("Select * from revenue")
    LiveData<List<Revenue>> getListRevenue();

    @Query("Select * from revenueSource")
    LiveData<List<RevenueSource>> getListRevenueSource();

    @Insert
    void insertRevenue(Revenue... revenues);

    @Insert
    void insertRevenueSource(RevenueSource... revenuesSource);

    @Update
    void updateRevenueSource(RevenueSource revenue);

    @Update
    void updateRevenue(Revenue revenue);

    @Query("Delete from revenue where id=:id")
    void deleteRevenue(int id);

    @Query("Delete from revenueSource where id=:id")
    void deleteRevenueSource(int id);


    @Query("Select revenueSource.revenueSource_name from revenueSource")
    List<String> getAllListNameRevenueSource();

    @Insert
    void insertUser(User user);

    @Query("Select * from User where user_username=:username and user_password=:password")
    User getUser(String username, String password);

    @Query("Select * from revenue where id=:id")
    Revenue getRevenue(int id);


}
