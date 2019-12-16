package com.ztkj.wky.zhuantou.Activity.live_shop;

/**
 * 作者：wky
 * 功能描述： 商品分类
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.GuessLikeBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.ShopKeyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopClassActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearchEdt;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.clickSynthesize)
    TextView clickSynthesize;
    @BindView(R.id.clickSales)
    TextView clickSales;
    @BindView(R.id.clickPrice)
    TextView clickPrice;
    @BindView(R.id.clickFiltrate)
    TextView clickFiltrate;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.shopBanner)
    CardView shopBanner;
    @BindView(R.id.reView)
    RecyclerView reView;
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();
    private OrderBean.DataBean dataBean;
    private ShopKeyBean shopKeyBean;
    private String keyString;
    private List<String> listKey = new ArrayList<>();
    private StringBuilder stringBuilder;
    private GuessLikeBean guessLikeBean;
    private List<GuessLikeBean.DataBean> GuessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_class);
        ButterKnife.bind(this);

        //解决滑动冲突
        reView.setHasFixedSize(true);
        reView.setNestedScrollingEnabled(false);
        //banner
        Banner_gi();
        getKeys();

//        JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json2, JsonBean.class);
//        List<JsonBean.ListBean> list = jsonBean.getList();
//        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(this, list);
//        reView.setLayoutManager(new GridLayoutManager(this, 2));
//        reView.setAdapter(liveShopAdapter);

    }


    private void Banner_gi() {
        OkHttpUtils.post()
                .addParams("token", "")
                .url(Contents.BANNER)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("repres", response);
                        BannerBean bannerBean = gson.fromJson(response, BannerBean.class);

                        if (bannerBean.getErrno().equals("200")) {
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                imgs.add(data.get(i).getBanner());
                                strs.add(data.get(i).getHref());
                            }
                            banner.setIndicatorGravity(BannerConfig.CENTER);
                            banner.setDelayTime(5000);
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(imgs);
                            banner.start();
                        }
                    }
                });
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopClassActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }


    @OnClick({R.id.layout_back, R.id.bigsearch_edt, R.id.more, R.id.clickSynthesize, R.id.clickSales, R.id.clickPrice, R.id.clickFiltrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.bigsearch_edt:
                break;
            case R.id.more:
                break;
            case R.id.clickSynthesize:
                break;
            case R.id.clickSales:
                break;
            case R.id.clickPrice:
                break;
            case R.id.clickFiltrate:
                break;
        }
    }

    private void getKeys() {
        OkHttpUtils.post().url(Contents.getKey)
                .addParams("goods_name", "vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x")
                .addParams("goods_details", "vivo Z5x极点全面屏高通骁龙710大电池智能手机官方正品手机新品vivoz5x限量版 z3x")
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
                                reView.setLayoutManager(new GridLayoutManager(ShopClassActivity.this, 2));
                                reView.setAdapter(new GuessAdapter());
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

    class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {

        @NonNull
        @Override
        public GuessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(ShopClassActivity.this).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

            return new GuessAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GuessAdapter.ViewHolder viewHolder, int i) {
            Glide.with(ShopClassActivity.this).load(GuessData.get(i).getSc_img()).into(viewHolder.img_shopPic);
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
