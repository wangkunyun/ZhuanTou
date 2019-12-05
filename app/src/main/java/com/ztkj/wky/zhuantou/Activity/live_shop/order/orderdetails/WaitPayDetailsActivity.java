package com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails;
/**
 * 作者：wky
 * 功能描述：待付款详情
 */

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.ConfirmOrderActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.GuessLikeBean;
import com.ztkj.wky.zhuantou.bean.JudgmentTimeBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.OrderDetailsBean;
import com.ztkj.wky.zhuantou.bean.ShopKeyBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.util.ArrayList;
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
    @BindView(R.id.tv_payDetails1)
    TextView tvPayDetails1;
    @BindView(R.id.tv_payDetails2)
    TextView tvPayDetails2;
    @BindView(R.id.rl_payDetailsBottom)
    RelativeLayout rlPayDetailsBottom;
    private OrderDetailsBean.DataBean data;
    private List<OrderDetailsBean.DataBean.ArrBean> arr;
    private OrderBean.DataBean dataBean;
    private String sso_state;
    private ShopKeyBean shopKeyBean;
    private String keyString;
    private List<String> listKey = new ArrayList<>();
    private StringBuilder stringBuilder;
    private GuessLikeBean guessLikeBean;
    private List<GuessLikeBean.DataBean> GuessData;
    private String TAG = "WaitPayDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_details);
        ButterKnife.bind(this);
        layoutTitleTv.setText("订单详情");
        reViewGuess.setHasFixedSize(true);
        reViewGuess.setNestedScrollingEnabled(false);
        reView.setHasFixedSize(true);
        reView.setNestedScrollingEnabled(false);
        String orderState = getIntent().getStringExtra("orderState");
        String orderNum = getIntent().getStringExtra("orderNum");
        dataBean = (OrderBean.DataBean) getIntent().getSerializableExtra("dataBean");
        sso_state = dataBean.getSso_state();

