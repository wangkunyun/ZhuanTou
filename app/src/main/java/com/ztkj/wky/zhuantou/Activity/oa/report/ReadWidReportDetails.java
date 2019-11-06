package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.ztkj.wky.zhuantou.bean.WidDetailsBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class ReadWidReportDetails extends AppCompatActivity {

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
        sharedPreferencesHelper = new SharedPreferencesHelper(ReadWidReportDetails.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(ReadWidReportDetails.this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        intent = getIntent();
        report_id = intent.getStringExtra("report_id");
        report_type = intent.getStringExtra("report_type");
        report_name = intent.getStringExtra("report_name");
        report_head = intent.getStringExtra("report_head");
        Log.e(TAG, "onCreate: " + "cid=" + cid + "   token=" + token + "   uid=" + uid);
        Log.e(TAG, "onCreate: " + "id=" + report_id + "     type=" + report_type + "    name=" + report_name + "     time=" + report_head);
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
                WidDetailsBean widDetailsBean = new Gson().fromJson(response, WidDetailsBean.class);
//                ReadReportDetailsBean readReportDetailsBean = new Gson().fromJson(response, ReadReportDetailsBean.class);
                Log.e(TAG, "onResponse: " + response);
                if (widDetailsBean.getErrno().equals("200")) {
//                    ReadReportDetailsBean.DataBean data = readReportDetailsBean.getData();
                    WidDetailsBean.DataBean data = widDetailsBean.getData();
                    detailsReportName.setText(report_name);//日报人名称
                    detailsReportTime.setText(data.getAddtime());//日报人时间
                    //设置图片圆角角度
                    RoundedCorners roundedCorners = new RoundedCorners(96);
                    RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                    Glide.with(ReadWidReportDetails.this).load(report_head)
                            .apply(options)
                            .into(detailsReportImgHead);
                    if (!data.getWeekly_completion().equals("")) {
                        detailsLinJobContents.setVisibility(View.VISIBLE);
                        detailsReportWorkContent.setText(data.getWeekly_completion());
                    }
                    if (!data.getZhou_summary().equals("")) {
                        detailsLinPerformance.setVisibility(View.VISIBLE);
                        detailsReportPerformance.setText(data.getZhou_summary());
                    }
                    if (!data.getWeekly_plan().equals("")) {
                        detailsLinPlan.setVisibility(View.VISIBLE);
                        detailsReportPlan.setText(data.getWeekly_plan());
                    }

                    List<WidDetailsBean.DataBean.ReaderBean> reader = data.getReader();
                    for (int i = 0; i < reader.size(); i++) {
                        Arr_approver.add(reader.get(i).getHead());
                    }

                    //适配器
                    ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(ReadWidReportDetails.this, Arr_approver);

//        ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WorkSummary.this, Arr_approver);
                    showAdpoverAdapter.notifyDataSetChanged();
                    //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ReadWidReportDetails.this, 5);
                    //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
                    gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                    reDetailsApprover.setLayoutManager(gridLayoutManager);
                    reDetailsApprover.setAdapter(showAdpoverAdapter);



                } else if (widDetailsBean.getErrno().equals("666666")) {
                    Toast.makeText(ReadWidReportDetails.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(ReadWidReportDetails.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(ReadWidReportDetails.this, LoginActivity.class);
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
