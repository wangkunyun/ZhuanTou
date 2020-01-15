package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JiFenDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JfSuceesDetailActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.uv)
    TextView uv;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.iv_shop)
    ImageView ivShop;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_type_shop)
    TextView tvTypeShop;
    @BindView(R.id.enter_iv)
    ImageView enterIv;
    @BindView(R.id.constrainShop)
    ConstraintLayout constrainShop;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    JiFenDetail jiFenDetail;
    String gid;
    @BindView(R.id.tv_add)
    TextView tv_add;
    String shopPic;

    public static void start(Context context, String gid, String shopPic) {
        Intent starter = new Intent(context, JfSuceesDetailActivity.class);
        starter.putExtra("gid", gid);
        starter.putExtra("shopPic", shopPic);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jf_sucees_detail);
        ButterKnife.bind(this);
        gid = getIntent().getStringExtra("gid");
        shopPic = getIntent().getStringExtra("shopPic");
        layoutTitleTv.setText("兑换详情");
        initData();
    }

    private void initData() {
        OkHttpUtils.post().url(Contents.BASE + Contents.jinfenDetail)
                .addParams("gid", gid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            jiFenDetail = new Gson().fromJson(response, JiFenDetail.class);
                            if (jiFenDetail != null) {
                                Glide.with(JfSuceesDetailActivity.this).load(shopPic).into(ivShop);
                                tvShopName.setText(jiFenDetail.getData().getGname());
                                tvTypeShop.setText(jiFenDetail.getData().getSku() + "  " + jiFenDetail.getData().getIntegral());
                                tvAddressName.setText(jiFenDetail.getData().getConsignee());
                                tvAddressPhone.setText(jiFenDetail.getData().getPhone());
                                tv_add.setText(jiFenDetail.getData().getAddress());
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.layout_back, R.id.enter_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:

                finish();
                break;
            case R.id.enter_iv:
//                ShopDetailActivity.start(this,jiFenDetail.getData().get);

                break;
        }
    }
}
