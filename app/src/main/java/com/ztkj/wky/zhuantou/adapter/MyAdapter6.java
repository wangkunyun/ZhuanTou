package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.SearchBean;
import com.ztkj.wky.zhuantou.bean.StringDesignUtil;
import com.ztkj.wky.zhuantou.homepage.AllServiceActivity;

import java.util.List;

public class MyAdapter6 extends RecyclerView.Adapter<MyAdapter6.ViewHolder>{
    private List<SearchBean.DataBean.EnterpriseBean> mData;
    private String searchtv;
    private Context context;

    public MyAdapter6(List<SearchBean.DataBean.EnterpriseBean>  mData, Context context,String searchtv) {
        this.mData = mData;
        this.context = context;
        this.searchtv = searchtv;
    }

    @Override
    public MyAdapter6.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        MyAdapter6.ViewHolder viewHolder = new MyAdapter6.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter6.ViewHolder holder, final int position) {

        holder.textView.setText(StringDesignUtil.getSpanned(mData.get(position).getEname(), searchtv, Colorstring.t3));
        holder.textView2.setText("功能");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllServiceActivity.class);
                intent.putExtra("eid",mData.get(position).getEid());
                intent.putExtra("title",mData.get(position).getEname());
                intent.putExtra("ase","0");
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

