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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.listener.OnCalendarChangedListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ThreeAndOneBean;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;
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
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
                Toast.makeText(getActivity(), localDate.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPunchInInfo();

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
                    tvWorkOff.setText("上班打卡 " + stroff);
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

    @OnClick({R.id.layout_back, R.id.more, R.id.tv_ApplyWorkOn, R.id.tv_ApplyWorkOff})
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
            case R.id.tv_ApplyWorkOn:
                break;
            case R.id.tv_ApplyWorkOff:
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
