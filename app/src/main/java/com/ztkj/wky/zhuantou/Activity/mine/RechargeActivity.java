package com.ztkj.wky.zhuantou.Activity.mine;
/**
 * 作者：wky
 * 功能描述：充值
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.PayResult;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BandingBankCardBean;
import com.ztkj.wky.zhuantou.bean.WxPayBean;
import com.ztkj.wky.zhuantou.landing.SecurityCodeView3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.rechargeMoney)
    EditText rechargeMoney;
    @BindView(R.id.click_rl_choosePayType)
    RelativeLayout clickRlChoosePayType;
    @BindView(R.id.lin_parent)
    LinearLayout linParent;
    @BindView(R.id.img_rechargePayType)
    ImageView imgRechargePayType;
    @BindView(R.id.tv_rechargePayType)
    TextView tvRechargePayType;
    @BindView(R.id.btnPay)
    Button btnPay;
    private String TAG = "RechargeActivity";
    private static final int SDK_ALI_PAY_FLAG = 1;
    private CustomPopWindow mListPopWindow;
    public static CustomPopWindow mListPopWindow2;
    private Intent intent;
    private StringBuffer stringBuffer = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        layoutTitleTv.setText("充值");

        rechargeMoney.setFocusable(true);
        rechargeMoney.setFocusableInTouchMode(true);
        rechargeMoney.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @OnClick({R.id.layout_back, R.id.click_rl_choosePayType, R.id.btnPay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.click_rl_choosePayType:
                View contentView = LayoutInflater.from(this).inflate(R.layout.pp_layout_choose_paytype, null);
                //处理popWindow 显示内容
                handleListView(contentView);
                //创建并显示popWindow
                mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, 1000)//显示大小
                        .create()
                        .showAtLocation(linParent, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btnPay:
                if (rechargeMoney.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入充值金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                //支付
                switch (tvRechargePayType.getText().toString()) {
                    case "微信支付":
                        requestWx();
                        break;
                    case "支付宝":
                        requestAliPay();
                        break;
                    default:
                        View contentView2 = LayoutInflater.from(this).inflate(R.layout.layout_pp_payword_edit, null);
                        //处理popWindow 显示内容
                        handleListView2(contentView2);
                        //创建并显示popWindow
                        mListPopWindow2 = new CustomPopWindow.PopupWindowBuilder(this)
                                .setView(contentView2)
                                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                                .setBgDarkAlpha(0.7f) // 控制亮度
                                .size(ViewGroup.LayoutParams.MATCH_PARENT, 1400)//显示大小
                                .create()
                                .showAtLocation(linParent, Gravity.BOTTOM, 0, 0);
                        break;


                }
                break;
        }
    }

    //popwindow点击事件
    private void handleListView(View contentView) {

        RecyclerView pp_re_BankCard = contentView.findViewById(R.id.pp_re_BankCard);
        RelativeLayout addBankCardPayType = contentView.findViewById(R.id.addBankCardPayType);
        ImageView pp_finish = contentView.findViewById(R.id.pp_finish);

        pp_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPopWindow.dissmiss();
            }
        });

        //添加银行卡付款点击事件
        addBankCardPayType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RechargeActivity.this, BindingIdCard.class);
                intent.putExtra("intentTag", "0");
                startActivity(intent);
            }
        });

        //请求数据
        OkHttpUtils.post().url(Contents.BANKCARLIST)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                BandingBankCardBean bandingBankCardBean = GsonUtil.gsonToBean(response, BandingBankCardBean.class);
                List<BandingBankCardBean.DataBean> data = bandingBankCardBean.getData();

                ArrayList<String> payTypeList = new ArrayList<>(); //银行卡拼接字段
                ArrayList<String> payTypeListCardName = new ArrayList<>(); //银行卡拼接字段

                payTypeList.add("微信支付");
                payTypeList.add("支付宝");
                payTypeListCardName.add("微信支付");
                payTypeListCardName.add("支付宝");
                if (data.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        String card_number = data.get(i).getCard_number();
                        String substring = card_number.substring(card_number.length() - 4, card_number.length());
                        String str = data.get(i).getBank_name() + data.get(i).getCard_type() + "(" + substring + ")";
                        payTypeList.add(str);
                        payTypeListCardName.add(data.get(i).getBank_name());
                    }
                }

                pp_re_BankCard.setLayoutManager(new LinearLayoutManager(RechargeActivity.this));
                pp_re_BankCard.setAdapter(new PayTypeBankCarAdapter(RechargeActivity.this, payTypeList, payTypeListCardName));

            }
        });

    }

    //popwindow点击事件
    private void handleListView2(View contentView) {
        //密码框
        SecurityCodeView3 pp_edit_payWord = contentView.findViewById(R.id.pp_edit_payWord);
        //返回
        ImageView pp_payWordFinish = contentView.findViewById(R.id.pp_payWordFinish);
        //忘记密码
        TextView pp_forgetPayWord = contentView.findViewById(R.id.pp_forgetPayWord);

        pp_payWordFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPopWindow2.dissmiss();
            }
        });

        pp_forgetPayWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RechargeActivity.this, ForgetPayWordActivity.class);
                startActivity(intent);
            }
        });


    }


    /**
     * 微信支付
     *
     * @param
     */
    private void requestWx() {
        Log.e("uid", "requestWx: " + SPUtils.getInstance().getString("uid"));
        OkHttpUtils.post().url("https://api.zhuantoukj.com/birck/index.php/Home/Wxpay/dopay")
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("total", rechargeMoney.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                //微信支付首先通过接口 服务器返回预付单
                WxPayBean wxPayBean = GsonUtil.gsonToBean(response, WxPayBean.class);
                //通过IWXAPI 获取到其对象  然后将自己的应用注册到微信
                IWXAPI api = WXAPIFactory.createWXAPI(RechargeActivity.this, wxPayBean.getData().getAppid(), true);
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
            }
        });
    }

    /**
     * 支付宝支付
     *
     * @param
     */
    private void requestAliPay() {
        OkHttpUtils.post().url("https://api.zhuantoukj.com/birck/index.php/Home/Zfb/zfbPay")
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("money", "0.01")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                //支付宝支付请求所需的签名字符串
                final String orderInfo = response;
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(RechargeActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());
                        Message msg = new Message();
                        msg.what = SDK_ALI_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();

            }
        });


    }

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
                    Log.i(TAG, resultStatus);
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
                    Toast.makeText(RechargeActivity.this, result, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };

    //适配器
    class PayTypeBankCarAdapter extends RecyclerView.Adapter<PayTypeBankCarAdapter.ViewHolder> {
        private Context context;
        private ArrayList<String> payTypeList;
        private ArrayList<String> payTypeListCardName;

        public PayTypeBankCarAdapter(Context context, ArrayList<String> payTypeList, ArrayList<String> payTypeListCardName) {
            this.context = context;
            this.payTypeList = payTypeList;
            this.payTypeListCardName = payTypeListCardName;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_layout_pay_type_bankcard, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.payTypeBankCardName.setText(payTypeList.get(i));
//            switch (tvRechargePayType.getText().toString()) {
//                case "微信支付":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//
//                    break;
//                case "支付宝":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "招商银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "中国银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "浦发银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "中信银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "交通银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "建设银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "光大银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "工商银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "农业银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                case "邮政储蓄银行":
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//                default:
//                    viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
//                    break;
//            }

            //每一条的点击事件
            viewHolder.click_rl_payType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListPopWindow.dissmiss();
                    tvRechargePayType.setText(payTypeListCardName.get(i));
                    switch (payTypeListCardName.get(i)) {
                        case "微信支付":
                            Glide.with(context).load(R.mipmap.icon_wx).into(imgRechargePayType);
                            break;
                        case "支付宝":
                            Glide.with(context).load(R.mipmap.icon_zfb).into(imgRechargePayType);
                            break;
                        case "招商银行":
                            Glide.with(context).load(R.mipmap.icon_bank_zhaoshang).into(imgRechargePayType);
                            break;
                        case "中国银行":
                            Glide.with(context).load(R.mipmap.icon_china_bank).into(imgRechargePayType);
                            break;
                        case "浦发银行":
                            Glide.with(context).load(R.mipmap.icon_bank_pufa).into(imgRechargePayType);
                            break;
                        case "中信银行":
                            Glide.with(context).load(R.mipmap.icon_bank_zhongxin).into(imgRechargePayType);
                            break;
                        case "交通银行":
                            Glide.with(context).load(R.mipmap.icon_bank_jiaotong).into(imgRechargePayType);
                            break;
                        case "建设银行":
                            Glide.with(context).load(R.mipmap.icon_bank_jianshe).into(imgRechargePayType);
                            break;
                        case "光大银行":
                            Glide.with(context).load(R.mipmap.icon_bank_guangda).into(imgRechargePayType);
                            break;
                        case "工商银行":
                            Glide.with(context).load(R.mipmap.icon_bank_guangshang).into(imgRechargePayType);
                            break;
                        case "农业银行":
                            Glide.with(context).load(R.mipmap.icon_bank_nongye).into(imgRechargePayType);
                            break;
                        case "邮政储蓄银行":
                            Glide.with(context).load(R.mipmap.icon_bank_youzheng).into(imgRechargePayType);
                            break;
                        default:
                            Glide.with(context).load(R.mipmap.icon_bank_others).into(imgRechargePayType);
                            break;
                    }
                }


            });

            //每一条显示图片和文字
            switch (payTypeListCardName.get(i)) {
                case "微信支付":
                    Glide.with(context).load(R.mipmap.icon_wx).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "支付宝":
                    Glide.with(context).load(R.mipmap.icon_zfb).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "招商银行":
                    Glide.with(context).load(R.mipmap.icon_bank_zhaoshang).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "中国银行":
                    Glide.with(context).load(R.mipmap.icon_china_bank).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "浦发银行":
                    Glide.with(context).load(R.mipmap.icon_bank_pufa).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "中信银行":
                    Glide.with(context).load(R.mipmap.icon_bank_zhongxin).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "交通银行":
                    Glide.with(context).load(R.mipmap.icon_bank_jiaotong).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "建设银行":
                    Glide.with(context).load(R.mipmap.icon_bank_jianshe).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "光大银行":
                    Glide.with(context).load(R.mipmap.icon_bank_guangda).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "工商银行":
                    Glide.with(context).load(R.mipmap.icon_bank_guangshang).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "农业银行":
                    Glide.with(context).load(R.mipmap.icon_bank_nongye).into(viewHolder.payTypeBankCardLogo);
                    break;
                case "邮政储蓄银行":
                    Glide.with(context).load(R.mipmap.icon_bank_youzheng).into(viewHolder.payTypeBankCardLogo);
                    break;
                default:
                    Glide.with(context).load(R.mipmap.icon_bank_others).into(viewHolder.payTypeBankCardLogo);
                    break;
            }


        }


        @Override
        public int getItemCount() {
            return payTypeList.size();
        }

        class ViewHolder extends MyAdapter.ViewHolder {
            private ImageView payTypeBankCardLogo, payTypeBankCardRight;
            private TextView payTypeBankCardName;
            private RelativeLayout click_rl_payType;

            public ViewHolder(View itemView) {
                super(itemView);

                payTypeBankCardLogo = itemView.findViewById(R.id.payTypeBankLogo);
                payTypeBankCardRight = itemView.findViewById(R.id.payTypeBankCardRight);
                payTypeBankCardName = itemView.findViewById(R.id.payTypeBankName);
                click_rl_payType = itemView.findViewById(R.id.click_rl_payType);
            }
        }
    }


}



