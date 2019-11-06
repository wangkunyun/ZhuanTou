package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ForgetPayWordBean;
import com.ztkj.wky.zhuantou.bean.ShiMingBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPayWord4Activity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.et_VerificCode)
    EditText etVerificCode;
    @BindView(R.id.btnVerific)
    Button btnVerific;
    private Intent intent;
    private String bankCardPhone, bankCardNum;
    private String TAG = "ForgetPayWord4Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_verific_code);
        ButterKnife.bind(this);
        intent = getIntent();
        bankCardPhone = intent.getStringExtra("bankCardPhone");
        bankCardNum = intent.getStringExtra("bankCardNum");

    }

    @OnClick({R.id.layout_back, R.id.btnVerific})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btnVerific:
                request();
                break;
        }
    }

    private void request() {
        //获取实名认证的信息
        OkHttpUtils.post().url(Contents.SHIMINGINFO)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ShiMingBean shiMingBean = GsonUtil.gsonToBean(response, ShiMingBean.class);
                if (shiMingBean.getErrno().equals("200")) {
                    OkHttpUtils.post().url(Contents.VERIFYBINDINGCODE)
                            .addParams("token", SPUtils.getInstance().getString("token"))
                            .addParams("uid", SPUtils.getInstance().getString("uid"))
                            .addParams("acct_name", shiMingBean.getData().getUsername())
                            .addParams("acct_pan", bankCardNum)
                            .addParams("cert_id", shiMingBean.getData().getIDnumber())
                            .addParams("phone_num", bankCardPhone)
                            .addParams("ver_code", etVerificCode.getText().toString())
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            ForgetPayWordBean forgetPayWordBean = GsonUtil.gsonToBean(response, ForgetPayWordBean.class);
                            if (forgetPayWordBean.getErrno().equals("200")) {
                                intent = new Intent(ForgetPayWord4Activity.this, ForgetSetPayWord2.class);
                            }
                        }
                    });
                }
            }
        });


    }

}
