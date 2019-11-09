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
import com.ztkj.wky.zhuantou.bean.PayForDeialsBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayForDetials extends AppCompatActivity {

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
    @BindView(R.id.detials_money)
    TextView detialsMoney;
    @BindView(R.id.detials_type)
    TextView detialsType;
    @BindView(R.id.detials_date)
    TextView detialsDate;
    @BindView(R.id.detials_bank)
    TextView detialsBank;
    @BindView(R.id.detials_account)
    TextView detialsAccount;
    @BindView(R.id.details_img_appover)
    ImageView detailsImgAppover;
    @BindView(R.id.details_tv_appover)
    TextView detailsTvAppover;
    @BindView(R.id.re_details_approver2)
    RecyclerView reDetailsApprover2;
    @BindView(R.id.detials_object)
    TextView detialsObject;
    @BindView(R.id.detials_detials)
    TextView detialsDetials;
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
    private String TAG = "PayForDetials";
    private String head;
    private ArrayList<String> appovername;
    private ArrayList<String> appoverhead;
    private String discover;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_for_detials);
        ButterKnife.bind(this);
        toolbarTitle.setText("付款申请");
        intent = getIntent();
        id = intent.getStringExtra("id");
        head = intent.getStringExtra("head");
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
                .addParams("type", "paid")
                .addParams("id", id)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                PayForDeialsBean payForDeialsBean = new Gson().fromJson(response, PayForDeialsBean.class);
                PayForDeialsBean.DataBean data = payForDeialsBean.getData();

                detailsReportTime.setText(data.getAddtime());

                if (data.getStatus().equals("1")) {
                    imgStatePass.setVisibility(View.VISIBLE);
                } else if (data.getStatus().equals("2")) {
                    imgStateRefuse.setVisibility(View.VISIBLE);
                } else {

                }

                //获取汉字格式的时间戳
                long l = Long.parseLong(data.getPayment_date() + "000");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
                detialsDate.setText(simpleDateFormat.format(l));
                detialsObject.setText(data.getPayment_object());
                detialsMoney.setText(data.getBank_account());
                detialsType.setText(data.getPayment_type());
                detialsBank.setText(data.getOpening_bank());
                detialsAccount.setText(data.getBank_account());
                detialsDetials.setText(data.getSubject_matter());

                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(96);
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(PayForDetials.this).load(head)
                        .apply(options).into(detailsReportImgHead);
                Glide.with(PayForDetials.this).load(data.getApprover().getHead())
                        .apply(options).into(detailsImgAppover);
                detailsTvAppover.setText(data.getApprover().getName());

                List<PayForDeialsBean.DataBean.CopierBean> copier = data.getCopier();
                appovername = new ArrayList<>();
                appoverhead = new ArrayList<>();
                for (int i = 0; i < copier.size(); i++) {
                    appovername.add(copier.get(i).getName());
                    appoverhead.add(copier.get(i).getHead());
                }
                AppoverAdapter appoverAdapter = new AppoverAdapter(PayForDetials.this, appovername, appoverhead);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(PayForDetials.this, 5);
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
