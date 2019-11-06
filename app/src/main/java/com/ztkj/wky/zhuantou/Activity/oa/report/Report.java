package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ztkj.wky.zhuantou.R;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Report extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);



        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80,80)
                .setFontSize(10)
                .addTabItem("写日志", R.mipmap.icon_wrt_select, WriteReportFragment.class)
                .addTabItem("看日志", R.mipmap.icon_look_sel, ReadReportFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
