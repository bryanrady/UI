package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.SlideConflictSectionRecyclerViewAdapter;
import com.bryanrady.ui.view.recycler.slide_conflict.SlideConflictRecyclerView;

/**
 * Created by Administrator on 2019/5/14.
 */

public class SlideConflictRecyclerViewActivity extends AppCompatActivity {

    private SlideConflictRecyclerView mRvNormal;
    private SlideConflictRecyclerView mRvBetter;
    private SlideConflictRecyclerView mRvFeedRoot;
    private SlideConflictSectionRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_slide_conflict);

        mRvNormal = findViewById(R.id.rv_normal);
        mRvBetter = findViewById(R.id.rv_better);
        mRvFeedRoot = findViewById(R.id.rv_feed);

        mRvNormal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvBetter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvFeedRoot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new SlideConflictSectionRecyclerViewAdapter();
        mRvNormal.setAdapter(mAdapter);
        mRvBetter.setAdapter(mAdapter);
        mRvFeedRoot.setAdapter(mAdapter);
    }
}
