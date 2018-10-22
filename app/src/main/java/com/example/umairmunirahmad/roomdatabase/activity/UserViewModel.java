package com.example.umairmunirahmad.roomdatabase.activity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;
    private LiveData<Integer> usersCount;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
        usersCount=userRepository.getUsersCount();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void updateUser(Double userSalary) {
        userRepository.updateUser(userSalary);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<Integer>getUsersCount()
    {
        return usersCount;
    }
}
