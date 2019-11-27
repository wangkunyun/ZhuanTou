package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.mine.RechargeActivity;
import com.ztkj.wky.zhuantou.MyUtils.PayResult;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ConfimOrderAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AdressUpdateBean;
import com.ztkj.wky.zhuantou.bean.BaseStatusBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;
import com.ztkj.wky.zhuantou.bean.WxPayBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        type = getIntent().getIntExtra("type", 0);
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
        selct_address.setVisibility(View.GONE);
        rela_address.setVisibility(View.VISIBLE);
        initData();
    }

    String ssc_id;
    StringBuilder stringBuilder;

    private void initData() {
        if (serInfos != null) {
            stringBuilder = new StringBuilder();
            if (serInfos.size() > 0) {
                for (int i = 0; i < serInfos.size(); i++) {
                    for (int j = 0; j < serInfos.get(i).getArr().size(); j++) {
                        if (j != 0) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(serInfos.get(i).getArr().get(j).getSsc_id());
                    }

                }
                ssc_id = stringBuilder.toString();
            }
            confimOrderAdapter.setData(serInfos);
            confimOrderAdapter.notifyDataSetChanged();
        }
    }


    List<ShopCartBean.DataBean> serInfos = new ArrayList<>();

    public static void start(Context context, List<ShopCartBean.DataBean> list, String totalPrice, int type) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        starter.putExtra("listobj", (Serializable) list);
        starter.putExtra("totalPrice", totalPrice);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    int type;

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
                if (adressUpdateBean == null) {
                    ToastUtils.showShort("请完善地址");
                    return;
                }
                switch (type) {
                    case 1:
                        atOnceBuy();
                        break;
                    case 2:
                        createOrderCart();
                        break;
                }


//                popuCoupon();
                break;
            case R.id.selct_address:
                Intent intent = new Intent(ConfirmOrderActivity.this, EditAddressActivity.class);
                startActivityForResult(intent, 1);
//                EditAddressActivity.start(ConfirmOrderActivity.this);

                break;
        }
    }

    OrderBean orderBean;

    private void atOnceBuy() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.orderImmediatePurchase)
                .addParams("uid", "369")
                .addParams("so_order_address", "北京市北京市东城区")
                .addParams("so_order_address_id", "2")
                .addParams("so_order_total_price", totalPrice)
                .addParams("sc_id", serInfos.get(0).getArr().get(0).getSsc_id())
                .addParams("ssc_sku_id", serInfos.get(0).getArr().get(0).getSsc_sku_id())
                .addParams("ssc_number", serInfos.get(0).getArr().get(0).getSsc_number())
                .addParams("ssc_unit_price", serInfos.get(0).getArr().get(0).getSsc_unit_price())
                .addParams("ssc_sku_name", serInfos.get(0).getArr().get(0).getSsc_sku_name())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        BaseStatusBean baseStatusBean = new Gson().fromJson(response, BaseStatusBean.class);
                        if (baseStatusBean.getErrno().equals("200")) {
                            orderBean = new Gson().fromJson(response, OrderBean.class);
                            if (orderBean != null && orderBean.getData().get(0).getSso_sub_order_number() != null) {
                                orderId = orderBean.getData().get(0).getSo_order_number();
                                popuCoupon();
                            }

                        } else {
                            ToastUtils.showShort(baseStatusBean.getErrmsg());
                        }
                    }
                });
    }

    private void createOrderCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.cartOrder)
                .addParams("uid", uid)
                .addParams("so_order_address", adressUpdateBean.getUseraddress())
                .addParams("so_order_address_id", adressUpdateBean.getAddressId())
                .addParams("so_order_total_price", totalPrice)
                .addParams("ssc_id", ssc_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("fdfafdsafs",response);
                        BaseStatusBean baseStatusBean = new Gson().fromJson(response, BaseStatusBean.class);
                        if (baseStatusBean.getErrno().equals("200")) {
                            orderBean = new Gson().fromJson(response, OrderBean.class);
                            if (orderBean != null && orderBean.getData().get(0).getSo_order_number() != null) {
                                orderId = orderBean.getData().get(0).getSo_order_number();
                                popuCoupon();
                            }
                        } else {
                            ToastUtils.showShort(baseStatusBean.getErrmsg());
                        }
                    }
                });
    }

    String orderId;

    private void popuCoupon() {
        View contentView = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.pp_shop_pay, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        RelativeLayout wx = contentView.findViewById(R.id.tv_pay_wx);
        RelativeLayout zfb = contentView.findViewById(R.id.tv_pay_zfb);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        ImageView wx_iv = contentView.findViewById(R.id.iv_pay_wx);
        ImageView zfb_iv = contentView.findViewById(R.id.iv_pay_zfb);
        View rootview = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.activity_confirm_order, null);
        setViewDow(contentView, rootview);
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay_default = 1;
                wx_iv.setVisibility(View.VISIBLE);
                zfb_iv.setVisibility(View.INVISIBLE);


            }
        });
        zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_default = 2;
                wx_iv.setVisibility(View.INVISIBLE);
                zfb_iv.setVisibility(View.VISIBLE);
            }
        });
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pay_default) {
                    case 1:
                        getWxPay();
                        break;
                    case 2:
                        getZfbPay();
                        break;
                }
            }
        });
    }
    private static final int SDK_ALI_PAY_FLAG = 1;
    //支付宝返回数据handler
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_ALI_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    String result = "";
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //支付成功
                        result = "支付成功";
