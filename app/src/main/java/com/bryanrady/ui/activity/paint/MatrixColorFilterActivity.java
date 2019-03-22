package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.paint.Dn6_ColorMatrixFilterTranslate;

/**
 * Created by wqb on 2018/6/27.
 */

public class MatrixColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dn6_ColorMatrixFilterTranslate filterView = new Dn6_ColorMatrixFilterTranslate(this);
        setContentView(filterView);
    }
}
