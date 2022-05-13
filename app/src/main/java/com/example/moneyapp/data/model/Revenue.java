package com.example.moneyapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "revenue")
public class Revenue {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "revenue_name")
    public String name;

    @ColumnInfo(name = "revenue_nameRevenueSource")
    public String nameRevenueSource;

    @ColumnInfo(name = "revenue_money")
    public double money;

    @ColumnInfo(name = "revenue_date")
    public String date;

    @ColumnInfo(name = "revenue_notes")
    public String notes;

    public Revenue() {

    }


}