//        request(orderState, orderNum);
        requestJudgementTime(orderState, orderNum);
        getKeys();
    }

    private void requestJudgementTime(String orderState, String orderNum) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.judgementTime)
                .addParams("sso_sub_order_number", orderNum)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                JudgmentTimeBean judgmentTimeBean = GsonUtil.gsonToBean(response, JudgmentTimeBean.class);
                if (judgmentTimeBean.getErrno().equals("200")) {
                    tvFinishOrderTime.setText(judgmentTimeBean.getData().getTime() + "分钟内后自动关闭");
                    request(orderState, orderNum);
                } else if (judgmentTimeBean.getErrno().equals("201")) {
                    tvPayDetails1.setVisibility(View.GONE);
                    tvFinishOrderTime.setVisibility(View.GONE);
                    rlPayDetailsBottom.setVisibility(View.GONE);
                    tvPayDetails2.setVisibility(View.VISIBLE);
                    request("4", orderNum);
                }
            }
        });
    }

    private void request(String orderState, String orderNum) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.OrderDetails)
                .addParams("so_state", orderState)
                .addParams("sso_sub_order_number", orderNum)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                OrderDetailsBean orderDetailsBean = GsonUtil.gsonToBean(response, OrderDetailsBean.class);
                if (orderDetailsBean.getErrno().equals("200")) {

                    data = orderDetailsBean.getData();
                    arr = data.getArr();
                    tvName.setText(data.getSra_username());
                    tvPhone.setText(data.getSra_phone());
                    tvAddress.setText(data.getSra_address());
                    tvStoreName.setText(data.getSs_name());
                    if (!data.getSs_logo().equals("0")) {
                        Glide.with(WaitPayDetailsActivity.this).load(data.getSs_logo()).into(imgStoreHead);
                    }
                    tvOrderNum.setText("订单编号: " + data.getSso_sub_order_number());
                    tvAddOrderTime.setText("下单时间: " + data.getSo_addtime());
                    tvShopNum.setText("共" + data.getArr().size() + "件商品");
                    float sum = 0;
                    for (int j = 0; j < arr.size(); j++) {
                        float sog_total_price = Float.parseFloat(arr.get(j).getSog_total_price());
                        sum += sog_total_price;
                    }
                    tvShopPrice.setText("￥" + sum);

                    Adapter adapter = new Adapter();
                    reView.setLayoutManager(new LinearLayoutManager(WaitPayDetailsActivity.this));
                    reView.setAdapter(adapter);
                }
            }
        });

    }


    private void getKeys() {
        OkHttpUtils.post().url(Contents.getKey)
                .addParams("goods_name", dataBean.getArr().get(0).getSog_name())
                .addParams("goods_details", dataBean.getArr().get(0).getSog_name())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            shopKeyBean = new Gson().fromJson(response, ShopKeyBean.class);
                            listKey = shopKeyBean.getData();
                            keyString = ListToString();
                            if (keyString != null) {
                                getGussLike();
                                if (SPUtils.getInstance().getString("uid") != null) {
                                    recorder();
                                }
                            }
                        }
                    }
                });

    }

    private void getGussLike() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.guessLike)
                .addParams("key_word", keyString)
                .addParams("page", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            guessLikeBean = new Gson().fromJson(response, GuessLikeBean.class);
                            if (guessLikeBean != null) {
                                GuessData = guessLikeBean.getData();
                                reViewGuess.setLayoutManager(new GridLayoutManager(WaitPayDetailsActivity.this, 2));
                                reViewGuess.setAdapter(new GuessAdapter());
                            } else {
                                ToastUtils.showShort("解析失败");
                            }
                        }
                    }


                });
    }

    private void recorder() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.recorderUser)
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("key_word", keyString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                    }
                });
    }

    private String ListToString() {
        if (listKey != null && listKey.size() > 0) {
            stringBuilder = new StringBuilder();
            for (int i = 0; i < listKey.size(); i++) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(listKey.get(i));
            }
        }

        return stringBuilder.toString();

    }

    public static void start(Context context, String orderState, String orderNum, OrderBean.DataBean dataBean) {
        Intent starter = new Intent(context, WaitPayDetailsActivity.class);
        starter.putExtra("orderState", orderState);
        starter.putExtra("orderNum", orderNum);
        starter.putExtra("dataBean", dataBean);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.tv_clickCopy, R.id.tv_ClickCancelOrder, R.id.tv_clickPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_clickCopy: //复制
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(data.getSso_sub_order_number());
                Toast.makeText(this, "复制成功!", Toast.LENGTH_LONG).show();

                break;
            case R.id.tv_ClickCancelOrder: //取消订单
                popuinit("是否确定取消订单吗？", "取消", "确认", data.getSso_sub_order_number(), "取消订单");
                break;
            case R.id.tv_clickPay: //立即付款
                ConfirmOrderActivity.startConfim(WaitPayDetailsActivity.this, dataBean);
                break;
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(WaitPayDetailsActivity.this).inflate(R.layout.item_layout_order_details, viewGroup, false);

            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(WaitPayDetailsActivity.this).load(arr.get(i).getSc_img()).into(viewHolder.item_imgOrderDetailsPic);
            viewHolder.item_tvOrderDetailsTitle.setText(arr.get(i).getSog_name());
            viewHolder.item_tvOrderDetailsSku.setText(arr.get(i).getSog_sku_name());
            viewHolder.item_tvOrderDetailsBuyNum.setText(arr.get(i).getSog_number() + "件");
            viewHolder.tv_itemOrderDetailsPrice.setText(arr.get(i).getSog_total_price());
            if (sso_state.equals("0")) {
                viewHolder.item_tvClickRefund.setVisibility(View.GONE);
            }
            //退款
            viewHolder.item_tvClickRefund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RefundActivity.start(WaitPayDetailsActivity.this, dataBean);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView item_imgOrderDetailsPic;
            private TextView item_tvOrderDetailsTitle, item_tvOrderDetailsSku, item_tvOrderDetailsBuyNum, tv_itemOrderDetailsPrice, item_tvClickRefund;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_imgOrderDetailsPic = itemView.findViewById(R.id.item_imgOrderDetailsPic);
                item_tvOrderDetailsTitle = itemView.findViewById(R.id.item_tvOrderDetailsTitle);
                item_tvOrderDetailsSku = itemView.findViewById(R.id.item_tvOrderDetailsSku);
                item_tvOrderDetailsBuyNum = itemView.findViewById(R.id.item_tvOrderDetailsBuyNum);
                tv_itemOrderDetailsPrice = itemView.findViewById(R.id.tv_itemOrderDetailsPrice);
                item_tvClickRefund = itemView.findViewById(R.id.item_tvClickRefund);
            }
        }
    }

    class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(WaitPayDetailsActivity.this).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(WaitPayDetailsActivity.this).load(GuessData.get(i).getSc_img()).into(viewHolder.img_shopPic);
            viewHolder.tv_shop_des.setText(GuessData.get(i).getSc_name());
            viewHolder.tv_shopPrice.setText(GuessData.get(i).getSc_present_price());
        }

        @Override
        public int getItemCount() {
            return GuessData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView img_shopPic;
            private TextView tv_shop_des, tv_shopPrice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img_shopPic = itemView.findViewById(R.id.img_shopPic);
                tv_shop_des = itemView.findViewById(R.id.tv_shop_des);
                tv_shopPrice = itemView.findViewById(R.id.tv_shopPrice);
            }
        }
    }


    private void popuinit(String s, String s1, final String s2, String id, String orderState) {
        View contentView = LayoutInflater.from(WaitPayDetailsActivity.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(WaitPayDetailsActivity.this).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (orderState) {
                    case "取消订单":
                        requestCancelOrder();
                        break;
                    case "确认收货":
                        requestconfirmReceipt();
                        break;
                    case "删除订单":
                        requestDelOrder();
                        break;
                }

                window.dismiss();
            }

            private void requestconfirmReceipt() {
                OkHttpUtils.post().url(Contents.SHOPBASE + Contents.confirmReceipt)
                        .addParams("sso_sub_order_number", id)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {

                            ToastUtils.showLong("已确认收货");
                        }
                    }
                });
            }

            private void requestDelOrder() {
                OkHttpUtils.post().url(Contents.SHOPBASE + Contents.deleteOrder)
                        .addParams("sso_sub_order_number", id)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {

                            ToastUtils.showLong("订单删除成功");
                        }
                    }
                });
            }

            private void requestCancelOrder() {
                OkHttpUtils.post().url(Contents.SHOPBASE + Contents.cancelOrder)
                        .addParams("sso_sub_order_number", id)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {

                            ToastUtils.showLong("订单取消成功");
                        }
                    }
                });
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
