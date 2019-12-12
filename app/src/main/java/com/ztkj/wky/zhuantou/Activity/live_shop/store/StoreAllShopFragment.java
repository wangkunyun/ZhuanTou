package com.ztkj.wky.zhuantou.Activity.live_shop.store;
/**
 * 作者：wky
 * 功能描述：全部商品
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.SearchShopAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;
import com.ztkj.wky.zhuantou.bean.ShopSearchBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class StoreAllShopFragment extends Fragment {


    @BindView(R.id.clickSynthesize)
    TextView clickSynthesize;
    @BindView(R.id.clickSales)
    TextView clickSales;
    @BindView(R.id.clickPrice)
    TextView clickPrice;
    @BindView(R.id.clickFiltrate)
    TextView clickFiltrate;
    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;
    ShopOpenDrawerListen shopOpenDrawerListen;
    String searchName;
    @BindView(R.id.empty)
    ImageView empty;
    ShopSearchBean shopSearchBean;
    SearchShopAdapter liveShopAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        shopOpenDrawerListen = (ShopOpenDrawerListen) context;
    }


    public static StoreAllShopFragment newInstance(String searchName) {
        StoreAllShopFragment fragment = new StoreAllShopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("searchName", searchName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_all_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        searchName = getArguments().getString("searchName");
        initData();
        return view;
    }

    private void initData() {
        liveShopAdapter = new SearchShopAdapter(getActivity());
        reView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        reView.setAdapter(liveShopAdapter);
        if (searchName != null) {
            getShopList();
        } else {
            emptyListView();
        }
    }

    List<ShopSearchBean.DataBean.CommodityBean> list;
    int order_type = 1;
    String order_by = "ASC";

    private void getShopList() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.shopList)
                .addParams("search", searchName)
                .addParams("page", String.valueOf(1))
                .addParams("one_class", "")
                .addParams("two_class", "")
                .addParams("price_start", "")
                .addParams("price_end", "")
                .addParams("sb_id", "")
                .addParams("order_type", String.valueOf(order_type))
                .addParams("order_by", order_by)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("dsfa", response);
                        shopSearchBean = new Gson().fromJson(response, ShopSearchBean.class);
                        if (shopSearchBean != null) {
                            reView.setVisibility(View.VISIBLE);
                            empty.setVisibility(View.GONE);
                            list = shopSearchBean.getData().getCommodity();
                            if (list != null && list.size() > 0) {
                                liveShopAdapter.setData(list);
                            } else {
                                emptyListView();
                            }
                        } else {
                            emptyListView();
                        }
                    }
                });
    }

    private void emptyListView() {
        empty.setImageDrawable(getResources().getDrawable(R.mipmap.no_message));
        reView.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.clickSynthesize, R.id.clickSales, R.id.clickPrice, R.id.clickFiltrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clickSynthesize:
                order_type = 1;
                refreshData();
                break;
            case R.id.clickSales:
                order_type = 2;
                refreshData();
                break;
            case R.id.clickPrice:
                order_type = 3;
                if (order_by.equals("ASC")) {
                    order_by = "DESC";
                } else {
                    order_by = "ASC";
                }
                refreshData();
                break;
            case R.id.clickFiltrate:
                //调出右侧抽屉
//                ShopStoreActivity.mDrawer.openDrawer(Gravity.END);
                if (shopOpenDrawerListen != null) {
                    shopOpenDrawerListen.open();
                }
                break;
        }
    }

    private void refreshData() {
        if (liveShopAdapter != null && liveShopAdapter.getData() > 0) {
            liveShopAdapter.clearDara();
            list = null;
        }
        getShopList();
    }

    public interface ShopOpenDrawerListen {
        void open();
    }
}
