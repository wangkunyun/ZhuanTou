package com.ztkj.wky.zhuantou.n1fra;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.LiveShopFragment;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.homepage.SearchNearActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveTabFragment extends Fragment {

    @BindView(R.id.near_dingweiimg)
    ImageView nearDingweiimg;
    @BindView(R.id.near_title)
    TextView nearTitle;
    @BindView(R.id.rl_live)
    RelativeLayout rlLive;
    @BindView(R.id.click_live_chat)
    ImageView clickLiveChat;
    @BindView(R.id.click_live_gouwuche)
    ImageView clickLiveGouwuche;
    @BindView(R.id.lin_shop)
    LinearLayout linShop;
    @BindView(R.id.jd_sy)
    RadioButton jdSy;
    @BindView(R.id.jd_xiahua1)
    TextView jdXiahua1;
    @BindView(R.id.jd_sy2)
    RadioButton jdSy2;
    @BindView(R.id.jd_xiahua2)
    TextView jdXiahua2;
    @BindView(R.id.n1_group)
    RadioGroup n1Group;
    @BindView(R.id.jdfr)
    FrameLayout jdfr;
    Unbinder unbinder;
    private String TAG = "LiveTabFragment";
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"生活", "商城"};
    private Intent intent;
    private int position = 0;
    private N5Fragment n5Fragment;
    private LiveShopFragment liveShopFragment;
    private String co_id, co_address;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String first = "1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_tab, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        if (!(sharedPreferencesHelper.contain("co_id"))) {
            sharedPreferencesHelper.put("co_id", "1");
            sharedPreferencesHelper.put("co_address", "世界侨商中心");
        }
        co_address = (String) sharedPreferencesHelper.getSharedPreference("co_address", "");
        nearTitle.setText(co_address);

        position = 0;
        setTabSelection(position);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改底部导航栏按钮状态
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
//        transaction.hide(shouYeFragment).hide(caiFuFragment).hide(jdFragment).hide(woDeFragment).commit();
        transaction = getChildFragmentManager().beginTransaction();
        switch (position) {
            case 0:
//                jdSy.setTextColor(Color.parseColor(Colorstring.lanse));
//                jdSy2.setTextColor(Color.parseColor(Colorstring.baise));
                rlLive.setVisibility(View.VISIBLE);
                linShop.setVisibility(View.GONE);
                jdSy.setTextSize(22);
                jdSy2.setTextSize(16);
                TextPaint p = jdSy.getPaint();
                p.setFakeBoldText(true);
                TextPaint p2 = jdSy2.getPaint();
                p2.setFakeBoldText(true);
                jdXiahua1.setAlpha(1f);
                jdXiahua2.setAlpha(0f);
                jdSy.setChecked(true);
//                if (ziXunFragment == null) {
                n5Fragment = new N5Fragment();
                transaction.add(R.id.jdfr, n5Fragment);
                break;
            case 1:
//                jdSy.setTextColor(Color.parseColor(Colorstring.baise));
//                jdSy2.setTextColor(Color.parseColor(Colorstring.lanse));
                rlLive.setVisibility(View.GONE);
                linShop.setVisibility(View.VISIBLE);
                jdSy.setTextSize(16);
                jdSy2.setTextSize(22);
                TextPaint p3 = jdSy.getPaint();
                p3.setFakeBoldText(true);
                TextPaint p4 = jdSy2.getPaint();
                p4.setFakeBoldText(true);
                jdXiahua1.setAlpha(0f);
                jdXiahua2.setAlpha(1f);
                jdSy2.setChecked(true);
//                if (huaTiFragment == null) {
                liveShopFragment = new LiveShopFragment();
                transaction.add(R.id.jdfr, liveShopFragment);
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (liveShopFragment != null)
            transaction.hide(liveShopFragment);
        if (n5Fragment != null)
            transaction.hide(n5Fragment);
        transaction.commit();
    }

    @OnClick({R.id.jd_sy, R.id.jd_sy2, R.id.click_live_chat, R.id.click_live_gouwuche,R.id.rl_live})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jd_sy:
                position = 0;
                setTabSelection(position);
                break;
            case R.id.jd_sy2:
                position = 1;
                setTabSelection(position);
                break;

            case R.id.rl_live: //定位位置
                intent = new Intent(getContext(), SearchNearActivity.class);
                intent.putExtra("first", first);
                startActivity(intent);

                break;

            case R.id.click_live_chat:
                break;
            case R.id.click_live_gouwuche:
                break;
        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
