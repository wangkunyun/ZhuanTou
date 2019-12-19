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
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class ConfimOrderDetailAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int type;

    public ConfimOrderDetailAdapter(Context context) {
        this.mContext = context;
    }

    public void setList(List<OrderBean.DataBean.ArrBean> list) {
        this.list = list;
    }

    public List<OrderBean.DataBean.ArrBean> list = new ArrayList<>();

    public void setData(List<OrderBean.DataBean.ArrBean> lists, int type) {
        this.list = lists;
        this.type = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_confirm_order_detail_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        Glide.with(mContext).load(list.get(i).getSc_img()).into(((ViewHolder) viewHolder).order_pic);
        switch (type){
            case 2:
                viewHolder1.tv_order_name.setText(list.get(i).getSsc_name());
                viewHolder1.tv_order_size.setText(list.get(i).getSsc_sku_name());
                viewHolder1.num_shop.setText(list.get(i).getSsc_number()+"件");
                viewHolder1.price.setText(Contents.moneyTag+list.get(i).getSsc_unit_price());
                break;
            case 1:
                viewHolder1.tv_order_name.setText(list.get(i).getSog_name());
                viewHolder1.tv_order_size.setText(list.get(i).getSog_sku_name());
                viewHolder1.num_shop.setText(list.get(i).getSog_number()+"件");
                viewHolder1.price.setText(Contents.moneyTag+list.get(i).getSog_total_price());
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(list!=null){
           return list.size();
        }else{
            return 0;
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView order_pic;
        private TextView tv_order_name;
        private TextView tv_order_size;
        private TextView num_shop;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_pic = itemView.findViewById(R.id.order_pic);
            tv_order_name = itemView.findViewById(R.id.tv_order_name);
            tv_order_size = itemView.findViewById(R.id.tv_order_size);
            num_shop = itemView.findViewById(R.id.num_shop);
            price = itemView.findViewById(R.id.price);


        }
    }
}
