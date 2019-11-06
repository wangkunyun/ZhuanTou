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

import java.util.ArrayList;

public class LiveShopAdapter extends RecyclerView.Adapter<LiveShopAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> img;
    private ArrayList<String> des;
    private ArrayList<String> price;

    public LiveShopAdapter(Context context, ArrayList<Integer> img, ArrayList<String> des, ArrayList<String> price) {
        this.context = context;
        this.img = img;
        this.des = des;
        this.price = price;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_live_shop_waterfall, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_des.setText(des.get(i));
        viewHolder.tv_price.setText(price.get(i));
        Glide.with(context).load(img.get(i)).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return des.size();
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
