package com.ztkj.wky.zhuantou.Activity.live_shop.invoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceDetailsWaitActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.et_WaitInvoiceCompanyName)
    EditText etWaitInvoiceCompanyName;
    @BindView(R.id.tv_WaitInvoiceCompanyDuty)
    EditText tvWaitInvoiceCompanyDuty;
    @BindView(R.id.tv_WaitInvoiceMoney)
    TextView tvWaitInvoiceMoney;
    @BindView(R.id.et_WaitInvoiceEmail)
    EditText etWaitInvoiceEmail;
    @BindView(R.id.tv_WaitInvoiceAmendTime)
    TextView tvWaitInvoiceAmendTime;
    @BindView(R.id.btnAmendApply)
    TextView btnAmendApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details_wait);
        ButterKnife.bind(this);
        layoutTitleTv.setText("发票详情");
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InvoiceDetailsWaitActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.btnAmendApply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btnAmendApply:
                break;
        }
    }
}
