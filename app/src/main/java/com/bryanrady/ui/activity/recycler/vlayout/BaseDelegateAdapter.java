package com.bryanrady.ui.activity.recycler.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bryanrady.ui.R;

/**
 * Created by Administrator on 2019/5/16.
 */

public abstract class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseDelegateAdapter.ViewHolder> {

    private LayoutHelper mLayoutHelper;
    private int mCount;

    private LayoutInflater mInflater;

    public BaseDelegateAdapter(Context context, LayoutHelper layoutHelper,int count){
        mInflater = LayoutInflater.from(context);
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.recycler_virtual_layout_item, viewGroup, false));
    }

    public abstract void onBindViewHolder(@NonNull ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_grid_recycler_item_title);
        }

    }

}
