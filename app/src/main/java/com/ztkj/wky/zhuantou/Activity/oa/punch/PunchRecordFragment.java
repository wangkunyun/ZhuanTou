package com.ztkj.wky.zhuantou.Activity.oa.punch;

/**
 * 作者：wky
 * 功能描述：
 * 打卡记录 考勤记录
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.listener.OnCalendarChangedListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.PunchInListBean;
import com.ztkj.wky.zhuantou.bean.ThreeAndOneBean;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PunchRecordFragment extends Fragment {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.img_click_lase)
    ImageView imgClickLase;
    @BindView(R.id.tv_calDate)
    TextView tvCalDate;
    @BindView(R.id.img_click_next)
    ImageView imgClickNext;
    @BindView(R.id.calender)
    MonthCalendar calender;
    Unbinder unbinder;
    @BindView(R.id.tv_todayHours)
    TextView tvTodayHours;
    @BindView(R.id.tv_companyWorkOn)
    TextView tvCompanyWorkOn;
    @BindView(R.id.tv_lateWorkOn)
    TextView tvLateWorkOn;
    @BindView(R.id.tv_WorkOn)
    TextView tvWorkOn;
    @BindView(R.id.tv_ApplyWorkOn)
    TextView tvApplyWorkOn;
    @BindView(R.id.tv_CompanyWorkOff)
    TextView tvCompanyWorkOff;
    @BindView(R.id.tv_lateWorkOff)
    TextView tvLateWorkOff;
    @BindView(R.id.tv_WorkOff)
    TextView tvWorkOff;
    @BindView(R.id.tv_ApplyWorkOff)
    TextView tvApplyWorkOff;
    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid, token, cid;
    private String TAG = "PunchRecordFragment";
    private String addTime;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_punch_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        layoutTitleTv.setText("考勤记录");
        more.setVisibility(View.VISIBLE);
        more.setText("统计表");
        tvCompanyWorkOn.setText(Contents.WORKON);
        tvCompanyWorkOff.setText(Contents.WORKOFF);

        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(Objects.requireNonNull(getContext()), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getContext(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");


        calender.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
                tvCalDate.setText(year + "年" + month + "月");
                requestPunchInList(year + "", month + "", localDate.getDayOfMonth() + "");
            }
        });

        return view;
    }

    private void requestPunchInList(String year, String month, String day) {
        OkHttpUtils.post().url(Contents.PUNCHINLIST)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
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
                PunchInListBean punchInListBean = GsonUtil.gsonToBean(response, PunchInListBean.class);
                if (punchInListBean.getErrno().equals("200")) {
                    List<PunchInListBean.DataBean> data = punchInListBean.getData();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getDay().equals(day)) {
                            addTime = data.get(i).getAddtime();
                            //设置今日时长
                            if (data.get(i).getStart_time().equals("0") || data.get(i).getEnd_time().equals("0")) {
                                tvTodayHours.setText("今日工时：0小时");
                            } else {
                                int todayHour = Integer.parseInt(data.get(i).getEnd_time()) - Integer.parseInt(data.get(i).getStart_time());
                                tvTodayHours.setText("今日工时：" + todayHour / 3600 + "小时");
                            }

                            //判断设置出勤
                            if (!data.get(i).getStart_time().equals("0")) {
                                tvWorkOn.setVisibility(View.VISIBLE);
                                String stron = timeStamp2Date(Integer.parseInt(data.get(i).getStart_time()), "HH:mm");
                                tvWorkOn.setText("上班打卡 " + stron);
                                if (!data.get(i).getLate().equals("0")) {
                                    tvLateWorkOn.setVisibility(View.VISIBLE);
                                    tvApplyWorkOn.setVisibility(View.VISIBLE);
                                    tvLateWorkOn.setText("迟到");
                                }
                                if (!data.get(i).getStart_field_address().equals("0")) {
                                    tvLateWorkOn.setVisibility(View.VISIBLE);
                                    tvApplyWorkOn.setVisibility(View.VISIBLE);
                                    tvLateWorkOn.setText("外勤");
                                }
                                if (data.get(i).getLate().equals("0") && data.get(i).getStart_field_address().equals("0")) {
                                    tvLateWorkOn.setVisibility(View.GONE);
                                    tvApplyWorkOn.setVisibility(View.GONE);
                                }
                            } else {
                                tvWorkOn.setVisibility(View.GONE);
                                tvApplyWorkOn.setVisibility(View.VISIBLE);
                                tvLateWorkOn.setVisibility(View.VISIBLE);
                                tvLateWorkOn.setText("缺卡");
                            }

                            if (!data.get(i).getEnd_time().equals("0")) {
                                tvWorkOff.setVisibility(View.VISIBLE);
                                String stroff = timeStamp2Date(Integer.parseInt(data.get(i).getEnd_time()), "HH:mm");
                                tvWorkOff.setText("下班打卡 " + stroff);
                                if (!data.get(i).getLeave_early().equals("0")) {
                                    tvLateWorkOff.setVisibility(View.VISIBLE);
                                    tvApplyWorkOff.setVisibility(View.VISIBLE);
                                    tvLateWorkOff.setText("早退");
                                }
                                if (!data.get(i).getEnd_field_address().equals("0")) {
                                    tvLateWorkOff.setVisibility(View.VISIBLE);
                                    tvApplyWorkOff.setVisibility(View.VISIBLE);
                                    tvLateWorkOff.setText("外勤");
                                }
                                if (data.get(i).getLeave_early().equals("0") && data.get(i).getEnd_field_address().equals("0")) {
                                    tvLateWorkOff.setVisibility(View.GONE);
                                    tvApplyWorkOff.setVisibility(View.GONE);
                                }
                            } else {
                                tvWorkOff.setVisibility(View.GONE);
                                tvApplyWorkOff.setVisibility(View.VISIBLE);
                                tvLateWorkOff.setVisibility(View.VISIBLE);
                                tvLateWorkOff.setText("缺卡");
                            }
                        }
//                        else {
//                            Log.e(TAG, "onResponse: " + day + "没有数据");
//                            tvWorkOn.setVisibility(View.GONE);
//                            tvWorkOff.setVisibility(View.GONE);
//                            tvLateWorkOn.setVisibility(View.GONE);
//                            tvLateWorkOff.setVisibility(View.GONE);
//                            tvApplyWorkOn.setVisibility(View.GONE);
//                            tvApplyWorkOff.setVisibility(View.GONE);
//                            break;
//                        }

                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        requestPunchInInfo();

    }

    private void requestPunchInInfo() {
        OkHttpUtils.post().url(Contents.SANHEYIPANDUAN)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("x", String.valueOf(Contents.LONGITUDE))
                .addParams("y", String.valueOf(Contents.LATITUDE))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ThreeAndOneBean threeAndOneBean = new Gson().fromJson(response, ThreeAndOneBean.class);
                ThreeAndOneBean.DataBean data = threeAndOneBean.getData();
                if (data.getMorning_attendance().equals("2")) {
                    tvWorkOn.setVisibility(View.VISIBLE);
                    String stron = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getStart_time()), "HH:mm");
                    tvWorkOn.setText("上班打卡 " + stron);
                    if (!data.getPunchInInfo().getLate().equals("0")) {
                        tvLateWorkOn.setVisibility(View.VISIBLE);
                        tvApplyWorkOn.setVisibility(View.VISIBLE);
                        tvLateWorkOn.setText("迟到");
                    }
                    if (!data.getPunchInInfo().getStart_field_address().equals("0")) {
                        tvLateWorkOn.setVisibility(View.VISIBLE);
                        tvApplyWorkOn.setVisibility(View.VISIBLE);
                        tvLateWorkOn.setText("外勤");
                    }
                }

                if (data.getEnd_night().equals("2")) {
                    tvWorkOff.setVisibility(View.VISIBLE);
                    String stroff = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getEnd_time()), "HH:mm");
                    tvWorkOff.setText("下班打卡 " + stroff);
                    if (!data.getPunchInInfo().getLeave_early().equals("0")) {
                        tvLateWorkOff.setVisibility(View.VISIBLE);
                        tvApplyWorkOff.setVisibility(View.VISIBLE);
                        tvLateWorkOff.setText("早退");
                    }
                    if (!data.getPunchInInfo().getEnd_field_address().equals("0")) {
                        tvLateWorkOff.setVisibility(View.VISIBLE);
                        tvApplyWorkOff.setVisibility(View.VISIBLE);
                        tvLateWorkOn.setText("外勤");
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_back, R.id.more, R.id.tv_ApplyWorkOn, R.id.tv_ApplyWorkOff, R.id.img_click_lase, R.id.img_click_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.more: //统计表
                intent = new Intent(getActivity(), StatisticalManagerTab.class);
//                intent.putExtra("uid", SPUtils.getInstance().getString("uid"));
//                intent = new Intent(getActivity(), Statistical_Table.class);
                startActivity(intent);
                break;
            case R.id.tv_ApplyWorkOn: //上午的补卡申请
                intent = new Intent(getActivity(), ApplyReissuePunchinActivity.class);
                intent.putExtra("time", addTime);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.tv_ApplyWorkOff://下午的补卡申请
                intent = new Intent(getActivity(), ApplyReissuePunchinActivity.class);
                intent.putExtra("time", addTime);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.img_click_lase:
                calender.toLastPager();
                break;
            case R.id.img_click_next:
                calender.toNextPager();
                break;
        }
    }

    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time * 1000));
    }

}
