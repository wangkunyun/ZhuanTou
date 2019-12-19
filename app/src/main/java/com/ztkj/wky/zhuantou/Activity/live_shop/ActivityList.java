package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.LiveShopAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ShopHomeBean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityList extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_back)
    ImageView layouyBack;
    @BindView(R.id.layout_title_tv)
    TextView layout_title_tv;
    int type;
    @BindView(R.id.recycle)
    RecyclerView recyclerView;
    ShopHomeBean shopHomeBean;
    List<ShopHomeBean.DataBean> list;
    public static void start(Context context, int type) {
        Intent starter = new Intent(context, ActivityList.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        layouyBack.setOnClickListener(this);
        layout_title_tv.setText("活动专区");
        type = getIntent().getIntExtra("type", 0);
        initData();
    }

    private void initData() {
        OkHttpUtils.post().url(Contents.SHOPBASE+Contents.getSelectList)
                .addParams("sac_saa_id",String.valueOf(type))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {
                        shopHomeBean=new Gson().fromJson(response,ShopHomeBean.class);
                        if(shopHomeBean!=null&&shopHomeBean.getErrno().equals("200")){
                            list=shopHomeBean.getData();
                            if(list!=null&&list.size()>0){
                                LiveShopAdapter liveShopAdapter = new LiveShopAdapter(ActivityList.this, list);
                                recyclerView.setLayoutManager(new GridLayoutManager(ActivityList.this, 2));
                                recyclerView.setAdapter(liveShopAdapter);
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

        }
    }
}
