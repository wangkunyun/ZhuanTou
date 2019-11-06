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
import com.ztkj.wky.zhuantou.bean.SendWaterBean;

import java.util.List;

public class SendWaterShopAdapter extends RecyclerView.Adapter<SendWaterShopAdapter.ViewHolder> {
    private Context context;
    private List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> waterList;

    public SendWaterShopAdapter(Context context, List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> waterList) {
        this.context = context;
        this.waterList = waterList;
    }

    public SendWaterShopAdapter(Context context) {
        this.context = context;
    }

    public void setWaterList(List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> waterList2) {
        if (waterList2 == null) {
            return;
        }
        waterList = waterList2;
        notifyDataSetChanged();
    }

    public List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> getData() {
        if (waterList == null) {
            return null;
        }
        return waterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_send_water_shop, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_WaterName.setText(waterList.get(i).getTrade_name());
        viewHolder.tv_WaterSealNum.setText("月售"+waterList.get(i).getSales_volume());
        viewHolder.tv_WaterPrice.setText(waterList.get(i).getMoney());
        Glide.with(context).load(waterList.get(i).getImage()).into(viewHolder.img_pic);
        viewHolder.click_addWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickLinsh != null) {
                    clickLinsh.click(i, waterList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (waterList == null) {
            return 0;
        }
        return waterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_WaterName, tv_WaterPrice, tv_WaterSealNum;
        private ImageView img_pic, click_addWater;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_WaterName = itemView.findViewById(R.id.tv_water_name);
            img_pic = itemView.findViewById(R.id.imgSendWaterPic);
            tv_WaterPrice = itemView.findViewById(R.id.tv_water_price);
            tv_WaterSealNum = itemView.findViewById(R.id.tv_water_SealNum);
            click_addWater = itemView.findViewById(R.id.click_addWater);
        }
    }

    public interface ClickLinsh {
        void click(int position, SendWaterBean.DataBean.ShopGoodsBean.GoodsBean waterList);
    }

    public ClickLinsh clickLinsh;

    public void setOnItemClickListener(ClickLinsh listener) {
        this.clickLinsh = listener;
    }

}
