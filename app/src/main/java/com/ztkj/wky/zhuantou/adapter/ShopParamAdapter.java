package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopParamBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopParamAdapter extends BaseAdapter {

    private Context context;
    List<ShopParamBean.DataBean> list = new ArrayList<>();

    public ShopParamAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShopParamBean.DataBean> lists) {
        this.list = lists;
        notifyDataSetChanged();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_shop_param_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (list.get(i).getSp_name() != null) {
            viewHolder.left_param.setText(list.get(i).getSp_name());
        }
        if (list.get(i).getArr().getSp_name() != null) {
            viewHolder.paramName.setText(list.get(i).getArr().getSp_name());
        }
        return view;
    }

    static
    class ViewHolder {
        @BindView(R.id.param_name)
        TextView paramName;
        @BindView(R.id.left_param)
        TextView left_param;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
