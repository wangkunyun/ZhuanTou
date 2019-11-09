package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.applydetials;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AppoverAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.EvectionDetialsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvectionDetials extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.details_report_name)
    TextView detailsReportName;
    @BindView(R.id.details_report_time)
    TextView detailsReportTime;
    @BindView(R.id.details_report_img_head)
    ImageView detailsReportImgHead;
    @BindView(R.id.detials_start_time)
    TextView detialsStartTime;
    @BindView(R.id.detials_end_time)
    TextView detialsEndTime;
    @BindView(R.id.detials_objective)
    TextView detialsObjective;
    @BindView(R.id.detials_duration)
    TextView detialsDuration;
    @BindView(R.id.detials_place)
    TextView detialsPlace;
    @BindView(R.id.details_img_appover)
    ImageView detailsImgAppover;
    @BindView(R.id.details_tv_appover)
    TextView detailsTvAppover;
    @BindView(R.id.re_details_approver2)
    RecyclerView reDetailsApprover2;
    @BindView(R.id.img_state_pass)
    ImageView imgStatePass;
    @BindView(R.id.img_state_refuse)
    ImageView imgStateRefuse;
    @BindView(R.id.tv_click_refuse)
    TextView tvClickRefuse;
    @BindView(R.id.tv_click_pass)
    TextView tvClickPass;
    @BindView(R.id.lin_click_appover)
    LinearLayout linClickAppover;

    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token;
    private String TAG = "EvectionDetials";
    private String head;
    private ArrayList<String> appovername;
    private ArrayList<String> appoverhead;
    private String discover;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evection_detials);
        ButterKnife.bind(this);
        toolbarTitle.setText("外勤申请");
        intent = getIntent();
        id = intent.getStringExtra("id");
        head = intent.getStringExtra("head");
        discover = intent.getStringExtra("discover");
        String name = intent.getStringExtra("name");
        detailsReportName.setText(name);
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");


        if (discover.equals("1")) {
            linClickAppover.setVisibility(View.VISIBLE);
        } else {
            linClickAppover.setVisibility(View.GONE);
        }

        request(id);
    }

    private void request(String id) {
        OkHttpUtils.post()
                .url(Contents.ALLDETIALS)
                .addParams("token", token)
                .addParams("type", "fid")
                .addParams("id", id)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: 00" + response);
                EvectionDetialsBean evectionDetialsBean = new Gson().fromJson(response, EvectionDetialsBean.class);
                EvectionDetialsBean.DataBean data = evectionDetialsBean.getData();
                detailsReportTime.setText(data.getAddtime());
                if (data.getStatus().equals("1")) {
                    imgStatePass.setVisibility(View.VISIBLE);
                } else if (data.getStatus().equals("2")) {
                    imgStateRefuse.setVisibility(View.VISIBLE);
                } else {

                }
                //获取汉字格式的时间戳
                long l = Long.parseLong(data.getStart_time() + "000");
                long l2 = Long.parseLong(data.getEnd_time() + "000");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
                detialsStartTime.setText(simpleDateFormat.format(l));
                detialsEndTime.setText(simpleDateFormat.format(l2));
                detialsDuration.setText(data.getDuration()+"小时");
                detialsObjective.setText(data.getFie_note());
                detialsPlace.setText(data.getPlace());
                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(96);
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(EvectionDetials.this).load(head)
                        .apply(options).into(detailsReportImgHead);
                Glide.with(EvectionDetials.this).load(data.getApprover().getHead())
                        .apply(options).into(detailsImgAppover);
                detailsTvAppover.setText(data.getApprover().getName());

                List<EvectionDetialsBean.DataBean.CopierBean> copier = data.getCopier();
                appovername = new ArrayList<>();
                appoverhead = new ArrayList<>();
                for (int i = 0; i < copier.size(); i++) {
                    appovername.add(copier.get(i).getName());
                    appoverhead.add(copier.get(i).getHead());
                }
                AppoverAdapter appoverAdapter = new AppoverAdapter(EvectionDetials.this, appovername, appoverhead);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(EvectionDetials.this, 5);
                //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
                gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                reDetailsApprover2.setLayoutManager(gridLayoutManager);
                reDetailsApprover2.setAdapter(appoverAdapter);

            }
        });

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.tv_click_refuse, R.id.tv_click_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_click_refuse:
                request_state("2");
                break;
            case R.id.tv_click_pass:
                request_state("1");
                break;
        }
    }

    private void request_state(final String status) {
        OkHttpUtils.post()
                .url(Contents.EXAMINE)
                .addParams("token", token)
                .addParams("type", "fid")
                .addParams("id", id)
                .addParams("status", status)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String errno = (String) jsonObject.get("errno");
                    if (errno.equals("200")) {
                        if (status.equals("1")) {
                            imgStatePass.setVisibility(View.VISIBLE);
                        } else if (status.equals("2")) {
                            imgStateRefuse.setVisibility(View.GONE);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}


