package com.ztkj.wky.zhuantou.Activity.mine;
/**
 * 作者：wky
 * 功能描述：
 * 绑定银行卡
 * 填写预留手机号 获取验证码
 */

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
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingIdCard2 extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_BankType)
    TextView tvBankType;
    @BindView(R.id.tv_BankCardType)
    TextView tvBankCardType;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.et_BankIdCardPhone)
    EditText etBankIdCardPhone;
    private Intent intent;
    private String TAG = "BindingIdCard2";
    private String cardNum,intentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_id_card2);
        ButterKnife.bind(this);
        intent = getIntent();
        cardNum = intent.getStringExtra("CardNum");
         intentTag = intent.getStringExtra("IntentTag");
        String cardInfo = intent.getStringExtra("CardInfo");
        String[] split = cardInfo.split("-");
        tvBankType.setText(split[0]);
        tvBankCardType.setText(split[1]);

    }

    @OnClick({R.id.layout_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_next:
                if (etBankIdCardPhone.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入银行预留手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post().url(Contents.BANKCODE)
                        .addParams("token", SPUtils.getInstance().getString("token"))
                        .addParams("mobile", etBankIdCardPhone.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {
                            Toast.makeText(BindingIdCard2.this, "发送成功", Toast.LENGTH_SHORT).show();
                            intent = new Intent(BindingIdCard2.this, BankCardVerificCode.class);
                            Log.e(TAG, "onResponse: cardNum" + cardNum);
                            intent.putExtra("CardNum", cardNum);
                            intent.putExtra("BankPhone", etBankIdCardPhone.getText().toString());
                            intent.putExtra("intentTag",intentTag);
                            startActivity(intent);
                            finish();

                        }
                    }
                });

                break;
        }
    }

}
