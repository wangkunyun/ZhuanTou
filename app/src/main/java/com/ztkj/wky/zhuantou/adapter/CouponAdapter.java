package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponAdapter extends BaseAdapter {
    private Context context;
    List<ShopDetailBean.DataBean.CouponBean> list = new ArrayList<>();

    public CouponAdapter(Context context, List<ShopDetailBean.DataBean.CouponBean> lists) {
        this.context = context;
        this.list = lists;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_coupon_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvMoney.setText("¥"+list.get(i).getSuc_reduce_money());
        viewHolder.tvUserWay.setText("满"+list.get(i).getSuc_full_money()+"可用");
        viewHolder.time.setText(list.get(i).getSc_start_time()+"-"+list.get(i).getSc_end_time());
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_user_way)
        TextView tvUserWay;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
