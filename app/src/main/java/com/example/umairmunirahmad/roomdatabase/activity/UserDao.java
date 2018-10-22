package com.example.umairmunirahmad.roomdatabase.activity;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from users_table")
    LiveData<List<User>>getAllUsers();

    @Query("Select count(*) from users_table")
    LiveData<Integer> getUsersCount();

    @Query("UPDATE users_table SET userSalary = :userSalary  WHERE  userName='umair'")
    void updateUser(Double userSalary);

}
