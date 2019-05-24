package com.bryanrady.ui.activity.recycler.layout_manager;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.recycler.layout_manager.card.CardItemView;
import com.bryanrady.ui.view.recycler.layout_manager.card.CardLayoutManager;
import com.bryanrady.ui.view.recycler.layout_manager.flow_layout.FlowLayoutManager;
import com.bryanrady.ui.view.recycler.layout_manager.flow_layout.FlowLayoutManager2;

import java.util.Random;

public class FlowLayoutManagerActivity extends StatusBarBaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_flow_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.flow_layout_recycler_view);
        init();
    }

    private void init() {
        mRecyclerView.setLayoutManager(new FlowLayoutManager());
        mRecyclerView.setAdapter(new MyAdapter());
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private String[] mData = new String[300];

        public MyAdapter(){
            initData();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_flow_layout_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.btn.setText(mData[position]);
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            Button btn;
            public ViewHolder(View itemView) {
                super(itemView);
                btn = itemView.findViewById(R.id.btn);
            }
        }

        private void initData(){
            String[] str = new String[]{"adf","gfgfadfaf","gfgfadfafadf","gfgfadfafdfa","gfgfadfafadffad","gfgfadfafadfasfsfd","gfg","gfgfadfafadfadfafadfa"};
            for (int i  = 0;i<mData.length;i++){
                mData[i] = str[(int) (Math.random()*str.length)];
            }
        }
    }
}
