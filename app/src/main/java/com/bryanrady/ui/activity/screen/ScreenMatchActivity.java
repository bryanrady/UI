package com.bryanrady.ui.activity.screen;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * 其实Android系统就是根据目标手机的像素密度到对应的资源目录下去查找相关资源。这就是Android屏幕适配的原理。
 *      1、mdpi      120dp - 160dp   此时 dp与px之间的关系：  1dp = 0.75 - 1px
 *      2、hdpi      160dp - 240dp    此时dp与px之间的关系：   1dp = 1 - 1.5px
 *      3、xhdpi     240dp - 320dp   此时dp与px之间的关系：   1dp = 1.5 - 2px
 *      4、xxhdpi    320dp - 480dp   此时dp与px之间的关系：   1dp = 2 - 3px
 *      5、xxxhdpi   480dp - 640dp   此时dp与px之间的关系：   1dp = 3 - 4px
 *
 *
 *      https://www.jianshu.com/p/759375113de9
 *
 *      https://www.jianshu.com/p/1302ad5a4b04
 *
 *
 *
 *      https://blog.csdn.net/WHB20081815/article/details/76937801  Android 万能适配方案和UI屏幕适配 不同分辨率 最全面 最易懂的
 *      密度类型	            代表的分辨率（px）	屏幕密度（dpi）	    换算（px/dp）	比例
 *      低密度（ldpi）	        240x320	                120（最大）	    1dp=0.75px	    3
 *      中密度（mdpi）	        320x480	                160（最大）	    1dp=1px	        4
 *      高密度（hdpi）	        480x800	                240（最大）	    1dp=1.5px	    6
 *      超高密度（xhdpi）	    720x1280	            320（最大）	    1dp=2px	        8
 *      超超高密度（xxhdpi）	1080x1920	            480（最大）	    1dp=3px	        12
 *
 *
 *
 *
 */
public class ScreenMatchActivity extends StatusBarBaseActivity {

    //声明本次使用到的java类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_match);


        //获取swdp方案
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int widthPixels = dm.widthPixels;
        float density = dm.density;
        float widthDP = widthPixels / density;
        int heightPixels = dm.heightPixels;
        DisplayMetrics dm2 = new DisplayMetrics();


        getWindowManager().getDefaultDisplay().getRealMetrics(dm2);

        int height = dm2.heightPixels;
        float heightdp = (height-heightPixels) / density;
        Log.i("wangqingbin","widthPixels:"+widthPixels);
        Log.i("wangqingbin","height:"+height);
        Log.i("wangqingbin","heightdp:"+heightdp);
        Log.i("wangqingbin","heightPixels:"+heightPixels);
        Log.i("wangqingbin","density:"+density);
        Log.i("wangqingbin","sw widthDP:"+widthDP);

//          4.0  480*800
//        04-16 02:56:56.393 5937-5937/com.bryanrady.ui I/wangqingbin: widthPixels:480
//        04-16 02:56:56.394 5937-5937/com.bryanrady.ui I/wangqingbin: height:800
//        04-16 02:56:56.394 5937-5937/com.bryanrady.ui I/wangqingbin: heightdp:0.0
//        04-16 02:56:56.394 5937-5937/com.bryanrady.ui I/wangqingbin: heightPixels:800
//        04-16 02:56:56.396 5937-5937/com.bryanrady.ui I/wangqingbin: density:1.5
//        04-16 02:56:56.396 5937-5937/com.bryanrady.ui I/wangqingbin: sw widthDP:320.0

//          4.65  720*1280
//        04-16 02:53:33.017 5168-5168/com.bryanrady.ui I/wangqingbin: widthPixels:720
//        04-16 02:53:33.019 5168-5168/com.bryanrady.ui I/wangqingbin: height:1280
//        04-16 02:53:33.021 5168-5168/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 02:53:33.021 5168-5168/com.bryanrady.ui I/wangqingbin: heightPixels:1184
//        04-16 02:53:33.021 5168-5168/com.bryanrady.ui I/wangqingbin: density:2.0
//        04-16 02:53:33.022 5168-5168/com.bryanrady.ui I/wangqingbin: sw widthDP:360.0


//          4.7  720*1280
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: widthPixels:720
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: height:1280
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: heightdp:0.0
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: heightPixels:1280
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: density:2.0
//        04-16 03:11:32.112 3476-3476/com.bryanrady.ui I/wangqingbin: sw widthDP:360.0

//          4.7  768*1280
//        04-16 03:15:31.156 4247-4247/com.bryanrady.ui I/wangqingbin: widthPixels:768
//        04-16 03:15:31.156 4247-4247/com.bryanrady.ui I/wangqingbin: height:1280
//        04-16 03:15:31.156 4247-4247/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 03:15:31.156 4247-4247/com.bryanrady.ui I/wangqingbin: heightPixels:1184
//        04-16 03:15:31.157 4247-4247/com.bryanrady.ui I/wangqingbin: density:2.0
//        04-16 03:15:31.161 4247-4247/com.bryanrady.ui I/wangqingbin: sw widthDP:384.0

//          5.0  1080*1920
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: widthPixels:1080
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: height:1920
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: heightPixels:1776
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: density:3.0
//        04-16 03:04:39.075 4145-4145/com.bryanrady.ui I/wangqingbin: sw widthDP:360.0

//          5.5  1440*2560
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: widthPixels:1440
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: height:2560
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: heightPixels:2392
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: density:3.5
//        04-16 03:23:42.669 3491-3491/com.bryanrady.ui I/wangqingbin: sw widthDP:411.42856

//          5.7  1440*2560
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: widthPixels:1440
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: height:2560
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: heightPixels:2392
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: density:3.5
//        04-16 03:06:48.040 3511-3511/com.bryanrady.ui I/wangqingbin: sw widthDP:411.42856

//          5.96  1440*2560
//        04-16 03:27:08.024 3490-3490/com.bryanrady.ui I/wangqingbin: widthPixels:1440
//        04-16 03:27:08.024 3490-3490/com.bryanrady.ui I/wangqingbin: height:2560
//        04-16 03:27:08.024 3490-3490/com.bryanrady.ui I/wangqingbin: heightdp:48.0
//        04-16 03:27:08.024 3490-3490/com.bryanrady.ui I/wangqingbin: heightPixels:2392
//        04-16 03:27:08.024 3490-3490/com.bryanrady.ui I/wangqingbin: density:3.5
//        04-16 03:27:08.025 3490-3490/com.bryanrady.ui I/wangqingbin: sw widthDP:411.42856




    }

}
