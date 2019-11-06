package com.ztkj.wky.zhuantou.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建人： wky
 * 创建时间： 2019/7/27 17:.4
 * 功能描述： 碎片适配器
 * @author zhanshen
 */

public class XtablayoutAdapter extends FragmentPagerAdapter {
    private List<Fragment> datas;
    private List<String> titles;

    public XtablayoutAdapter(FragmentManager fm, List<Fragment> datas, List<String> titles) {
        super(fm);
        this.datas = datas;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? null : titles.get(position);
    }
}