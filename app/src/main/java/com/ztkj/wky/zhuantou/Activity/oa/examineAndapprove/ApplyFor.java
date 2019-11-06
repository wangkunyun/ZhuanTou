package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyFor extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for);
        ButterKnife.bind(this);


        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(10)
                .addTabItem("写申请", R.mipmap.icon_wrt_select, WriteApplyForFragment.class)
                .addTabItem("申请记录", R.mipmap.icon_look_sel, ReadApplyForFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }


}
