package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;
import com.ztkj.wky.zhuantou.bean.ShopDetailBean;

import java.util.List;

public class LiveShopListAdapter extends RecyclerView.Adapter<LiveShopListAdapter.ViewHolder> {
    private Context context;
    private List<ShopDetailBean.DataBean.StoreBean> list;


    public LiveShopListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopDetailBean.DataBean.StoreBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_list_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (list.get(i).getSc_name() != null) {
            viewHolder.tv_store_name.setText(list.get(i).getSc_name());
        }
        if (list.get(i).getSc_present_price() != null) {
            viewHolder.tv_store_price.setText(Contents.moneyTag + list.get(i).getSc_present_price());
        }
        if (list.get(i).getSc_img() != null) {
            Glide.with(context).load(list.get(i).getSc_img()).into(viewHolder.iv_store);
        }
        viewHolder.item_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopDetailActivity.start(context,list.get(i).getSc_id());
            }
        });

    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_store;
        private TextView tv_store_name;
        private TextView tv_store_price;
        private LinearLayout item_shop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_store = itemView.findViewById(R.id.iv_store);
            tv_store_name = itemView.findViewById(R.id.tv_store_name);
            tv_store_price = itemView.findViewById(R.id.tv_store_price);
            item_shop = itemView.findViewById(R.id.item_shop);
        }
    }
}
