package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ConfimOrderAdapter;
import com.ztkj.wky.zhuantou.adapter.ResuseWayAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.price_total)
    TextView price_total;
    @BindView(R.id.selct_address)
    RelativeLayout selct_address;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        uid = SPUtils.getInstance().getString("uid");
        totalPrice = getIntent().getStringExtra("totalPrice");
        serInfos = (List<ShopCartBean.DataBean>) getIntent().getSerializableExtra("listobj");
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
        selct_address.setOnClickListener(this);
        if (totalPrice != null) {
            price_total.setText("¥ " + totalPrice);
        }
        initData();
    }

    private void initData() {
        if (serInfos != null) {
            confimOrderAdapter.setData(serInfos);
            confimOrderAdapter.notifyDataSetChanged();
        }
    }


    List<ShopCartBean.DataBean> serInfos = new ArrayList<>();

    public static void start(Context context, List<ShopCartBean.DataBean> list, String totalPrice) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        starter.putExtra("listobj", (Serializable) list);
        starter.putExtra("totalPrice", totalPrice);
        context.startActivity(starter);
    }

    String totalPrice;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rela_address:
                Intent intent1 = new Intent(ConfirmOrderActivity.this, CreateAddressActivity.class);
                startActivityForResult(intent1, 2);
//                CreateAddressActivity.start(ConfirmOrderActivity.this);
                break;
            case R.id.upload_confirm:
                createOrderCart();
//                popuCoupon();
                break;
            case R.id.selct_address:
                Intent intent = new Intent(ConfirmOrderActivity.this, EditAddressActivity.class);
                startActivityForResult(intent, 1);

//                EditAddressActivity.start(ConfirmOrderActivity.this);

                break;
        }
    }

    private void createOrderCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.cartOrder)
                .addParams("uid", uid)
                .addParams("so_order_address", "北京市北京市东城区")
                .addParams("so_order_total_price", totalPrice)
                .addParams("ssc_id", serInfos.get(0).getArr().get(0).getSsc_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("dfaf",e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("dfaf",response);
                    }
                });
    }


    private void popuCoupon() {
        View contentView = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.pp_shop_pay, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        RelativeLayout wx = contentView.findViewById(R.id.tv_pay_wx);
        RelativeLayout zfb = contentView.findViewById(R.id.tv_pay_zfb);
        ImageView wx_iv = contentView.findViewById(R.id.iv_pay_wx);
        ImageView zfb_iv = contentView.findViewById(R.id.iv_pay_zfb);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:

                break;

            case 2:

                break;


        }
    }

}
