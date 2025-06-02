package com.example.sqllitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private ArrayList<Record> recordArrayList;
    private Context context;

    public RecordAdapter(ArrayList<Record> recordArrayList, Context context) {
        this.recordArrayList = recordArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cardrecord, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = recordArrayList.get(position);
        holder.tvName.setText("Name:"+record.getName());
        holder.tvDOB.setText("DOB: " + record.getDob());
        holder.tvQualification.setText("Qualification: " + record.getQualification());
    }

    @Override
    public int getItemCount() {
        return recordArrayList.size();
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDOB, tvQualification;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDOB = itemView.findViewById(R.id.tvDOB);
            tvQualification = itemView.findViewById(R.id.tvQualification);
        }
    }
}
