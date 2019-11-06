package com.ztkj.wky.zhuantou.Activity.oa.punch;
/**
 * 作者：wky
 * 功能描述： 管理人员的Tab
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticalManagerTab extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tl)
    SegmentTabLayout tl;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"团队", "我的"};
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_manager_tab);
        ButterKnife.bind(this);

        mFragments.add(new StatisticaTeamFragment());
        mFragments.add(new StatisticalMineFragment());

        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tl.setTabData(mTitles);
        tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
