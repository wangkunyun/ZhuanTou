package com.ztkj.wky.zhuantou.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.BillListBean;

import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder> {
    private Context context;
    private List<BillListBean.DataBean> data;

    public BillListAdapter(Context context, List<BillListBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_billlist, viewGroup, false);

        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvType.setText(data.get(i).getReason());
        viewHolder.tvTime.setText(data.get(i).getAddtime());
        if (data.get(i).getType().equals("1")) {
            viewHolder.tvMoney.setTextColor(Color.parseColor("#FFA33F"));
            viewHolder.tvMoney.setText("+ ¥" + data.get(i).getMoney());
        } else if (data.get(i).getType().equals("2")) {
            viewHolder.tvMoney.setTextColor(Color.parseColor("#65C9D2"));
            viewHolder.tvMoney.setText("- ¥" + data.get(i).getMoney());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends MyAdapter.ViewHolder {
        private TextView tvType, tvTime, tvMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvBillType);
            tvTime = itemView.findViewById(R.id.tvBillTime);
            tvMoney = itemView.findViewById(R.id.tvBillMoney);
        }
    }
}
