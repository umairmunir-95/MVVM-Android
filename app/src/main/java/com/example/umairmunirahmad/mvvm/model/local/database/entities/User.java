package com.example.umairmunirahmad.mvvm.model.local.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String userDesignation;
    private double userSalary;

    public User(String userName, String userDesignation, double userSalary) {
        this.userName = userName;
        this.userDesignation = userDesignation;
        this.userSalary = userSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public double getUserSalary() {
        return userSalary;
    }
}
