package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopColorAdapter extends BaseAdapter {

    private Context context;
    private List<ShopDetailActivity.ShopBeanSize> list;
    boolean isFirst;

    public ShopColorAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopDetailActivity.ShopBeanSize> lists) {
        this.list = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

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
            view = LayoutInflater.from(context).inflate(R.layout.item_conlust_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.content.setText(list.get(i).getSk_name());
        if (list.get(i).isSelect()) {
            viewHolder.content.setTextColor(context.getResources().getColor(R.color.white));
            viewHolder.content.setBackground(context.getResources().getDrawable(R.drawable.yuanjiaobtnfourseklect));
        } else {
            viewHolder.content.setTextColor(context.getResources().getColor(R.color.t2));
            viewHolder.content.setBackground(context.getResources().getDrawable(R.drawable.yuanjiaobtnfours));
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
