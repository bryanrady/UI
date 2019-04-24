package com.bryanrady.ui.activity.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/6/24.
 */

public class SvgSearchAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_search_animator);
        final ImageView imageView = findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatedVectorDrawable drawable= (AnimatedVectorDrawable) imageView.getDrawable();
                drawable.start();
            }
        });
    }
}
