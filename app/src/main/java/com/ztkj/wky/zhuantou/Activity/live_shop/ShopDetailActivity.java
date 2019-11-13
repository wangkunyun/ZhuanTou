package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopDetailAdapter;
import com.ztkj.wky.zhuantou.adapter.LiveShopListAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {

    @BindView(R.id.guess_like_list)
    RecyclerView guess_like_list;
    @BindView(R.id.shop_list)
    RecyclerView shop_list;
    LiveShopListAdapter liveShopListAdapter;
    @BindView(R.id.shop_detail_img)
    RecyclerView shop_detail_img;
    LiveShopDetailAdapter liveShopDetailAdapter;
    @BindView(R.id.layout_back)
    ImageView layout_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        initData();
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json, JsonBean.class);
        List<JsonBean.ListBean> list = jsonBean.getList();
        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(ShopDetailActivity.this, list);
        guess_like_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 2));
        guess_like_list.setAdapter(liveShopAdapter);
        shop_list.setLayoutManager(new GridLayoutManager(ShopDetailActivity.this, 3));
        liveShopListAdapter = new LiveShopListAdapter(ShopDetailActivity.this, list);
        shop_list.setAdapter(liveShopListAdapter);
        shop_detail_img.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
        liveShopDetailAdapter = new LiveShopDetailAdapter(ShopDetailActivity.this, list);
        shop_detail_img.setAdapter(liveShopDetailAdapter);
        shop_detail_img.setNestedScrollingEnabled(false);
        shop_list.setNestedScrollingEnabled(false);
        guess_like_list.setNestedScrollingEnabled(false);
        shop_list.setHasFixedSize(true);
        shop_detail_img.setHasFixedSize(true);
        guess_like_list.setHasFixedSize(true);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
