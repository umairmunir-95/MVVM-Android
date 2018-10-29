package com.example.umairmunirahmad.mvvm.view.ui.activities;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.umairmunirahmad.mvvm.R;
import com.example.umairmunirahmad.mvvm.model.local.database.entities.User;
import com.example.umairmunirahmad.mvvm.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName,etDesignation,etSalary;
    Button btnSave,btnUpdate;

    private UserViewModel userViewModel;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.et_name);
        etDesignation=findViewById(R.id.et_designation);
        etSalary=findViewById(R.id.et_salary);
        btnSave=findViewById(R.id.btn_save);
        btnUpdate=findViewById(R.id.btn_update);

        userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {

              for(int i=0;i<userViewModel.getAllUsers().getValue().size();i++) {
                  User user=userViewModel.getAllUsers().getValue().get(i);
                  Log.d(TAG, "\n Data : "+user.getId()+" , "+user.getUserName() + " , " + user.getUserDesignation() + " , " + user.getUserSalary());
              }
            }
        });

        userViewModel.getUsersCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer usersCount) {
                Log.d(TAG, "Count : "+usersCount);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUser();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void insertUser() {
        String userName = etName.getText().toString();
        String userDesignation = etDesignation.getText().toString();
        double userSalary = Double.valueOf(etSalary.getText().toString());
        User user=new User(userName,userDesignation,userSalary);
        userViewModel.insertUser(user);
        Toast.makeText(getApplicationContext(),"user added successfully",Toast.LENGTH_LONG).show();
    }

    private void updateUser()
    {
        double userSalary = Double.valueOf(etSalary.getText().toString());
        userViewModel.updateUser(userSalary);
    }
}
