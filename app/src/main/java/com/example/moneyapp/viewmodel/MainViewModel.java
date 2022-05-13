package com.example.moneyapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.data.model.RevenueSource;
import com.example.moneyapp.data.model.User;
import com.example.moneyapp.repository.RevenueRepository;
import com.example.moneyapp.repository.UserRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private RevenueRepository revenueRepository;
    public LiveData<List<RevenueSource>> getAllListRevenueSource;
    public LiveData<List<Revenue>> getAllListRevenue;

    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        revenueRepository = new RevenueRepository(application);
        getAllListRevenueSource = revenueRepository.getListRevenueSource;
        getAllListRevenue = revenueRepository.getListRevenue;
    }

    public void insertUser(User user) {
        userRepository.insertUer(user);
    }

    public User getUser(String username, String password) {
        return userRepository.getUser(username, password);
    }

    public void insertRevenueSource(RevenueSource revenueSource) {
        revenueRepository.insertRevenueSource(revenueSource);
    }

    public void updateRevenueSource(RevenueSource revenueSource) {
        revenueRepository.updateRevenueSource(revenueSource);
    }

    public void deleteRevenueSource(int id) {
        revenueRepository.deleteRevenueSource(id);
    }

    public void insertRevenue(Revenue revenue) {
        revenueRepository.insertRevenue(revenue);
    }

    public void updateRevenue(Revenue revenue) {
        revenueRepository.updateRevenue(revenue);
    }

    public void deleteRevenue(int id) {
        revenueRepository.deleteRevenue(id);
    }

    public List<String> getAllListNameRevenueSource() {
        return revenueRepository.getAllListNameRevenueSource();
    }

    public Revenue getRevenueById(int id) {
        return revenueRepository.getRevenueById(id);
    }
}
