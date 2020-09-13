package com.bryanrady.ui.activity.conflict;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.path.PathBaseActivity;
import com.bryanrady.ui.activity.path.bezier.PathBezierActivity;
import com.bryanrady.ui.activity.path.path_measure.PathMeasureActivity;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

public class ConflictActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_conflict_vp_vp;
    private Button btn_conflict_sl_vp;
    private Button btn_conflict_vp_sl;
    private Button btn_conflict_rl_rl;
    private Button btn_conflict_sl_rl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initView();
    }

    private void initView() {

        btn_conflict_vp_vp = (Button) findViewById(R.id.btn_conflict_vp_vp);
        btn_conflict_vp_vp.setOnClickListener(this);

        btn_conflict_sl_vp = (Button) findViewById(R.id.btn_conflict_sl_vp);
        btn_conflict_sl_vp.setOnClickListener(this);

        btn_conflict_vp_sl = (Button) findViewById(R.id.btn_conflict_vp_sl);
        btn_conflict_vp_sl.setOnClickListener(this);

        btn_conflict_rl_rl = (Button) findViewById(R.id.btn_conflict_rl_rl);
        btn_conflict_rl_rl.setOnClickListener(this);

        btn_conflict_sl_rl = (Button) findViewById(R.id.btn_conflict_sl_rl);
        btn_conflict_sl_rl.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_conflict_vp_vp:
                intent.setClass(this, PathBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_conflict_sl_vp:
                intent.setClass(this, PathBezierActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_conflict_vp_sl:
                intent.setClass(this, PathMeasureActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_conflict_rl_rl:

                break;
            case R.id.btn_conflict_sl_rl:
                intent.setClass(this, PathMeasureActivity.class);
                break;
        }
    }
}