package com.bryanrady.ui.activity.canvas;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.canvas.CustomHorizontalScrollView;
import com.bryanrady.ui.view.canvas.RevealDrawable;

public class CanvasRevealDrawableActivity extends StatusBarBaseActivity {

    private CustomHorizontalScrollView mScrollView;

    private int[] mImgIds = new int[]{
            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline,

            R.drawable.avft,
            R.drawable.box_stack,
            R.drawable.bubble_frame,
            R.drawable.bubbles,
            R.drawable.bullseye,
            R.drawable.circle_filled,
            R.drawable.circle_outline
    };
    private int[] mImgIds_active = new int[]{
            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active,

            R.drawable.avft_active, R.drawable.box_stack_active, R.drawable.bubble_frame_active,
            R.drawable.bubbles_active, R.drawable.bullseye_active, R.drawable.circle_filled_active,
            R.drawable.circle_outline_active
    };

    public Drawable[] revealDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_reveal_drawable);

        initDrable();

        mScrollView = findViewById(R.id.customHorizontalScrollView);
        mScrollView.addContainerChildViews(revealDrawables);
    }

    private void initDrable() {
        revealDrawables = new Drawable[mImgIds.length];

        for (int i = 0; i < mImgIds.length; i++) {
            RevealDrawable drawable = null;
            drawable = new RevealDrawable(
                    ContextCompat.getDrawable(this, mImgIds[i]),
                    ContextCompat.getDrawable(this, mImgIds_active[i]));

            revealDrawables[i] = drawable;
        }
    }
}
