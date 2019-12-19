package com.ztkj.wky.zhuantou.Activity.live_shop;

/**
 * 作者：wky
 * 功能描述： 商品分类
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.layout_tvSearch)
    TextView layoutTvSearch;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.relaEmpty)
    RelativeLayout relaEmpty;
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();

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


    }


    private void Banner_gi() {
        OkHttpUtils.post()
                .addParams("token", "")
                .url(Contents.getShopBanner)
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


}
