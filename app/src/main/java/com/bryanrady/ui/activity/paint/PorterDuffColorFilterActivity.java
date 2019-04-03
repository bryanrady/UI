package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.paint.PorteDuffColorFilter;

/**
 * Created by wqb on 2018/6/27.
 */

public class PorterDuffColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PorteDuffColorFilter filterView = new PorteDuffColorFilter(this);
        setContentView(filterView);
    }
}
