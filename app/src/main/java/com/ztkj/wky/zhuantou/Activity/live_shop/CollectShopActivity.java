package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CollectShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectShopActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.rela_confirm_shop)
    RelativeLayout rela_confirm_shop;
    CollectShopAdapter collectShopAdapter;
    @BindView(R.id.collectList)
    RecyclerView recyclerView;
    List<ShopBean> list = new ArrayList<>();
    @BindView(R.id.ll_is_all_selelct)
    LinearLayout ll_is_all_selelct;
    @BindView(R.id.is_select_buy)
    ImageView is_select_buy;
    @BindView(R.id.all_select)
    TextView all_select;
    @BindView(R.id.delete_shop)
    TextView delete_shop;

    public static void start(Context context) {
        Intent starter = new Intent(context, CollectShopActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_shop);
        ButterKnife.bind(this);
        layoutTitleTv.setText("收藏家");
        layoutBack.setOnClickListener(this);
        more.setOnClickListener(this);
        delete_shop.setOnClickListener(this);
        more.setText("管理");
        more.setVisibility(View.VISIBLE);
        ll_is_all_selelct.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectShopActivity.this));
        collectShopAdapter = new CollectShopAdapter(CollectShopActivity.this);
        recyclerView.setAdapter(collectShopAdapter);
        collectShopAdapter.setCollectListen(new CollectShopAdapter.CollectDelete() {
            @Override
            public void collectDelete(boolean deleteAll) {
                if (deleteAll) {
                    is_select_buy.setSelected(true);
                } else {
                    is_select_buy.setSelected(false);
                }
            }
        });
        initData();

    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            ShopBean shopBean = new ShopBean();
            shopBean.setName("11111");
            shopBean.setSelect(false);
            list.add(shopBean);
        }
        collectShopAdapter.setData(list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                if (more.getText().toString().equals("管理")) {
                    rela_confirm_shop.setVisibility(View.VISIBLE);
                    more.setText("取消");

                } else {
                    more.setText("管理");
                    rela_confirm_shop.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_is_all_selelct:
                if (isAll) {
                    isAll = false;
                    is_select_buy.setSelected(false);
                    collectShopAdapter.cancelSelectAll();
                } else {
                    isAll = true;
                    is_select_buy.setSelected(true);
                    collectShopAdapter.selectAll();
                }

                break;
            case R.id.delete_shop:
                deleteShop();
                break;
        }
    }

    private void deleteShop() {
      collectShopAdapter.getSelectData();
    }

    boolean isAll = false;


    public class ShopBean {
        private String name;
        private boolean isSelect;

        @Override
        public String toString() {
            return "ShopBean{" +
                    "name='" + name + '\'' +
                    ", isSelect=" + isSelect +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
