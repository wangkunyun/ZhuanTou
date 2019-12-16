package com.ztkj.wky.zhuantou.Activity.live_shop.order;

/**
 * 作者：wky
 * 功能描述：全部订单
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.invoice.ApplyInvoiceActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.invoice.InvoiceDetailsWaitActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails.AlreadyPayDetailsActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails.AlreadySendDetailsActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails.WaitPayDetailsActivity;
import com.ztkj.wky.zhuantou.MyApplication;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.PayResult;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.InvoiceTypeBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;
import com.ztkj.wky.zhuantou.bean.WxPayBean;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment {


    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;
    private String TAG = "AllOrderFragment";
    private List<OrderBean.DataBean> data;
    PopupWindow window;
    private int pay_default = 1;
    private WxPayBean wxPayBean;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        unbinder = ButterKnife.bind(this, view);
//        requestData();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getOrderList)
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("page", "1")
                .addParams("type", "1")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:全部订单 " + response);
                OrderBean orderBean = GsonUtil.gsonToBean(response, OrderBean.class);
                if (orderBean.getErrno().equals("200")) {
                    data = orderBean.getData();
                    AdapterOut adapterOut = new AdapterOut();
                    reView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    reView.setAdapter(adapterOut);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class AdapterOut extends RecyclerView.Adapter<AdapterOut.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_out, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            List<OrderBean.DataBean.ArrBean> arr;
            arr = data.get(i).getArr();

            viewHolder.item_reOrderOut.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterIn adapterIn = new AdapterIn(data.get(i), arr, data.get(i).getSso_state(), data.get(i).getSso_sub_order_number());
            viewHolder.item_reOrderOut.setAdapter(adapterIn);
            //设置店铺logo
            if (data.get(i).getSs_logo() != null) {
                RoundedCorners roundedCorners = new RoundedCorners(96);
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(Objects.requireNonNull(getActivity())).load(data.get(i).getSs_logo())
                        .apply(options).into(viewHolder.item_imgOrderOutStoreHead);
            }
            //设置店铺名称
            if (data.get(i).getSs_name() != null) {
                viewHolder.item_tvOrderOutStoreName.setText(data.get(i).getSs_name());
            }

            //商品数量
            if (data.get(i).getArr() != null) {
                viewHolder.item_tvOrderOutShopUnm.setText("共" + data.get(i).getArr().size() + "件商品");
            }

            float sum = 0;
            if (data.get(i).getArr() != null) {
                for (int j = 0; j < data.get(i).getArr().size(); j++) {
                    float sog_total_price = Float.parseFloat(arr.get(j).getSog_total_price());
                    sum += sog_total_price;
                }
                viewHolder.itemOrderOutPrice.setText("￥" + sum + "");
            }
            //设置订单状态
            switch (data.get(i).getSso_state()) {
                case "0":
                    viewHolder.item_tvOrderOutState.setText("待付款");
                    viewHolder.item_clickOrderButton1.setText("立即付款");
                    viewHolder.item_clickOrderButton2.setText("取消订单");
                    viewHolder.item_clickOrderButton1.setTextColor(Color.parseColor("#65C9D2"));
                    break;
                case "1":
                    viewHolder.item_tvOrderOutState.setText("已付款");
                    viewHolder.item_clickOrderButton1.setText("批量退款");
                    break;
                case "2":
                    viewHolder.item_tvOrderOutState.setText("待收货");
                    viewHolder.item_clickOrderButton1.setText("确认收货");
                    viewHolder.item_clickOrderButton2.setText("查看物流");
                    viewHolder.item_clickOrderButton3.setText("批量退款");
                    viewHolder.item_clickOrderButton1.setTextColor(Color.parseColor("#65C9D2"));
                    break;
                case "3":
                    viewHolder.item_tvOrderOutState.setText("交易成功");
                    viewHolder.item_clickOrderButton1.setText("查看物流");
                    viewHolder.item_clickOrderButton2.setText("申请开票");
                    viewHolder.item_clickOrderButton3.setText("删除订单");
                    break;
                case "4":
                    viewHolder.item_tvOrderOutState.setText("交易关闭");
                    viewHolder.item_clickOrderButton1.setText("删除订单");
                    break;
            }

            /**
             *  处理点击事件
             */
            //第一个按钮
            viewHolder.item_clickOrderButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data.get(i).getSso_state()) {
                        case "0": //立即付款
                            if (getActivity() != null) {
//                                ConfirmOrderActivity.startConfim(getActivity(), data.get(i));
                                popuCoupon(data.get(i));
                            }
                            break;
                        case "1": //退款
                            if (getActivity() != null) {
                                RefundActivity.start(getActivity(), data.get(i));
                            }
                            break;
                        case "2": //确认收货
                            popuinit("确认收货后钱款将打入卖家账户，您无法发起退款。", "取消", "确认", data.get(i).getSso_sub_order_number(), "确认收货", i);
                            break;
                        case "3"://查看物流
                            if (getActivity() != null) {
                                TradeLogisticsActivity.start(getActivity());
                            }
                            break;
                        case "4": //删除订单
                            popuinit("删除后将无法恢复，确认删除？", "取消", "确认", data.get(i).getSso_sub_order_number(), "删除订单", i);
                            break;
                    }
                }
            });

            //第二个按钮
            float finalSum = sum;
            viewHolder.item_clickOrderButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data.get(i).getSso_state()) {
                        case "0": //取消订单
                            popuinit("是否确定取消订单吗？", "取消", "确认", data.get(i).getSso_sub_order_number(), "取消订单", i);
                            break;
                        case "2": //查看物流
                            if (getActivity() != null) {
                                TradeLogisticsActivity.start(getActivity());
                            }
                            break;
                        case "3"://申请开票
                            if (getActivity() != null) {
                                Log.e(TAG, "onClick: 店铺订单和id" + data.get(i).getSso_sub_order_number() + "+++" + data.get(i).getSs_id());
                                OkHttpUtils.post().url(Contents.SHOPBASE + Contents.invoiceType)
                                        .addParams("si_order_number", data.get(i).getSso_sub_order_number())
                                        .addParams("si_store_id", data.get(i).getSs_id())
                                        .addParams("si_user_id", SPUtils.getInstance().getString("uid"))
                                        .build().execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        Log.e(TAG, "onResponse: 查看开票状态" + response);
                                        InvoiceTypeBean invoiceTypeBean = GsonUtil.gsonToBean(response, InvoiceTypeBean.class);
                                        if (invoiceTypeBean.getErrno().equals("201")) {
                                            ApplyInvoiceActivity.start(getActivity(), finalSum + "", data.get(i).getSso_sub_order_number(), data.get(i).getSs_id());
                                        } else if (invoiceTypeBean.getErrno().equals("200")) {
                                            InvoiceTypeBean.DataBean data = invoiceTypeBean.getData();
                                            InvoiceDetailsWaitActivity.start(getActivity(), data.getSi_enterprise_name(), data.getSi_enterprise_tax_number(),
                                                    data.getSi_invoice_amount(), data.getSi_ticket_collection_box(), data.getSi_addtime());
                                        }

                                    }
                                });
                            }
                            break;
                    }
                }
            });

            //第三个按钮
            viewHolder.item_clickOrderButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data.get(i).getSso_state()) {
                        case "2": //批量退款
                            if (getActivity() != null) {
                                RefundActivity.start(getActivity(), data.get(i));
                            }
                            break;
                        case "3"://删除订单
                            popuinit("删除后将无法恢复，确认删除？", "取消", "确认", data.get(i).getSso_sub_order_number(), "删除订单", i);
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView item_reOrderOut;
            private ImageView item_imgOrderOutStoreHead;
            private TextView item_tvOrderOutStoreName, item_tvOrderOutState, item_tvOrderOutShopUnm,
                    itemOrderOutPrice, item_clickOrderButton1, item_clickOrderButton2, item_clickOrderButton3;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_reOrderOut = itemView.findViewById(R.id.item_reOrderOut);
                item_imgOrderOutStoreHead = itemView.findViewById(R.id.item_imgOrderOutStoreHead);
                item_tvOrderOutStoreName = itemView.findViewById(R.id.item_tvOrderOutStoreName);
                item_tvOrderOutState = itemView.findViewById(R.id.item_tvOrderOutState);
                item_tvOrderOutShopUnm = itemView.findViewById(R.id.item_tvOrderOutShopUnm);
                itemOrderOutPrice = itemView.findViewById(R.id.itemOrderOutPrice);
                item_clickOrderButton1 = itemView.findViewById(R.id.item_clickOrderButton1);
                item_clickOrderButton2 = itemView.findViewById(R.id.item_clickOrderButton2);
                item_clickOrderButton3 = itemView.findViewById(R.id.item_clickOrderButton3);
            }
        }
    }

    /**
     * 里面一层
     */
    class AdapterIn extends RecyclerView.Adapter<AdapterIn.ViewHolder> {
        private OrderBean.DataBean dataBean;
        private List<OrderBean.DataBean.ArrBean> arr;
        private String state;
        private String num;

        public AdapterIn(OrderBean.DataBean dataBean, List<OrderBean.DataBean.ArrBean> arr, String state, String num) {
            this.dataBean = dataBean;
            this.arr = arr;
            this.state = state;
            this.num = num;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_in, viewGroup, false);

            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(Objects.requireNonNull(getContext())).load(arr.get(i).getSc_img()).into(viewHolder.item_imgOrderInPic);
            viewHolder.item_tvOrderInTitle.setText(arr.get(i).getSog_name());
            viewHolder.item_tvOrderInSku.setText(arr.get(i).getSog_sku_name());
            viewHolder.item_tvOrderInBuyNum.setText(arr.get(i).getSog_number() + "件");
            viewHolder.tv_itemOrderInPrice.setText("￥" + arr.get(i).getSog_total_price());
            switch (arr.get(i).getSog_refund_type()) {
                case "0":
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.GONE);
                    break;
                case "2":
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.VISIBLE);
                    break;
                default:
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.VISIBLE);
                    viewHolder.item_tvOrderInIsRefund.setText("退款中");
            }
            //点击进入详情页
            viewHolder.rl_clickOrderIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (state) {
                        case "0": //代付款
                            WaitPayDetailsActivity.start(getActivity(), state, num, dataBean);
                            break;
                        case "1": //代发货
                            AlreadyPayDetailsActivity.start(getActivity(), state, num, dataBean);
                            break;
                        case "2": //待收货
                            AlreadySendDetailsActivity.start(getActivity(), state, num, dataBean);
                            break;
                        case "3": //交易成功
                            WaitPayDetailsActivity.start(getActivity(), state, num, dataBean);
                            break;
                        case "4": //交易关闭
                            WaitPayDetailsActivity.start(getActivity(), state, num, dataBean);
                            break;
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            if (arr != null) {
                return arr.size();
            } else {
                return 0;
            }

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView item_imgOrderInPic;
            private TextView item_tvOrderInTitle, item_tvOrderInSku, item_tvOrderInIsRefund, item_tvOrderInBuyNum, tv_itemOrderInPrice;
            private RelativeLayout rl_clickOrderIn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_imgOrderInPic = itemView.findViewById(R.id.item_imgOrderInPic);
                item_tvOrderInTitle = itemView.findViewById(R.id.item_tvOrderInTitle);
                item_tvOrderInSku = itemView.findViewById(R.id.item_tvOrderInSku);
                item_tvOrderInIsRefund = itemView.findViewById(R.id.item_tvOrderInIsRefund);
                item_tvOrderInBuyNum = itemView.findViewById(R.id.item_tvOrderInBuyNum);
                tv_itemOrderInPrice = itemView.findViewById(R.id.tv_itemOrderInPrice);
                rl_clickOrderIn = itemView.findViewById(R.id.rl_clickOrderIn);
            }
        }
    }


    private void popuinit(String s, String s1, final String s2, String id, String orderState, int i) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_sz, null);

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
                        requestCancelOrder(i);
                        break;
                    case "确认收货":
                        requestconfirmReceipt(i);
                        break;
                    case "删除订单":
                        requestDelOrder(i);
                        break;
                }

                window.dismiss();
            }

            private void requestconfirmReceipt(int i) {
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
                            requestData();
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showLong("已确认收货");
                        }
                    }
                });
            }

            private void requestDelOrder(int i) {
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
                            requestData();
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showLong("订单删除成功");
                        }
                    }
                });
            }

            private void requestCancelOrder(int i) {
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
                            requestData();
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
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


    private void popuCoupon(OrderBean.DataBean dataBean) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pp_shop_pay, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        RelativeLayout wx = contentView.findViewById(R.id.tv_pay_wx);
        RelativeLayout zfb = contentView.findViewById(R.id.tv_pay_zfb);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        ImageView wx_iv = contentView.findViewById(R.id.iv_pay_wx);
        ImageView zfb_iv = contentView.findViewById(R.id.iv_pay_zfb);
        TextView total_price = contentView.findViewById(R.id.total_price);
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_confirm_order, null);
        setViewDow(contentView, rootview);
        Double doublePeice = 0.0;
        String totalPrice;

        for (int i = 0; i < dataBean.getArr().size(); i++) {
            if (dataBean.getArr().get(i).getSog_total_price() != null) {
                doublePeice += Double.parseDouble(dataBean.getArr().get(i).getSog_total_price());
            }
        }
        totalPrice = String.valueOf(doublePeice);
        if (totalPrice != null) {
            total_price.setText("¥" + totalPrice);
        }
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

                        getWxPay(dataBean);
                        break;
                    case 2:
                        getZfbPay(dataBean);
                        break;
                }
            }
        });
    }

    private void getWxPay(OrderBean.DataBean dataBean) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.wxpayOrder)
                .addParams("order_id", dataBean.getSso_sub_order_number())
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("total", "0.01")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("wky", "onResponse: " + response);
                        WxPayBean wxPayBean = new Gson().fromJson(response, WxPayBean.class);

                        if (MyApplication.AppwxPayBean == null) {
                            MyApplication.AppwxPayBean = wxPayBean;
//                            Contents.map.put()
                            setWxPayBean(MyApplication.AppwxPayBean, dataBean);
                        }
                    }
                });
    }

    private void setWxPayBean(WxPayBean wxPay, OrderBean.DataBean dataBean) {
        if (wxPay.getErrno().equals("200")) {
            Contents.map.put(dataBean.getSso_sub_order_number(), wxPay.getData().getPrepayid());
            //通过IWXAPI 获取到其对象  然后将自己的应用注册到微信
            IWXAPI api = WXAPIFactory.createWXAPI(getActivity(), wxPay.getData().getAppid(), true);
            api.registerApp(wxPay.getData().getAppid());
            //通过如下代码调起微信支付
            PayReq request = new PayReq();
            request.appId = wxPay.getData().getAppid();
            request.partnerId = wxPay.getData().getPartnerid();
            request.prepayId = wxPay.getData().getPrepayid();
            request.packageValue = "Sign=WXPay";
            request.nonceStr = wxPay.getData().getNoncestr();
            request.timeStamp = String.valueOf(wxPay.getData().getTimestamp());
            request.sign = wxPay.getData().getSign();
            api.sendReq(request);
        } else {
            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
            ToastUtils.showShort(wxPay.getErrmsg());
        }
    }

    //异步处理
    private static final int SDK_ALI_PAY_FLAG = 1;
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
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };
    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {
            PayTask alipay = new PayTask(getActivity());
            Map<String, String> result = alipay.payV2(wxPayBean.getData().getResponse(), true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = SDK_ALI_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };

    private void getZfbPay(OrderBean.DataBean dataBean) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.zfbPayOrder)
                .addParams("order_id", dataBean.getSso_sub_order_number())
                .addParams("uid", SPUtils.getInstance().getString("uid"))//totalPrice
                .addParams("total", "0.01")
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
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

}
