package com.bryanrady.ui.activity.paint;

import android.os.Bundle;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.paint.ColorMatrixFilterMulipty;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorFilterMuliptyActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorMatrixFilterMulipty filterView = new ColorMatrixFilterMulipty(this);
        setContentView(filterView);
    }
}
