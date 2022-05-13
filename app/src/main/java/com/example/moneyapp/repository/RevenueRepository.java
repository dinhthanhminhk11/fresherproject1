package com.example.moneyapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.moneyapp.data.Database;
import com.example.moneyapp.data.model.Dao;
import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.data.model.RevenueSource;

import java.util.List;

public class RevenueRepository {
    public Dao dao;
    public LiveData<List<Revenue>> getListRevenue;
    public LiveData<List<RevenueSource>> getListRevenueSource;

    public RevenueRepository(Application application) {
        Database database = Database.getDatabaseInstance(application);
        dao = database.dao();
        getListRevenue = dao.getListRevenue();
        getListRevenueSource = dao.getListRevenueSource();
    }

    public void insertRevenue(Revenue revenue) {
        dao.insertRevenue(revenue);
    }

    public void updateRevenue(Revenue revenue) {
        dao.updateRevenue(revenue);
    }

    public void deleteRevenue(int id) {
        dao.deleteRevenue(id);
    }


    public void insertRevenueSource(RevenueSource revenueSource) {
        dao.insertRevenueSource(revenueSource);
    }

    public void updateRevenueSource(RevenueSource revenueSource) {
        dao.updateRevenueSource(revenueSource);
    }

    public void deleteRevenueSource(int id) {
        dao.deleteRevenueSource(id);
    }

    public List<String> getAllListNameRevenueSource() {
        return dao.getAllListNameRevenueSource();
    }
    public Revenue getRevenueById(int id){
        return dao.getRevenue(id);
    }
}