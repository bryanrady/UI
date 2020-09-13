package com.bryanrady.ui.activity.paint;

import android.os.Bundle;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.paint.filter.ColorMatrixFilterOther;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorMatrixFilterOtherActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorMatrixFilterOther filterView = new ColorMatrixFilterOther(this);
        setContentView(filterView);
    }
}
