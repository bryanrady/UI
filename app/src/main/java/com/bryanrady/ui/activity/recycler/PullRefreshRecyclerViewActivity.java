package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.PullRefreshRecyclerViewAdapter;
import com.bryanrady.ui.view.recycler.pull_refresh.MessageRelativeLayout;
import com.bryanrady.ui.view.recycler.pull_refresh.PullRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PullRefreshRecyclerViewActivity extends AppCompatActivity {

    private PullRefreshRecyclerView mArticleContent;
    private MessageRelativeLayout mArticleMessageContent;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_pull_refresh);

        initView();
    }

    private void initView() {
        mArticleContent = (PullRefreshRecyclerView) findViewById(R.id.pullRefresh_article_RecyclerView);
        mArticleMessageContent = (MessageRelativeLayout) findViewById(R.id.pullRefresh_article_message_RelativeLayout);
        mArticleContent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mList = new ArrayList<>();
        for (int i=0;i<20;i++){
            mList.add("old data "+ i);
        }
        mArticleContent.setAdapter(new PullRefreshRecyclerViewAdapter(this,mList));

        mArticleContent.setOnRefreshListener(new PullRefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                final List<String> tempList = new ArrayList<>();
                for (int i=0;i<10;i++){
                    tempList.add("new data "+ i);
                }
                mList.addAll(0,tempList);

                //结束刷新
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mArticleMessageContent.setMessage("更新了"+tempList.size()+"条新数据");
                        mArticleContent.stopRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //加载更多
                List<String> tempList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    tempList.add("more data" + i);
                }
                mList.addAll(tempList);
                //结束加载
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mArticleContent.stopLoadMore();
                    }
                }, 2000);
            }
        });

    }
}
