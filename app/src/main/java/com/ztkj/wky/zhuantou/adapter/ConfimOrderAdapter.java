package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

public class ConfimOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public ConfimOrderAdapter(Context context) {
        this.mContext = context;
    }

    public void setData() {

    }

    ConfimOrderDetailAdapter confimOrderDetailAdapter;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_confirm_order_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        confimOrderDetailAdapter = new ConfimOrderDetailAdapter();
        viewHolder1.recycle_shop_detail.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder1.recycle_shop_detail.setAdapter(confimOrderDetailAdapter);
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_shop_name;

        private RecyclerView recycle_shop_detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            recycle_shop_detail = itemView.findViewById(R.id.recycle_shop_detail);

        }
    }
}
