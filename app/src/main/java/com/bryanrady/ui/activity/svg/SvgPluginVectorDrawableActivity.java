package com.bryanrady.ui.activity.svg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.bryanrady.ui.R;
import com.xw.repo.VectorCompatTextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;

/**
 * Created by wqb on 2018/6/24.
 */

public class SvgPluginVectorDrawableActivity extends AppCompatActivity {

//    Vector图像刚发布的时候，是只支持Android 5.0+的设备
//    不过自从AppCompat 23.2之后，我们只需要引入com.android.support:appcompat-v7:23.2.0以上的版本 的兼容包，Vector可以使用于Android 2.1以上的所有系统。
//    经测试静态VectorDrawable在5.0前后运行都正常，只不过5.0之前的版本图片有点模糊
//    compileSdkVersion 24
//    buildToolsVersion "25.0.2"
//    compile 'com.android.support:appcompat-v7:25.3.1'
//    未使用 vectorDrawables.useSupportLibrary = true
//    未使用
//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }
//
//    动态AnimatedVectorDrawable
//      5.0后正常
//
//      5.0前异常
//    XmlPullParserException: Binary XML file line #2: invalid drawable tag animated-vector
//    android.content.res.Resources$NotFoundException: File res/drawable/animatevector.xml from drawable resource ID #0x7f020053
//
//    解决方法：
//    compile 'com.android.support:appcompat-v7:XXX'
//    defaultConfig {
//        vectorDrawables.useSupportLibrary = true
//    }
//    android:src=""改为app:srcCompat=""
//    下面代码用不用都可以
//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_plugin_vector_drawable);

        final VectorCompatTextView textView = findViewById(R.id.checkable_text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.toggle();
                textView.setText(textView.isChecked() ? "SELECTED TAB" : "UNSELECTED TAB");
            }
        });
    }
}
