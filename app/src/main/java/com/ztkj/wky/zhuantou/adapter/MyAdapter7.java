package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.H5.H5Activity;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.SearchBean;
import com.ztkj.wky.zhuantou.bean.StringDesignUtil;

import java.util.List;

public class MyAdapter7 extends RecyclerView.Adapter<MyAdapter7.ViewHolder>{
    private List<SearchBean.DataBean.ListBean> mData;
    private String searchtv;
    private Context context;

    public MyAdapter7(List<SearchBean.DataBean.ListBean>  mData, Context context,String searchtv) {
        this.mData = mData;
        this.context = context;
        this.searchtv = searchtv;
    }

    @Override
    public MyAdapter7.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        MyAdapter7.ViewHolder viewHolder = new MyAdapter7.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter7.ViewHolder holder, final int position) {

//        holder.textView.setText(mData.get(position).getState());
//        holder.textView2.setText(mData.get(position).getDatetime()+"  "+mData.get(position).getIntervaltime());
//        holder.textView3.setText(mData.get(position).getSpanlength());

        holder.textView2.setText("文章");
        holder.textView.setText(StringDesignUtil.getSpanned(mData.get(position).getTitle(), searchtv, Colorstring.t3));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, H5Activity.class);
                intent.putExtra("aid",mData.get(position).getAid());
                intent.putExtra("title",mData.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView,textView2;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.sea_item1_zx);
            textView2 = itemView.findViewById(R.id.sea_item1_gn);
            relativeLayout = itemView.findViewById(R.id.sea_item1_bg);

        }
    }
}

