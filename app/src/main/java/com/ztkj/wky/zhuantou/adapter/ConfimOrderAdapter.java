package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.OrderBean;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfimOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<OrderBean.DataBean> list;

    public ConfimOrderAdapter(Context context) {
        this.mContext = context;
    }

    int type;

    public void setData(List<OrderBean.DataBean> lists, int type) {
        this.list = lists;
        this.type = type;
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
        Glide.with(mContext).load(list.get(i).getSs_logo()).into(viewHolder1.circleImageView);
        confimOrderDetailAdapter = new ConfimOrderDetailAdapter(mContext);
        viewHolder1.recycle_shop_detail.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder1.recycle_shop_detail.setAdapter(confimOrderDetailAdapter);
        if (type == 1) {
            confimOrderDetailAdapter.setData(list.get(i).getArr(), 2);
        } else {
            confimOrderDetailAdapter.setData(list.get(i).getArr(), 1);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_shop_name;

        private RecyclerView recycle_shop_detail;
        private CircleImageView circleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.n3_headImg);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            recycle_shop_detail = itemView.findViewById(R.id.recycle_shop_detail);

        }
    }
}


