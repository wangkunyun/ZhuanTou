package com.ztkj.wky.zhuantou.Activity.live_shop;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.homepage.SearchActivity;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveShopFragment extends Fragment {


    @BindView(R.id.live_search)
    RelativeLayout liveSearch;
    @BindView(R.id.tv_click1)
    TextView tvClick1;
    @BindView(R.id.tv_click2)
    TextView tvClick2;
    @BindView(R.id.tv_click3)
    TextView tvClick3;
    @BindView(R.id.tv_click4)
    TextView tvClick4;
    @BindView(R.id.tv_click5)
    TextView tvClick5;
    @BindView(R.id.lin_4Btn)
    LinearLayout lin4Btn;
    @BindView(R.id.n5_banner)
    Banner n5Banner;
    @BindView(R.id.cardview_banner)
    CardView cardviewBanner;
    @BindView(R.id.n5_tv_shop)
    TextView n5TvShop;
    @BindView(R.id.re_shop)
    RecyclerView reShop;
    Unbinder unbinder;
    private ArrayList<Integer> img;
    private ArrayList<String> des;
    private ArrayList<String> price;
    private String banner_url = StringUtils.jiekouqianzui + "Article/bannerShop";
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_shop, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        reShop.setHasFixedSize(true);
        reShop.setNestedScrollingEnabled(false);

        Banner_gi();
        Listdata();


        return view;
    }

    private void Banner_gi() {

        OkHttpUtils.post()
                .url(banner_url)
                .addParams("token", token)
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

                            n5Banner.setIndicatorGravity(BannerConfig.CENTER);
                            n5Banner.setDelayTime(5000);
                            n5Banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            n5Banner.setImageLoader(new GlideImageLoader());
                            n5Banner.setImages(imgs);
                            n5Banner.start();
                        } else if (bannerBean.getErrno().equals("666666")) {
                            Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), LoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {

                        }
                    }
                });


    }

    private void Listdata() {
        img = new ArrayList<>();
        des = new ArrayList<>();
        price = new ArrayList<>();

        img.add(R.mipmap.img_live_shop_zhanwei);
        img.add(R.mipmap.img_live_shop_zhanweitu2);
        img.add(R.mipmap.img_live_shop_zhanweitu3);
        img.add(R.mipmap.img_live_zhanweitu4);

        des.add("ZIIIRO日食手表女防水德国创意夜光简约抖…");
        des.add("波浪边渔夫帽男女休闲春夏出游文艺…");
        des.add("北欧/丹麦Frandsen灯具/简约现代/Dad..");
        des.add("原创设计女装【隐侠】秋冬新款 高领百…");

        price.add("1457");
        price.add("32");
        price.add("432");
        price.add("58");

        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(getContext(), img, des, price);
        reShop.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        reShop.setAdapter(liveShopAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @OnClick({R.id.live_search, R.id.tv_click1, R.id.tv_click2, R.id.tv_click3, R.id.tv_click4, R.id.tv_click5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_search:
                intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_click1:
                break;
            case R.id.tv_click2:
                break;
            case R.id.tv_click3:
                break;
            case R.id.tv_click4:
                break;
            case R.id.tv_click5:
                break;
        }
    }
}
