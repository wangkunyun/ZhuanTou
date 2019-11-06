package com.ztkj.wky.zhuantou.MyUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ztkj.wky.zhuantou.R;

public class TipsView extends LinearLayout {
    public TipsView(Context context) {
        super(context);
        init();
    }

    public TipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TipsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.pp2, this);
    }
}
