package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.OriginBean;
import com.ztkj.wky.zhuantou.bean.SearchOriginBean;
import com.ztkj.wky.zhuantou.bean.UnOriginBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchItemOriginAdapter extends RecyclerView.Adapter {

    private Context mContext;


    public SearchItemOriginAdapter(Context context) {
        this.mContext = context;
        inviteMember= (InviteMember) context;
    }

    List<SearchOriginBean.DataBean> list = new ArrayList<>();

    public void setData(List<SearchOriginBean.DataBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }


    public void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_origin_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.tv_name.setText(list.get(i).getUsername());
        Glide.with(mContext).load(list.get(i).getHead()).into(viewHolder1.circle_real);
        if(list.get(i).getDepartment_id().equals("0")){
            viewHolder1.tv_invite.setBackground(mContext.getResources().getDrawable(R.drawable.yuanjiaobtn_lanse_16dp));
            viewHolder1.tv_invite.setEnabled(true);
        }else{
            viewHolder1.tv_invite.setBackground(mContext.getResources().getDrawable(R.drawable.yuanjiaobtn_16dp));
            viewHolder1.tv_invite.setEnabled(false);
        }
        viewHolder1.tv_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(i).getDepartment_id().equals("0")){
                    inviteMember.invite(list.get(i).getPhone());
                }
            }
        });

    }
    InviteMember inviteMember;
    public interface InviteMember{
        void invite(String phone);
    }




    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_real)
        CircleImageView circle_real;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_invite)
        TextView tv_invite;
//        @BindView(R.id.view)
//        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
