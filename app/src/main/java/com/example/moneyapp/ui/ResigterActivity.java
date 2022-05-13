package com.example.moneyapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneyapp.data.model.User;
import com.example.moneyapp.databinding.ActivityResigterBinding;
import com.example.moneyapp.repository.UserRepository;
import com.example.moneyapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class ResigterActivity extends AppCompatActivity {
    ActivityResigterBinding binding;
    private MainViewModel mainViewModel;
    private String username, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResigterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.login.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.resigter.setOnClickListener(v -> {
            name = binding.name.getText().toString();
            username = binding.userName.getText().toString();
            password = binding.password.getText().toString();
            createUser(name, username, password, v);
        });
    }

    private void createUser(String name, String username, String password, View view) {
        User user = User.getInstance();
        user.username = username;
        user.password = password;
        user.name = name;
        mainViewModel.insertUser(user);
        Snackbar.make(view, "Create User Successful", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}