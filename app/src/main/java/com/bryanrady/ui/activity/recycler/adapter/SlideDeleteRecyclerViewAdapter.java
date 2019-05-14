package com.bryanrady.ui.activity.recycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bryanrady.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class SlideDeleteRecyclerViewAdapter extends RecyclerView.Adapter<SlideDeleteRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;

    public SlideDeleteRecyclerViewAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    public void slideDelete(int position) {
        if(position < 0 || position > getItemCount()) {
            return;
        }
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void dragMove(int fromPosition, int toPosition) {
        String prev = mList.remove(fromPosition);
        mList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_linear_vertical_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.mTvTitle.setText(mList.get(position));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickListener != null) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_linear_recycler_item_title);
        }
    }
}
