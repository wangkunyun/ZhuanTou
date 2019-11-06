package com.ztkj.wky.zhuantou.Activity.mine;
/**
 * 作者：wky
 * 功能描述：
 * 绑定银行卡
 * 输入银行卡号获取信息
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

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BankCardBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingIdCard extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.idCardName)
    TextView idCardName;
    @BindView(R.id.et_IdCard)
    EditText etIdCard;
    @BindView(R.id.btnVerific)
    Button btnVerific;
    private String TAG = "BindingIdCard";
    private Intent intent;
    private String intentTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_id_card);
        ButterKnife.bind(this);
        layoutTitleTv.setText("绑定银行卡");
        idCardName.setText(SPUtils.getInstance().getString("idCardName"));
        intent = getIntent();
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
        OkHttpUtils.post().url(Contents.BANKCARINFO)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("card", etIdCard.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                BankCardBean bankCardBean = GsonUtil.gsonToBean(response, BankCardBean.class);
                if (bankCardBean.getErrno().equals("200")) {
                    intent = new Intent(BindingIdCard.this, BindingIdCard2.class);
                    intent.putExtra("CardInfo", bankCardBean.getData().getCard());
                    intent.putExtra("CardNum", etIdCard.getText().toString());
                    intent.putExtra("IntentTag",intentTag);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
