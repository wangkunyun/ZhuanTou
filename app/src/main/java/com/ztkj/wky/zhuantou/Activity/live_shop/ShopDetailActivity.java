package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.store.ShopStoreActivity;
import com.ztkj.wky.zhuantou.MainActivity;
import com.ztkj.wky.zhuantou.MyUtils.HeadRefreshView;
import com.ztkj.wky.zhuantou.MyUtils.LoadMoreView;
import com.ztkj.wky.zhuantou.MyUtils.ObservableScrollView;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CouponAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopDetailAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopListAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopColorAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopMateralAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopParamAdapter;
import com.ztkj.wky.zhuantou.adapter.ShopSizeAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BaseStatusBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.PunchInBean;
import com.ztkj.wky.zhuantou.bean.ShopDetailBean;
import com.ztkj.wky.zhuantou.bean.ShopHomeBean;
import com.ztkj.wky.zhuantou.bean.ShopKeyBean;
import com.ztkj.wky.zhuantou.bean.ShopParamBean;
import com.ztkj.wky.zhuantou.bean.ShopSizeBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ztkj.wky.zhuantou.base.Contents.addCart;
import static com.ztkj.wky.zhuantou.base.Contents.getCoupon;
import static com.ztkj.wky.zhuantou.base.Contents.getShopParam;
import static com.ztkj.wky.zhuantou.base.Contents.recorderUser;


public class ShopDetailActivity extends AppCompatActivity implements View.OnClickListener, BaseRefreshListener, ObservableScrollView.ScrollViewListener {

    @BindView(R.id.guess_like_list)
    RecyclerView guess_like_list;
    @BindView(R.id.shop_list)
    RecyclerView shop_list;
    LiveShopListAdapter liveShopListAdapter;
    @BindView(R.id.shop_detail_img)
    WebView webView;
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
    @BindView(R.id.collect_shop)
    TextView collect_shop;
    String uid;
    @BindView(R.id.cart_shop)
    TextView cart_shop;
    @BindView(R.id.shop)
    TextView shop;
    @BindView(R.id.tv_origin_price)
    TextView tv_origin_price;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.iv_shop)
    CircleImageView iv_shop;
    @BindView(R.id.layout_title_tv)
    TextView layout_title_tv;
    @BindView(R.id.tv_msg_num)
    TextView tv_msg_num;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    @BindView(R.id.n1_cf)
    PullToRefreshLayout n1Cf;
    @BindView(R.id.rela_enter_shop)
    RelativeLayout rela_enter_shop;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        setrestoreKey();
        shopDetailId = getIntent().getStringExtra("shopId");
        uid = SPUtils.getInstance().getString("uid");
        layout_title_tv.setText("商品详情");
        more.setBackground(getResources().getDrawable(R.mipmap.icon_person_sangedian));
        initWebview();
        initData();
        layout_back.setOnClickListener(this);
        add_shopping_cart.setOnClickListener(this);
        at_once_buy.setOnClickListener(this);
        rela_selelct_param.setOnClickListener(this);
        rela_select_size.setOnClickListener(this);
        get_cuopon.setOnClickListener(this);
        collect_shop.setOnClickListener(this);
        cart_shop.setOnClickListener(this);
        shop.setOnClickListener(this);
        more.setOnClickListener(this);
        iv_top.setOnClickListener(this);
        rela_enter_shop.setOnClickListener(this);
        n1Cf.setHeaderView(new HeadRefreshView(ShopDetailActivity.this));
        n1Cf.setFooterView(new LoadMoreView(ShopDetailActivity.this));
        n1Cf.setRefreshListener(ShopDetailActivity.this);
        scrollview.setScrollViewListener(this);

    }

    private void initWebview() {
        WebSettings wetSettings = webView.getSettings();
        wetSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wetSettings.setJavaScriptEnabled(true);
        wetSettings.setDomStorageEnabled(true);
        webView.setDrawingCacheEnabled(true);
        webView.clearCache(true);
        WebStorage.getInstance().deleteAllData();
    }

    int pageNum = 1;

    private void initData() {
        shop_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 3));
        liveShopListAdapter = new LiveShopListAdapter(ShopDetailActivity.this);
        shop_list.setAdapter(liveShopListAdapter);
