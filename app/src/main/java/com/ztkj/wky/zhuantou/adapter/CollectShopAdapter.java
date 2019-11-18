package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.CollectShopActivity;
import com.ztkj.wky.zhuantou.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CollectShopAdapter extends RecyclerView.Adapter {

    private Context context;
    List<CollectShopActivity.ShopBean> list = new ArrayList<>();
    List<CollectShopActivity.ShopBean> listDelet = new ArrayList<>();
    boolean isExpand;

    public CollectShopAdapter(Context context) {
        this.context = context;
    }

    public void getSelectData() {
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    listDelet.add(list.get(i));
//                    list.remove(i);
//                    notifyDataSetChanged();
                }
            }
            list.removeAll(listDelet);
            Log.e("Dfsfsfs", list.size() + "" + list.toString());
            notifyDataSetChanged();
        }
    }

    public List<CollectShopActivity.ShopBean> getData() {
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

    public void setData(List<CollectShopActivity.ShopBean> lists) {
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
        if (i == 0) {
            viewHolder1.view.setVisibility(View.VISIBLE);
        } else {
            viewHolder1.view.setVisibility(View.GONE);
        }
        if (isExpand) {
            viewHolder1.shop_select.setVisibility(View.VISIBLE);
            viewHolder1.shop_select.setSelected(list.get(i).isSelect());
        } else {
            viewHolder1.shop_select.setVisibility(View.INVISIBLE);
            viewHolder1.shop_select.setSelected(list.get(i).isSelect());
        }
        viewHolder1.shop_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(i).isSelect()) {
                    viewHolder1.shop_select.setSelected(false);
                    list.get(i).setSelect(false);
                } else {
                    viewHolder1.shop_select.setSelected(true);
                    list.get(i).setSelect(true);

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
