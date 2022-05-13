package com.example.moneyapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneyapp.data.model.User;
import com.example.moneyapp.databinding.ActivityLoginBinding;
import com.example.moneyapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    MainViewModel mainViewModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.login.setOnClickListener(v -> {
            String username = binding.userName.getText().toString();
            String password = binding.password.getText().toString();
            login(username, password, v);
        });
        binding.resigter.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ResigterActivity.class));
        });
    }

    private void login(String username, String password, View view) {
        User user = mainViewModel.getUser(username, password);
        if (username.equals(user.username) && password.equals(user.password)) {
            User userStatic = User.getInstance();
            userStatic.name = user.name;
            userStatic.username = user.username;
            userStatic.password = user.password;
            remember();
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Snackbar.make(view, "username and password failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void remember() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username = binding.userName.getText().toString();
        String password = binding.password.getText().toString();
        boolean checkRemember = binding.savePassword.isChecked();
        if (!checkRemember) {
            editor.clear();
        } else {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("remember", true);
        }
        editor.apply();
    }

    private void getInputRemember() {
        boolean checkRemember = sharedPreferences.getBoolean("remember", false);
        if (checkRemember) {
            binding.userName.setText(sharedPreferences.getString("username", ""));
            binding.password.setText(sharedPreferences.getString("password", ""));
        } else {
            binding.userName.setText("");
            binding.password.setText("");
        }
        binding.savePassword.setChecked(checkRemember);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInputRemember();
    }
}