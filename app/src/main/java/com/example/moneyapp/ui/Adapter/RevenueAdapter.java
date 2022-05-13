package com.example.moneyapp.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneyapp.R;
import com.example.moneyapp.data.model.Revenue;

import java.sql.Ref;
import java.util.List;

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.MyViewHolder> {
    private List<Revenue> data;
    private Callback callback;
    private Context context;

    public RevenueAdapter(Callback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void setData(List<Revenue> list) {
        this.data = list;
    }

    public interface Callback {
        void onClick(Revenue revenue);
    }

    @NonNull
    @Override
    public RevenueAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrevenue, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevenueAdapter.MyViewHolder holder, int position) {
        Revenue revenue = data.get(position);
        if (revenue != null) {
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(revenue);
            });
            holder.revenueName.setText(revenue.name);
            holder.revenueSourceName.setText(revenue.nameRevenueSource);
            holder.revenueDate.setText(revenue.date);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView revenueName;
        private TextView revenueSourceName;
        private TextView revenueDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            revenueName = (TextView) itemView.findViewById(R.id.revenueName);
            revenueSourceName = (TextView) itemView.findViewById(R.id.revenueSourceName);
            revenueDate = (TextView) itemView.findViewById(R.id.revenueDate);

        }
    }
}
