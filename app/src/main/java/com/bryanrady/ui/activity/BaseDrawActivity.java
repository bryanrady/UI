package com.bryanrady.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/6/24.
 */

public class BaseDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_draw);
    }
}
