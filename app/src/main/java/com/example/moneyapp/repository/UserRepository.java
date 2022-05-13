package com.example.moneyapp.repository;

import android.app.Application;

import com.example.moneyapp.data.Database;
import com.example.moneyapp.data.model.Dao;
import com.example.moneyapp.data.model.User;

public class UserRepository {
    private Dao dao;
    public UserRepository(Application application) {
        Database database = Database.getDatabaseInstance(application);
        dao = database.dao();
    }
    public void insertUer(User user){
        dao.insertUser(user);
    }
    public User getUser(String username , String password){
      return   dao.getUser(username ,password);
    }
}
