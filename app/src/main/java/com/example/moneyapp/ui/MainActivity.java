package com.example.moneyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.User;
import com.example.moneyapp.databinding.ActivityMainBinding;
import com.example.moneyapp.ui.frament.HomeFragment;
import com.example.moneyapp.ui.frament.UserFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        User user = User.getInstance();
        getSupportActionBar().setTitle("Hello, " + user.name);


        loadFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.user:
                    loadFragment(new UserFragment());
                    return true;
            }
            return false;
        });

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}