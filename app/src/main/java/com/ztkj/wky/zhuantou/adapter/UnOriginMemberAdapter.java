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
import com.ztkj.wky.zhuantou.bean.UnOriginBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UnOriginMemberAdapter extends RecyclerView.Adapter {

    private Context mContext;


    public UnOriginMemberAdapter(Context context) {
        this.mContext = context;
    }



    List<OriginBean.DataBean.DepartmentLiseBean> lists=new ArrayList<>();
    public void setDepartMember(List<OriginBean.DataBean.DepartmentLiseBean> data){
        this.lists=data;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_form_super_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.tv_name.setText(lists.get(i).getUsername());
        Glide.with(mContext).load(lists.get(i).getHead()).into(viewHolder1.circleImageView);

//        if (i == list.size() - 1) {
//            viewHolder1.view.setVisibility(View.GONE);
//        } else {
//            viewHolder1.view.setVisibility(View.VISIBLE);
//
//        }
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        CircleImageView circleImageView;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_super_name)
        TextView tv_super_name;
//        @BindView(R.id.view)
//        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
