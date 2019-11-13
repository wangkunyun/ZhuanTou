package com.ztkj.wky.zhuantou.Activity.live_shop.store;
/**
 * 作者：wky
 * 功能描述：商家店铺
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopStoreActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_store);
        ButterKnife.bind(this);

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(0)
                .addTabItem("写申请1", R.mipmap.icon_wrt_select, StoreHomePageFragment.class)
                .addTabItem("写申请2", R.mipmap.icon_wrt_select, StoreAllShopFragment.class)
                .addTabItem("写申请3", R.mipmap.icon_wrt_select, StoreShopClassFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
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

}
