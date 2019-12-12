package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopsActivity;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.SearchBean;
import com.ztkj.wky.zhuantou.bean.SearchResultBean;
import com.ztkj.wky.zhuantou.bean.ShopSearchBean;
import com.ztkj.wky.zhuantou.bean.StringDesignUtil;
import com.ztkj.wky.zhuantou.homepage.AllServiceActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private List<SearchResultBean.DataBean> mData=new ArrayList<>();
    private Context context;


    public SearchItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SearchResultBean.DataBean> mData){
        this.mData=mData;
        notifyDataSetChanged();
    }
    public void clearData(){
        this.mData.clear();

    }

    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        SearchItemAdapter.ViewHolder viewHolder = new SearchItemAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SearchItemAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position).getSkw_word());
        holder.textView2.setText("商品");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchShopActivity.start(context, mData.get(position).getSkw_word());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textView2;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);

            textView = itemView.findViewById(R.id.sea_item1_zx);
            textView2 = itemView.findViewById(R.id.sea_item1_gn);
            relativeLayout = itemView.findViewById(R.id.sea_item1_bg);

        }
    }
}
