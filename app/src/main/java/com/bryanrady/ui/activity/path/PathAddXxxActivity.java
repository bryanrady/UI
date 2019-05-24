package com.bryanrady.ui.activity.path;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.PathAddXxxView;
import com.bryanrady.ui.view.path.PathXxxToView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathAddXxxActivity extends StatusBarBaseActivity {

    //https://www.jianshu.com/p/db01b37b6231
    //https://github.com/venshine/BezierMaker

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathAddXxxView(this));
    }

}
