package com.bryanrady.ui.activity.recycler.vlayout.taobao;

import android.content.Context;
import android.widget.ImageView;

import com.bryanrady.ui.R;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * ImageLoader 是图片轮播库的一个加载图片的类
 * Created by Administrator on 2019/5/16.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.img_nesting_scroll)
                .into(imageView);

    }
}
