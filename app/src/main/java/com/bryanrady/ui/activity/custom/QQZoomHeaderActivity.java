package com.bryanrady.ui.activity.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.custom.QQHeaderSrollListView;

public class QQZoomHeaderActivity extends AppCompatActivity {

    private QQHeaderSrollListView mQQHeaderSrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_header);
        mQQHeaderSrollView = findViewById(R.id.qq_header_listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new String[]{
                        "星期一 	和马云洽谈",
                        "星期二	    约见李彦宏",
                        "星期三 	约见乔布斯",
                        "星期四 	和Lance钓鱼",
                        "星期五 	和Jett洽谈",
                        "星期六 	和Jason洽谈",
                        "星期日 	和MZ洽谈",
                        "星期一 	和马云洽谈",
                        "星期二	    约见李彦宏",
                        "星期三 	约见乔布斯",
                        "星期四 	和Ricky钓鱼",
                        "星期五 	和David洽谈",
                        "星期六 	和Jason洽谈",
                        "星期日 	和MZ洽谈",
                        "……"
                }
        );

        //头部布局
        View header = View.inflate(this, R.layout.listview_qq_header, null);
        ImageView iv = header.findViewById(R.id.layout_header_image);
        mQQHeaderSrollView.setZoomImageView(iv);
        mQQHeaderSrollView.addHeaderView(header);

        mQQHeaderSrollView.setAdapter(adapter);
    }

}
