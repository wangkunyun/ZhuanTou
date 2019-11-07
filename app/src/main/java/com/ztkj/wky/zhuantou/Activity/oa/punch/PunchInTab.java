package com.ztkj.wky.zhuantou.Activity.oa.punch;
/**
 * 作者：wky
 * 功能描述： 打卡的tablayout
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PunchInTab extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    private SharedPreferencesHelper sp_create_team;
    private String jur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_in_tab);
        ButterKnife.bind(this);
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        jur = (String) sp_create_team.getSharedPreference("Create_team_jurisdiction", "");
//        if (jur.equals("1")) {
//            bottomTabBar.init(getSupportFragmentManager())
//                    .setImgSize(80, 80)
//                    .setFontSize(10)
//                    .addTabItem("考勤打卡", R.mipmap.icon_wrt_select, PunchInFragment.class)
//                    .addTabItem("考勤记录", R.mipmap.icon_look_sel, PunchRecordFragment.class)
//                    .setTabBarBackgroundColor(Color.WHITE)
//                    .isShowDivider(true);
//        } else {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(10)
                .addTabItem("考勤打卡", R.mipmap.icon_wrt_select, PunchInFragment.class)
                .addTabItem("考勤记录", R.mipmap.icon_look_sel, PunchRecordFragment.class)
                .addTabItem("考勤设置", R.mipmap.icon_daka_set, PunchManagerFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
//        }


    }
}
