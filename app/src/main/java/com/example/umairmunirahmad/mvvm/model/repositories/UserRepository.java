package com.example.umairmunirahmad.mvvm.model.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.umairmunirahmad.mvvm.model.local.database.daos.UserDao;
import com.example.umairmunirahmad.mvvm.model.local.database.database.UserDatabase;
import com.example.umairmunirahmad.mvvm.model.local.database.entities.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<Integer> usersCount;

    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
        allUsers=userDao.getAllUsers();
        usersCount=userDao.getUsersCount();
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }
    public void updateUser(Double userSalary)
    {
        new UpdateUserAsyncTask(userDao,userSalary).execute();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<Integer> getUsersCount() {
        return usersCount;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<Double, Void, Void> {
        private UserDao userDao;
        private Double userSalary;
        private UpdateUserAsyncTask(UserDao userDao,Double userSalary) {
            this.userDao=userDao;
            this.userSalary=userSalary;
        }

        @Override
        protected Void doInBackground(Double... doubles) {
            userDao.updateUser(userSalary);
            return null;
        }
    }
}
