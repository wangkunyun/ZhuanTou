package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.oa.report.ChooseAdressActivity;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyReissuePunchinActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.tv_ReissueHour)
    TextView tvReissueHour;
    @BindView(R.id.tv_ReissueDate)
    TextView tvReissueDate;
    @BindView(R.id.click_rl_Reissue)
    RelativeLayout clickRlReissue;
    @BindView(R.id.et_ReissueReason)
    EditText etReissueReason;
    @BindView(R.id.click_rl_addApppover)
    RelativeLayout clickRlAddApppover;
    @BindView(R.id.Reissue_img_showPicture)
    ImageView ReissueImgShowPicture;
    @BindView(R.id.Reissue_del_picture)
    ImageView ReissueDelPicture;
    @BindView(R.id.rl_appvoer)
    RelativeLayout rlAppvoer;
    private Intent intent;
    private SharedPreferencesHelper listApprover_head_uid;
    private String approver_uid, approver_head;
    private String TAG = "ApplyReissuePunchinActivity";
    private String formatDate, formatHour, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_reissue_punchin);
        ButterKnife.bind(this);
        layoutTitleTv.setText("申请补卡");
        //获取从上一页面传过来的日期
        intent = getIntent();
        formatDate = intent.getStringExtra("time");
        type = intent.getStringExtra("type");
        tvReissueDate.setText(formatDate);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //从联系人跳过来的sp里的审批人id和
        listApprover_head_uid = new SharedPreferencesHelper(ApplyReissuePunchinActivity.this, "listApprover");
        approver_head = (String) listApprover_head_uid.getSharedPreference("approver_head", "");
        approver_uid = (String) listApprover_head_uid.getSharedPreference("approver_uid", "");
        Log.e(TAG, "onResume: " + approver_head);
        if (!StringUtils.isEmpty(approver_uid)) {
            rlAppvoer.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.click_rl_Reissue, R.id.click_rl_addApppover, R.id.Reissue_del_picture, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.click_rl_Reissue://选择时间
                TimePickerView pvTime = new TimePickerBuilder(ApplyReissuePunchinActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        //获取汉字格式的时间戳
                        formatHour = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        tvReissueHour.setText(formatHour);

                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setType(new boolean[]{false, false, false, true, true, false})
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime.show();
                break;
            case R.id.click_rl_addApppover://选择审批人
                intent = new Intent(ApplyReissuePunchinActivity.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.Reissue_del_picture://删除当前选中审批人

                break;
            case R.id.btnSubmit://提交补卡申请
                if (TextUtils.isEmpty(etReissueReason.getText().toString())) {
                    Toast.makeText(this, "请输入补卡原因", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(TAG, "onViewClicked: " + etReissueReason.getText().toString());
                if (StringUtils.isEmpty(approver_uid)) {
                    Toast.makeText(this, "请选择审批人", Toast.LENGTH_SHORT).show();
                    return;
                }
                //拼接选择当天的时间戳
                String timestamp = date2TimeStamp(formatDate + " " + formatHour, "yyyy-MM-dd HH:mm");
                OkHttpUtils.post().url(Contents.APPLYREISSUEPUNCH)
                        .addParams("token", SPUtils.getInstance().getString("token"))
                        .addParams("uid", SPUtils.getInstance().getString("uid"))
                        .addParams("cid", SPUtils.getInstance().getString("cid"))
                        .addParams("addtime", formatDate)
                        .addParams("time", timestamp)
                        .addParams("approver", approver_uid)
                        .addParams("explains", etReissueReason.getText().toString())
                        .addParams("type", type)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                    }
                });

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listApprover_head_uid.clear();
    }

    //日期格式字符串转换时间戳
    public static String date2TimeStamp(String date, String format) {
        try {
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
