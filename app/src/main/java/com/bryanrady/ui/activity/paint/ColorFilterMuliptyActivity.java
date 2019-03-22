package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.paint.Dn6_ColorMatrixFilterMulipty;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorFilterMuliptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dn6_ColorMatrixFilterMulipty filterView = new Dn6_ColorMatrixFilterMulipty(this);
        setContentView(filterView);
    }
}
