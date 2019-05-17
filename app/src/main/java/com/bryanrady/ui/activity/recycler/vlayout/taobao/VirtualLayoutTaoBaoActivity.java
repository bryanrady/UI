package com.bryanrady.ui.activity.recycler.vlayout.taobao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bryanrady.ui.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://juejin.im/post/5a0c348051882555cc41657d
 *
 * LinearLayoutHelper——线性布局
 * GridLayoutHelper    Grid布局
 * FixLayoutHelper——固定布局
 * ScrollFixLayoutHelper——固定布局
 * FloatLayoutHelper——浮动布局
 * ColumnLayoutHelper——栏格布局
 * SingleLayoutHelper——通栏布局
 * OnePlusNLayoutHelper——一拖N布局
 * StickyLayoutHelper——stikcy布局
 * StaggeredGridLayoutHelper——瀑布流布局
 *
 * Created by Administrator on 2019/5/16.
 */

public class VirtualLayoutTaoBaoActivity extends AppCompatActivity {

    //不同类型item必须不同的viewtype
    private static final int BANNER_VIEW_TYPE = 1;
    private static final int MENU_VIEW_TYPE = 2;
    private static final int NEWS_VIEW_TYPE = 3;
    private static final int TITLE_VIEW_TYPE = 4;
    private static final int GRID_VIEW_TYPE = 5;

    //商品分类菜单应用位
    private String[] MENU_ITEM_NAMES = {"天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类"};
    private int[] MENU_ITEM_IMG_URLS = {
            R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan,
            R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai,
            R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center,
            R.mipmap.ic_travel, R.mipmap.ic_tao_gold,
            R.mipmap.ic_auction, R.mipmap.ic_classify
    };

    //高颜值推荐商品广告标题位
    private int[] TITLE_IMG_URLS = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5};
    //高颜值推荐商品位
    private int[] GRID_ITEM_IMG_URLS = {R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4};

    private RecyclerView mRecyclerView;
    private DelegateAdapter mDelegateAdapter;
    private List<DelegateAdapter.Adapter> mAdapterList; //存放各个模块的适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_virtual_layout_taobao);
        mRecyclerView = findViewById(R.id.recycler_virtual_ayout_taobao);
        init();
    }

    private void init() {
        mAdapterList = new LinkedList<>();

        //初始化工作
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(virtualLayoutManager);

        //设置回收复用池大小（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 10);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        initImageBannerAdapter();
        initMenuAdapter();
        initNewsAdapter();
        initGridItemAdapter();

        mDelegateAdapter = new DelegateAdapter(virtualLayoutManager,true);   //hasConsistItemType    子适配器项目类型是否一致
        mDelegateAdapter.setAdapters(mAdapterList);
        mRecyclerView.setAdapter(mDelegateAdapter);
    }

    private void initImageBannerAdapter() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        TaobaoDelegateAdapter bannerAdapter = new TaobaoDelegateAdapter(this, linearLayoutHelper,
                R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
                ArrayList<String> imageUrls = new ArrayList<>();
                //网络图片的路径   我这里没有网络图片路径 随便写了
                imageUrls.add("imgServerPath1");
                imageUrls.add("imgServerPath2");

                Banner banner = baseViewHolder.getView(R.id.banner);
                //设置banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(imageUrls);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                //        mBanner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                banner.start();

                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getApplicationContext(), "banner点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapterList.add(bannerAdapter);
    }

    private void initMenuAdapter() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5,MENU_ITEM_NAMES.length);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        gridLayoutHelper.setVGap(30);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0);// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE);

        TaobaoDelegateAdapter menuAdapter = new TaobaoDelegateAdapter(this,gridLayoutHelper,
                R.layout.vlayout_menu,MENU_ITEM_NAMES.length,MENU_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, final int position) {
                baseViewHolder.setText(R.id.tv_menu_title_home, MENU_ITEM_NAMES[position] + "");
                baseViewHolder.setImageResource(R.id.iv_menu_home, MENU_ITEM_IMG_URLS[position]);
                baseViewHolder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), MENU_ITEM_NAMES[position], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapterList.add(menuAdapter);
    }

    private void initNewsAdapter() {
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        TaobaoDelegateAdapter newsAdapter = new TaobaoDelegateAdapter(this,singleLayoutHelper,
                R.layout.vlayout_news,1,NEWS_VIEW_TYPE) {
            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
                MarqueeView marqueeView1 = baseViewHolder.getView(R.id.marqueeView1);
                MarqueeView marqueeView2 = baseViewHolder.getView(R.id.marqueeView2);

                List<String> info1 = new ArrayList<>();
                info1.add("天猫超市最近发大活动啦，快来抢");
                info1.add("没有最便宜，只有更便宜！");

                List<String> info2 = new ArrayList<>();
                info2.add("这个是用来搞笑的，不要在意这写小细节！");
                info2.add("啦啦啦啦，我就是来搞笑的！");

            //    marqueeView1.startWithList(info1);
            //    marqueeView2.startWithList(info2);
                // 在代码里设置自己的动画
                marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out);
                marqueeView2.startWithList(info2, R.anim.anim_bottom_in, R.anim.anim_top_out);

                marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mAdapterList.add(newsAdapter);
    }

    private void initGridItemAdapter(){
        //由于item的图片太少，这里我就循环图片item 实际项目中不同的ITEM 继续往下走就行
        for (int i = 0; i < TITLE_IMG_URLS.length; i++) {
            //item1 title
            final int finalI = i;
            TaobaoDelegateAdapter titleAdapter = new TaobaoDelegateAdapter(this, new LinearLayoutHelper(),
                    R.layout.vlayout_title, 1, TITLE_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder baseViewHolder, int position) {
                    baseViewHolder.setImageResource(R.id.iv, TITLE_IMG_URLS[finalI]);
                }
            };
            mAdapterList.add(titleAdapter);

            //item1 gird
            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
            gridLayoutHelper.setMargin(0, 0, 0, 0);
            gridLayoutHelper.setPadding(0, 0, 0, 0);
            gridLayoutHelper.setVGap(0);// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(0);// 控制子元素之间的水平间距
            gridLayoutHelper.setBgColor(Color.WHITE);
            gridLayoutHelper.setAutoExpand(true);//是否自动填充空白区域
            TaobaoDelegateAdapter girdAdapter = new TaobaoDelegateAdapter(this, gridLayoutHelper,
                    R.layout.vlayout_grid, GRID_ITEM_IMG_URLS.length, GRID_VIEW_TYPE) {
                @Override
                public void onBindViewHolder(BaseViewHolder baseViewHolder, final int position) {
                    int item = GRID_ITEM_IMG_URLS[position];
                    ImageView iv = baseViewHolder.getView(R.id.iv);
                    Glide.with(getApplicationContext()).load(item).into(iv);

                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "item" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            };
            mAdapterList.add(girdAdapter);
        }

    }
}
