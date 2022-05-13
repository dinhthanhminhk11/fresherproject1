package com.example.moneyapp.ui.frament;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.RevenueSource;
import com.example.moneyapp.databinding.FragmentRevenueSourceBinding;
import com.example.moneyapp.repository.RevenueRepository;
import com.example.moneyapp.ui.Adapter.RevenueSourceAdapter;
import com.example.moneyapp.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RevenueSourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RevenueSourceFragment extends Fragment {
    private TextView title;
    private EditText revenuesource;
    private LinearLayout contentLinearLayout;
    private Button add;
    private Button cancel;
    private MainViewModel mainViewModel;
    FragmentRevenueSourceBinding binding;
    private RevenueSourceAdapter adapter;
    public List<RevenueSource> data;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RevenueSourceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RevenueSourceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RevenueSourceFragment newInstance(String param1, String param2) {
        RevenueSourceFragment fragment = new RevenueSourceFragment();
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

        // Inflate the layout for this fragment
        binding = FragmentRevenueSourceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        adapter = new RevenueSourceAdapter(getActivity(), new RevenueSourceAdapter.Callback() {
            @Override
            public void onClick(RevenueSource revenueSource) {

            }

            @Override
            public void onDelete(View view, int id) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("Notification").setMessage("Are you sure you want to delete?").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainViewModel.deleteRevenueSource(id);
                        Snackbar.make(view, "Delete Successful", Snackbar.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void onEdit(RevenueSource revenueSource, View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_addrevenuesource);
                title = (TextView) dialog.findViewById(R.id.title);
                revenuesource = (EditText) dialog.findViewById(R.id.revenuesource);
                contentLinearLayout = (LinearLayout) dialog.findViewById(R.id.contentLinearLayout);
                add = (Button) dialog.findViewById(R.id.add);
                cancel = (Button) dialog.findViewById(R.id.cancel);
                title.setText("Update");
                add.setText("Update");
                revenuesource.setText(revenueSource.name);
                add.setOnClickListener(view -> {
                    String revenueSourceName = revenuesource.getText().toString();
                    editRevenueSource(revenueSourceName, v);
                    dialog.cancel();
                });
                cancel.setOnClickListener(view -> {
                    dialog.dismiss();
                });
                dialog.show();
            }
        });


        binding.addButton.setOnClickListener(v -> {
            showDialog(false, v);
        });
    }

    private void editRevenueSource(String revenueSourceName, View view) {
        RevenueSource revenueSource = new RevenueSource();
        revenueSource.name = revenueSourceName;
        mainViewModel.updateRevenueSource(revenueSource);
        Snackbar.make(view, "Update successful", Snackbar.LENGTH_SHORT).show();
    }

    private void createRevenueSourceName(String revenueSourceName, View view) {
        RevenueSource revenueSource = new RevenueSource();
        revenueSource.name = revenueSourceName;
        mainViewModel.insertRevenueSource(revenueSource);
        Snackbar.make(view, "Create Successful", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainViewModel.getAllListRevenueSource.observe(this, revenueSources -> {
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    adapter.setData(revenueSources);
                    binding.recyclerview.setAdapter(adapter);
                }
        );
    }

    private void showDialog(boolean isForEdit, View v) {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_addrevenuesource);
        title = (TextView) dialog.findViewById(R.id.title);
        revenuesource = (EditText) dialog.findViewById(R.id.revenuesource);
        contentLinearLayout = (LinearLayout) dialog.findViewById(R.id.contentLinearLayout);
        add = (Button) dialog.findViewById(R.id.add);
        cancel = (Button) dialog.findViewById(R.id.cancel);
        add.setOnClickListener(view -> {
            String revenueSourceName = revenuesource.getText().toString();
            createRevenueSourceName(revenueSourceName, v);
            dialog.cancel();
        });
        cancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}