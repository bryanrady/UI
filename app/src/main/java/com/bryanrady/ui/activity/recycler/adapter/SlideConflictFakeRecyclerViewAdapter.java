package com.bryanrady.ui.activity.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bryanrady.ui.R;


public class SlideConflictFakeRecyclerViewAdapter extends RecyclerView.Adapter<SlideConflictFakeRecyclerViewAdapter.ViewHolder> {

    private final int DEFAULT_COUNT = 20;
    private int mCount = DEFAULT_COUNT;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card_hor_item, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.view.setText("Text :" + position);
    }

    public void set(int value) {
        mCount = value;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = (TextView)itemView.findViewById(R.id.item_view_holder_text);
        }
    }
}
