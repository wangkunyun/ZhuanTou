package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.ztkj.wky.zhuantou.bean.ToastBean;
import com.ztkj.wky.zhuantou.landing.SecurityCodeView2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetSetPayWord2 extends AppCompatActivity {

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
    private Intent intent;
    private String payWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_word2);
        ButterKnife.bind(this);
        layoutTitleTv.setText("修改支付密码");
        intent = getIntent();
        payWord = intent.getStringExtra("PayWord");
    }

    @OnClick({R.id.layout_back, R.id.PayWord_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                layoutTitleTv.setText("修改支付密码");
                break;
            case R.id.PayWord_next:
                if (editPayWordSecurityCode.getEditContent().equals("")) {
                    Toast.makeText(this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editPayWordSecurityCode.getEditContent().equals(payWord)) {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                OkHttpUtils.post().url(Contents.RESETPAYWORD)
                        .addParams("token", SPUtils.getInstance().getString("token"))
                        .addParams("uid", SPUtils.getInstance().getString("uid"))
                        .addParams("pp", payWord)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        Toast.makeText(ForgetSetPayWord2.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
        }
    }
}
