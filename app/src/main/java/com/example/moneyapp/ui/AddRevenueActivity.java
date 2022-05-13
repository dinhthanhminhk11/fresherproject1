package com.example.moneyapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.data.model.User;
import com.example.moneyapp.databinding.ActivityAddRevenueBinding;
import com.example.moneyapp.viewmodel.MainViewModel;

import java.util.List;

public class AddRevenueActivity extends AppCompatActivity {
    private ActivityAddRevenueBinding binding;
    private MainViewModel mainViewModel;
    private List<String> listRevenueSourceName;
    private String revenueSourceName;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRevenueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Revenue");
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initSpinner();
        initDatePicker();
        binding.addButton.setOnClickListener(v -> {
            createRevenue();
        });
    }

    private void createRevenue() {
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
        mainViewModel.insertRevenue(revenue);
        Toast.makeText(this, "Add successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRevenueActivity.this, new DatePickerDialog.OnDateSetListener() {
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