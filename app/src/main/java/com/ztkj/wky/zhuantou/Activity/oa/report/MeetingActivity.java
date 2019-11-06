package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MeetingAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.MeetingListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.ReMeeting)
    RecyclerView ReMeeting;
    @BindView(R.id.btnNewAdd)
    Button btnNewAdd;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String token;
    private String TAG = "MeetingActivity";
    private Intent intent;
    private List<MeetingListBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);

        layoutTitleTv.setText("会议记录");
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        ReMeeting.setHasFixedSize(true);
        ReMeeting.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //===========查看会议==========
        requestMeeting();
    }

    private void requestMeeting() {
        OkHttpUtils.post().url(Contents.LISTMEETING)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("page", "1")
                .addParams("count", "10")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                MeetingListBean meetingListBean = new Gson().fromJson(response, MeetingListBean.class);
                data = meetingListBean.getData();
                MeetingAdapter meetingAdapter = new MeetingAdapter(MeetingActivity.this, data);
                ReMeeting.setLayoutManager(new LinearLayoutManager(MeetingActivity.this));
                ReMeeting.setAdapter(meetingAdapter);
            }
        });
    }

    @OnClick({R.id.layout_back, R.id.btnNewAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btnNewAdd:
                Intent intent = new Intent(MeetingActivity.this, AddMeeting.class);
                intent.putExtra("tag", "0");
                startActivity(intent);
                break;
        }
    }
}
