package com.bryanrady.ui.activity.recycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bryanrady.ui.R;

import java.util.List;

/**
 * Created by Administrator on 2019/4/25.
 */

public class DragSwitchRecyclerViewAdapter extends RecyclerView.Adapter<DragSwitchRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    public DragSwitchRecyclerViewAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    public void dragMove(int fromPosition, int toPosition) {
        String prev = mList.remove(fromPosition);
        mList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_grid_layout_drag_switch_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.mTvTitle.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_drag_switch_item_title);
        }
    }
}
