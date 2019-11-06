package com.ztkj.wky.zhuantou.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.SendWaterBean;

import java.util.List;

public class SendWaterShopListAdapter extends RecyclerView.Adapter<SendWaterShopListAdapter.ViewHolder> {

    private Context context;
    private List<SendWaterBean.DataBean.ShopGoodsBean> typeList;

    public SendWaterShopListAdapter(Context context) {
        this.context = context;
    }


    public List<SendWaterBean.DataBean.ShopGoodsBean> getData() {
        if (typeList == null || typeList.size() == 0) {
            return null;
        }
        return typeList;
    }

    public void setTypeList(List<SendWaterBean.DataBean.ShopGoodsBean> typeList2) {
        if (typeList2 == null) {
            return;
        }
        typeList = typeList2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_send_water_shoplist, viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.tv_title_shop.setText(typeList.get(position).getName());
        if (typeList.get(position).getType() == 0) {
            //不选中
            viewHolder.sendWaterSelect.setVisibility(View.GONE);
            viewHolder.bg_rl.setBackgroundColor(Color.parseColor("#F8F8F8"));
        } else {
            //选中
            viewHolder.sendWaterSelect.setVisibility(View.VISIBLE);
            viewHolder.bg_rl.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        viewHolder.tv_title_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickLinsh != null) {
                    clickLinsh.click(position, typeList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return typeList == null ? 0 : typeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title_shop;
        private View sendWaterSelect;
        private RelativeLayout bg_rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_shop = itemView.findViewById(R.id.tv_title_shop);
            sendWaterSelect = itemView.findViewById(R.id.sendWaterSelect);
            bg_rl = itemView.findViewById(R.id.rl_sendWaterLeft);
        }
    }

    public interface ClickLinsh {
        void click(int position, SendWaterBean.DataBean.ShopGoodsBean shopGoodsBean);
    }

    public ClickLinsh clickLinsh;

    public void setOnItemClickListener(ClickLinsh listener) {
        this.clickLinsh = listener;
    }
}