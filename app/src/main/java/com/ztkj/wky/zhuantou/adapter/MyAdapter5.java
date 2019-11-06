package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.DakaBean;

import java.util.List;

public class MyAdapter5 extends RecyclerView.Adapter<MyAdapter5.ViewHolder>{
    private List<DakaBean.DataBean.ListBean> mData;

    private Context context;

    public MyAdapter5(List<DakaBean.DataBean.ListBean>  mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MyAdapter5.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.daka_item, parent, false);
        MyAdapter5.ViewHolder viewHolder = new MyAdapter5.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter5.ViewHolder holder, final int position) {

        if (mData.get(position).getState().equals("未打卡")){
            holder.textView.setTextColor(Color.parseColor(Colorstring.t6));
        }else {
            holder.textView.setTextColor(Color.parseColor(Colorstring.t3));
        }
        if (mData.get(position).getSpanlength().equals("无")){
            holder.textView3.setTextColor(Color.parseColor(Colorstring.t6));
        }else {
            holder.textView3.setTextColor(Color.parseColor(Colorstring.t3));
        }

        holder.textView.setText(mData.get(position).getState());
        holder.textView2.setText(mData.get(position).getDatetime()+"  "+mData.get(position).getIntervaltime());
        holder.textView3.setText(mData.get(position).getSpanlength());


//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"暂未开通此服务",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView,textView2,textView3;
//        private ImageView img;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
//            img  = itemView.findViewById(R.id.myztitem_img1);
            textView = itemView.findViewById(R.id.dakaitem_tv1);
            textView2 = itemView.findViewById(R.id.dakaitem_day);
            textView3 = itemView.findViewById(R.id.dakaitem_hour);
            relativeLayout = itemView.findViewById(R.id.dakaitem_rl);

        }
    }
}
