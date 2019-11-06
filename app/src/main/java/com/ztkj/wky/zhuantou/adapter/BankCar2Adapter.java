package com.ztkj.wky.zhuantou.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.mine.ForgetPayWord3Activity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.BandingBankCardBean;

import java.util.List;

public class BankCar2Adapter extends RecyclerView.Adapter<BankCar2Adapter.ViewHolder> {
    private Context context;
    private List<BandingBankCardBean.DataBean> data;

    public BankCar2Adapter(Context context, List<BandingBankCardBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_bankcard2, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_bank_name.setText(data.get(i).getBank_name()+" 储蓄卡");
        viewHolder.rl_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ForgetPayWord3Activity.class);
                intent.putExtra("bankCardName",data.get(i).getBank_name());
                intent.putExtra("bankCardNum",data.get(i).getCard_number());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends MyAdapter.ViewHolder{
        private RelativeLayout rl_Click;
        private TextView tv_bank_name;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_bank_name = itemView.findViewById(R.id.tv_cardName);
            rl_Click = itemView.findViewById(R.id.rl_click);
        }
    }

}
