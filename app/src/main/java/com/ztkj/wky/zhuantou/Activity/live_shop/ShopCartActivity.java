package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ShopCartAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopCartDentailDetailAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.MallShopCartBean;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    ShopCartAdapter shopCartAdapter;
    @BindView(R.id.shop_cart)
    RecyclerView shop_cart;
    ShopCartBean shopCartBean;
    List<ShopCartBean.DataBean> list;
    @BindView(R.id.is_select_buy)
    ImageView isSelectBuy;
    @BindView(R.id.all_select)
    TextView allSelect;
    @BindView(R.id.ll_is_all_selelct)
    LinearLayout llIsAllSelelct;
    @BindView(R.id.total_amount)
    TextView totalAmount;
    @BindView(R.id.upload_confirm)
    TextView uploadConfirm;
    @BindView(R.id.rela_cart)
    RelativeLayout relaCart;


    public static void start(Context context) {
        Intent starter = new Intent(context, ShopCartActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        ButterKnife.bind(this);
        layoutBack.setOnClickListener(this);
        layoutTitleTv.setText("购物车");
        more.setText("管理");
        more.setVisibility(View.VISIBLE);
        shop_cart.setLayoutManager(new LinearLayoutManager(ShopCartActivity.this));
        shopCartAdapter = new ShopCartAdapter(ShopCartActivity.this);
        shop_cart.setAdapter(shopCartAdapter);
    }

    private void initData() {
        shopCartBean = new Gson().fromJson(Contents.jsonShopList, ShopCartBean.class);
        if (shopCartBean.getData() != null && shopCartBean.getData().size() > 0) {
            list = shopCartBean.getData();
            shopCartAdapter.setData(list, 1);
            shopCartAdapter.notifyDataSetChanged();
        }
    }

    //这里用了eventBus来进行实时价格的UI更改。
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String event) {
        //刷新UI
        Log.e("sdfafasa",event);
        totalAmount.setText("￥" + event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;

        }
    }


    @OnClick({R.id.layout_back, R.id.more, R.id.ll_is_all_selelct})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
//                isSelectBuy.setVisibility(View.VISIBLE);
//                allSelect.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_is_all_selelct:
                if (isSelectBuy.isSelected()) {
                    isSelectBuy.setSelected(false);
                    shopCartAdapter.setAllselect(false);
                } else {
                    isSelectBuy.setSelected(true);
                    shopCartAdapter.setAllselect(true);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
