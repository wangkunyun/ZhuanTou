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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.RefundActivity;
import com.ztkj.wky.zhuantou.MyUtils.MyGridView;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CouponAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopDetailAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopGuessAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopListAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopParamAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.GuessLikeBean;
import com.ztkj.wky.zhuantou.bean.ShopDetailBean;
import com.ztkj.wky.zhuantou.bean.ShopKeyBean;
import com.ztkj.wky.zhuantou.bean.ShopParamBean;
import com.ztkj.wky.zhuantou.bean.ShopSizeBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ztkj.wky.zhuantou.base.Contents.addCart;
import static com.ztkj.wky.zhuantou.base.Contents.getCoupon;
import static com.ztkj.wky.zhuantou.base.Contents.getShopParam;


public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.guess_like_list)
    MyGridView guess_like_list;
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
    @BindView(R.id.sc_name)
    TextView sc_name;
    @BindView(R.id.sc_present_price)
    TextView sc_present_price;
    @BindView(R.id.iv_cover)
    ImageView iv_cover;
    @BindView(R.id.collect_shop)
    TextView collect_shop;
    String uid;
    @BindView(R.id.cart_shop)
    TextView cart_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        shopDetailId = getIntent().getStringExtra("shopId");
        uid = SPUtils.getInstance().getString("uid");
        initData();
//        getShopSize();
        layout_back.setOnClickListener(this);
        add_shopping_cart.setOnClickListener(this);
        at_once_buy.setOnClickListener(this);
        rela_selelct_param.setOnClickListener(this);
        rela_select_size.setOnClickListener(this);
        get_cuopon.setOnClickListener(this);
        collect_shop.setOnClickListener(this);
        cart_shop.setOnClickListener(this);
    }

    int pageNum = 1;
    LiveShopGuessAdapter liveShopGuessAdapter;

    private void initData() {
//        guess_like_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this,2));
        liveShopGuessAdapter = new LiveShopGuessAdapter(ShopDetailActivity.this);
//        guess_like_list.setAdapter(liveShopDetailAdapter);
        guess_like_list.setAdapter(liveShopGuessAdapter);
        shop_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 3));
        liveShopListAdapter = new LiveShopListAdapter(ShopDetailActivity.this);
        shop_list.setAdapter(liveShopListAdapter);

        shop_detail_img.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
        liveShopDetailAdapter = new LiveShopDetailAdapter(ShopDetailActivity.this);
        shop_detail_img.setAdapter(liveShopDetailAdapter);

        shop_detail_img.setNestedScrollingEnabled(false);
        shop_list.setNestedScrollingEnabled(false);
//        guess_like_list.setNestedScrollingEnabled(false);
        shop_list.setHasFixedSize(true);
        shop_detail_img.setHasFixedSize(true);
