package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ShowAdpoverAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.MidDetailsBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class ReadReportMidDetails extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.details_report_name)
    TextView detailsReportName;
    @BindView(R.id.details_report_time)
    TextView detailsReportTime;
    @BindView(R.id.details_report_img_head)
    ImageView detailsReportImgHead;

    @BindView(R.id.details_report_work_content)
    TextView detailsReportWorkContent;
    @BindView(R.id.details_lin_job_contents)
    LinearLayout detailsLinJobContents;

    @BindView(R.id.details_report_performance)
    TextView detailsReportPerformance;
    @BindView(R.id.details_lin_performance)
    LinearLayout detailsLinPerformance;

    @BindView(R.id.details_report_plan)
    TextView detailsReportPlan;
    @BindView(R.id.details_lin_plan)
    LinearLayout detailsLinPlan;

    @BindView(R.id.details_report_read)
    TextView detailsReportRead;
    @BindView(R.id.details_report_unread)
    TextView detailsReportUnread;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.details_report_performance_title)
    TextView detailsReportPerformanceTitle;
    @BindView(R.id.re_details_approver)
    RecyclerView reDetailsApprover;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid;
    private String token;
    private String cid;
    private String report_type, report_name, report_head, report_id;

    private ArrayList<String> Arr_approver = new ArrayList<>();//审批人


    private Intent intent;
    private String TAG = "ReadReportDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_report_details);
        ButterKnife.bind(this);

        //解决滑动冲突
        reDetailsApprover.setHasFixedSize(true);
        reDetailsApprover.setNestedScrollingEnabled(false);
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(ReadReportMidDetails.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(ReadReportMidDetails.this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        intent = getIntent();
        report_id = intent.getStringExtra("report_id");
        report_type = intent.getStringExtra("report_type");
        report_name = intent.getStringExtra("report_name");
        report_head = intent.getStringExtra("report_head");
        request();
    }

    private void request() {
        Log.e(TAG, "request: type============" + report_type);
        OkHttpUtils.post()
                .url(Contents.REPORTDETAILS)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("type", report_type)
                .addParams("id", report_id)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                MidDetailsBean midDetailsBean = new Gson().fromJson(response, MidDetailsBean.class);
//                ReadReportDetailsBean readReportDetailsBean = new Gson().fromJson(response, ReadReportDetailsBean.class);
                Log.e(TAG, "onResponse: " + response);
                if (midDetailsBean.getErrno().equals("200")) {
//                    ReadReportDetailsBean.DataBean data = readReportDetailsBean.getData();
                    MidDetailsBean.DataBean data = midDetailsBean.getData();
                    detailsReportName.setText(report_name);//日报人名称
                    detailsReportTime.setText(data.getAddtime());//日报人时间
                    //设置图片圆角角度
                    RoundedCorners roundedCorners = new RoundedCorners(96);
                    RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                    Glide.with(ReadReportMidDetails.this).load(report_head)
                            .apply(options)
                            .into(detailsReportImgHead);
                    if (!data.getMonthly_work().equals("")) {
                        detailsLinJobContents.setVisibility(View.VISIBLE);
                        detailsReportWorkContent.setText(data.getMonthly_work());
                    }
                    if (!data.getMonthly_summary().equals("")) {
                        detailsLinPerformance.setVisibility(View.VISIBLE);
                        detailsReportPerformance.setText(data.getMonthly_summary());
                    }
                    if (!data.getMonthly_plan().equals("")) {
                        detailsLinPlan.setVisibility(View.VISIBLE);
                        detailsReportPlan.setText(data.getMonthly_plan());
                    }
                    List<MidDetailsBean.DataBean.ReaderBean> reader = data.getReader();
                    for (int i = 0; i < reader.size(); i++) {
                        Arr_approver.add(reader.get(i).getHead());
                    }
                    //适配器
                    ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(ReadReportMidDetails.this, Arr_approver);

//        ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WorkSummary.this, Arr_approver);
                    showAdpoverAdapter.notifyDataSetChanged();
                    //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
//                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ReadReportMidDetails.this, 5);
                    //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
//                    gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                    reDetailsApprover.setLayoutManager(new LinearLayoutManager(ReadReportMidDetails.this));
                    reDetailsApprover.setAdapter(showAdpoverAdapter);


                } else if (midDetailsBean.getErrno().equals("666666")) {
                    Toast.makeText(ReadReportMidDetails.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(ReadReportMidDetails.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    SPUtils.getInstance().clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(ReadReportMidDetails.this, NewLoginActivity.class);
                    startActivity(intent);
                } else {

                }


            }
        });
    }

    @OnClick({R.id.back, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.more:
                break;
        }
    }
}
