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

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;

public class PayTypeBankCarAdapter extends RecyclerView.Adapter<PayTypeBankCarAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> payTypeList;
    private ArrayList<String> payTypeListCardName;

    public PayTypeBankCarAdapter(Context context, ArrayList<String> payTypeList, ArrayList<String> payTypeListCardName) {
        this.context = context;
        this.payTypeList = payTypeList;
        this.payTypeListCardName = payTypeListCardName;
    }

    @NonNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_pay_type_bankcard, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.payTypeBankCardName.setText(payTypeList.get(i));
        viewHolder.click_rl_payType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.payTypeBankCardRight.setVisibility(View.VISIBLE);
            }
        });
        switch (payTypeListCardName.get(i)) {
            case "微信支付":
                Glide.with(context).load(R.mipmap.icon_wx).into(viewHolder.payTypeBankCardLogo);
                break;
            case "支付宝":
                Glide.with(context).load(R.mipmap.icon_zfb).into(viewHolder.payTypeBankCardLogo);
                break;
            case "招商银行":
                Glide.with(context).load(R.mipmap.icon_bank_zhaoshang).into(viewHolder.payTypeBankCardLogo);
                break;
            case "中国银行":
                Glide.with(context).load(R.mipmap.icon_china_bank).into(viewHolder.payTypeBankCardLogo);
                break;
            case "浦发银行":
                Glide.with(context).load(R.mipmap.icon_bank_pufa).into(viewHolder.payTypeBankCardLogo);
                break;
            case "中信银行":
                Glide.with(context).load(R.mipmap.icon_bank_zhongxin).into(viewHolder.payTypeBankCardLogo);
                break;
            case "交通银行":
                Glide.with(context).load(R.mipmap.icon_bank_jiaotong).into(viewHolder.payTypeBankCardLogo);
                break;
            case "建设银行":
                Glide.with(context).load(R.mipmap.icon_bank_jianshe).into(viewHolder.payTypeBankCardLogo);
                break;
            case "光大银行":
                Glide.with(context).load(R.mipmap.icon_bank_guangda).into(viewHolder.payTypeBankCardLogo);
                break;
            case "工商银行":
                Glide.with(context).load(R.mipmap.icon_bank_guangshang).into(viewHolder.payTypeBankCardLogo);
                break;
            case "农业银行":
                Glide.with(context).load(R.mipmap.icon_bank_nongye).into(viewHolder.payTypeBankCardLogo);
                break;
            case "邮政储蓄银行":
                Glide.with(context).load(R.mipmap.icon_bank_youzheng).into(viewHolder.payTypeBankCardLogo);
                break;
            default:
                Glide.with(context).load(R.mipmap.icon_bank_others).into(viewHolder.payTypeBankCardLogo);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return payTypeList.size();
    }

    class ViewHolder extends MyAdapter.ViewHolder {
        private ImageView payTypeBankCardLogo, payTypeBankCardRight;
        private TextView payTypeBankCardName;
        private RelativeLayout click_rl_payType;

        public ViewHolder(View itemView) {
            super(itemView);

            payTypeBankCardLogo = itemView.findViewById(R.id.payTypeBankLogo);
            payTypeBankCardRight = itemView.findViewById(R.id.payTypeBankCardRight);
            payTypeBankCardName = itemView.findViewById(R.id.payTypeBankName);
            click_rl_payType = itemView.findViewById(R.id.click_rl_payType);
        }
    }

}
