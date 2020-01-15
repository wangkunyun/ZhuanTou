package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.AllApplyFor;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.HaveApply;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply.NoApply;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadApplyForFragment extends Fragment {


    @BindView(R.id.apply_choose_type)
    TextView applyChooseType;
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.lin_choose_type)
    LinearLayout linChooseType;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;

    private boolean type = false;
    private XtablayoutAdapter xtablayoutAdapter;
    private SharedPreferencesHelper sp_apply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_apply_for, container, false);
        unbinder = ButterKnife.bind(this, view);
        layoutTitleTv.setText("申请记录");
        sp_apply = new SharedPreferencesHelper(getActivity(), "apply");

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("未审批");
        titles.add("已审批");
        fragments.add(new AllApplyFor());
        fragments.add(new NoApply());
        fragments.add(new HaveApply());

        xtablayoutAdapter = new XtablayoutAdapter(getFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
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

    @OnClick({R.id.layout_back, R.id.type_all, R.id.apply_choose_type, R.id.type_car, R.id.type_seal, R.id.type_baoxiao, R.id.type_over_time, R.id.type_leave, R.id.type_go_out, R.id.type_out_work, R.id.type_chuchai, R.id.type_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                getActivity().finish();
                break;
            case R.id.apply_choose_type:
                if (!type) {
                    linChooseType.setVisibility(View.VISIBLE);
                    type = true;
                    setDrawable(true);
                } else {
                    linChooseType.setVisibility(View.GONE);
                    type = false;
                }
                break;
            case R.id.type_all:
                setDrawable(false);
                applyChooseType.setText("全部");
                sp_apply.put("sp_apply_type", "0");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_car:
                setDrawable(false);
                applyChooseType.setText("用车");
                sp_apply.put("sp_apply_type", "1");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);

                break;
            case R.id.type_seal:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "2");
                applyChooseType.setText("用印");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_baoxiao:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "3");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                applyChooseType.setText("报销");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_over_time:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "4");
                applyChooseType.setText("加班");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_leave:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "5");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                applyChooseType.setText("请假");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_go_out:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "6");
                applyChooseType.setText("外出");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_out_work:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "7");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                applyChooseType.setText("外勤");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_chuchai:
                setDrawable(false);
                sp_apply.put("sp_apply_type", "8");
                applyChooseType.setText("出差");
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
            case R.id.type_pay:
                setDrawable(false);
                applyChooseType.setText("付款");
                sp_apply.put("sp_apply_type", "9");
                Log.e("AllReport", "onViewClicked: " + sp_apply.getSharedPreference("sp_apply_type", ""));
                type = false;
                fragments.get(0).onResume();
                fragments.get(1).onResume();
                fragments.get(2).onResume();
                linChooseType.setVisibility(View.GONE);
                break;
        }
    }

    public void setDrawable(boolean isTop) {
        Drawable drawable;
        if (isTop) {
            drawable = getResources().getDrawable(R.mipmap.apply_type_up);
        } else {
            drawable = getResources().getDrawable(R.mipmap.apply_type_down);
        }
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置text 左边图片
        applyChooseType.setCompoundDrawables(null, null, drawable, null);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sp_apply.clear();
    }
}
