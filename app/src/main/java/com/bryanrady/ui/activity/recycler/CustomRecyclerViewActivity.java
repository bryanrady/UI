package com.bryanrady.ui.activity.recycler;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.recycler.recyclerview.Adapter;
import com.bryanrady.ui.view.recycler.recyclerview.MyRecyclerView;

/**
 * Created by Administrator on 2019/5/23.
 */

public class CustomRecyclerViewActivity extends StatusBarBaseActivity {

    MyRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_custom_recycler_view);

        recyclerView = findViewById(R.id.table);
        recyclerView.setAdapter(new MyAdapter(this,200));
    //    handler.sendEmptyMessageDelayed(1, 8000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            recyclerView.setAdapter(new MyAdapter(CustomRecyclerViewActivity.this,1111111150));
            super.handleMessage(msg);
        }
    };

    class MyAdapter implements Adapter{
        LayoutInflater inflater;
        private  int height;
        private int count;
        public MyAdapter(Context context, int count) {
            Resources resources = context.getResources();
            height = resources.getDimensionPixelSize(R.dimen.table_height);
            inflater= LayoutInflater.from(context);
            this.count=count;
        }
        @Override
        public int getCount() {
            return count;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView= inflater.inflate( R.layout.recycler_custom_recycler_view_item,parent,false);
            }

            //
            TextView textView= (TextView) convertView.findViewById(R.id.text1);
            textView.setText("第 "+position+"行");
            return convertView;
        }

        @Override
        public int getItemViewType(int row) {
            return 0;
        }

        @Override
        public int getHeight(int index) {
            return height;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }
    }

}
