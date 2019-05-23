package com.bryanrady.ui.activity.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.layout_manager.CardLayoutManagerActivity;
import com.bryanrady.ui.activity.recycler.layout_manager.FlowLayoutManagerActivity;
import com.bryanrady.ui.activity.recycler.layout_manager.ViewPagerLayoutManagerActivity;
import com.bryanrady.ui.activity.recycler.vlayout.VirtualLayoutBaseActivity;
import com.bryanrady.ui.activity.recycler.vlayout.taobao.VirtualLayoutTaoBaoActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class LayoutManagerRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_recycler_virtual_layout_base;
    private Button btn_recycler_virtual_layout_taobao;
    private Button btn_recycler_flow_layout_manager;
    private Button btn_recycler_douyin_layout_manager;
    private Button btn_recycler_tantan_layout_manager;
    private Button btn_recycler_echelon_layout_manager;
    private Button btn_recycler_discover_layout_manager;
    private Button btn_recycler_card_layout_manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_custom_layout);
        initView();
    }

    private void initView() {

        btn_recycler_virtual_layout_base = (Button) findViewById(R.id.btn_recycler_virtual_layout_base);
        btn_recycler_virtual_layout_base.setOnClickListener(this);

        btn_recycler_virtual_layout_taobao = (Button) findViewById(R.id.btn_recycler_virtual_layout_taobao);
        btn_recycler_virtual_layout_taobao.setOnClickListener(this);

        btn_recycler_flow_layout_manager = (Button) findViewById(R.id.btn_recycler_flow_layout_manager);
        btn_recycler_flow_layout_manager.setOnClickListener(this);

        btn_recycler_douyin_layout_manager = (Button) findViewById(R.id.btn_recycler_douyin_layout_manager);
        btn_recycler_douyin_layout_manager.setOnClickListener(this);

        btn_recycler_tantan_layout_manager = (Button) findViewById(R.id.btn_recycler_tantan_layout_manager);
        btn_recycler_tantan_layout_manager.setOnClickListener(this);

        btn_recycler_echelon_layout_manager = (Button) findViewById(R.id.btn_recycler_echelon_layout_manager);
        btn_recycler_echelon_layout_manager.setOnClickListener(this);

        btn_recycler_discover_layout_manager = (Button) findViewById(R.id.btn_recycler_discover_layout_manager);
        btn_recycler_discover_layout_manager.setOnClickListener(this);

        btn_recycler_card_layout_manager = (Button) findViewById(R.id.btn_recycler_card_layout_manager);
        btn_recycler_card_layout_manager.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_recycler_virtual_layout_base:
                intent.setClass(this, VirtualLayoutBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_virtual_layout_taobao:
                intent.setClass(this, VirtualLayoutTaoBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_flow_layout_manager:
                intent.setClass(this, FlowLayoutManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_douyin_layout_manager:
                intent.setClass(this, ViewPagerLayoutManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_tantan_layout_manager:
                intent.setClass(this, VirtualLayoutTaoBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_echelon_layout_manager:
                intent.setClass(this, VirtualLayoutTaoBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_discover_layout_manager:
                intent.setClass(this, VirtualLayoutTaoBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_card_layout_manager:
                intent.setClass(this, CardLayoutManagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}