package com.bryanrady.ui.activity.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.canvas.CanvasClipPathView;
import com.bryanrady.ui.view.canvas.CanvasClipRectView;


public class CanvasClipPathActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasClipPathView(this));
    }

}
