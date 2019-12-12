package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopHomeBean;
import com.ztkj.wky.zhuantou.bean.ShopSearchBean;

import java.util.List;

public class SearchShopAdapter extends RecyclerView.Adapter<SearchShopAdapter.ViewHolder> {
    private Context context;
    private List<ShopSearchBean.DataBean.CommodityBean> list;


    public SearchShopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopSearchBean.DataBean.CommodityBean> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public int getData() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void clearDara() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (list.get(i).getSc_name() != null) {
            viewHolder.tv_des.setText(list.get(i).getSc_name());
        }
        if (list.get(i).getSc_present_price() != null) {
            viewHolder.tv_price.setText(list.get(i).getSc_present_price());
        }
        if (list.get(i).getSc_img() != null) {
            Glide.with(context).load(list.get(i).getSc_img()).into(viewHolder.img);
        }
        viewHolder.item_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopDetailActivity.start(context, list.get(i).getSc_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv_des, tv_price;
        private CardView item_shop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_shopPic);
            tv_des = itemView.findViewById(R.id.tv_shop_des);
            tv_price = itemView.findViewById(R.id.tv_shopPrice);
            item_shop = itemView.findViewById(R.id.item_shop);
        }
    }
}
