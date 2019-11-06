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
import com.ztkj.wky.zhuantou.bean.ToastBean;
import com.ztkj.wky.zhuantou.isMy.GeRenActivity;
import com.ztkj.wky.zhuantou.landing.SecurityCodeView2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayWord2Activity extends AppCompatActivity {

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
    private String TAG = "PayWord2Activity";
    private String intentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_word2);
        ButterKnife.bind(this);
        if (SPUtils.getInstance().getString("IsPayPassWord").equals("0")) {
            layoutTitleTv.setText("设置支付密码");
        } else {
            layoutTitleTv.setText("修改支付密码");
        }
        intent = getIntent();
        payWord = intent.getStringExtra("PayWord");
        intentTag = intent.getStringExtra("intentTag");
    }

    @OnClick({R.id.layout_back, R.id.PayWord_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
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

                if (intentTag.equals("1")) {
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
                            Toast.makeText(PayWord2Activity.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                } else {
                    OkHttpUtils.post().url(Contents.UPDATEIDCAED)
                            .addParams("token", SPUtils.getInstance().getString("token"))
                            .addParams("uid", SPUtils.getInstance().getString("uid"))
                            .addParams("fronts", SPUtils.getInstance().getString("PathPros"))
                            .addParams("backs", SPUtils.getInstance().getString("PathCons"))
                            .addParams("IDnumber", SPUtils.getInstance().getString("idCardNum"))
                            .addParams("username", SPUtils.getInstance().getString("idCardName"))
                            .addParams("pp", payWord)
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                            if (toastBean.getErrno().equals("200")) {
                                Toast.makeText(PayWord2Activity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                intent = new Intent(PayWord2Activity.this, GeRenActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }


                break;
        }
    }
}
