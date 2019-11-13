package com.ztkj.wky.zhuantou.Activity.live_shop.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.Activity.oa.report.AllReport;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReceiveReport;
import com.ztkj.wky.zhuantou.Activity.oa.report.SubmitReport;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderTabActivity extends AppCompatActivity {

    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_tvSearch)
    TextView layoutTvSearch;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearchEdt;
    @BindView(R.id.more)
    ImageView more;
    List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tab);
        ButterKnife.bind(this);
        layoutTvSearch.setText("搜索订单");

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("我提交的");
        titles.add("我收到的");
        fragments.add(new AllReport());
        fragments.add(new SubmitReport());
        fragments.add(new ReceiveReport());

        XtablayoutAdapter xtablayoutAdapter = new XtablayoutAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。

        xTablayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        xTablayout.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.layout_back, R.id.bigsearch_edt, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.bigsearch_edt:
                break;
            case R.id.more:
                break;
        }
    }
}
