package com.bryanrady.ui.view.svg.path_measure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.svg.PathParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class PathMeasureMapView extends View {

    private Context context;
    private float scale=1.0f;
    private RectF totalRect;
    private Paint paint;
    private float mAnimatorValue=0;
    private ArrayList<Path> dstList;
    private ArrayList<PathMeasure> pathMeasureList;
    public PathMeasureMapView(Context context) {
        super(context);
    }

    public PathMeasureMapView(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        获取到当前控件宽高值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

//        map 的宽度  和高度
        if (totalRect != null) {
            double mapWidth = totalRect.width();
            scale= (float) (width / mapWidth);
        }

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height , MeasureSpec.EXACTLY));
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        dstList = new ArrayList<>();
        pathMeasureList = new ArrayList<>();

        loadThread.start();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                Log.i("wangqingbin", "mAnimatorValue: "+mAnimatorValue);
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(5);
        valueAnimator.start();
    }

    public PathMeasureMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (pathMeasureList != null) {
            Log.d("wangqingbin","scale =="+scale);
            scale = 1.0f;
            canvas.save();
            canvas.scale(scale,scale);

            for (int i=0;i<pathMeasureList.size();i++) {
                dstList.get(i).reset();
                // 硬件加速的BUG
                dstList.get(i).lineTo(0,0);
                float stop = pathMeasureList.get(i).getLength() * mAnimatorValue;
                pathMeasureList.get(i).getSegment(0, stop, dstList.get(i), true);
                canvas.drawPath(dstList.get(i),paint);
            }
        }
    }

    private Thread loadThread=new Thread() {
        @Override
        public void run() {
            InputStream inputStream = context.getResources().openRawResource(R.raw.china);
            try {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //取得DocumentBuilderFactory实例
                DocumentBuilder builder = null; //从factory获取DocumentBuilder实例
                builder = factory.newDocumentBuilder();
                Document doc = builder.parse(inputStream);   //解析输入流 得到Document实例
                Element rootElement = doc.getDocumentElement();
                NodeList items = rootElement.getElementsByTagName("path");
//                中国地图的  矩形
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;
                for (int i = 0; i < items.getLength(); i++) {
                    Element element = (Element) items.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    PathMeasure pathMeasure = new PathMeasure();
                    pathMeasure.setPath(path, true);
                    pathMeasureList.add(pathMeasure);
                    Path dst = new Path();
                    dstList.add(dst);
//                    获取宽高
                    RectF rect = new RectF();
                    path.computeBounds(rect, true);
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                    totalRect = new RectF(left, top, right, bottom);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    };
}
