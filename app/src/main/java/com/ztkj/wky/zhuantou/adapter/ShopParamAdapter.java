package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopParamAdapter extends BaseAdapter {

    private Context context;

    public ShopParamAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_shop_param_layout, null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.param_name)
        TextView paramName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
