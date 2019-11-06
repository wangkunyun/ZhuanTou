package com.ztkj.wky.zhuantou.Activity.oa.punch;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.TeamStaticalDetailsAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.TeamStatiscalBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticaTeamFragment extends Fragment {


    @BindView(R.id.img_click_lase)
    ImageView imgClickLase;
    @BindView(R.id.tv_calDate)
    TextView tvCalDate;
    @BindView(R.id.img_click_next)
    ImageView imgClickNext;
    @BindView(R.id.tv_pj)
    TextView tvPj;
    @BindView(R.id.img_pj)
    ImageView imgPj;
    @BindView(R.id.rl_click_pj)
    RelativeLayout rlClickPj;
    @BindView(R.id.re_pj)
    RecyclerView rePj;
    @BindView(R.id.tv_cd)
    TextView tvCd;
    @BindView(R.id.img_cd)
    ImageView imgCd;
    @BindView(R.id.rl_click_cd)
    RelativeLayout rlClickCd;
    @BindView(R.id.re_cd)
    RecyclerView reCd;
    @BindView(R.id.tv_zt)
    TextView tvZt;
    @BindView(R.id.img_zt)
    ImageView imgZt;
    @BindView(R.id.rl_click_zt)
    RelativeLayout rlClickZt;
    @BindView(R.id.re_zt)
    RecyclerView reZt;
    @BindView(R.id.tv_qk)
    TextView tvQk;
    @BindView(R.id.img_qk)
    ImageView imgQk;
    @BindView(R.id.rl_click_qk)
    RelativeLayout rlClickQk;
    @BindView(R.id.re_qk)
    RecyclerView reQk;
    @BindView(R.id.tv_kg)
    TextView tvKg;
    @BindView(R.id.img_kg)
    ImageView imgKg;
    @BindView(R.id.rl_click_kg)
    RelativeLayout rlClickKg;
    @BindView(R.id.re_kg)
    RecyclerView reKg;
    @BindView(R.id.tv_wq)
    TextView tvWq;
    @BindView(R.id.img_wq)
    ImageView imgWq;
    @BindView(R.id.rl_click_wq)
    RelativeLayout rlClickWq;
    @BindView(R.id.re_wq)
    RecyclerView reWq;
    @BindView(R.id.tv_jb)
    TextView tvJb;
    @BindView(R.id.img_jb)
    ImageView imgJb;
    @BindView(R.id.rl_click_jb)
    RelativeLayout rlClickJb;
    @BindView(R.id.re_jb)
    RecyclerView reJb;
    Unbinder unbinder;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simYear = new SimpleDateFormat("yyyy");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simMonth = new SimpleDateFormat("MM");
    private String year, thisyear, month, thismonth;
    private String TAG = "StatisticaTeamFragment";
    private boolean tag1 = false;
    private boolean tag2 = false;
    private boolean tag3 = false;
    private boolean tag4 = false;
    private boolean tag5 = false;
    private boolean tag6 = false;
    private boolean tag7 = false;
    private TeamStatiscalBean.DataBean data;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistical_team_tab, container, false);
        unbinder = ButterKnife.bind(this, view);

        year = simYear.format(new Date().getTime());
        month = simMonth.format(new Date().getTime());
        thismonth = simMonth.format(new Date().getTime());
        thisyear = simYear.format(new Date().getTime());
        tvCalDate.setText(year + "年" + month + "月");

        rePj.setHasFixedSize(true);
        rePj.setNestedScrollingEnabled(false);
        reJb.setHasFixedSize(true);
        reJb.setNestedScrollingEnabled(false);
        reWq.setHasFixedSize(true);
        reWq.setNestedScrollingEnabled(false);
        reKg.setHasFixedSize(true);
        reKg.setNestedScrollingEnabled(false);
        reQk.setHasFixedSize(true);
        reQk.setNestedScrollingEnabled(false);
        reZt.setHasFixedSize(true);
        reZt.setNestedScrollingEnabled(false);
        reCd.setHasFixedSize(true);
        reCd.setNestedScrollingEnabled(false);


        requestStatistical();


        return view;
    }

    private void requestStatistical() {
        if (year.equals(thisyear) && month.equals(thismonth)) {
            imgClickNext.setEnabled(false);
        } else {
            imgClickNext.setEnabled(true);
        }
        Log.e(TAG, "onCreateView: " + SPUtils.getInstance().getString("token") + "    " + SPUtils.getInstance().getString("cid") + "    " + year + "    " + month);
        OkHttpUtils.post().url(Contents.TEAMSTATISTICS)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("year", year)
                .addParams("month", month)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                TeamStatiscalBean teamStatiscalBean = GsonUtil.gsonToBean(response, TeamStatiscalBean.class);
                data = teamStatiscalBean.getData();

                tvPj.setText(data.getWorkingHours() + "小时");
                tvCd.setText(data.getLateNumber() + "人");
                tvZt.setText(data.getLeaveEarlyNumber() + "人");
                tvQk.setText(data.getMissingCardNumber() + "人");
                tvKg.setText(data.getAbsenteeismNumber() + "人");
                tvWq.setText(data.getFieldPersonnelNumber() + "人");
                tvJb.setText(data.getOvertimeNumber() + "人");



            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_click_lase, R.id.img_click_next, R.id.rl_click_pj, R.id.rl_click_cd, R.id.rl_click_zt, R.id.rl_click_qk, R.id.rl_click_kg, R.id.rl_click_wq, R.id.rl_click_jb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_click_lase:
                month = Integer.parseInt(month) - 1 + "";
                if (Integer.parseInt(month) < 1) {
                    month = "12";
                    year = Integer.parseInt(year) - 1 + "";
                }
                tvCalDate.setText(year + "年" + month + "月");
                requestStatistical();

                break;
            case R.id.img_click_next:
                month = Integer.parseInt(month) + 1 + "";
                if (Integer.parseInt(month) > 12) {
                    month = "1";
                    year = Integer.parseInt(year) + 1 + "";
                }
                tvCalDate.setText(year + "年" + month + "月");
                requestStatistical();

                break;
            case R.id.rl_click_pj:
                if (tag1) {
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    tag1 = false;
                } else {
                    //展示数据
                    requestTeamDetails(1, rePj);
                    rePj.setVisibility(View.VISIBLE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_up);
                    tag1 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_cd:
                if (tag2) {
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    tag1 = false;
                } else {
                    //展示数据
                    requestTeamDetails(2, reCd);
                    reCd.setVisibility(View.VISIBLE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_up);
                    tag2 = true;
                    tag1 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_zt:
                if (tag3) {
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    tag3 = false;
                } else {
                    //展示数据
                    requestTeamDetails(3, reZt);
                    reZt.setVisibility(View.VISIBLE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_up);
                    tag3 = true;
                    tag2 = false;
                    tag1 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_qk:
                if (tag4) {
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    tag4 = false;
                } else {
                    //展示数据
                    requestTeamDetails(4, reQk);
                    reQk.setVisibility(View.VISIBLE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_up);
                    tag4 = true;
                    tag2 = false;
                    tag3 = false;
                    tag1 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_kg:
                if (tag5) {
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    tag5 = false;
                } else {
                    //展示数据
                    requestTeamDetails(5, reKg);
                    reKg.setVisibility(View.VISIBLE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_up);
                    tag5 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag1 = false;
                    tag6 = false;
                    tag7 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_wq:
                if (tag6) {
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    tag6 = false;
                } else {
                    //展示数据
                    requestTeamDetails(6, reWq);
                    reWq.setVisibility(View.VISIBLE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_up);
                    tag6 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag1 = false;
                    tag7 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
            case R.id.rl_click_jb:
                if (tag7) {
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                    tag7 = false;
                } else {
                    //展示数据
                    requestTeamDetails(7, reJb);
                    reJb.setVisibility(View.VISIBLE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_up);
                    tag7 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag1 = false;
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                }
                break;
        }
    }

    private void requestTeamDetails(int i, RecyclerView re) {
        TeamStatiscalBean.DataBean.ArrayNewBean arrayNew = data.getArrayNew();
        TeamStaticalDetailsAdapter teamStaticalDetailsAdapter = new TeamStaticalDetailsAdapter(getContext(), arrayNew, i);
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        re.setAdapter(teamStaticalDetailsAdapter);

    }
}
