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

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CollectShopAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.CollecShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectShopActivity extends AppCompatActivity implements View.OnClickListener {


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

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_shop);
        ButterKnife.bind(this);
        uid = SPUtils.getInstance().getString("uid");
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
            public void collectDelete(boolean deleteAll, String postion) {
                isAllDelete = deleteAll;
                if (isAllDelete) {
                    is_select_buy.setSelected(true);
                } else {
                    if (postion != null) {
                        deleteSingleShop = list.get(Integer.parseInt(postion)).getSc_id();
                    }
                    is_select_buy.setSelected(false);

                }
            }
        });
        initData();

    }

    String deleteSingleShop;
    boolean isAllDelete;
    CollecShopBean collecShopBean;
    int page = 1;
    List<CollecShopBean.DataBean> list = new ArrayList<>();

    private void initData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getCollectList)
                .addParams("uid", uid)
                .addParams("page", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        collecShopBean = new Gson().fromJson(response, CollecShopBean.class);
                        if (collecShopBean != null) {
                            if (collecShopBean.getErrno().equals("200")) {
                                list = collecShopBean.getData();
                                collectShopAdapter.setData(list);
                            }
                        }
                    }
                });
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
                    isAllDelete=false;
                    is_select_buy.setSelected(false);
                    collectShopAdapter.cancelSelectAll();
                } else {
                    isAll = true;
                    isAllDelete=true;
                    is_select_buy.setSelected(true);
                    collectShopAdapter.selectAll();
                }

                break;
            case R.id.delete_shop:
                collectShopAdapter.getSelectData();
                if (isAll&&isAllDelete) {
                    rela_confirm_shop.setVisibility(View.GONE);
                    deleteShop();
                } else {
                    delete_SinleShop();
                }
                break;
        }
    }

    private void delete_SinleShop() {
        Log.e("dsfas",deleteSingleShop+""+uid);
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.clearSingleShop)
                .addParams("sc_id", deleteSingleShop)
                .addParams("sc_user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("logSingle", e.getMessage());

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("logSingle", response);
                    }
                });
    }

    private void deleteShop() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.clearCollect)
                .addParams("sc_user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("logAll", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("logAll", response);
                    }
                });
    }

    boolean isAll = false;

}
