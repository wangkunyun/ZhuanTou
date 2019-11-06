package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.BandingBankCardBean;

import java.util.List;

public class BankCarAdapter extends RecyclerView.Adapter<BankCarAdapter.ViewHolder> {
    private Context context;
    private List<BandingBankCardBean.DataBean> data;

    public BankCarAdapter(Context context, List<BandingBankCardBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_bankcar, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_bank_name.setText(data.get(i).getBank_name());
        viewHolder.tv_bank_type.setText(data.get(i).getCard_type());
        viewHolder.tv_bank_num.setText(data.get(i).getCard_number());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends MyAdapter.ViewHolder{
        private RelativeLayout lin_setBg;
        private ImageView icon_bankLogo;
        private TextView tv_bank_name,tv_bank_type,tv_bank_num;
        public ViewHolder(View itemView) {
            super(itemView);
            lin_setBg = itemView.findViewById(R.id.lin_setBg);
            icon_bankLogo = itemView.findViewById(R.id.icon_bank_logo);
            tv_bank_name = itemView.findViewById(R.id.tv_bank_name);
            tv_bank_type = itemView.findViewById(R.id.tv_bank_type);
            tv_bank_num = itemView.findViewById(R.id.tv_bank_num);
        }
    }

}
