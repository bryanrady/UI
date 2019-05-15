package com.bryanrady.ui.activity.recycler.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bryanrady.ui.R;


public class SlideConflictSectionRecyclerViewAdapter extends RecyclerView.Adapter<SlideConflictSectionRecyclerViewAdapter.ViewHolder> {

    private final int DEFAULT_COUNT = 20;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_section_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.bind();
    }

    @Override
    public int getItemCount() {
        return DEFAULT_COUNT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       RecyclerView rvHorizontal;
       LinearLayoutManager layoutManager;
       public ViewHolder(View itemView) {
           super(itemView);
           rvHorizontal = itemView.findViewById(R.id.rv_horizontal);
           layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
           rvHorizontal.setLayoutManager(layoutManager);
           rvHorizontal.setAdapter(new SlideConflictFakeRecyclerViewAdapter());
       }

       public void bind() {
           layoutManager.scrollToPosition(0);
       }
   }
}
