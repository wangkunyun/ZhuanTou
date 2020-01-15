package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.AddressListBean;
import com.ztkj.wky.zhuantou.bean.OriginBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OriginAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private TextView tv_bumen_name;

    public OriginAdapter(Context context) {
        this.mContext = context;
        originlisten= (Originlisten) mContext;
    }

    List<OriginBean.DataBean> list = new ArrayList<>();

    public void setData(List<OriginBean.DataBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }


    public void clearData() {
        if (this.list != null) {
            this.list.clear();
            notifyDataSetChanged();
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_form_bumen_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        if (list != null) {
            viewHolder1.tv_bumen_name.setText(list.get(i).getDepartment_name() + "(" + list.get(i).getDepartment_count() + ")");
            if (i == list.size() - 1) {
                viewHolder1.view.setVisibility(View.GONE);
            } else {
                viewHolder1.view.setVisibility(View.VISIBLE);

            }
            viewHolder1.tv_bumen_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    originlisten.index(i);
                }
            });
        }



    }

    Originlisten originlisten;
    public interface Originlisten {
        void index(int pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_bumen_name)
        TextView tv_bumen_name;
        @BindView(R.id.view)
        View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
