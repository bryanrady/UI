package com.bryanrady.ui.activity.svg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.path.PathBaseActivity;
import com.bryanrady.ui.activity.path.bezier.PathBezierActivity;
import com.bryanrady.ui.activity.path.path_measure.PathMeasureActivity;

/**
 * https://www.jianshu.com/p/9f3221179e3c
 * https://blog.csdn.net/leaf_130/article/details/54848071
 * https://www.jianshu.com/p/0555b8c1d26a
 *
 * https://github.com/woxingxiao/GracefulMovies
 * 简影讯，简约精彩影讯。基于Retrofit+RxJava+Android Arch+DataBinding+Room的高颜值影讯app。
 * 简约，优雅，精彩，即看即走，已正式发布上线。
 */
public class SvgActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_svg_china_map;
    private Button btn_svg_search_animator;
    private Button btn_svg_plugin;
    private Button btn_svg_other;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        initView();
    }

    private void initView() {

        btn_svg_china_map = (Button) findViewById(R.id.btn_svg_china_map);
        btn_svg_china_map.setOnClickListener(this);

        btn_svg_search_animator = (Button) findViewById(R.id.btn_svg_search_animator);
        btn_svg_search_animator.setOnClickListener(this);

        btn_svg_plugin = (Button) findViewById(R.id.btn_svg_plugin);
        btn_svg_plugin.setOnClickListener(this);

        btn_svg_other = (Button) findViewById(R.id.btn_svg_other);
        btn_svg_other.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_svg_china_map:
                intent.setClass(this, SvgChinaMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_svg_search_animator:
                intent.setClass(this, SvgSearchAnimatorActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_svg_plugin:
                intent.setClass(this, SvgPluginVectorDrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_svg_other:
                intent.setClass(this, SvgPluginVectorDrawableActivity.class);
                startActivity(intent);
                break;
        }
    }
}