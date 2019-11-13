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

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopFragAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.JsonBean;
import com.ztkj.wky.zhuantou.homepage.SearchActivity;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

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


    @BindView(R.id.recycle_shop_list)
    RecyclerView recycle_shop_list;
    Unbinder unbinder;
    LiveShopFragAdapter liveShopFragAdapter;
    private String banner_url = StringUtils.jiekouqianzui + "Article/bannerShop";
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 4;
                } else if (position == 1) {
                    return 4;
                } else {
                    return 4;
                }
            }
        });
        recycle_shop_list.setLayoutManager(manager);
        liveShopFragAdapter = new LiveShopFragAdapter(getActivity());
        recycle_shop_list.setAdapter(liveShopFragAdapter);
        Listdata();


        return view;
    }



//                            getActivity().finish();
    private void Listdata() {
            OkHttpUtils.post()
                    .url(banner_url)
                    .addParams("token", "")
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
                                liveShopFragAdapter.setBannerData(bannerBean);
                                liveShopFragAdapter.notifyDataSetChanged();
//                                List<BannerBean.DataBean> data = bannerBean.getData();

                            } else if (bannerBean.getErrno().equals("666666")) {
                                Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
//                                JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
//                                sharedPreferencesHelper.clear();
                                SPUtils.getInstance().clear();
                                ActivityManager.getInstance().exit();
                                intent = new Intent(getContext(), NewLoginActivity.class);
                                startActivity(intent);

                            }
                        }
                    });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}
