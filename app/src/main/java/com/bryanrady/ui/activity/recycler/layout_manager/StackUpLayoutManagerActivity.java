package com.bryanrady.ui.activity.recycler.layout_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.recycler.layout_manager.echelon.EchelonLayoutManager;
import com.bryanrady.ui.view.recycler.layout_manager.stack_up.StackUpLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2019/5/21.
 */

public class StackUpLayoutManagerActivity extends StatusBarBaseActivity {

    private RecyclerView mRecyclerView;
    private StackUpLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_stack_up_layout);
        mRecyclerView = findViewById(R.id.stack_up_layout_recycler_view);

        mLayoutManager = new StackUpLayoutManager(1.5f, 0.85f);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int[] imgs = {
                R.mipmap.skid_right_1,
                R.mipmap.skid_right_2,
                R.mipmap.skid_right_3,
                R.mipmap.skid_right_4,
                R.mipmap.skid_right_5,
                R.mipmap.skid_right_6,
                R.mipmap.skid_right_7,

        };
        String[] titles = {"Acknowl", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence"};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(StackUpLayoutManagerActivity.this)
                    .inflate(R.layout.recycler_stack_up_layout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Glide.with(StackUpLayoutManagerActivity.this)
                    .load(imgs[position % 7])
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.imgBg);

            holder.tvTitle.setText(titles[position % 6]);
            holder.imgBg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StackUpLayoutManagerActivity.this, StackUpLayoutManagerDetailActivity.class);
                    intent.putExtra("img", imgs[position % 7]);
                    intent.putExtra("title", titles[position % 6]);
                    Pair<View, String> p1 = Pair.create((View)holder.imgBg, "img_view_1");
                    Pair<View, String> p2 = Pair.create((View)holder.tvTitle, "title_1");
                    Pair<View, String> p3 = Pair.create((View)holder.tvBottom, "tv_bottom");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(StackUpLayoutManagerActivity.this, p1,p2,p3);
                    startActivity(intent,options.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            TextView tvTitle;
            TextView tvBottom;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvBottom = itemView.findViewById(R.id.tv_bottom);
            }
        }
    }

}
