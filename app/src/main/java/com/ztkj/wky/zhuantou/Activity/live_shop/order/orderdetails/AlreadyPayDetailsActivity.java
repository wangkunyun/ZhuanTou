package com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails;

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
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.GuessLikeBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.OrderDetailsBean;
import com.ztkj.wky.zhuantou.bean.ShopKeyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlreadyPayDetailsActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
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
    @BindView(R.id.tv_clickRefundAll)
    TextView tvClickRefundAll;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_pay_details);
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
        request(orderState, orderNum);
        getKeys();
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
                                reViewGuess.setLayoutManager(new GridLayoutManager(AlreadyPayDetailsActivity.this, 2));
                                reViewGuess.setAdapter(new GuessAdapter());
                            } else {
                                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
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
                OrderDetailsBean orderDetailsBean = GsonUtil.gsonToBean(response, OrderDetailsBean.class);
                if (orderDetailsBean.getErrno().equals("200")) {
                    data = orderDetailsBean.getData();
                    arr = data.getArr();
                    tvName.setText(data.getSra_username());
                    tvPhone.setText(data.getSra_phone());
                    tvAddress.setText(data.getSra_address());
                    tvStoreName.setText(data.getSs_name());
                    if (!data.getSs_logo().equals("0")) {
                        Glide.with(AlreadyPayDetailsActivity.this).load(data.getSs_logo()).into(imgStoreHead);
                    }
                    tvOrderNum.setText("订单编号: " + data.getSso_sub_order_number());
                    tvAddOrderTime.setText("下单时间: " + data.getSo_addtime());

                    Adapter adapter = new Adapter();
                    reView.setLayoutManager(new LinearLayoutManager(AlreadyPayDetailsActivity.this));
                    reView.setAdapter(adapter);
                }
            }
        });

    }

    public static void start(Context context, String orderState, String orderNum, OrderBean.DataBean dataBean) {
        Intent starter = new Intent(context, AlreadyPayDetailsActivity.class);
        starter.putExtra("orderState", orderState);
        starter.putExtra("orderNum", orderNum);
        starter.putExtra("dataBean", dataBean);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.tv_clickCopy, R.id.tv_clickRefundAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_clickCopy:
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(data.getSso_sub_order_number());
                Toast.makeText(this, "复制成功!", Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_clickRefundAll:
                RefundActivity.start(AlreadyPayDetailsActivity.this, dataBean);
                break;
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(AlreadyPayDetailsActivity.this).inflate(R.layout.item_layout_order_details, viewGroup, false);

            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(AlreadyPayDetailsActivity.this).load(arr.get(i).getSc_img()).into(viewHolder.item_imgOrderDetailsPic);
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
                    RefundActivity.start(AlreadyPayDetailsActivity.this, dataBean);
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
            View view = LayoutInflater.from(AlreadyPayDetailsActivity.this).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(AlreadyPayDetailsActivity.this).load(GuessData.get(i).getSc_img()).into(viewHolder.img_shopPic);
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
}
