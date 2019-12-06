package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OrderBean;

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
    OrderBean OrderBean;
    List<OrderBean.DataBean> list;
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
    @BindView(R.id.relaEmpty)
    RelativeLayout relaEmpty;

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
                            OrderBean = new Gson().fromJson(response, OrderBean.class);
                            if (OrderBean.getData() != null && OrderBean.getData().size() > 0) {
                                list = OrderBean.getData();
                                if (list != null && list.size() > 0) {
                                    isHidden(false);
                                    shopCartAdapter.setData(list, 1);
                                    shopCartAdapter.notifyDataSetChanged();
                                } else {
                                    isHidden(true);
                                }

                            }
                        }
                    }
                });
    }

    private void isHidden(boolean isHidden) {
        if(isHidden){
            shop_cart.setVisibility(View.GONE);
            relaEmpty.setVisibility(View.VISIBLE);
        }else{
            shop_cart.setVisibility(View.VISIBLE);
            relaEmpty.setVisibility(View.GONE);
        }
    }

    //这里用了eventBus来进行实时价格的UI更改。
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String event) {
        //刷新UI
        totalprice = event;
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

    @BindView(R.id.add_collect)
    TextView add_collect;

    @OnClick({R.id.layout_back, R.id.more, R.id.ll_is_all_selelct, R.id.delete_shop, R.id.upload_confirm, R.id.add_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_collect:
                if(shopCartAdapter.getData()>0){
                    CollectShopActivity.start(ShopCartActivity.this);
                }else{
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("购物车暂无数据");
                }
                break;
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                if(shopCartAdapter.getData()>0){
                    if (more.getText().toString().equals("管理")) {
                        add_collect.setVisibility(View.VISIBLE);
                        uploadConfirm.setVisibility(View.INVISIBLE);
                        delete_shop.setVisibility(View.VISIBLE);
                        more.setText("完成");
                    } else {
                        add_collect.setVisibility(View.INVISIBLE);
                        uploadConfirm.setVisibility(View.VISIBLE);
                        delete_shop.setVisibility(View.INVISIBLE);
                        more.setText("管理");
                    }
                }else{
                    ToastUtils.showShort("购物车暂无数据");
                }
                break;
            case R.id.ll_is_all_selelct:
                if(shopCartAdapter!=null&&shopCartAdapter.getData()>0){
                    if (isSelectBuy.isSelected()) {
                        isSelectBuy.setSelected(false);
                        shopCartAdapter.setAllselect(false);
                    } else {
                        isSelectBuy.setSelected(true);
                        shopCartAdapter.setAllselect(true);
                    }
                }else{
                    ToastUtils.showShort("购物车暂无数据");
                }
                break;
            case R.id.delete_shop:
                shopCartAdapter.getSelect();
                ssc_id = shopCartAdapter.ssc_id;
                if (uid != null) {
                    if (ssc_id != null) {
                        if(shopCartAdapter.getData()>0){

                        }else{
                            isHidden(true);
                            delete_shop.setVisibility(View.INVISIBLE);
                            add_collect.setVisibility(View.INVISIBLE);
                            totalAmount.setText(Contents.moneyTag+"0");
                            isSelectBuy.setSelected(false);
                        }

                    } else {
                        ToastUtils.showShort("请选择删除的商品");
                    }
                }
                break;
            case R.id.upload_confirm:
                listSelect = shopCartAdapter.getSelectList();
                if (listSelect != null && listSelect.size() > 0) {
                    ConfirmOrderActivity.start(ShopCartActivity.this, listSelect, totalprice, 2);
//                    ConfirmOrderActivity.start(ShopCartActivity.this, listSelect);
                } else {
                    ToastUtils.showShort("购物车暂无数据");
                }

                break;
        }
    }

    List<OrderBean.DataBean> listSelect = new ArrayList<>();
    String ssc_id;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
