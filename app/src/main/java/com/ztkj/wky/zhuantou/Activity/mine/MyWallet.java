package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.BankCarAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BandingBankCardBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWallet extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.click_charge)
    TextView clickCharge;
    @BindView(R.id.tv_idCardNum)
    TextView tvIdCardNum;
    @BindView(R.id.reBankCard)
    RecyclerView reBankCard;
    @BindView(R.id.click_addBankCard)
    ImageView clickAddBankCard;
    private Intent intent;
    private String TAG = "MyWallet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        layoutTitleTv.setText("我的钱包");
        more.setVisibility(View.VISIBLE);
        more.setText("账单");
        tvMoney.setText(SPUtils.getInstance().getString("balance"));
        requestBankCarList() ;
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
                reBankCard.setLayoutManager(new LinearLayoutManager(MyWallet.this));
                reBankCard.setAdapter(new BankCarAdapter(MyWallet.this,data));

            }
        });
    }

    @OnClick({R.id.layout_back, R.id.more, R.id.click_charge, R.id.click_addBankCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more: //账单
                intent = new Intent(MyWallet.this, BillList.class);
                startActivity(intent);
                break;
            case R.id.click_charge: //充值
                if (SPUtils.getInstance().getString("isrenzheng").equals("0")) {
                    popuinit("实名认证后即可绑定银行卡，是否前往？", "取消", "前往");
                } else {
                    intent = new Intent(MyWallet.this, RechargeActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.click_addBankCard: //添加银行卡
                if (SPUtils.getInstance().getString("isrenzheng").equals("0")) {
                    popuinit("实名认证后即可绑定银行卡，是否前往？", "取消", "前往");
                } else {
                    intent = new Intent(MyWallet.this, BindingIdCard.class);
                    intent.putExtra("intentTag","0");
                    startActivity(intent);
                }

                break;
        }
    }


    private void popuinit(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(MyWallet.this).inflate(R.layout.pp_create, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(MyWallet.this).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 前往实名认证
                 */
                intent = new Intent(MyWallet.this, NameRenZhengActivity.class);
                startActivity(intent);
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


}
