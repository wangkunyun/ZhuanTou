package com.ztkj.wky.zhuantou.Activity.oa.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadReportFragment extends Fragment {


    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    List<Fragment> fragments = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("我提交的");
        titles.add("我收到的");
        fragments.add(new AllReport());
        fragments.add(new SubmitReport());
        fragments.add(new ReceiveReport());

        XtablayoutAdapter xtablayoutAdapter = new XtablayoutAdapter(getFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。

        xTablayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        xTablayout.setupWithViewPager(viewPager);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
