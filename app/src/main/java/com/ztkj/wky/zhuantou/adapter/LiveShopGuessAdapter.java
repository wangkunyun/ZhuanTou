package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.GuessLikeBean;

import java.util.ArrayList;
import java.util.List;

public class LiveShopGuessAdapter extends BaseAdapter {
    private Context context;
    private List<GuessLikeBean.DataBean> list = new ArrayList<>();


    public LiveShopGuessAdapter(Context context) {
        this.context = context;
    }

    public void setGuess(List<GuessLikeBean.DataBean> lists) {
        this.list.addAll(lists);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_live_shop_waterfall, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

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
//                ShopDetailActivity.start(context, list.get(i).getSc_id());
            }
        });
        return view;
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
