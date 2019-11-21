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

public class LiveShopDetailAdapter extends RecyclerView.Adapter<LiveShopDetailAdapter.ViewHolder> {
    private Context context;
    private List<String> list;


    public LiveShopDetailAdapter(Context context) {
        this.context = context;

    }


    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_detail_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(list.get(i)!=null){
            Glide.with(context).load(list.get(i)).into(viewHolder.iv_shop_detail);
        }
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
        private ImageView iv_shop_detail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_shop_detail = itemView.findViewById(R.id.iv_shop_detail);

        }
    }
}
