package com.bryanrady.ui.activity.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bryanrady.ui.view.canvas.CanvasTransferView;

/**
 * Canvas 变换技巧
 * Created by wqb on 2018/7/1.
 */

public class CanvasTransferActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasTransferView(this));
    }

}
