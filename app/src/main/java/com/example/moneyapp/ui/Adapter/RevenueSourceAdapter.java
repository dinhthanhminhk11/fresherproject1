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
import com.example.moneyapp.data.model.RevenueSource;

import java.util.List;

public class RevenueSourceAdapter extends RecyclerView.Adapter<RevenueSourceAdapter.MyViewHolder> {
    private Context context;
    private List<RevenueSource> data;
    private Callback callback;

    public RevenueSourceAdapter(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void setData(List<RevenueSource> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface Callback {
        void onClick(RevenueSource revenueSource);

        void onDelete(View view, int id);

        void onEdit(RevenueSource revenueSource, View view);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrevenuesourece, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RevenueSource revenueSource = data.get(position);
        if (revenueSource != null) {
            holder.revenueSourceName.setText(revenueSource.name);
            holder.delete.setOnClickListener(v -> {
                callback.onDelete(v, revenueSource.id);
            });
            holder.edit.setOnClickListener(v -> {
                callback.onEdit(revenueSource, v);
            });
            holder.itemView.setOnClickListener(v -> {
                callback.onClick(revenueSource);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView revenueSourceName;
        private ImageView edit;
        private ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            revenueSourceName = (TextView) itemView.findViewById(R.id.revenueSourceName);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }
}
