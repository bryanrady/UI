package com.bryanrady.ui.activity.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {

    public CustomButton(Context context) {
        this(context,null);
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                //禁用事件拦截功能
//            //    getParent().requestDisallowInterceptTouchEvent(true);
//                /**
//                 *  当子View接收到了按下事件后，就禁用父容器事件拦截功能，使父容器不能拦截后续事件，
//                 *  就是把disallowIntercept设置为true，这时候走的就是else语句的代码，得出的结果就是
//                 *  不拦截事件，所以说Down事件后面的MOVE和UP事件还是会分发到子View上。
//                 *
//                 *                if (!disallowIntercept) {
//                 *                     intercepted = onInterceptTouchEvent(ev);
//                 *                 } else {
//                 *                     intercepted = false;
//                 *                 }
//                 *
//                 *
//                 */
//                Log.d("wangqingbin", "You down button");
//                return true;
//            case MotionEvent.ACTION_UP:
//            //    getParent().requestDisallowInterceptTouchEvent(false);
//                Log.d("wangqingbin", "You up button");
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                Log.d("wangqingbin", "You move button");
//                return true;
//        }
        return super.onTouchEvent(event);
    }
}
