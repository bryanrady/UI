package com.bryanrady.ui.activity.path.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.bezier.PathBezierDragBubbleView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathBezierDragBubbleActivity extends StatusBarBaseActivity {

    private PathBezierDragBubbleView mPathBezierDragBubbleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_bezier_drag_bubble);

        mPathBezierDragBubbleView = findViewById(R.id.drag_buddle_view);
        findViewById(R.id.reset_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathBezierDragBubbleView.reset();
            }
        });

    }

}
