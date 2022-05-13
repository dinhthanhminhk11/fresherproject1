package com.example.moneyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.databinding.ActivityUpdateRevenueBinding;
import com.example.moneyapp.viewmodel.MainViewModel;

import java.util.List;

public class UpdateRevenueActivity extends AppCompatActivity {
    private ActivityUpdateRevenueBinding binding;
    private MainViewModel mainViewModel;
    private List<String> listRevenueSourceName;
    private String revenueSourceName;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateRevenueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initDatePicker();
        initSpinner();
        Intent intent = getIntent();
        String id = intent.getStringExtra("idRevenue");
        Log.e("Tab", id);
        Revenue revenue = mainViewModel.getRevenueById(Integer.parseInt(id));
        binding.datePickerActions.setText(revenue.date);
        binding.money.setText(String.valueOf(revenue.money));
        binding.name.setText(revenue.name);
        binding.notesInput.setText(revenue.notes);
        binding.revenueSourceName.setSelection(listRevenueSourceName.indexOf(revenue.nameRevenueSource));
        binding.addButton.setOnClickListener(v -> {
            updateRevenue();
        });
    }

    private void updateRevenue() {
        String name = binding.name.getText().toString();
        String date = binding.datePickerActions.getText().toString();
        String money = binding.money.getText().toString();
        String notes = binding.notesInput.getText().toString();
        Revenue revenue = new Revenue();
        revenue.date = date;
        revenue.nameRevenueSource = revenueSourceName;
        revenue.name = name;
        revenue.money = Double.parseDouble(money);
        revenue.notes = notes;
        mainViewModel.updateRevenue(revenue);
        Toast.makeText(this, "Fix successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initSpinner() {
        listRevenueSourceName = mainViewModel.getAllListNameRevenueSource();
        binding.revenueSourceName.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listRevenueSourceName));
        binding.revenueSourceName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                revenueSourceName = binding.revenueSourceName.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initDatePicker() {
        binding.datePickerActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final java.util.Calendar calendar = java.util.Calendar.getInstance();
                int d = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                int m = calendar.get(java.util.Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateRevenueActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final String NgayGD = year + "-" + +(month + 1) + "-" + dayOfMonth;
                        binding.datePickerActions.setText(NgayGD);
                    }
                }, y, m, d);
                datePickerDialog.show();
            }
        });
    }
}