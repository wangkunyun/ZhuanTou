package com.ztkj.wky.zhuantou.Activity.mine;

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
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPayWord3Activity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.et_bankCardNum)
    EditText etBankCardNum;
    @BindView(R.id.et_bankPhone)
    EditText etBankPhone;
    @BindView(R.id.zc_btn)
    Button zcBtn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tltleNum)
    TextView tvTltleNum;
    private Intent intent;
    private String bankCardName, bankCardNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_word3);
        ButterKnife.bind(this);
        layoutTitleTv.setText("修改支付密码");
        intent = getIntent();
        bankCardName = intent.getStringExtra("bankCardName");
        bankCardNum = intent.getStringExtra("bankCardNum");
        tvTitle.setText(bankCardName);
        String substring = bankCardNum.substring(bankCardNum.length() - 4);
        tvTltleNum.setText(substring);
    }

    @OnClick({R.id.layout_back, R.id.zc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.zc_btn:
                request();
                break;
        }
    }

    private void request() {
        if (!bankCardNum.equals(etBankCardNum.getText().toString())){
            Toast.makeText(this, "您输入的卡号有误", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post().url(Contents.BANKCODE)
                .addParams("mobile", etBankPhone.getText().toString())
                .addParams("token", SPUtils.getInstance().getString("token"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    Toast.makeText(ForgetPayWord3Activity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    intent = new Intent(ForgetPayWord3Activity.this, ForgetPayWord4Activity.class);
                    intent.putExtra("bankCardNum", etBankCardNum.getText().toString());
                    intent.putExtra("bankCardPhone", etBankPhone.getText().toString());
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
