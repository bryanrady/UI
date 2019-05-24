package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/6/26.
 */

public class Xfermode_DSTActivity extends StatusBarBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_xfermode_dst);

        findViewById(R.id.rounddstinbtn).setOnClickListener(this);
        findViewById(R.id.invertdstinbtn).setOnClickListener(this);
        findViewById(R.id.irregularwavebtn).setOnClickListener(this);
        findViewById(R.id.heartbitbtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()){
            case R.id.rounddstinbtn:
                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
                break;
            case R.id.invertdstinbtn:
                findViewById(R.id.invertimg).setVisibility(View.VISIBLE);
                break;
            case R.id.irregularwavebtn:
                findViewById(R.id.irregularwaveview).setVisibility(View.VISIBLE);
                break;
            case R.id.heartbitbtn:
                findViewById(R.id.heartbitview).setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAllViews(){
        findViewById(R.id.roundimage).setVisibility(View.GONE);
        findViewById(R.id.invertimg).setVisibility(View.GONE);
        findViewById(R.id.irregularwaveview).setVisibility(View.GONE);
        findViewById(R.id.heartbitview).setVisibility(View.GONE);
    }
}
