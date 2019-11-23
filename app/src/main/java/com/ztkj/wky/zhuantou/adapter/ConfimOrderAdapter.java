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

import com.ztkj.wky.zhuantou.Activity.live_shop.CollectShopActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;

import java.util.List;

public class ConfimOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ShopCartBean.DataBean> list;

    public ConfimOrderAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ShopCartBean.DataBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
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
        viewHolder1.tv_shop_name.setText(list.get(i).getSs_name());
        confimOrderDetailAdapter = new ConfimOrderDetailAdapter();
        viewHolder1.recycle_shop_detail.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder1.recycle_shop_detail.setAdapter(confimOrderDetailAdapter);
        confimOrderDetailAdapter.setData(list.get(i).getArr());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
