package com.ztkj.wky.zhuantou.Activity.live_shop.store;
/**
 * 作者：wky
 * 功能描述：商家店铺
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopStoreActivity extends AppCompatActivity implements StoreAllShopFragment.ShopOpenDrawerListen {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearchEdt;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.img_StoreHead)
    ImageView imgStoreHead;
    @BindView(R.id.tv_StoreName)
    TextView tvStoreName;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @SuppressLint("StaticFieldLeak")
    public static DrawerLayout mDrawer;
    @BindView(R.id.reView)
    RecyclerView reView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_store);
        ButterKnife.bind(this);

        mDrawer = findViewById(R.id.mDrawer);
        // 禁止手势滑动
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        ImmersionBar.with(this)
//                .init();
        reView.setLayoutManager(new LinearLayoutManager(this));
        reView.setAdapter(new mAdapter());
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(250, 120)
                .setFontSize(0)
                .addTabItem("写申请1", R.mipmap.icon_store_home, R.mipmap.icon_store_home2, StoreHomePageFragment.class)
                .addTabItem("写申请2", R.mipmap.icon_store_allshop, R.mipmap.icon_store_allshop2, StoreAllShopFragment.class)
                .addTabItem("写申请3", R.mipmap.icon_store_classiy, StoreShopClassFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ShopStoreActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.bigsearch_edt, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.bigsearch_edt:

                break;
            case R.id.more:
                break;
        }
    }

    @Override
    public void open() {
        if (mDrawer != null) {
            mDrawer.openDrawer(Gravity.END);
        }
    }


    class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(ShopStoreActivity.this).inflate(R.layout.item_layout_screen_out, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.reItem.setLayoutManager(new GridLayoutManager(ShopStoreActivity.this, 3));
            viewHolder.reItem.setAdapter(new mAdapterIn());
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView reItem;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                reItem = itemView.findViewById(R.id.reItem);
            }
        }
    }

    class mAdapterIn extends RecyclerView.Adapter<mAdapterIn.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(ShopStoreActivity.this).inflate(R.layout.item_layout_screen_in, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

}
