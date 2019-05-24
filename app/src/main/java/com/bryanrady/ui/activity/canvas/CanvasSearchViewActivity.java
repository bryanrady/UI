package com.bryanrady.ui.activity.canvas;

import android.os.Bundle;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.canvas.CustomSearchView;
import com.bryanrady.ui.view.canvas.CustomSearchViewController;

public class CanvasSearchViewActivity extends StatusBarBaseActivity {

    private CustomSearchView mCustomSearchView;
    private CustomSearchViewController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_search_view);

        mCustomSearchView = findViewById(R.id.customSearchView);

        mController = new CustomSearchViewController();
        mController.setSearchView(mCustomSearchView);
    }

    public void open(View view) {
        mController.open();
    }

    public void close(View view) {
        mController.close();
    }
}
