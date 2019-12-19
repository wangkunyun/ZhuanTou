package com.ztkj.wky.zhuantou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OrderBean;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopCartAdapter extends RecyclerView.Adapter {


    private Context mContext;

    List<OrderBean.DataBean> list = new ArrayList<>();
    List<OrderBean.DataBean> listSelect = new ArrayList<>();
    OrderBean.DataBean cartBean;

    public ShopCartAdapter(Context context) {
        this.mContext = context;
    }

    int isExpand;

    public void setData(List<OrderBean.DataBean> mList, int isExpand) {
        this.list.addAll(mList);
        this.isExpand = isExpand;
        notifyDataSetChanged();
    }

    public int getData() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    public List<OrderBean.DataBean> listData() {
        if (list != null) {
            return list;
        } else {
            return null;
        }
    }


    public ShopCartDentailDetailAdapter shopCartDentailDetailAdapter;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shop_cart_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        cartBean = list.get(i);
        Glide.with(mContext).load(cartBean.getSs_logo()).into(holder.n3HeadImg);
        holder.ivIsSelectCart.setOnCheckedChangeListener(null);
        //读取实体内存储的选中状态
        holder.tvShopName.setText(cartBean.getSs_name());
        holder.ivIsSelectCart.setChecked(cartBean.isSelect());
        holder.ivIsSelectCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //更改item选中状态同时进行实体内的选中状态改变
                list.get(i).setSelect(isChecked);
                if (isChecked) {
                    listSelectIS.add(list.get(i));
                } else {
                    if (listSelectIS != null && listSelectIS.size() > 0) {
                        listSelectIS.remove(list.get(i));
                    }
                }
                //外层选中状态改变后，要遍历改变子recyclerView内item的选中状态
                for (OrderBean.DataBean.ArrBean cartItemResultDtoList : list.get(i).getArr()) {
                    cartItemResultDtoList.setSelect(isChecked);
                }
                setAllShopListens.shopListen(isAllSelect());
                notifyDataSetChanged();
                EventBus.getDefault().post(getAllPrice());
            }
        });

        holder.itemView.setTag(cartBean);//传object回去
        if (list != null && list.size() > 0) {
            shopCartDentailDetailAdapter = new ShopCartDentailDetailAdapter(mContext);
            holder.recycleShopDetail.setLayoutManager(new LinearLayoutManager(mContext));
            holder.recycleShopDetail.setAdapter(shopCartDentailDetailAdapter);
            shopCartDentailDetailAdapter.setData(list.get(i).getArr(), cartBean, this, isExpand);
            shopCartDentailDetailAdapter.notifyDataSetChanged();
            shopCartDentailDetailAdapter.setSmallListen(new ShopCartDentailDetailAdapter.SmallShopListen() {
                @Override
                public void selectSmallShop(boolean select) {
                    if (select) {
                        listSelectIS.add(list.get(i));
                        setAllShopListens.shopListen(isAllSelect());
                    } else {
                        if (listSelectIS != null && listSelectIS.size() > 0) {
                            listSelectIS.remove(list.get(i));
                        }
                        setAllShopListens.shopListen(isAllSelect());
                    }
                }
            });
            holder.recycleShopDetail.setFocusableInTouchMode(false);
            holder.recycleShopDetail.requestFocus();
            //单个商家的商品列表不需要滑动，所以在这里禁止掉商品item的滑动事件
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            holder.recycleShopDetail.setLayoutManager(linearLayoutManager);
        }

    }


    List<OrderBean.DataBean> listSelectIS = new ArrayList<>();
    SetAllShopListen setAllShopListens;

    public interface SetAllShopListen {
        void shopListen(boolean isSlect);
    }

    public void setShopListen(SetAllShopListen setAllShopListen) {
        this.setAllShopListens = setAllShopListen;
    }

    public boolean isAllSelect() {
        if (list.size() == listSelectIS.size()) {
            return true;
        } else {
            return false;
        }
    }

    //设置全选/全不选
    public void setAllselect(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(b);
            for (OrderBean.DataBean.ArrBean cartItemResultDtoList : list.get(i).getArr()) {
                cartItemResultDtoList.setSelect(b);
            }
        }
        notifyDataSetChanged();
        //发送 消息
        EventBus.getDefault().post(getAllPrice());
    }

    List<OrderBean.DataBean> listGroup = new ArrayList<>();
    List<OrderBean.DataBean.ArrBean> listChild = new ArrayList<>();
    public String ssc_id;

    public void getSelect() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    listGroup.add(list.get(i));
                    for (int j = 0; j < list.get(i).getArr().size(); j++) {
                        if (list.get(i).getArr().get(j).isSelect()) {
                            if (j != 0) {
                                stringBuilder.append(",");
                            }
                            ssc_id = stringBuilder.append(list.get(i).getArr().get(j).getSsc_id()).toString();
                        }
                    }
                } else {
                    for (int j = 0; j < list.get(i).getArr().size(); j++) {
                        if (list.get(i).getArr().get(j).isSelect()) {
                            listChild.add(list.get(i).getArr().get(j));
                            ssc_id = stringBuilder.append(list.get(i).getArr().get(j).getSsc_id()).toString();
                            if (j != list.get(i).getArr().size() - 1) {
                                stringBuilder.append(",");
                            }
                        }
                    }
                    list.get(i).getArr().removeAll(listChild);
                }
            }
            list.removeAll(listGroup);
        }
        if (ssc_id != null) {
            deleteCart();
        }
        notifyDataSetChanged();
        EventBus.getDefault().post(getAllPrice());
    }

    private void deleteCart() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.deleltCart)
                .addParams("ssc_id", ssc_id)
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
//                        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
//                        ToastUtils.showShort("删除成功");
                    }
                });
    }

    List<OrderBean.DataBean.ArrBean> arrBeanList = new ArrayList<>();
    List<OrderBean.DataBean> dataBeanList = new ArrayList<>();

    public List<OrderBean.DataBean> getSelectList() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isSelect()) {
                    listSelect.add(list.get(i));

                } else {
                    for (OrderBean.DataBean.ArrBean cartItemResultDtoList : list.get(i).getArr()) {
                        if (cartItemResultDtoList.isSelect()) {
                            arrBeanList.add(cartItemResultDtoList);
                        }
//                        list.get(i).setArr(arrBeanList);
                        if (listSelect.size() == 0) {
                            dataBeanList.add(list.get(i));
                            dataBeanList.get(0).setArr(arrBeanList);
                            listSelect = dataBeanList;
                        } else {
                            listSelect.get(0).setArr(arrBeanList);
                        }
                        Log.e("dfsfafa", "" + listSelect.get(0).getArr().size());
                    }
                }
            }

        }
        return listSelect;
    }

    StringBuilder stringBuilder = new StringBuilder();

    //获取需要商品总价格
    public String getAllPrice() {
        BigDecimal allprice = new BigDecimal("0");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                List<OrderBean.DataBean.ArrBean> data = list.get(i).getArr();
                for (int y = 0; y < data.size(); y++) {
                    if (data.get(y).isSelect()) {
                        BigDecimal interestRate = new BigDecimal(data.get(y).getSsc_number());
                        BigDecimal interest = new BigDecimal(data.get(y).getSsc_unit_price()).multiply(interestRate);
                        allprice = allprice.add(interest);
                        Log.e("allprice", allprice + "");
                    }
                }
            }
        }
        return allprice.toString();
    }


    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_is_select_cart)
        CheckBox ivIsSelectCart;
        @BindView(R.id.n3_headImg)
        CircleImageView n3HeadImg;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.recycle_shop_detail)
        RecyclerView recycleShopDetail;
        @BindView(R.id.item_big_shop)
        LinearLayout item_big_shop;
        @BindView(R.id.item_enter_shop)
        RelativeLayout item_enter_shop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