//        shop_detail_img.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
//        liveShopDetailAdapter = new LiveShopDetailAdapter(ShopDetailActivity.this);
//        shop_detail_img.setAdapter(liveShopDetailAdapter);
//        shop_detail_img.setNestedScrollingEnabled(false);
        shop_list.setNestedScrollingEnabled(false);
        shop_list.setHasFixedSize(true);
//        shop_detail_img.setHasFixedSize(true);
        guess_like_list.setHasFixedSize(true);
        guess_like_list.setNestedScrollingEnabled(false);
        if (shopDetailId != null) {
            initDetailData();
        }

    }

    ShopDetailBean shopDetailBean;

    private void initDetailData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.shopDetail)
                .addParams("uid", uid)
                .addParams("sc_id", shopDetailId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            shopDetailBean = new Gson().fromJson(response, ShopDetailBean.class);
                            if (shopDetailBean != null) {
                                if (shopDetailBean.getErrno().equals("200")) {
                                    setView();
                                } else {
                                    ToastUtils.showShort(shopDetailBean.getErrmsg());
                                }
                            }
                        }
                    }
                });

    }

    String goodsName;
    String goodsDetail;
    @BindView(R.id.shopdetail_banner)
    Banner shopdetail_banner;
    ImageView iv_cover;
    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    List<ShopDetailBean.DataBean.StoreBean> storeBeanList;

    private void setView() {
        if (shopDetailBean.getData().getSs_logo() != null) {
            Glide.with(ShopDetailActivity.this).load(shopDetailBean.getData().getSs_logo()).into(iv_shop);
        }
        if (shopDetailBean.getData().getSs_name() != null) {
            tv_shop_name.setText(shopDetailBean.getData().getSs_name());
        }

        if (shopDetailBean.getData().getSc_name() != null) {
            goodsName = shopDetailBean.getData().getSc_name();
            goodsDetail = shopDetailBean.getData().getSc_details();
            sc_name.setText(goodsName);
        }
        if (shopDetailBean.getData().getSc_present_price() != null) {
            sc_present_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_present_price());
        }
        if (shopDetailBean.getData().getSc_original_price() != null) {
            tv_origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tv_origin_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_original_price());
        }

