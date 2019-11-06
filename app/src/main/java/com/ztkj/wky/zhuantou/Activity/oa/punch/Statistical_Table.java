package com.ztkj.wky.zhuantou.Activity.oa.punch;
/**
 * 作者：wky
 * 功能描述：
 * 非管理员权限统计表
 * 统计表
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MineStaticalDetailsAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.MineStatiscallBean;
import com.ztkj.wky.zhuantou.bean.MineStatisticalDetailsBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Statistical_Table extends AppCompatActivity {


    @BindView(R.id.tv_pj)
    TextView tvPj;
    @BindView(R.id.re_pj)
    RecyclerView rePj;
    @BindView(R.id.rl_click_pj)
    RelativeLayout rlClickPj;
    @BindView(R.id.tv_cq)
    TextView tvCq;
    @BindView(R.id.re_cq)
    RecyclerView reCq;
    @BindView(R.id.rl_click_cq)
    RelativeLayout rlClickCq;
    @BindView(R.id.tv_xx)
    TextView tvXx;
    @BindView(R.id.re_xx)
    RecyclerView reXx;
    @BindView(R.id.rl_click_xx)
    RelativeLayout rlClickXx;
    @BindView(R.id.tv_cd)
    TextView tvCd;
    @BindView(R.id.re_cd)
    RecyclerView reCd;
    @BindView(R.id.rl_click_cd)
    RelativeLayout rlClickCd;
    @BindView(R.id.tv_zt)
    TextView tvZt;
    @BindView(R.id.re_zt)
    RecyclerView reZt;
    @BindView(R.id.rl_click_zt)
    RelativeLayout rlClickZt;
    @BindView(R.id.tv_qk)
    TextView tvQk;
    @BindView(R.id.re_qk)
    RecyclerView reQk;
    @BindView(R.id.rl_click_qk)
    RelativeLayout rlClickQk;
    @BindView(R.id.tv_kg)
    TextView tvKg;
    @BindView(R.id.re_kg)
    RecyclerView reKg;
    @BindView(R.id.rl_click_kg)
    RelativeLayout rlClickKg;
    @BindView(R.id.tv_wq)
    TextView tvWq;
    @BindView(R.id.re_wq)
    RecyclerView reWq;
    @BindView(R.id.rl_click_wq)
    RelativeLayout rlClickWq;
    @BindView(R.id.tv_jb)
    TextView tvJb;
    @BindView(R.id.re_jb)
    RecyclerView reJb;
    @BindView(R.id.rl_click_jb)
    RelativeLayout rlClickJb;
    @BindView(R.id.img_click_lase)
    ImageView imgClickLase;
    @BindView(R.id.tv_calDate)
    TextView tvCalDate;
    @BindView(R.id.img_click_next)
    ImageView imgClickNext;
    @BindView(R.id.img_pj)
    ImageView imgPj;
    @BindView(R.id.img_cq)
    ImageView imgCq;
    @BindView(R.id.img_xx)
    ImageView imgXx;
    @BindView(R.id.img_cd)
    ImageView imgCd;
    @BindView(R.id.img_zt)
    ImageView imgZt;
    @BindView(R.id.img_qk)
    ImageView imgQk;
    @BindView(R.id.img_kg)
    ImageView imgKg;
    @BindView(R.id.img_wq)
    ImageView imgWq;
    @BindView(R.id.img_jb)
    ImageView imgJb;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private boolean tag1 = false;
    private boolean tag2 = false;
    private boolean tag3 = false;
    private boolean tag4 = false;
    private boolean tag5 = false;
    private boolean tag6 = false;
    private boolean tag7 = false;
    private boolean tag8 = false;
    private boolean tag9 = false;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simYear = new SimpleDateFormat("yyyy");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simMonth = new SimpleDateFormat("MM");
    private String year, month;
    private String TAG = "StatisticalMineFragment";
    private ArrayList<Boolean> tagbooleans;
    private ArrayList<RecyclerView> listRe;
    private ArrayList<ImageView> listImageViews;
    private Intent intent;
    private String uid;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical__table);
        ButterKnife.bind(this);
        intent = getIntent();
        uid = intent.getStringExtra("uid");
        toolbar.setVisibility(View.VISIBLE);
        initDate();
        tvCalDate.setText(year + "年" + month + "月");
        requestStatistical();
    }

    private void requestStatistical() {
        Log.e(TAG, "requestStatistical: " + year + "    " + month);
        OkHttpUtils.post().url(Contents.MINESTATISTICS)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", uid)
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
                Log.e(TAG, "onResponse: 个人第一层数据" + response);
                MineStatiscallBean mineStatiscallBean = GsonUtil.gsonToBean(response, MineStatiscallBean.class);
                if (mineStatiscallBean.getErrno().equals("200")) {
                    MineStatiscallBean.DataBean data = mineStatiscallBean.getData();
                    if (!data.getLate().equals("0")) {
                        tvCd.setTextColor(Color.parseColor("#E61A1A"));
                    }
                    if (!data.getLeaveEarly().equals("0")) {
                        tvZt.setTextColor(Color.parseColor("#E61A1A"));
                    }
                    if (!data.getMissingCard().equals("0")) {
                        tvQk.setTextColor(Color.parseColor("#E61A1A"));
                    }
                    if (!data.getAbsenteeism().equals("0")) {
                        tvKg.setTextColor(Color.parseColor("#E61A1A"));
                    }
                    tvPj.setText(data.getWorkingHours() + "小时");
                    tvCq.setText(data.getAttendanceDays() + "天");
                    tvXx.setText(data.getRest() + "天");
                    tvCd.setText(data.getLate() + "次");
                    tvZt.setText(data.getLeaveEarly() + "次");
                    tvQk.setText(data.getMissingCard() + "次");
                    tvKg.setText(data.getAbsenteeism() + "次");
                    tvWq.setText(data.getFieldPersonnel() + "次");
                    tvJb.setText(data.getOvertime() + "次");
                }

            }
        });
    }


    @SuppressLint("SetTextI18n")
    @OnClick({R.id.layout_back, R.id.img_click_lase, R.id.img_click_next, R.id.rl_click_pj, R.id.rl_click_cq, R.id.rl_click_xx, R.id.rl_click_cd, R.id.rl_click_zt, R.id.rl_click_qk, R.id.rl_click_kg, R.id.rl_click_wq, R.id.rl_click_jb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_click_lase: //上一月
                month = Integer.parseInt(month) - 1 + "";
                if (Integer.parseInt(month) < 1) {
                    month = "12";
                    year = Integer.parseInt(year) - 1 + "";
                }
                tvCalDate.setText(year + "年" + month + "月");
                requestStatistical();
                break;
            case R.id.img_click_next: //下一月
                month = Integer.parseInt(month) + 1 + "";
                if (Integer.parseInt(month) > 12) {
                    month = "1";
                    year = Integer.parseInt(year) + 1 + "";
                }
                tvCalDate.setText(year + "年" + month + "月");
                requestStatistical();
                break;
            case R.id.rl_click_pj: //平均时长
                if (tag1) {
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    tag1 = false;
                } else {
                    //展示数据
                    requestMineDetails(1, rePj);
                    rePj.setVisibility(View.VISIBLE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_up);
                    tag1 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_cq: //出勤天数
                if (tag2) {
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    tag2 = false;
                } else {
                    //展示数据
                    requestMineDetails(2, reCq);
                    reCq.setVisibility(View.VISIBLE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_up);
                    tag2 = true;
                    tag1 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_xx: //休息天数
                if (tag3) {
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
                    tag3 = false;
                } else {
                    //展示数据
                    requestMineDetails(3, reXx);
                    reXx.setVisibility(View.VISIBLE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_up);
                    tag3 = true;
                    tag2 = false;
                    tag1 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    rePj.setVisibility(View.GONE);
                    imgPj.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_cd: //迟到天数
                if (tag4) {
                    reCd.setVisibility(View.GONE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_down);
                    tag4 = false;
                } else {
                    //展示数据
                    requestMineDetails(4, reCd);
                    reCd.setVisibility(View.VISIBLE);
                    imgCd.setImageResource(R.mipmap.icon_arrows_up);
                    tag4 = true;
                    tag2 = false;
                    tag3 = false;
                    tag1 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_zt: //早退次数
                if (tag5) {
                    reZt.setVisibility(View.GONE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_down);
                    tag5 = false;

                } else {
                    //展示数据
                    requestMineDetails(5, reZt);
                    reZt.setVisibility(View.VISIBLE);
                    imgZt.setImageResource(R.mipmap.icon_arrows_up);
                    tag5 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag1 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_qk: //缺卡次数
                if (tag6) {
                    reQk.setVisibility(View.GONE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_down);
                    tag6 = false;
                } else {
                    //展示数据
                    requestMineDetails(6, reQk);
                    reQk.setVisibility(View.VISIBLE);
                    imgQk.setImageResource(R.mipmap.icon_arrows_up);
                    tag6 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag1 = false;
                    tag7 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_kg: //旷工次数
                if (tag7) {
                    reKg.setVisibility(View.GONE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_down);
                    tag7 = false;
                } else {
                    //展示数据
                    requestMineDetails(7, reKg);
                    reKg.setVisibility(View.VISIBLE);
                    imgKg.setImageResource(R.mipmap.icon_arrows_up);
                    tag7 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag1 = false;
                    tag8 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_wq: //外勤次数
                if (tag8) {
                    reWq.setVisibility(View.GONE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_down);
                    tag8 = false;
                } else {
                    //展示数据
                    requestMineDetails(8, reWq);
                    reWq.setVisibility(View.VISIBLE);
                    imgWq.setImageResource(R.mipmap.icon_arrows_up);
                    tag8 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag1 = false;
                    tag9 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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
            case R.id.rl_click_jb: //加班时长
                if (tag9) {
                    reJb.setVisibility(View.GONE);
                    imgJb.setImageResource(R.mipmap.icon_arrows_down);
                    tag9 = false;
                } else {
                    //展示数据
                    requestMineDetails(9, reJb);
                    imgJb.setImageResource(R.mipmap.icon_arrows_up);
                    reJb.setVisibility(View.VISIBLE);
                    tag9 = true;
                    tag2 = false;
                    tag3 = false;
                    tag4 = false;
                    tag5 = false;
                    tag6 = false;
                    tag7 = false;
                    tag8 = false;
                    tag1 = false;
                    reCq.setVisibility(View.GONE);
                    imgCq.setImageResource(R.mipmap.icon_arrows_down);
                    reXx.setVisibility(View.GONE);
                    imgXx.setImageResource(R.mipmap.icon_arrows_down);
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

    private void shouOrHind(RecyclerView re, ImageView img) {
        listRe.remove(re);
        listImageViews.remove(img);
        for (int i = 0; i < listRe.size(); i++) {
            listRe.get(i).setVisibility(View.GONE);
            listImageViews.get(i).setImageResource(R.mipmap.icon_arrows_down);
        }
    }

    private void requestMineDetails(int num, RecyclerView re) {
        Log.e(TAG, "requestMineDetails: " + SPUtils.getInstance().getString("token") + "    " + SPUtils.getInstance().getString("uid")
                + "    " + SPUtils.getInstance().getString("cid") + "   " + year + "   " + month);
        OkHttpUtils.post().url(Contents.MINESTATISTICSDETAILS)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", uid)
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("year", year)
                .addParams("month", month)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + year + month);
                Log.e(TAG, "onResponse: + 个人数据详情 " + response);
                MineStatisticalDetailsBean mineStatisticalDetailsBean = GsonUtil.gsonToBean(response, MineStatisticalDetailsBean.class);
                MineStatisticalDetailsBean.DataBean data = mineStatisticalDetailsBean.getData();
                MineStaticalDetailsAdapter mineStaticalDetailsAdapter = new MineStaticalDetailsAdapter(Statistical_Table.this, data, num);
                re.setLayoutManager(new LinearLayoutManager(Statistical_Table.this));
                re.setAdapter(mineStaticalDetailsAdapter);
            }
        });

    }


    private void initDate() {
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
        reXx.setHasFixedSize(true);
        reXx.setNestedScrollingEnabled(false);
        reCq.setHasFixedSize(true);
        reCq.setNestedScrollingEnabled(false);
        year = simYear.format(new Date().getTime());
        month = simMonth.format(new Date().getTime());


        tagbooleans = new ArrayList<>();
        tagbooleans.add(tag1);
        tagbooleans.add(tag2);
        tagbooleans.add(tag3);
        tagbooleans.add(tag4);
        tagbooleans.add(tag5);
        tagbooleans.add(tag6);
        tagbooleans.add(tag7);
        tagbooleans.add(tag8);
        tagbooleans.add(tag9);
        listRe = new ArrayList<>();
        listRe.add(rePj);
        listRe.add(reCq);
        listRe.add(reXx);
        listRe.add(reCd);
        listRe.add(reZt);
        listRe.add(reQk);
        listRe.add(reKg);
        listRe.add(reWq);
        listRe.add(reJb);
        listImageViews = new ArrayList<>();
        listImageViews.add(imgPj);
        listImageViews.add(imgCq);
        listImageViews.add(imgXx);
        listImageViews.add(imgCd);
        listImageViews.add(imgZt);
        listImageViews.add(imgQk);
        listImageViews.add(imgKg);
        listImageViews.add(imgWq);
        listImageViews.add(imgJb);
    }
}
