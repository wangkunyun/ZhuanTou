package com.ztkj.wky.zhuantou.Activity.mine;

/**
 * 作者：wky
 * 功能描述：
 * 银行卡验证码
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.ztkj.wky.zhuantou.bean.BindingBankCard;
import com.ztkj.wky.zhuantou.bean.ShiMingBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCardVerificCode extends AppCompatActivity {

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
    private String cardNum, bankPhone, intentTag;
    private String TAG = "BankCardVerificCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_verific_code);
        ButterKnife.bind(this);
        layoutTitleTv.setText("填写验证码");
        intent = getIntent();
        cardNum = intent.getStringExtra("CardNum");
        bankPhone = intent.getStringExtra("BankPhone");
        intentTag = intent.getStringExtra("intentTag");
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
        if (etVerificCode.getText().toString().equals("")) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
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
                    String username = shiMingBean.getData().getUsername();
                    String iDnumber = shiMingBean.getData().getIDnumber();

                    OkHttpUtils.post().url(Contents.BANDINGBANKCARD)
                            .addParams("token", SPUtils.getInstance().getString("token"))
                            .addParams("uid", SPUtils.getInstance().getString("uid"))
                            .addParams("acct_name", username)
                            .addParams("acct_pan", cardNum)
                            .addParams("cert_id", iDnumber)
                            .addParams("phone_num", bankPhone)
                            .addParams("ver_code", etVerificCode.getText().toString())
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            BindingBankCard bindingBankCard = GsonUtil.gsonToBean(response, BindingBankCard.class);
                            if (bindingBankCard.getErrno().equals("200")) {

                                if (intentTag.equals("1")) {
                                    intent = new Intent(BankCardVerificCode.this, PayWord3Activity.class);
                                    intent.putExtra("intentTag", intentTag);
                                    startActivity(intent);


                                }
                                Toast.makeText(BankCardVerificCode.this, "绑定成功", Toast.LENGTH_SHORT).show();
                                finish();

                            } else if (bindingBankCard.getErrno().equals("201")) {
                                Toast.makeText(BankCardVerificCode.this, "该银行卡已被绑定", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    });
                }
            }
        });
    }
}
