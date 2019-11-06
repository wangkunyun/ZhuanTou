package com.ztkj.wky.zhuantou.Activity.oa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ScheduleListAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ScheduleListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScheduleList extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.re)
    RecyclerView re;
    private Intent intent, intent2;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String TAG = "ScheduleList";
    private List<ScheduleListBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);

        layoutTitleTv.setText("本日日程");
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        intent = getIntent();
        String schedule_date = intent.getStringExtra("Schedule_date");

        OkHttpUtils.post().url(Contents.SELECTSCHEDULE)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("start_days", schedule_date)
                .addParams("end_days", schedule_date)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ScheduleListBean scheduleListBean = new Gson().fromJson(response, ScheduleListBean.class);
                data = scheduleListBean.getData();
                if (scheduleListBean.getErrno().equals("200")) {
                    ScheduleListAdapter scheduleListAdapter = new ScheduleListAdapter(data, ScheduleList.this);
                    re.setLayoutManager(new LinearLayoutManager(ScheduleList.this));
                    re.setAdapter(scheduleListAdapter);
                }
            }
        });

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
