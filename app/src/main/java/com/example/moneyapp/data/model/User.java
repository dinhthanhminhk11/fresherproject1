package com.example.moneyapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "user_username")
    public String username;
    @ColumnInfo(name = "user_password")
    public String password;
    @ColumnInfo(name = "user_name")
    public String name;
    private static final User instance = new User();
    public User() {
    }
    public static User getInstance() {
        return instance;
    }

}
