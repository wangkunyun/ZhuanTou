package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.baidu.mapapi.search.core.PoiInfo;
import com.ztkj.wky.zhuantou.R;

import java.util.List;


public class PoiListAdapter extends BaseAdapter {

    private Context mcontext;
    private List<PoiInfo> mPoilist;

    public PoiListAdapter(Context mcontext, List<PoiInfo> mPoilist) {
        this.mcontext = mcontext;
        this.mPoilist = mPoilist;
    }

    @Override
    public int getCount() {
        if (mPoilist != null) {
            return mPoilist.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return mPoilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mcontext, R.layout.poi_item, null);
            viewHolder.poiName = convertView.findViewById(R.id.poi_name);
            viewHolder.poiAddress =convertView.findViewById(R.id.poi_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.poiName.setText(mPoilist.get(position).getName());
        viewHolder.poiAddress.setText(mPoilist.get(position).getAddress());
        return convertView;
    }



    private static class ViewHolder {
        TextView poiName;
        TextView poiAddress;
    }






}
