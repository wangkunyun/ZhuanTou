package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopCatatoryBean;

import java.util.List;

public class ShopCataroryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mLayoutInflater = null;
    List<ShopCatatoryBean.DataBean> lists;
    Context context;

    public ShopCataroryAdapter(List<ShopCatatoryBean.DataBean> list, Context context) {
        this.lists = list;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ShopCataroryiewHolder(mLayoutInflater.inflate(R.layout.item_catatory_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ShopCataroryiewHolder shopCataroryiewHolder = (ShopCataroryiewHolder) viewHolder;
        shopCataroryiewHolder.tv_catarory_name.setText(lists.get(i).getSc_name());
        switch (i) {
            case 0:
                shopCataroryiewHolder.iv_shop.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_live_lingshi));
                break;
            case 1:
                shopCataroryiewHolder.iv_shop.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_live_wenjv));

                break;
            case 2:
                shopCataroryiewHolder.iv_shop.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_live_books));

                break;
            case 3:
                shopCataroryiewHolder.iv_shop.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_live_shuma));

                break;
            case 4:
                shopCataroryiewHolder.iv_shop.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_live_skin));

                break;


        }
    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    class ShopCataroryiewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_shop;
        private TextView tv_catarory_name;

        public ShopCataroryiewHolder(@NonNull View itemView) {
            super(itemView);
            iv_shop = itemView.findViewById(R.id.iv_catarory);
            tv_catarory_name = itemView.findViewById(R.id.tv_catarory_name);
        }
    }
}
