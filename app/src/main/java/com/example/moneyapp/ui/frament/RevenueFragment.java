package com.example.moneyapp.ui.frament;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.Revenue;
import com.example.moneyapp.databinding.FragmentRevenueBinding;
import com.example.moneyapp.ui.Adapter.RevenueAdapter;
import com.example.moneyapp.ui.AddRevenueActivity;
import com.example.moneyapp.ui.UpdateRevenueActivity;
import com.example.moneyapp.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.SQLOutput;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RevenueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RevenueFragment extends Fragment {
    private FragmentRevenueBinding binding;
    private List<Revenue> listData;
    private MainViewModel mainViewModel;
    private RevenueAdapter adapter;
    private LinearLayout bottomsheetcontainer;
    private TextView edit;
    private TextView delete;
    private TextView txtHuy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RevenueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RevenueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RevenueFragment newInstance(String param1, String param2) {
        RevenueFragment fragment = new RevenueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRevenueBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        adapter = new RevenueAdapter(new RevenueAdapter.Callback() {
            @Override
            public void onClick(Revenue revenue) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottomsheet, (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomsheetcontainer));
                edit = (TextView) bottomSheetView.findViewById(R.id.edit);
                delete = (TextView) bottomSheetView.findViewById(R.id.delete);
                txtHuy = (TextView) bottomSheetView.findViewById(R.id.txt_Huy);

                edit.setOnClickListener(v -> {
                    editRevenue(revenue);
                    bottomSheetDialog.dismiss();
                });
                delete.setOnClickListener(v -> {
                    removeRevenue(revenue.id);
                    bottomSheetDialog.dismiss();
                });
                txtHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        }, getActivity());
        binding.addButton.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddRevenueActivity.class));
        });

    }

    private void editRevenue(Revenue revenue) {
        System.out.println(revenue.id);
        Intent intent = new Intent(getActivity(), UpdateRevenueActivity.class);
        intent.putExtra("idRevenue", String.valueOf(revenue.id));
        startActivity(intent);
    }

    private void removeRevenue(int id) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Are you delete you want to escape").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.deleteRevenue(id);
                Toast.makeText(getActivity(), "Delete Successful", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mainViewModel.getAllListRevenue.observe(this, revenueSources -> {
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter.setData(revenueSources);
                    binding.recyclerview.setAdapter(adapter);
                }
        );
    }
}