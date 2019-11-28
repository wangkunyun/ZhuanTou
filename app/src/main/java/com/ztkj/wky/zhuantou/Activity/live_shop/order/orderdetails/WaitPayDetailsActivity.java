package com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails;
/**
 * 作者：wky
 * 功能描述：待付款详情
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OrderDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaitPayDetailsActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.tv_FinishOrderTime)
    TextView tvFinishOrderTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_Address)
    TextView tvAddress;
    @BindView(R.id.img_StoreHead)
    ImageView imgStoreHead;
    @BindView(R.id.tv_StoreName)
    TextView tvStoreName;
    @BindView(R.id.reView)
    RecyclerView reView;
    @BindView(R.id.tv_orderNum)
    TextView tvOrderNum;
    @BindView(R.id.tv_addOrderTime)
    TextView tvAddOrderTime;
    @BindView(R.id.tv_clickCopy)
    TextView tvClickCopy;
    @BindView(R.id.reViewGuess)
    RecyclerView reViewGuess;
    @BindView(R.id.tv_ShopNum)
    TextView tvShopNum;
    @BindView(R.id.tv_shopPrice)
    TextView tvShopPrice;
    @BindView(R.id.tv_ClickCancelOrder)
    TextView tvClickCancelOrder;
    @BindView(R.id.tv_clickPay)
    TextView tvClickPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_details);
        ButterKnife.bind(this);

        String orderState = getIntent().getStringExtra("orderState");
        String orderNum = getIntent().getStringExtra("orderNum");

        request(orderState, orderNum);

    }

    private void request(String orderState, String orderNum) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.OrderDetails)
                .addParams("so_state", orderState)
                .addParams("sso_sub_order_number", orderNum)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                OrderDetailsBean orderDetailsBean = GsonUtil.gsonToBean(response, OrderDetailsBean.class);
                if (orderDetailsBean.getErrno().equals("200")) {
                    List<OrderDetailsBean.DataBean> data = orderDetailsBean.getData();
//                    tvStoreName.setText(data.get);
                }
            }
        });

    }

    public static void start(Context context, String orderState, String orderNum) {
        Intent starter = new Intent(context, WaitPayDetailsActivity.class);
        starter.putExtra("orderState", orderState);
        starter.putExtra("orderNum", orderNum);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.tv_clickCopy, R.id.tv_ClickCancelOrder, R.id.tv_clickPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.tv_clickCopy: //复制
                break;
            case R.id.tv_ClickCancelOrder: //取消订单
                break;
            case R.id.tv_clickPay: //立即付款
                break;
        }
    }
}
