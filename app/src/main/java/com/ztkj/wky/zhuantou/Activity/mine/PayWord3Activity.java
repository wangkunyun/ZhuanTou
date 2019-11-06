package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.PayWordBean;
import com.ztkj.wky.zhuantou.landing.SecurityCodeView2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayWord3Activity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.edit_payWord_security_code)
    SecurityCodeView2 editPayWordSecurityCode;
    @BindView(R.id.PayWord_next)
    Button PayWordNext;
    private String TAG = "PayWord3Activity";
    private Intent intent;
    private String intentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_word3);
        ButterKnife.bind(this);
        layoutTitleTv.setText("修改支付密码");
        intent = getIntent();
         intentTag = intent.getStringExtra("intentTag");
    }

    @OnClick({R.id.layout_back, R.id.PayWord_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.PayWord_next:
                OkHttpUtils.post().url(Contents.OLDPAYPASSWORD)
                        .addParams("token", SPUtils.getInstance().getString("token"))
                        .addParams("uid", SPUtils.getInstance().getString("uid"))
                        .addParams("pp", editPayWordSecurityCode.getEditContent())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        PayWordBean payWordBean = GsonUtil.gsonToBean(response, PayWordBean.class);
                        String state = payWordBean.getData().getState();
                        if (state.equals("1")) {
                            intent = new Intent(PayWord3Activity.this, PayWordActivity.class);
                            intent.putExtra("intentTag",intentTag);
                            startActivity(intent);
                        }else {
                            Toast.makeText(PayWord3Activity.this, "您输入的密码不正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
