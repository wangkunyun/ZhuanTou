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
import com.ztkj.wky.zhuantou.bean.GouWuCheBean;

import java.util.ArrayList;

public class GouWuCheAdapter extends RecyclerView.Adapter<GouWuCheAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GouWuCheBean> gouWuCheList;

    public GouWuCheAdapter(Context context, ArrayList<GouWuCheBean> gouWuCheList) {
        this.context = context;
        this.gouWuCheList = gouWuCheList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_item_gouwuche, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_name.setText(gouWuCheList.get(i).getName());
        viewHolder.tv_price.setText(gouWuCheList.get(i).getPrice());
        viewHolder.tv_num.setText(gouWuCheList.get(i).getNum());
        Glide.with(context).load(gouWuCheList.get(i).getImg()).into(viewHolder.img_pic);
        viewHolder.img_addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickAdd != null) {
                    clickAdd.click(i, gouWuCheList.get(i));
                }
            }
        });
        viewHolder.img_removeShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickDel != null) {
                    clickDel.click(i, gouWuCheList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gouWuCheList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_pic, img_addShop, img_removeShop;
        private TextView tv_name, tv_price, tv_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pic = itemView.findViewById(R.id.imgSendWaterPic);
            img_addShop = itemView.findViewById(R.id.click_addWater);
            img_removeShop = itemView.findViewById(R.id.click_removeWater);
            tv_name = itemView.findViewById(R.id.tv_water_name);
            tv_price = itemView.findViewById(R.id.tv_water_price);
            tv_num = itemView.findViewById(R.id.tv_gouwuchenNum);


        }
    }

    public interface ClickAdd {
        void click(int position, GouWuCheBean gouWuCheList);
    }

    public ClickAdd clickAdd;

    public void setOnItemClickListener(ClickAdd listener) {
        this.clickAdd = listener;
    }

    public interface ClickDel {
        void click(int position, GouWuCheBean gouWuCheList);
    }

    public ClickDel clickDel;

    public void setOnItemClickListener(ClickDel listener) {
        this.clickDel = listener;
    }
}
