package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddressAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public AddressAdapter(Context context) {
        this.mContext = context;
    }

    public void setData() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_add_address_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.n3_headImg)
        CircleImageView n3HeadImg;
        @BindView(R.id.go_back)
        ImageView goBack;
        @BindView(R.id.cofirm_user_name)
        TextView cofirmUserName;
        @BindView(R.id.confirm_user_phone)
        TextView confirmUserPhone;
        @BindView(R.id.confim_user_info)
        RelativeLayout confimUserInfo;
        @BindView(R.id.rela_address)
        LinearLayout relaAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
