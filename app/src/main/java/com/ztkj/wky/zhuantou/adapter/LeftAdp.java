package com.ztkj.wky.zhuantou.adapter;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ztkj.wky.zhuantou.R;

import java.util.Map;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/22
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public class LeftAdp extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

    public LeftAdp(int item_send_water_shoplist) {
        super(item_send_water_shoplist);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(BaseViewHolder helper, Map<String, String> item) {
        helper.setText(R.id.tv_title_shop, item.get("name"));
        TextView text_name = helper.itemView.findViewById(R.id.tv_title_shop);
        TextView sendWaterSelect = helper.itemView.findViewById(R.id.sendWaterSelect);
        RelativeLayout rl_sendWaterLeft = helper.itemView.findViewById(R.id.rl_sendWaterLeft);
        String type = item.get("type");
        if (type.equals("1")) {
            text_name.setTextColor(ContextCompat.getColor(mContext, R.color.t6));
            sendWaterSelect.setVisibility(View.GONE);
            rl_sendWaterLeft.setBackgroundColor(ContextCompat.getColor(mContext, R.color.f8));
        } else {
            text_name.setTextColor(ContextCompat.getColor(mContext, R.color.t5));
            sendWaterSelect.setVisibility(View.VISIBLE);
            rl_sendWaterLeft.setBackgroundColor(ContextCompat.getColor(mContext, R.color.baise));
        }
    }

}