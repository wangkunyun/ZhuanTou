package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.ztkj.wky.zhuantou.Activity.live_shop.LiveShopFragment;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.ArrayList;
import java.util.List;

public class LiveShopFragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_TOP = 0;
    public static final int ITEM_TYPE_MIDDLE = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private LayoutInflater mLayoutInflater = null;
    private Context mContext = null;
    List<BannerBean.DataBean> list = new ArrayList<>();
    List<String> listImgs = new ArrayList<>();

    public LiveShopFragAdapter(@NonNull Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setBannerData(BannerBean bannerData) {
        if (bannerData != null && bannerData.getData() != null && bannerData.getData().size() > 0) {
            list = bannerData.getData();
            for (int i = 0; i < list.size(); i++) {
                listImgs.add(list.get(i).getBanner());
            }
        }

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
            shopRecommendViewHolder.re_shop.setLayoutManager(new GridLayoutManager(mContext, 2));
            shopRecommendViewHolder.re_shop.setAdapter(liveShopAdapter);
        } else if (i == 0) {
            ShopCataoryViewHolder shopCataoryViewHolder = (ShopCataoryViewHolder) viewHolder;
            shopCataoryViewHolder.n5_banner.setIndicatorGravity(BannerConfig.CENTER);
            shopCataoryViewHolder.n5_banner.setDelayTime(5000);
            shopCataoryViewHolder.n5_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            shopCataoryViewHolder.n5_banner.setImageLoader(new GlideImageLoader());
            shopCataoryViewHolder.n5_banner.setImages(listImgs);
            shopCataoryViewHolder.n5_banner.start();
            shopCataoryViewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShopDetailActivity.start(mContext);
                }
            });
        } else {

        }
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
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
        private TextView tv_click1;
        Banner n5_banner;

        public ShopCataoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_click1 = itemView.findViewById(R.id.tv_click1);
            n5_banner = itemView.findViewById(R.id.n5_banner);
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
