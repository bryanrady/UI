package com.bryanrady.ui.activity.recycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bryanrady.ui.R;

import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 *
 *      下拉刷新   给点字提示和刷新动画   刷新完成后在顶部展示更新消息
 *
 *      加载更多  正在加载中 给一个loading动画  加载完成后更新数据
 */

public class PullRefreshRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mList;

    public PullRefreshRecyclerViewAdapter(Context context, List<String> list) {
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else if (position % 3 == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = mInflater.inflate(R.layout.item_type1, parent, false);
            return new MyViewHolderType1(view);
        } else if (viewType == 2) {
            View view = mInflater.inflate(R.layout.item_type2, parent, false);
            return new MyViewHolderType2(view);
        } else {
            View view = mInflater.inflate(R.layout.item_type3, parent, false);
            return new MyViewHolderType3(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == 1) {
            ((MyViewHolderType1) holder).mTextView.setText("类型1++++" + mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),"点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (viewType == 2) {
            ((MyViewHolderType2) holder).mTextView.setText("类型2++++" + mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),"点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ((MyViewHolderType3) holder).mTextView.setText("类型3++++" + mList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),"点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolderType1 extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolderType1(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.type1_textView);
        }
    }

    class MyViewHolderType2 extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolderType2(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.type2_textView);
        }
    }

    class MyViewHolderType3 extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolderType3(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.type3_textView);
        }
    }

}
