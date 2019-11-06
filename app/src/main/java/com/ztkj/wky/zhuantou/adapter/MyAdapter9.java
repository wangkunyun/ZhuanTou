package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.NearBean;
import com.ztkj.wky.zhuantou.homepage.AllServiceActivity;

import java.util.List;

public class MyAdapter9 extends RecyclerView.Adapter<MyAdapter9.ViewHolder>{
    private List<NearBean.DataBean> mData;

    private Context context;

    public MyAdapter9(List<NearBean.DataBean>  mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyAdapter9.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.nearlist_item, parent, false);
        MyAdapter9.ViewHolder viewHolder = new MyAdapter9.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter9.ViewHolder holder, final int position) {

//        holder.textView.setText(mData.get(position).getState());
//        holder.textView2.setText(mData.get(position).getDatetime()+"  "+mData.get(position).getIntervaltime());
//        holder.textView3.setText(mData.get(position).getSpanlength());

//        holder.textView.setText(mData.get(position).getTitle());
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(8);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(mData.get(position).getBu_image())
                .apply(options)
                .into(holder.imageView);
        holder.textView.setText(mData.get(position).getBu_name());
        holder.textView2.setText(mData.get(position).getKey_word());
        holder.textView3.setText(mData.get(position).getBu_address());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllServiceActivity.class);
                intent.putExtra("eid",mData.get(position).getBu_id());
                intent.putExtra("ase","1");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView,textView2,textView3;
        private ImageView imageView;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.nearlist_t1);
            textView2 = itemView.findViewById(R.id.nearlist_t2);
            textView3 = itemView.findViewById(R.id.nearlist_t3);
            imageView = itemView.findViewById(R.id.nearlist_img);
            relativeLayout = itemView.findViewById(R.id.nearlsit_bc);

        }
    }
}