//  getSc_original_price      if (shopDetailBean.getData().getSc_img() != null) {
//            listShopDetail.add(shopDetailBean.getData().getSc_img());
//            liveShopDetailAdapter.setData(listShopDetail);
//        }
        if (shopDetailBean.getData().getStore() != null && shopDetailBean.getData().getStore().size() > 0) {
            storeBeanList = shopDetailBean.getData().getStore();
            liveShopListAdapter.setData(storeBeanList);
            liveShopListAdapter.notifyDataSetChanged();
        }
        if (goodsName != null && goodsDetail != null) {
            getKeys();
        }
        if (shopDetailBean.getData().getSc_wheel_planting() != null) {
            listBanner = shopDetailBean.getData().getSc_wheel_planting().split(",");
            for (int i = 0; i < listBanner.length; i++) {
                listImages.add(listBanner[i]);
            }
            if (listImages.size() > 0) {
                setBannerDetial();
            }
        }
        if (shopDetailBean.getData().getJudgementCollection().equals("0")) {
            collect_shop.setSelected(false);
        } else {
            collect_shop.setSelected(true);
        }
        getShopSize();
        getShopParam();
        if (!uid.equals("")) {
            h5Store = Contents.shopDetailH5 + "?uid=" + uid + "&sc_id=" + shopDetailBean.getData().getSc_id();
            webView.loadUrl(h5Store);
            recorderUser();
        }
    }

    String h5Store;

    @Override
    public void onResume() {
        super.onResume();
        if (!uid.equals("")) {
            getCart();
        }

    }

    OrderBean OrderBean;

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
                            if (OrderBean.getData() != null && OrderBean.getData().size() > 0 && OrderBean.getData().get(0) != null) {
                                if (OrderBean.getData().get(0).getArr() != null) {
                                    Contents.numCart = OrderBean.getData().get(0).getArr().size();
                                    tv_msg_num.setVisibility(View.VISIBLE);
                                    tv_msg_num.setText(String.valueOf(Contents.numCart));
                                } else {
                                    tv_msg_num.setVisibility(View.GONE);
                                }

                            } else {
                                tv_msg_num.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }


    private void recorderUser() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.trajectoryAdd)
                .addParams("sf_user_id", uid)
                .addParams("sf_commodity_id", shopDetailId)
                .addParams("sf_trade_price", shopDetailBean.getData().getSc_present_price())
                .addParams("sf_trade_img", shopDetailBean.getData().getSc_img())
                .addParams("sf_trade_name", shopDetailBean.getData().getSc_name())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                    }
                });
    }

    String[] listBanner;
    List<String> listImages = new ArrayList<>();

    private void setBannerDetial() {
        shopdetail_banner.setIndicatorGravity(BannerConfig.CENTER);
        shopdetail_banner.setDelayTime(5000);
        shopdetail_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        shopdetail_banner.setImageLoader(new GlideImageLoader());
        shopdetail_banner.setImages(listImages);
        shopdetail_banner.start();
    }

    @Override
    public void refresh() {
        n1Cf.finishRefresh();
    }

    @Override
    public void loadMore() {
        n1Cf.finishLoadMore();
    }

    @Override
    public void onScrollChanged(ObservableScrollView observableScrollView, int x, int y, int oldx, int oldy) {
        Log.e("sdfa", y + "");
        if (y > 500) {
            iv_top.setVisibility(View.VISIBLE);
        } else {
            if (y < 0 || y == 0) {
                iv_top.setVisibility(View.INVISIBLE);
            }
        }

    }


    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    List<ShopParamBean.DataBean> listParam = new ArrayList<>();

    private void getShopParam() {
        OkHttpUtils.post().url(Contents.SHOPBASE + getShopParam)
                .addParams("sp_commodity_id", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            shopParamBean = new Gson().fromJson(response, ShopParamBean.class);
                            if (shopParamBean.getErrno().equals("200")) {
                                listParam = shopParamBean.getData();
                            }
                        }
                    }
                });
    }

    ShopParamBean shopParamBean;
    ShopSizeBean skuBean;

    private void getShopSize() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getSku)
                .addParams("sk_id", shopDetailBean.getData().getSc_sku_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            skuBean = new Gson().fromJson(response, ShopSizeBean.class);
                            //默认展示第一条数据和拿到颜色集合
                            getShopPop(0);

                        }
                    }
                });
    }

    private void getShopPop(int postion) {
        if (skuBean.getData().getArr().size() != 0) {
            for (int i = 0; i < skuBean.getData().getArr().size(); i++) {
                if (listColor.size() != skuBean.getData().getArr().size()) {
                    ShopBeanSize shopBeanSize = new ShopBeanSize();
                    shopBeanSize.setSk_id(skuBean.getData().getArr().get(i).getSk_id());
                    shopBeanSize.setSk_name(skuBean.getData().getArr().get(i).getSk_name());
                    shopBeanSize.setSk_stock(skuBean.getData().getArr().get(i).getSk_stock());
                    shopBeanSize.setSk_price(skuBean.getData().getArr().get(i).getSk_price());
                    listColor.add(shopBeanSize);
                }
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
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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
        OkHttpUtils.post().url(Contents.getKey)
                .addParams("goods_name", goodsName)
                .addParams("goods_details", goodsDetail)
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
                                if (uid != null) {
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
                            guessLikeBean = new Gson().fromJson(response, ShopHomeBean.class);
                            if (guessLikeBean != null) {
                                listGuessBean = guessLikeBean.getData();
                                setGuessData();
                            } else {
                                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                                ToastUtils.showShort("解析失败");
                            }
                        }
                    }


                });
    }


    private void setGuessData() {
        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(ShopDetailActivity.this, listGuessBean);
        guess_like_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 2));
        guess_like_list.setAdapter(liveShopAdapter);
    }

    StringBuilder stringBuilder;
    String keyString;
    ShopHomeBean guessLikeBean;
    List<ShopHomeBean.DataBean> listGuessBean = new ArrayList<>();

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


    private void recorder() {
        OkHttpUtils.post().url(Contents.SHOPBASE + recorderUser)
                .addParams("uid", uid)
                .addParams("key_word", keyString)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                    }
                });
    }


    private void popuinit() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_param, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        TextView pp1_btn = contentView.findViewById(R.id.pp1_btn);
        ShopParamAdapter shopParamAdapter = new ShopParamAdapter(ShopDetailActivity.this);
        lv.setAdapter(shopParamAdapter);
        if (shopParamAdapter != null) {
            if (listParam != null) {
                shopParamAdapter.setData(listParam);
                shopParamAdapter.notifyDataSetChanged();
            }
        }
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowDissMiss();
            }
        });
    }

    PopupWindow window;

    private void setViewDow(View view, View rootview) {
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                typeWay = 0;
//                if (shopColorAdapter != null) {
//                    closePopu();
//                }
                windowDissMiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    TextView numTv;
    TextView btnConfirm, add_pop_shopping_cart, btn_at_once;
    TextView origin_price;
    ShopMateralAdapter shopMateralAdapter;
    ShopSizeAdapter shopSizeAdapter;
    ShopColorAdapter shopColorAdapter;
    int typeWay;

    private void popuSize() {
        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.pp_shop_size, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        btn_at_once = contentView.findViewById(R.id.btn_at_once);
        add_pop_shopping_cart = contentView.findViewById(R.id.add_pop_shopping_cart);
        close = contentView.findViewById(R.id.close);
        tv_select_size = contentView.findViewById(R.id.tv_select_size);
        lvTagColor = contentView.findViewById(R.id.flowLayout);
        lvTagMatarel = contentView.findViewById(R.id.flowLayout_matarel);
        lvTagSize = contentView.findViewById(R.id.flowLayout_size);
        tv_price = contentView.findViewById(R.id.tv_price);
        btnConfirm = contentView.findViewById(R.id.btn_confirm);
        iv_cover = contentView.findViewById(R.id.iv_cover);
        origin_price = contentView.findViewById(R.id.origin_price);
        RelativeLayout reduce_shop_cart = contentView.findViewById(R.id.reduce_shop_cart);
        RelativeLayout add_shop_cart = contentView.findViewById(R.id.add_shop_cart);
        numTv = contentView.findViewById(R.id.num);
        shopColorAdapter = new ShopColorAdapter(ShopDetailActivity.this);
        shopSizeAdapter = new ShopSizeAdapter(ShopDetailActivity.this);
        shopMateralAdapter = new ShopMateralAdapter(ShopDetailActivity.this);
        lvTagColor.setAdapter(shopColorAdapter);
        lvTagMatarel.setAdapter(shopMateralAdapter);
        lvTagSize.setAdapter(shopSizeAdapter);
        if (typeWay == 0) {
            add_pop_shopping_cart.setVisibility(View.VISIBLE);
            btn_at_once.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
        } else {
            add_pop_shopping_cart.setVisibility(View.GONE);
            btn_at_once.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.VISIBLE);
        }
        Glide.with(ShopDetailActivity.this).load(shopDetailBean.getData().getSc_img()).into(iv_cover);
        if (shopDetailBean.getData().getSc_present_price() != null) {
            tv_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_present_price());
            origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            origin_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_original_price());
        }
        if (sku_name != null) {
            tv_select_size.setText(alreadySelect + sku_name);
        }
        lvTagColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int g = 0; g < listColor.size(); g++) {
                    listColor.get(g).setSelect(false);
                }
                listColor.get(i).setSelect(true);
                shopColorAdapter.setData(listColor);
                tagSelect = i;
