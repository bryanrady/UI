package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.paint.ColorMatrixFilterOther;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorMatrixFilterOtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorMatrixFilterOther filterView = new ColorMatrixFilterOther(this);
        setContentView(filterView);
    }
}
