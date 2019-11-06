package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticalDetailsActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
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
    private String uid;
    private String date;
    private String IntentStartTime;
    private String IntentEndTime;
    private String late;
    private String leave_early;
    private String start_field_address;
    private String end_field_address;
    private String workOn, workOff;
    private String TAG = "StatisticalDetailsActivity";
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_details);
        ButterKnife.bind(this);
        intent = getIntent();
        date = intent.getStringExtra("date");
        uid = intent.getStringExtra("uid");
        IntentStartTime = intent.getStringExtra("startTime");
        IntentEndTime = intent.getStringExtra("endTime");
        late = intent.getStringExtra("late");
        leave_early = intent.getStringExtra("leave_early");
        start_field_address = intent.getStringExtra("start_field_address");
        end_field_address = intent.getStringExtra("end_field_address");

        String startTime = this.format.format(Integer.parseInt(IntentStartTime) * 1000);
        String endTime = this.format.format(Integer.parseInt(IntentEndTime) * 1000);
        tvCompanyWorkOn.setText("上班时间 " + Contents.WORKON); //公司上班时间
        tvCompanyWorkOff.setText("下班时间 " + Contents.WORKOFF); //公司下班时间
        tvTodayHours.setText(date); //设置显示日期
        layoutTitleTv.setText(SPUtils.getInstance().getString("realname") + "的考勤详情");

        workOn = date2TimeStamp(date + " " + Contents.WORKON, "yyyy-MM-dd HH:mm");
        workOff = date2TimeStamp(date + " " + Contents.WORKOFF, "yyyy-MM-dd HH:mm");

        if (!IntentStartTime.equals("0") && !IntentEndTime.equals("0")) {
            if (!late.equals("0") && !start_field_address.equals("0")) {
                tvLateWorkOn.setText("外勤 迟到");
            } else if (!late.equals("0")) {
                tvLateWorkOn.setText("迟到");
                tvWorkOn.setText("上班打卡" + startTime);
            } else if (!start_field_address.equals("0")) {
                tvLateWorkOn.setText("外勤");
                tvWorkOn.setText("上班打卡" + startTime);
            } else { //上午正常 隐藏补卡和状态
                tvWorkOn.setText("上班打卡" + startTime);
                tvApplyWorkOn.setVisibility(View.GONE);
                tvLateWorkOn.setVisibility(View.GONE);
            }

            if (!leave_early.equals("0") && !end_field_address.equals("0")) {
                tvLateWorkOff.setText("外勤 早退");
            } else if (!leave_early.equals("0")) {
                tvWorkOff.setText("下班时间" + endTime);
                tvLateWorkOff.setText("早退");
            } else if (!start_field_address.equals("0")) {
                tvWorkOff.setText("下班时间" + endTime);
                tvLateWorkOff.setText("外勤");
            } else { //下午正常 隐藏补卡和状态
                tvWorkOff.setText("下班时间" + endTime);
                tvApplyWorkOff.setVisibility(View.GONE);
                tvLateWorkOff.setVisibility(View.GONE);
            }

        } else if (IntentStartTime.equals("0") && !IntentEndTime.equals("0")) {
            tvLateWorkOn.setText("缺卡");
            tvWorkOn.setVisibility(View.GONE);
            if (!leave_early.equals("0") && !end_field_address.equals("0")) {
                tvLateWorkOff.setText("外勤 早退");
            } else if (!late.equals("0")) {
                tvWorkOff.setText("下班时间" + endTime);
                tvLateWorkOff.setText("早退");
            } else if (!start_field_address.equals("0")) {
                tvWorkOff.setText("下班时间" + endTime);
                tvLateWorkOff.setText("外勤");
            } else { //下午正常 隐藏补卡和状态
                tvWorkOff.setText("下班时间" + endTime);
                tvApplyWorkOff.setVisibility(View.GONE);
                tvLateWorkOff.setVisibility(View.GONE);
            }
        } else if (!IntentStartTime.equals("0")) {
            tvLateWorkOff.setText("缺卡");
            tvWorkOff.setVisibility(View.GONE);
            if (!late.equals("0") && !start_field_address.equals("0")) {
                tvLateWorkOn.setText("外勤 迟到");
            } else if (!late.equals("0")) {
                tvLateWorkOn.setText("迟到");
                tvWorkOn.setText("上班打卡" + startTime);
            } else if (!start_field_address.equals("0")) {
                tvLateWorkOn.setText("外勤");
                tvWorkOn.setText("上班打卡" + startTime);
            } else { //上午正常 隐藏补卡和状态
                tvWorkOn.setText("上班打卡" + startTime);
                tvApplyWorkOn.setVisibility(View.GONE);
                tvLateWorkOn.setVisibility(View.GONE);
            }
        } else {
            tvLateWorkOff.setText("缺卡");
            tvWorkOff.setVisibility(View.GONE);
            tvLateWorkOn.setText("缺卡");
            tvWorkOn.setVisibility(View.GONE);
        }


    }

    @OnClick({R.id.layout_back, R.id.tv_ApplyWorkOn, R.id.tv_ApplyWorkOff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_ApplyWorkOn://上午恢复为正常
                if (!start_field_address.equals("0")) { //外勤
                    RestoreAttendance2("2", workOn);
                } else {
                    RestoreAttendance("1", workOn);
                }

                break;
            case R.id.tv_ApplyWorkOff://下午恢复为正常
                if (!end_field_address.equals("0")) { //外勤
                    RestoreAttendance2("2", workOff);
                } else {
                    RestoreAttendance("2", workOff);
                }
                break;
        }
    }

    private void RestoreAttendance(String type, String time) {
        OkHttpUtils.post().url(Contents.RESTOREATTENDANCENORMAL)
                .addParams("type", type)
                .addParams("uid", uid)
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("time", time)
                .addParams("address", Contents.ADDRESS)
                .addParams("addtime", date).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    Toast.makeText(StatisticalDetailsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                    if (type.equals("1")) {
                        tvWorkOn.setText(Contents.WORKON);
                        tvWorkOn.setVisibility(View.VISIBLE);
                        tvLateWorkOn.setVisibility(View.GONE);
                        tvApplyWorkOn.setVisibility(View.GONE);
                    } else {
                        tvWorkOff.setText(Contents.WORKOFF);
                        tvWorkOff.setVisibility(View.VISIBLE);
                        tvLateWorkOff.setVisibility(View.GONE);
                        tvApplyWorkOff.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void RestoreAttendance2(String type, String time) {
        OkHttpUtils.post().url(Contents.RESTOREATTENDANCEOUTWORK)
                .addParams("type", type)
                .addParams("uid", uid)
                .addParams("time", time)
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("addtime", date).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    Toast.makeText(StatisticalDetailsActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    tvWorkOn.setVisibility(View.GONE);
                    tvLateWorkOn.setVisibility(View.GONE);
                }
            }
        });
    }

    //日期格式字符串转换时间戳
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
