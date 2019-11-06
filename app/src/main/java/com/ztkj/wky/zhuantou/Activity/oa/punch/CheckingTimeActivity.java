package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckingTimeActivity extends AppCompatActivity {

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
    @BindView(R.id.click_rl_startTime)
    RelativeLayout clickRlStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.click_rl_endTime)
    RelativeLayout clickRlEndTime;
    private String TAG = "CheckingTimeActivity";
    private String startTime, endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_time);
        ButterKnife.bind(this);
        layoutTitleTv.setText("考勤时间设置");
        more.setVisibility(View.VISIBLE);
        more.setText("确定");

    }

    @OnClick({R.id.layout_back, R.id.more, R.id.click_rl_startTime, R.id.click_rl_endTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                if (TextUtils.equals(tvStartTime.getText().toString(), "请选择")) {
                    Toast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(tvEndTime.getText().toString(), "请选择")) {
                    Toast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post().url(Contents.AMENDPUNCHININFO)
                        .addParams("token", SPUtils.getInstance().getString("token"))
                        .addParams("phone", "13460720766")//SPUtils.getInstance().getString("phone")
                        .addParams("cid", SPUtils.getInstance().getString("cid"))
                        .addParams("start_time", startTime)
                        .addParams("end_time", endTime)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.get("errno").equals("200")) {
                                Toast.makeText(CheckingTimeActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.click_rl_startTime:
                TimePickerView pvTime = new TimePickerBuilder(CheckingTimeActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        //获取汉字格式的时间戳
                        startTime = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        tvStartTime.setText(startTime);

                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setType(new boolean[]{false, false, false, true, true, false})
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime.show();
                break;
            case R.id.click_rl_endTime:
                TimePickerView pvTime2 = new TimePickerBuilder(CheckingTimeActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        //获取汉字格式的时间戳
                        endTime = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        tvEndTime.setText(endTime);
                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setType(new boolean[]{false, false, false, true, true, false})
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime2.show();

                break;
        }
    }
}
