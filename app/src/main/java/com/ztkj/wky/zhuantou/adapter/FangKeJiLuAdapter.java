package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.FangKeJiKvBean;

import java.util.List;

public class FangKeJiLuAdapter extends RecyclerView.Adapter<FangKeJiLuAdapter.ViewHolder> {

    List<FangKeJiKvBean.DataBean> data2;
    private Context context;

    public FangKeJiLuAdapter(List<FangKeJiKvBean.DataBean> data2, Context context) {
        this.data2 = data2;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.re_fangkejilv, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView_name.setText(data2.get(position).getName());
        holder.textView_do.setText(data2.get(position).getRemarks());
        holder.textView_time.setText(data2.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_name, textView_do, textView_time;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.re_name);
            textView_do = itemView.findViewById(R.id.re_do);
            textView_time = itemView.findViewById(R.id.re_time);
        }
    }
}