//                notifyAfapter();
                String sizeId = listColor.get(i).getSk_id();
                getForSize(sizeId);
            }
        });
        lvTagMatarel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int g = 0; g < listMateral.size(); g++) {
                    listMateral.get(g).setSelect(false);
                }
                listMateral.get(i).setSelect(true);
                shopMateralAdapter.setData(listMateral);
                tagSizeSelect = i;
                String sizeId = listMateral.get(i).getSk_id();
                getForSize(sizeId);
            }
        });
        lvTagSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int g = 0; g < listSize.size(); g++) {
                    listSize.get(g).setSelect(false);
                }
                listSize.get(i).setSelect(true);
                shopSizeAdapter.setData(listSize);
                tagLastSelect = i;
                String sizeId = listSize.get(i).getSk_id();
                getForSize(sizeId);
            }
        });
        initColorAdater();
        initMateralAdater();
        initSizeAdater();
        View rootview = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        reduce_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stock != null) {
                    num = Integer.parseInt(numTv.getText().toString());
                    if (num > 1) {
                        num--;
                    }
                    numTv.setText(String.valueOf(num));
                }

            }
        });
        add_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stock != null) {
                    num = Integer.parseInt(numTv.getText().toString());
                    num++;
                    if (num > Integer.parseInt(stock)) {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort("库存不足");
                        return;
                    }
                    numTv.setText(String.valueOf(num));
                } else {
                    ToastUtils.showShort("请选择商品属性");
                }
            }
        });
        add_pop_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(numTv.getText().toString());
                if (ssc_sku_id == null || skuMiddlename == null || skuLast == null || sku_name == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请选择商品属性");
                    return;
                } else {
                    if (stock != null) {
                        if (num > Integer.parseInt(stock)) {
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showShort("库存不足");
                            return;
                        }
                        if (tv_select_size.getText().toString() != null) {
                            select_special_size.setText(tv_select_size.getText().toString());
                        }
                    }
                }
                addCart();
                windowDissMiss();
            }
        });
        btn_at_once.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = Integer.parseInt(numTv.getText().toString());
                if (ssc_sku_id == null || skuMiddlename == null || skuLast == null || sku_name == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请选择商品属性");
                    return;
                } else {
                    if (stock != null) {
                        if (num > Integer.parseInt(stock)) {
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showShort("库存不足");
                            return;
                        }
                        if (tv_select_size.getText().toString() != null) {
                            select_special_size.setText(tv_select_size.getText().toString());
                        }
                    }
                }
                setIntentConfim();
                windowDissMiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = Integer.parseInt(numTv.getText().toString());
                if (ssc_sku_id == null || skuMiddlename == null || skuLast == null || sku_name == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请选择商品属性");
                    return;
                } else {
                    if (stock != null) {
                        if (num > Integer.parseInt(stock)) {
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showShort("库存不足");
                            return;
                        }
                        if (tv_select_size.getText().toString() != null) {
                            select_special_size.setText(tv_select_size.getText().toString());
                        }
                    }
                }
                switch (typeWay) {
                    case 1:
                        addCart();
                        break;
                    case 2:
                        setIntentConfim();
                        break;
                }
                windowDissMiss();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                closePopu();
                windowDissMiss();
            }
        });
    }

    private void setIntentConfim() {
        if (sku_name != null && sku_price != null && ssc_sku_id != null) {
//            ssc_sku_id = shopDetailBean.getData().getSc_sku_id() + "," + ssc_sku_id;
//            ssc_sku_id =ssc_sku_id;
            OrderBean.DataBean shopCart = new OrderBean.DataBean();
            shopCart.setSs_logo(shopDetailBean.getData().getSc_img());
            shopCart.setSs_name(shopDetailBean.getData().getSs_name());
            listShopCart.add(shopCart);
            OrderBean.DataBean.ArrBean arrBean = new OrderBean.DataBean.ArrBean();
            arrBean.setSsc_id(shopDetailBean.getData().getSc_id());
            arrBean.setSsc_name(shopDetailBean.getData().getSc_name());
            arrBean.setSsc_sku_name(sku_name);
            arrBean.setSsc_sku_id(ssc_sku_id);
            arrBean.setSsc_store_id(shopDetailBean.getData().getSc_store_id());
            arrBean.setSsc_number(String.valueOf(num));
            arrBean.setSsc_unit_price(sku_price);
            arrBean.setSc_img(shopDetailBean.getData().getSc_img());
            arrBeanList.add(arrBean);
            listShopCart.get(0).setArr(arrBeanList);
        }
        if (num > 0 && sku_price != null) {
            String totalPrice = String.valueOf(num * Double.parseDouble(sku_price));
            ConfirmOrderActivity.start(ShopDetailActivity.this, listShopCart, totalPrice, 1);
            ssc_sku_id = null;
            sku_name = null;
            sku_price = null;
            num = 0;
            arrBeanList.clear();
            listShopCart.clear();
            select_special_size.setText("尺码 颜色等");
        }
        closePopu();
    }

    @BindView(R.id.select_special_size)
    TextView select_special_size;

    private void closePopu() {
        if (listColor != null && listColor.size() > 0) {
            listColor.clear();
        }
        tagSelect = 0;
        notifyAfapter();
        windowDissMiss();
    }

    private void setrestoreKey() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.lanse)     //状态栏颜色，不写默认透明色
