package com.bryanrady.ui.activity.recycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.recycler.drag.RecyclerUtils;
import com.bryanrady.ui.view.recycler.drag.SlideRecyclerItemView;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class QQSlideRecyclerViewAdapter extends RecyclerView.Adapter<QQSlideRecyclerViewAdapter.ViewHolder>
        implements SlideRecyclerItemView.OnSlidingLayoutListener{

    private Context mContext;
    private List<String> mList;

    private SlideRecyclerItemView mItemView;
    private OnSlidingViewClickListener mListener;

    public QQSlideRecyclerViewAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }

    /**
     * 删除
     * @param position
     */
    public void removeData(int position) {
        if(position < 0 || position > getItemCount()) {
            return;
        }
        mList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mItemView.closeSlideLayout();
        mItemView = null;

    }

    /**
     * 判断是否有菜单打开
     * @return
     */
    public boolean menuIsOpen() {
        if(mItemView != null && mItemView.slideLayoutIsOpen()){
            return true;
        }
        return false;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.recycler_qq_slide_view_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.mTvTitle.setText(mList.get(position));

        viewHolder.mLeftLayout.getLayoutParams().width = RecyclerUtils.getScreenWidth(mContext);
        viewHolder.mLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();
                } else {
                    if(mListener != null){
                        int pos = viewHolder.getLayoutPosition();
                        mListener.onItemClick(v, pos);
                    }
                }
            }
        });
        viewHolder.mLeftLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();
                } else {
                    if(mListener != null){
                        int pos = viewHolder.getLayoutPosition();
                        mListener.onItemLongClick(v, pos);
                    }
                }
                return true;
            }
        });

        viewHolder.mTvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int pos = viewHolder.getLayoutPosition();
                    mListener.onItemSettingClick(v, pos);
                }
            }
        });

        viewHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int pos = viewHolder.getLayoutPosition();
                    mListener.onItemDeleteClick(v, pos);
                }
            }
        });

        viewHolder.mTvRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int pos = viewHolder.getLayoutPosition();
                    mListener.onItemReadClick(v, pos);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    //设置在滑动侦听器上
    public void setOnSlidindViewClickListener(OnSlidingViewClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onMenuIsOpen(SlideRecyclerItemView recyclerItemView) {
        mItemView = recyclerItemView;
    }

    @Override
    public void onDownOrMove(SlideRecyclerItemView recyclerItemView) {
        if(menuIsOpen()){
            if(mItemView != recyclerItemView){
                closeMenu();
            }
        }
    }

    // 在滑动视图上单击侦听器
    public interface OnSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

        void onItemSettingClick(View view, int position);
        void onItemDeleteClick(View view, int position);
        void onItemReadClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout mLeftLayout;
        private TextView mTvTitle;

        private TextView mTvSetting;
        private TextView mTvDelete;
        private TextView mTvRead;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SlideRecyclerItemView slideRecyclerItemView = (SlideRecyclerItemView) itemView;
            mLeftLayout = slideRecyclerItemView.findViewById(R.id.layout_left);
            mTvTitle = slideRecyclerItemView.findViewById(R.id.tv_recycler_item_title);

            mTvSetting = slideRecyclerItemView.findViewById(R.id.tv_slide_item_setting);
            mTvDelete = slideRecyclerItemView.findViewById(R.id.tv_slide_item_delete);
            mTvRead = slideRecyclerItemView.findViewById(R.id.tv_slide_item_read);

            ((SlideRecyclerItemView)itemView).setOnSlidingLayoutListener(QQSlideRecyclerViewAdapter.this);
        }
    }
}
