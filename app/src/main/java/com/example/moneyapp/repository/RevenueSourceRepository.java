package com.example.moneyapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moneyapp.data.Database;
import com.example.moneyapp.data.model.Dao;
import com.example.moneyapp.data.model.RevenueSource;

import java.util.List;

public class RevenueSourceRepository {
    private Dao dao;
    public LiveData<List<RevenueSource>> getAllList;
    public RevenueSourceRepository(Application application) {
        Database database = Database.getDatabaseInstance(application);
        dao = database.dao();
        getAllList = dao.getListRevenueSource();
    }
}
