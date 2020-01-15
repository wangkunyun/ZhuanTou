package com.ztkj.wky.zhuantou.Activity.live_shop.store;
/**
 * 作者：wky
 * 功能描述：全部商品
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
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

import com.blankj.utilcode.util.SPUtils;
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
    String id;
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
        liveShopAdapter = new SearchShopAdapter(getActivity());
        reView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        reView.setAdapter(liveShopAdapter);

        if(getArguments()!=null){
            searchName = getArguments().getString("searchName");
            initData();
        }else{
            id= SPUtils.getInstance().getString("storeId");
            getShopList(Contents.shopStore +Contents.shopStoreShop,"shop_id",id);
//            emptyListView();
        }

        return view;
    }



    private void initData() {
        if (searchName == null) {
            emptyListView();
        } else {
            getShopList(Contents.SHOPBASE +Contents.shopList,"search",searchName);
        }
    }

    List<ShopSearchBean.DataBean.CommodityBean> list;
    int order_type = 1;
    String order_by = "ASC";

    private void getShopList(String url,String paramType,String param) {
        OkHttpUtils.post().url( url)
                .addParams(paramType, param)
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
                order_by = "ASC";
                clickSynthesize.setTextColor(getResources().getColor(R.color.t2));
                clickSales.setTextColor(getResources().getColor(R.color.t4));
                clickPrice.setTextColor(getResources().getColor(R.color.t4));
                refreshData();
                break;
            case R.id.clickSales:
                order_type = 2;
                order_by = "ASC";
                clickSynthesize.setTextColor(getResources().getColor(R.color.t4));
                clickSales.setTextColor(getResources().getColor(R.color.t2));
                clickPrice.setTextColor(getResources().getColor(R.color.t4));
                refreshData();
                break;
            case R.id.clickPrice:
                order_type = 3;
                clickSynthesize.setTextColor(getResources().getColor(R.color.t4));
                clickSales.setTextColor(getResources().getColor(R.color.t4));
                clickPrice.setTextColor(getResources().getColor(R.color.t2));
                if (order_by.equals("ASC")) {
                    setDrawable(true);
                    order_by = "DESC";
                } else {
                    setDrawable(false);
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

    public void setDrawable(boolean isTop) {
        Drawable drawable;
        if (isTop) {
            drawable = getResources().getDrawable(R.mipmap.icon_sort_up);
        } else {
            drawable = getResources().getDrawable(R.mipmap.icon_sort_down);
        }
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //设置text 左边图片
        clickPrice.setCompoundDrawables(null, null, drawable, null);

    }

    private void refreshData() {
        if (liveShopAdapter != null && liveShopAdapter.getData() > 0) {
            liveShopAdapter.clearDara();
            list = null;
        }
        if(getArguments()!=null){
            initData();
        }else{
            getShopList(Contents.shopStore +Contents.shopStoreShop,"shop_id",id);
        }

    }

    public interface ShopOpenDrawerListen {
        void open();
    }
}
