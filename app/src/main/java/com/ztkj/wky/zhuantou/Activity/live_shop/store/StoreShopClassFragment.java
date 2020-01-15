package com.ztkj.wky.zhuantou.Activity.live_shop.store;

/**
 * 作者：wky
 * 功能描述：商品分类
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.SearchShopActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopClassActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean2;
import com.ztkj.wky.zhuantou.bean.StoreShopCataory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreShopClassFragment extends Fragment {


    @BindView(R.id.reView)
    RecyclerView reView;
    Unbinder unbinder;
    private Intent intent;
    String id;
    List<StoreShopCataory.DataBean> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_store_shop_class, container, false);
        unbinder = ButterKnife.bind(this, view);
        id= SPUtils.getInstance().getString("storeId");
        if(!id.equals("")){
            initData();
        }
        return view;
    }
    StoreShopCataory shopCataory;
    private void initData() {
        OkHttpUtils.post().url(Contents.shopStore+Contents.shopCatarory)
                .addParams("shop_id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            shopCataory=new Gson().fromJson(response,StoreShopCataory.class);
                            if(shopCataory.getData()!=null){
                                data=shopCataory.getData();
                                reView.setLayoutManager(new LinearLayoutManager(getContext()));
                                Adapter adapter = new Adapter();
                                reView.setAdapter(adapter);
                            }
                        }
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 外层的adapter
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_storeclass, viewGroup, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.item_class1_title.setText(data.get(i).getSsc_name());
            List<StoreShopCataory.DataBean.ChilddataBean> classX = data.get(i).getChilddata();
            Adapter2 adapter2 = new Adapter2(classX);
            viewHolder.reClass.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            viewHolder.reClass.setAdapter(adapter2);

            //点击跳转分类页
            viewHolder.item_rl_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchName=viewHolder.item_class1_title.getText().toString();
                    if(searchName!=null){
                        SearchShopActivity.start(getActivity(),searchName);
                    }
//                    intent = new Intent(getActivity(), ShopClassActivity.class);
//                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView item_class1_title;
            private RecyclerView reClass;
            private RelativeLayout item_rl_click;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_class1_title = itemView.findViewById(R.id.item_class1_title);
                reClass = itemView.findViewById(R.id.reClass);
                item_rl_click = itemView.findViewById(R.id.item_rl_click);
            }
        }
    }

    /**
     * 里面的adapter
     */
    class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
        List<StoreShopCataory.DataBean.ChilddataBean> classX;

        public Adapter2(List<StoreShopCataory.DataBean.ChilddataBean> classX) {
            this.classX = classX;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_layout_storeclass2, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.item_tv_class.setText(classX.get(i).getSsc_name());
            viewHolder.item_rl_click2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String searchName=viewHolder.item_tv_class.getText().toString();
                    if(searchName!=null){
                        SearchShopActivity.start(getActivity(),searchName);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return classX.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView item_tv_class;
            private RelativeLayout item_rl_click2;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_tv_class = itemView.findViewById(R.id.item_tv_class);
                item_rl_click2=itemView.findViewById(R.id.item_rl_click2);
            }
        }
    }
}
