package com.ztkj.wky.zhuantou.Activity.live_shop.invoice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.MyUtils.DonwloadSaveImg;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceDetailsFinishActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.img_Invoice)
    ImageView imgInvoice;
    @BindView(R.id.downloadImg)
    TextView downloadImg;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details_finish);
        ButterKnife.bind(this);
        layoutTitleTv.setText("发票详情");
        Glide.with(this).load("https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png").into(imgInvoice);

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, InvoiceDetailsFinishActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.downloadImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.downloadImg:
                DonwloadSaveImg.donwloadImg(InvoiceDetailsFinishActivity.this, "https://api.zhuantoukj.com/birck/Public/heard/2019-08-02/5d44029fa3c69.png");
                break;
        }
    }

}
