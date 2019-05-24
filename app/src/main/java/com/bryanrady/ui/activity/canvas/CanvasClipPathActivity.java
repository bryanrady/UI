package com.bryanrady.ui.activity.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.canvas.CanvasClipPathView;


public class CanvasClipPathActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasClipPathView(this));
    }

}
