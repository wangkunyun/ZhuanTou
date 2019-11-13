package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

public class LiveShopDetailAdapter extends RecyclerView.Adapter<LiveShopDetailAdapter.ViewHolder> {
    private Context context;
    private List<JsonBean.ListBean> list;


    public LiveShopDetailAdapter(Context context, List<JsonBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_detail_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load("https://test-other-1258996935.cos.ap-beijing.myqcloud.com/1567407703537261884.png").into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_shop_detail);

        }
    }
}