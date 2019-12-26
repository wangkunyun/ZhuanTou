package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.Activity.mine.jf.ConversionShopDetails;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.MyZtBean;

import java.util.List;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ViewHolder> {
    private List<MyZtBean.DataBean.CommodityListBean> mData;

    private Context context;

    public MyAdapter4(List<MyZtBean.DataBean.CommodityListBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyAdapter4.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.myzt_item, parent, false);
        MyAdapter4.ViewHolder viewHolder = new MyAdapter4.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter4.ViewHolder holder, final int position) {

        Glide.with(context).load(mData.get(position).getCom_cover()).into(holder.img);
        holder.textView.setText(mData.get(position).getCom_name());
        holder.textView2.setText(mData.get(position).getCom_integral());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至积分兑换
                ConversionShopDetails.start(context);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2;
        private ImageView img;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.myztitem_img1);
            textView = itemView.findViewById(R.id.myztitem_tv1);
            textView2 = itemView.findViewById(R.id.myztitem_tv2);
            relativeLayout = itemView.findViewById(R.id.myztitem_rl);

        }
    }
}