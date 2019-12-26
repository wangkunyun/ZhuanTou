package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JfTab extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_tab);
        ButterKnife.bind(this);

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(10)
                .addTabItem("兑换中心", R.mipmap.icon_jf1, JfConversionFragment.class)
                .addTabItem("兑换记录", R.mipmap.icon_jf2, JfConversionRecordFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, JfTab.class);
        context.startActivity(starter);
    }
}
