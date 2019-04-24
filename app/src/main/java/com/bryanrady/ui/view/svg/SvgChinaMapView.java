package com.bryanrady.ui.view.svg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 2019/4/23.
 */

public class SvgChinaMapView extends View {

    private Context mContext;
    private Paint mPaint;

    private final int LOAD_COMPLETE = 0x001;
    private List<ProvinceItem> mProvinceItemList;
    private RectF mMapRect;
    private float mScale = 1.0f;
    private ProvinceItem mSelectItem; //点击的Item

    public SvgChinaMapView(Context context) {
        super(context);
    }

    public SvgChinaMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SvgChinaMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //开启线程加载svg数据
        loadDataThread.start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOAD_COMPLETE:
                    postInvalidate();
                    break;
            }
        }
    };

    private Thread loadDataThread = new Thread(new Runnable() {
        @Override
        public void run() {
            List<ProvinceItem> list = new ArrayList<>();
            InputStream inputStream = mContext.getResources().openRawResource(R.raw.china);
            try {
                //dom解析xml
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//取得DocumentBuilderFactory实例
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                 //从factory获取DocumentBuilder实例
                Document document = documentBuilder.parse(inputStream);//解析输入流 得到Document实例
                Element rootElement = document.getDocumentElement();
                NodeList nodeList = rootElement.getElementsByTagName("path");

                //中国地图  矩形
                float left = -1;
                float right = -1;
                float top = -1;
                float bottom = -1;

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String pathData = element.getAttribute("android:pathData");
                    Path path = PathParser.createPathFromPathData(pathData);
                    ProvinceItem item = new ProvinceItem();
                    item.setPath(path);
                    item.setDrawColor(Color.CYAN);
                    list.add(item);

                    //计算出map的宽高
                    RectF rect = new RectF();
                    path.computeBounds(rect, true);
                    left = left == -1 ? rect.left : Math.min(left, rect.left);
                    right = right == -1 ? rect.right : Math.max(right, rect.right);
                    top = top == -1 ? rect.top : Math.min(top, rect.top);
                    bottom = bottom == -1 ? rect.bottom : Math.max(bottom, rect.bottom);
                    mMapRect = new RectF(left, top, right, bottom);
                }
                mProvinceItemList = list;
                handler.sendEmptyMessage(LOAD_COMPLETE);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int)event.getX();
                int y = (int)event.getY();
                if (mProvinceItemList != null) {
                    for (ProvinceItem item : mProvinceItemList) {
                        if(item.isTouch(x/mScale,y/mScale)){
                            mSelectItem = item;
                            postInvalidate();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heigth = MeasureSpec.getSize(heightMeasureSpec);

        // 因为svg是用px做的单位，会有适配问题 所以要进行缩放
        if (mMapRect != null) {
            double mapWidth = mMapRect.width();
            mScale= (float) (width / mapWidth);
        }
        setMeasuredDimension(width,heigth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mProvinceItemList != null){
            canvas.save();
            canvas.scale(mScale,mScale);

            for (ProvinceItem item : mProvinceItemList){
                item.drawProvinceItem(canvas, mPaint,false);
            }

            if(mSelectItem != null){
                mSelectItem.drawProvinceItem(canvas, mPaint,true);
            }
        }
    }

}