//        guess_like_list.setHasFixedSize(true);
        if (shopDetailId != null) {
            initDetailData();

        }
    }


    ShopDetailBean shopDetailBean;

    private void initDetailData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.shopDetail)
                .addParams("sc_id", shopDetailId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.e("dfsf", response);
                            shopDetailBean = new Gson().fromJson(response, ShopDetailBean.class);
                            if (shopDetailBean.getErrno().equals("200")) {
                                setView();
                            } else {
                                ToastUtils.showShort(shopDetailBean.getErrmsg());
                            }
                        }
                    }


                });

    }

    String goodsName;
    String goodsDetail;

    private void setView() {
        if (shopDetailBean.getData().getSc_name() != null) {
            goodsName = shopDetailBean.getData().getSc_name();
            goodsDetail = shopDetailBean.getData().getSc_details();
            sc_name.setText(goodsName);
        }
        if (shopDetailBean.getData().getSc_present_price() != null) {
            sc_present_price.setText(shopDetailBean.getData().getSc_present_price());
        }
        if (shopDetailBean.getData().getSc_img() != null) {
            String img = shopDetailBean.getData().getSc_img();
            Glide.with(ShopDetailActivity.this).load(img).into(iv_cover);
        }
        if (shopDetailBean.getData().getSc_img() != null) {
            listShopDetail.add(shopDetailBean.getData().getSc_img());
            liveShopDetailAdapter.setData(listShopDetail);
        }
        if (shopDetailBean.getData().getStore() != null && shopDetailBean.getData().getStore().size() > 0) {
            liveShopListAdapter.setData(shopDetailBean.getData().getStore());
            liveShopListAdapter.notifyDataSetChanged();
        }
        if (goodsName != null && goodsDetail != null) {
            getKeys();
        }
        getShopSize();
        getShopParam();
    }

    List<ShopParamBean.DataBean> listParam = new ArrayList<>();

    private void getShopParam() {
        OkHttpUtils.post().url(Contents.SHOPBASE + getShopParam)
                .addParams("sp_commodity_id", shopDetailBean.getData().getSc_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            shopParamBean = new Gson().fromJson(response, ShopParamBean.class);
                            if(shopParamBean.getErrmsg().equals("200")){
                                listParam=shopParamBean.getData();
                            }

                        }
                    }
                });

    }

    ShopParamBean shopParamBean;
    ShopSizeBean skuBean;

    private void getShopSize() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.shopSize)
                .addParams("sc_sku_id", shopDetailBean.getData().getSc_sku_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            skuBean = new Gson().fromJson(Contents.ShopSkuJson, ShopSizeBean.class);
                            //默认展示第一条数据和拿到颜色集合
                            getShopPop(0);

                        }
                    }
                });
    }

    private void getShopPop(int postion) {
        if (skuBean.getData().getArr().size() != 0) {
            for (int i = 0; i < skuBean.getData().getArr().size(); i++) {
                ShopBeanSize shopBeanSize = new ShopBeanSize();
                shopBeanSize.setSk_id(skuBean.getData().getArr().get(i).getSk_id());
                shopBeanSize.setSk_name(skuBean.getData().getArr().get(i).getSk_name());
                shopBeanSize.setSk_stock(skuBean.getData().getArr().get(i).getSk_stock());
                shopBeanSize.setSk_price(skuBean.getData().getArr().get(i).getSk_price());
                listColor.add(shopBeanSize);
                if (postion == i) {
                    if (skuBean.getData().getArr().get(postion).getSk_arr().getArr().size() != 0) {
                        for (int j = 0; j < skuBean.getData().getArr().get(postion).getSk_arr().getArr().size(); j++) {
                            ShopBeanSize shopBeanSize1 = new ShopBeanSize();
                            shopBeanSize1.setSk_id(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_id());
                            shopBeanSize1.setSk_name(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_name());
                            shopBeanSize1.setSk_stock(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_stock());
                            shopBeanSize1.setSk_price(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_price());
                            listMateral.add(shopBeanSize1);
                            if (skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().size() != 0) {
                                for (int g = 0; g < skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().size(); g++) {
                                    if (j == 0) {
                                        ShopBeanSize shopBeanSize2 = new ShopBeanSize();
                                        shopBeanSize2.setSk_id(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_id());
                                        shopBeanSize2.setSk_name(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_name());
                                        shopBeanSize2.setSk_stock(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_stock());
                                        shopBeanSize2.setSk_price(skuBean.getData().getArr().get(postion).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_price());
                                        listSize.add(shopBeanSize2);
                                    }

                                }
                            }

                        }
                    }
                }

            }
        }
    }

    List<ShopBeanSize> listColor = new ArrayList<>();
    List<ShopBeanSize> listMateral = new ArrayList<>();
    List<ShopBeanSize> listSize = new ArrayList<>();
    ShopKeyBean shopKeyBean;


    public class ShopBeanSize {
        private String sk_stock;
        private String sk_price;
        private String sk_name;
        private String sk_id;


        public String getSk_stock() {
            return sk_stock;
        }

        public void setSk_stock(String sk_stock) {
            this.sk_stock = sk_stock;
        }

        public String getSk_price() {
            return sk_price;
        }

        public void setSk_price(String sk_price) {
            this.sk_price = sk_price;
        }

        public String getSk_name() {
            return sk_name;
        }

        public void setSk_name(String sk_name) {
            this.sk_name = sk_name;
        }

        public String getSk_id() {
            return sk_id;
        }

        public void setSk_id(String sk_id) {
            this.sk_id = sk_id;
        }
    }

    private void getKeys() {
        OkHttpUtils.get().url(Contents.getKey + goodsName + "&goods_details=" + goodsDetail)
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
                            }
                        }
                    }
                });

    }

    private void getGussLike() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.guessLike)
                .addParams("key_word", keyString)
                .addParams("page", String.valueOf(pageNum))
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
                                listGuess = guessLikeBean.getData();
                                setGuessData();
                            } else {
                                ToastUtils.showShort("解析失败");
                            }
                        }
                    }


                });
    }


    private void setGuessData() {
        if (listGuess != null && listGuess.size() > 0) {
            for (int i = 0; i < listGuess.size(); i++) {
                for (int j = 0; j < listGuess.get(i).size(); j++) {
                    listGuessBean.add(listGuess.get(i).get(j));
                }
            }
            liveShopGuessAdapter.setGuess(listGuessBean);
            liveShopGuessAdapter.notifyDataSetChanged();
        }
    }

    StringBuilder stringBuilder;
    String keyString;
    GuessLikeBean guessLikeBean;
    List<List<GuessLikeBean.DataBean>> listGuess = new ArrayList<>();
    List<GuessLikeBean.DataBean> listGuessBean = new ArrayList<>();

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

    List<String> listKey = new ArrayList<>();
    List<String> listShopDetail = new ArrayList<>();

    public static void start(Context context, String shopDeitalId) {
        Intent starter = new Intent(context, ShopDetailActivity.class);
        starter.putExtra("shopId", shopDeitalId);
        context.startActivity(starter);
    }

    private String shopDetailId;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (uid != null) {
            recorder();
        }


    }

    private void recorder() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.recorderUser)
                .addParams("uid", uid)
                .addParams("key_word", keyString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("keyString", e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("keyString", response);
                    }
                });
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
        if(shopParamAdapter!=null){
            shopParamAdapter.setData(listParam);
            shopParamAdapter.notifyDataSetChanged();
        }
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

    TextView numTv;
    Button btnConfirm;

    private void popuSize() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_size, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        tv_select_size = contentView.findViewById(R.id.tv_select_size);
        lvTagColor = contentView.findViewById(R.id.flowLayout);
        lvTagMatarel = contentView.findViewById(R.id.flowLayout_matarel);
        lvTagSize = contentView.findViewById(R.id.flowLayout_size);
        btnConfirm = contentView.findViewById(R.id.btn_confirm);
        RelativeLayout reduce_shop_cart = contentView.findViewById(R.id.reduce_shop_cart);
        RelativeLayout add_shop_cart = contentView.findViewById(R.id.add_shop_cart);
        numTv = contentView.findViewById(R.id.num);
        initColorAdater();
        initMateralAdater();
        initSizeAdater();
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        reduce_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(numTv.getText().toString());
                if (num > 1) {
                    num--;
                }
                numTv.setText(String.valueOf(num));
            }
        });
        add_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(numTv.getText().toString());
                num++;
                if (num > Integer.parseInt(stock)) {
                    ToastUtils.showShort("库存不足");
                    return;
                }
                numTv.setText(String.valueOf(num));
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tagSelect == -1 || skuMiddlename == null || skuLast == null) {
                    ToastUtils.showShort("请选择商品属性");
                } else {
//                    num = Integer.parseInt(numTv.getText().toString());
//                    if (num > Integer.parseInt(stock)) {
//                        ToastUtils.showShort("库存不足");
//                        return;
//                    }
                    backgroundAlpha(1f);
                    window.dismiss();
                }
            }
        });
    }

    int num;
    TextView tv_select_size;
    TagFlowLayout lvTagColor, lvTagMatarel, lvTagSize;
    TagAdapter tagAdapter, tagMateralAdapter, tagSizeAdapter;

    private void initColorAdater() {
        if (tagAdapter != null) {
            tagAdapter.setSelectedList(tagSelect);
        } else {
            tagAdapter = new TagAdapter<ShopBeanSize>(listColor) {
                @Override
                public View getView(FlowLayout parent, int position, ShopBeanSize shopBeanSize) {
                    View view = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.item_conlust_layout, lvTagColor, false);
                    TextView tv = view.findViewById(R.id.content);
                    tv.setText(shopBeanSize.getSk_name());
                    return tv;
                }

                @Override
                public void onSelected(int position, View view) {
                    super.onSelected(position, view);
                    View views = view;
                    tagSelect = position;
                    TextView tv = views.findViewById(R.id.content);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfourseklect));
                    notifyAfapter();
                    String sizeId = listColor.get(position).getSk_id();
                    getForSize(sizeId);
                }


                @Override
                public void unSelected(int position, View view) {
                    super.unSelected(position, view);
                    View views = view;
                    TextView tv = views.findViewById(R.id.content);
                    tv.setTextColor(getResources().getColor(R.color.t2));
                    tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfours));
                }
            };
            lvTagColor.setAdapter(tagAdapter);
        }


    }

    private void notifyAfapter() {
        listMateral.clear();
        listSize.clear();
        tagMateralAdapter = null;
        tagSizeAdapter = null;
        tagLastSelect = 0;
        tagSizeSelect = 0;
        skuLast = null;
        stock = null;
        skuMiddlename = null;
        getShopPop(tagSelect);
        initMateralAdater();
        initSizeAdater();

    }

    private void initMateralAdater() {
        tagMateralAdapter = new TagAdapter<ShopBeanSize>(listMateral) {
            @Override
            public View getView(FlowLayout parent, int position, ShopBeanSize shopBeanSize) {
                View view = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.item_conlust_layout, lvTagColor, false);
                TextView tv = view.findViewById(R.id.content);
                tv.setText(shopBeanSize.getSk_name());
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                tagSizeSelect = position;
                View views = view;
                TextView tv = views.findViewById(R.id.content);
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfourseklect));
                String sizeId = listMateral.get(position).getSk_id();
                getForSize(sizeId);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                View views = view;
                TextView tv = views.findViewById(R.id.content);
                tv.setTextColor(getResources().getColor(R.color.t2));
                tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfours));
            }
        };
        lvTagMatarel.setAdapter(tagMateralAdapter);
    }

    private void initSizeAdater() {
        tagSizeAdapter = new TagAdapter<ShopBeanSize>(listSize) {
            @Override
            public View getView(FlowLayout parent, int position, ShopBeanSize shopBeanSize) {
                View view = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.item_conlust_layout, lvTagColor, false);
                TextView tv = view.findViewById(R.id.content);
                tv.setText(shopBeanSize.getSk_name());
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                tagLastSelect = position;
                View views = view;
                TextView tv = views.findViewById(R.id.content);
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfourseklect));
                String sizeId = listSize.get(position).getSk_id();
                getForSize(sizeId);

            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                View views = view;
                TextView tv = views.findViewById(R.id.content);
                tv.setTextColor(getResources().getColor(R.color.t2));
                tv.setBackground(getResources().getDrawable(R.drawable.yuanjiaobtnfours));
            }
        };
        lvTagSize.setAdapter(tagSizeAdapter);
    }


    private void getForSize(String sizeId) {
        if (skuBean.getData().getArr().size() != 0) {
            for (int i = 0; i < skuBean.getData().getArr().size(); i++) {
                if (skuBean.getData().getArr().get(i).getSk_arr().getArr().size() != 0) {
                    if (sizeId.equals(skuBean.getData().getArr().get(i).getSk_id())) {
                        tv_select_size.setText("已选  " + skuBean.getData().getArr().get(i).getSk_name() + "   " + skuBean.getData().getArr().get(i).getSk_stock());
                        return;
                    }
                    for (int j = 0; j < skuBean.getData().getArr().get(i).getSk_arr().getArr().size(); j++) {
                        if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_id().equals(sizeId)) {
                            String skuLastName = skuBean.getData().getArr().get(tagSelect).getSk_name();
                            skuMiddlename = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_name();
                            if (skuLast == null) {
                                stock = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_stock();
                                if (stock != null) {
                                    tv_select_size.setText("已选  " + skuLastName + "  " + skuMiddlename + "   " + stock);
                                }
                            } else {
                                stock = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(tagLastSelect).getSk_stock();
                                if (stock != null) {
                                    tv_select_size.setText("已选  " + "  " + skuLastName + "  " + skuMiddlename + "   " + skuLast + "   " + stock);
                                }
                            }
                            return;
                        }
                        if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().size() != 0) {
                            for (int g = 0; g < skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().size(); g++) {
                                if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_id().equals(sizeId)) {
                                    String skuLastLastName = skuBean.getData().getArr().get(tagSelect).getSk_name();
                                    String skuLastName = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_name();
                                    skuLast = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_name();
                                    stock = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_stock();
                                    tv_select_size.setText("已选  " + skuLastLastName + "   " + skuLastName + "  " + skuLast + "  " + stock);
                                }
                            }
                        }

                    }
                }


            }
        }
        if (Integer.parseInt(stock) > 0) {
            numTv.setText(String.valueOf(1));
            btnConfirm.setEnabled(true);
            btnConfirm.setText("确定");
        } else {
            btnConfirm.setEnabled(false);
            btnConfirm.setText("库存不足");
        }


    }

    int tagSelect = -1;
    int tagSizeSelect = -1;
    int tagLastSelect = -1;
    String skuLast, stock;
    String skuMiddlename;

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
//                ConfirmOrderActivity.start(ShopDetailActivity.this);
                break;
            case R.id.rela_selelct_param:
                if(listParam!=null&&listParam.size()>0){
                    popuinit();
                }else{
                    ToastUtils.showShort("暂无数据");
                }

                break;
            case R.id.rela_select_size:
                popuSize();
                break;
            case R.id.get_cuopon:
                list = shopDetailBean.getData().getCoupon();
                if(list!=null&&list.size()>0){
                    popuCoupon();
                }else{
                    ToastUtils.showShort("暂无优惠卷");
                }

