package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResuseWayAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ResuseWayAdapter(Context context, List<String> lists) {
        this.context = context;
        this.list = lists;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_reason_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.shop_tv_reason.setText(list.get(i));
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.shop_tv_reason)
        TextView shop_tv_reason;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
