package com.ztkj.wky.zhuantou.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

public class ConfimOrderDetailAdapter extends RecyclerView.Adapter {


    public void setData() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_order_detail_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;

    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView order_pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_pic = itemView.findViewById(R.id.order_pic);

        }
    }
}
