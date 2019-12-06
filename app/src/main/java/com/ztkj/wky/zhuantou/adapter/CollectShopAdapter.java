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
import com.ztkj.wky.zhuantou.bean.CollecShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectShopAdapter extends RecyclerView.Adapter {

    private Context context;
    List<CollecShopBean.DataBean> list = new ArrayList<>();
    List<CollecShopBean.DataBean> listDelet = new ArrayList<>();
    boolean isExpand;

    public CollectShopAdapter(Context context) {
        this.context = context;
    }

    public void getSelectData() {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    listDelet.add(list.get(i));
                }
            }
            list.removeAll(listDelet);
            notifyDataSetChanged();
        }
    }

    CollectDelete collectDelete;

    public interface CollectDelete {
        void collectDelete(boolean deleteAll, String postion);
    }

    public void setCollectListen(CollectDelete collectDeletes) {
        this.collectDelete = collectDeletes;
    }

    public List<CollecShopBean.DataBean> getData() {
        if (this.list != null && this.list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public void clearData() {
        if (list != null && list.size() > 0) {
            this.list.clear();
        }
        notifyDataSetChanged();
    }

    public void setData(List<CollecShopBean.DataBean> lists) {
        this.list.addAll(lists);
        notifyDataSetChanged();
    }

    public void selectAll() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelect(true);
            }
            isExpand = true;
            notifyDataSetChanged();
        }

    }

    public void cancelSelectAll() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelect(false);
            }
            isExpand = false;
            notifyDataSetChanged();
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collect_shop_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        if (isExpand) {
            viewHolder1.shop_select.setVisibility(View.VISIBLE);
            viewHolder1.shop_select.setSelected(list.get(i).isSelect());
        } else {
            viewHolder1.shop_select.setVisibility(View.GONE);
            viewHolder1.shop_select.setSelected(list.get(i).isSelect());
        }
        Glide.with(context).load(list.get(i).getSc_img()).into(viewHolder1.ivCollect);
        viewHolder1.tvCollectName.setText(list.get(i).getSc_name());
        viewHolder1.tvShopPrice.setText(list.get(i).getSc_present_price());
        viewHolder1.rela_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                if (list.get(i).isSelect()) {
                    viewHolder1.shop_select.setSelected(false);
                    list.get(i).setSelect(false);
                } else {
                    viewHolder1.shop_select.setSelected(true);
                    list.get(i).setSelect(true);
                }
                boolean noSelect = false;
                //内层item选中状态改变后要遍历判断是否全选，以改变外层item的选中状态
                for (CollecShopBean.DataBean cartItemResultDtoList : list) {
                    if (!cartItemResultDtoList.isSelect()) {
                        noSelect = true;
                    }
                }
                if (noSelect) {
                    collectDelete.collectDelete(false, String.valueOf(i));
                } else {
                    collectDelete.collectDelete(true, null);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv_collect)
        ImageView ivCollect;
        @BindView(R.id.tv_collect_name)
        TextView tvCollectName;
        @BindView(R.id.tv_shop_price)
        TextView tvShopPrice;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.shop_select)
        ImageView shop_select;
        @BindView(R.id.rela_collect)
        RelativeLayout rela_collect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
