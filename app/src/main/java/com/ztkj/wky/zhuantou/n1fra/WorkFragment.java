package com.ztkj.wky.zhuantou.n1fra;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.necer.calendar.MonthCalendar;
import com.necer.painter.InnerPainter;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.chat.AddressTab;
import com.ztkj.wky.zhuantou.Activity.chat.EChatConversationList;
import com.ztkj.wky.zhuantou.Activity.enterpriseService.SendWater;
import com.ztkj.wky.zhuantou.Activity.oa.AddSchedule;
import com.ztkj.wky.zhuantou.Activity.oa.Create_Team;
import com.ztkj.wky.zhuantou.Activity.oa.ScheduleList;
import com.ztkj.wky.zhuantou.Activity.oa.SetTongzhi;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.ApplyFor;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.Examine;
import com.ztkj.wky.zhuantou.Activity.oa.punch.PunchInTab;
import com.ztkj.wky.zhuantou.Activity.oa.report.ReportTab;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AllApplyBean;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.GetCompanyAnnBean;
import com.ztkj.wky.zhuantou.bean.ReportRedDot;
import com.ztkj.wky.zhuantou.bean.ScheduleListBean;
import com.ztkj.wky.zhuantou.bean.ServersListBean;
import com.ztkj.wky.zhuantou.bean.WhatDaysBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 企业服务
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends Fragment {
    @BindView(R.id.n4_create_team)
    TextView n4CreateTeam;
    @BindView(R.id.tv_n4_celebrated_dictum)
    TextView tvN4CelebratedDictum;
    @BindView(R.id.tv_n4_author)
    TextView tvN4Author;
    @BindView(R.id.tv_n4_daka)
    TextView tvN4Daka;
    @BindView(R.id.n4_daka)
    RelativeLayout n4Daka;
    @BindView(R.id.img_n4_apply)
    ImageView imgN4Apply;
    @BindView(R.id.tv_n4_apply)
    TextView tvN4Apply;
    @BindView(R.id.n4_apply)
    RelativeLayout n4Apply;
    @BindView(R.id.img_n4_log)
    ImageView imgN4Log;
    @BindView(R.id.tv_n4_log)
    TextView tvN4Log;
    @BindView(R.id.n4_log)
    RelativeLayout n4Log;
    @BindView(R.id.img_n4_examine)
    ImageView imgN4Examine;
    @BindView(R.id.tv_n4_examine)
    TextView tvN4Examine;
    @BindView(R.id.n4_examine)
    RelativeLayout n4Examine;
    @BindView(R.id.all_servicetv2)
    TextView allServicetv2;
    @BindView(R.id.img_n4_daka1)
    ImageView imgN4Daka1;
    @BindView(R.id.tv_n4_daka1)
    TextView tvN4Daka1;
    @BindView(R.id.n4_daka1)
    RelativeLayout n4Daka1;
    @BindView(R.id.img_n4_apply1)
    ImageView imgN4Apply1;
    @BindView(R.id.tv_n4_apply1)
    TextView tvN4Apply1;
    @BindView(R.id.n4_apply1)
    RelativeLayout n4Apply1;
    @BindView(R.id.img_n4_log1)
    ImageView imgN4Log1;
    @BindView(R.id.tv_n4_log1)
    TextView tvN4Log1;
    @BindView(R.id.n4_log1)
    RelativeLayout n4Log1;
    @BindView(R.id.img_n4_examine1)
    ImageView imgN4Examine1;
    @BindView(R.id.tv_n4_examine1)
    TextView tvN4Examine1;
    @BindView(R.id.n4_examine1)
    RelativeLayout n4Examine1;
    @BindView(R.id.calender)
    MonthCalendar calender;
    @BindView(R.id.tv_tongzhi)
    TextView tvTongzhi;
    @BindView(R.id.tv_addDaySchedule)
    TextView tvAddDaySchedule;
    @BindView(R.id.img_click_lase)
    ImageView imgClickLase;
    @BindView(R.id.tv_calDate)
    TextView tvCalDate;
    @BindView(R.id.img_click_next)
    ImageView imgClickNext;
    @BindView(R.id.img_n4_daka2)
    ImageView imgN4Daka2;
    @BindView(R.id.tv_n4_daka2)
    TextView tvN4Daka2;
    @BindView(R.id.n4_daka2)
    RelativeLayout n4Daka2;
    @BindView(R.id.img_n4_apply2)
    ImageView imgN4Apply2;
    @BindView(R.id.tv_n4_apply2)
    TextView tvN4Apply2;
    @BindView(R.id.n4_apply2)
    RelativeLayout n4Apply2;
    @BindView(R.id.img_n4_log3)
    ImageView imgN4Log3;
    @BindView(R.id.tv_n4_log3)
    TextView tvN4Log3;
    @BindView(R.id.n4_log3)
    RelativeLayout n4Log3;
    @BindView(R.id.img_n4_examine4)
    ImageView imgN4Examine4;
    @BindView(R.id.tv_n4_examine4)
    TextView tvN4Examine4;
    @BindView(R.id.n4_examine4)
    RelativeLayout n4Examine4;
    @BindView(R.id.ExamineRedDot)
    TextView ExamineRedDot;
    @BindView(R.id.ReportRedDot)
    TextView ReportRedDot;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    private SharedPreferencesHelper sp_apply;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp_create_team;
    private String uid, token, team_name, cid;
    private boolean isFirst = true;
    private Intent intent;
    private String url = StringUtils.jiekouqianzui + "Article/enterpriseService";
    private String bannerUrl = StringUtils.jiekouqianzui + "Article/banner";

    private String TAG = "WorkFragment";
    private View view;
    private ArrayList<String> days;
    private List<ServersListBean.DataBean> data;
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_n4, container, false);
        unbinder = ButterKnife.bind(this, view);
        /**
         * 从SP中获取token等
         */

        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        sp_apply = new SharedPreferencesHelper(getActivity(), "apply");

        sp_create_team = new SharedPreferencesHelper(getContext(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        team_name = (String) sp_create_team.getSharedPreference("Create_team_name", "");
        intent = new Intent();
        Log.e(TAG, "onCreateView: +=====team_name=====" + team_name + "===");
        //如果团队名称为空则显示创建团队，不为空则显示团队名称
        if (!team_name.equals("0")) {
            n4CreateTeam.setText(team_name);
        }
        //==========================日历============================
//        calender.post(() -> calender.setSelectedMode(SelectedModel.SINGLE_UNSELECTED));

        //日历左右滑动监听
        calender.setOnTouchListener((v, event) -> {
            isFirst = true;
            return false;
        });


        calender.setOnCalendarChangedListener((baseCalendar, year, month, localDate) -> {
            tvCalDate.setText(year + "年" + month + "月");
            if (isFirst) {
                isFirst = false;
            } else {
                if (!StringUtils.isEmpty(uid)) {
                    if (days != null && days.size() > 0) {
                        for (int i = 0; i < days.size(); i++) {
                            if (days.contains(localDate.toString())) {
                                intent = new Intent(getContext(), ScheduleList.class);
                                intent.putExtra("Schedule_date", localDate.toString());
                                startActivity(intent);
                                break;
                            }
                        }
                    }

                }
            }
        });


        //==========================日历============================
        if (!StringUtils.isEmpty(uid)) {
            SchedulerInnerPainter();
        }

        gi();
        //==========================请求企业服务列表========================
        requestFuwu();
        //==========================请求企业服务列表========================


        return view;
    }

    private void gi() {

        OkHttpUtils.post()
                .url(bannerUrl)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("repres", response);
                        BannerBean bannerBean = gson.fromJson(response, BannerBean.class);

                        if (bannerBean.getErrno().equals("200")) {
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                imgs.add(data.get(i).getBanner());
                                strs.add(data.get(i).getHref());
                            }

                            banner.setIndicatorGravity(BannerConfig.CENTER);
                            banner.setDelayTime(5000);
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(imgs);
                            banner.start();
                        } else {

                        }
                    }
                });

    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    private void requestRedDot() {
        //请求审批小红点
        OkHttpUtils.post()
                .url(Contents.NOTEXAME)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("type", "0")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                AllApplyBean allApplyBean = new Gson().fromJson(response, AllApplyBean.class);
                if (allApplyBean.getErrno().equals("200")) {
                    List<AllApplyBean.DataBean> data = allApplyBean.getData();
                    if (data.size() != 0) {
                        ExamineRedDot.setVisibility(View.VISIBLE);
                    } else {
                        ExamineRedDot.setVisibility(View.GONE);
                    }
                }
            }
        });
        //请求日志小红点
        OkHttpUtils.post()
                .url(Contents.REPORTREDDOT)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("type", "0")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ReportRedDot reportRedDot = GsonUtil.gsonToBean(response, ReportRedDot.class);
                if (reportRedDot.getErrno().equals("200")) {
                    if(reportRedDot.getData().getNum()!=null){
                        int num = Integer.parseInt(reportRedDot.getData().getNum());
                        Contents.reportReddotNum = num;
                        if (num != 0) {
                            ReportRedDot.setVisibility(View.VISIBLE);
                        } else {
                            ReportRedDot.setVisibility(View.GONE);
                        }
                    }

                }
            }
        });
    }

    private void requestFuwu() {
        OkHttpUtils.post().url(Contents.SERVERLIST)
                .addParams("token", token)
                .addParams("types", "1")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: =====请求企业服务列表接口=====" + response);
                ServersListBean serversListBean = new Gson().fromJson(response, ServersListBean.class);
                data = serversListBean.getData();
                for (int i = 0; i < data.size(); i++) {
                    Log.e(TAG, "onResponse: " + data.get(i));
                }
                showList(imgN4Daka1, tvN4Daka1, data.get(0).getElogo(), data.get(0).getEname());
                showList(imgN4Apply1, tvN4Apply1, data.get(1).getElogo(), data.get(1).getEname());
                showList(imgN4Log1, tvN4Log1, data.get(2).getElogo(), data.get(2).getEname());
                showList(imgN4Examine1, tvN4Examine1, data.get(3).getElogo(), data.get(3).getEname());
                showList(imgN4Daka2, tvN4Daka2, data.get(4).getElogo(), data.get(4).getEname());
                showList(imgN4Apply2, tvN4Apply2, data.get(5).getElogo(), data.get(5).getEname());
                showList(imgN4Log3, tvN4Log3, data.get(6).getElogo(), data.get(6).getEname());
                showList(imgN4Examine4, tvN4Examine4, data.get(7).getElogo(), data.get(7).getEname());
            }

            private void showList(ImageView img, TextView tv, String elogo, String ename) {
                Glide.with(Objects.requireNonNull(getActivity())).load(elogo).into(img);
                tv.setText(ename);
            }

        });


    }

    private void SchedulerInnerPainter() {
        InnerPainter innerPainter = (InnerPainter) calender.getCalendarPainter();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("MM");
        //获取汉字格式的时间戳
        String year = simpleDateFormat2.format(new Date().getTime());
        String month = simpleDateFormat3.format(new Date().getTime());

        OkHttpUtils.post().url(Contents.WEEKDAY)
                .addParams("", "")
                .addParams("year", year)
                .addParams("month", month)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                WhatDaysBean whatDaysBean = new Gson().fromJson(response, WhatDaysBean.class);
                String dayNum = whatDaysBean.getData().getDayNum();
                OkHttpUtils.post().url(Contents.SELECTSCHEDULE)
                        .addParams("token", token)
                        .addParams("uid", uid)
                        .addParams("start_days", year + "-" + month + "-" + "1")
                        .addParams("end_days", year + "-" + month + "-" + dayNum)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, "onResponse: " + response);
                        ScheduleListBean scheduleListBean = new Gson().fromJson(response, ScheduleListBean.class);
                        if (scheduleListBean.getErrno().equals("200")) {
                            days = new ArrayList<>();
                            List<ScheduleListBean.DataBean> data = scheduleListBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                days.add(data.get(i).getDays());
                            }

