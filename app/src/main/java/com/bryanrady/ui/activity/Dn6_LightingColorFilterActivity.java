package com.bryanrady.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.draw.Dn6_LightingColorFilter;

/**
 * Created by wqb on 2018/6/27.
 */

public class Dn6_LightingColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dn6_LightingColorFilter filterView = new Dn6_LightingColorFilter(this);
        setContentView(filterView);
    }
}
