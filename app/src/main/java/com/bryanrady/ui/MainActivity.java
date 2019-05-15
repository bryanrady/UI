package com.bryanrady.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.activity.custom.CustomViewActivity;
import com.bryanrady.ui.activity.event.DispatchEventActivity;
import com.bryanrady.ui.activity.measure_layout.MeasureLayoutActivity;
import com.bryanrady.ui.activity.paint.PaintActivity;
import com.bryanrady.ui.activity.canvas.CanvasActivity;
import com.bryanrady.ui.activity.animation.AnimationActivity;
import com.bryanrady.ui.activity.path.PathActivity;
import com.bryanrady.ui.activity.recycler.RecyclerViewActivity;
import com.bryanrady.ui.activity.screen.ScreenMatchActivity;
import com.bryanrady.ui.activity.svg.SvgActivity;
import com.bryanrady.ui.activity.typeArray.TypeArrayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_measure_layout;
    private Button btn_paint;
    private Button btn_canvas;
    private Button btn_path;
    private Button btn_animation;
    private Button btn_screen_match;
    private Button btn_dispatch_event;
    private Button btn_type_array;
    private Button btn_svg;
    private Button btn_recyclerView;
    private Button btn_customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_measure_layout = (Button) findViewById(R.id.btn_measure_layout);
        btn_measure_layout.setOnClickListener(this);

        btn_paint = (Button) findViewById(R.id.btn_paint);
        btn_paint.setOnClickListener(this);

        btn_canvas = (Button) findViewById(R.id.btn_canvas);
        btn_canvas.setOnClickListener(this);

        btn_path = (Button) findViewById(R.id.btn_path);
        btn_path.setOnClickListener(this);

        btn_animation = (Button) findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(this);

        btn_screen_match = (Button) findViewById(R.id.btn_screen_match);
        btn_screen_match.setOnClickListener(this);

        btn_dispatch_event = (Button) findViewById(R.id.btn_dispatch_event);
        btn_dispatch_event.setOnClickListener(this);

        btn_type_array = (Button) findViewById(R.id.btn_type_array);
        btn_type_array.setOnClickListener(this);

        btn_svg = (Button) findViewById(R.id.btn_svg);
        btn_svg.setOnClickListener(this);

        btn_recyclerView = (Button) findViewById(R.id.btn_recyclerView);
        btn_recyclerView.setOnClickListener(this);

        btn_customView = (Button) findViewById(R.id.btn_customView);
        btn_customView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_measure_layout:
                intent.setClass(this, MeasureLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint:
                intent.setClass(this, PaintActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas:
                intent.setClass(this, CanvasActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path:
                intent.setClass(this, PathActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_animation:
                intent.setClass(this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_screen_match:
                intent.setClass(this, ScreenMatchActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dispatch_event:
                intent.setClass(this, DispatchEventActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_type_array:
                intent.setClass(this, TypeArrayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_svg:
                intent.setClass(this, SvgActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recyclerView:
                intent.setClass(this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_customView:
                intent.setClass(this, CustomViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}