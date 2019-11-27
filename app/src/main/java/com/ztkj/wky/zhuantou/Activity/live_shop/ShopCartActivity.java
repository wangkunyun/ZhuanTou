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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ShopCartAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopCartDentailDetailAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.MallShopCartBean;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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

    @BindView(R.id.delete_shop)
    TextView delete_shop;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopCartActivity.class);
        context.startActivity(starter);
    }

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        uid = SPUtils.getInstance().getString("uid");
        initView();
        initData();
    }

    private void initView() {
        layoutBack.setOnClickListener(this);
        layoutTitleTv.setText("购物车");
        more.setText("管理");
        more.setVisibility(View.VISIBLE);
        shop_cart.setLayoutManager(new LinearLayoutManager(ShopCartActivity.this));
        shopCartAdapter = new ShopCartAdapter(ShopCartActivity.this);
        shop_cart.setAdapter(shopCartAdapter);
        shopCartAdapter.setShopListen(new ShopCartAdapter.SetAllShopListen() {
            @Override
            public void shopListen(boolean isSlect) {
                if (isSlect) {
                    isSelectBuy.setSelected(true);
                } else {
                    isSelectBuy.setSelected(false);
                }
            }
        });

    }

    private void initData() {

        if (uid != null) {
            getCart();
        }

    }

    private void getCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.cartList)
                .addParams("page", "1")
                .addParams("user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            shopCartBean = new Gson().fromJson(response, ShopCartBean.class);
                            if (shopCartBean.getData() != null && shopCartBean.getData().size() > 0) {
                                list = shopCartBean.getData();
                                shopCartAdapter.setData(list, 1);
                                shopCartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    //这里用了eventBus来进行实时价格的UI更改。
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String event) {
        //刷新UI
        totalprice = event;
        Log.e("sdfafasa", event);
        totalAmount.setText("￥" + event);
    }

    String totalprice;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;

        }
    }


    @OnClick({R.id.layout_back, R.id.more, R.id.ll_is_all_selelct, R.id.delete_shop, R.id.upload_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
//                isSelectBuy.setVisibility(View.VISIBLE);
//                allSelect.setVisibility(View.VISIBLE);
                CollectShopActivity.start(ShopCartActivity.this);
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
            case R.id.delete_shop:
                shopCartAdapter.getSelect();
//              ssc_id= shopCartAdapter.selectShopCart();
                if (uid != null) {
                    if (ssc_id != null) {
                        deleteCart();
                        llIsAllSelelct.setVisibility(View.GONE);
                        isSelectBuy.setVisibility(View.GONE);
                    } else {
                        ToastUtils.showShort("请选择删除的商品");
                    }

                }
                break;
            case R.id.upload_confirm:
                listSelect = shopCartAdapter.getSelectList();
                if (listSelect != null && listSelect.size() > 0) {
                    ConfirmOrderActivity.start(ShopCartActivity.this, listSelect, totalprice,2);
//                    ConfirmOrderActivity.start(ShopCartActivity.this, listSelect);
                } else {
                    ToastUtils.showShort("请选择商品");
                }

                break;
        }
    }

    List<ShopCartBean.DataBean> listSelect = new ArrayList<>();
    String ssc_id;

    private void deleteCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.deleltCart)
                .addParams("ssc_id", ssc_id)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("daf", response);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