//                        for (int i = 0; i < days.size(); i++) {
//                            Log.e(TAG, "onResponse: " + days.get(i));
//                        }

                            innerPainter.setPointList(days);
                        }

                    }
                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        sp_apply = new SharedPreferencesHelper(getActivity(), "apply");

        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        //如果团队名称为空则显示创建团队，不为空则显示团队名称
        sp_create_team = new SharedPreferencesHelper(getContext(), "Create_team");
        team_name = (String) sp_create_team.getSharedPreference("Create_team_name", "");
//        Log.e(TAG, "onResume: +" + team_name + "===");
        if (!team_name.equals("0")) {
            n4CreateTeam.setText(team_name);
            n4CreateTeam.setEnabled(false);
        }

        //获取到通知内容
        if (!StringUtils.isEmpty(uid) && !StringUtils.isEmpty(token)) {
            requestTongzhi();
        }
        //==========================小红点========================
        requestRedDot();
        //==========================小红点========================
    }


    private void requestTongzhi() {
        Log.e(TAG, "requestTongzhi: " + uid + "====" + token + "====" + cid);
        OkHttpUtils.post().url(Contents.GETCOMPANYANNOUNCEMENT)
                .addParams("token", token)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:gettongzhi " + response);
                GetCompanyAnnBean getCompanyAnnBean = new Gson().fromJson(response, GetCompanyAnnBean.class);
                if (getCompanyAnnBean.getErrno().equals("200")) {
                    String notice = getCompanyAnnBean.getData().getNotice();
//                Log.e(TAG, "onResponse: ==========notice=================" + notice);
                    if (!com.hyphenate.easeui.utils.map.StringUtils.isEmpty(notice)) {
                        if (!notice.equals("0")) {
                            tvTongzhi.setText("通知：" + notice);
                        }
                    } else {
                    }
                }


            }
        });
    }


    private void popuinit(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_sz, null);
        pbutton.setVisibility(View.GONE);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.n4_create_team, R.id.img_n4_address_book, R.id.img_n4_chat, R.id.n4_daka, R.id.n4_apply, R.id.n4_log, R.id.n4_examine
            , R.id.n4_daka1, R.id.n4_apply1, R.id.n4_log1, R.id.n4_examine1, R.id.lin_click_tongzhi,
            R.id.tv_addDaySchedule, R.id.img_click_lase, R.id.img_click_next
            , R.id.n4_daka2, R.id.n4_apply2, R.id.n4_log3, R.id.n4_examine4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.n4_create_team://创建团队
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getActivity(), Create_Team.class);
                startActivity(intent);
                break;
            case R.id.img_n4_address_book://联系人
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getActivity(), AddressTab.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();

                break;
            case R.id.img_n4_chat://聊天
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getActivity(), EChatConversationList.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.n4_daka://打卡
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), PunchInTab.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                OrderTabActivity.start(getActivity());
//                ApplyInvoiceActivity.start(getActivity());
//                TradeLogisticsActivity.start(getActivity());
                break;


            case R.id.n4_log://日志
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                if (cid.equals(0)) {
                    popuinit("该功能需加入团队后方可使用，请先加入团队", "取消", "确认");
                } else {
                    intent.setClass(getActivity(), ReportTab.class);
                    startActivity(intent);
                }

                break;

            case R.id.n4_apply://申请
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                sp_apply.put("apply_isAppover", "0");
                intent = new Intent(getContext(), ApplyFor.class);
                startActivity(intent);
                break;
            case R.id.n4_examine://审批
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                sp_apply.put("apply_isAppover", "1");
                intent.setClass(getActivity(), Examine.class);
                startActivity(intent);
                break;

            /**
             * 企业服务
             */
            case R.id.n4_daka1:
//                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                intent = new Intent(getContext(), SendWater.class);
                intent.putExtra("eid", data.get(0).getEid());
                startActivity(intent);
                break;
            case R.id.n4_apply1:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(1).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_log1:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(2).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_examine1:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(3).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_daka2:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(4).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_apply2:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(5).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_log3:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(6).getEid());
//                startActivity(intent);
                break;
            case R.id.n4_examine4:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
//                intent = new Intent(getContext(), SendWater.class);
//                intent.putExtra("eid", data.get(7).getEid());
//                startActivity(intent);
                break;
            case R.id.lin_click_tongzhi: //通知
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), SetTongzhi.class);
                startActivity(intent);
                break;
            case R.id.tv_addDaySchedule://添加日程
//                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();

                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), AddSchedule.class);
                intent.putExtra("tag", "0");
                startActivity(intent);
                break;
            case R.id.img_click_lase://上一月
                isFirst = true;
                calender.toLastPager();
                break;
            case R.id.img_click_next://下一月
                isFirst = true;
                calender.toNextPager();
                break;
        }
    }


}
