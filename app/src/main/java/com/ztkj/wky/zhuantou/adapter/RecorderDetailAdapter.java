package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.CollecShopBean;
import com.ztkj.wky.zhuantou.bean.RecorderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecorderDetailAdapter extends RecyclerView.Adapter {

    private Context context;
    List<RecorderBean.DataBean.ArrBean> list = new ArrayList<>();


    public RecorderDetailAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<RecorderBean.DataBean.ArrBean> lists) {
        this.list.addAll(lists);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collect_shop_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.shop_select.setVisibility(View.GONE);
        Glide.with(context).load(list.get(i).getSf_trade_img()).into(viewHolder1.ivCollect);
        viewHolder1.tvCollectName.setText(list.get(i).getSf_trade_name());
        viewHolder1.tvShopPrice.setText(list.get(i).getSf_trade_price());
        viewHolder1.rela_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopDetailActivity.start(context,list.get(i).getSf_commodity_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_collect)
        ImageView ivCollect;
        @BindView(R.id.tv_collect_name)
        TextView tvCollectName;
        @BindView(R.id.tv_shop_price)
        TextView tvShopPrice;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.shop_select)
        ImageView shop_select;
        @BindView(R.id.rela_collect)
        RelativeLayout rela_collect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
