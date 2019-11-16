package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.H5.H5Activity2;
import com.ztkj.wky.zhuantou.MyUtils.DisplayUtil;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.MyFlowLayout;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CouponAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopDetailAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopListAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopParamAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.guess_like_list)
    RecyclerView guess_like_list;
    @BindView(R.id.shop_list)
    RecyclerView shop_list;
    LiveShopListAdapter liveShopListAdapter;
    @BindView(R.id.shop_detail_img)
    RecyclerView shop_detail_img;
    LiveShopDetailAdapter liveShopDetailAdapter;
    @BindView(R.id.layout_back)
    ImageView layout_back;
    @BindView(R.id.add_shopping_cart)
    TextView add_shopping_cart;
    @BindView(R.id.at_once_buy)
    TextView at_once_buy;
    @BindView(R.id.rela_select_size)
    RelativeLayout rela_select_size;
    @BindView(R.id.rela_selelct_param)
    RelativeLayout rela_selelct_param;
    @BindView(R.id.get_cuopon)
    LinearLayout get_cuopon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        initData();
        layout_back.setOnClickListener(this);
        add_shopping_cart.setOnClickListener(this);
        at_once_buy.setOnClickListener(this);
        rela_selelct_param.setOnClickListener(this);
        rela_select_size.setOnClickListener(this);
        get_cuopon.setOnClickListener(this);
    }

    private void initData() {
        JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json, JsonBean.class);
        List<JsonBean.ListBean> list = jsonBean.getList();
        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(ShopDetailActivity.this, list);
        guess_like_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 2));
        guess_like_list.setAdapter(liveShopAdapter);
        shop_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 3));
        liveShopListAdapter = new LiveShopListAdapter(ShopDetailActivity.this, list);
        shop_list.setAdapter(liveShopListAdapter);
        shop_detail_img.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
        liveShopDetailAdapter = new LiveShopDetailAdapter(ShopDetailActivity.this, list);
        shop_detail_img.setAdapter(liveShopDetailAdapter);
        shop_detail_img.setNestedScrollingEnabled(false);
        shop_list.setNestedScrollingEnabled(false);
        guess_like_list.setNestedScrollingEnabled(false);
        shop_list.setHasFixedSize(true);
        shop_detail_img.setHasFixedSize(true);
        guess_like_list.setHasFixedSize(true);


    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private void popuinit() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_param, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        ShopParamAdapter shopParamAdapter = new ShopParamAdapter(ShopDetailActivity.this);
        lv.setAdapter(shopParamAdapter);
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
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

    private void popuSize() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_size, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        MyFlowLayout lv = contentView.findViewById(R.id.flowLayout);
        Button btnConfirm = contentView.findViewById(R.id.btn_confirm);
        setFlowLayout(lv);
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
    }

    private void setFlowLayout(MyFlowLayout myFlowLayout) {
        try {
            for (int i = 0; i < 12; i++) {
                TextView tv = new TextView(this);
                tv.setTextSize(14);
                tv.setText("蓝色");
                tv.setTextColor(getResources().getColor(R.color.t4));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 15, 15, 5);
                tv.setLayoutParams(params);
                tv.setMaxWidth(DisplayUtil.getScreenWidth(ShopDetailActivity.this) - 20);//这句话是为了限制过长的内容顶出屏幕而设置的
                tv.setPadding(25, 15, 25, 15);
                tv.setBackgroundResource(R.drawable.yuanjiaobtnfours);
                myFlowLayout.addView(tv);
            }
        } catch (Exception e) {
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.add_shopping_cart:
                ShopCartActivity.start(ShopDetailActivity.this);
                break;
            case R.id.at_once_buy:
                ConfirmOrderActivity.start(ShopDetailActivity.this);
                break;
            case R.id.rela_selelct_param:
                popuinit();
                break;
            case R.id.rela_select_size:
                popuSize();
                break;
            case R.id.get_cuopon:
//                popuCoupon();
                RefundActivity.start(ShopDetailActivity.this);
                break;
        }
    }

    private void popuCoupon() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_coupon, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        CouponAdapter shopParamAdapter = new CouponAdapter(ShopDetailActivity.this);
        lv.setAdapter(shopParamAdapter);
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }
}
