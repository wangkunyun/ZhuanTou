package com.ztkj.wky.zhuantou.Activity.live_shop.invoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.et_WaitInvoiceCompanyName)
    TextView etWaitInvoiceCompanyName;
    @BindView(R.id.tv_WaitInvoiceCompanyDuty)
    EditText tvWaitInvoiceCompanyDuty;
    @BindView(R.id.tv_WaitInvoiceMoney)
    TextView tvWaitInvoiceMoney;
    @BindView(R.id.et_WaitInvoiceEmail)
    TextView etWaitInvoiceEmail;
    @BindView(R.id.tv_WaitInvoiceAmendTime)
    TextView tvWaitInvoiceAmendTime;
    private String CompanyName, InvoiceNum, InvoicePrice, Email, ApplyDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details_wait);
        ButterKnife.bind(this);
        layoutTitleTv.setText("发票详情");
        CompanyName = getIntent().getStringExtra("CompanyName");
        InvoiceNum = getIntent().getStringExtra("InvoiceNum");
        InvoicePrice = getIntent().getStringExtra("InvoicePrice");
        Email = getIntent().getStringExtra("Email");
        ApplyDate = getIntent().getStringExtra("ApplyDate");
        etWaitInvoiceCompanyName.setText(CompanyName);
        tvWaitInvoiceCompanyDuty.setText(InvoiceNum);
        tvWaitInvoiceMoney.setText(InvoicePrice + "元");
        etWaitInvoiceEmail.setText(Email);
        tvWaitInvoiceAmendTime.setText(ApplyDate);

    }

    public static void start(Context context, String CompanyName, String InvoiceNum, String InvoicePrice, String Email, String ApplyDate) {
        Intent starter = new Intent(context, InvoiceDetailsWaitActivity.class);
        starter.putExtra("CompanyName", CompanyName);
        starter.putExtra("InvoiceNum", InvoiceNum);
        starter.putExtra("InvoicePrice", InvoicePrice);
        starter.putExtra("Email", Email);
        starter.putExtra("ApplyDate", ApplyDate);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
        }
    }
}