//                RefundActivity.start(ShopDetailActivity.this);
                break;
            case R.id.collect_shop:
                if (uid != null) {
                    collectShop();
                    deleteShop();
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }

                break;
            case R.id.cart_shop:
                addCart();
                break;
        }
    }

    String sku_name = "蓝黑" + " " + "不锈钢" + " " + "5.6英寸";

    private void addCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + addCart)
                .addParams("user_id", uid)
                .addParams("ssc_name", shopDetailBean.getData().getSc_name())
                .addParams("ssc_number", "2")
                .addParams("ssc_unit_price", "150")
                .addParams("ssc_sku_name", sku_name)
                .addParams("ssc_sku_id", "1,9,10,13")
                .addParams("ssc_sc_id", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("fsadfas", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("fsadfas", response);
                    }
                });
    }

    //sc_id 收藏夹列表的id
    private void deleteShop() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.deleteShop)
                .addParams("sc_user_id", uid)
                .addParams("sc_id", shopDetailId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        collect_shop.setText("收藏");
                    }
                });

    }

    private void collectShop() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.collectShop)
                .addParams("uid", uid)
                .addParams("sc_commodity_id", shopDetailId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        collect_shop.setText("已收藏");
                    }
                });

    }

    List<ShopDetailBean.DataBean.CouponBean> list = new ArrayList<>();

    private void popuCoupon() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_coupon, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        CouponAdapter shopParamAdapter = new CouponAdapter(ShopDetailActivity.this, list);
        lv.setAdapter(shopParamAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).getSc_id() != null) {
                    String scid = list.get(i).getSc_id();
                    if (uid != null && scid != null) {
                        getCoupon(scid);
                    }

                }

            }
        });
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

    private void getCoupon(String id) {
        OkHttpUtils.post().url(Contents.SHOPBASE + getCoupon)
                .addParams("sc_id", id)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("Dfsaf", response);
                    }
                });
    }
}
