package com.ztkj.wky.zhuantou.Activity.live_shop.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private String TAG = "OrderTabActivity";
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tab);
        ButterKnife.bind(this);
        layoutTvSearch.setText("搜索订单");
        more.setVisibility(View.GONE);
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待收货");
        fragments.add(new AllOrderFragment());
        fragments.add(new WaitPayFragment());
        fragments.add(new WaitDeliverFragment());
        fragments.add(new WaitReceiveFragment());
        i = getIntent().getIntExtra("tag", 0);
        XtablayoutAdapter xtablayoutAdapter = new XtablayoutAdapter(getSupportFragmentManager(), fragments, titles);
//        xTablayout.setxTabDisplayNum(2);
//        xTablayout.setScrollPosition(2, 0, true);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        xTablayout.setupWithViewPager(viewPager);


        Objects.requireNonNull(xTablayout.getTabAt(i)).select();
        //切換fragment 监听
        xTablayout.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void start(Context context, int i) {
        Intent starter = new Intent(context, OrderTabActivity.class);
        starter.putExtra("tag", i);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.bigsearch_edt, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.bigsearch_edt:
                break;
            case R.id.more:
                break;
        }
    }
}
