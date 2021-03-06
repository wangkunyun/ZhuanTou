package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.ztkj.wky.zhuantou.Activity.live_shop.ActivityList;
import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopsActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.store.ShopStoreActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.ShopCatatoryBean;
import com.ztkj.wky.zhuantou.bean.ShopHomeBean;
import com.ztkj.wky.zhuantou.homepage.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class LiveShopFragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

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
    List<ShopHomeBean.DataBean> listShop;
    public void setListShop(List<ShopHomeBean.DataBean> list) {
        this.listShop = list;
        notifyDataSetChanged();
    }
    List<ShopCatatoryBean.DataBean> listShopCatratiry;

    public void setShopCatarory(List<ShopCatatoryBean.DataBean> listShopCatratiry) {
        this.listShopCatratiry = listShopCatratiry;
        notifyDataSetChanged();
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
            if (listShop != null) {
                LiveShopAdapter liveShopAdapter = new LiveShopAdapter(mContext, listShop);
                shopRecommendViewHolder.re_shop.setLayoutManager(new GridLayoutManager(mContext, 2));
                shopRecommendViewHolder.re_shop.setAdapter(liveShopAdapter);
            }

        } else if (i == 0) {
            ShopCataoryViewHolder shopCataoryViewHolder = (ShopCataoryViewHolder) viewHolder;
            shopCataoryViewHolder.n5_banner.setIndicatorGravity(BannerConfig.CENTER);
            shopCataoryViewHolder.n5_banner.setDelayTime(5000);
            shopCataoryViewHolder.n5_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            shopCataoryViewHolder.n5_banner.setImageLoader(new GlideImageLoader());
            shopCataoryViewHolder.n5_banner.setImages(listImgs);
            shopCataoryViewHolder.n5_banner.start();
            shopCataoryViewHolder.live_search.setOnClickListener(this);
            if (listShopCatratiry != null) {
                ShopCataroryAdapter shopCataroryAdapter = new ShopCataroryAdapter(listShopCatratiry, mContext);
                shopCataoryViewHolder.recycle.setLayoutManager(new GridLayoutManager(mContext, 5));
                shopCataoryViewHolder.recycle.setAdapter(shopCataroryAdapter);
            }

        } else {
            ShopSpecialAreaViewHolder specialAreaViewHolder= (ShopSpecialAreaViewHolder) viewHolder;
            specialAreaViewHolder.area_one.setOnClickListener(this);
            specialAreaViewHolder.area_two.setOnClickListener(this);
            specialAreaViewHolder.area_three.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_search:
                SearchShopsActivity.start(mContext);
                break;
                //打折区域
            case R.id.area_three:
                ActivityList.start(mContext,1);
                break;
            case R.id.area_two:
                ActivityList.start(mContext,2);
                break;
            case R.id.area_one:
                ActivityList.start(mContext,3);
                break;
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
        Banner n5_banner;
        private RelativeLayout live_search;
        private RecyclerView recycle;

        public ShopCataoryViewHolder(@NonNull View itemView) {
            super(itemView);
            n5_banner = itemView.findViewById(R.id.n5_banner);
            live_search = itemView.findViewById(R.id.live_search);
            recycle = itemView.findViewById(R.id.recycle);
        }
    }

    class ShopSpecialAreaViewHolder extends RecyclerView.ViewHolder {
        private TextView n5_tv_shop;
        private ImageView area_one;
        private ImageView area_two;
        private ImageView area_three;

        public ShopSpecialAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            n5_tv_shop = itemView.findViewById(R.id.n5_tv_shop);
            area_one = itemView.findViewById(R.id.area_one);
            area_two = itemView.findViewById(R.id.area_two);
            area_three = itemView.findViewById(R.id.area_three);
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
