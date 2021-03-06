package com.ztkj.wky.zhuantou.Activity.live_shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.HeadRefreshView;
import com.ztkj.wky.zhuantou.MyUtils.LoadMoreView;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopFragAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.ShopCatatoryBean;
import com.ztkj.wky.zhuantou.bean.ShopHomeBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;
import com.ztkj.wky.zhuantou.messagefra2.Blank1Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank2Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank3Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank4Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveShopFragment extends Fragment implements BaseRefreshListener {


    @BindView(R.id.recycle_shop_list)
    RecyclerView recycle_shop_list;
    Unbinder unbinder;
    LiveShopFragAdapter liveShopFragAdapter;
    @BindView(R.id.n1_cf)
    PullToRefreshLayout n1Cf;
    @BindView(R.id.refreshLayout)
    RelativeLayout refreshLayout;
    private String banner_url = StringUtils.jiekouqianzui + "Article/bannerShop";
    Intent intent;
    List<ShopCatatoryBean.DataBean> listShopCatratiry;
    @BindView(R.id.iv_top)
    ImageView iv_top;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        n1Cf.setHeaderView(new HeadRefreshView(getContext()));
        n1Cf.setFooterView(new LoadMoreView(getContext()));
        n1Cf.setRefreshListener(this);
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
        uid = SPUtils.getInstance().getString("uid");
        initShopCatatory();
        if (uid != null) {
            initShopList(uid);
        } else {
            initShopList(null);
        }
        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycle_shop_list.smoothScrollToPosition(0);
                iv_top.setVisibility(View.INVISIBLE);
            }
        });
        recycle_shop_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>110){
                    iv_top.setVisibility(View.VISIBLE);
                }else{
                    if(dy<0||dy==0){
                        iv_top.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        return view;
    }


    ShopCatatoryBean shopCatatoryBean;

    private void initShopCatatory() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.shopcataoty)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        shopCatatoryBean = new Gson().fromJson(response, ShopCatatoryBean.class);
                        if (shopCatatoryBean != null) {
                            listShopCatratiry = shopCatatoryBean.getData();
                            liveShopFragAdapter.setShopCatarory(listShopCatratiry);
                        }
                    }
                });
    }


    int pageNum = 1;
    String uid;

    private void initShopList(String userUid) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.homeShopList)
                .addParams("uid", userUid)
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
                            shopHomeBean = new Gson().fromJson(response, ShopHomeBean.class);
                            if (shopHomeBean != null) {
                                if (shopHomeBean.getErrno().equals("200")) {
                                    list = shopHomeBean.getData();
                                    liveShopFragAdapter.setListShop(list);
                                    liveShopFragAdapter.notifyDataSetChanged();
                                } else {
                                    ToastUtils.showShort(shopHomeBean.getErrmsg());
                                }
                            } else {
                                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                                ToastUtils.showShort("解析数据失败");
                            }
                        }
                    }
                });


    }

    ShopHomeBean shopHomeBean;
    List<ShopHomeBean.DataBean> list = new ArrayList<>();

    private void Listdata() {
        OkHttpUtils.post()
                .url(Contents.SHOPBASE + Contents.getShopBanner)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        BannerBean bannerBean = new Gson().fromJson(response, BannerBean.class);
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


    @Override
    public void refresh() {
        n1Cf.finishRefresh();
    }

    @Override
    public void loadMore() {
        n1Cf.finishLoadMore();

    }
}
