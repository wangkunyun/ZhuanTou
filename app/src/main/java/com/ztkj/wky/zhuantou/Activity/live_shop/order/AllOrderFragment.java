package com.ztkj.wky.zhuantou.Activity.live_shop.order;

/**
 * 作者：wky
 * 功能描述：全部订单
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.orderdetails.WaitPayDetailsActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OrderBean;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment {


    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;
    private String TAG = "AllOrderFragment";
    private List<OrderBean.DataBean> data;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, container, false);
        unbinder = ButterKnife.bind(this, view);

        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getOrderList)
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("page", "1")
                .addParams("type", "1")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                OrderBean orderBean = GsonUtil.gsonToBean(response, OrderBean.class);
                if (orderBean.getErrno().equals("200")) {
                    data = orderBean.getData();
                    AdapterOut adapterOut = new AdapterOut();
                    reView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    reView.setAdapter(adapterOut);
                }
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    class AdapterOut extends RecyclerView.Adapter<AdapterOut.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_out, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            List<OrderBean.DataBean.ArrBean> arr;
            arr = data.get(i).getArr();
            viewHolder.item_reOrderOut.setLayoutManager(new LinearLayoutManager(getActivity()));
            AdapterIn adapterIn = new AdapterIn(arr);
            viewHolder.item_reOrderOut.setAdapter(adapterIn);
            //设置店铺logo
            if (!data.get(i).getSs_logo().equals("0")) {
                Glide.with(Objects.requireNonNull(getActivity())).load(data.get(i).getSs_logo()).into(viewHolder.item_imgOrderOutStoreHead);
            }
            //设置店铺名称
            viewHolder.item_tvOrderOutStoreName.setText(data.get(i).getSs_name());
            //商品数量
            viewHolder.item_tvOrderOutShopUnm.setText("共" + data.get(i).getArr().size() + "件商品");
            float sum = 0;
            for (int j = 0; j < data.get(i).getArr().size(); j++) {
                float sog_total_price = Float.parseFloat(arr.get(j).getSog_total_price());
                sum += sog_total_price;
            }
            viewHolder.itemOrderOutPrice.setText(sum + "");

            //点击进入详情页
            viewHolder.item_reOrderOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置订单状态
                    switch (data.get(i).getSso_state()) {
                        case "0":
                            WaitPayDetailsActivity.start(getActivity(), data.get(i).getSso_state(), data.get(i).getSso_sub_order_number());
                            break;
                        case "1":
                            viewHolder.item_tvOrderOutState.setText("待发货");
                            break;
                        case "2":
                            viewHolder.item_tvOrderOutState.setText("待收货");
                            break;
                        case "3":
                            viewHolder.item_tvOrderOutState.setText("交易成功");
                            break;
                        case "4":
                            viewHolder.item_tvOrderOutState.setText("交易关闭");
                            break;
                    }
                }
            });
            //设置订单状态
            switch (data.get(i).getSso_state()) {
                case "0":
                    viewHolder.item_tvOrderOutState.setText("待付款");
                    break;
                case "1":
                    viewHolder.item_tvOrderOutState.setText("待发货");
                    break;
                case "2":
                    viewHolder.item_tvOrderOutState.setText("待收货");
                    break;
                case "3":
                    viewHolder.item_tvOrderOutState.setText("交易成功");
                    break;
                case "4":
                    viewHolder.item_tvOrderOutState.setText("交易关闭");
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView item_reOrderOut;
            private ImageView item_imgOrderOutStoreHead;
            private TextView item_tvOrderOutStoreName, item_tvOrderOutState, item_tvOrderOutShopUnm, itemOrderOutPrice, item_clickOrderOutDel, item_clickOrderOutRefund;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_reOrderOut = itemView.findViewById(R.id.item_reOrderOut);
                item_imgOrderOutStoreHead = itemView.findViewById(R.id.item_imgOrderOutStoreHead);
                item_tvOrderOutStoreName = itemView.findViewById(R.id.item_tvOrderOutStoreName);
                item_tvOrderOutState = itemView.findViewById(R.id.item_tvOrderOutState);
                item_tvOrderOutShopUnm = itemView.findViewById(R.id.item_tvOrderOutShopUnm);
                itemOrderOutPrice = itemView.findViewById(R.id.itemOrderOutPrice);
                item_clickOrderOutDel = itemView.findViewById(R.id.item_clickOrderOutDel);
                item_clickOrderOutRefund = itemView.findViewById(R.id.item_clickOrderOutRefund);
            }
        }
    }

    class AdapterIn extends RecyclerView.Adapter<AdapterIn.ViewHolder> {
        private List<OrderBean.DataBean.ArrBean> arr;

        public AdapterIn(List<OrderBean.DataBean.ArrBean> arr) {
            this.arr = arr;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_order_in, viewGroup, false);

            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Glide.with(Objects.requireNonNull(getContext())).load(arr.get(i).getSc_img()).into(viewHolder.item_imgOrderInPic);
            viewHolder.item_tvOrderInTitle.setText(arr.get(i).getSog_name());
            viewHolder.item_tvOrderInSku.setText(arr.get(i).getSog_sku_name());
            viewHolder.item_tvOrderInBuyNum.setText(arr.get(i).getSog_number() + "件");
            viewHolder.tv_itemOrderInPrice.setText(arr.get(i).getSog_total_price());
            switch (arr.get(i).getSog_refund_type()) {
                case "0":
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.GONE);
                    break;
                case "2":
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.VISIBLE);
                    break;
                default:
                    viewHolder.item_tvOrderInIsRefund.setVisibility(View.VISIBLE);
                    viewHolder.item_tvOrderInIsRefund.setText("退款中");
            }
        }

        @Override
        public int getItemCount() {
            return arr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView item_imgOrderInPic;
            private TextView item_tvOrderInTitle, item_tvOrderInSku, item_tvOrderInIsRefund, item_tvOrderInBuyNum, tv_itemOrderInPrice;
            private RelativeLayout rl_clickOrderIn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_imgOrderInPic = itemView.findViewById(R.id.item_imgOrderInPic);
                item_tvOrderInTitle = itemView.findViewById(R.id.item_tvOrderInTitle);
                item_tvOrderInSku = itemView.findViewById(R.id.item_tvOrderInSku);
                item_tvOrderInIsRefund = itemView.findViewById(R.id.item_tvOrderInIsRefund);
                item_tvOrderInBuyNum = itemView.findViewById(R.id.item_tvOrderInBuyNum);
                tv_itemOrderInPrice = itemView.findViewById(R.id.tv_itemOrderInPrice);
                rl_clickOrderIn = itemView.findViewById(R.id.rl_clickOrderIn);
            }
        }
    }
}
