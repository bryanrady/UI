package com.bryanrady.ui.activity.recycler.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bryanrady.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 如何动态设置宽高，因为要实现瀑布流效果，就是高度随机，参差不齐那种视觉效果
 * Created by Administrator on 2019/4/25.
 */

public class StaggeredGridRecyclerAdapter extends RecyclerView.Adapter<StaggeredGridRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private List<Integer> mHeightList;//装产出的随机数
    private OnItemClickListener mOnItemClickListener;

    public StaggeredGridRecyclerAdapter(Context context,List<String> list){
        this.mContext = context;
        this.mList = list;

        //记录为每个控件产生的随机高度,避免滑回到顶部出现空白
        mHeightList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int height = new Random().nextInt(200) + 400;//[400,600)的随机数
            mHeightList.add(height);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_staggered_grid_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        viewHolder.mTvTitle.setText(mList.get(position));

        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
        ViewGroup.LayoutParams params = viewHolder.mTvTitle.getLayoutParams();
        params.height = mHeightList.get(position);
        viewHolder.mTvTitle.setLayoutParams(params);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView,layoutPosition);
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickListener != null){
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(viewHolder.itemView,layoutPosition);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 向指定位置添加元素
     */
    public void addItem(int position, String value) {
        if(position > mList.size()) {
            position = mList.size();
        }
        if(position < 0) {
            position = 0;
        }
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        mList.add(position, value);//在集合中添加这条数据
        mHeightList.add(position,new Random().nextInt(200) + 400);//添加一个随机高度,会在onBindViewHolder方法中得到设置
        notifyItemInserted(position);//通知插入了数据
    }

    /**
     * 移除指定位置元素
     */
    public String removeItem(int position) {
        if(position > mList.size()-1) {
            return null;
        }
        mHeightList.remove(position);//删除添加的高度
        String value = mList.remove(position);//所以还需要手动在集合中删除一次
        notifyItemRemoved(position);//通知删除了数据,但是没有删除list集合中的数据
        return value;
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
            mTvTitle = itemView.findViewById(R.id.tv_staggered_recycler_item_title);
        }
    }

}
