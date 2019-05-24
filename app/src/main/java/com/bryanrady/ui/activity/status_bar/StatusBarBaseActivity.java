package com.bryanrady.ui.activity.status_bar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bryanrady.ui.R;

/**
 * https://www.jianshu.com/p/f29053be12a9
 * Created by Administrator on 2019/5/24.
 */

public class StatusBarBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(Color.RED);
        setSupportActionBar(toolbar);

        View nav = findViewById(R.id.nav);
        setToolBarStyle(toolbar,nav,Color.RED);

        LinearLayout llBack = findViewById(R.id.ll_toolbar_back);
        TextView tvToolbarTitle = findViewById(R.id.tv_toolbar_center_title);
        llBack.setVisibility(View.VISIBLE);
        tvToolbarTitle.setText("中间标题");
    }

    /**
     * 设置样式         4.4透明状态栏    5.0 变色状态栏
     * @param topView       顶部View ToolBar
     * @param bottomView    底部View NavigationBar
     * @param styleColor
     */
    public void setToolBarStyle(View topView,View bottomView,int styleColor){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){   //4.4以后5.0之前

            //首先把状态栏和导航栏设置透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            //1.设置状态栏
            if(topView != null){
                int statusBarHeight = getStatusBarHeight();

                //第一种
//                ViewGroup.LayoutParams layoutParams = topView.getLayoutParams();
//                layoutParams.height += statusBarHeight;
//                topView.setLayoutParams(layoutParams);

                //第二种
                topView.setPadding(0,topView.getPaddingTop() + statusBarHeight,0,0);

                topView.setBackgroundColor(styleColor);
            }

            //2.设置导航栏
            if (haveNavgtion() && bottomView != null) {
                int navigationBarHeight = getNavigationBarHeight();

                //第一种
//                ViewGroup.LayoutParams layoutParams = bottomView.getLayoutParams();
//                layoutParams.height += navigationBarHeight;
//                bottomView.setLayoutParams(layoutParams);

                //第二种
                bottomView.setPadding(0,0,0,bottomView.getPaddingBottom() + navigationBarHeight);

                bottomView.setBackgroundColor(styleColor);
            }

        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){    //5.0之后
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //设置状态栏透明
        //    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //设置导航栏透明 航栏设置透明之后 设置颜色就不起作用了

            getWindow().setStatusBarColor(styleColor);
            getWindow().setNavigationBarColor(styleColor);

        }else{  //4.4以前 直接不考虑

        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        int statusBarHeight=-1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String status_bar_height = clazz.getField("status_bar_height").get(object).toString();
            statusBarHeight = Integer.parseInt(status_bar_height);
            //dp--px
            statusBarHeight = getResources().getDimensionPixelSize(statusBarHeight);
            Log.d("wangqingbin", "statusBarHeight == " + statusBarHeight);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    private boolean haveNavgtion() {
        //屏幕的高度  真实物理的屏幕
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);

        int heightDisplay = displayMetrics.heightPixels;
        //为了防止横屏
        int widthDisplay = displayMetrics.widthPixels;
        DisplayMetrics contentDisplayMetrics = new DisplayMetrics();
        display.getMetrics(contentDisplayMetrics);

        int contentDisplay = contentDisplayMetrics.heightPixels;
        int contentDisplayWidth = contentDisplayMetrics.widthPixels;

        //屏幕内容高度  显示内容的屏幕
        int w = widthDisplay-contentDisplayWidth;
        //哪一方大于0   就有导航栏
        int h = heightDisplay-contentDisplay;
        Log.d("wangqingbin", "haveNavgtion == " + (w > 0 || h > 0));
        return w > 0 || h > 0;
    }

    /**
     * 获取导航栏高度
     * @return
     */
    private int getNavigationBarHeight() {
        int navigationBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String navigation_bar_height = clazz.getField("navigation_bar_height").get(object).toString();
            navigationBarHeight = Integer.parseInt(navigation_bar_height);
            //dp--px
            navigationBarHeight = getResources().getDimensionPixelSize(navigationBarHeight);
            Log.d("wangqingbin", "navigationBarHeight == " + navigationBarHeight);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return navigationBarHeight;
    }
}
