package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ShopCartBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCartDentailDetailAdapter extends RecyclerView.Adapter {

    List<ShopCartBean.DataBean.SubordinateBean> list = new ArrayList<>();
    int isExpand;
    Context context;
    ShopCartBean.DataBean shortCartBean;
    ShopCartAdapter shopCartAdapter;

    public ShopCartDentailDetailAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    public void setData(List<ShopCartBean.DataBean.SubordinateBean> mList, ShopCartBean.DataBean shortCartBeans, ShopCartAdapter shopCartAdapters, int isExpand) {
        this.list.addAll(mList);
        this.isExpand = isExpand;
        this.shortCartBean = shortCartBeans;
        this.shopCartAdapter = shopCartAdapters;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_cart_detail_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;

        viewHolder1.ivIsDetailSelect.setOnCheckedChangeListener(null);
        viewHolder1.orderPrice.setText(list.get(i).getPrice());
        final ShopCartBean.DataBean.SubordinateBean cartBean = list.get(i);
        //读取实体内存储的选中状态
        viewHolder1.ivIsDetailSelect.setChecked(cartBean.isSelect());
        viewHolder1.ivIsDetailSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
                if (list.get(i).isSelect()) {
                    list.get(i).setSelect(false);
                    viewHolder1.ivIsDetailSelect.setSelected(false);
                } else {
                    list.get(i).setSelect(true);
                    viewHolder1.ivIsDetailSelect.setSelected(true);
                }
                notifyDataSetChanged();
                boolean noSelect = false;
                //内层item选中状态改变后要遍历判断是否全选，以改变外层item的选中状态
                for (ShopCartBean.DataBean.SubordinateBean cartItemResultDtoList : list) {
                    if (!cartItemResultDtoList.isSelect()) {
                        noSelect = true;
                    }
                }
                if (!noSelect) {
                    shortCartBean.setSelect(!noSelect);
                } else {
                    shortCartBean.setSelect(!noSelect);
                }
                shopCartAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(shopCartAdapter.getAllPrice());

            }
        });

        /**
         * 加号的点击
         */
        viewHolder1.addShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.valueOf(viewHolder1.num.getText().toString());
                number++;
                list.get(i).setNum(number);
                viewHolder1.num.setText(number + "");
                notifyDataSetChanged();
                EventBus.getDefault().post(shopCartAdapter.getAllPrice());

            }
        });

        /**
         *  点击减号
         */
        viewHolder1.reduceShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.valueOf(viewHolder1.num.getText().toString());
                if (number > 1) {
                    number--;
                    list.get(i).setNum(number);
                    viewHolder1.num.setText(number + "");
                    notifyDataSetChanged();
                    EventBus.getDefault().post(shopCartAdapter.getAllPrice());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    public interface SmallShopListen {
        void selectSmallShop(int position);
    }

    SmallShopListen smallShopListen;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_is_detail_select)
        CheckBox ivIsDetailSelect;
        @BindView(R.id.order_pic)
        ImageView orderPic;
        @BindView(R.id.tv_order_name)
        TextView tvOrderName;
        @BindView(R.id.type_shop_canshu)
        TextView typeShopCanshu;
        @BindView(R.id.order_price)
        TextView orderPrice;
        @BindView(R.id.reduce_shop_cart)
        RelativeLayout reduceShopCart;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.add_shop_cart)
        RelativeLayout addShopCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
