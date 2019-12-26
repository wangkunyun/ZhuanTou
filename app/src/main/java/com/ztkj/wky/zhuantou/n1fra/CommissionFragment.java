package com.ztkj.wky.zhuantou.n1fra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.HaveApply;
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
public class CommissionFragment extends Fragment {


    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private XtablayoutAdapter xtablayoutAdapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commission, container, false);
        unbinder = ButterKnife.bind(this, view);

        List<String> titles = new ArrayList<>();
        titles.add("已提交");
        titles.add("待审批");
        titles.add("已审批");
        titles.add("抄送我");
//        fragments.add(new AllApplyFor());
//        fragments.add(new AllApplyFor());
//        fragments.add(new HaveApply());
//        fragments.add(new NoApply());
        fragments.add(new HaveApply());
        fragments.add(new HaveApply());
        fragments.add(new HaveApply());
        fragments.add(new HaveApply());

        xtablayoutAdapter = new XtablayoutAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(4);
        xtablayoutAdapter.notifyDataSetChanged();
        //将TabLayout和ViewPager关联起来。
//        xTablayout.setupWithViewPager(viewPager);
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
