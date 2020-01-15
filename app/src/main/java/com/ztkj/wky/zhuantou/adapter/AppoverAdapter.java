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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

public class AppoverAdapter extends RecyclerView.Adapter<AppoverAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> appovername;
    private ArrayList<String> appoverhead;

    public AppoverAdapter(Context context, ArrayList<String> appovername, ArrayList<String> appoverhead) {
        this.context = context;
        this.appovername = appovername;
        this.appoverhead = appoverhead;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_item_appover, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.item_tv.setText(appovername.get(i));
        if (!appoverhead.get(i).equals("0")) {
            //设置图片圆角角度
            RoundedCorners roundedCorners = new RoundedCorners(96);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(context).load(appoverhead.get(i)).apply(options).into(viewHolder.item_img);
        } else {
            viewHolder.item_img.setImageResource(R.drawable.head_portrait);
        }
    }

    @Override
    public int getItemCount() {
        return appoverhead.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_img;
        private TextView item_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_details_img_appover);
            item_tv = itemView.findViewById(R.id.item_details_tv_appover);
        }
    }
}
