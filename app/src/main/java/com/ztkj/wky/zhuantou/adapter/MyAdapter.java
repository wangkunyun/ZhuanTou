package com.ztkj.wky.zhuantou.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.H5.H5Activity;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.FangKeJiKvBean;
import com.ztkj.wky.zhuantou.bean.RvBean1;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<RvBean1.DataBean.ListBean> mData;
    private Context context;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;


    public MyAdapter(List<RvBean1.DataBean.ListBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    public MyAdapter(List<FangKeJiKvBean.DataBean> mData, StringCallback stringCallback) {

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_item1, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(8);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
//        Log.e("response200", "onBindViewHolder: " + mData.get(position).getCover());
        Glide.with(context).load(mData.get(position).getCover())
                .apply(options)
                .into(holder.img2);
        holder.textView.setText(mData.get(position).getTitle());
        holder.textView2.setText(mData.get(position).getIntroduce());
        if (mData.get(position).getPopular().equals("0")) {
            holder.img.setAlpha(0f);
        } else {
            holder.img.setAlpha(1f);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, H5Activity.class);
                intent.putExtra("aid", mData.get(position).getAid() + "");
                intent.putExtra("title", mData.get(position).getTitle() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2;
        private ImageView img, img2;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.item1_hot);
            img2 = itemView.findViewById(R.id.item1_img);
            textView = itemView.findViewById(R.id.item1_title);
            textView2 = itemView.findViewById(R.id.item1_content);
            relativeLayout = itemView.findViewById(R.id.item1_rl);
        }
    }
}