//                        aliPaySuccess();
                    } else if ("6001".equals(resultStatus)) {
                        result = "您取消了支付";
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        result = "支付失败";
                    }
//                    ToastUtils.showToast(mContext, result);
                    Toast.makeText(ConfirmOrderActivity.this, result, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };
    //异步处理
    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {
            PayTask alipay = new PayTask(ConfirmOrderActivity.this);
            Map<String, String> result = alipay.payV2(wxPayBean.getData().getResponse(), true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = SDK_ALI_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };
    private void getZfbPay() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.zfbPayOrder)
                .addParams("order_id", orderId)
                .addParams("uid", uid)
                .addParams("total", totalPrice)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        wxPayBean = new Gson().fromJson(response, WxPayBean.class);
                        if (wxPayBean.getErrno().equals("200")) {
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtils.showShort(wxPayBean.getErrmsg());
                        }
                    }
                });

    }

    WxPayBean wxPayBean;

    private void getWxPay() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.wxpayOrder)
                .addParams("order_id", orderId)
                .addParams("uid", uid)
                .addParams("total", totalPrice)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        wxPayBean = new Gson().fromJson(response, WxPayBean.class);
                        if (wxPayBean.getErrno().equals("200")) {
                            //通过IWXAPI 获取到其对象  然后将自己的应用注册到微信
                            IWXAPI api = WXAPIFactory.createWXAPI(ConfirmOrderActivity.this, wxPayBean.getData().getAppid(), true);
                            api.registerApp(wxPayBean.getData().getAppid());
                            //通过如下代码调起微信支付
                            PayReq request = new PayReq();
                            request.appId = wxPayBean.getData().getAppid();
                            request.partnerId = wxPayBean.getData().getPartnerid();
                            request.prepayId = wxPayBean.getData().getPrepayid();
                            request.packageValue = "Sign=WXPay";
                            request.nonceStr = wxPayBean.getData().getNoncestr();
                            request.timeStamp = String.valueOf(wxPayBean.getData().getTimestamp());
                            request.sign = wxPayBean.getData().getSign();
                            api.sendReq(request);
                        } else {
                            ToastUtils.showShort(wxPayBean.getErrmsg());
                        }
                    }
                });
    }

    int pay_default = 1;
    PopupWindow window;
    @BindView(R.id.cofirm_user_name)
    TextView cofirm_user_name;
    @BindView(R.id.confirm_user_phone)
    TextView confirm_user_phone;
    @BindView(R.id.tc_address)
    TextView tc_address;

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

    AdressUpdateBean adressUpdateBean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                selct_address.setVisibility(View.GONE);
                rela_address.setVisibility(View.VISIBLE);
                setDataUser(data);
                break;
            case 2:
                setDataUser(data);
                break;


        }
    }

    private void setDataUser(Intent data) {
        adressUpdateBean = (AdressUpdateBean) data.getSerializableExtra("user");
        if (adressUpdateBean != null) {
            confirm_user_phone.setText(adressUpdateBean.getUserphone());
            tc_address.setText(adressUpdateBean.getUseraddress());
            cofirm_user_name.setText(adressUpdateBean.getUsername());
        }
    }

}
