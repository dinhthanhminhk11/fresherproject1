package com.example.moneyapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "revenueSource")
public class RevenueSource {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "revenueSource_name")
    public String name;
}
