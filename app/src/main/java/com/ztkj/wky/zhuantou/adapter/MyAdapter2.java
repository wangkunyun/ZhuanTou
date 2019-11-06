package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.H5.H52Activity;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.RvBean2;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<RvBean2.DataBean.ListBean> mData;
    private Context context;

    public MyAdapter2(List<RvBean2.DataBean.ListBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context).load(mData.get(position).getCover()).into(holder.img2);
        holder.textView.setText(mData.get(position).getT_title());
        holder.textView2.setText(mData.get(position).getT_degree()+" 万热度");
        holder.textView3.setText(position+1+"");
        if (position < 3){
            holder.img.setAlpha(1f);
            holder.textView3.setTextColor(Color.parseColor(Colorstring.baise));
        }else {
            holder.img.setAlpha(0f);
            holder.textView3.setTextColor(Color.parseColor(Colorstring.t1));
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, H52Activity.class);
                intent.putExtra("tid",mData.get(position).getTid()+"");
                intent.putExtra("title",mData.get(position).getT_title());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2,textView3;
        private ImageView img,img2;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            img  = itemView.findViewById(R.id.item2_lvyuan);
            img2 = itemView.findViewById(R.id.item2_img1);
            textView = itemView.findViewById(R.id.item2_title);
            textView2 = itemView.findViewById(R.id.item2_hot);
            textView3 = itemView.findViewById(R.id.item2_number);
            relativeLayout = itemView.findViewById(R.id.item2_r1);

        }
    }
}

