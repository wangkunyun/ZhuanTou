package com.ztkj.wky.zhuantou.Activity.live_shop.invoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyInvoiceActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_InvoicePrice)
    TextView tvInvoicePrice;
    @BindView(R.id.tv_OrderNum)
    TextView tvOrderNum;
    @BindView(R.id.tv_InvoiceType)
    TextView tvInvoiceType;
    @BindView(R.id.rl_clickChooseInvoiceType)
    RelativeLayout rlClickChooseInvoiceType;
    @BindView(R.id.et_InvoiceCompanyName)
    EditText etInvoiceCompanyName;
    @BindView(R.id.etInvoiceCompanyDutyNum)
    EditText etInvoiceCompanyDutyNum;
    @BindView(R.id.et_InvoiceBank)
    EditText etInvoiceBank;
    @BindView(R.id.et_InvoiceBankNum)
    EditText etInvoiceBankNum;
    @BindView(R.id.et_InvoiceCompanyAdress)
    EditText etInvoiceCompanyAdress;
    @BindView(R.id.et_InvoiceEmil)
    EditText etInvoiceEmil;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.view_person)
    RelativeLayout viewPerson;
    @BindView(R.id.view_company)
    LinearLayout viewCompany;
    @BindView(R.id.et_InvoiceEmilPerson)
    EditText etInvoiceEmilPerson;
    private String shopMoney, shopNum, shopID;
    private String InvoiceType = "1";
    private String email;
    private String TAG = "ApplyInvoiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_invoice);
        ButterKnife.bind(this);
        layoutTitleTv.setText("申请开票");
        etInvoiceEmil.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etInvoiceEmilPerson.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        shopMoney = getIntent().getStringExtra("shopMoney");
        shopNum = getIntent().getStringExtra("shopNum");
        shopID = getIntent().getStringExtra("shopID");

        tvInvoicePrice.setText("￥" + shopMoney);
        tvOrderNum.setText(shopNum);
        email = etInvoiceEmilPerson.getText().toString();
    }

    @OnClick({R.id.layout_back, R.id.rl_clickChooseInvoiceType, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rl_clickChooseInvoiceType:
                popuinit();
                break;
            case R.id.btnSubmit:
                if (tvInvoiceType.getText().toString().equals("个人")) {
                    if (etInvoiceEmilPerson.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    popuinit2("是否确定提交该发票申请？", "取消 ", "确定");
                } else if (tvInvoiceType.getText().toString().equals("企业")) {
                    if (etInvoiceCompanyName.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入企业名称", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etInvoiceCompanyDutyNum.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入企业税号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (etInvoiceEmil.getText().toString().equals("")) {
                        Toast.makeText(this, "请输入企业税号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    popuinit2("是否确定提交该发票申请？", "取消 ", "确定");
                }

                break;
        }
    }

    public static void start(Context context, String shopMoney, String shopNum, String shopID) {
        Intent starter = new Intent(context, ApplyInvoiceActivity.class);
        starter.putExtra("shopMoney", shopMoney);
        starter.putExtra("shopNum", shopNum);
        starter.putExtra("shopID", shopID);
        context.startActivity(starter);
    }

    private void popuinit() {
        View contentView = LayoutInflater.from(ApplyInvoiceActivity.this).inflate(R.layout.invoice_pp, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.4f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
//        final EditText editText = contentView.findViewById(R.id.ppsz_edt);
        final TextView pbutton = contentView.findViewById(R.id.pp_t1);
        final TextView pbutton2 = contentView.findViewById(R.id.pp_t2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(ApplyInvoiceActivity.this).inflate(R.layout.activity_apply_invoice, null);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInvoiceType.setText(pbutton.getText().toString());
                viewCompany.setVisibility(View.VISIBLE);
                viewPerson.setVisibility(View.GONE);
                email = etInvoiceEmil.getText().toString();
                InvoiceType = "2";
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInvoiceType.setText(pbutton2.getText().toString());
                viewPerson.setVisibility(View.VISIBLE);
                viewCompany.setVisibility(View.GONE);
                email = etInvoiceEmilPerson.getText().toString();
                InvoiceType = "1";
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    private void popuinit2(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(ApplyInvoiceActivity.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(ApplyInvoiceActivity.this).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                InvoiceDetailsFinishActivity.start(ApplyInvoiceActivity.this);
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //确定按钮
                //确认提交发票
                OkHttpUtils.post().url(Contents.SHOPBASE + Contents.ApplyInvoice)
                        .addParams("si_user_id", SPUtils.getInstance().getString("uid"))
                        .addParams("si_store_id", shopID)
                        .addParams("si_invoice_amount", shopMoney)
                        .addParams("si_order_number", shopNum)
                        .addParams("si_header_type", InvoiceType)
                        .addParams("si_header", tvInvoiceType.getText().toString())
                        .addParams("si_ticket_collection_box", email)
                        .addParams("si_enterprise_name", etInvoiceCompanyName.getText().toString())
                        .addParams("si_enterprise_tax_number", etInvoiceCompanyDutyNum.getText().toString())
                        .addParams("si_bank_of_deposit", etInvoiceBank.getText().toString())
                        .addParams("si_bank_account", etInvoiceBankNum.getText().toString())
                        .addParams("si_enterprise_address", etInvoiceCompanyAdress.getText().toString())
                        .addParams("si_invoice_address", etInvoiceCompanyAdress.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            long time = new Date().getTime();
                            String format = formatter.format(time);
                            InvoiceDetailsWaitActivity.start(ApplyInvoiceActivity.this, etInvoiceCompanyName.getText().toString(), etInvoiceCompanyDutyNum.getText().toString(), shopMoney, email, format);
                        } else {
                            ToastUtils.showLong("开票请求异常");
                        }

                    }
                });


                backgroundAlpha(1f);
                window.dismiss();
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
