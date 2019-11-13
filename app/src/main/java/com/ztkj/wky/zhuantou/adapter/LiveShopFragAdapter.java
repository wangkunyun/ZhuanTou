package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

public class LiveShopFragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_TOP = 0;
    public static final int ITEM_TYPE_MIDDLE = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private LayoutInflater mLayoutInflater = null;
    private Context mContext = null;

    public LiveShopFragAdapter(@NonNull Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ITEM_TYPE_TOP) {
            return new ShopCataoryViewHolder(mLayoutInflater.inflate(R.layout.item_shop_cataory_layout, null));

        } else if (i == ITEM_TYPE_MIDDLE) {
            return new ShopSpecialAreaViewHolder(mLayoutInflater.inflate(R.layout.item_special_area_layout, null));

        } else {
            return new ShopRecommendViewHolder(mLayoutInflater.inflate(R.layout.item_recommend_list_layout, null));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2) {
            ShopRecommendViewHolder shopRecommendViewHolder = (ShopRecommendViewHolder) viewHolder;
            JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json, JsonBean.class);
            List<JsonBean.ListBean> list = jsonBean.getList();
            LiveShopAdapter liveShopAdapter = new LiveShopAdapter(mContext, list);
            shopRecommendViewHolder.re_shop.setLayoutManager(new GridLayoutManager(mContext,2));
            shopRecommendViewHolder.re_shop.setAdapter(liveShopAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;

        } else {
            return 2;
        }
    }

    class ShopCataoryViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout live_search;

        public ShopCataoryViewHolder(@NonNull View itemView) {
            super(itemView);
            live_search = itemView.findViewById(R.id.live_search);
        }
    }

    class ShopSpecialAreaViewHolder extends RecyclerView.ViewHolder {
        private TextView n5_tv_shop;

        public ShopSpecialAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            n5_tv_shop = itemView.findViewById(R.id.n5_tv_shop);
        }
    }

    class ShopRecommendViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView re_shop;

        public ShopRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            re_shop = itemView.findViewById(R.id.re_shop);
        }
    }

}
