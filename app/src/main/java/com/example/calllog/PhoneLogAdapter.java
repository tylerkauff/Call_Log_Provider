package com.example.calllog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneLogAdapter extends RecyclerView.Adapter<PhoneLogAdapter.ViewHolder> {
    private List<Call> mData;
    private LayoutInflater mInflater;

    PhoneLogAdapter(Context context, List<Call> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.call_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneLogAdapter.ViewHolder holder, int position) {
        Call call = mData.get(position);

        holder.numberTextView.setText(call.getNumber());
        holder.durationTextView.setText(call.getDuration());
        holder.typeTextView.setText(call.getType());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView;
        TextView durationTextView;
        TextView typeTextView;

        public ViewHolder(View view) {
            super(view);
            numberTextView = view.findViewById(R.id.tvNumber);
            durationTextView = view.findViewById(R.id.tvDuration);
            typeTextView = view.findViewById(R.id.tvItem);
        }

    }
}
