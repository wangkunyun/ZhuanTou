package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.CollectShopActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.RecorderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecorderAdapter extends RecyclerView.Adapter {


    private Context context;
    RecorderDetailAdapter recorderDetailAdapter;
    List<RecorderBean.DataBean> list=new ArrayList<>();

    public RecorderAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<RecorderBean.DataBean> lists) {
        this.list=lists;
        notifyDataSetChanged();
    }

    public void clearData() {
        if (list != null && list.size() > 0) {
            this.list.clear();
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecorderAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recoder_top_layout, null));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.reycle_rorder.setLayoutManager(new LinearLayoutManager(context));
        recorderDetailAdapter = new RecorderDetailAdapter(context);
        viewHolder1.reycle_rorder.setAdapter(recorderDetailAdapter);
        viewHolder1.tv_recorder_time.setText(list.get(i).getTime());
        recorderDetailAdapter.setData(list.get(i).getArr());

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recorder_time)
        TextView tv_recorder_time;
        @BindView(R.id.reycle_rorder)
        RecyclerView reycle_rorder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}