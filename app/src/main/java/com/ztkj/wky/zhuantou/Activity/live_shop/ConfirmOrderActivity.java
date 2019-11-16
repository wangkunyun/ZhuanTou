package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ConfimOrderAdapter;
import com.ztkj.wky.zhuantou.adapter.ResuseWayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.confirm_list)
    RecyclerView confirm_list;
    ConfimOrderAdapter confimOrderAdapter;
    @BindView(R.id.rela_address)
    LinearLayout rela_address;
    @BindView(R.id.view_divider)
    View view_divider;
    @BindView(R.id.upload_confirm)
    TextView uploadConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        layoutTitleTv.setText("确认订单");
        view_divider.setVisibility(View.GONE);
        layoutBack.setOnClickListener(this);
        confimOrderAdapter = new ConfimOrderAdapter(ConfirmOrderActivity.this);
        confirm_list.setLayoutManager(new LinearLayoutManager(ConfirmOrderActivity.this));
        confirm_list.setAdapter(confimOrderAdapter);
        confirm_list.setHasFixedSize(true);
        confirm_list.setNestedScrollingEnabled(false);
        rela_address.setOnClickListener(this);
        uploadConfirm.setOnClickListener(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rela_address:
                CreateAddressActivity.start(ConfirmOrderActivity.this);
                break;
            case R.id.upload_confirm:
                popuCoupon();
                break;
        }
    }
//
//    Drawable drawableLeft = ConfirmOrderActivity.this.getResources().getDrawable(
//            R.mipmap.pay_sel);


    private void popuCoupon() {
        View contentView = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.pp_shop_pay, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        RelativeLayout wx = contentView.findViewById(R.id.tv_pay_wx);
        RelativeLayout zfb = contentView.findViewById(R.id.tv_pay_zfb);
        ImageView wx_iv=contentView.findViewById(R.id.iv_pay_wx);
        ImageView zfb_iv=contentView.findViewById(R.id.iv_pay_zfb);
        View rootview = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.activity_confirm_order, null);
        setViewDow(contentView, rootview);
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wx_iv.setVisibility(View.VISIBLE);
                zfb_iv.setVisibility(View.GONE);

            }
        });
        zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wx_iv.setVisibility(View.GONE);
                zfb_iv.setVisibility(View.VISIBLE);
            }
        });
    }


    PopupWindow window;

    private void setViewDow(View view, View rootview) {
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
