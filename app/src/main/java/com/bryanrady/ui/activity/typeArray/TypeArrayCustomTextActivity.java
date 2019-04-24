package com.bryanrady.ui.activity.typeArray;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bryanrady.ui.MainActivity;
import com.bryanrady.ui.R;
import com.bryanrady.ui.view.path.PathXxxToView;
import com.bryanrady.ui.view.typeArray.CustomTextView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class TypeArrayCustomTextActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_array_custom_text);

        CustomTextView customTextView1 = findViewById(R.id.coustomTextView);
        customTextView1.setLeftImg(getResources().getDrawable(R.drawable.ic_search))
                .setRightImg(getResources().getDrawable(R.drawable.icon_portrait))
                .setLeftTv("代码添加", "#c95fdc")
                .setRightTv("代码添加", "#4be1c3")
                .setCenterTv("代码", null)
                .setLeftTopTv("上", null)
                .setLeftBottomTv("下", null)
                .setBottomLine("#1587e7")
                .setCustomTvBackground("#4dacff");

        customTextView1.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener() {
            @Override
            public void OnLeftImgClick() {
                Toast.makeText(TypeArrayCustomTextActivity.this, "左边图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnRightImgClick() {
                Toast.makeText(TypeArrayCustomTextActivity.this, "右边图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnTextViewClick() {
                Toast.makeText(TypeArrayCustomTextActivity.this, "布局", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
