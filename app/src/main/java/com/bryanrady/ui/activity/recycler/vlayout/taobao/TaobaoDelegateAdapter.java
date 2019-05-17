package com.bryanrady.ui.activity.recycler.vlayout.taobao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2019/5/16.
 */

public abstract class TaobaoDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private int mItemViewType = -1;

    private LayoutInflater mInflater;

    public TaobaoDelegateAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int itemViewType){
        mInflater = LayoutInflater.from(context);
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mCount = count;
        this.mItemViewType = itemViewType;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemViewType;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mItemViewType) {
            return new BaseViewHolder(mInflater.inflate(mLayoutId, viewGroup, false));
        }
        return null;
    }

    public abstract void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position);

    @Override
    public int getItemCount() {
        return mCount;
    }
}
