package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

public class LiveShopAdapter extends RecyclerView.Adapter<LiveShopAdapter.ViewHolder> {
    private Context context;
    private List<JsonBean.ListBean> list;

    public LiveShopAdapter(Context context, List<JsonBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_des.setText(list.get(i).getTitle());
        viewHolder.tv_price.setText(list.get(i).getMoney());
        Glide.with(context).load(list.get(i).getImg()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv_des, tv_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_shopPic);
            tv_des = itemView.findViewById(R.id.tv_shop_des);
            tv_price = itemView.findViewById(R.id.tv_shopPrice);
        }
    }
}