//                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
//                .navigationBarColor(R.color.baise) //导航栏颜色，不写默认黑色
                .fullScreen(true)
                .init();
    }

    int num;
    TextView tv_select_size, tv_price;
    GridView lvTagColor, lvTagMatarel, lvTagSize;
    ImageView close;

    private void initColorAdater() {
        shopColorAdapter.setData(listColor);
        shopColorAdapter.notifyDataSetChanged();
    }

    private void notifyAfapter() {
        listMateral.clear();
        listSize.clear();
        if (tagSelect > -1) {
            tagLastSelect = 0;
            tagSizeSelect = 0;
        } else {
            tagLastSelect = -1;
            tagSizeSelect = -1;
        }
        skuLast = null;
        stock = null;
        skuMiddlename = null;
        getShopPop(tagSelect);
        initMateralAdater();
        initSizeAdater();
    }

    private void initMateralAdater() {
        shopMateralAdapter.setData(listMateral);
        lvTagMatarel.setAdapter(shopMateralAdapter);
    }

    private void initSizeAdater() {
        shopSizeAdapter.setData(listSize);
        lvTagSize.setAdapter(shopSizeAdapter);
    }

    String alreadySelect = "已选  ";
    String empryTrim = "  ";

    private void getForSize(String sizeId) {
        if (skuBean.getData().getArr().size() != 0) {
            for (int i = 0; i < skuBean.getData().getArr().size(); i++) {
                if (skuBean.getData().getArr().get(i).getSk_arr().getArr().size() != 0) {
                    if (sizeId.equals(skuBean.getData().getArr().get(i).getSk_id())) {
                        if (skuLast != null && skuMiddlename != null) {
                            stock = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(tagLastSelect).getSk_stock();
                            String skuId = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(tagLastSelect).getSk_id();
                            ssc_sku_id = skuBean.getData().getArr().get(tagSelect).getSk_id() + "," + skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_id() + "," + skuId;
                            tv_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_present_price());
                            sku_name = skuBean.getData().getArr().get(i).getSk_name() + empryTrim + skuMiddlename + empryTrim + skuLast;
                            tv_select_size.setText(alreadySelect + sku_name);
                        } else {
                            tv_price.setText(Contents.moneyTag + shopDetailBean.getData().getSc_present_price());
                            tv_select_size.setText(alreadySelect + skuBean.getData().getArr().get(i).getSk_name());
                        }
                        return;
                    }
                    for (int j = 0; j < skuBean.getData().getArr().get(i).getSk_arr().getArr().size(); j++) {
                        if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_id().equals(sizeId)) {
                            String skuLastName = skuBean.getData().getArr().get(tagSelect).getSk_name();
                            skuMiddlename = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_name();
                            if (skuLast == null) {
                                stock = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_stock();
                                if (stock != null) {
                                    tv_price.setText(Contents.moneyTag + skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_price());
                                    tv_select_size.setText(alreadySelect + skuLastName + empryTrim + skuMiddlename);
                                }
                            } else {
                                stock = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(tagLastSelect).getSk_stock();
                                if (stock != null) {
                                    String skuId = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(tagLastSelect).getSk_id();
                                    ssc_sku_id = skuBean.getData().getArr().get(tagSelect).getSk_id() + "," + skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_id() + "," + skuId;
                                    sku_price = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(tagLastSelect).getSk_price();
                                    tv_price.setText(Contents.moneyTag + sku_price);
                                    sku_name = skuLastName + empryTrim + skuMiddlename + empryTrim + skuLast;
                                    tv_select_size.setText(alreadySelect + empryTrim + sku_name);
                                }
                            }
                            return;
                        }
                        if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().size() != 0) {
                            for (int g = 0; g < skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().size(); g++) {
                                if (skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_id().equals(sizeId)) {
                                    tagLastSelect = g;
                                    String skuLastLastName = skuBean.getData().getArr().get(tagSelect).getSk_name();
                                    if (skuMiddlename != null) {
                                        String skuLastName = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_name();
                                        String skuId = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_id();
                                        ssc_sku_id = skuBean.getData().getArr().get(tagSelect).getSk_id() + "," + skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_id() + "," + skuId;
                                        skuLast = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_name();
                                        stock = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_stock();
                                        sku_price = skuBean.getData().getArr().get(tagSelect).getSk_arr().getArr().get(tagSizeSelect).getSk_arr().getArr().get(g).getSk_price();
                                        tv_price.setText(Contents.moneyTag + sku_price);
                                        sku_name = skuLastLastName + empryTrim + skuLastName + empryTrim + skuLast;
                                        tv_select_size.setText(alreadySelect + sku_name);
                                    } else {
                                        skuLast = skuBean.getData().getArr().get(i).getSk_arr().getArr().get(j).getSk_arr().getArr().get(g).getSk_name();
                                        sku_name = skuLastLastName + empryTrim + skuLast;
                                        tv_select_size.setText(alreadySelect + sku_name);
                                    }

                                }
                            }
                        }

                    }
                }


            }

        }
        if (stock != null) {
            if (Integer.parseInt(stock) > 0) {
                numTv.setText(String.valueOf(1));
                btnConfirm.setEnabled(true);
                btnConfirm.setText("立即购买");
            }
        }
    }

    int tagSelect = 0;
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

    List<OrderBean.DataBean> listShopCart = new ArrayList<>();
    List<OrderBean.DataBean.ArrBean> arrBeanList = new ArrayList<>();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.add_shopping_cart:
                if (!uid.equals("")) {
                    typeWay = 1;
                    popuSize();
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }
                break;
            case R.id.at_once_buy:
                if (!uid.equals("")) {
                    typeWay = 2;
                    popuSize();
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }
                break;
            case R.id.rela_selelct_param:
                if (listParam != null && listParam.size() > 0) {
                    popuinit();
                } else {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("暂无数据");
                }
                break;
            case R.id.rela_select_size:
                popuSize();
                break;
            case R.id.get_cuopon:
                if (!uid.equals("")) {
                    list = shopDetailBean.getData().getCoupon();
                    if (list != null && list.size() > 0) {
                        popuCoupon();
                    } else {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort("暂无优惠卷");
                    }
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }

                break;
            case R.id.collect_shop:
                if (!uid.equals("")) {
                    if (collect_shop.isSelected()) {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort("取消收藏");
                        collect_shop.setSelected(false);
                        deleteShop();
                    } else {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort("收藏成功");
                        collect_shop.setSelected(true);
                        collectShop();
                    }
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }
                break;
            case R.id.cart_shop:
                if (!uid.equals("")) {
                    ShopCartActivity.start(ShopDetailActivity.this);
                } else {
                    NewLoginActivity.start(ShopDetailActivity.this);
                }
                break;
            case R.id.shop:
                SPUtils.getInstance().put("storeId", shopDetailBean.getData().getSc_store_id());
                ShopStoreActivity.start(ShopDetailActivity.this, shopDetailBean.getData().getSs_logo(), shopDetailBean.getData().getSs_name());
                break;
            case R.id.more:
                popuShare(view);
                break;
            case R.id.iv_top:
                iv_top.setVisibility(View.INVISIBLE);
                scrollview.fullScroll(ScrollView.FOCUS_UP);
                break;
            case R.id.rela_enter_shop:
                SPUtils.getInstance().put("storeId", shopDetailBean.getData().getSc_store_id());
                ShopStoreActivity.start(ShopDetailActivity.this, shopDetailBean.getData().getSs_logo(), shopDetailBean.getData().getSs_name());
                break;
        }
    }

    private void popuShare(View v) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pp_shop, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.7f);
        //下面是p里面的东西lin_pp3_btn1
        LinearLayout lin_pp3_btn1 = contentView.findViewById(R.id.lin_pp3_btn1);
        LinearLayout lin_pp3_btn2 = contentView.findViewById(R.id.lin_pp3_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        lin_pp3_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                MainActivity.start(ShopDetailActivity.this, 4);
                finish();

            }
        });

        lin_pp3_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAsDropDown(v);
    }

    String sku_name;
    String sku_price;
    String ssc_sku_id;

    private void addCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + addCart)
                .addParams("user_id", uid)
                .addParams("ssc_name", shopDetailBean.getData().getSc_name())
                .addParams("ssc_number", String.valueOf(num))
                .addParams("ssc_unit_price", sku_price)
                .addParams("ssc_sku_name", sku_name)
                .addParams("ssc_sku_id", ssc_sku_id)
                .addParams("ssc_sc_id", shopDetailBean.getData().getSc_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        BaseStatusBean baseStatusBean = new Gson().fromJson(response, BaseStatusBean.class);
                        if (baseStatusBean.getErrno().equals("200")) {
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showShort("加入购物车成功");
                            Contents.numCart++;
                            tv_msg_num.setText(String.valueOf(Contents.numCart));
                        } else {
                            ToastUtils.showShort(baseStatusBean.getErrmsg());
                        }
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
        TextView pp1_btn = contentView.findViewById(R.id.pp1_btn);
        CouponAdapter shopParamAdapter = new CouponAdapter(ShopDetailActivity.this, list);
        lv.setAdapter(shopParamAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).getSc_id() != null) {
                    String scid = list.get(i).getSc_id();
                    if (list.get(i).getWhetherToReceive().equals("0")) {
                        list.get(i).setWhetherToReceive("1");
                        shopParamAdapter.notifyDataSetChanged();
                    }
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
                windowDissMiss();
            }
        });

    }

    private void windowDissMiss() {
        backgroundAlpha(1f);
        window.dismiss();
    }

    private void getCoupon(String id) {
        OkHttpUtils.post().url(Contents.SHOPBASE + getCoupon)
                .addParams("sc_id", id)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        BaseStatusBean baseStatusBean = new Gson().fromJson(response, BaseStatusBean.class);
                        if (baseStatusBean.getErrno().equals("200")) {
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                            ToastUtils.showShort("领取成功");
                        } else {
                            ToastUtils.showShort(baseStatusBean.getErrmsg());
                        }
                    }
                });
    }
}
