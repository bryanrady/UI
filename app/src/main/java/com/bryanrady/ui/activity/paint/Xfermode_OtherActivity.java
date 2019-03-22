package com.bryanrady.ui.activity.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/6/26.
 */

public class Xfermode_OtherActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_xfermode_other);
        findViewById(R.id.lightbookview).setOnClickListener(this);
        findViewById(R.id.twitterview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()){
            case R.id.lightbookview:
                findViewById(R.id.lightbookview_view).setVisibility(View.VISIBLE);
                break;
            case R.id.twitterview:
                findViewById(R.id.twitterview_view).setVisibility(View.VISIBLE);
                break;

        }

    }

    private void hideAllViews(){
        findViewById(R.id.lightbookview_view).setVisibility(View.GONE);
        findViewById(R.id.twitterview_view).setVisibility(View.GONE);
    }
}
