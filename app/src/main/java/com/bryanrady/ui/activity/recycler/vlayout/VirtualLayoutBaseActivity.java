package com.bryanrady.ui.activity.recycler.vlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.BaseLayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixAreaAdjuster;
import com.alibaba.android.vlayout.layout.FixAreaLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bryanrady.ui.R;
import com.bryanrady.ui.view.svg.ProvinceItem;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/16.
 */

public class VirtualLayoutBaseActivity extends AppCompatActivity {

    private List<String> mList;

    private RecyclerView mRecyclerView;
    private List<DelegateAdapter.Adapter> mAdapterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_virtual_layout_base);
        mRecyclerView = findViewById(R.id.recycler_virtual_layout_base);

        init();
    }

    private void init() {
        mList = new ArrayList<>();
        for (int i=0;i<20;i++){
            mList.add("第" + i + "行");
        }

        mAdapterList = new LinkedList<>();

        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0,10);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        initLinearLayoutHelper();           //线性布局
        initGridLayoutHelper();             //网格布局

        initSingleLayoutHelper();           //通栏布局
        initColumnLayoutHelper();           //栏格布局

        initOnePlusNLayoutHelper();         //1拖N布局

        initStaggeredGridLayoutHelper();    //瀑布流布局

        initFixLayoutHelper();              //固定布局
        initScrollFixLayoutHelper();        //固定布局

        initFloatLayoutHelper();            //浮动布局

        initStickyLayoutHelper();           //吸边布局

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.setAdapters(mAdapterList);
        mRecyclerView.setAdapter(delegateAdapter);
        mRecyclerView.setBackgroundColor(Color.WHITE);
    }

    private void initLinearLayoutHelper() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE); // 设置背景颜色
        linearLayoutHelper.setMargin(5, 20, 5, 20);
        linearLayoutHelper.setMarginTop(300);
        linearLayoutHelper.setAspectRatio(6);       //设置设置布局内每行布局的宽与高的比
        linearLayoutHelper.setDividerHeight(10);    //设置分割线高度
        BaseDelegateAdapter linearLayoutAdapter = new BaseDelegateAdapter(this,linearLayoutHelper, 5) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("LinearLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(linearLayoutAdapter);
    }

    private void initGridLayoutHelper(){
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4,20);
        gridLayoutHelper.setMargin(5, 20, 5, 20);
        gridLayoutHelper.setAspectRatio(3);     //设置设置布局内每行布局的宽与高的比
        gridLayoutHelper.setVGap(20);           //设置子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);           //设置子元素之间的水平间距
        gridLayoutHelper.setWeights(new float[]{35,20,30,15});  //设置item之间的权重
        gridLayoutHelper.setAutoExpand(true);   //是否自动填充空白区域

        BaseDelegateAdapter gridLayoutAdapter = new BaseDelegateAdapter(this,gridLayoutHelper,20) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("GridLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(gridLayoutAdapter);
    }

    private void initStaggeredGridLayoutHelper(){
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper(3);
        staggeredGridLayoutHelper.setMargin(5, 20, 5, 20);
        staggeredGridLayoutHelper.setAspectRatio(3);     //设置设置布局内每行布局的宽与高的比
        staggeredGridLayoutHelper.setVGap(20);           //设置子元素之间的垂直间距
        staggeredGridLayoutHelper.setHGap(20);           //设置子元素之间的水平间距

        BaseDelegateAdapter staggeredGridLayoutAdapter = new BaseDelegateAdapter(this,staggeredGridLayoutHelper,20) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                //随机改变item高度 实现瀑布流效果
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150 + position % 5 * 20);
                holder.itemView.setLayoutParams(layoutParams);
                if(position == 0){
                    holder.mTv.setText("StaggeredGridLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(staggeredGridLayoutAdapter);
    }

    private void initSingleLayoutHelper(){
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setAspectRatio(5);
        singleLayoutHelper.setMargin(5,20,5,20);
        BaseDelegateAdapter singleLayoutAdapter = new BaseDelegateAdapter(this,singleLayoutHelper,1) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("singleLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(singleLayoutAdapter);
    }

    private void initColumnLayoutHelper(){
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setAspectRatio(5);
        columnLayoutHelper.setMargin(5,20,5,20);
        columnLayoutHelper.setWeights(new float[]{50,20,30});
        BaseDelegateAdapter columnLayoutAdapter = new BaseDelegateAdapter(this,columnLayoutHelper,3) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("ColumnLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(columnLayoutAdapter);
    }

    private void initOnePlusNLayoutHelper(){
        //将布局分为不同比例，最多是1拖4,,即5个
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper();
        onePlusNLayoutHelper.setMargin(5,20,5,20);
        //第一个50代表左边第一个 第二个50参数右边上面一个
        //第一个20代表第二行第一个    第二个20代表第二行第2个  第三个20代表第二行第3个
        onePlusNLayoutHelper.setColWeights(new float[]{40,60,20,20,20});
    //    onePlusNLayoutHelper.setColWeights(new float[]{40,60}); //这种效果和上面设置的效果是一样的 他会平均分配60成3份

        BaseDelegateAdapter onePlusNLayoutAdapter = new BaseDelegateAdapter(this,onePlusNLayoutHelper,5) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("OnePlusNLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
            }
        };
        mAdapterList.add(onePlusNLayoutAdapter);
    }

    private void initFixLayoutHelper(){
        //布局说明：布局里的Item 固定位置    固定在屏幕某个位置，且不可拖拽 & 不随页面滚动而滚动
        //参数： 基准位置(alignType)  基准位置的偏移量x  基准位置的偏移量y
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT,10,30);
        fixLayoutHelper.setBgColor(Color.GREEN);
        fixLayoutHelper.setMargin(5,20,5,20);
        fixLayoutHelper.setAspectRatio(6);
        fixLayoutHelper.setSketchMeasure(false);
        BaseDelegateAdapter fixLayoutAdapter = new BaseDelegateAdapter(this,fixLayoutHelper,1) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("FixLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
                holder.itemView.setAlpha(0.7f);
            }
        };
        mAdapterList.add(fixLayoutAdapter);
    }

    private void initScrollFixLayoutHelper(){
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_LEFT,10,30);
        scrollFixLayoutHelper.setBgColor(Color.GREEN);
        scrollFixLayoutHelper.setMargin(5,20,5,20);
        scrollFixLayoutHelper.setAspectRatio(6);
        scrollFixLayoutHelper.setSketchMeasure(false);
        //这里搞不懂为什么设置其他模式的时候 不会出现效果
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ALWAYS);
        BaseDelegateAdapter scrollFixLayoutAdapter = new BaseDelegateAdapter(this,scrollFixLayoutHelper,1) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("ScrollFixLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
                holder.itemView.setAlpha(0.7f);
            }
        };
        mAdapterList.add(scrollFixLayoutAdapter);
    }

    private void initFloatLayoutHelper(){
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        floatLayoutHelper.setMargin(5,20,5,20);
        floatLayoutHelper.setBgColor(Color.WHITE);
        floatLayoutHelper.setAspectRatio(6);
        floatLayoutHelper.setAlignType(FixLayoutHelper.BOTTOM_LEFT);
    //    floatLayoutHelper.setDefaultLocation(150,150);
        BaseDelegateAdapter floatLayoutAdapter = new BaseDelegateAdapter(this,floatLayoutHelper,1) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("FloatLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
                holder.itemView.setAlpha(0.5f);
            }
        };
        mAdapterList.add(floatLayoutAdapter);
    }

    private void initStickyLayoutHelper(){
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        stickyLayoutHelper.setBgColor(Color.RED);
        stickyLayoutHelper.setMargin(5,20,5,20);
        stickyLayoutHelper.setAspectRatio(6);
        // true = 组件吸在顶部
        // false = 组件吸在底部
        stickyLayoutHelper.setStickyStart(false);
        stickyLayoutHelper.setOffset(200);

        BaseDelegateAdapter stickyLayoutAdapter = new BaseDelegateAdapter(this,stickyLayoutHelper,1) {
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                if(position == 0){
                    holder.mTv.setText("StickyLayoutHelper");
                }else{
                    holder.mTv.setText(mList.get(position));
                }
                holder.itemView.setAlpha(0.7f);
            }
        };
        mAdapterList.add(stickyLayoutAdapter);
    }

}
