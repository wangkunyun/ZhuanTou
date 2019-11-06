package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.BankCar2Adapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BandingBankCardBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPayWord2Activity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.reBankCard)
    RecyclerView reBankCard;
    @BindView(R.id.rl_click_BankCard)
    RelativeLayout rlClickBankCard;
    @BindView(R.id.addNameBankCard)
    TextView addNameBankCard;

    private String TAG = "ForgetPayWord2Activity";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pay_word2);
        ButterKnife.bind(this);
        requestBankCarList();
        layoutTitleTv.setText("修改支付密码");
        String idCardName = SPUtils.getInstance().getString("idCardName");
        String substring = idCardName.substring(idCardName.length() - 1, idCardName.length());
        addNameBankCard.setText("添加**"+substring+"的银行卡");

    }


    private void requestBankCarList() {
        OkHttpUtils.post().url(Contents.BANKCARLIST)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                BandingBankCardBean bandingBankCardBean = GsonUtil.gsonToBean(response, BandingBankCardBean.class);
                List<BandingBankCardBean.DataBean> data = bandingBankCardBean.getData();
                reBankCard.setLayoutManager(new LinearLayoutManager(ForgetPayWord2Activity.this));
                reBankCard.setAdapter(new BankCar2Adapter(ForgetPayWord2Activity.this, data));

            }
        });
    }

    @OnClick({R.id.layout_back, R.id.rl_click_BankCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rl_click_BankCard:
                intent = new Intent(this,BindingIdCard.class);
                intent.putExtra("intentTag","1");
                startActivity(intent);

                break;
        }
    }
}
