package com.bryanrady.ui.view.measure_layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by wqb on 2018/6/23.
 */

public class FlowLayout extends ViewGroup {

    private FlowLayoutHelper mFlowLayoutHelper;
    private boolean isMeasured;

    private List<List<View>> mLineViewsList = new ArrayList<>();
    private List<Integer> mLineHeightList = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 如果需要使用MarginLayoutParams 就必须重写这个方法
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //这里必须要清除 详细 https://www.jianshu.com/p/ac7b9cdbd91d
        mLineViewsList.clear();
        mLineHeightList.clear();

        mFlowLayoutHelper = new FlowLayoutHelper();

        //增加变量，因为测量方法会调用两次，如果是测量过了，就直接使用测量得出的值
        if(isMeasured){
            isMeasured = false;
            setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight());
            return;
        }
        isMeasured = true;
        mFlowLayoutHelper.measure(this,widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(mFlowLayoutHelper.getMeasuredWidth(),mFlowLayoutHelper.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mFlowLayoutHelper.layout();
    }

    class FlowLayoutHelper{

        int measuredWidth;
        int measuredHeight;

        FlowLayoutHelper(){

        }

        int getMeasuredWidth() {
            return measuredWidth;
        }

        int getMeasuredHeight() {
            return measuredHeight;
        }

        void measure(ViewGroup parent, int widthMeasureSpec, int heightMeasureSpec){

            //1.得到父容器的测量模式和测量大小
            int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
            int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
            int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
            int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);

            //2.如果宽和高的模式都是确定的，就不用测量了,控件的宽高就父容器的宽高
            if(measureWidthMode == MeasureSpec.EXACTLY && measureHeightMode == MeasureSpec.EXACTLY){
                measuredWidth = measureWidthSize;
                measuredHeight = measureHeightSize;
            }else{
                //但是宽高如果有一个不确定，我们就要进行测量

                int curLineWidth = 0;
                int curLineHeight = 0;

                int childWidth;
                int childHeight;

                List<View> viewList = new ArrayList<>();
                int childCount = parent.getChildCount();
                //每个孩子我们都要测量
                for(int i = 0; i < childCount; i++){
                    View childView = parent.getChildAt(i);
                    //测量
                    measureChild(childView,widthMeasureSpec,heightMeasureSpec);
                    //使用MarginLayoutParams 就必须重写generateLayoutParams(AttributeSet attrs)这个方法
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                    childWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                    childHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                    //如果当前行宽度+ 准备添加的View的宽度 大于 父容器的宽度 ， 则换行
                   if(curLineWidth + childWidth > measureWidthSize){ //需要换行
                       //记录当前行信息
                       measuredWidth = Math.max(measuredWidth,curLineWidth);
                       measuredHeight += curLineHeight;

                       //将当前行的rowViewList添加到总的mViewList中
                       //   把当前行的高度添加到总的行高度列表mHeightList
                       mLineViewsList.add(viewList);
                       mLineHeightList.add(curLineHeight);

                       //换行 重置当前行信息
                       curLineWidth = childWidth;
                       curLineHeight = childHeight;

                       //创建新一行的孩子集合
                       viewList = new ArrayList<>();
                       //将新一行的孩子添加进来
                       viewList.add(childView);

                   }else{
                       curLineWidth += childWidth;
                       curLineHeight = Math.max(curLineHeight,childHeight);

                       viewList.add(childView);
                   }

                    //如果是最后一行 要单独出来 没这个处理的话 最后一行是不会显示出来的
                    if(i == childCount -1){
                        measuredWidth = Math.max(measuredWidth,curLineWidth);
                        measuredHeight += curLineHeight;

                        //将最后一行的rowViewList添加到总的mViewList中
                        //把最后一行的高度添加到总的行高度列表mHeightList
                        mLineViewsList.add(viewList);
                        mLineHeightList.add(curLineHeight);

                    }

                }

            }
        }

        void layout(){

            int left,top,right,bottom;
            int curLeft = 0;
            int curTop = 0;

            Log.d("wangqingbin","mLineViewsList.size=="+mLineViewsList.size());
            Log.d("wangqingbin","mLineHeightList.size=="+mLineHeightList.size());
            //获取所有行的View的数量
            int viewCount = mLineViewsList.size();
            for(int i = 0; i < viewCount; i++){
                //获取每一行的View的数量
                List<View> viewList = mLineViewsList.get(i);
                for(int j = 0; j < viewList.size(); j++){
                    View childView = viewList.get(j);

                    //计算每个孩子的摆放位置
                    MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                    left = curLeft + layoutParams.leftMargin;
                    top = curTop + layoutParams.topMargin;
                    right = left + childView.getMeasuredWidth();
                    bottom = top + childView.getMeasuredHeight();

                    //开始摆放控件
                    childView.layout(left, top, right, bottom);

                    //每次都要更新距离左边的位置
                    curLeft = right + layoutParams.rightMargin;
                }

                //换行
                curLeft = 0;
                curTop += mLineHeightList.get(i);
            }
        }

    }

}
