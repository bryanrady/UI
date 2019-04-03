package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.paint.ColorMatrixFilterMulipty;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorFilterMuliptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorMatrixFilterMulipty filterView = new ColorMatrixFilterMulipty(this);
        setContentView(filterView);
    }
}
