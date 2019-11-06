package com.ztkj.wky.zhuantou.MyUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.ztkj.wky.zhuantou.R;
import com.jwenfeng.library.pulltorefresh.view.FooterView;

public class  LoadMoreView extends FrameLayout implements FooterView {

    private TextView tv;
    private ImageView arrow;
    private ProgressBar progressBar;

    public LoadMoreView(Context context) {
        this(context,null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layheader,null);
        addView(view);
        tv = (TextView) view.findViewById(R.id.header_tv);
        arrow = (ImageView) view.findViewById(R.id.header_arrow);
        progressBar = (ProgressBar) view.findViewById(R.id.header_progress);
    }

    @Override
    public void begin() {
    }

    @Override
    public void progress(float progress, float all) {
        float s = progress / all;
        if (s >= 0.9f){
            arrow.setRotation(0);
        }else{
            arrow.setRotation(180);
        }
        if (progress >= all-10){
            tv.setText("释放加载更多");
        }else{
            tv.setText("上拉加载");
        }
    }

    @Override
    public void finishing(float progress, float all) {
    }

    @Override
    public void loading() {
        arrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tv.setText("加载中...");
    }

    @Override
    public void normal() {
        arrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        tv.setText("上拉加载");
    }

    @Override
    public View getView() {
        return this;
    }
}

