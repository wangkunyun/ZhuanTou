package com.ztkj.wky.zhuantou.Activity.live_shop.store;
/**
 * 作者：wky
 * 功能描述：全部商品
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_all_shop, container, false);
        unbinder = ButterKnife.bind(this, view);

//        JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json, JsonBean.class);
//        List<JsonBean.ListBean> list = jsonBean.getList();
//        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(getContext(), list);
//        reView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        reView.setAdapter(liveShopAdapter);

        return view;
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
