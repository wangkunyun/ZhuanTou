package com.ztkj.wky.zhuantou.Activity.oa;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.CalendarReminderUtils;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSchedule extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.enterStartTime)
    ImageView enterStartTime;
    @BindView(R.id.clearStartTime)
    ImageView clearStartTime;
    @BindView(R.id.click_leave_rl_startTime)
    RelativeLayout clickLeaveRlStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.enterEndTime)
    ImageView enterEndTime;
    @BindView(R.id.clearEndTime)
    ImageView clearEndTime;
    @BindView(R.id.click_leave_rl_endTime)
    RelativeLayout clickLeaveRlEndTime;
    @BindView(R.id.etGroupAnn)
    EditText etGroupAnn;

    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String updatestartTime, updateendTime, formatDays, startTime, endTime, save_startTime, save_endTime, content;
    private long l_Strtetime, l_Endtime;
    private Intent intent;
    private String TAG = "AddSchedule";
    private String tag, sc_id;
    private static String CALENDER_URL = "content://com.android.calendar/calendars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        intent = getIntent();
        tag = intent.getStringExtra("tag");
        if (tag.equals("0")) {
            layoutTitleTv.setText("添加日程");
            more.setVisibility(View.VISIBLE);
            more.setText("保存");
        } else if (tag.equals("1")) {
            layoutTitleTv.setText("查看日程");
            more.setVisibility(View.VISIBLE);
            more.setText("修改");
            save_startTime = intent.getStringExtra("startTime");
            save_endTime = intent.getStringExtra("endTime");
            content = intent.getStringExtra("content");
            sc_id = intent.getStringExtra("sc_id");
            tvStartTime.setText(save_startTime);
            tvEndTime.setText(save_endTime);
            etGroupAnn.setText(content);
        }

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
    }

    @OnClick({R.id.layout_back, R.id.more, R.id.click_leave_rl_startTime, R.id.click_leave_rl_endTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                if (tag.equals("0")) {
                    addScheduleRequest();
                } else if (tag.equals("1")) {
                    saveScheduleRequest();
                } else {

                }


                break;
            case R.id.click_leave_rl_startTime://开始时间
                TimePickerView pvTime = new TimePickerBuilder(AddSchedule.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                        //获取汉字格式的时间戳
                        startTime = simpleDateFormat.format(date);
                        formatDays = simpleDateFormat2.format(date);
                        l_Strtetime = date.getTime() / 1000;
                        //获取英文格式的时间戳并上传给后台
                        updatestartTime = String.valueOf(date.getTime() / 1000);
                        tvStartTime.setText(startTime);

                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setType(new boolean[]{false, true, true, true, true, false})
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime.show();
                break;
            case R.id.click_leave_rl_endTime://结束时间
                TimePickerView pvTime2 = new TimePickerBuilder(AddSchedule.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                        //获取汉字格式的时间戳
                        endTime = simpleDateFormat.format(date);
                        l_Endtime = date.getTime() / 1000;
                        //获取英文格式的时间戳并上传给后台
                        updateendTime = String.valueOf(date.getTime() / 1000);
                        tvEndTime.setText(endTime);

                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setType(new boolean[]{false, true, true, true, true, false})
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime2.show();
                break;
        }
    }


    private void saveScheduleRequest() {
        if (tvStartTime.equals("")) {
            Toast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tvEndTime.equals("")) {
            Toast.makeText(this, "请选择结束时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etGroupAnn.getText().toString().equals("")) {
            Toast.makeText(this, "请输入日程信息", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post().url(Contents.SAVESCHEDULE)
                .addParams("token", token)
                .addParams("sc_id", sc_id)
                .addParams("days", save_startTime)
                .addParams("start_time", save_startTime)
                .addParams("end_time", save_endTime)
                .addParams("remarks", content)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    //此处为修改日程，因没有时间戳所以本地的暂时先不修改
//                    CalendarReminderUtils.addCalendarEvent(AddSchedule.this, "小砖提醒", etGroupAnn.getText().toString(), l_Strtetime, l_Endtime, 1);
                    Toast.makeText(AddSchedule.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddSchedule.this, toastBean.getErrmsg() + "，请更新日程内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addScheduleRequest() {
        int i = CalendarReminderUtils.checkAndAddCalendarAccount(AddSchedule.this);
        if (i == -1) {
            Toast.makeText(this, "没有日历ID", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Log.e(TAG, "addScheduleRequest: =====日历ID====" + i);
        }
        if (tvStartTime.equals("请选择")) {
            Toast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tvEndTime.equals("请选择")) {
            Toast.makeText(this, "请选择结束时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etGroupAnn.getText().toString().equals("")) {
            Toast.makeText(this, "请输入日程信息", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.post().url(Contents.ADDSCHEDULE)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("days", formatDays)
                .addParams("start_time", startTime)
                .addParams("end_time", endTime)
                .addParams("remarks", etGroupAnn.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
//                    InstallUtils.checkInstallPermission
                    PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
                        @Override
                        public void permissionGranted(@NonNull String[] permission) {

                            CalendarReminderUtils.addCalendarEvent(AddSchedule.this, "小砖提醒", etGroupAnn.getText().toString(), l_Strtetime, l_Endtime, 1);
                            Toast.makeText(AddSchedule.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void permissionDenied(@NonNull String[] permission) {
                            Toast.makeText(AddSchedule.this, "用户拒接了权限申请", Toast.LENGTH_SHORT).show();
                        }
                    }, Manifest.permission.WRITE_CALENDAR);


                } else {
                    Toast.makeText(AddSchedule.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
