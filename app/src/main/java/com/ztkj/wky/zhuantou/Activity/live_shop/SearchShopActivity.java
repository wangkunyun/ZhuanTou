package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.store.StoreAllShopFragment;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.adapter.SearchShopAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ShopSearchBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Collections.emptyList;

public class SearchShopActivity extends AppCompatActivity implements StoreAllShopFragment.ShopOpenDrawerListen, View.OnClickListener {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.mDrawer)
    DrawerLayout mDrawer;
    String shopSearch;
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    StoreAllShopFragment shopFragment;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearch_edt;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    public static void start(Context context, String shopSearchBean) {
        Intent starter = new Intent(context, SearchShopActivity.class);
        starter.putExtra("shopSearch", shopSearchBean);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);
        ButterKnife.bind(this);
        shopSearch = getIntent().getStringExtra("shopSearch");
        if (shopSearch != null) {
            shopFragment = StoreAllShopFragment.newInstance(shopSearch);
            transaction.add(R.id.fragmentShopList, shopFragment);
            transaction.commitAllowingStateLoss();
        }
        layoutBack.setOnClickListener(this);
        bigsearch_edt.setOnClickListener(this);
    }


    @Override
    public void open() {
        if (mDrawer != null) {
            mDrawer.openDrawer(Gravity.END);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bigsearch_edt:
                SearchShopsActivity.start(SearchShopActivity.this);
                break;
            case R.id.layout_back:
                finish();
                break;
        }
    }
}
